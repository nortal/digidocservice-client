package com.nortal.sk.client.ws.processor;

import com.nortal.sk.client.processor.AbstractProcessor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.ws.WsClient;

public abstract class AbstractWsProcessor<T extends StateHolder> extends AbstractProcessor<T>implements WsProcessor<T> {
    private WsClient wsClient;

    public AbstractWsProcessor(T state, WsClient wsClient) {
        setState(state);
        this.wsClient = wsClient;
    }

    @Override
    public WsClient getWsClient() {
        return wsClient;
    }
}
