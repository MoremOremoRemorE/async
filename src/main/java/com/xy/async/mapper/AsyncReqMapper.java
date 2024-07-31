package com.xy.async.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.async.domain.entity.AsyncLog;
import com.xy.async.domain.entity.AsyncReq;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: jinchaoqun|
 * @Date: 2024/7/31 09:33
 */
public interface AsyncReqMapper extends BaseMapper<AsyncReq> {
    @Select("SELECT * FROM async_req WHERE exec_status = 1 AND exec_count < #{execCount} AND application_name = #{applicationName} ORDER BY id LIMIT #{limit}")
    List<AsyncReq> listRetry(@Param("execCount") int execCount,
                             @Param("applicationName") String applicationName,
                             @Param("limit") int limit);
}
