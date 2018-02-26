package com.nortal.sk.client.jdigidoc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.client.model.FileEntryType;
import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.model.HashcodesType;
import com.nortal.sk.client.model.ObjectFactory;

import ee.sk.digidoc.Base64Util;
import ee.sk.digidoc.DataFile;
import ee.sk.digidoc.DataFileAttribute;
import ee.sk.digidoc.DigiDocException;
import ee.sk.digidoc.SignedDoc;
import ee.sk.digidoc.factory.DigiDocFactory;
import ee.sk.utils.ConfigManager;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class DigiDocClientUtils {
    private static MimetypesFileTypeMap MIME_TYPES = new MimetypesFileTypeMap();
    private static JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance("com.nortal.sk.client.model");
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private DigiDocClientUtils() {
    }

    private static DigiDocFactory getFactory() throws DigiDocException {
        return ConfigManager.instance().getDigiDocFactory();
    }

    public static boolean isBDOC(File file) throws FileNotFoundException {
        return isSignedDocOfType(new FileInputStream(file), true);
    }

    public static boolean isBDOC(byte[] content) {
        return isSignedDocOfType(new ByteArrayInputStream(content), true);
    }

    public static boolean isSignedDocOfType(InputStream is, boolean bdoc) {
        boolean result = false;
        try {
            ArrayList<DigiDocException> errors = new ArrayList<>();
            SignedDoc doc = getFactory().readSignedDocFromStreamOfType(is, bdoc, errors);
            result = doc != null && errors.isEmpty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static SignedDoc readSignedDoc(File file) throws FileNotFoundException, DigiDocException {
        return readSignedDoc(new FileInputStream(file), isBDOC(file));
    }

    public static SignedDoc readSignedDoc(byte[] content) throws DigiDocException {
        return readSignedDoc(new ByteArrayInputStream(content), isBDOC(content));
    }

    public static SignedDoc readSignedDoc(InputStream is, boolean bdoc) throws DigiDocException {
        return getFactory().readSignedDocFromStreamOfType(is, bdoc);
    }

    public static SignedDoc createBDOC(Collection<FileModel> files) throws DigiDocException {
        SignedDoc doc = createSignedDoc(SignedDoc.FORMAT_BDOC, SignedDoc.BDOC_VERSION_2_1, DataFile.CONTENT_BINARY, files);
        doc.setProfile(SignedDoc.BDOC_PROFILE_TM);
        return doc;
    }

    public static SignedDoc createSignedDoc(String format, String version, String contentType, Collection<FileModel> files) throws DigiDocException {
        if (StringUtils.isBlank(version) || CollectionUtils.isEmpty(files)) {
            return null;
        }

        SignedDoc doc = new SignedDoc(format, version);
        for (FileModel item : files) {
            String mimeType = item.getMimeType();
            if (StringUtils.isBlank(mimeType)) {
                mimeType = MIME_TYPES.getContentType(item.getName());
            }
            DataFile df = new DataFile(doc.getNewDataFileId(), contentType, item.getName(), mimeType, doc);
            df.setSize(item.getSize());
            df.setBody(item.getContent());
            doc.addDataFile(df);
        }
        return doc;
    }

    public static String toWSFormat(SignedDoc doc) throws DigiDocException, IOException, JAXBException {
        if (doc == null) {
            return null;
        }
        if (StringUtils.equalsIgnoreCase(SignedDoc.FORMAT_BDOC, doc.getFormat())) {
            return toBDOCWSFormat(doc);
        }
        else if (StringUtils.equalsIgnoreCase(SignedDoc.FORMAT_DIGIDOC_XML, doc.getFormat())) {
            return toDDOCWSFormat(doc);
        }
        return null;
    }

    private static String toBDOCWSFormat(SignedDoc doc) throws DigiDocException, IOException, JAXBException {
        ByteArrayOutputStream dos = new ByteArrayOutputStream();
        doc.writeToStream(dos);

        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(dos.toByteArray()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(os);

        int len;
        byte[] buf = new byte[1024];
        ZipEntry entry = null;
        while ((entry = zis.getNextEntry()) != null) {
            if (StringUtils.equalsIgnoreCase(entry.getName(), "mimetype") || StringUtils.startsWithIgnoreCase(entry.getName(), "META-INF/")) {
                zos.putNextEntry(new ZipEntry(entry));
                while ((len = zis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
            }
        }
        zos.putNextEntry(new ZipEntry("META-INF/hashcodes-sha256.xml"));
        zos.write(dataFilesToHash(doc, SignedDoc.SHA256_DIGEST_TYPE));
        zos.putNextEntry(new ZipEntry("META-INF/hashcodes-sha512.xml"));
        zos.write(dataFilesToHash(doc, SignedDoc.SHA512_DIGEST_TYPE));

        zis.close();
        zos.close();

        return Base64Util.encode(os.toByteArray());
    }

    @SuppressWarnings("unchecked")
    private static byte[] dataFilesToHash(SignedDoc doc, String digestType) throws DigiDocException, JAXBException {
        List<DataFile> dataFiles = doc.getDataFiles();

        ObjectFactory of = new ObjectFactory();
        HashcodesType hashcodes = of.createHashcodesType();
        for (DataFile df : dataFiles) {
            FileEntryType entry = of.createFileEntryType();
            entry.setFullPath(df.getFileName());
            entry.setSize(df.getSize());
            entry.setHash(Base64Util.encode(df.getDigestValueOfType(digestType)));
            hashcodes.getFileEntry().add(entry);
        }

        Marshaller m = JAXB_CONTEXT.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter sw = new StringWriter();
        m.marshal(of.createHashcodes(hashcodes), sw);

        return sw.toString().getBytes();
    }

    @SuppressWarnings("unchecked")
    private static String toDDOCWSFormat(SignedDoc doc) throws DigiDocException {
        List<DataFile> dataFiles = doc.getDataFiles();
        for (DataFile df : dataFiles) {
            df.setContentType(DataFile.CONTENT_HASHCODE);
            df.addAttribute(new DataFileAttribute("DigestType", DataFile.DIGEST_TYPE_SHA1));
            df.addAttribute(new DataFileAttribute("DigestValue", Base64Util.encode(df.getDigestValueOfType(DataFile.DIGEST_TYPE_SHA1), 0)));
        }
        return doc.toXML();
    }

    public static byte[] fromWSFormat(SignedDoc doc, String content) throws IOException, DigiDocException {
        if (doc == null) {
            return null;
        }
        if (StringUtils.equalsIgnoreCase(SignedDoc.FORMAT_BDOC, doc.getFormat())) {
            return fromBDOCWSFormat(doc, content);
        }
        else if (StringUtils.equalsIgnoreCase(SignedDoc.FORMAT_DIGIDOC_XML, doc.getFormat())) {
            return fromDDOCWSFormat(doc, content);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static byte[] fromBDOCWSFormat(SignedDoc doc, String content) throws IOException, DigiDocException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(Base64Util.decode(content)));
        ZipOutputStream zos = new ZipOutputStream(os);

        int len;
        byte[] buf = new byte[1024];
        ZipEntry entry = null;
        while ((entry = zis.getNextEntry()) != null) {
            if (!StringUtils.startsWithIgnoreCase(entry.getName(), "META-INF/hashcodes-sha")) {
                zos.putNextEntry(new ZipEntry(entry));
                while ((len = zis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
            }
        }

        List<DataFile> dataFiles = doc.getDataFiles();
        for (DataFile df : dataFiles) {
            zos.putNextEntry(new ZipEntry(df.getFileName()));
            zos.write(df.getBody());
        }
        zis.close();
        zos.close();

        return os.toByteArray();
    }

    private static byte[] fromDDOCWSFormat(SignedDoc doc, String content) throws DigiDocException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SignedDoc result = readSignedDoc(content.getBytes());
        for (int i = 0; i < result.countDataFiles(); i++) {
            DataFile df = result.getDataFile(i);
            df.setBody(Base64Util.decode(doc.getDataFile(i).getBody()));
            df.setContentType(DataFile.CONTENT_EMBEDDED_BASE64);
        }
        result.writeToStream(os);

        return os.toByteArray();
    }
}
