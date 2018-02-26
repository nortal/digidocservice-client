package com.nortal.sk.client.ws.step;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.SesscodeReq;
import com.nortal.sk.ws.model.SesscodeReqImpl;
import com.nortal.sk.ws.model.SignedDocRsp;

public class SignedDocStep extends AbstractWsStep<WsSignProcessor, SesscodeReq> {
    //  {
    //    setCode(StepCodeEnum.SIGNED_DOC);
    //    setType(SesscodeReqImpl.class);
    //  }

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
        //    SesscodeReq req = getReq();
        SignedDocRsp rsp = processor.getWsClient().getSignedDoc(req.getSesscode());

        //    SignedDoc doc = getState().getDoc();
        //    if (StringUtils.isNotBlank(rsp.getSignedDocData()) && doc != null) {
        //      getState().setDoc(DigiDocClientUtils.readSignedDoc(DigiDocClientUtils.fromWSFormat(doc, rsp.getSignedDocData())));
        //    }
        //    FileModel doc = processor.getState().getDoc();
        if (StringUtils.isNotBlank(rsp.getSignedDocData())) {
            processor.fromWSFormat(rsp.getSignedDocData());
            //      processor.getState().getDoc().setContent(processor.getSignHelper().fromWSFormat(processor.getState().getDoc(), rsp.getSignedDocData()));
        }
        return rsp;
    }
}
