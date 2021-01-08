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

public class SharedAllEventHookSequence implements ISequence {

    private IEventContext<? extends Event>[] contexts;

    public SharedAllEventHookSequence(IEventContext<? extends Event>[] contexts) {
        this.contexts = contexts;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        final List<CancelContext> hookCancelContextList = new ArrayList<>();
        final List<IEventContext<?>> completedContextList = new ArrayList<>();
        
        for (IEventContext<? extends Event> context : contexts) {
            CancelContext hookCancelContext = new CancelContext();
            new EventHookSequence<>(context).resolve(plugin, (cancelled) -> {
                if (cancelled) return;
    
                for (CancelContext ctx : hookCancelContextList) {
                    ctx.cancel();
                }
    
                completedContextList.add(context);
    
                if (completedContextList.size() >= contexts.length && callback != null) callback.onEnd(false);
            }, hookCancelContext);

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
