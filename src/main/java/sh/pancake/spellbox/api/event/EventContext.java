package sh.pancake.spellbox.api.event;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class EventContext<T> implements IEventContext<T> {

    private Class<T> eventClass;
    private IEventHookFunc<T> hook;

    public EventContext(Class<T> eventClass, IEventHookFunc<T> hook) {
        this.eventClass = eventClass;
        this.hook = hook;
    }

    public Class<T> getEventClass() {
        return eventClass;
    }

    public IEventHookFunc<T> getHook() {
        return hook;
    }
}