package com.nortal.sk.ws.model;

/**
 * 8.16 PrepareSignature
 * 
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface PrepareSignatureReq extends SesscodeReq {
    /**
     * signer ’ s certificate transferred to HEX string format (from binary (DER) format ). Mostly the signing software
     * (signing component) in the user ’ s computer delivers the certificate in a proper format.
     * 
     * @return
     */
    String getSignersCertificate();

    /**
     * identifier of the private key ’ s slot on a smartcard . The signing software defines it ’ s value within reading
     * the signer ’ s certificate and forwards it to the signing application.
     * 
     * @return
     */
    String getSignersTokenId();

    /**
     * The text of the role or resolution defined by the user.
     * 
     * @return
     */
    String getRole();

    /**
     * Name of the city, where it ’ s signed
     * 
     * @return
     */
    String getCity();

    /**
     * Name of the state, where it ’ s signed.
     * 
     * @return
     */
    String getState();

    /**
     * Postal code of the signing location.
     * 
     * @return
     */
    String getPostalCode();

    /**
     * Name of the country, where it ’ s signed.
     * 
     * @return
     */
    String getCountry();

    /**
     * - LT_TM (Long Term with Time Mark): a profile for BDOC - TM (a BDOC signature with time - mark) and DDOC. LT_TM
     * is currently the default option . - LT (Long Term): Used for creating standard BDOC - TS (BDOC with time - stamp
     * / ASiC - E) signatures. Currently it is a reserved value that simply returns the error code 101 with the
     * following message: BDOC - TS signature format is not supported in the current service version. For signing BDOC
     * files with Mobile - ID, please use BDOC - TM format. Support for the LT profile is planned for the service
     * version 3.9
     * 
     * @return
     */
    String getSigningProfile();
}
