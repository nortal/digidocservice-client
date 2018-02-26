package com.nortal.sk.client.ws;

import com.nortal.sk.client.model.CardSignDataModel;
import com.nortal.sk.client.model.MobileDataModel;
import com.nortal.sk.client.model.MobileSignDataModel;
import com.nortal.sk.client.model.SignDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.ws.processor.WsProcessorImpl;
import com.nortal.sk.client.ws.processor.WsSignProcessorImpl;
import com.nortal.sk.client.ws.step.AddDataFileStep;
import com.nortal.sk.client.ws.step.CreateSignedDocStep;
import com.nortal.sk.client.ws.step.FinalizeSignatureStep;
import com.nortal.sk.client.ws.step.MobileAuthenticateStatusStep;
import com.nortal.sk.client.ws.step.MobileAuthenticateStep;
import com.nortal.sk.client.ws.step.MobileSignStep;
import com.nortal.sk.client.ws.step.PrepareSignatureStep;
import com.nortal.sk.client.ws.step.SignedDocStep;
import com.nortal.sk.client.ws.step.StartSessionStep;
import com.nortal.sk.client.ws.step.StatusInfoStep;
import com.nortal.sk.ws.SimpleSkWs;
import com.nortal.sk.ws.SkWs;
import com.nortal.sk.ws.model.CheckCertificateReq;
import com.nortal.sk.ws.model.CheckCertificateRsp;
import com.nortal.sk.ws.model.FinalizeSignatureReqImpl;
import com.nortal.sk.ws.model.MobileAuthenticateReqImpl;
import com.nortal.sk.ws.model.MobileSignReqImpl;
import com.nortal.sk.ws.model.PrepareSignatureReqImpl;

public class SimpleSkWsClient implements SkWsClient {
    private SkWs skWs;

    {
        setSkWs(new SimpleSkWs());
    }

    @Override
    public CheckCertificateRsp checkCertificate(CheckCertificateReq req) {
        return skWs.checkCertificate(req);
    }

    @Override
    public Processor<StateHolder> createAuthProcessor() {
        return new WsProcessorImpl<>(skWs);
    }

    @Override
    public Processor<StateHolder> createSimpleAuthProcessor(MobileDataModel data) {
        // @formatter:off
        return createAuthProcessor()
                  .step(MobileAuthenticateStep.of(MobileAuthenticateReqImpl.of(data.getIdCode(), data.getPhoneNo())))
                  .step(new MobileAuthenticateStatusStep());
        // @formatter:on
    }

    @Override
    public Processor<SignStateHolder> createSignProcessor() throws Exception {
        return new WsSignProcessorImpl(skWs);
    }

    @Override
    public Processor<SignStateHolder> createSimpleSignProcessor(SignDataModel data) throws Exception {

        if (MobileSignDataModel.class.isAssignableFrom(data.getClass())) {
            MobileSignDataModel mData = (MobileSignDataModel) data;

            // @formatter:off
            return createSignProcessor()
                    .step(new StartSessionStep())
                    .step(new CreateSignedDocStep())
                    .step(new AddDataFileStep())
                    .step(MobileSignStep.of(MobileSignReqImpl.of(mData.getIdCode(), mData.getPhoneNo())))
                    .step(new StatusInfoStep())
                    .step(new SignedDocStep());
            // @formatter:on
        }
        else if (CardSignDataModel.class.isAssignableFrom(data.getClass())) {
            CardSignDataModel cData = (CardSignDataModel) data;

            // @formatter:off
            return createSignProcessor()
                    .step(new StartSessionStep())
                    .step(new CreateSignedDocStep())
                    .step(new AddDataFileStep())
                    .step(PrepareSignatureStep.of(PrepareSignatureReqImpl.of(cData.getCertHex())))
                    .step(FinalizeSignatureStep.of(FinalizeSignatureReqImpl.of(cData.getSignatureValue())))
                    .step(new SignedDocStep());
            // @formatter:on
        }
        else {
            throw new IllegalStateException("SimpleSkWsClient.createSimpleSignProcessor: unsupported data model type=" + data.getClass());
        }
    }

    public void setSkWs(SkWs skWs) {
        this.skWs = skWs;
    }
}
