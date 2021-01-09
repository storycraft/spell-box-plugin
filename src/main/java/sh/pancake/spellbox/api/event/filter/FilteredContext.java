package sh.pancake.spellbox.api.event.filter;

import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class FilteredContext<T> implements IEventContext<T> {

    private IEventContext<T> context;
    private IEventResolver<T> resolver;

    public FilteredContext(IEventFilter<T> filter, IEventContext<T> context) {
        this.context = context;
        this.resolver = (event) -> {
            filter.filter(event, this.context.getResolver());
        };
    }

    @Override
    public Class<T> getEventClass() {
        return context.getEventClass();
    }

    @Override
    public IEventResolver<? super T> getResolver() {
        return resolver;
    }
    
}
