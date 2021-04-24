package org.testmy.persona.auth;

import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.testmy.persona.Persona;

public class LightCredentialsProvider {
    public static String passwordPropertyName = "testmyorg.commonPass";

    public static Credentials getCredentialsFor(Persona testPersona) {
        return new UsernamePasswordCredentials(testPersona.getUsername(), System.getProperty(passwordPropertyName));
    }
}
