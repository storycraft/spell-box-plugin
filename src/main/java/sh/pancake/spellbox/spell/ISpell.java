package sh.pancake.spellbox.spell;

/*
 * Created on Wed Jan 06 2021
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

public interface ISpell<T> {

    // user가 스펠 사용
    public void use(T user);

}