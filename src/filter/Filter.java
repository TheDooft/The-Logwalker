package filter;

import java.util.ArrayList;
import java.util.List;

import event.LogEvent;

public class Filter {
	
	private List<Class<? extends LogEvent>> eventClassList;
	
	public Filter() {
		eventClassList = new ArrayList<Class<? extends LogEvent>>();
	}

	public void addEventType(Class<? extends LogEvent> eventClass) {
		eventClassList.add(eventClass);
		
	}

	public void remEventType(Class<? extends LogEvent> eventClass) {
		eventClassList.remove(eventClass);
	}
	
	public boolean isMatching(LogEvent log){
		return (isEventMatching(log));
	}

	private boolean isEventMatching(LogEvent log) {
		if (eventClassList.isEmpty())
			return true;
		for (Class<? extends LogEvent> eventClass : eventClassList){
			if (log.getClass().isAssignableFrom(eventClass))
				return true;
		}
		return false;
	}
}
