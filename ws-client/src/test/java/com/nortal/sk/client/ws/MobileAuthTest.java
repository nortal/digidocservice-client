package com.nortal.sk.client.ws;

import org.junit.Assert;
import org.junit.Test;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.model.MobileDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.ws.BaseWsTest;
import com.nortal.sk.ws.model.ChallengeRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileAuthTest extends BaseWsTest {
    public static String ID_CODE = "14212128025";
    public static String PHONE_NO = "37200007";

    protected SimpleSkWsClient skWsClient = new SimpleSkWsClient();

    @Test
    @SuppressWarnings("static-access")
    public void mobileSignTest1() throws Exception {
        StateHolder state = new StateHolder();
        Processor<StateHolder> processor = skWsClient.createSimpleAuthProcessor(createMobileData(PHONE_NO, ID_CODE));

        do {
            state = processor.process(state);
            if (StepCodeEnum.MOBILE_AUTHENTICATE.equals(state.getActiveStep())) {
                ChallengeRsp rsp = state.getActiveResponse();
                System.out.println("Client challenge: " + rsp.getChallengeID());
            }
            else {
                Thread.currentThread().sleep(5000);
            }
        } while (!state.isComplete());

        Assert.assertTrue(state.isValid());
        Assert.assertEquals(StepCodeEnum.MOBILE_AUTHENTICATE_STATUS, state.getActiveStep());
    }

    private MobileDataModel createMobileData(final String phoneNo, final String idCode) {
        return new MobileDataModel() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getPhoneNo() {
                return phoneNo;
            }

            @Override
            public String getIdCode() {
                return idCode;
            }
        };
    }
}
