package com.nortal.sk.client.model;

public interface CardSignDataModel extends SignDataModel {
    String getCertHex();

    String getSignatureValue();
}
