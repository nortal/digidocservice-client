package com.nortal.sk.client.digidoc4j;

import com.nortal.sk.client.card.AbstractCardSignClient;
import com.nortal.sk.client.digidoc4j.processor.DigiDoc4JSignProcessor;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;

// TODO: apply configuration?
public class SimpleSkDigiDoc4JClient extends AbstractCardSignClient {
    @Override
    public Processor<SignStateHolder> createSignProcessor() {
        return new DigiDoc4JSignProcessor();
    }
}
