package world;

import java.util.HashMap;
import java.util.Map;

public class Spell {

	int id;

	String name;

	int school;

	static private Map<Integer, Spell> instanceList;

	public Spell(int id, String name, int school) {
		this.id = id;
		this.name = new String(name);
		this.school = school;

	}

	static public Spell getInstance(int id, String name, int school) {
		if (instanceList == null)
			instanceList = new HashMap<Integer, Spell>();
		if (instanceList.containsKey(id) == true)
			return instanceList.get(id);
		Spell spell = new Spell(id, name, school);
		instanceList.put(id, spell);
		return spell;
	}

	public String getName() {
		return name;
	}

	static public String getSchoolName(int school) {
		switch (school) {
		case 1:
			return "physical";
		case 2:
			return "holy";
		case 4:
			return "fire";
		case 8:
			return "nature";
		case 16:
			return "frost";
		case 32:
			return "shadow";
		case 64:
			return "arcane";
		case 3:
			return "holystrike";
		case 5:
			return "flamestrike";
		case 6:
			return "holyfire";
		case 9:
			return "stormstrike";
		case 10:
			return "holystorm";
		case 12:
			return "firestorm";
		case 17:
			return "froststrike";
		case 18:
			return "holyfrost";
		case 20:
			return "frostfire";
		case 24:
			return "froststorm";
		case 33:
			return "shadowstrike";
		case 34:
			return "twilight";
		case 36:
			return "shadowflame";
		case 40:
			return "plague";
		case 48:
			return "shadowfrost";
		case 65:
			return "spellstrike";
		case 66:
			return "divine";
		case 68:
			return "spellfire";
		case 72:
			return "spellstorm";
		case 80:
			return "spellfrost";
		case 96:
			return "spellshadow";
		case 28:
			return "elemental";
		case 124:
			return "chromatic";
		case 126:
			return "magic";
		case 127:
			return "chaos";
		default:
			return "unknown";
		}
	}
}
