package com.nortal.sk.ws.model;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class StatusInfoReqImpl extends SesscodeReqImpl implements StatusInfoReq {
    private boolean returnDocInfo;
    private boolean waitSignature;

    @Override
    public boolean isReturnDocInfo() {
        return returnDocInfo;
    }

    public void setReturnDocInfo(boolean returnDocInfo) {
        this.returnDocInfo = returnDocInfo;
    }

    @Override
    public boolean isWaitSignature() {
        return waitSignature;
    }

    public void setWaitSignature(boolean waitSignature) {
        this.waitSignature = waitSignature;
    }

    public static StatusInfoReqImpl of(int sesscode) {
        StatusInfoReqImpl req = new StatusInfoReqImpl();
        req.setSesscode(sesscode);
        return req;
    }

    public static StatusInfoReqImpl of(boolean waitSignature) {
        StatusInfoReqImpl req = new StatusInfoReqImpl();
        req.setWaitSignature(waitSignature);
        return req;
    }
}