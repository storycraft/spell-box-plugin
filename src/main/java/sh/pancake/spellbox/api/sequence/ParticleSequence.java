package sh.pancake.spellbox.api.sequence;

import org.bukkit.plugin.java.JavaPlugin;



public class ParticleSequence implements ISequence {

    private ISequence sequence;

    public ParticleSequence(ISequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void resolveChecked(JavaPlugin plugin, ISequenceCallback callback, CancelContext cancelContext) {
        new HookSequence(sequence, () -> {
            // TODO
            return false;
        }).resolve(plugin, callback);
    }

}
