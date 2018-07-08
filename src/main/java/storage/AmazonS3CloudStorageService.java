package storage;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public class AmazonS3CloudStorageService {
    private final String bucket;
    private final boolean bucketNameDomainized;
    private final AmazonS3 s3Client;
    private TransferManager transferManager;

    public AmazonS3CloudStorageService(AmazonS3 s3Client, String bucket, boolean bucketNameDomainized) {
        this.bucket = bucket;
        this.bucketNameDomainized = bucketNameDomainized;
        this.s3Client = s3Client;
        this.transferManager = new TransferManager(s3Client);
        TransferManagerConfiguration configuration = new TransferManagerConfiguration();
        //configuration.setMinimumUploadPartSize(ByteUnit.MB.toBytes(5));
        //configuration.setMultipartUploadThreshold(ByteUnit.MB.toBytes(16));
        transferManager.setConfiguration(configuration);
        //setAmazonHttpClientLogLevelToWarn();
    }

    public InputStream read(String objectName) throws IOException {
        S3Object object = s3Client.getObject(new GetObjectRequest(this.bucket, objectName));
        return object.getObjectContent();
    }

    public void setPolicy(String bucket, String policy) throws IOException {
        try {
            s3Client.setBucketPolicy(bucket, policy);
        } catch (AmazonClientException e) {
            throw new IOException(e);
        }
    }

    public List<S3ObjectSummary> listObjects() throws AmazonServiceException {
        return listObjects(null, null);
    }

    public List<S3ObjectSummary> listObjects(String prefix, String delimiter) throws AmazonServiceException {
        return listObjects(prefix, delimiter, null);
    }

    public List<S3ObjectSummary> listObjects(String prefix, String delimiter, Integer maxListingLength) throws AmazonServiceException {
        ListObjectsRequest listObjectRequest = new ListObjectsRequest(bucket, prefix, null, delimiter, maxListingLength);
        ObjectListing objectListing = s3Client.listObjects(listObjectRequest);
        List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        while (objectListing.isTruncated()) {
            objectListing = s3Client.listNextBatchOfObjects(objectListing);
            objectSummaries.addAll(objectListing.getObjectSummaries());
        }
        return objectSummaries;
    }

    public String getBucketName() {
        return bucket;
    }

    @SuppressWarnings("UnusedDeclaration")
    public boolean createBucket(String bucketName) {
        try {
            boolean exists = s3Client.doesBucketExist(bucketName);
            if (!exists) {
                s3Client.createBucket(bucketName);
            }
            return true;
        } catch (AmazonClientException e) {
            return false;
        }
    }

    public void createSite(String bucketName) {
        try {
            BucketWebsiteConfiguration configuration = new BucketWebsiteConfiguration("index.html", "error.html");
            s3Client.setBucketWebsiteConfiguration(bucketName, configuration);
        } catch (AmazonClientException e) {
            // ignore
        }
    }

    public void renameObject(String sourceBucket, String sourceKey, String destinationKey) {
        renameObject(sourceBucket, sourceKey, bucket, destinationKey);
    }

    public void renameObject(String sourceBucket, String sourceKey, String destBucket, String destKey) {
        try {
            CopyObjectRequest request = new CopyObjectRequest(sourceBucket, sourceKey, destBucket, destKey);
            s3Client.copyObject(request);
            s3Client.deleteObject(sourceBucket, sourceBucket);
        } catch (AmazonClientException ignored) {
        }
    }

    public AmazonS3 getAmazonS3Client() {
        return s3Client;
    }

}
