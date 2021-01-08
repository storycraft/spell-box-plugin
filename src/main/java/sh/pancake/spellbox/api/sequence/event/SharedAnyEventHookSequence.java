package sh.pancake.spellbox.api.sequence.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;
import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class SharedAnyEventHookSequence implements ISequence {

    private IEventContext<? extends Event>[] contexts;
    
    public SharedAnyEventHookSequence(IEventContext<? extends Event>[] contexts) {
        this.contexts = contexts;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        final List<CancelContext> hookCancelContextList = new ArrayList<>();

        ISequenceCallback cancelCallback = (cancelled) -> {
            if (cancelled) return;

            for (CancelContext context : hookCancelContextList) {
                context.cancel();
            }

            if (callback != null) callback.onEnd(false);
        };
        
        for (IEventContext<? extends Event> context : contexts) {
            CancelContext hookCancelContext = new CancelContext();
            new EventHookSequence<>(context).resolve(plugin, cancelCallback, hookCancelContext);

            hookCancelContextList.add(hookCancelContext);
        }

        cancelContext.updateCancelHandler(() -> {
            for (CancelContext context : hookCancelContextList) {
                context.cancel();
            }

            if (callback != null) callback.onEnd(true);
        });
    }
    
}
