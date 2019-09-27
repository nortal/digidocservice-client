package com.nortal.sk.client.step;

import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.common.constant.StatusEnum;
import com.nortal.sk.common.model.GeneralReq;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.common.model.StatusRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public abstract class AbstractStep<T extends Processor<?>, V extends GeneralReq> implements Step {
    private V req;

    protected V getReq() throws Exception {
        if (req == null && getReqImpl() != null) {
            req = getReqImpl().newInstance();
        }
        return req;
    }

    public void setReq(V input) {
        this.req = input;
    }

    @Override
    public boolean isReturning() throws Exception {
        return false;
    }

    @SuppressWarnings("rawtypes")
    protected Enum getValidStatus() {
        return StatusEnum.OK;
    }

    @SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
    protected boolean isValid(GeneralRsp rsp) {
        try {
            Enum validStatus = getValidStatus();
            return validStatus.equals(validStatus.valueOf(validStatus.getClass(), ((StatusRsp) rsp).getStatus()));
        }
        catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean isValid(StateHolder state) throws Exception {
        return isValid((GeneralRsp) state.getResponse(getCode()));
    }

    @Override
    public boolean isComplete(StateHolder state) throws Exception {
        return isValid(state);
    }

    @Override
    @SuppressWarnings("unchecked")
    public GeneralRsp execute(Processor<?> processor) throws Exception {
        return innerExecute((T) processor, req);
    }

    protected abstract Class<? extends V> getReqImpl();

    protected abstract GeneralRsp innerExecute(T processor, V req) throws Exception;
}
