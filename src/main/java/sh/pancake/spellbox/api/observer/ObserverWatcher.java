package sh.pancake.spellbox.api.observer;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import sh.pancake.spellbox.api.observer.IValueObserver.Snapshot;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class ObserverWatcher<T> {
    
    private IValueObserver<T> observer;
    private ISnapshotWatcher<T> watcher;

    private BukkitTask task;

    public ObserverWatcher(JavaPlugin plugin, IValueObserver<T> observer, ISnapshotWatcher<T> watcher, int interval) {
        this.observer = observer;
        this.watcher = watcher;

        this.task = plugin.getServer().getScheduler().runTaskTimer(plugin, this::updateSnapshot, interval, 0);
    }

    private void updateSnapshot() {
        if (task == null) return;

        if (watcher.watch(observer.getSnapshot())) {
            task.cancel();
            this.task = null;
        }
    }

    @FunctionalInterface
    public static interface ISnapshotWatcher<T> {

        // Return true if task should stop
        boolean watch(Snapshot<T> snapshot);

    }

}
