package com.nortal.sk.ws.model;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileAuthenticateStatusReqImpl extends SesscodeReqImpl implements MobileAuthenticateStatusReq {
    private boolean waitSignature;

    @Override
    public boolean isWaitSignature() {
        return waitSignature;
    }

    public void setWaitSignature(boolean waitSignature) {
        this.waitSignature = waitSignature;
    }

    public static MobileAuthenticateStatusReqImpl of(int sesscode) {
        return of(sesscode, false);
    }

    public static MobileAuthenticateStatusReqImpl of(int sesscode, boolean waitSignature) {
        MobileAuthenticateStatusReqImpl req = new MobileAuthenticateStatusReqImpl();
        req.setSesscode(sesscode);
        req.setWaitSignature(waitSignature);
        return req;
    }
}
