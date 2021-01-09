package sh.pancake.spellbox.api.event.filter;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import sh.pancake.spellbox.api.event.IEventContext.IEventResolver;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class ClickEventFilter implements IEventFilter<PlayerInteractEvent> {

    private Action action;

    public ClickEventFilter(Action action) {
        this.action = action;
    }

    @Override
    public void filter(PlayerInteractEvent event, IEventResolver<? super PlayerInteractEvent> resolver) {
        if (action == event.getAction()) {
            resolver.on(event);
        }
    }

}
