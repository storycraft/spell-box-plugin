package sh.pancake.spellbox.api.sequence.event;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;
import sh.pancake.spellbox.api.event.EventContext;
import sh.pancake.spellbox.api.event.EventContextListener;
import sh.pancake.spellbox.api.event.IEventContext;
import sh.pancake.spellbox.api.event.IEventHookFunc;
import sh.pancake.spellbox.api.sequence.CancelContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EventHookSequence<T extends Event> implements ISequence {

    private EventContextListener<T> contextListener;

    public EventHookSequence(IEventContext<T> context) {
        this.contextListener = new EventContextListener<>(context);
    }

    public EventHookSequence(Class<T> eventClass, IEventHookFunc<T> hook) {
        this(new EventContext<>(eventClass, hook));
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        final EventContextListener.Bind listenerBind = contextListener.bind(plugin, (event, pl, bind) -> {
            if (cancelContext != null) cancelContext.resetCancelHandler();

            bind.unbind();
            callback.onEnd(false);
        });

        if (cancelContext != null) {
            cancelContext.updateCancelHandler(() -> {
                listenerBind.unbind();
                callback.onEnd(true);
            });
        }
    }

}
