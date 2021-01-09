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

    private ClickType clickType;

    public ClickEventFilter(ClickType clickType) {
        this.clickType = clickType;
    }

    @Override
    public void filter(PlayerInteractEvent event, IEventResolver<? super PlayerInteractEvent> resolver) {
        Action action = event.getAction();

        if ((action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR)
            && (clickType == ClickType.LEFT || clickType == ClickType.ANY)
            || (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)
            && (clickType == ClickType.RIGHT || clickType == ClickType.ANY)) {
            resolver.on(event);
        }
    }

    public static enum ClickType {

        LEFT, RIGHT, ANY

    }

}
