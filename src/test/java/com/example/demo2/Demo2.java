package com.example.demo2;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.jupiter.api.Test;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

public class Demo2 extends AbstractTest {

    @Test
    public void someTestMethod() {
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(localstack.getEndpointConfiguration(S3))
                .withCredentials(localstack.getDefaultCredentialsProvider())
                .build();

        s3.createBucket("foo");
        s3.putObject("foo", "bar", "baz");
    }

}
