package com.nortal.sk.client.ws.step;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.SesscodeReq;
import com.nortal.sk.ws.model.SesscodeReqImpl;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
@Deprecated
public class CloseSessionStep extends AbstractWsStep<WsSignProcessor, SesscodeReq> {
    //  {
    //    setCode(StepCodeEnum.CLOSE_SESSION);
    //    setValidStatus(StatusEnum.OK);
    //    setType(SesscodeReqImpl.class);
    //  }

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.CLOSE_SESSION;
    }

    @Override
    protected Class<? extends SesscodeReq> getReqImpl() {
        return SesscodeReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, SesscodeReq req) throws Exception {
        return processor.getWsClient().closeSession(req.getSesscode());
    }

}
