package sh.pancake.spellbox.api.sequence;

import javax.annotation.Nullable;

import org.bukkit.plugin.java.JavaPlugin;



/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class HookSequence implements ISequence {
    
    private ISequence sequence;

    // Return true to cancel
    private IPreHook preHook;
    private ISequenceCallback postHook;

    public HookSequence(ISequence sequence, @Nullable IPreHook preHook, @Nullable ISequenceCallback postHook) {
        this.sequence = sequence;

        this.preHook = preHook;
        this.postHook = postHook;
    }

    public HookSequence(ISequence sequence, @Nullable ISequenceCallback postHook) {
        this(sequence, null, postHook);
    }

    public HookSequence(ISequence sequence, @Nullable IPreHook preHook) {
        this(sequence, preHook, null);
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        boolean result = false;
        if (preHook != null) {
            result = preHook.call();
        }

        if (result) {
            if (callback != null) callback.onEnd(true);
            return;
        }

        ISequenceCallback hookedCallback = (cancelled) -> {
            if (postHook != null) {
                postHook.onEnd(cancelled);
            }

            callback.onEnd(cancelled);
        };

        sequence.resolve(plugin, hookedCallback);
    }

    @FunctionalInterface
    public static interface IPreHook {

        boolean call();

    }

}