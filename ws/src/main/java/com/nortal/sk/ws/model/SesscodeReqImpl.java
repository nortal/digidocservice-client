package com.nortal.sk.ws.model;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class SesscodeReqImpl implements SesscodeReq {
    private int sesscode = 0;

    @Override
    public int getSesscode() {
        return sesscode;
    }

    @Override
    public void setSesscode(int sesscode) {
        this.sesscode = sesscode;
    }
}
