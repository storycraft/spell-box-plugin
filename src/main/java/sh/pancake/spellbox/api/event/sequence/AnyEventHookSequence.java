package sh.pancake.spellbox.api.event.sequence;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;
import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.event.EventContextListener;
import sh.pancake.spellbox.api.event.filter.FilteredContext;
import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class AnyEventHookSequence implements ISequence {

    private Iterable<IEventContext<? extends Event>> contextIterable;
    
    public AnyEventHookSequence(Iterable<IEventContext<? extends Event>> contextIterable) {
        this.contextIterable = contextIterable;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        EventContextListener listener = new EventContextListener(plugin);

        if (cancelContext != null) {
            cancelContext.updateCancelHandler(() -> {
                listener.unbindAll();
                if (callback != null) callback.onEnd(true);
            });
        }
        
        Iterator<IEventContext<? extends Event>> iterator = contextIterable.iterator();
        while (iterator.hasNext()) {
            IEventContext<? extends Event> context = iterator.next();
            resolveContext(listener, context, callback, cancelContext);
        }
    }

    protected <T extends Event>void resolveContext(
        EventContextListener listener,
        IEventContext<T> context,
        @Nullable ISequenceCallback callback,
        @Nullable CancelContext cancelContext
        ) {
            listener.bindContext(new FilteredContext<>((event, resolver) -> {
                resolver.on(event);
                
                if (cancelContext != null) cancelContext.resetCancelHandler();
    
                listener.unbindAll();
                if (callback != null) callback.onEnd(false);
            }, context));
    }
    
}
