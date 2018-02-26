package com.nortal.sk.client.ws.step;

import java.util.Base64;

import org.apache.commons.beanutils.PropertyUtils;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.ws.processor.WsSignProcessor;
import com.nortal.sk.common.constant.StatusEnum;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.common.model.StatusRspImpl;
import com.nortal.sk.ws.constant.ContentTypeEnum;
import com.nortal.sk.ws.model.AddDataFileReq;
import com.nortal.sk.ws.model.AddDataFileReqImpl;
import com.nortal.sk.ws.model.SignedDocInfoRsp;

import eu.europa.ec.markt.dss.DigestAlgorithm;

public class AddDataFileStep extends AbstractWsStep<WsSignProcessor, AddDataFileReq> {

    @Override
    public StepCodeEnum getCode() {
        return StepCodeEnum.ADD_DATA_FILE;
    }

    @Override
    protected Class<? extends AddDataFileReq> getReqImpl() {
        return AddDataFileReqImpl.class;
    }

    @Override
    protected GeneralRsp innerExecute(WsSignProcessor processor, AddDataFileReq req) throws Exception {
        // Skip if container is present
        if (processor.getState().getDoc() != null) {
            return StatusRspImpl.of(StatusEnum.OK);
        }

        SignedDocInfoRsp rsp = null;
        for (FileModel file : processor.getState().getFiles()) {
            AddDataFileReqImpl reqImpl = new AddDataFileReqImpl();
            PropertyUtils.copyProperties(reqImpl, req);
            reqImpl.setFileName(file.getName());
            reqImpl.setMimeType(file.getMimeType());
            reqImpl.setSize(file.getSize());
            reqImpl.setDigestValue(processor.calculateDigest(file, DigestAlgorithm.forName(req.getDigestType())));

            if (ContentTypeEnum.EMBEDDED_BASE64 == ContentTypeEnum.valueOf(reqImpl.getContentType())) {
                reqImpl.setContent(Base64.getEncoder().encodeToString(file.getContent()));
            }
            else {
                reqImpl.setContent(null);
            }
            rsp = processor.getWsClient().addDataFile(reqImpl);
        }
        return rsp;
    }

}
