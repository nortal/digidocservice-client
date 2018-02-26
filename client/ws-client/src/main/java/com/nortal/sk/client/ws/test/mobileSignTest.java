package com.nortal.sk.client.ws.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.client.ws.SkWsClient;
import com.nortal.sk.client.ws.step.AddDataFileStep;
import com.nortal.sk.client.ws.step.CreateSignedDocStep;
import com.nortal.sk.client.ws.step.MobileSignStep;
import com.nortal.sk.client.ws.step.SignedDocStep;
import com.nortal.sk.client.ws.step.StartSessionStep;
import com.nortal.sk.client.ws.step.StatusInfoStep;
import com.nortal.sk.ws.model.ChallengeRsp;
import com.nortal.sk.ws.model.MobileSignReqImpl;

public class mobileSignTest {
    //  private static String ID_CODE = "14212128025";
    //  private static String PHONE_NO = "37200007";
    private static String ID_CODE = "38301080372";
    private static String PHONE_NO = "3725235902";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        try {

            // TODO Auto-generated method stub
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");

            SignStateHolder state = SignStateHolder.of(FileModel.of(new File("C:/devel_tools/test.txt")), FileModel.of(new File("C:/devel_tools/test2.txt")));
            //      SignStateHolder state = SignStateHolder.of(FileModel.of(new File("C:/projects2/jDigiDocClient/client/test.bdoc")));
            SkWsClient client = context.getBean(SkWsClient.class);

          // @formatter:off
          Processor processor = client.createSignProcessor(state)
            .step(new StartSessionStep())
            .step(new CreateSignedDocStep())
            .step(new AddDataFileStep())
            .step(MobileSignStep.of(MobileSignReqImpl.of(ID_CODE, PHONE_NO)))
            .step(new StatusInfoStep())
            .step(new SignedDocStep());
          // @formatter:on

            do {
                processor.process();
                if (state.isValid()) {
                    switch (state.getActiveStep()) {
                        case MOBILE_SIGN:
                            ChallengeRsp rsp = state.getActiveResponse();
                            System.out.println("CHALLENGE: " + rsp.getChallengeID());
                            break;
                        case STATUS_INFO:
                            Thread.currentThread().sleep(2000);
                        default:
                            System.out.println(state.getActiveStep());
                            break;
                    }
                }
            } while (!state.isComplete());

            if (state.isValid()) {
                //        IOUtils.copy(new ByteArrayInputStream(state.getDoc().getContent()), new FileOutputStream("C:/projects2/jDigiDocClient/client/test.bdoc"));
                IOUtils.copy(new ByteArrayInputStream(state.getDoc().getContent()), new FileOutputStream("C:/projects2/jDigiDocClient/client/ws/test2.bdoc"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        }
    }
}
