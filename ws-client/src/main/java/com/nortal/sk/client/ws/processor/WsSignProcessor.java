package com.nortal.sk.client.ws.processor;

import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.processor.SignStateHolder;

public interface WsSignProcessor extends WsProcessor<SignStateHolder> {
    String calculateDigest(FileModel file, String digestType) throws Exception;

    String toWSFormat() throws Exception;

    void fromWSFormat(String data) throws Exception;
}
