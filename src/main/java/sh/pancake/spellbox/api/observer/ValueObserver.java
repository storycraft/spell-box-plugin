package sh.pancake.spellbox.api.observer;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class ValueObserver<T> implements IValueObserver<T> {

    private IObserverFunc<T> func;
    private T lastValue;
    
    public ValueObserver(IObserverFunc<T> func) {
        this.func = func;
        this.lastValue = null;

        this.update();
    }

    @Override
    public T getLastValue() {
        return lastValue;
    }

    @Override
    public T getCurrentValue() {
        return func.get();
    }

    @Override
    public void update() {
        this.lastValue = getCurrentValue();
    }



}
