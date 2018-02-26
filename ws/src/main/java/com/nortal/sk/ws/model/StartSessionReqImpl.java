package com.nortal.sk.ws.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class StartSessionReqImpl implements StartSessionReq {
    private String signingProfile = StringUtils.EMPTY;
    private String sigDocXML = StringUtils.EMPTY;
    private boolean bHoldSession = true;
    private DataFileData dataFile = new DataFileData();

    @Override
    public String getSigningProfile() {
        return signingProfile;
    }

    @Override
    public void setSigningProfile(String signingProfile) {
        this.signingProfile = signingProfile;
    }

    @Override
    public String getSigDocXML() {
        return sigDocXML;
    }

    @Override
    public void setSigDocXML(String sigDocXML) {
        this.sigDocXML = sigDocXML;
    }

    @Override
    public boolean isbHoldSession() {
        return bHoldSession;
    }

    @Override
    public void setbHoldSession(boolean bHoldSession) {
        this.bHoldSession = bHoldSession;
    }

    @Override
    public DataFileData getDataFile() {
        return dataFile;
    }

    @Override
    public void setDataFile(DataFileData dataFileData) {
        this.dataFile = dataFileData;
    }

    public static StartSessionReqImpl of(String sigDocXML) {
        StartSessionReqImpl req = new StartSessionReqImpl();
        req.setSigDocXML(sigDocXML);
        return req;
    }
}
