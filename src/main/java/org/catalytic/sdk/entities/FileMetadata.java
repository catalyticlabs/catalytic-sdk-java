package org.catalytic.sdk.entities;

/**
 * A File object
 */
public class FileMetadata {

    private String id;
    private String name;
    private String teamName;
    private String contentType;
    private int sizeInBytes;
    private String displaySize;
    private boolean isPublic;
    private String md5Hash;
    private String referenceName;

    public FileMetadata(String id, String name, String teamName, String contentType, int sizeInBytes, String displaySize, boolean isPublic, String md5Hash, String referenceName) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.contentType = contentType;
        this.sizeInBytes = sizeInBytes;
        this.displaySize = displaySize;
        this.isPublic = isPublic;
        this.md5Hash = md5Hash;
        this.referenceName = referenceName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getMd5Hash() {
        return md5Hash;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }
}