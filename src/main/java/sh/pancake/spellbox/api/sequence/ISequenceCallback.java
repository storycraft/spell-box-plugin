package sh.pancake.spellbox.api.sequence;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

@FunctionalInterface
public interface ISequenceCallback {
    
    void onEnd(boolean cancelled);
    
}
