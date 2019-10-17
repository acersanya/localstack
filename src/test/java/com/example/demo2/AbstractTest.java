package com.example.demo2;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;


@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTest {

    @ClassRule
    public static LocalStackContainer localstack = new LocalStackContainer().withServices(S3);

    static {
        //not mandatory
        localstack.start();
    }

}
