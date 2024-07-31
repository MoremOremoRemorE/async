package com.xy.async.domain.service.impl;

import com.xy.async.annotation.AsyncExec;
import com.xy.async.domain.service.TestService;
import com.xy.async.enums.AsyncTypeEnum;
import org.springframework.stereotype.Service;

/**
 * @Author: jinchaoqun|
 * @Date: 2024/7/31 16:08
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    @AsyncExec(type = AsyncTypeEnum.SAVE_ASYNC, delayTime = 1000, remark = "测试")
    public String test() {
        int i = 1 / 0;
        return "hello";
    }
}
