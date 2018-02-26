package com.nortal.sk.client.ws.test;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;

import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.model.MobileSignDataModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.client.ws.SimpleSkWsClient;
import com.nortal.sk.ws.model.ChallengeRsp;

public class mobileSignTest {
    private static String ID_CODE = "14212128025";
    private static String PHONE_NO = "37200007";

    public static void main(String[] args) {
        try {
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");

            SignStateHolder state = SignStateHolder.of(FileModel.of("test1.txt", "123456".getBytes()), FileModel.of("test2.txt", "abcdefg".getBytes()));

            Processor<SignStateHolder> processor = new SimpleSkWsClient().createSimpleSignProcessor(new MobileSignDataModel() {
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
                        case MOBILE_SIGN:
                            ChallengeRsp rsp = state.getActiveResponse();
                            System.out.println("CHALLENGE: " + rsp.getChallengeID());
                            break;
                        case STATUS_INFO:
                            Thread.currentThread().sleep(2000);
                        default:
                            System.out.println("RETURN: " + state.getActiveStep());
                            break;
                    }
                }
            } while (!state.isComplete());

            if (state.isValid()) {
                IOUtils.copy(new ByteArrayInputStream(state.getDoc().getContent()), new FileOutputStream("c:/temp/bdoctest.bdoc"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        }
    }
}
