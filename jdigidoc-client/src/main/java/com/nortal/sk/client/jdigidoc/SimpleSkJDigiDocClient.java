package com.nortal.sk.client.jdigidoc;

import javax.annotation.PostConstruct;

import com.nortal.sk.client.card.AbstractCardSignClient;
import com.nortal.sk.client.jdigidoc.processor.JDigiDocSignProcessor;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;

import ee.sk.utils.ConfigManager;

public class SimpleSkJDigiDocClient extends AbstractCardSignClient {
    private String configLocation = "jar://jdigidoc.cfg";

    @PostConstruct
    private void init() {
        if (ConfigManager.init(configLocation)) {
            ConfigManager.addProvider();
        }
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    @Override
    public Processor<SignStateHolder> createSignProcessor() {
        return new JDigiDocSignProcessor();
    }
}
