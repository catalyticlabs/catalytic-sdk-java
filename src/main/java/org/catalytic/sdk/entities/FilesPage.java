package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of files
 */
public class FilesPage extends Page {

    private List<FileMetadata> files;

    public FilesPage(List<FileMetadata> files, int count, String nextPageToken) {
        this.files = files;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<FileMetadata> getFiles() {
        return files;
    }

    public void setFiles(List<FileMetadata> files) {
        this.files = files;
    }
}