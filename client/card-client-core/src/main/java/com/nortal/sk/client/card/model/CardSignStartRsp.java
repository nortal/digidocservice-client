package com.nortal.sk.client.card.model;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.common.constant.StatusEnum;
import com.nortal.sk.common.model.StatusRspImpl;

public class CardSignStartRsp extends StatusRspImpl {
    public String digestHex;

    public String getDigestHex() {
        return digestHex;
    }

    public void setDigestHex(String digestHex) {
        this.digestHex = digestHex;
    }

    public static CardSignStartRsp of(String digestHex) {
        CardSignStartRsp rsp = new CardSignStartRsp();
        rsp.setDigestHex(digestHex);
        rsp.setStatus(StringUtils.isNotBlank(digestHex) ? StatusEnum.OK.getCode() : null);
        return rsp;
    }
}
