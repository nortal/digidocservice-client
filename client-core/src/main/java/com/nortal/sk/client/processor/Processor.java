package com.nortal.sk.client.processor;

import com.nortal.sk.client.step.Step;

public interface Processor<T extends StateHolder> {
    T getState();

    Processor<T> step(Step step);

    T process(T state) throws Exception;
}
