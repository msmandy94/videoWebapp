package storage;

import java.io.File;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public class FileDownloadEvent {

    public enum EventType {
        FILE_DOWNLOAD_COMPLETE,
        DIR_DOWNLOAD_COMPLETE,
        DOWNLOAD_FAILED
    }

    private CloudFile cloudFile;
    private File file;
    private EventType eventType;

    public FileDownloadEvent(CloudFile cloudFile, File file, EventType eventType) {
        this.cloudFile = cloudFile;
        this.file = file;
        this.eventType = eventType;
    }

    public File getFile() {
        return file;
    }

    public EventType getEventType() {
        return eventType;
    }

    public CloudFile getCloudFile() {
        return cloudFile;
    }
}
