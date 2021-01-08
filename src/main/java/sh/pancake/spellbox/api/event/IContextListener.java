package sh.pancake.spellbox.api.event;

/*
 * Created on Fri Jan 08 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface IContextListener<T> {

    <E extends T>void bindContext(IEventContext<E> context);

    void unbindAll();

}
