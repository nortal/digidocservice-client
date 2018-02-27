package com.nortal.sk.client.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.common.model.FaultRsp;
import com.nortal.sk.common.model.GeneralRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
// TODO: maybe interface?
public class StateHolder {
    private String id = UUID.randomUUID().toString();
    private boolean complete;
    private Map<StepCodeEnum, Collection<GeneralRsp>> responses = new LinkedHashMap<>();

    public String getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    public <T extends GeneralRsp> T getResponse(StepCodeEnum step) {
        if (!responses.containsKey(step)) {
            return null;
        }
        Collection<GeneralRsp> stepResponses = responses.get(step);
        return (T) stepResponses.toArray(new GeneralRsp[0])[stepResponses.size() - 1];
    }

    protected void setResponse(StepCodeEnum step, GeneralRsp rsp) {
        if (!responses.containsKey(step)) {
            responses.put(step, new ArrayList<GeneralRsp>());
        }
        responses.get(step).add(rsp);
    }

    @SuppressWarnings("unchecked")
    public <T extends GeneralRsp> T getResponse(Class<T> type) {
        for (Collection<GeneralRsp> items : responses.values()) {
            for (GeneralRsp item : items) {
                if (type.isAssignableFrom(item.getClass())) {
                    return (T) item;
                }
            }
        }
        return null;
    }

    public StepCodeEnum getActiveStep() {
        if (responses.isEmpty()) {
            return StepCodeEnum.PREPARING;
        }
        Set<StepCodeEnum> keys = responses.keySet();
        return keys.toArray(new StepCodeEnum[0])[keys.size() - 1];
    }

    public <T extends GeneralRsp> T getActiveResponse() {
        return getResponse(getActiveStep());
    }

    public boolean isValid() {
        GeneralRsp rsp = getActiveResponse();
        return rsp != null ? !FaultRsp.class.isAssignableFrom(rsp.getClass()) : true;
    }

    protected void complete() {
        this.complete = true;
    }

    public boolean isComplete() {
        return complete || !isValid();
    }
}
