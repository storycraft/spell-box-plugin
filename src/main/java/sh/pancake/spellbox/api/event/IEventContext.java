package sh.pancake.spellbox.api.event;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface IEventContext<T> {

    Class<T> getEventClass();
    
    IEventResolver<? super T> getResolver();
    
    @FunctionalInterface
    public static interface IEventResolver<T> {

        void on(T event);
        
    }

}
