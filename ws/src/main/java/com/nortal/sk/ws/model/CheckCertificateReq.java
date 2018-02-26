package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.GeneralReq;

/**
 * 7.3 CheckCertificate - request
 * 
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface CheckCertificateReq extends GeneralReq {
    /**
     * Certificate to be checked for validity, in Base64 format. May include "--- BEGIN CERTIFICATE --- „ and „ --- END
     * CERTIFICATE ---" lines (according to PEM form at)
     * 
     * @return
     */
    String getCertificate();

    /**
     * If TRUE, certificate's validity information is returned on RevocationData field in response.
     * 
     * @return
     */
    boolean isReturnRevocationData();
}
