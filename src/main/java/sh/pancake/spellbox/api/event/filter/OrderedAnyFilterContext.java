package sh.pancake.spellbox.api.event.filter;

import java.util.Iterator;

import sh.pancake.spellbox.api.event.IEventContext;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class OrderedAnyFilterContext<T> implements IEventContext<T> {

    private IEventContext<T> context;
    private Iterable<IEventFilter<T>> filterIterable;

    public OrderedAnyFilterContext(Iterable<IEventFilter<T>> filterIterable, IEventContext<T> context) {
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

        private boolean resolved;

        public Resolver(Iterator<IEventFilter<T>> iterator, IEventResolver<? super T> resolver) {
            this.iterator = iterator;
            this.resolver = resolver;
            this.resolved = false;
        }

        @Override
        public void on(T event) {
            while (!resolved && iterator.hasNext()) {
                IEventFilter<T> next = iterator.next();
    
                next.filter(event, this::onResolved);
            }
        }

        private void onResolved(T event) {
            this.resolved = true;
            this.resolver.on(event);
        }

    }
    
}
