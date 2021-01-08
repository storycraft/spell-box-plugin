package sh.pancake.spellbox.skill;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import sh.pancake.spellbox.api.event.EventContext;
import sh.pancake.spellbox.api.event.ICancellableEventContext;
import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class LeapEventContext implements ICancellableEventContext<EntityDamageEvent> {

    private boolean cancelled;

    private EventContext<EntityDamageEvent> context;

    public LeapEventContext(boolean cancelled) {
        this.cancelled = cancelled;
        this.context = new EventContext<>(EntityDamageEvent.class, this::onEvent);
    }

    @Override
    public IEventContext<EntityDamageEvent> getContext() {
        return context;
    }
    
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    protected void onEvent(EntityDamageEvent e, Runnable resolve) {
        if (e.getCause() != DamageCause.FALL) return;

        e.setCancelled(cancelled);

        resolve.run();
    }

}
