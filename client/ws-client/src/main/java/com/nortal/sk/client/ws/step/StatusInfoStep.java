package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.constant.MobileSignStatusEnum;
import com.nortal.sk.ws.model.StatusInfoReq;
import com.nortal.sk.ws.model.StatusInfoReqImpl;
import com.nortal.sk.ws.model.StatusInfoRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class StatusInfoStep extends AbstractWsStep<WsSignProcessor, StatusInfoReq> {
    //  {
    //    setCode(StepCodeEnum.STATUS_INFO);
    //    setValidStatus(StatusEnum.OK);
    //    setType(StatusInfoReqImpl.class);
    //  }

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.STATUS_INFO;
    }

    @Override
    protected Class<? extends StatusInfoReq> getReqImpl() {
        return StatusInfoReqImpl.class;
    }

    @Override
    public boolean isReturning() throws Exception {
        return !getReq().isWaitSignature();
    }

    @Override
    public boolean isComplete(StateHolder state) throws Exception {
        if (!super.isComplete(state)) {
            return false;
        }
        StatusInfoRsp rsp = state.getResponse(getCode());
        return MobileSignStatusEnum.SIGNATURE.equals(MobileSignStatusEnum.valueOf(rsp.getStatusCode()));
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, StatusInfoReq req) throws Exception {
        //    StatusInfoReq req = prepareRequest();
        //    setReturning(!req.isWaitSignature());
        return processor.getWsClient().getStatusInfo(req);
    }

    public static StatusInfoStep of(StatusInfoReq req) {
        StatusInfoStep step = new StatusInfoStep();
        step.setReq(req);
        return step;
    }
}
