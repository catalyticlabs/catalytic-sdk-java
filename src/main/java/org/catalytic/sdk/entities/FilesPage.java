package org.catalytic.sdk.entities;

/**
 * An object which represents a page of files
 */
public class FilesPage extends Page {

    private File[] files;

    public FilesPage(File[] files, int count, String nextPageToken) {
        this.files = files;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }
}