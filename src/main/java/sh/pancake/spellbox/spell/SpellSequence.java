package sh.pancake.spellbox.spell;

import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.CancelContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;

public class SpellSequence<T> implements ISequence {

    private T user;
    private ISpell<T> spell;

    public SpellSequence(T user, ISpell<T> spell) {
        this.user = user;
        this.spell = spell;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        if (cancelContext != null && cancelContext.isCancelled()) {
            if (callback != null) callback.onEnd(true);
            return;
        }

        spell.use(user);

        if (callback != null) callback.onEnd(false);
    }
    
}