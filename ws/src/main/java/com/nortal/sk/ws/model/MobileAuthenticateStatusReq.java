package com.nortal.sk.ws.model;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface MobileAuthenticateStatusReq extends SesscodeReq {
    boolean isWaitSignature();
}
