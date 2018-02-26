package com.nortal.sk.client.ws.processor;

import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.ws.SkWs;

public interface WsProcessor<T extends StateHolder> extends Processor<T> {
    SkWs getWsClient();
}
