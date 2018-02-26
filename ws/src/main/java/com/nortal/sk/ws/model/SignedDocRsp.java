package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

/**
 * 8.8 GetSignedDoc
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class SignedDocRsp extends StatusRspImpl {
    private String signedDocData;

    /**
     * The signed document in the session in XML format. As the XML tags has been transformed to HTML encoded format, th
     * erefore a HTMLDecode transduction should be done before saving the file in file system or to database.
     * 
     * @return
     */
    public String getSignedDocData() {
        return signedDocData;
    }

    public void setSignedDocData(String signedDocData) {
        this.signedDocData = signedDocData;
    }
}
