package com.example.demo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

@SpringBootTest
class DemoApplicationTests extends AbstractTest {

    @Autowired
    LocalStackContainer localStackContainer;

    @Test
    public void someTestMethod() {
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(S3))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .build();

        s3.createBucket("foo");
        s3.putObject("foo", "bar", "baz");
    }

}
