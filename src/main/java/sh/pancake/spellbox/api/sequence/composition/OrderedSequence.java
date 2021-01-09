package sh.pancake.spellbox.api.sequence.composition;

import java.util.Arrays;
import java.util.Iterator;

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

public class OrderedSequence implements ISequence {

    private Iterable<ISequence> sequenceIterable;

    public OrderedSequence(Iterable<ISequence> sequenceIterable) {
        this.sequenceIterable = sequenceIterable;
    }

    public OrderedSequence(ISequence... sequences) {
        this(Arrays.asList(sequences));
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        Iterator<ISequence> iterator = sequenceIterable.iterator();

        if (!iterator.hasNext()) {
            if (callback != null) callback.onEnd(false);
            return;
        }

        iterator.next().resolve(plugin, new Callback(plugin, iterator, callback, cancelContext), cancelContext);
    }

    public static class Callback implements ISequenceCallback {

        private JavaPlugin plugin;

        private Iterator<ISequence> iterator;
        private ISequenceCallback callback;

        @Nullable
        private CancelContext cancelContext;

        public Callback(JavaPlugin plugin, Iterator<ISequence> iterator, ISequenceCallback callback, @Nullable CancelContext cancelContext) {
            this.plugin = plugin;
            this.iterator = iterator;
            this.callback = callback;
            this.cancelContext = cancelContext;
        }

        @Override
        public void onEnd(boolean cancelled) {
            if (cancelled) {
                if (callback != null) callback.onEnd(true);
                return;
            }

            if (iterator.hasNext()) {
                ISequence next = iterator.next();

                next.resolve(plugin, this, cancelContext);
            } else {
                if (callback != null) callback.onEnd(false);
            }
        }

    }
    
}
