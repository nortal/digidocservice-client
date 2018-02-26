package com.nortal.sk.client.card.step;

import com.nortal.sk.client.card.model.CardSignFinalizeReq;
import com.nortal.sk.client.card.model.CardSignFinalizeReqImpl;
import com.nortal.sk.client.card.processor.CardSignProcessor;
import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.step.AbstractStep;
import com.nortal.sk.common.constant.StatusEnum;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.common.model.StatusRspImpl;

public class CardSignFinalizeStep extends AbstractStep<CardSignProcessor, CardSignFinalizeReq> {

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.CARD_SIGN_FINALIZE;
    }

    @Override
    protected Class<? extends CardSignFinalizeReq> getReqImpl() {
        return CardSignFinalizeReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(CardSignProcessor processor, CardSignFinalizeReq req) throws Exception {
        processor.applySignature(req.getSignatureHex());
        return StatusRspImpl.of(StatusEnum.OK);
    }

    public static CardSignFinalizeStep of(CardSignFinalizeReq req) {
        CardSignFinalizeStep step = new CardSignFinalizeStep();
        step.setReq(req);
        return step;
    }
}
