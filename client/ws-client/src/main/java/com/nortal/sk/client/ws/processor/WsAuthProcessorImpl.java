package com.nortal.sk.client.ws.processor;

import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.ws.WsClient;

public class WsAuthProcessorImpl extends AbstractWsProcessor<StateHolder>implements WsAuthProcessor {
    public WsAuthProcessorImpl(StateHolder state, WsClient wsClient) {
        super(state, wsClient);
    }
}
