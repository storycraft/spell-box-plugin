package sh.pancake.spellbox.skill;

import sh.pancake.spellbox.api.event.filter.EntityLimitFilter;
import sh.pancake.spellbox.api.event.filter.FilteredContext;
import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.composition.OrderedSequence;
import sh.pancake.spellbox.api.event.sequence.EventHookSequence;
import sh.pancake.spellbox.target.IEntityTarget;
import sh.pancake.spellbox.spell.ISpell;
import sh.pancake.spellbox.spell.LeapSpell;
import sh.pancake.spellbox.spell.SpellSequence;

/*
 * Created on Mon Jan 04 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class LeapingSkill<T extends IEntityTarget> implements ISkill<T> {

    private float power;
    private ISpell<? super T> damageSpell;
    
    public LeapingSkill(
        float power,
        ISpell<? super T> damageSpell
        ) {
        this.power = power;
        this.damageSpell = damageSpell;
    }

    public float getPower() {
        return power;
    }

    public ISpell<? super T> getDamageSpell() {
        return damageSpell;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public void setDamageSpell(ISpell<? super T> damageSpell) {
        this.damageSpell = damageSpell;
    }

    @Override
    public ISequence use(T user) {
        return new OrderedSequence(
            new SpellSequence<>(
                user,
                new LeapSpell(
                    user
                    .getDirection()
                    .normalize()
                    .setY(power)
                )
            ),
            new EventHookSequence(
                new FilteredContext<>(new EntityLimitFilter<>(user.getEntity()), new LeapEventContext(true))
            ),
            new SpellSequence<>(user, damageSpell)
        );
    }

}
