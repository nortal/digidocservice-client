package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.MobileSignReq;
import com.nortal.sk.ws.model.MobileSignReqImpl;

public class MobileSignStep extends AbstractWsStep<WsSignProcessor, MobileSignReq> {
    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.MOBILE_SIGN;
    }

    @Override
    protected Class<? extends MobileSignReq> getReqImpl() {
        return MobileSignReqImpl.class;
    }

    @Override
    public boolean isReturning() throws Exception {
        return true;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, MobileSignReq req) throws Exception {
        return processor.getWsClient().mobileSign(req);
    }

    public static MobileSignStep of(MobileSignReq req) {
        MobileSignStep step = new MobileSignStep();
        step.setReq(req);
        return step;
    }
}
