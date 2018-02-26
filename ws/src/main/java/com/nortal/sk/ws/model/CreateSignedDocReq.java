package com.nortal.sk.ws.model;

/**
 * 8.3 CreateSignedDoc
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public interface CreateSignedDocReq extends SesscodeReq {
    /**
     * a format of a document container to be created (currently supported formats are DIGIDOC-XML 1.3 and BDOC 2.1)
     * 
     * @return
     */
    String getFormat();

    /**
     * a version number of the format of a creatable document container (currently the supported versions for
     * DIGIDOC-XML is 1.3 and BDOC 2.1)
     * 
     * @return
     */
    String getVersion();
}
