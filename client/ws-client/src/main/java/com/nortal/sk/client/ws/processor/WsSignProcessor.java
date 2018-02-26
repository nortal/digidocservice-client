package com.nortal.sk.client.ws.processor;

import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;

import eu.europa.ec.markt.dss.DigestAlgorithm;

public interface WsSignProcessor extends WsProcessor<SignStateHolder> {
    String calculateDigest(FileModel file, DigestAlgorithm digestAlgorithm) throws Exception;

    String toWSFormat() throws Exception;

    void fromWSFormat(String data) throws Exception;

    Processor simple(String idCode, String phoneNo);
}
