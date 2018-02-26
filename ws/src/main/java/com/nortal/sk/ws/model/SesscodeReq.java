package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.GeneralReq;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface SesscodeReq extends GeneralReq {
    /**
     * An identifier of the active session
     * 
     * @return
     */
    int getSesscode();

    void setSesscode(int sesscode);
}
