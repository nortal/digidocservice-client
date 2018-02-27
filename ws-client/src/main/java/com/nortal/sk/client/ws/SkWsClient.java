package com.nortal.sk.client.ws;

import com.nortal.sk.client.SkClient;
import com.nortal.sk.client.model.MobileDataModel;
import com.nortal.sk.client.model.SignDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.ws.model.CheckCertificateReq;
import com.nortal.sk.ws.model.CheckCertificateRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface SkWsClient extends SkClient<SignDataModel> {
    CheckCertificateRsp checkCertificate(CheckCertificateReq req);

    Processor<StateHolder> createAuthProcessor();

    Processor<StateHolder> createSimpleAuthProcessor(MobileDataModel data);
}
