package xyz.nlaz.discover;

/**
 * Created by nlazaris on 5/10/16.
 */
public class MediaItem {
    private int imageId;
    private String bucketName;
    private String filePath;
    private String dateTaken;

    public MediaItem(int imageId, String bucketName, String filePath, String dateTaken) {
        this.imageId = imageId;
        this.bucketName = bucketName;
        this.filePath = filePath;
        this.dateTaken = dateTaken;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }
}
