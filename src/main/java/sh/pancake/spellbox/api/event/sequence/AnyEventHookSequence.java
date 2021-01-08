package sh.pancake.spellbox.api.event.sequence;

import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;
import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.event.EventContextListener;
import sh.pancake.spellbox.api.event.FilteredContext;
import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class AnyEventHookSequence implements ISequence {

    private List<IEventContext<? extends Event>> contextList;
    
    public AnyEventHookSequence(List<IEventContext<? extends Event>> contextList) {
        this.contextList = contextList;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        EventContextListener listener = new EventContextListener(plugin);
        
        for (IEventContext<? extends Event> context : contextList) {
            listener.bindContext(new FilteredContext<>(context, (event, resolver) -> {
                resolver.on(event);
                
                if (cancelContext != null) cancelContext.resetCancelHandler();
    
                listener.unbindAll();
    
                if (callback != null) callback.onEnd(false);
            }));
        }

        if (cancelContext != null) {
            cancelContext.updateCancelHandler(() -> {
                listener.unbindAll();
                if (callback != null) callback.onEnd(true);
            });
        }
    }
    
}
