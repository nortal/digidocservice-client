package com.nortal.sk.client.ws.test;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.client.model.CardSignDataModel;
import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.client.ws.SimpleSkWsClient;
import com.nortal.sk.ws.model.PrepareSignatureRsp;

public class cardSignTest {
    private static String certHex = "valid cert hex plx";

    public static void main(String[] args) {
        try {
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");

            SignStateHolder state = SignStateHolder.of(FileModel.of("test1.txt", "123456".getBytes()), FileModel.of("test2.txt", "abcdefg".getBytes()));

            // @formatter:off
            Processor<SignStateHolder> processor = new SimpleSkWsClient().createSimpleSignProcessor(new CardSignDataModel() {
                private static final long serialVersionUID = 1L;

                @Override
                public String getSignatureValue() {
                    return StringUtils.EMPTY;
                }
                
                @Override
                public String getCertHex() {
                    return certHex;
                }
            });

            do {
                state = processor.process(state);
                if (state.isValid()) {
                    switch (state.getActiveStep()) {
                        case PREPARE_SIGNATURE:
                            PrepareSignatureRsp rsp = state.getActiveResponse();
                            System.out.println("DIGEST: " + rsp.getSignedInfoDigest());
                            break;
                        default:
                            System.out.println(state.getActiveStep());
                            break;
                    }
                }
            } while (!state.isComplete());

            if (state.isValid()) {
                IOUtils.copy(new ByteArrayInputStream(state.getDoc().getContent()), new FileOutputStream("C:/temp/bdoctest2.bdoc"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        }
    }
}
