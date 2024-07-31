package com.xy.async.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 异步执行实体类
 *
 * @author xiongyan
 * @date 2021/01/08
 */
@Data
public class AsyncReq implements Serializable {

    private static final long serialVersionUID = 7606071967983038048L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 方法签名
     */
    private String sign;

    /**
     * 全路径类名称
     */
    private String className;

    /**
     * method名称
     */
    private String methodName;

    /**
     * 异步策略类型
     */
    private String asyncType;

    /**
     * 执行状态 0：未处理 1：处理失败
     */
    private Integer execStatus;

    /**
     * 执行次数
     */
    private Integer execCount;

    /**
     * 参数json字符串
     */
    private String paramJson;

    /**
     * 业务描述
     */
    private String remark;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
