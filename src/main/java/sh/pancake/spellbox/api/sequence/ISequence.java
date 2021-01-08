package sh.pancake.spellbox.api.sequence;

import javax.annotation.Nullable;

import org.bukkit.plugin.java.JavaPlugin;



/*
 * Created on Mon Jan 04 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

@FunctionalInterface
public interface ISequence {

    void resolveChecked(JavaPlugin plugin, @Nullable ISequenceCallback callback, @Nullable CancelContext cancelContext);

    default void resolve(JavaPlugin plugin, @Nullable ISequenceCallback callback, @Nullable CancelContext cancelContext) {
        if (cancelContext != null && cancelContext.isCancelled()) {
            if (callback != null) callback.onEnd(true);
            return;
        }

        resolveChecked(plugin, callback, cancelContext);
    }

    default void resolve(JavaPlugin plugin, ISequenceCallback callback) {
        resolve(plugin, callback, null);
    }

    default void resolve(JavaPlugin plugin, CancelContext cancelContext) {
        resolve(plugin, null, cancelContext);
    }

    default void resolve(JavaPlugin plugin) {
        resolve(plugin, null, null);
    }

}
