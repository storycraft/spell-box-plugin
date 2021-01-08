package sh.pancake.spellbox.target;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.util.Vector;

public interface ITarget {
    
    Location getLocation();

    Vector getDirection();

    // 유저 텔레포트
    void teleportTo(Location location);

    // 유저 velocity 값
    Vector getVelocity();
    void setVelocity(Vector vector);

    // target 에게 데미지 입힘
    void damage(double damage, Damageable target);

    // 해당 객체가 시전 유저인지 확인
    boolean isSpellUser(Object target);
}
