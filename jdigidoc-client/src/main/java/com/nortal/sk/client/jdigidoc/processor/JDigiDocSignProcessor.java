package com.nortal.sk.client.jdigidoc.processor;

import java.io.ByteArrayOutputStream;

import com.nortal.sk.client.card.model.CardSignStartReq;
import com.nortal.sk.client.card.processor.CardSignProcessor;
import com.nortal.sk.client.jdigidoc.DigiDocClientUtils;
import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.processor.AbstractProcessor;
import com.nortal.sk.client.processor.SignStateHolder;

import ee.sk.digidoc.Signature;
import ee.sk.digidoc.SignatureProductionPlace;
import ee.sk.digidoc.SignedDoc;

public class JDigiDocSignProcessor extends AbstractProcessor<SignStateHolder>implements CardSignProcessor {
    @Override
    protected void prepare() throws Exception {
        SignedDoc doc = null;
        if (getState().getDoc() != null) {
            doc = DigiDocClientUtils.readSignedDoc(getState().getDoc().getContent());
        }
        else {
            doc = DigiDocClientUtils.createBDOC(getState().getFiles());
        }
        getState().setContainer(doc);
    }

    @Override
    public void finalize() throws Exception {
        SignedDoc doc = getState().getContainer();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        doc.writeToStream(os);
        os.close();
        getState().setDoc(FileModel.of(null, os.toByteArray()));
    }

    @Override
    public String getDigestHex(CardSignStartReq req) throws Exception {
        SignedDoc doc = getState().getContainer();
        Signature sig = doc.prepareSignature(req.getCert(), req.getClaimedRoles(), new SignatureProductionPlace(req.getCity(), req.getCity(), req.getCountry(), req.getZip()));
        return SignedDoc.bin2hex(sig.calculateSignedInfoDigest());
    }

    @Override
    public void applySignature(String signatureHex) throws Exception {
        SignedDoc doc = getState().getContainer();
        Signature sig = doc.getSignature(doc.getSignatures().size() - 1);
        sig.setSignatureValue(SignedDoc.hex2bin(signatureHex));
    }

    @Override
    public void getSignatureConfirmation() throws Exception {
        SignedDoc doc = getState().getContainer();
        Signature sig = doc.getSignature(doc.getSignatures().size() - 1);
        sig.getConfirmation();
    }
}
