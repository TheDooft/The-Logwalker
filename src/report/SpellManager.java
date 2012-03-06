package report;

import java.util.HashMap;
import java.util.Map;

import world.Spell;

public class SpellManager {
    Map<String, Spell> spellMap = new HashMap<String, Spell>();

    public Spell parseSpell(String id, String name, int school) {

        if(spellMap.containsKey(id)) {
            return spellMap.get(id);
        }

        return addSpell(id, name, school);

    }

    private Spell addSpell(String id, String name, int flags) {

        Spell spell = new Spell(id, name, flags);
        spellMap.put(id, spell);

        return spell;
    }
}
