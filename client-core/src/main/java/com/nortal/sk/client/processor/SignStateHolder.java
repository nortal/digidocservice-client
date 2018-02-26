package com.nortal.sk.client.processor;

import java.util.Arrays;
import java.util.Collection;

import com.nortal.sk.client.model.FileModel;

// TODO: maybe interface?
public class SignStateHolder extends StateHolder {
    private Object container;
    private FileModel doc;
    private Collection<FileModel> files;

    @SuppressWarnings("unchecked")
    public <T> T getContainer() {
        return (T) container;
    }

    public <T> void setContainer(T container) {
        this.container = container;
    }

    public FileModel getDoc() {
        return doc;
    }

    public void setDoc(FileModel doc) {
        this.doc = doc;
    }

    public Collection<FileModel> getFiles() {
        return files;
    }

    public void setFiles(Collection<FileModel> files) {
        this.files = files;
    }

    public static SignStateHolder of(FileModel doc) {
        SignStateHolder state = new SignStateHolder();
        state.setDoc(doc);
        return state;
    }

    public static SignStateHolder of(FileModel... files) {
        SignStateHolder state = new SignStateHolder();
        state.setFiles(Arrays.asList(files));
        return state;
    }
}
