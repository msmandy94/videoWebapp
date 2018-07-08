package storage;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public class main {
    private static volatile AmazonS3 amazonS3;

    public static void main(String[] args) {

        //final AmazonS3 s3Client;
/*        AmazonS3CloudStorageService amazonS3CloudStorageService = new AmazonS3CloudStorageService(getAmazonS3Client(), "top100videos", false);
        List<S3ObjectSummary> s3ObjectSummaries = amazonS3CloudStorageService.listObjects();
        for (S3ObjectSummary obj : s3ObjectSummaries) {
            System.out.println(obj.getBucketName() + obj.getSize() + obj.getOwner());
        }*/


        String input ="password";
        System.out.println(input.getBytes(Charset.defaultCharset()));
        System.out.println(input.hashCode());

    }

    public static AmazonS3 getAmazonS3Client() {
        if (amazonS3 == null) {
            //amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new MyPropertiesCredentialsProvider()).setRegion("ap-south-1"))
            amazonS3 = new AmazonS3Client(new MyPropertiesCredentialsProvider());
            amazonS3.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        }
        return amazonS3;
    }
}
