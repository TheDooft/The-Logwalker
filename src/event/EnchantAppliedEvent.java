package event;

import world.Item;
import world.Spell;
import world.Unit;

public class EnchantAppliedEvent extends LogEvent {

	private final Spell spell;
	private final Item item;

	public EnchantAppliedEvent(int time, Unit source, Unit target,
			String spellName, Integer itemId, String itemName) {
		super(time, source, target);
		this.spell = new Spell(0, spellName, 0);
		this.item = Item.getInstance(itemId, itemName);
	}

	@Override
	public String getText() {
		if (target == Unit.nil) {
			return source.getName() + " applies " + spell.getName() + " on "
					+ item.getName();
		}
		return source.getName() + " applies " + spell.getName() + " on "
				+ target.getName() + " " + item.getName();
	}
}
