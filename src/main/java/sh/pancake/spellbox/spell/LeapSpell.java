package sh.pancake.spellbox.spell;

import org.bukkit.util.Vector;

import sh.pancake.spellbox.target.ITarget;

/*
 * Created on Mon Jan 04 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

// 보는 방향으로 도약
public class LeapSpell implements ISpell<ITarget> {

    private Vector direction;

    public LeapSpell(Vector direction) {
        this.direction = direction;
    }

    @Override
    public void use(ITarget user) {
        user.setVelocity(direction);
    }
    
}
