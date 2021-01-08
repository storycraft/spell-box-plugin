package sh.pancake.spellbox.spell;

import java.util.Iterator;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

import sh.pancake.spellbox.target.ITarget;

/*
 * Created on Mon Jan 04 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

// radius 반경안의 엔디티에게 데미지를 입힘
public class SplashDamageSpell implements ISpell<ITarget> {

    private float damage;
    private float radius;
    
    public SplashDamageSpell(float damage, float radius) {
        this.damage = damage;
        this.radius = radius;
    }

    @Override
    public void use(ITarget user) {
        Iterator<Entity> iter = user.getLocation().getNearbyEntities(radius, radius, radius).iterator();

        while (iter.hasNext()) {
            Entity entity = iter.next();

            if (user.isSpellUser(entity)) continue;

            if (!(entity instanceof Damageable)) continue;

            Damageable damageable = (Damageable) entity;
            user.damage(damage, damageable);
        }
    }
    


}
