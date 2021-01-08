package sh.pancake.spellbox.target;

import org.bukkit.entity.LivingEntity;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class LivingEntityTarget implements ILivingEntityTarget {

    private LivingEntity entity;

    public LivingEntityTarget(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }
    
}
