package com.nortal.sk.ws.model;

/**
 * 8.5 MobileSign
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface MobileSignReq extends SesscodeReq {
    /**
     * Identification number of the signer (personal national ID number). It is recommended to use both input parameters
     * IDCode and PhoneNo! In case of Lithuanian Mobile - ID users Signer IDCode and Signer PhoneNo are mandatory.
     * 
     * @return
     */
    String getSignerIDCode();

    /**
     * Country which issued the personal national ID number in ISO 3166 - style 2 - character format (e.g." EE ” )
     * 
     * @return
     */
    String getSignersCountry();

    /**
     * Phone number of the signer with the country code in format +xxxxxxxxx (for example +3706234566). If both
     * SignerPhoneNo and SignerIDCode parameters are given, correspondence between personal code and phone number is
     * verified and in case of inconsistency SOAP error code 301 is returned. It is recommended to use both input
     * parameters IDCode and PhoneNo! In case of Lithuanian Mobile - ID users Signer IDCode and Signer PhoneNo are
     * mandatory (see chapter 5.2 ) . If the element " Signer PhoneNo" has been set, the country attribute set in the
     * prefix is used (independent on the value of the element " SignersCountry ").
     * 
     * @return
     */
    String getSignerPhoneNo();

    /**
     * Name of the service – previously agreed with Application Provider and DigiDocService operator. Required, maximum
     * length – 20 chars.
     * 
     * @return
     */
    String getServiceName();

    /**
     * Additional text shown to the signer. Optional. Maximum length is 40 bytes. In case of Latin letters, this means
     * also a 40 character long text, but Cyrillic characters may be encoded by two bytes and you will not be able to
     * send more than 20 symbols.
     * 
     * @return
     */
    String getAdditionalDataToBeDisplayed();

    /**
     * Language of the message displayed to the signer’s phone. ISO 639 a 3-character-code in uppercase is used (for
     * example EST, ENG, RUS, LIT).
     * 
     * @return
     */
    String getLanguage();

    /**
     * The text of the role or resolution defined by the user. Optional.
     * 
     * @return
     */
    String getRole();

    /**
     * Name of the city, where it’s signed. Optional.
     * 
     * @return
     */
    String getCity();

    /**
     * Name of the state/province, where it’s signed. Optional.
     * 
     * @return
     */
    String getStateOrProvince();

    /**
     * Postal code of the signing location. Optional.
     * 
     * @return
     */
    String getPostalCode();

    /**
     * Name of the country, where it’s signed. Optional.
     * 
     * @return
     */
    String getCountryName();

    /**
     * -"LT_TM" (Long Term with Time Mark): a profile for BDOC-TM (a BDOC signature with time-mark) and DDOC."LT_TM”
     * is currently the default option. -"LT” (Long Term): Used for creating standard BDOC-TS (BDOC with time-stamp /
     * ASiC-E) signatures. Currently it is a reserved value that simply returns the error code 101 with the following
     * message:"BDOC-TS signature format is not supported in the current service version. For signing BDOC files with
     * Mobile-ID, please use BDOC-TM format”. Support for the"LT” profile is planned for the service version 3.9.
     * 
     * @return
     */
    String getSigningProfile();

    /**
     * Determines the mode how the response of the MobileSign is returned. Following modes are supported:: -
     *"asynchClientServer” – Some additional status request are made after MobileSign request by the Application
     * Provider -"asynchServerServer” – After signing or in case of an error the server sends a request to the
     * client-application . The client application should be capable to act in server mode to recieve the signature
     * information request according to the parameters in AsyncConfiguration parameter.
     * 
     * @return
     */
    String getMessagingMode();

    /**
     * Determines configuration used in asynchServerServer messaging mode. This shall be agreed previously between
     * Application Provider and DigiDocService provider.
     * 
     * @return
     */
    int getAsyncConfiguration();

    /**
     * If the value is true, the DigiDoc file information is returned as a result of the request.
     * 
     * @return
     */
    boolean isReturnDocInfo();

    /**
     * If the value is true, a DigiDoc document in HTMLescaped format SignedDocData element is returned.
     * 
     * @return
     */
    boolean isReturnDocData();
}
