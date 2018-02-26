package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsAuthProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.MobileAuthenticateReq;
import com.nortal.sk.ws.model.MobileAuthenticateReqImpl;

public class MobileAuthenticateStep extends AbstractWsStep<WsAuthProcessor, MobileAuthenticateReq> {
    //  {
    //    setCode(StepCodeEnum.MOBILE_AUTHENTICATE);
    //    setType(MobileAuthenticateReqImpl.class);
    //    setReturning(true);
    //  }

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.MOBILE_AUTHENTICATE;
    }

    @Override
    protected Class<? extends MobileAuthenticateReq> getReqImpl() {
        return MobileAuthenticateReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(WsAuthProcessor processor, MobileAuthenticateReq req) throws Exception {
        return processor.getWsClient().mobileAuthenticate(req);
    }

    public static MobileAuthenticateStep of(MobileAuthenticateReq req) {
        MobileAuthenticateStep step = new MobileAuthenticateStep();
        step.setReq(req);
        return step;
    }
}
