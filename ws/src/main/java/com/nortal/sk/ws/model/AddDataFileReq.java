package com.nortal.sk.ws.model;

/**
 * 8.4 AddDataFile
 * 
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface AddDataFileReq extends SesscodeReq {
    /**
     * Name of the data file without the path
     * 
     * @return
     */
    String getFileName();

    /**
     * Type of the datafile
     * 
     * @return
     */
    String getMimeType();

    /**
     * Data file’s content type (HASHCODE, EMBEDDED_BASE64) HASHCODE – To service is sent the hashcode only, not the
     * entire data file’s content. The method how to calculate the hashcode is described in parameter DigestType and the
     * hashcode itself is in parameter DigestValue. Please see section 8.1. how to calculate hash from the source data
     * file and how to send it to the service. EMBEDDED_BASE64 – The content of the file is in Base64 encoding in
     * Content parameter
     * 
     * @return
     */
    String getContentType();

    /**
     * The actual size of data file in bytes
     * 
     * @return
     */
    int getSize();

    /**
     * Hash code type of the data file. In case of DIGIDOC-XML format, "sha1" is supported; in case of BDOC, "sha256" is
     * supported. Required in case of HASHCODE content type of file only
     * 
     * @return
     */
    String getDigestType();

    /**
     * The value of data file’s hash in Base64 encoding. Required for HASHCODE content type only. In case of the
     * DIGIDOC-XML format, the hash is calculated over a DigiDoc <Datafile> element, using a canonicalized form (for
     * more information, see chapter 8.1). In case of BDOC, the has is calculated over the binary data file content
     * 
     * @return
     */
    String getDigestValue();

    /**
     * The content of data file in Base64 encoding, is set if Con tentType is EMBEDDED_BASE64
     * 
     * @return
     */
    String getContent();
}
