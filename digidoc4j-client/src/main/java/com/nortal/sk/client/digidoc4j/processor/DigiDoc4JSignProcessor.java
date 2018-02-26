package com.nortal.sk.client.digidoc4j.processor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.digidoc4j.Container;
import org.digidoc4j.ContainerBuilder;
import org.digidoc4j.DataToSign;
import org.digidoc4j.DigestAlgorithm;
import org.digidoc4j.SignatureBuilder;
import org.digidoc4j.SignatureProfile;

import com.nortal.sk.client.card.model.CardSignStartReq;
import com.nortal.sk.client.card.processor.CardSignProcessor;
import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.processor.AbstractProcessor;
import com.nortal.sk.client.processor.SignStateHolder;

import eu.europa.ec.markt.dss.DSSUtils;

public class DigiDoc4JSignProcessor extends AbstractProcessor<SignStateHolder>implements CardSignProcessor {
    private DigiDoc4JModel getModel() {
        return getState().getContainer();
    }

    @Override
    protected void prepare() throws Exception {
        ContainerBuilder cb = ContainerBuilder.aContainer();
        if (getState().getDoc() != null) {
            cb.fromStream(new ByteArrayInputStream(getState().getDoc().getContent()));
        }
        else {
            for (FileModel file : getState().getFiles()) {
                cb.withDataFile(new ByteArrayInputStream(file.getContent()), file.getName(), file.getMimeType());
            }
        }
        getState().setContainer(new DigiDoc4JModel(cb.build()));
    }

    @Override
    public void finalize() throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        IOUtils.copy(getModel().getContainer().saveAsStream(), os);
        os.close();
        getState().setDoc(FileModel.of(null, os.toByteArray()));
    }

    @Override
    public String getDigestHex(CardSignStartReq req) {
        // @formatter:off
        DataToSign dataToSign = SignatureBuilder.aSignature()
            .withContainer(getModel().getContainer())
            .withSigningCertificate(req.getCert())
            .withRoles(req.getClaimedRoles())
            .withCity(req.getCity())
            .withStateOrProvince(req.getState())
            .withCountry(req.getCountry())
            .withPostalCode(req.getZip())
            .withDigestAlgorithm(DigestAlgorithm.SHA256)
            .withSignatureProfile(SignatureProfile.LT)
            .buildDataToSign();
        // @formatter:off
        getModel().setDataToSign(dataToSign);
        return DSSUtils.toHex(dataToSign.getDigestToSign());
    }

      @Override
      public void applySignature(String signatureHex) throws Exception {
        getModel().getDataToSign().finalize(Hex.decodeHex(signatureHex.toCharArray()));
      }
    
      @Override
      public void getSignatureConfirmation() throws Exception {
      }
      
      private class DigiDoc4JModel {
        private Container container;
        private DataToSign dataToSign;
        
        public DigiDoc4JModel(Container container) {
          this.container = container;
        }
        
        public Container getContainer() {
          return container;
        }
    
        public DataToSign getDataToSign() {
          return dataToSign;
        }
    
        public void setDataToSign(DataToSign dataToSign) {
          this.dataToSign = dataToSign;
        }
      }
}
