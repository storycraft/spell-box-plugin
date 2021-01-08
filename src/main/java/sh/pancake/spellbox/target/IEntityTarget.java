package sh.pancake.spellbox.target;

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface IEntityTarget extends ITarget {

    Entity getEntity();

    @Override
    default Location getLocation() {
        return getEntity().getLocation();
    }

    @Override
    default void teleportTo(Location location) {
        getEntity().teleport(location);
    }

    @Override
    default Vector getVelocity() {
        return getEntity().getVelocity();
    }

    @Override
    default void setVelocity(Vector vector) {
        getEntity().setVelocity(vector);
    }

    @Override
    default Vector getDirection() {
        return getEntity().getLocation().getDirection();
    }

    @Override
    default void damage(double damage, Damageable target) {
        target.damage(damage, getEntity());
    }

    @Override
    default boolean isSpellUser(Object target) {
        return getEntity().equals(target);
    }

}
