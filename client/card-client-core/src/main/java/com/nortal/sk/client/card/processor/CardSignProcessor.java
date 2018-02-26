package com.nortal.sk.client.card.processor;

import com.nortal.sk.client.card.model.CardSignStartReq;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.client.processor.StateProcessor;

public interface CardSignProcessor extends StateProcessor<SignStateHolder> {
    void applySignature(String signatureHex) throws Exception;

    String getDigestHex(CardSignStartReq req) throws Exception;

    void getSignatureConfirmation() throws Exception;
}
