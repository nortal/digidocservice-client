package com.nortal.sk.client.model;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface CardSignDataModel extends SignDataModel {
    String getCertHex();

    String getSignatureValue();
}
