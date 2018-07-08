package storage;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public class MyPropertiesCredentialsProvider implements AWSCredentialsProvider {
    private static final Logger LOG = LoggerFactory.getLogger(MyPropertiesCredentialsProvider.class);

    //private static Properties environment = SprProperties.getInstance();

    @Override
    public AWSCredentials getCredentials() {
        String accessKey = "AKIAIX4IUIKQSU6PXMNQ"; //decryptDefault(environment.getProperty("s3.key"));
        String secretKey = "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"; //decryptDefault(environment.getProperty("s3.secret"));

        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Override
    public void refresh() {}
}
