package com.nortal.sk.client.ws.step;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.SesscodeReq;
import com.nortal.sk.ws.model.SesscodeReqImpl;
import com.nortal.sk.ws.model.SignedDocRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class SignedDocStep extends AbstractWsStep<WsSignProcessor, SesscodeReq> {
    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.SIGNED_DOC;
    }

    @Override
    protected Class<? extends SesscodeReq> getReqImpl() {
        return SesscodeReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, SesscodeReq req) throws Exception {
        SignedDocRsp rsp = processor.getWsClient().getSignedDoc(req.getSesscode());
        if (StringUtils.isNotBlank(rsp.getSignedDocData())) {
            processor.fromWSFormat(rsp.getSignedDocData());
        }
        return rsp;
    }
}
