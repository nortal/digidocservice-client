package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.ws.processor.WsProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.MobileAuthenticateReq;
import com.nortal.sk.ws.model.MobileAuthenticateReqImpl;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileAuthenticateStep extends AbstractWsStep<WsProcessor<StateHolder>, MobileAuthenticateReq> {
    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.MOBILE_AUTHENTICATE;
    }

    @Override
    protected Class<? extends MobileAuthenticateReq> getReqImpl() {
        return MobileAuthenticateReqImpl.class;
    }

    @Override
    public boolean isReturning() throws Exception {
        return true;
    }

    @Override
    protected GeneralRsp innerExecute(WsProcessor<StateHolder> processor, MobileAuthenticateReq req) throws Exception {
        return processor.getWsClient().mobileAuthenticate(req);
    }

    public static MobileAuthenticateStep of(MobileAuthenticateReq req) {
        MobileAuthenticateStep step = new MobileAuthenticateStep();
        step.setReq(req);
        return step;
    }
}
