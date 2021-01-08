package sh.pancake.spellbox.target;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.util.Vector;

public class EmptyTarget implements ITarget {

    private Location location;

    public EmptyTarget(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void teleportTo(Location location) {
        this.location = location;
    }

    @Override
    public Vector getVelocity() {
        return new Vector(0d, 0d, 0d);
    }

    @Override
    public void setVelocity(Vector vector) {

    }

    @Override
    public Vector getDirection() {
        return new Vector(0d, 0d, 0d);
    }

    @Override
    public void damage(double damage, Damageable target) {
        target.damage(damage);
    }

    @Override
    public boolean isSpellUser(Object target) {
        return false;
    }
    
}
