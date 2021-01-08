package sh.pancake.spellbox.api.sequence.event;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import sh.pancake.spellbox.api.event.EventContext;
import sh.pancake.spellbox.api.event.ICancellableEventContext;
import sh.pancake.spellbox.api.event.IEventContext;
/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class ClickEventContext implements ICancellableEventContext<PlayerInteractEvent> {

    private EventContext<PlayerInteractEvent> context;

    private ClickType clickType;
    private boolean cancelled;

    public ClickEventContext(ClickType clickType, boolean cancelled) {
        this.context = new EventContext<>(PlayerInteractEvent.class, this::clickHook);
        this.clickType = clickType;
        this.cancelled = cancelled;
    }

    public ClickEventContext(ClickType clickType) {
        this(clickType, false);
    }

    @Override
    public IEventContext<PlayerInteractEvent> getContext() {
        return context;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    protected void clickHook(PlayerInteractEvent event, Runnable resolve) {
        Action action = event.getAction();

        if ((action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR)
            && (clickType == ClickType.LEFT || clickType == ClickType.ANY)
            || (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)
            && (clickType == ClickType.RIGHT || clickType == ClickType.ANY)) {
            event.setCancelled(cancelled);
            resolve.run();
        }
    }

    public static enum ClickType {

        LEFT, RIGHT, ANY

    }

}
