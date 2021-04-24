package com.testmyorg.samples.login;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CrendetialsProviderContext.class)
public class CredentialsProviderIT {
    @Autowired
    CredentialsProvider credProvider;
    @Test
    public void useCacheToSearchForData(){
        credProvider.loadCredentials("", Optional.empty());
    }
}
