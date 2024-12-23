package homework.spring.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import homework.spring.exceptions.LogicException;
import homework.spring.exceptions.ValidationException;

public class CompassService {
	private static final CompassService instance = new CompassService();
	
	private final Map<String, String> sidesCoordinates;
	private boolean isInitiated = false;
	
	public static CompassService getService() {
		return instance;
	}

	public CompassService() {
		sidesCoordinates = new HashMap<String, String>();
		
		sidesCoordinates.put("North", "0-0");
		sidesCoordinates.put("Northeast", "0-0");
		sidesCoordinates.put("East", "0-0");
		sidesCoordinates.put("Southeast", "0-0");
		sidesCoordinates.put("South", "0-0");
		sidesCoordinates.put("Southwest", "0-0");
		sidesCoordinates.put("West", "0-0");
		sidesCoordinates.put("Northwest", "0-0");
	}
	
	public String findSide(int degree) {
		if (degree >= 360 || degree < 0) {
			throw new ValidationException("Degree must be greater than  or equal 0 and less than 360, actual = " + degree);
		}
		if (!isInitiated) {
			throw new ValidationException("Sides are not initiated");
		}
		for(Entry<String,String> entry : sidesCoordinates.entrySet()) {
			String[] cur = entry.getValue().split("-");
			int curStart= Integer.parseInt(cur[0]);
			int curEnd = Integer.parseInt(cur[1]);
			if (curEnd < curStart) {
				if ((degree >= curStart && degree < 360) || 
						(degree >= 0 && degree <= curEnd)) {
					return entry.getKey();
				}
			}
			else if(degree >= curStart && degree <= curEnd) {
				return entry.getKey();
			}
		}
		throw new LogicException("Something goes wrong (((");
	}
	
	public boolean initiate(Map<String, String> input) {
		ArrayList<String> coordinates = new ArrayList<String>();
		for(Entry<String,String> entry : input.entrySet()) {
			coordinates.add(entry.getValue());
		}		
		if (validateSides(input.keySet()) && validateCoordinates(coordinates)) {			
			for (Entry<String,String> entry : input.entrySet()) {
				sidesCoordinates.put(entry.getKey(), entry.getValue());
			}
			isInitiated = true;
			return isInitiated;
		}
		else {
			throw new ValidationException("Initiation exception");
		}
	}
	
	private boolean validateSides(Set<String> sides) {
		if (sides == null || sides.size() != sidesCoordinates.size()) {
			throw new ValidationException("Sides length must be " + sidesCoordinates.size());
		}
		for(String side : sides) {
			if (!sidesCoordinates.containsKey(side)) {
				throw new ValidationException("There is no such side " + side);
			}
		}
		return true;
	}
	
	private boolean validateCoordinates(ArrayList<String> coordinates) {
		Comparator<String> comparator = (String s1, String s2) -> {
			String[] s1Start = s1.split("-");
			String[] s2Start = s2.split("-");
			return Integer.compare(Integer.parseInt(s1Start[0]), Integer.parseInt(s2Start[0])); 
		};
		coordinates.sort(comparator);
		int total = 0;
		int last = -1;
		for (int i = 0; i < coordinates.size(); i++) {
			String[] cur = coordinates.get(i).split("-");
			int curStart= Integer.parseInt(cur[0]);
			int curEnd = Integer.parseInt(cur[1]);
			if (curStart >= 360 || curEnd >= 360 || curStart < 0 || curEnd < 0) {
				throw new ValidationException("Start and end of the interval must be greater than  or equal 0 and less than 360,"
						+ " actual start = " + curStart + ", end = " + curEnd);
			}
			if (i != 0 && last != curStart - 1) {
				throw new ValidationException("End of the previous interval is " + last + 
						" but start of the current one is " + curStart);
			}
			last = curEnd;
			if (curEnd <= curStart) {
				total += 360 - curStart + curEnd + 1;
			}
			else {
				total += curEnd - curStart + 1;
			}
		}
		if (total != 360) {
			throw new ValidationException("Coverage of intervals must be 360 ​​degrees, actual = " + total);
		}
		return true;
	}
}
