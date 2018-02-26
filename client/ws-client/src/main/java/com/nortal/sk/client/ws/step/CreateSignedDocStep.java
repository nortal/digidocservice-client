package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.constant.StatusEnum;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.common.model.StatusRspImpl;
import com.nortal.sk.ws.model.CreateSignedDocReq;
import com.nortal.sk.ws.model.CreateSignedDocReqImpl;

public class CreateSignedDocStep extends AbstractWsStep<WsSignProcessor, CreateSignedDocReq> {

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.CREATE_SIGNED_DOC;
    }

    @Override
    protected Class<? extends CreateSignedDocReq> getReqImpl() {
        return CreateSignedDocReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, CreateSignedDocReq req) throws Exception {
        // Skip if container is present
        if (processor.getState().getDoc() != null) {
            return StatusRspImpl.of(StatusEnum.OK);
        }
        return processor.getWsClient().createSignedDoc(req);
    }
}
