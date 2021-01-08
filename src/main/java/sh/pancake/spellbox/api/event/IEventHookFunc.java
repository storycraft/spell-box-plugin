package sh.pancake.spellbox.api.event;

@FunctionalInterface
public interface IEventHookFunc<T> {
        
    void onEvent(T event, Runnable resolve);

}
