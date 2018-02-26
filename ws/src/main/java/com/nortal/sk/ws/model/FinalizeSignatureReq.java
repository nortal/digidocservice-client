package com.nortal.sk.ws.model;

/**
 * 8.17 FinalizeSignature
 * 
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface FinalizeSignatureReq extends SesscodeReq {
    /**
     * T he unique identifier of the signature which was returned as the result of PrepareSignature method
     * 
     * @return
     */
    String getSignatureId();

    void setSignatureId(String signatureId);

    /**
     * V alue of the signature (signed hash) as a HEX string. The signing software returns the value
     * 
     * @return
     */
    String getSignatureValue();
}
