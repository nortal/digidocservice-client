package com.nortal.sk.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

public class SkUtils {
    private SkUtils() {
    }

    public static X509Certificate getCert(String certHex) throws DecoderException, CertificateException {
        if (StringUtils.isBlank(certHex)) {
            return null;
        }

        InputStream is = new ByteArrayInputStream(Hex.decodeHex(certHex.toCharArray()));
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(is);
    }
}
