package sh.pancake.spellbox.api.observer.sequence;

import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.observer.IValueObserver;
import sh.pancake.spellbox.api.observer.IValueObserver.Snapshot;
import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class ValueChangeSequence implements ISequence {

    private ValueObserverSequence<?> observerSequence;

    public ValueChangeSequence(IValueObserver<?> observer, int checkInterval) {
        this.observerSequence = new ValueObserverSequence<>(observer, this::isDone, checkInterval);
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        observerSequence.resolveChecked(plugin, callback, cancelContext);
    }

    private boolean isDone(Snapshot<?> snapshot) {
        return snapshot.isChanged();
    }
    
}
