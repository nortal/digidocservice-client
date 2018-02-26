package com.nortal.sk.client.card.model;

public class CardSignFinalizeReqImpl implements CardSignFinalizeReq {
    private String signatureHex;

    @Override
    public String getSignatureHex() {
        return signatureHex;
    }

    public void setSignatureHex(String signatureHex) {
        this.signatureHex = signatureHex;
    }

    public static CardSignFinalizeReqImpl of(String signatureHex) {
        CardSignFinalizeReqImpl req = new CardSignFinalizeReqImpl();
        req.setSignatureHex(signatureHex);
        return req;
    }
}
