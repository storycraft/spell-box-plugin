package sh.pancake.spellbox.skill;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class LeapEventContext implements IEventContext<EntityDamageEvent> {

    private boolean fallDamage;

    public LeapEventContext(boolean fallDamage) {
        this.fallDamage = fallDamage;
    }
    
    public boolean haveFallDamage() {
        return fallDamage;
    }

    protected void onEvent(EntityDamageEvent e) {
        if (e.getCause() != DamageCause.FALL) return;

        e.setCancelled(fallDamage);
    }

    @Override
    public Class<EntityDamageEvent> getEventClass() {
        return EntityDamageEvent.class;
    }

    @Override
    public IEventResolver<? super EntityDamageEvent> getResolver() {
        return this::onEvent;
    }

}
