package sh.pancake.spellbox.api.particle.sequence;

import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.sequence.HookSequence;
import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class ParticleSequence implements ISequence {

    private ISequence sequence;

    public ParticleSequence(ISequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        new HookSequence(sequence, () -> {
            // TODO
            return false;
        }).resolve(plugin, callback);
    }

}
