package com.nortal.sk.client.processor;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.step.Step;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public abstract class AbstractProcessor<T extends StateHolder> implements Processor<T> {
    private T state;
    private Map<StepCodeEnum, Step> steps = new LinkedHashMap<>();

    @Override
    public T getState() {
        return state;
    }

    @Override
    public Processor<T> step(Step step) {
        if (steps.containsKey(step.getCode())) {
            throw new RuntimeException("Processor already have step " + step.getCode() + " included");
        }
        steps.put(step.getCode(), step);
        return this;
    }

    protected void setState(T state) {
        this.state = state;
    }

    protected boolean isComplete() {
        return state.isComplete();
    }

    protected boolean isPreparing() {
        return StepCodeEnum.PREPARING == state.getActiveStep();
    }

    protected void prepare() throws Exception {
        // If there anything to prepare
    }

    protected void finalize() throws Exception {
        // If there anything to finalize
    }

    private boolean isLast(Step step) {
        return Arrays.asList(steps.keySet().toArray()).indexOf(step.getCode()) == steps.size() - 1;
    }

    private boolean isReturning(Step step) throws Exception {
        return !step.isValid(getState()) || (!isLast(step) && step.isReturning());
    }

    @Override
    public T process(T state) throws Exception {
        setState((T) state);
        if (isComplete()) {
            return getState();
        }

        if (isPreparing()) {
            prepare();
        }

        int pos = 0;
        for (Step step : steps.values()) {
            pos++;
            if (step.isComplete(getState())) {
                continue;
            }
            getState().setResponse(step.getCode(), step.execute(this));

            if (isReturning(step)) {
                return getState();
            }
        }

        if (getState().isValid()) {
            finalize();
        }
        getState().complete();
        return getState();
    }
}
