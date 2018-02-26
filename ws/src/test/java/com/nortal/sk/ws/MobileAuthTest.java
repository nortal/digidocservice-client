package com.nortal.sk.ws;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nortal.sk.ws.constant.MobileAuthStatusEnum;
import com.nortal.sk.ws.model.MobileAuthenticateReqImpl;
import com.nortal.sk.ws.model.MobileAuthenticateRsp;
import com.nortal.sk.ws.model.MobileAuthenticateStatusReqImpl;
import com.nortal.sk.ws.model.MobileAuthenticateStatusRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
// TODO: implement more tests plx
public class MobileAuthTest {
    private static final String SUCCESS_ID_CODE = "14212128025";
    private static final String SUCCESS_PHONE_NO = "37200007";

    private SkWs digiDocServiceClient = new SimpleSkWs();

    @Before
    public void init() {
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
    }

    @Test
    public void mobileAuthTest1() {
        try {
            MobileAuthenticateRsp rsp = digiDocServiceClient.mobileAuthenticate(MobileAuthenticateReqImpl.of(SUCCESS_ID_CODE, SUCCESS_PHONE_NO));
            MobileAuthenticateStatusRsp autRsp = digiDocServiceClient.getMobileAuthenticateStatus(MobileAuthenticateStatusReqImpl.of(rsp.getSesscode(), true));

            Assert.assertArrayEquals("MobileAuthTest.mobileAuthTest1: expected status=" + MobileAuthStatusEnum.USER_AUTHENTICATED,
                                     new MobileAuthStatusEnum[] { MobileAuthStatusEnum.USER_AUTHENTICATED },
                                     new MobileAuthStatusEnum[] { MobileAuthStatusEnum.valueOf(autRsp.getStatus()) });
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("MobileAuthTest.mobileAuthTest1: exception occurred");
        }
    }
}
