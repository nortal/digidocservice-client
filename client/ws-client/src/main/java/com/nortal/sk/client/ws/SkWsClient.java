package com.nortal.sk.client.ws;

import com.nortal.sk.client.SkClient;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.StateHolder;

public interface SkWsClient extends SkClient {
    Processor createAuthProcessor(StateHolder state);
}
