package sh.pancake.spellbox.api.event;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EventContext<T> implements IEventContext<T> {

    private Class<T> eventClass;
    private IEventResolver<? super T> resolver;

    public EventContext(Class<T> eventClass, IEventResolver<? super T> resolver) {
        this.eventClass = eventClass;
        this.resolver = resolver;
    }

    public Class<T> getEventClass() {
        return eventClass;
    }
    
    public IEventResolver<? super T> getResolver() {
        return resolver;
    }
    
}