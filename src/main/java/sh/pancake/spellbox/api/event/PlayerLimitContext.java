package sh.pancake.spellbox.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class PlayerLimitContext<T extends PlayerEvent> implements ILimitedContext<T> {

    private IEventContext<T> innerContext;
    private Player player;

    public PlayerLimitContext(IEventContext<T> context, Player player) {
        this.innerContext = context;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public IEventContext<T> getContext() {
        return innerContext;
    }

    @Override
    public boolean canExecute(T event) {
        return player.equals(event.getPlayer());
    }

    
}
