package com.nortal.sk.client.card.processor;

import com.nortal.sk.client.card.model.CardSignStartReq;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;

public interface CardSignProcessor extends Processor<SignStateHolder> {
    void applySignature(String signatureHex) throws Exception;

    String getDigestHex(CardSignStartReq req) throws Exception;

    void getSignatureConfirmation() throws Exception;
}
