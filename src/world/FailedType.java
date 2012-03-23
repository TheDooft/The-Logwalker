package world;

import java.util.ArrayList;
import java.util.List;

public class FailedType {
	private static final List<String> failedTypeList = new ArrayList<String>() {
		private static final long serialVersionUID = 9177607091012221113L;
		{
			add("A more powerful spell is already active");
			add("Another action is in progress");
			add("Can only use outside");
			add("Can only use while swimming");
			add("Can't do that while asleep");
			add("Can't do that while charmed");
			add("Can't do that while confused");
			add("Can't do that while fleeing");
			add("Can't do that while horrified");
			add("Can't do that while incapacitated");
			add("Can't do that while moving");
			add("Can't do that while silenced");
			add("Can't do that while stunned");
			add("Interrupted");
			add("Invalid target");
			add("Item is not ready yet");
			add("Must be in Bear Form); Dire Bear Form");
			add("Must have a Ranged Weapon equipped");
			add("No path available");
			add("No target");
			add("Not enough energy");
			add("Not enough mana");
			add("Not enough rage");
			add("Not yet recovered");
			add("Nothing to dispel");
			add("Out of range");
			add("Target is friendly");
			add("Target is hostile");
			add("Target needs to be in front of you.");
			add("Target not in line of sight");
			add("Target too close");
			add("You are dead");
			add("You are in combat");
			add("You are in shapeshift form");
			add("You are unable to move");
			add("You can't do that yet");
			add("You must be behind your target.");
			add("Your target is dead");
		}
	};
	
	private static final String unknown = "Unknown reason";
	
	static public String getFailedType(String string){
		for (String type : failedTypeList)
			if (type.equals(string))
				return type;
		return unknown;
	}
}
