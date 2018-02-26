package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.PrepareSignatureReq;
import com.nortal.sk.ws.model.PrepareSignatureReqImpl;

public class PrepareSignatureStep extends AbstractWsStep<WsSignProcessor, PrepareSignatureReq> {

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.PREPARE_SIGNATURE;
    }

    @Override
    protected Class<? extends PrepareSignatureReq> getReqImpl() {
        return PrepareSignatureReqImpl.class;
    }

    @Override
    public boolean isReturning() throws Exception {
        return true;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, PrepareSignatureReq req) throws Exception {
        return processor.getWsClient().prepareSignature(req);
    }

    public static PrepareSignatureStep of(PrepareSignatureReq req) {
        PrepareSignatureStep step = new PrepareSignatureStep();
        step.setReq(req);
        return step;
    }
}
