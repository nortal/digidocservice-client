package com.nortal.sk.client.digidoc4j;

import com.nortal.sk.client.SkClient;
import com.nortal.sk.client.digidoc4j.processor.DigiDoc4JSignProcessor;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;

public class SimpleSkDigiDoc4JClient implements SkClient {
    @Override
    public Processor createSignProcessor(SignStateHolder state) {
        return new DigiDoc4JSignProcessor(state);
    }
}
