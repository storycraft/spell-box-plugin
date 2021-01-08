package sh.pancake.spellbox.api.sequence.composition;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;
import sh.pancake.spellbox.api.sequence.SequenceQueued;

public class ComposedSequence implements ISequence {

    private ISequence[] sequences;

    public ComposedSequence(ISequence... sequences) {
        this.sequences = sequences;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        int len = this.sequences.length;

        if (len < 1) {
            if (callback != null) callback.onEnd(false);
            return;
        }

        ISequenceCallback lastCallback = callback;

        for (int i = len - 1; i >= 0; i--) {
            ISequence sequence = sequences[i];

            lastCallback = new SequenceQueued(plugin, sequence, lastCallback);
        }

        ISequence first = sequences[0];
        first.resolve(plugin, lastCallback);
    }
    
}
