package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.ws.processor.WsProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.constant.MobileAuthStatusEnum;
import com.nortal.sk.ws.model.MobileAuthenticateStatusReq;
import com.nortal.sk.ws.model.MobileAuthenticateStatusReqImpl;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileAuthenticateStatusStep extends AbstractWsStep<WsProcessor<StateHolder>, MobileAuthenticateStatusReq> {
    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.MOBILE_AUTHENTICATE_STATUS;
    }

    @Override
    protected Class<? extends MobileAuthenticateStatusReq> getReqImpl() {
        return MobileAuthenticateStatusReqImpl.class;
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Enum getValidStatus() {
        return MobileAuthStatusEnum.USER_AUTHENTICATED;
    }

    @Override
    public boolean isReturning() throws Exception {
        return !getReq().isWaitSignature();
    }

    @Override
    protected GeneralRsp innerExecute(WsProcessor<StateHolder> processor, MobileAuthenticateStatusReq req) throws Exception {
        return processor.getWsClient().getMobileAuthenticateStatus(req);
    }

    public static MobileAuthenticateStatusStep of(MobileAuthenticateStatusReq req) {
        MobileAuthenticateStatusStep step = new MobileAuthenticateStatusStep();
        step.setReq(req);
        return step;
    }
}
