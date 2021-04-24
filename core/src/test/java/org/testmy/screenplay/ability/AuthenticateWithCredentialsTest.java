package org.testmy.screenplay.ability;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


import org.junit.Test;
import org.testmy.screenplay.fact.PersonaBehaviour;

import net.serenitybdd.screenplay.Actor;

public class AuthenticateWithCredentialsTest {
    @Test
    public void testAuthenticateAs(){
        final Actor mike = Actor.named("Mike");

        mike
            .can(Authenticate.withCredentials())
            .has(PersonaBehaviour.of("Sales"));

        final AuthenticateWithCredentials authenticated = AuthenticateWithCredentials.as(mike);

        assertThat(authenticated.getUsername(), is(notNullValue()));
        assertThat(authenticated.getPassword(), is(notNullValue()));
    }
}
