package com.nortal.sk.client.ws.processor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.client.model.FileEntryType;
import com.nortal.sk.client.model.FileModel;
import com.nortal.sk.client.model.HashcodesType;
import com.nortal.sk.client.model.ObjectFactory;
import com.nortal.sk.client.processor.SignStateHolder;
import com.nortal.sk.ws.SkWs;
import com.nortal.sk.ws.constant.DigestTypeEnum;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class WsSignProcessorImpl extends WsProcessorImpl<SignStateHolder> implements WsSignProcessor {
    public WsSignProcessorImpl(SkWs wsClient) {
        super(wsClient);
    }

    @Override
    protected void prepare() throws Exception {
        // Read files from container
        if (getState().getDoc() != null) {
            List<FileModel> files = new ArrayList<>();
            ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(getState().getDoc().getContent()));

            int len;
            byte[] buf = new byte[1024];
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                if (!StringUtils.equalsIgnoreCase(entry.getName(), "mimetype") && !StringUtils.startsWithIgnoreCase(entry.getName(), "META-INF/")) {
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    while ((len = zis.read(buf)) > 0) {
                        os.write(buf, 0, len);
                    }
                    files.add(FileModel.of(entry.getName(), os.toByteArray()));
                }
            }
            getState().setFiles(files);
        }
    }

    @Override
    public String calculateDigest(FileModel file, String digestAlgorithm) throws Exception {
        return Base64.encodeBase64String(MessageDigest.getInstance(digestAlgorithm).digest(file.getContent()));
    }

    @Override
    public String toWSFormat() throws Exception {
        if (getState().getDoc() == null) {
            return StringUtils.EMPTY;
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(getState().getDoc().getContent()));
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
        zos.write(toHashCode(DigestTypeEnum.SHA256));
        zos.putNextEntry(new ZipEntry("META-INF/hashcodes-sha512.xml"));
        zos.write(toHashCode(DigestTypeEnum.SHA512));

        zis.close();
        zos.close();
        return Base64.encodeBase64String(os.toByteArray());
    }

    private byte[] toHashCode(DigestTypeEnum digestType) throws Exception {
        ObjectFactory of = new ObjectFactory();
        HashcodesType hashcodes = of.createHashcodesType();
        for (FileModel file : getState().getFiles()) {
            FileEntryType entry = of.createFileEntryType();
            entry.setFullPath(file.getName());
            entry.setSize(file.getSize());
            entry.setHash(calculateDigest(file, digestType.getCode()));
            hashcodes.getFileEntry().add(entry);
        }

        JAXBContext context = JAXBContext.newInstance("com.nortal.sk.client.model");
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter sw = new StringWriter();
        m.marshal(of.createHashcodes(hashcodes), sw);

        return sw.toString().getBytes();
    }

    @Override
    public void fromWSFormat(String data) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(Base64.decodeBase64(data)));
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

        for (FileModel file : getState().getFiles()) {
            zos.putNextEntry(new ZipEntry(file.getName()));
            zos.write(file.getContent());
        }
        zis.close();
        zos.close();

        getState().setDoc(FileModel.of(null, os.toByteArray()));
    }
}
