package sh.pancake.spellbox.api.event;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface ICancellableEventContext<T> extends IEventContext<T> {
    
    IEventContext<T> getContext();

    boolean isCancelled();
    void setCancelled(boolean cancelled);

    @Override
    default Class<T> getEventClass() {
        return getContext().getEventClass();
    }

    @Override
    default IEventHookFunc<T> getHook() {
        return getContext().getHook();
    }

}
