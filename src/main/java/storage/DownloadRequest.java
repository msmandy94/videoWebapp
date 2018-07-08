package storage;

import java.io.File;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public interface DownloadRequest {
    boolean isRecursive();

    File getDestinationDirectory();

    //FileDownloadListener getListener();

    public boolean isAutoUncompress();
}
