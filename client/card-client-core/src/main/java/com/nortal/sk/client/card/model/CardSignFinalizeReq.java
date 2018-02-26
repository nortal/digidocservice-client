package com.nortal.sk.client.card.model;

import com.nortal.sk.common.model.GeneralReq;

public interface CardSignFinalizeReq extends GeneralReq {
    String getSignatureHex();
}
