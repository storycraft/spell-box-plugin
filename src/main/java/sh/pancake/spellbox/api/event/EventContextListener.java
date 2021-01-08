package sh.pancake.spellbox.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EventContextListener implements IContextListener<Event> {
    
    private JavaPlugin plugin;
    private Listener listener;

    public EventContextListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.listener = new Listener() {};
    }

    @SuppressWarnings("unchecked")
    public <T extends Event>void bindContext(IEventContext<T> context) {
        Class<T> eventClass = context.getEventClass();
        
        plugin.getServer().getPluginManager().registerEvent(
            eventClass,
            listener,
            EventPriority.NORMAL,
            (Listener eventListener, Event event) -> {
                if (eventClass.equals(event.getClass()) && listener == eventListener) {
                    context.getResolver().on((T) event);
                }
            },
            plugin
        );
    }

    @Override
    public void unbindAll() {
        HandlerList.unregisterAll(listener);
    }

}
