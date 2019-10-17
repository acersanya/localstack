package com.example.demo;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbstractTest.ConfigureLocalStack.class)
public abstract class AbstractTest {



    @TestConfiguration
    public static class ConfigureLocalStack {

        @Bean
        public LocalStackContainer localStackContainer() {
            LocalStackContainer localstack = new LocalStackContainer().withServices(S3);
            localstack.start();
            return localstack;
        }

        @Bean
        @Primary
        public AmazonS3 amazonS3(LocalStackContainer localStackContainer) {
            final  AwsClientBuilder.EndpointConfiguration S3_ENDPOINT_CONFIGURATION
                    = new AwsClientBuilder.EndpointConfiguration("http://localhost:4572", "us-east-1");

            final AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(S3_ENDPOINT_CONFIGURATION)
                    .withPathStyleAccessEnabled(true)
                    .build();
            amazonS3Client.createBucket("agency-test");
            return amazonS3Client;
        }
    }

}
