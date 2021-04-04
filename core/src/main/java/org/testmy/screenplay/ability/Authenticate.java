package org.testmy.screenplay.ability;

public class Authenticate {
    public static AuthenticateWithCredentials withCredentials() {
        return new AuthenticateWithCredentials();
    }
}
