package tools;

import java.util.Hashtable;

import world.Spell;
import world.Unit;

public class SpecialCaseManager {
	private static SpecialCaseManager instance;
	private final static Hashtable<Integer, Integer> specialIdList = new Hashtable<Integer, Integer>() {
		private static final long serialVersionUID = -1838811774644304294L;
		{
			put(23133, 8836); // Gnomish Battle Chicken
			put(71521, 46157); // Hnad of Gul'dan
		}
	};
	private Hashtable<Integer, Unit> watchList;

	private SpecialCaseManager() {
		watchList = new Hashtable<Integer, Unit>();
	}

	public static SpecialCaseManager getInstance() {
		if (instance == null)
			instance = new SpecialCaseManager();
		return instance;
	}

	public Unit getSpecialSummon(int id) {
		Unit unit = Unit.nil;
		if (watchList.containsKey(id)) {
			unit = watchList.get(id);
			watchList.remove(id);
		}
		return unit;
	}

	public void addSpecialSummon(Unit caster, Spell spell) {
		watchList.put(specialIdList.get(spell.getId()), caster);
	}

	public static boolean isSpecialSpell(int id) {
		return specialIdList.containsKey(id);
	}
}
