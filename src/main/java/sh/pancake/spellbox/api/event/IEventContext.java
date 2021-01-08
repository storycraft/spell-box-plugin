package sh.pancake.spellbox.api.event;

public interface IEventContext<T> {
    
    Class<T> getEventClass();

    IEventHookFunc<T> getHook();

}
