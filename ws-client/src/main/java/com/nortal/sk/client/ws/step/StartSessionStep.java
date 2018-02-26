package com.nortal.sk.client.ws.step;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.StartSessionReq;
import com.nortal.sk.ws.model.StartSessionReqImpl;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class StartSessionStep extends AbstractWsStep<WsSignProcessor, StartSessionReq> {
    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.START_SESSION;
    }

    @Override
    protected Class<? extends StartSessionReq> getReqImpl() {
        return StartSessionReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, StartSessionReq req) throws Exception {
        if (StringUtils.isBlank(req.getSigDocXML())) {
            req.setSigDocXML(processor.toWSFormat());
        }
        return processor.getWsClient().startSession(req);
    }
}
