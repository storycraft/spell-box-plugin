package sh.pancake.spellbox.spell;

import org.bukkit.entity.Damageable;

import sh.pancake.spellbox.target.ITarget;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class TargetDamageSpell implements ISpell<ITarget> {

    private Damageable target;
    private float damage;

    public TargetDamageSpell(Damageable target, float damage) {
        this.target = target;
        this.damage = damage;
    }

    @Override
    public void use(ITarget user) {
        user.damage(damage, target);
    }
    
}
