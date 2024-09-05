package Shit;



import java.io.Serializable;
import java.sql.Timestamp;

public class FileData implements Serializable {
    private String filename;
    private Timestamp uploadTime;
    private String accessLevel;

 

    public FileData(String filename, Timestamp uploadTime, String accessLevel) {
		// TODO Auto-generated constructor stub   this.filename = filename;
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
}