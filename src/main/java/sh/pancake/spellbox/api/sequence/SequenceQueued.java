package sh.pancake.spellbox.api.sequence;

import javax.annotation.Nullable;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class SequenceQueued implements ISequenceCallback {

    private JavaPlugin plugin;
    private ISequence sequence;
    private ISequenceCallback callback;

    public SequenceQueued(JavaPlugin plugin, ISequence sequence, @Nullable ISequenceCallback callback) {
        this.plugin = plugin;
        this.sequence = sequence;
        this.callback = callback;
    }

    @Override
    public void onEnd(boolean cancelled) {
        if (cancelled) {
            if (callback != null) callback.onEnd(true);
            return;
        }

        sequence.resolve(plugin, callback);
    }
    
}
