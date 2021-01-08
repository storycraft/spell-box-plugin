package sh.pancake.spellbox.api.event;

import javax.annotation.Nullable;

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

public class EventContextListener<T extends Event> {
    
    private IEventContext<T> listenerContext;

    public EventContextListener(IEventContext<T> listenerContext) {
        this.listenerContext = listenerContext;
    }

    public IEventContext<T> getListenerContext() {
        return listenerContext;
    }

    public void setListenerContext(IEventContext<T> listenerContext) {
        this.listenerContext = listenerContext;
    }

    @SuppressWarnings("unchecked")
    public Bind bind(JavaPlugin plugin, @Nullable IEventResolver<T> onEvent) {
        Listener listener = new Listener() {};
        Bind bind = new Bind(listener);
        
        plugin.getServer().getPluginManager().registerEvent(
            listenerContext.getEventClass(),
            listener,
            EventPriority.NORMAL,
            (Listener eventListener, Event event) -> {
                if (listenerContext.getEventClass().equals(event.getClass()) && listener == eventListener) {
                    listenerContext.getHook().onEvent(
                        (T) event,
                        () -> onEvent.on((T) event, plugin, bind)
                    );
                }
            },
            plugin
        );

        return bind;
    }

    public Bind bind(JavaPlugin plugin) {
        return bind(plugin, null);
    }

    public static class Bind {

        private Listener listener;

        private Bind(Listener listener) {
            this.listener = listener;
        }

        public Listener getListener() {
            return listener;
        }

        public void unbind() {
            HandlerList.unregisterAll(listener);
        }

    }

    @FunctionalInterface
    public static interface IEventResolver<T> {

        void on(T event, JavaPlugin plugin, Bind bind);
        
    }

}
