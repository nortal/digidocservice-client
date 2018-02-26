package com.nortal.sk.client.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.common.model.GeneralRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface Step {
    StepCodeEnum getCode();

    GeneralRsp execute(Processor<?> processor) throws Exception;

    boolean isReturning() throws Exception;

    boolean isValid(StateHolder state) throws Exception;

    boolean isComplete(StateHolder state) throws Exception;
}
