package sh.pancake.spellbox.spell;

import org.bukkit.potion.PotionEffect;

import sh.pancake.spellbox.target.ILivingEntityTarget;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

// 시전시 시전자에게 이펙트 부여
public class PotionSpell implements ISpell<ILivingEntityTarget> {

    private PotionEffect effect;

    public PotionSpell(PotionEffect effect) {
        this.effect = effect;
    }

    @Override
    public void use(ILivingEntityTarget user) {
        user.getEntity().addPotionEffect(effect);
    }
    
}
