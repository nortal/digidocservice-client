package com.nortal.sk.ws;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.common.model.StatusRsp;
import com.nortal.sk.common.model.StatusRspImpl;
import com.nortal.sk.ws.model.AddDataFileReq;
import com.nortal.sk.ws.model.CheckCertificateReq;
import com.nortal.sk.ws.model.CheckCertificateRsp;
import com.nortal.sk.ws.model.CreateSignedDocReq;
import com.nortal.sk.ws.model.DataFileData;
import com.nortal.sk.ws.model.DigiDocService;
import com.nortal.sk.ws.model.DigiDocServicePortType;
import com.nortal.sk.ws.model.FinalizeSignatureReq;
import com.nortal.sk.ws.model.MobileAuthenticateReq;
import com.nortal.sk.ws.model.MobileAuthenticateRsp;
import com.nortal.sk.ws.model.MobileAuthenticateStatusReq;
import com.nortal.sk.ws.model.MobileAuthenticateStatusRsp;
import com.nortal.sk.ws.model.MobileSignReq;
import com.nortal.sk.ws.model.MobileSignRsp;
import com.nortal.sk.ws.model.PrepareSignatureReq;
import com.nortal.sk.ws.model.PrepareSignatureRsp;
import com.nortal.sk.ws.model.SignedDocInfo;
import com.nortal.sk.ws.model.SignedDocInfoRsp;
import com.nortal.sk.ws.model.SignedDocRsp;
import com.nortal.sk.ws.model.StartSessionReq;
import com.nortal.sk.ws.model.StartSessionRsp;
import com.nortal.sk.ws.model.StatusInfoReq;
import com.nortal.sk.ws.model.StatusInfoRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public abstract class AbstractSkWs implements SkWs {
    private DigiDocService digiDocService;
    private String endpoint;
    private String serviceName;

    public void setDigiDocService(DigiDocService digiDocService) {
        this.digiDocService = digiDocService;
    }

    public String getEnpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    private DigiDocServicePortType getPort() {
        DigiDocServicePortType port = digiDocService.getDigiDocService();
        if (StringUtils.isNotBlank(getEnpoint())) {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getEnpoint());
        }
        return port;
    }

    protected <T> T nvl(T v1, T v2) {
        if (v1 != null && (!String.class.isAssignableFrom(v1.getClass()) || (StringUtils.isNotBlank((String) v1)))) {
            return v1;
        }
        return v2;
    }

    @Override
    public CheckCertificateRsp checkCertificate(String certificate, boolean returnRevocationData) {
        Holder<String> status = new Holder<>();
        Holder<String> userIDCode = new Holder<>();
        Holder<String> userGivename = new Holder<>();
        Holder<String> userSurname = new Holder<>();
        Holder<String> userCountry = new Holder<>();
        Holder<String> userOrganisation = new Holder<>();
        Holder<String> userCN = new Holder<>();
        Holder<String> issuer = new Holder<>();
        Holder<String> keyUsage = new Holder<>();
        Holder<String> enhancedKeyUsage = new Holder<>();
        Holder<String> revocationData = new Holder<>();

        getPort().checkCertificate(certificate,
                                   returnRevocationData,
                                   status,
                                   userIDCode,
                                   userGivename,
                                   userSurname,
                                   userCountry,
                                   userOrganisation,
                                   userCN,
                                   issuer,
                                   keyUsage,
                                   enhancedKeyUsage,
                                   revocationData);

        CheckCertificateRsp rsp = new CheckCertificateRsp();
        rsp.setStatus(status.value);
        rsp.setUserIDCode(userIDCode.value);
        rsp.setUserGivename(userGivename.value);
        rsp.setUserSurname(userSurname.value);
        rsp.setUserCountry(userCountry.value);
        rsp.setUserOrganisation(userOrganisation.value);
        rsp.setUserCN(userCN.value);
        rsp.setIssuer(issuer.value);
        rsp.setKeyUsage(keyUsage.value);
        rsp.setEnhancedKeyUsage(enhancedKeyUsage.value);
        rsp.setRevocationData(revocationData.value);
        return rsp;
    }

    @Override
    public CheckCertificateRsp checkCertificate(CheckCertificateReq req) {
        return checkCertificate(req.getCertificate(), req.isReturnRevocationData());
    }

    // SIGNING METHODS
    @Override
    public StartSessionRsp startSession(String signingProfile, String sigDocXML, boolean bHoldSession, DataFileData dataFileData) {
        Holder<String> status = new Holder<>();
        Holder<Integer> sesscode = new Holder<>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<>();

        getPort().startSession(signingProfile, sigDocXML, bHoldSession, dataFileData, status, sesscode, signedDocInfo);

        StartSessionRsp rsp = new StartSessionRsp();
        rsp.setStatus(status.value);
        rsp.setSesscode(sesscode.value);
        rsp.setSignedDocInfo(signedDocInfo.value);
        return rsp;
    }

    @Override
    public SignedDocInfoRsp createSignedDoc(int sesscode, String format, String version, String signingProfile) {
        Holder<String> status = new Holder<>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<>();

        getPort().createSignedDoc(sesscode, format, version, signingProfile, status, signedDocInfo);

        SignedDocInfoRsp rsp = new SignedDocInfoRsp();
        rsp.setStatus(status.value);
        rsp.setSignedDocInfo(signedDocInfo.value);
        return rsp;
    }

    @Override
    public SignedDocInfoRsp createSignedDoc(CreateSignedDocReq req) {
        return createSignedDoc(req.getSesscode(), req.getFormat(), req.getVersion(), req.getSigningProfile());
    }

    @Override
    public SignedDocInfoRsp addDataFile(int sesscode, String fileName, String mimeType, String contentType, int size, String digestType, String digestValue, String content) {
        Holder<String> status = new Holder<>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<>();

        getPort().addDataFile(sesscode, fileName, mimeType, contentType, size, digestType, digestValue, content, status, signedDocInfo);

        SignedDocInfoRsp rsp = new SignedDocInfoRsp();
        rsp.setStatus(status.value);
        rsp.setSignedDocInfo(signedDocInfo.value);
        return rsp;
    }

    @Override
    public SignedDocInfoRsp addDataFile(AddDataFileReq req) {
        return addDataFile(req.getSesscode(),
                           req.getFileName(),
                           req.getMimeType(),
                           req.getContentType(),
                           req.getSesscode(),
                           req.getDigestType(),
                           req.getDigestValue(),
                           nvl(req.getContent(), StringUtils.EMPTY));
    }

    @Override
    public StartSessionRsp startSession(StartSessionReq req) {
        return startSession(req.getSigningProfile(), req.getSigDocXML(), req.isbHoldSession(), req.getDataFile());
    }

    @Override
    public PrepareSignatureRsp prepareSignature(int sesscode,
                                                String signersCertificate,
                                                String signersTokenId,
                                                String role,
                                                String city,
                                                String state,
                                                String postalCode,
                                                String country,
                                                String signingProfile) {
        Holder<String> status = new Holder<>();
        Holder<String> signatureId = new Holder<>();
        Holder<String> signedInfoDigest = new Holder<>();
        getPort().prepareSignature(sesscode, signersCertificate, signersTokenId, role, city, state, postalCode, country, signingProfile, status, signatureId, signedInfoDigest);

        PrepareSignatureRsp rsp = new PrepareSignatureRsp();
        rsp.setStatus(status.value);
        rsp.setSignatureId(signatureId.value);
        rsp.setSignedInfoDigest(signedInfoDigest.value);
        return rsp;
    }

    @Override
    public PrepareSignatureRsp prepareSignature(PrepareSignatureReq req) {
        return prepareSignature(req.getSesscode(),
                                req.getSignersCertificate(),
                                req.getSignersTokenId(),
                                req.getRole(),
                                req.getCity(),
                                req.getState(),
                                req.getPostalCode(),
                                req.getCountry(),
                                req.getSigningProfile());
    }

    @Override
    public SignedDocInfoRsp finalizeSignature(int sesscode, String signatureId, String signatureValue) {
        Holder<String> status = new Holder<>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<>();

        getPort().finalizeSignature(sesscode, signatureId, signatureValue, status, signedDocInfo);

        SignedDocInfoRsp rsp = new SignedDocInfoRsp();
        rsp.setStatus(status.value);
        rsp.setSignedDocInfo(signedDocInfo.value);
        return rsp;
    }

    @Override
    public SignedDocInfoRsp finalizeSignature(FinalizeSignatureReq req) {
        return finalizeSignature(req.getSesscode(), req.getSignatureId(), req.getSignatureValue());
    }

    @Override
    public MobileSignRsp mobileSign(int sesscode,
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
                                    boolean returnDocData) {
        Holder<String> status = new Holder<>();
        Holder<String> statusCode = new Holder<>();
        Holder<String> challengeID = new Holder<>();

        getPort().mobileSign(sesscode,
                             signerIDCode,
                             signersCountry,
                             signerPhoneNo,
                             serviceName,
                             additionalDataToBeDisplayed,
                             language,
                             role,
                             city,
                             stateOrProvince,
                             postalCode,
                             countryName,
                             signingProfile,
                             messagingMode,
                             asyncConfiguration,
                             returnDocInfo,
                             returnDocData,
                             status,
                             statusCode,
                             challengeID);

        MobileSignRsp rsp = new MobileSignRsp();
        rsp.setStatus(status.value);
        rsp.setStatusCode(statusCode.value);
        rsp.setChallengeID(challengeID.value);
        return rsp;
    }

    @Override
    public MobileSignRsp mobileSign(MobileSignReq req) {
        return mobileSign(req.getSesscode(),
                          req.getSignerIDCode(),
                          req.getSignersCountry(),
                          req.getSignerPhoneNo(),
                          nvl(req.getServiceName(), getServiceName()),
                          req.getAdditionalDataToBeDisplayed(),
                          req.getLanguage(),
                          req.getRole(),
                          req.getCity(),
                          req.getStateOrProvince(),
                          req.getPostalCode(),
                          req.getCountryName(),
                          req.getSigningProfile(),
                          req.getMessagingMode(),
                          req.getAsyncConfiguration(),
                          req.isReturnDocInfo(),
                          req.isReturnDocData());
    }

    @Override
    public StatusInfoRsp getStatusInfo(int sesscode, boolean returnDocInfo, boolean waitSignature) {
        Holder<String> status = new Holder<>();
        Holder<String> statusCode = new Holder<>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<>();

        getPort().getStatusInfo(sesscode, returnDocInfo, waitSignature, status, statusCode, signedDocInfo);

        StatusInfoRsp rsp = new StatusInfoRsp();
        rsp.setStatus(status.value);
        rsp.setStatusCode(statusCode.value);
        rsp.setSignedDocInfo(signedDocInfo.value);
        return rsp;
    }

    @Override
    public StatusInfoRsp getStatusInfo(StatusInfoReq req) {
        return getStatusInfo(req.getSesscode(), req.isReturnDocInfo(), req.isWaitSignature());
    }

    @Override
    public SignedDocRsp getSignedDoc(int sesscode) {
        Holder<String> status = new Holder<>();
        Holder<String> signedDocData = new Holder<>();

        getPort().getSignedDoc(sesscode, status, signedDocData);

        SignedDocRsp rsp = new SignedDocRsp();
        rsp.setStatus(status.value);
        rsp.setSignedDocData(signedDocData.value);
        return rsp;
    }

    @Override
    public StatusRsp closeSession(int sesscode) {
        return StatusRspImpl.of(getPort().closeSession(sesscode));
    }

    // MOBILE AUTH METHODS
    @Override
    public MobileAuthenticateRsp mobileAuthenticate(String idCode,
                                                    String countryCode,
                                                    String phoneNo,
                                                    String language,
                                                    String serviceName,
                                                    String messageToDisplay,
                                                    String spChallenge,
                                                    String messagingMode,
                                                    int asyncConfiguration,
                                                    boolean returnCertData,
                                                    boolean returnRevocationData) {
        Holder<Integer> sesscode = new Holder<>();
        Holder<String> status = new Holder<>();
        Holder<String> userIDCode = new Holder<>();
        Holder<String> userGivenname = new Holder<>();
        Holder<String> userSurname = new Holder<>();
        Holder<String> userCountry = new Holder<>();
        Holder<String> userCN = new Holder<>();
        Holder<String> certificateData = new Holder<>();
        Holder<String> challengeID = new Holder<>();
        Holder<String> challenge = new Holder<>();
        Holder<String> revocationData = new Holder<>();

        getPort().mobileAuthenticate(idCode,
                                     countryCode,
                                     phoneNo,
                                     language,
                                     serviceName,
                                     messageToDisplay,
                                     spChallenge,
                                     messagingMode,
                                     asyncConfiguration,
                                     returnCertData,
                                     returnRevocationData,
                                     sesscode,
                                     status,
                                     userIDCode,
                                     userGivenname,
                                     userSurname,
                                     userCountry,
                                     userCN,
                                     certificateData,
                                     challengeID,
                                     challenge,
                                     revocationData);

        MobileAuthenticateRsp rsp = new MobileAuthenticateRsp();
        rsp.setSesscode(sesscode.value);
        rsp.setStatus(status.value);
        rsp.setUserIDCode(userIDCode.value);
        rsp.setUserGivenname(userGivenname.value);
        rsp.setUserCountry(userCountry.value);
        rsp.setUserCN(userCN.value);
        rsp.setCertificateData(certificateData.value);
        rsp.setChallengeID(challengeID.value);
        rsp.setChallenge(challenge.value);
        rsp.setRevocationData(revocationData.value);

        return rsp;
    }

    @Override
    public MobileAuthenticateRsp mobileAuthenticate(MobileAuthenticateReq req) {
        return mobileAuthenticate(req.getIdCode(),
                                  req.getCountryCode(),
                                  req.getPhoneNo(),
                                  req.getLanguage(),
                                  nvl(req.getServiceName(), getServiceName()),
                                  req.getMessageToDisplay(),
                                  req.getSpChallenge(),
                                  req.getMessagingMode(),
                                  req.getAsyncConfiguration(),
                                  req.isReturnCertData(),
                                  req.isReturnRevocationData());
    }

    @Override
    public MobileAuthenticateStatusRsp getMobileAuthenticateStatus(int sesscode, boolean waitSignature) {
        Holder<String> status = new Holder<>();
        Holder<String> signature = new Holder<>();

        getPort().getMobileAuthenticateStatus(sesscode, waitSignature, status, signature);

        MobileAuthenticateStatusRsp rsp = new MobileAuthenticateStatusRsp();
        rsp.setStatus(status.value);
        rsp.setSignature(signature.value);
        return rsp;
    }

    @Override
    public MobileAuthenticateStatusRsp getMobileAuthenticateStatus(MobileAuthenticateStatusReq req) {
        return getMobileAuthenticateStatus(req.getSesscode(), req.isWaitSignature());
    }
}
