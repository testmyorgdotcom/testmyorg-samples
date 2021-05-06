package org.testmy.screenplay.ability;

import org.testmy.error.AbilityIsAbsentException;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.RefersToActor;

public class AbilityAs {

    public static <T extends Ability & RefersToActor> T actor(Actor actorWithAbility,
            Class<T> abilityClass) {
        final T ability = actorWithAbility.abilityTo(abilityClass);

        if (null == ability) {
            throw new AbilityIsAbsentException(String.format("Actor: %s has no specified ability: %s",
                    actorWithAbility.getName(), abilityClass.getSimpleName()));
        }
        ability.asActor(actorWithAbility);
        return ability;
    }

}
