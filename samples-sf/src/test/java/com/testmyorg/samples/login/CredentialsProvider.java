package com.testmyorg.samples.login;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.auth.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

public class CredentialsProvider {
    private ObjectMapper om = new ObjectMapper();
    @Value("classpath:data.json")
    Resource resourceFile;
    public Optional<Credentials> loadCredentials(String actorName, Optional<String> persona){
        try{
            final Map<String, String> result = om.readValue(resourceFile.getInputStream(), Map.class);
            System.out.println(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{

        }
        return Optional.empty();
    }
}
