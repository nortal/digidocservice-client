package com.nortal.sk.client.ws.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.ws.SkWsClient;
import com.nortal.sk.client.ws.step.MobileAuthenticateStatusStep;
import com.nortal.sk.client.ws.step.MobileAuthenticateStep;
import com.nortal.sk.common.model.FaultRsp;
import com.nortal.sk.ws.model.ChallengeRsp;
import com.nortal.sk.ws.model.MobileAuthenticateReqImpl;
import com.nortal.sk.ws.model.MobileAuthenticateRsp;

public class authTest {

    private static String ID_CODE = "14212128025";
    private static String PHONE_NO = "37200007";

    public static void main(String[] args) {
        try {
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
            ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

            StateHolder state = new StateHolder();
            SkWsClient client = context.getBean(SkWsClient.class);
            do {

                // @formatter:off
                client.createAuthProcessor(state)
                    .step(MobileAuthenticateStep.of(MobileAuthenticateReqImpl.of(ID_CODE, PHONE_NO)))
                    .step(new MobileAuthenticateStatusStep()).process();
                // @formatter:on

                switch (state.getActiveStep()) {
                    case MOBILE_AUTHENTICATE:
                        ChallengeRsp rsp = state.getActiveResponse();
                        System.out.println("CHALLENGE: " + rsp.getChallengeID());
                        break;
                    default:
                        Thread.currentThread().sleep(5000);
                        System.out.println(state.getActiveStep());
                        break;
                }
            } while (!state.isComplete());

            if (state.isValid()) {
                MobileAuthenticateRsp rsp = state.getResponse(StepCodeEnum.MOBILE_AUTHENTICATE);
                if (rsp != null) {
                    System.out.println(rsp.getUserGivenname() + " " + rsp.getStatus());
                }
            }
            else {
                FaultRsp rsp = state.getActiveResponse();
                System.out.println("code=" + rsp.getCode() + ", message=" + rsp.getMessage());
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
