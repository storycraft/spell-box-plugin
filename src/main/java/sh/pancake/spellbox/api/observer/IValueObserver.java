package sh.pancake.spellbox.api.observer;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface IValueObserver<T> {
    
    T getLastValue();

    T getCurrentValue();

    void update();

    default Snapshot<T> getSnapshot() {
        return new Snapshot<>(getCurrentValue(), getLastValue());
    }

    @FunctionalInterface
    public interface IObserverFunc<T> {

        T get();

    }

    public class Snapshot<T> {

        private T value;
        private T lastValue;

        public Snapshot(T value, T lastValue) {
            this.value = value;
            this.lastValue = lastValue;
        }

        public T getValue() {
            return value;
        }

        public T getLastValue() {
            return lastValue;
        }

        public boolean isChanged() {
            return value != lastValue && (value == null || !value.equals(lastValue));
        }

    }

}
