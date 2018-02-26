package com.nortal.sk.client.ws.processor;

import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.processor.StateProcessor;
import com.nortal.sk.ws.WsClient;

public interface WsProcessor<T extends StateHolder> extends StateProcessor<T> {
    WsClient getWsClient();
}
