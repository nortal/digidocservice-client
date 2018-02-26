package com.nortal.sk.client.card;

import com.nortal.sk.client.SkClient;
import com.nortal.sk.client.card.model.CardSignFinalizeReqImpl;
import com.nortal.sk.client.card.model.CardSignStartReqImpl;
import com.nortal.sk.client.card.step.CardSignFinalizeStep;
import com.nortal.sk.client.card.step.CardSignStartStep;
import com.nortal.sk.client.model.CardSignDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;

public abstract class AbstractCardSignClient implements SkClient<CardSignDataModel> {

    @Override
    public Processor<SignStateHolder> createSimpleSignProcessor(CardSignDataModel data) throws Exception {
        // @formatter:off
        return createSignProcessor()
                   .step(CardSignStartStep.of(CardSignStartReqImpl.of(data.getCertHex())))
                   .step(CardSignFinalizeStep.of(CardSignFinalizeReqImpl.of(data.getSignatureValue())));
        // @formatter:on
    }
}
