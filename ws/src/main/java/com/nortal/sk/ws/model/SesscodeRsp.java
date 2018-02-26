package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRsp;

public interface SesscodeRsp extends StatusRsp {
    /**
     * Session code used for further requests in the given transaction
     * 
     * @return
     */
    Integer getSesscode();
}
