package sh.pancake.spellbox.api.event;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface ILimitedContext<T> extends IEventContext<T> {

    IEventContext<T> getContext();

    boolean canExecute(T event);

    @Override
    default Class<T> getEventClass() {
        return getContext().getEventClass();
    }

    @Override
    default IEventHookFunc<T> getHook() {
        return this::innerHook;
    }

    default void innerHook(T event, Runnable resolve) {
        if (canExecute(event)) {
            getContext().getHook().onEvent(event, resolve);
        }
    }
    
}
