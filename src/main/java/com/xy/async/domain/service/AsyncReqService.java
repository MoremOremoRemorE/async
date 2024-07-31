package com.xy.async.domain.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.async.domain.entity.AsyncLog;
import com.xy.async.domain.entity.AsyncReq;
import com.xy.async.dto.PageInfoDto;

/**
 * 异步执行接口
 *
 * @author xiongyan
 * @date 2021/01/08
 */
public interface AsyncReqService extends IService<AsyncReq> {

    /**
     * 保存
     * 
     * @param asyncReq
     */
    void saveReq(AsyncReq asyncReq);

    /**
     * 更新状态
     * 
     * @param id
     * @param execStatus
     */
    void updateStatus(String id, Integer execStatus);

    /**
     * 删除
     *
     * @param id
     */
    void deleteReq(String id);

    /**
     * 根据ID查询
     * 
     * @param id
     * @return
     */
    AsyncReq getByIdReq(String id);

    /**
     * 自动重试
     *
     * @return
     */
    List<AsyncReq> listRetry();

    /**
     * 自动补偿
     *
     * @return
     */
    List<AsyncReq> listComp();

    /**
     * 人工执行
     *
     * @param pageInfo
     */
    void listAsyncPage(PageInfoDto<AsyncReq> pageInfo);
}
