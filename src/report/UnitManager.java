package report;

import java.util.HashMap;
import java.util.Map;

import world.Unit;

public class UnitManager {

    Map<String, Unit> unitMap = new HashMap<String, Unit>();

    public Unit parseUnit(String guid, String name, long flags) {

        if(unitMap.containsKey(guid)) {
            return unitMap.get(guid);
        }

        return addUnit(guid, name, flags);

    }

    private Unit addUnit(String guid, String name, long flags) {

        Unit unit = new Unit(guid, name, flags);
        unitMap.put(guid, unit);

        return unit;
    }

}
