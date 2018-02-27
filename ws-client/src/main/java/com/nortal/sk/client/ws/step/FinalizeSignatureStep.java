package com.nortal.sk.client.ws.step;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.FinalizeSignatureReq;
import com.nortal.sk.ws.model.FinalizeSignatureReqImpl;
import com.nortal.sk.ws.model.PrepareSignatureRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class FinalizeSignatureStep extends AbstractWsStep<WsSignProcessor, FinalizeSignatureReq> {

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.FINALIZE_SIGNATURE;
    }

    @Override
    protected Class<? extends FinalizeSignatureReq> getReqImpl() {
        return FinalizeSignatureReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, FinalizeSignatureReq req) throws Exception {
        if (StringUtils.isBlank(req.getSignatureId())) {
            req.setSignatureId(((PrepareSignatureRsp) processor.getState().getResponse(StepCodeEnum.PREPARE_SIGNATURE)).getSignatureId());
        }
        return processor.getWsClient().finalizeSignature(req);
    }

    public static FinalizeSignatureStep of(FinalizeSignatureReq req) {
        FinalizeSignatureStep step = new FinalizeSignatureStep();
        step.setReq(req);
        return step;
    }
}
