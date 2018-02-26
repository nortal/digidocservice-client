package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.GeneralReq;

/**
 * 8.1 StartSession
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface StartSessionReq extends GeneralReq {
    /**
     * This value is currently ignored and may be empty.
     * 
     * @return
     */
    String getSigningProfile();

    void setSigningProfile(String signingProfile);

    /**
     * BDOC or DDOC document. A DigiDoc in XML transformed to HTML - Escaped format. For example" <DataFile> ” should
     * be transformed to "&lt;DataFile&gt;" . The container in BDOC format should be coded to BASE64 before it is
     * delivered to the service.
     * 
     * @return
     */
    String getSigDocXML();

    void setSigDocXML(String sigDocXML);

    /**
     * A flag that indicates whether the data sent within the StartSession should be stored or the session should be
     * closed deleting all the temporary files straight after response. The default value is"false”.
     * 
     * @return
     */
    boolean isbHoldSession();

    void setbHoldSession(boolean bHoldSession);

    /**
     * Given parameter enables to send to service a data file within the StartSession request. Based on the file a
     * DigiDoc container is created. ( The BDOC format is not supported in this use case – please see the"
     * CreateSignedDoc ” operation ). For example , when sending a" cv.pdf ”, a" cv.ddoc ” is created which contains
     * the" cv.pdf" only. The structure of a datafile element is described in chapter 9.3 . While adding the datafile
     * it ’ s unnecessary to determine the id entifier. By default , DIGIDOC - XML 1.3 format fis created.
     * 
     * @return
     */
    DataFileData getDataFile();

    void setDataFile(DataFileData dataFile);
}
