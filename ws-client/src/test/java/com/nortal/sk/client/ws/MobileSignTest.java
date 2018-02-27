package com.nortal.sk.client.ws;

import org.junit.Assert;
import org.junit.Test;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.model.MobileSignDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.ws.BaseWsTest;
import com.nortal.sk.ws.model.ChallengeRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileSignTest extends BaseWsTest {
    public static String ID_CODE = "14212128025";
    public static String PHONE_NO = "37200007";

    protected SimpleSkWsClient skWsClient = new SimpleSkWsClient();

    @Test
    @SuppressWarnings("static-access")
    public void mobileAuthTest1() throws Exception {
        SignStateHolder state = SignStateHolder.of(FileModel.of("test1.txt", "123456".getBytes()), FileModel.of("test2.txt", "abcdefg".getBytes()));
        Processor<SignStateHolder> processor = skWsClient.createSimpleSignProcessor(createMobileData(PHONE_NO, ID_CODE));

        do {
            state = processor.process(state);
            if (StepCodeEnum.MOBILE_SIGN.equals(state.getActiveStep())) {
                ChallengeRsp rsp = state.getActiveResponse();
                System.out.println("Client challenge: " + rsp.getChallengeID());
            }
            else {
                Thread.currentThread().sleep(5000);
            }
        } while (!state.isComplete());

        Assert.assertTrue(state.isValid());
        Assert.assertEquals(StepCodeEnum.SIGNED_DOC, state.getActiveStep());
    }

    private MobileSignDataModel createMobileData(final String phoneNo, final String idCode) {
        return new MobileSignDataModel() {
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
