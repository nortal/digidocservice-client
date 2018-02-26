package com.nortal.sk.client.card.step;

import com.nortal.sk.client.card.processor.CardSignProcessor;
import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.step.AbstractStep;
import com.nortal.sk.common.constant.StatusEnum;
import com.nortal.sk.common.model.GeneralReq;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.common.model.StatusRspImpl;

public class SignatureConfirmationStep extends AbstractStep<CardSignProcessor, GeneralReq> {

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.SIGNATURE_CONFIRMATION;
    }

    @Override
    protected Class<? extends GeneralReq> getReqImpl() {
        return null;
    }

    @Override
    protected GeneralRsp innerExecute(CardSignProcessor processor, GeneralReq req) throws Exception {
        processor.getSignatureConfirmation();
        return StatusRspImpl.of(StatusEnum.OK);
    }
}
