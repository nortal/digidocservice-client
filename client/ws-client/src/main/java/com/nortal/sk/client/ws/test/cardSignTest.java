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
import com.nortal.sk.client.ws.step.FinalizeSignatureStep;
import com.nortal.sk.client.ws.step.PrepareSignatureStep;
import com.nortal.sk.client.ws.step.SignedDocStep;
import com.nortal.sk.client.ws.step.StartSessionStep;
import com.nortal.sk.ws.model.FinalizeSignatureReqImpl;
import com.nortal.sk.ws.model.PrepareSignatureReqImpl;
import com.nortal.sk.ws.model.PrepareSignatureRsp;

public class cardSignTest {
    //  private static String ID_CODE = "14212128025";
    //  private static String PHONE_NO = "37200007";
    private static String ID_CODE = "38301080372";
    private static String PHONE_NO = "3725235902";
    private static String certHex = "308204A63082038EA003020102021010B69B028738D313531433EE7EE73FE5300D06092A864886F70D01010505003064310B"
                    + "300906035504061302454531223020060355040A0C19415320536572746966697473656572696D69736B65736B7573311730"
                    + "1506035504030C0E4553544549442D534B20323031313118301606092A864886F70D0109011609706B6940736B2E6565301E"
                    + "170D3134303330333037343930325A170D3139303232383231353935395A30819C310B3009060355040613024545310F300D"
                    + "060355040A0C06455354454944311A3018060355040B0C116469676974616C207369676E6174757265312530230603550403"
                    + "0C1C4CC3845454454DC384452C4C415552492C33383330313038303337323113301106035504040C0A4CC3845454454DC384"
                    + "45310E300C060355042A0C054C41555249311430120603550405130B333833303130383033373230820123300D06092A8648"
                    + "86F70D010101050003820110003082010B0282010100EE547DDE87530AA92EFCC07D8555AA959BF35DFAF93E79F66FD5EE31"
                    + "A080848AA4FE12E736553644C821247A3C9CC2E8714E7A9A29B30C3F6B722CADA348254D1BD25C88EF44DBAA4A8E9126CD44"
                    + "10CEF5A6349705237700EFD5A04AEF2EBF7A57D37132AA9E67C9B80A068D1CD1528CD924F4F973BA487EDB7CEA741E5CB02F"
                    + "972C681C490B89D00842068EF69C27A2F8BF28F42C4026E690630649AD02167FC81C2CFF181DF8732732A8ABED2E89E5224B"
                    + "A2E607BEF4D8BDEA3DF2FBE7D50FC5030DBF9428DF19DC9D5B558DC40B29C2F719DB363F3B463B1254DA544A5D3A22AC206F"
                    + "99BE0D93436C2750FEF750711F1BE6B8A3750D33C9BC74CC210204C502041A1F87FDA38201183082011430090603551D1304"
                    + "023000300E0603551D0F0101FF04040302064030510603551D20044A30483046060B2B06010401CE1F010103033037301206"
                    + "082B0601050507020230061A046E6F6E65302106082B060105050702011615687474703A2F2F7777772E736B2E65652F6370"
                    + "732F301D0603551D0E04160414D26975F972EFF272890AA3F7011A28EA893DD8A1302206082B060105050701030416301430"
                    + "08060604008E4601013008060604008E460104301F0603551D230418301680147B6AF255505CB8D97A088741AEFAA22B3D5B"
                    + "577630400603551D1F043930373035A033A031862F687474703A2F2F7777772E736B2E65652F7265706F7369746F72792F63"
                    + "726C732F657374656964323031312E63726C300D06092A864886F70D010105050003820101000D790B78873AF83FF3A76B9D"
                    + "CF15883D3221922AFA4C9779E53C3B46D14884DE714B70A7F9DA797F56C64F5D4C9D6706ADF294652D536CB4D9F7B8D41F92"
                    + "4FB94763C4E0A89668F7FD2E8FF9AE5F9BC011AD6B5C90DE54128DCC4C2EC1F836F2070B0A5EBE566788B6A26F8E7E92BBD4"
                    + "4A2A78FADC9D572C11168A69934009BEDF0C401F36BAB9DD4EE695F6E5882188D7506EC7B37F891D7155475777D5A0190955"
                    + "25748D19C4A0E9E5D96D611BF311A59C08A7BA27C15E105BD853A2EBB5ADF105DFA434BBF2A9B23FDDE038F2C9904712FD44"
                    + "48FE87057FD911ADCEEDF073AE8A55C1522E50D05E570282AB19C76D4AE51CBB9D4113B2B3892132D5EA2739";

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
        .step(PrepareSignatureStep.of(PrepareSignatureReqImpl.of(certHex)))
        .step(FinalizeSignatureStep.of(FinalizeSignatureReqImpl.of("")))
        .step(new SignedDocStep());
      // @formatter:off
      
      do {
        processor.process();
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
//        IOUtils.copy(new ByteArrayInputStream(state.getDoc().getContent()), new FileOutputStream("C:/projects2/jDigiDocClient/client/test.bdoc"));
        IOUtils.copy(new ByteArrayInputStream(state.getDoc().getContent()), new FileOutputStream("C:/projects2/jDigiDocClient/client/test2.bdoc"));
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
  }
}
