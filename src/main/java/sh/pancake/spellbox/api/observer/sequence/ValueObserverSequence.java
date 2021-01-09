package sh.pancake.spellbox.api.observer.sequence;

import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.observer.IValueObserver;
import sh.pancake.spellbox.api.observer.ObserverWatcher;
import sh.pancake.spellbox.api.observer.ObserverWatcher.ISnapshotWatcher;
import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;

public class ValueObserverSequence<T> implements ISequence {

    private IValueObserver<T> observer;
    private ISnapshotWatcher<T> watcher;

    private int checkInterval;

    public ValueObserverSequence(IValueObserver<T> observer, ISnapshotWatcher<T> watcher, int checkInterval) {
        this.observer = observer;
        this.watcher = watcher;
        this.checkInterval = checkInterval;
    }

    public ValueObserverSequence(IValueObserver<T> observer, ISnapshotWatcher<T> watcher) {
        this(observer, watcher, 1);
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        new ObserverWatcher<>(plugin, observer, (snapshot) -> {
            if (watcher.watch(snapshot)) {
                if (callback != null) callback.onEnd(false);

                return true;
            } else {
                return false;
            }
        }, checkInterval);
    }

}
