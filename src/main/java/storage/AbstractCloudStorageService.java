package storage;

import java.io.*;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public abstract class AbstractCloudStorageService {
    //private static final IORetryPolicy IO_RETRY_POLICY = new IORetryPolicy(5);

    /*protected InputStream compress(InputStream content) throws IOException {
        byte[] contentBytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = null;
        GZIPOutputStream gzipOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            int len;
            while ((len = content.read(contentBytes)) > 0) {
                gzipOutputStream.write(contentBytes, 0, len);
            }
            gzipOutputStream.flush();
            gzipOutputStream.finish();
            contentBytes = byteArrayOutputStream.toByteArray();
        } finally {
            //GenericIOUtils.closeClosable(byteArrayOutputStream);
            //GenericIOUtils.closeClosable(gzipOutputStream);
        }
        return new ByteArrayInputStream(contentBytes);
    }

    protected InputStream compress(byte[] content) throws IOException {
        byte[] contentBytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = null;
        GZIPOutputStream gzipOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content, 0, content.length);
            gzipOutputStream.flush();
            gzipOutputStream.finish();
            contentBytes = byteArrayOutputStream.toByteArray();
        } finally {
            //GenericIOUtils.closeClosable(byteArrayOutputStream);
            //GenericIOUtils.closeClosable(gzipOutputStream);
        }
        return new ByteArrayInputStream(contentBytes);
    }

    @Override
    public URL storeFileWithRetry(final String objectName, final String fileName, final String contentType) throws IOException {

        return executeWithIORetry(new RetryProcessCallback<URL>() {
            @Override
            public URL execute(RetryContext retryContext) throws Exception {
                InputStream inputStream = null;
                try {
                    File file = new File(fileName);
                    long contentLength = file.length();
                    inputStream = new FileInputStream(fileName);
                    return store(objectName, contentType, contentLength, inputStream);
                } finally {
                    IOUtils.closeQuietly(inputStream);
                }
            }
        });
    }

    @Override
    public URL storeFileWithRetry(final String objectName, final File file, final String contentType) throws IOException {
        return storeFileWithRetry(objectName, file, contentType, false);
    }

    @Override
    public URL storeFileWithRetry(final String objectName, final File file, final String contentType, boolean isPrivate) throws IOException {
        return executeWithIORetry(retryContext -> {
            InputStream inputStream = null;
            try {
                long contentLength = file.length();
                inputStream = new FileInputStream(file);
                return store(objectName, contentType, contentLength, inputStream, isPrivate);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        });
    }

    @Override
    public URL storeFileWithRetry(String objectName, final InputStream inputStream, String contentType) throws IOException {
        return executeWithIORetry(new RetryProcessCallback<URL>() {
            @Override
            public URL execute(RetryContext retryContext) throws Exception {
                try {
                    return store(objectName, contentType, inputStream);
                } finally {
                    IOUtils.closeQuietly(inputStream);
                }
            }
        });
    }

    @Override
    public URL storeContentWithRetry(final String objectName, final String content, final String contentType) throws IOException {
        byte[] byteContent = content.getBytes(GlobalConstants.UTF_8);
        return storeContentWithRetry(objectName, byteContent, contentType);
    }

    @Override
    public URL storeContentWithRetry(final String objectName, final byte[] content, final String contentType) throws IOException {
        return executeWithIORetry(new RetryProcessCallback<URL>() {
            @Override
            public URL execute(RetryContext retryContext) throws Exception {
                return store(objectName, contentType, content);
            }
        });
    }

    <T> T executeWithIORetry(RetryProcessCallback<T> processCallback) throws IOException {
        try {
            return ServiceLocatorFactory.getService(RetryService.class).executeWithRetry(processCallback, IO_RETRY_POLICY);
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw (IOException) e;
            } else {
                throw ExceptionUtils.wrapInRuntimeExceptionIfNecessary(e);
            }
        }
    }

    @Override
    public void deleteObject(String objectName) throws IOException {
        throw new UnsupportedOperationException("Delete is not supported");
    }

    @Override
    public Iterable<CloudFile> listFiles(String path) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Iterable<CloudFile> listFiles(CloudFile dir) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Iterable<CloudFile> listFiles(CloudFile dir, boolean recursive) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Iterable<CloudFile> listFiles(String dir, boolean recursive) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public CloudFile getFile(String... path) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public CloudFile getDirectory(String... path) {
        throw new UnsupportedOperationException("Implement me");
    }*/
}
