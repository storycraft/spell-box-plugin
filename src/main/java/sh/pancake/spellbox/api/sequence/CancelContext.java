package sh.pancake.spellbox.api.sequence;

import javax.annotation.Nullable;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class CancelContext {
    
    private boolean cancelled;

    @Nullable
    private Runnable cancelHandler;

    public CancelContext(boolean cancelled) {
        this.cancelled = cancelled;
        this.cancelHandler = null;
    }

    public CancelContext() {
        this(false);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    // Returns CancelHandler invalidator
    public void updateCancelHandler(Runnable cancelHandler) {
        this.cancelHandler = cancelHandler;
    }

    // Must to be called to cancel handler
    public void resetCancelHandler() {
        this.cancelHandler = null;
    }

    public void cancel() {
        this.cancelled = true;

        if (this.cancelHandler != null) {
            this.cancelHandler.run();
            this.resetCancelHandler();
        }
    }

}