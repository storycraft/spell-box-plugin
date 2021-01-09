package sh.pancake.spellbox.api.observer;

import java.util.Map;

/*
 * Created on Sat Jan 09 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class MapValueObserver<K, T> implements IValueObserver<T> {

    private Map<K, T> map;
    private K key;

    private T lastValue;

    public MapValueObserver(Map<K, T> map, K key) {
        this.map = map;
        this.key = key;
        this.lastValue = null;

        this.update();
    }

    @Override
    public T getLastValue() {
        return lastValue;
    }

    @Override
    public T getCurrentValue() {
        return map.get(key);
    }

    @Override
    public void update() {
        this.lastValue = getCurrentValue();
    }
    
}
