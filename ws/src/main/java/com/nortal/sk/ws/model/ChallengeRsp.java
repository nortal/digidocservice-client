package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.GeneralRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface ChallengeRsp extends GeneralRsp {
    /**
     * 4-digit control code calculated from hash of the value to be signed. The control code shall be displayed to the
     * user in order to provide means to verify authenticity of the signing request.
     * 
     * @return
     */
    String getChallengeID();
}
