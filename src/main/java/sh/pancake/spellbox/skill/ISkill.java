package sh.pancake.spellbox.skill;

import sh.pancake.spellbox.api.sequence.ISequence;
import sh.pancake.spellbox.target.ITarget;

/*
 * Created on Thu Jan 07 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface ISkill<T extends ITarget> {

    // user가 스킬 사용
    ISequence use(T user);

}