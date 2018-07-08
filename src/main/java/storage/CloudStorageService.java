package storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public interface CloudStorageService {
    /**
     * Stores the Content in Cloud
     *
     * @param objectName  Object Name against which this Content should be stored
     * @param contentType Content-Type which will be used as Content-Type Header when this Content will be stored
     * @param content     Raw Content Byte Array
     * @return URL where this content can be accessed
     */
    URL store(String objectName, String contentType, byte[] content) throws IOException;

    /**
     * Stores the Content in Cloud
     *
     * @param objectName  Object Name against which this Content should be stored
     * @param contentType Content-Type which will be used as Content-Type Header when this Content will be stored
     * @param content     Raw Content Input Stream
     * @return URL where this content can be accessed
     */
    URL store(String objectName, String contentType, InputStream content) throws IOException;

    /**
     * Stores the Content in Cloud
     *
     * @param objectName                  Object Name against which this Content should be stored
     * @param contentType                 Content-Type which will be used as Content-Type Header when this Content will be stored
     * @param content                     Raw Content Input Stream
     * @param shouldOverrideDefaultDomain Should override default domain
     * @return URL where this content can be accessed
     */
    URL store(String objectName, String contentType, InputStream content, boolean shouldOverrideDefaultDomain) throws IOException;

    /**
     * @param objectName    Object Name against which this Content should be stored
     * @param contentType   Content-Type which will be used as Content-Type Header when this Content will be stored
     * @param contentLength length of content (file.length())
     * @param content       Input stream of raw content
     * @throws IOException
     */
    URL store(String objectName, String contentType, long contentLength, InputStream content) throws IOException;


    /**
     * @param objectName    Object Name against which this Content should be stored
     * @param contentType   Content-Type which will be used as Content-Type Header when this Content will be stored
     * @param contentLength length of content (file.length())
     * @param content       Input stream of raw content
     * @throws IOException
     */
    URL store(String objectName, String contentType, long contentLength, InputStream content,boolean isPrivate) throws IOException;



    /**
     * Stores the Content in Cloud in Compressed Form
     *
     * @param objectName  Object Name against which this Content should be stored
     * @param contentType Content-Type which will be used as Content-Type Header when this Content will be stored
     * @param content     Raw Content Byte Array
     * @return URL where this content can be accessed
     */
    URL storeCompressed(String objectName, String contentType, byte[] content) throws IOException;

    /**
     * Stores the Content in Cloud in Compressed Form
     *
     * @param objectName  Object Name against which this Content should be stored
     * @param contentType Content-Type which will be used as Content-Type Header when this Content will be stored
     * @param content     Raw Content Input Stream
     * @return URL where this content can be accessed
     */
    URL storeCompressed(String objectName, String contentType, InputStream content) throws IOException;

    /**
     * Reads the File from Cloud
     *
     * @return InputStream
     */
    InputStream read(String objectName) throws IOException;

    /**
     * Checks whether Object exists in Cloud Storage
     *
     * @throws IOException
     */
    boolean exists(String objectName) throws IOException;

    /**
     * Get the Content URL for this Object. Useful for URL computation when upload is going on
     */
    URL getContentURL(String objectName) throws IOException;

    URL getContentURL(String objectName, boolean shouldOverrideDefaultDomain) throws IOException;

    URL storeFileWithRetry(String objectName, String fileName, String contentType) throws IOException;

    URL storeFileWithRetry(String objectName, File file, String contentType) throws IOException;

    URL storeFileWithRetry(String objectName, InputStream inputStream, String contentType) throws IOException;

    URL storeContentWithRetry(String objectName, String content, String contentType) throws IOException;

    URL storeContentWithRetry(String objectName, byte[] content, String contentType) throws IOException;

    URL storeFileWithRetry(String objectName, File file, String contentType, boolean isPrivate) throws IOException;

    /**
     * Upload the file to the storage service using multi part upload. This method call is asynchronous, if the caller thread will return immediately.
     *
     * @param objectName The name of the file
     * @param file       The {@link java.io.File}
     * @return The {@link URL} of the uploaded file.
     * @throws IOException
     */
    /*@SuppressWarnings("UnusedDeclaration")
    URL multipartUploadFile(String objectName, File file) throws IOException;

    *//**
     * Upload the file to the storage service using multi part upload. This method call is asynchronous, if the caller thread will return immediately.
     *
     * @param objectName       The name of the file
     * @param file             The {@link java.io.File}
     * @param progressCallback The {@link MultiPartUploadProgressCallback}progress callback instance.
     * @return The {@link URL} of the uploaded file.
     * @throws IOException
     *//*
    URL multipartUploadFile(String objectName, File file, MultiPartUploadProgressCallback progressCallback) throws IOException;
*/
    void deleteObject(String objectName) throws IOException;

    /**
     * Returns the files for the given dir
     *
     * @param dir the dir to act on
     * @return an Iterable of CloudFiles that works across large data sets
     */
    Iterable<CloudFile> listFiles(String dir);

    /**
     * Returns the files for the given dir
     *
     * @param dir the dir to act on
     * @return an Iterable of CloudFiles that works across large data sets
     */
    Iterable<CloudFile> listFiles(CloudFile dir);

    /**
     * Returns the files for the given dir
     *
     * @param recursive If recursive will return everything under this folder, otherwise just what's under the current folder
     * @return an Iterable of CloudFiles that works across large data sets
     */
    Iterable<CloudFile> listFiles(CloudFile dir, boolean recursive);

    /**
     * Returns the files for the given dir
     *
     * @param recursive If recursive will return everything under this folder, otherwise just what's under the current folder
     * @return an Iterable of CloudFiles that works across large data sets
     */
    Iterable<CloudFile> listFiles(String dir, boolean recursive);

    /**
     * Returns a new or existing file for the given path
     *
     * @param path strings to be joined to form the path string
     * @return the resulting {@code CloudFile}
     */
    CloudFile getFile(String... path);

    /**
     * Returns a new or existing directory for the given path
     *
     * @param path strings to be joined to form the path string
     * @return the resulting {@code CloudFile}
     */
    CloudFile getDirectory(String... path);

    //CloudStorageServiceFactory.CloudStorageType getCloudStorageType();
}
