package com.amoigoscode.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "",""
        );
        return null;
    }
}
