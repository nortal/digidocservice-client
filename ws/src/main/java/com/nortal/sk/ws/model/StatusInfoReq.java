package com.nortal.sk.ws.model;

/**
 * 8.6 GetStatusInfo
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf"> DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface StatusInfoReq extends SesscodeReq {
    /**
     * If the value is "true" , in response SignedDocInfo is set.
     * 
     * @return
     */
    boolean isReturnDocInfo();

    /**
     * If the value is "true" , response is not sent before message fr om mobile phone is received or error condition is
     * detected. If the value is "false", the response is returned immediately and the GetStatusInfo invocation should
     * be repeated after a short time interval (2 - 10 seconds).
     * 
     * @return
     */
    boolean isWaitSignature();
}
