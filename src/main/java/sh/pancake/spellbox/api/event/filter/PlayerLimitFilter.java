package sh.pancake.spellbox.api.event.filter;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import sh.pancake.spellbox.api.event.IEventContext.IEventResolver;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class PlayerLimitFilter<T extends PlayerEvent> implements IEventFilter<T> {

    private Player player;

    public PlayerLimitFilter(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return player;
    }

    @Override
    public void filter(T event, IEventResolver<? super T> resolver) {
        if (player.equals(event.getPlayer())) {
            resolver.on(event);
        }
    }

}
