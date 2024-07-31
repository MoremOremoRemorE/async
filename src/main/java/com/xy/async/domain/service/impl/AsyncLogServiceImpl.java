package com.xy.async.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.async.domain.entity.AsyncLog;
import com.xy.async.domain.service.AsyncLogService;
import com.xy.async.mapper.AsyncLogMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 异步执行日志接口实现
 *
 * @author xiongyan
 * @date 2021/01/08
 */
@Service
public class AsyncLogServiceImpl extends ServiceImpl<AsyncLogMapper, AsyncLog> implements AsyncLogService {


    @Override
    public void saveLog(AsyncLog asyncLog) {
        asyncLog.setCreateTime(new Date());
        this.save(asyncLog);
    }

    @Override
    public void deleteLog(Long asyncId) {
        this.removeById(asyncId);
    }

    @Override
    public String getErrorData(Long asyncId) {
        return this.baseMapper.selectOne(new QueryWrapper<AsyncLog>().lambda().eq(AsyncLog::getAsyncId, asyncId).orderByDesc(AsyncLog::getId).last("limit 1")).getErrorData();
    }
}
