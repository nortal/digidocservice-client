package com.nortal.sk.client.ws;

import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.ws.processor.WsAuthProcessorImpl;
import com.nortal.sk.client.ws.processor.WsSignProcessorImpl;
import com.nortal.sk.ws.WsClient;

public class SimpleSkWsClient implements SkWsClient {
    private WsClient wsClient;

    @Override
    public Processor createAuthProcessor(StateHolder state) {
        return new WsAuthProcessorImpl(state, wsClient);
    }

    @Override
    public Processor createSignProcessor(SignStateHolder state) {
        return new WsSignProcessorImpl(state, wsClient);
    }

    public void setWsClient(WsClient wsClient) {
        this.wsClient = wsClient;
    }
}
