package sh.pancake.spellbox.api.event;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EntityLimitContext<T extends EntityEvent> implements ILimitedContext<T> {

    private IEventContext<T> context;
    private Entity entity;

    public EntityLimitContext(IEventContext<T> context, Entity entity) {
        this.context = context;
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return entity;
    }

    @Override
    public IEventContext<T> getContext() {
        return context;
    }

    @Override
    public boolean canExecute(T event) {
        return entity.equals(event.getEntity());
    }
}
