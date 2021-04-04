package org.testmy.screenplay.ability;

import org.apache.http.auth.Credentials;
import org.testmy.persona.Persona;
import org.testmy.persona.auth.LightCredentialsProvider;
import org.testmy.screenplay.fact.PersonaBehaviour;

import lombok.Getter;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class AuthenticateWithCredentials implements Ability { //, RefersToActor
    // private Actor actor;
    // @Autowired
    LightCredentialsProvider credentialsProvider;
    @Getter
    private String username;
    @Getter
    private String password;

    public static AuthenticateWithCredentials as(final Actor actor) {
        if (actor.abilityTo(AuthenticateWithCredentials.class) == null) {
            //TODO: add dedicated exception
            throw new IllegalArgumentException("Actor with name does not have this ability: " + actor.getName());
        }
        final AuthenticateWithCredentials result = actor.abilityTo(AuthenticateWithCredentials.class);
        final Persona persona = PersonaBehaviour.of(actor);
        final Credentials credentials = LightCredentialsProvider.getCredentialsFor(persona);
        result.username = credentials.getUserPrincipal().getName();
        result.password = credentials.getPassword();
        return result;
    }
}
