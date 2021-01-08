package sh.pancake.spellbox.api.event;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class NotEventContext<T> implements IEventContext<T> {
    
    private EventContext<T> context;
    private IEventHookFunc<T> notFunc;

    private boolean passed;

    public NotEventContext(Class<T> eventClass, IEventHookFunc<T> hookFunc, IEventHookFunc<T> notFunc) {
        this.passed = true;
        this.context = new EventContext<>(eventClass, this::eventHandler);
        this.notFunc = notFunc;
    }

    @Override
    public Class<T> getEventClass() {
        return context.getEventClass();
    }

    @Override
    public IEventHookFunc<T> getHook() {
        return context.getHook();
    }

    public IEventHookFunc<T> getNotHook() {
        return notFunc;
    }

    private void toNot() {
        this.passed = false;
    }

    protected void eventHandler(T event, Runnable resolve) {
        if (passed) {
            getHook().onEvent(event, this::toNot);
        }
    
        if (passed) {
            resolve.run();
        } else {
            getNotHook().onEvent(event, resolve);
            this.passed = true;
        }
    }
}
