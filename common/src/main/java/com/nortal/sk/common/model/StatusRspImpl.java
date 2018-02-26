package com.nortal.sk.common.model;

import com.nortal.sk.common.constant.StatusEnum;

public class StatusRspImpl implements StatusRsp {
    private String status;

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static StatusRspImpl of(StatusEnum status) {
        return of(status.getCode());
    }

    public static StatusRspImpl of(String status) {
        StatusRspImpl rsp = new StatusRspImpl();
        rsp.setStatus(status);
        return rsp;
    }
}
