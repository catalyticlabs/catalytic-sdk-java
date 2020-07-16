package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of files
 */
public class FilesPage extends Page {

    private List<File> files;

    public FilesPage(List<File> files, int count, String nextPageToken) {
        this.files = files;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "FilesPage{" +
                "files=" + files +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}