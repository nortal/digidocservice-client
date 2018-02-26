package com.nortal.sk.common.model;

public class FaultRsp implements GeneralRsp {
  private String code;
  private String message;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static FaultRsp of(String code, String message) {
    FaultRsp rsp = new FaultRsp();
    rsp.setCode(code);
    rsp.setMessage(message);
    return rsp;
  }
}
