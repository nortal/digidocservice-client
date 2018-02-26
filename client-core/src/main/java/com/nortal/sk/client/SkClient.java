package com.nortal.sk.client;

import com.nortal.sk.client.model.SignDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;

public interface SkClient<T extends SignDataModel> {
    Processor<SignStateHolder> createSignProcessor() throws Exception;

    Processor<SignStateHolder> createSimpleSignProcessor(T data) throws Exception;
}
