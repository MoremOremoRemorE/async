package com.xy.async.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.async.domain.entity.AsyncLog;

/**
 * 异步执行日志接口
 *
 * @author xiongyan
 * @date 2021/01/08
 */
public interface AsyncLogService extends IService<AsyncLog> {

    /**
     * 保存
     *
     * @param asyncLog
     */
    void saveLog(AsyncLog asyncLog);

    /**
     * 删除
     *
     * @param asyncId
     */
    void deleteLog(Long asyncId);

    /**
     * 获取最后一次失败信息
     * 
     * @param asyncId
     * @return
     */
    String getErrorData(Long asyncId);
}
