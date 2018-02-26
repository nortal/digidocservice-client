package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

public class SesscodeRspImpl extends StatusRspImpl implements SesscodeRsp {
    private Integer sesscode;

    @Override
    public Integer getSesscode() {
        return sesscode;
    }

    public void setSesscode(Integer sesscode) {
        this.sesscode = sesscode;
    }
}
