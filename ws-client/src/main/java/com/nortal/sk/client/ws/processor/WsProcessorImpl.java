package com.nortal.sk.client.ws.processor;

import com.nortal.sk.client.processor.AbstractProcessor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.ws.SkWs;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class WsProcessorImpl<T extends StateHolder> extends AbstractProcessor<T> implements WsProcessor<T> {
    private SkWs wsClient;

    public WsProcessorImpl(SkWs wsClient) {
        this.wsClient = wsClient;
    }

    @Override
    public SkWs getWsClient() {
        return wsClient;
    }
}
