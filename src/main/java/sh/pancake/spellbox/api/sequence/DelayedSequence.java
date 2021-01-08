package sh.pancake.spellbox.api.sequence;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class DelayedSequence implements ISequence {

    private long delay;

    public DelayedSequence(long delay) {
        this.delay = delay;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        final BukkitTask task = plugin.getServer().getScheduler().runTaskLater(
            plugin,
            () -> {
                if (cancelContext != null) cancelContext.resetCancelHandler();

                callback.onEnd(false);
            },
            delay
        );

        if (cancelContext != null) {
            cancelContext.updateCancelHandler(() -> {
                task.cancel();

                callback.onEnd(true);
            });
        }
    }
    
}
