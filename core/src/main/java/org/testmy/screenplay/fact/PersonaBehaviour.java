package org.testmy.screenplay.fact;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testmy.persona.Persona;
import org.testmy.persona.PersonaManager;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;

@AllArgsConstructor
public class PersonaBehaviour implements Fact{
    private static Map<Actor, String> actorsFacts = new ConcurrentHashMap<>();
    private static PersonaManager personaManager = new PersonaManager();

    private String persona;

    public static PersonaBehaviour of(String persona) {
        System.out.println(personaManager);
        return new PersonaBehaviour(persona);
    }

    @Override
    public void setup(final Actor actor) {
        actorsFacts.put(actor, persona);
    }

    public static Persona of(Actor actor) {
        if(!actorsFacts.containsKey(actor)){
            throw new IllegalArgumentException("Actor does not have Persona fact"); // TODO: add a better description
        }
        final String persona = actorsFacts.get(actor);
        return personaManager.reservePersonaFor(actor.getName(), persona);
    }
}
