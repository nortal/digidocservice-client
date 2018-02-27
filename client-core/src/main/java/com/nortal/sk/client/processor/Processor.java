package com.nortal.sk.client.processor;

import com.nortal.sk.client.step.Step;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface Processor<T extends StateHolder> {
    T getState();

    Processor<T> step(Step step);

    T process(T state) throws Exception;
}
