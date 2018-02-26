package com.nortal.sk.client.card.step;

import com.nortal.sk.client.card.processor.CardSignProcessor;
import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.step.AbstractStep;
import com.nortal.sk.common.constant.StatusEnum;
import com.nortal.sk.common.model.GeneralReq;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.common.model.StatusRspImpl;

public class SignatureConfirmationStep extends AbstractStep<CardSignProcessor, GeneralReq> {
    //  {
    //    setCode(StepCodeEnum.SIGNATURE_CONFIRMATION);
    //  }

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
        //    SignedDoc doc = getState().getDoc();
        //    Signature sig = doc.getSignature(doc.getSignatures().size() - 1);
        //    sig.getConfirmation();
        processor.getSignatureConfirmation();
        return StatusRspImpl.of(StatusEnum.OK);
    }
}
