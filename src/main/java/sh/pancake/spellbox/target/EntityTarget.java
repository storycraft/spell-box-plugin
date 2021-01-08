package sh.pancake.spellbox.target;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

import org.bukkit.entity.Entity;

public class EntityTarget implements IEntityTarget {

    private Entity entity;

    public EntityTarget(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
