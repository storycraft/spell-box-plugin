package sh.pancake.spellbox;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import sh.pancake.spellbox.api.event.EventContextListener;
import sh.pancake.spellbox.api.event.FilteredContext;
import sh.pancake.spellbox.api.sequence.CancelContext;
import sh.pancake.spellbox.api.event.ClickEventFilter;
import sh.pancake.spellbox.api.event.EventContext;
import sh.pancake.spellbox.api.event.ClickEventFilter.ClickType;
import sh.pancake.spellbox.skill.ISkill;
import sh.pancake.spellbox.skill.LeapingSkill;
import sh.pancake.spellbox.target.EntityTarget;
import sh.pancake.spellbox.target.IEntityTarget;
import sh.pancake.spellbox.spell.SplashDamageSpell;

/*
 * Created on Mon Jan 04 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public class SpellboxPlugin extends JavaPlugin {

    private ISkill<IEntityTarget> leapingSkill;

    public SpellboxPlugin() {
        this.leapingSkill = new LeapingSkill<>(1.7f, new SplashDamageSpell(20, 5));
    }

    @Override
    public void onEnable() {
        new EventContextListener(this)
        .bindContext(
            new FilteredContext<>(
                new EventContext<>(PlayerInteractEvent.class, (event) -> {
                    leapingSkill.use(new EntityTarget(event.getPlayer())).resolve(this);
                }),
                new ClickEventFilter(ClickType.RIGHT)
            )
        );
    }

}