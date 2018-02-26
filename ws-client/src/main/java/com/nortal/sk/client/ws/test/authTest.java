package com.nortal.sk.client.ws.test;

import com.nortal.sk.client.constant.StepCodeEnum;
import com.nortal.sk.client.model.MobileDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.StateHolder;
import com.nortal.sk.client.ws.SimpleSkWsClient;
import com.nortal.sk.common.model.FaultRsp;
import com.nortal.sk.ws.model.ChallengeRsp;
import com.nortal.sk.ws.model.MobileAuthenticateRsp;

public class authTest {

    private static String ID_CODE = "38002240211";
    private static String PHONE_NO = "37200001";

    public static void main(String[] args) {
        try {
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
            //            ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            //            SkWsClient client = context.getBean(SkWsClient.class);

            StateHolder state = new StateHolder();
            Processor<StateHolder> processor = new SimpleSkWsClient().createSimpleAuthProcessor(new MobileDataModel() {
                private static final long serialVersionUID = 1L;

                @Override
                public String getPhoneNo() {
                    return PHONE_NO;
                }

                @Override
                public String getIdCode() {
                    return ID_CODE;
                }
            });
            do {
                state = processor.process(state);
                if (state.isValid()) {
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
