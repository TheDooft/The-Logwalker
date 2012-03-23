package world;

import java.util.HashMap;
import java.util.Map;

public class Item {
	int id;
	String name;
	static private Map<Integer, Item> instanceList;

	public Item(int id, String name) {
		this.id = id;
		this.name = new String(name);
	}

	static public Item getInstance(int id, String name) {
		if (instanceList == null)
			instanceList = new HashMap<Integer, Item>();
		if (instanceList.containsKey(id) == true)
			return instanceList.get(id);
		Item item = new Item(id, name);
		instanceList.put(id, item);
		return item;
	}

	public String getName() {
		return name;
	}
}
