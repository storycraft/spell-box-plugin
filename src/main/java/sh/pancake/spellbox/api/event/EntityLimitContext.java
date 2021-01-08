package sh.pancake.spellbox.api.event;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EntityLimitContext<T extends EntityEvent> implements ILimitedContext<T> {

    private IEventContext<T> innerContext;
    private Entity entity;

    public EntityLimitContext(IEventContext<T> context, Entity entity) {
        this.innerContext = context;
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return entity;
    }

    public IEventContext<T> getInnerContext() {
        return innerContext;
    }

    @Override
    public IEventContext<T> getContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canExecute(T event) {
        return entity.equals(event.getEntity());
    }
}
