package com.nortal.sk.ws;

import com.nortal.sk.common.model.StatusRsp;
import com.nortal.sk.ws.model.AddDataFileReq;
import com.nortal.sk.ws.model.CheckCertificateReq;
import com.nortal.sk.ws.model.CheckCertificateRsp;
import com.nortal.sk.ws.model.CreateSignedDocReq;
import com.nortal.sk.ws.model.DataFileData;
import com.nortal.sk.ws.model.FinalizeSignatureReq;
import com.nortal.sk.ws.model.MobileAuthenticateReq;
import com.nortal.sk.ws.model.MobileAuthenticateRsp;
import com.nortal.sk.ws.model.MobileAuthenticateStatusReq;
import com.nortal.sk.ws.model.MobileAuthenticateStatusRsp;
import com.nortal.sk.ws.model.MobileSignReq;
import com.nortal.sk.ws.model.MobileSignRsp;
import com.nortal.sk.ws.model.PrepareSignatureReq;
import com.nortal.sk.ws.model.PrepareSignatureRsp;
import com.nortal.sk.ws.model.SignedDocInfoRsp;
import com.nortal.sk.ws.model.SignedDocRsp;
import com.nortal.sk.ws.model.StartSessionReq;
import com.nortal.sk.ws.model.StartSessionRsp;
import com.nortal.sk.ws.model.StatusInfoReq;
import com.nortal.sk.ws.model.StatusInfoRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface SkWs {

    /**
     * 7.3 CheckCertificate
     * 
     * @param certificate
     * @param returnRevocationData
     * @return
     */
    CheckCertificateRsp checkCertificate(String certificate, boolean returnRevocationData);

    /**
     * 7.3 CheckCertificate
     * 
     * @param req
     * @return
     */
    CheckCertificateRsp checkCertificate(CheckCertificateReq req);

    // Signing methods
    StartSessionRsp startSession(String signingProfile, String sigDocXML, boolean bHoldSession, DataFileData datafile);

    StartSessionRsp startSession(StartSessionReq req);

    /**
     * 8.3 CreateSignedDoc
     * 
     * @param sesscode
     * @param format
     */
    SignedDocInfoRsp createSignedDoc(int sesscode, String format, String version, String signingProfile);

    /**
     * 8.3 CreateSignedDoc
     * 
     * @param req
     */
    SignedDocInfoRsp createSignedDoc(CreateSignedDocReq req);

    /**
     * 8.4 AddDataFile
     */
    SignedDocInfoRsp addDataFile(int sesscode, String fileName, String mimeType, String contentType, int size, String digestType, String digestValue, String content);

    /**
     * 8.4 AddDataFile
     */
    SignedDocInfoRsp addDataFile(AddDataFileReq req);

    /**
     * 8.16 PrepareSignature
     * 
     * @param sesscode
     * @param signersCertificate
     * @param signersTokenId
     * @param role
     * @param city
     * @param state
     * @param postalCode
     * @param country
     * @param signingProfile
     * @return
     */
    PrepareSignatureRsp prepareSignature(int sesscode,
                                         String signersCertificate,
                                         String signersTokenId,
                                         String role,
                                         String city,
                                         String state,
                                         String postalCode,
                                         String country,
                                         String signingProfile);

    /**
     * 8.16 PrepareSignature
     * 
     * @param req
     * @return
     */
    PrepareSignatureRsp prepareSignature(PrepareSignatureReq req);

    /**
     * 8.17 FinalizeSignature
     * 
     * @param signatureId
     * @param signatureValue
     * @return
     */
    SignedDocInfoRsp finalizeSignature(int sesscode, String signatureId, String signatureValue);

    /**
     * 8.17 FinalizeSignature
     * 
     * @param req
     * @return
     */
    SignedDocInfoRsp finalizeSignature(FinalizeSignatureReq req);

    MobileSignRsp mobileSign(int sesscode,
                             String signerIDCode,
                             String signersCountry,
                             String signerPhoneNo,
                             String serviceName,
                             String additionalDataToBeDisplayed,
                             String language,
                             String role,
                             String city,
                             String stateOrProvince,
                             String postalCode,
                             String countryName,
                             String signingProfile,
                             String messagingMode,
                             int asyncConfiguration,
                             boolean returnDocInfo,
                             boolean returnDocData);

    MobileSignRsp mobileSign(MobileSignReq req);

    /**
     * 8.6 GetStatusInfo
     * 
     * @param sesscode
     * @param returnDocInfo
     * @param waitSignature
     * @return
     */
    StatusInfoRsp getStatusInfo(int sesscode, boolean returnDocInfo, boolean waitSignature);

    /**
     * 8.6 GetStatusInfo
     * 
     * @param req
     * @return
     */
    StatusInfoRsp getStatusInfo(StatusInfoReq req);

    /**
     * 8.2 CloseSession
     * 
     * @param sesscode
     * @return
     */
    StatusRsp closeSession(int sesscode);

    /**
     * 8.8 GetSignedDoc
     * 
     * @param sesscode
     * @return
     */
    SignedDocRsp getSignedDoc(int sesscode);

    // Mobile auth methods
    MobileAuthenticateRsp mobileAuthenticate(String idCode,
                                             String countryCode,
                                             String phoneNo,
                                             String language,
                                             String serviceName,
                                             String messageToDisplay,
                                             String spChallenge,
                                             String messagingMode,
                                             int asyncConfiguration,
                                             boolean returnCertData,
                                             boolean returnRevocationData);

    MobileAuthenticateRsp mobileAuthenticate(MobileAuthenticateReq req);

    MobileAuthenticateStatusRsp getMobileAuthenticateStatus(int sesscode, boolean waitSignature);

    MobileAuthenticateStatusRsp getMobileAuthenticateStatus(MobileAuthenticateStatusReq req);
}