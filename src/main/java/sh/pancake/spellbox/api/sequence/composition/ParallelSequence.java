package sh.pancake.spellbox.api.sequence.composition;

import javax.annotation.Nullable;

import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class ParallelSequence implements ISequence {

    private ISequence sequence;

    @Nullable
    private ISequenceCallback callback;

    @Nullable
    private CancelContext cancelContext;

    public ParallelSequence(ISequence sequence, @Nullable ISequenceCallback callback, @Nullable CancelContext cancelContext) {
        this.sequence = sequence;
        this.callback = callback;
        this.cancelContext = cancelContext;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback skippedCallback, CancelContext skippedCancelContext) {
        sequence.resolveChecked(plugin, callback, cancelContext);
        if (skippedCallback != null) skippedCallback.onEnd(false);
    }
    
}
