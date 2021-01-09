package sh.pancake.spellbox.api.event.filter;

import java.util.Iterator;

import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class OrderedFilterContext<T> implements IEventContext<T> {
    
    private IEventContext<T> context;
    private Iterable<IEventFilter<T>> filterIterable;

    public OrderedFilterContext(Iterable<IEventFilter<T>> filterIterable, IEventContext<T> context) {
        this.context = context;
        this.filterIterable = filterIterable;
    }

    @Override
    public Class<T> getEventClass() {
        return context.getEventClass();
    }

    @Override
    public IEventResolver<? super T> getResolver() {
        return new Resolver<>(filterIterable.iterator(), context.getResolver());
    }

    public static class Resolver<T> implements IEventResolver<T> {

        private Iterator<IEventFilter<T>> iterator;
        private IEventResolver<? super T> resolver;

        public Resolver(Iterator<IEventFilter<T>> iterator, IEventResolver<? super T> resolver) {
            this.iterator = iterator;
            this.resolver = resolver;
        }

        @Override
        public void on(T event) {
            if (iterator.hasNext()) {
                IEventFilter<T> next = iterator.next();
    
                next.filter(event, this::on);
            } else {
                resolver.on(event);
            }
        }

    }

}
