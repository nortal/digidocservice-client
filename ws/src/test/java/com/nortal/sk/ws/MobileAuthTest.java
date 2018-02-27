package com.nortal.sk.ws;

import org.junit.Assert;
import org.junit.Test;

import com.nortal.sk.ws.constant.MobileAuthStatusEnum;
import com.nortal.sk.ws.model.MobileAuthenticateReqImpl;
import com.nortal.sk.ws.model.MobileAuthenticateRsp;
import com.nortal.sk.ws.model.MobileAuthenticateStatusReqImpl;
import com.nortal.sk.ws.model.MobileAuthenticateStatusRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileAuthTest extends BaseWsTest {
    private static final String SUCCESS_ID_CODE = "14212128025";
    private static final String SUCCESS_PHONE_NO = "37200007";

    private SkWs digiDocServiceClient = new SimpleSkWs();

    @Test
    public void mobileAuthTest1() {
        MobileAuthenticateRsp rsp = digiDocServiceClient.mobileAuthenticate(MobileAuthenticateReqImpl.of(SUCCESS_ID_CODE, SUCCESS_PHONE_NO));
        MobileAuthenticateStatusRsp autRsp = digiDocServiceClient.getMobileAuthenticateStatus(MobileAuthenticateStatusReqImpl.of(rsp.getSesscode(), true));

        Assert.assertEquals(String.format("MobileAuthTest.mobileAuthTest1: expected status=%s", MobileAuthStatusEnum.USER_AUTHENTICATED),
                            MobileAuthStatusEnum.USER_AUTHENTICATED,
                            MobileAuthStatusEnum.valueOf(autRsp.getStatus()));
    }
}
