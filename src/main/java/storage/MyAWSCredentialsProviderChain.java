package storage;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;

/**
 * Created by mandeepsingh on 04/07/18.
 */
public class MyAWSCredentialsProviderChain extends AWSCredentialsProviderChain {
    public MyAWSCredentialsProviderChain() {
        super(new InstanceProfileCredentialsProvider(), new MyPropertiesCredentialsProvider());
    }
}
