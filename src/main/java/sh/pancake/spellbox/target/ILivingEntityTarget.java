package sh.pancake.spellbox.target;

import org.bukkit.entity.LivingEntity;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface ILivingEntityTarget extends IEntityTarget {
    
    LivingEntity getEntity();

}
