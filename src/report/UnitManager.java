package report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import world.Unit;

public class UnitManager {

    Map<String, Unit> unitMap = new HashMap<String, Unit>();
    Map<String, Unit> mobMap = new HashMap<String, Unit>();
    Map<String, Unit> playerMap = new HashMap<String, Unit>();

    public UnitManager() {
        unitMap.put(Unit.nil.getGuid(), Unit.nil);
    }

    public Unit parseUnit(String guid, String name, long flags) {

        if(unitMap.containsKey(guid)) {
            return unitMap.get(guid);
        }

        return addUnit(guid, name, flags);

    }

    private Unit addUnit(String guid, String name, long flags) {

        Unit unit = new Unit(guid, name, flags);
        unitMap.put(guid, unit);

        if(unit.isMob()) {
            mobMap.put(guid, unit);
        }

        if(unit.isPlayer()) {
            playerMap.put(guid, unit);
        }

        return unit;
    }

    public List<Unit> getByName(String name) {

        List<Unit> units = new ArrayList<Unit>();
        for(Unit unit: unitMap.values()) {
            if(unit.getName().equals(name)) {
                units.add(unit);
            }
        }
        return units;
    }

    public Unit getUniqueByName(String name) {
        for(Unit unit: unitMap.values()) {
            if(unit.getName().equals(name)) {
                return unit;
            }
        }

        return null;
    }

    public Collection<Unit> getMobs() {
        return mobMap.values();

    }

}
