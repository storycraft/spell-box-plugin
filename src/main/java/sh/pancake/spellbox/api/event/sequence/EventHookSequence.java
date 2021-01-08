package sh.pancake.spellbox.api.event.sequence;

import com.google.common.collect.Lists;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.api.sequence.ISequenceCallback;
import sh.pancake.spellbox.api.event.IEventContext;
import sh.pancake.spellbox.api.sequence.CancelContext;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EventHookSequence implements ISequence {

    private AnyEventHookSequence inner;

    public EventHookSequence(IEventContext<? extends Event> context) {
        this.inner = new AnyEventHookSequence(Lists.newArrayList(context));
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        inner.resolveChecked(plugin, callback, cancelContext);
    }

}
