package sh.pancake.spellbox.api.event.filter;

import sh.pancake.spellbox.api.event.IEventContext.IEventResolver;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

@FunctionalInterface
public interface IEventFilter<T> {
    
    void filter(T event, IEventResolver<? super T> resolver);

}
