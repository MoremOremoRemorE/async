package com.xy.async.domain.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.async.domain.entity.AsyncReq;
import com.xy.async.domain.service.AsyncReqService;
import com.xy.async.dto.PageInfoDto;
import com.xy.async.mapper.AsyncReqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 异步执行接口实现
 *
 * @author xiongyan
 * @date 2021/01/08
 */
@Service
public class AsyncReqServiceImpl extends ServiceImpl<AsyncReqMapper, AsyncReq> implements AsyncReqService {

    @Autowired
    private AsyncReqMapper asyncReqMapper;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 最大重试执行次数：默认5次
     */
    @Value("${async.exec.count:5}")
    private int execCount;

    /**
     * 每次执行最大查询数量：默认100
     */
    @Value("${async.retry.limit:100}")
    private int retryLimit;

    /**
     * 每次补偿最大查询数量：默认100
     */
    @Value("${async.comp.limit:100}")
    private int compLimit;

    @Override
    public void saveReq(AsyncReq asyncReq) {
        asyncReq.setCreateTime(new Date());
        asyncReq.setUpdateTime(new Date());
        asyncReq.setId(IdUtil.randomUUID());
        this.save(asyncReq);
    }

    @Override
    public void updateStatus(String id, Integer execStatus) {
        UpdateWrapper<AsyncReq> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("exec_status", execStatus)
                .set("update_time", new Date())
                .setSql("exec_count = exec_count + 1")
                .eq("id", id);
        this.update(updateWrapper);
    }

    @Override
    public void deleteReq(String id) {
        this.removeById(id);
    }

    @Override
    public AsyncReq getByIdReq(String id) {
        return this.getById(id);
    }

    @Override
    public List<AsyncReq> listRetry() {
        return asyncReqMapper.listRetry(execCount, applicationName, retryLimit);
    }

    @Override
    public List<AsyncReq> listComp() {
        // 获取当前时间并减去一小时
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAgo = now.minusHours(1);

        QueryWrapper<AsyncReq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("exec_status", 0)
                .eq("exec_count", 0)
                .lt("create_time", oneHourAgo)
                .eq("application_name", applicationName)
                .orderByAsc("id")
                .last("LIMIT " + compLimit);

        return list(queryWrapper);
    }

    @Override
    public void listAsyncPage(PageInfoDto<AsyncReq> pageInfo) {
        // 创建 Page 对象，传入当前页码和每页记录数
        Page<AsyncReq> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize(),true);

        // 构建 QueryWrapper 对象，添加查询条件
        QueryWrapper<AsyncReq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("exec_status", 1)
                .ge("exec_count", execCount)
                .eq("application_name", applicationName)
                .orderByAsc("id");

        // 执行分页查询
        IPage<AsyncReq> pageVoIPage = this.page(page, queryWrapper);

        // 设置分页结果到 PageInfoDto
        pageInfo.setList(pageVoIPage.getRecords());
        pageInfo.setTotal(pageVoIPage.getTotal());
    }
}