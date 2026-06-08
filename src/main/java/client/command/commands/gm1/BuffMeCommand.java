package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.Skill;
import client.SkillFactory;
import client.command.Command;
import server.StatEffect;

public class BuffMeCommand extends Command {
    {
        setDescription("Apply all major buffs permanently.");
    }

    private static final int[] BUFF_SKILLS = {
        // General / Beginner
        1005,       // Echo of Hero
        // Warrior
        1001003,    // Iron Body
        1101006,    // Rage
        1101007,    // Sword Booster
        1111002,    // Combo Attack
        1121000,    // Maple Warrior (Hero)
        1201003,    // Power Guard
        1201006,    // Shield Mastery
        1211006,    // Ice Charge
        1211008,    // Fire Charge
        1211009,    // Thunder Charge
        1221000,    // Maple Warrior (Paladin)
        1301007,    // Hyper Body
        1321000,    // Maple Warrior (Dark Knight)
        // Magician
        2000000,    // Improving MP Recovery
        2001002,    // MP Eater
        2001003,    // Magic Guard
        2101001,    // Spell Mastery
        2101003,    // Magic Booster (F/P)
        2111002,    // Meditation (F/P)
        2201003,    // Magic Booster (I/L)
        2211002,    // Meditation (I/L)
        2301004,    // Bless
        2311003,    // Holy Symbol
        2321000,    // Maple Warrior (Bishop)
        2321005,    // Holy Shield
        // Bowman
        3001004,    // Focus
        3101005,    // Soul Arrow
        3121000,    // Maple Warrior (BM)
        3121002,    // Sharp Eyes (BM)
        3201005,    // Soul Arrow
        3221000,    // Maple Warrior (MM)
        3221002,    // Sharp Eyes (MM)
        // Thief
        4101003,    // Steal
        4101004,    // Haste
        4201003,    // Haste
        4211005,    // Meso Up
        4221000,    // Maple Warrior (NL)
        // Pirate
        5001005,    // Dash
        5101006,    // Transformation
        5121000,    // Maple Warrior (Corsair)
        5121009,    // Speed Infusion
        5220001,    // Knuckle Booster
        5221000,    // Maple Warrior (Buccaneer)
        // GM skills
        9001000,    // Haste (GM)
        9001001,    // Holy Symbol (GM)
        9001002,    // Bless (GM)
        9001008,    // GM Buff
    };

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (int skillId : BUFF_SKILLS) {
            try {
                Skill skill = SkillFactory.getSkill(skillId);
                if (skill == null) continue;
                int maxLevel = skill.getMaxLevel();
                if (maxLevel <= 0) continue;
                StatEffect effect = skill.getEffect(maxLevel);
                if (effect == null) continue;
                effect.applyTo(player);
            } catch (Exception ignored) {}
        }
        player.healHpMp();
        player.dropMessage("All major buffs applied permanently.");
    }
}
