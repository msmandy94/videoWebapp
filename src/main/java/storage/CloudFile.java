package storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public interface CloudFile {

    String PATH_DELIMITER = "/";

    /**
     * Returns the full path to the file including the file name and extension
     *
     * @return the full path to the file
     */
    String getPath();

    /**
     * Returns the name of the file. For example, a path like: "/foo/bar/baz.zip", the file name would be baz.zip
     *
     * @return the name of the file
     */
    String getName();

    /**
     * Returns true if this is not a file but a partial path or directory
     *
     * @return true if this is a directory, otherwise false
     */
    boolean isDirectory();

    /**
     * Stolen from amazon s3 lingo, this is a top level mount point
     *
     * @return the name of the bucket
     */
    String getBucket();

    /**
     * Returns an input stream to the raw data associated with the file
     *
     * @return a raw input stream
     * @throws IOException
     * @throws FileNotFoundException if this is a directory or the file doesn't exist
     */
    InputStream getRawInputStream() throws IOException, FileNotFoundException;

    /**
     * Returns the files for the given dir, not recursive
     *
     * @return an Iterable of CloudFiles that works across large data sets
     */
    Iterable<CloudFile> listFiles();

    /**
     * Returns the files for the given dir
     *
     * @param recursive If recursive will return everything under this folder, otherwise just what's under the current folder
     * @return an Iterable of CloudFiles that works across large data sets
     */
    Iterable<CloudFile> listFiles(boolean recursive);

    /**
     * Splits up the path by the PATH_DELIMITER
     */
    List<String> getPathParts();

    /**
     * Returns the proper decompression stream wrapper when the file is compressed,
     * otherwise the underlining/raw InputStream is returned
     *
     * @return
     */
    InputStream getInputStream() throws IOException, FileNotFoundException;

    /**
     * Downloads all the files to the given local directory
     *
     * @param downloadRequest
     */
    Future<?> downloadDirectory(DownloadRequest downloadRequest);

}
