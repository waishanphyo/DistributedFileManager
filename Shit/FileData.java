package Shit;

import java.io.Serializable;
import java.sql.Timestamp;

public class FileData implements Serializable {
    private String filename;
    private Timestamp uploadTime;
    private String accessLevel;
    private int ownerId; // Changed from `id` to `ownerId` for clarity

    public FileData(int ownerId, String filename, Timestamp uploadTime, String accessLevel) {
        this.ownerId = ownerId;
        this.filename = filename;
        this.uploadTime = uploadTime;
        this.accessLevel = accessLevel;
    }

    public String getFilename() {
        return filename;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public boolean isOwner(int userId) {
        return this.ownerId == userId;
    }
}