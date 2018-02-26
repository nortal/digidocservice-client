package com.nortal.sk.client.card.step;

import com.nortal.sk.client.card.model.CardSignStartReq;
import com.nortal.sk.client.card.model.CardSignStartReqImpl;
import com.nortal.sk.client.card.model.CardSignStartRsp;
import com.nortal.sk.client.card.processor.CardSignProcessor;
import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.step.AbstractStep;
import com.nortal.sk.common.model.GeneralRsp;

public class CardSignStartStep extends AbstractStep<CardSignProcessor, CardSignStartReq> {
    //  {
    //    setCode(StepCodeEnum.CARD_SIGN_START);
    //    setReturning(true);
    //  }

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.CARD_SIGN_START;
    }

    @Override
    protected Class<? extends CardSignStartReq> getReqImpl() {
        return CardSignStartReqImpl.class;
    }

    @Override
    public boolean isReturning() throws Exception {
        return true;
    }

    @Override
    protected GeneralRsp innerExecute(CardSignProcessor processor, CardSignStartReq req) throws Exception {
        //    SignedDoc doc = getState().getDoc();
        //    CardSignStartReq req = getReq();
        //    Signature sig = doc.prepareSignature(req.getCert(), req.getClaimedRoles(), req.getAdr());
        //    SignedDoc.bin2hex(sig.calculateSignedInfoDigest());
        //    return CardSignStartRsp.of(SignedDoc.bin2hex(sig.calculateSignedInfoDigest()));
        return CardSignStartRsp.of(processor.getDigestHex(req));
    }

    public static CardSignStartStep of(CardSignStartReq req) {
        CardSignStartStep step = new CardSignStartStep();
        step.setReq(req);
        return step;
    }
}
