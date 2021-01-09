package sh.pancake.spellbox.api.event.filter;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;

import sh.pancake.spellbox.api.event.IEventContext.IEventResolver;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EntityLimitFilter<T extends EntityEvent> implements IEventFilter<T> {

    private Entity entity;

    public EntityLimitFilter(Entity entity) {
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void filter(T event, IEventResolver<? super T> resolver) {
        if (entity.equals(event.getEntity())) {
            resolver.on(event);
        }
    }

}
