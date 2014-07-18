package com.ligr.strategygame;

import org.andengine.util.debug.Debug;

/**
 * Class that contains the information for each mission
 * 
 * @author LgLinuss
 * 
 */
public class MissionInfo {
	// [n][i] n = current misions i = type
	// ex. i="inhab,150,false", means if the ammount of inhabitants is more than
	// 150 mark as completed.
	private String[][] map1Missions = new String[3][3];
	private String[][] map2Missions;

	public MissionInfo() {

		initMap1Missions();

	}

	// Initialize the map1Missions
	private void initMap1Missions() {
		map1Missions[0][0] = "2"; // 4 Elements in the first mission
		map1Missions[0][1] = "Inhab,150,false"; // IF there are at least 150
												// inhabitants (Inhab2 checks
												// for level 2 inhabitants)
		map1Missions[0][2] = "Food,0.6,false"; // If 60% of the population have
												// food
		map1Missions[1][0] = "2";
		map1Missions[1][1] = "Inhab,200,false";
		map1Missions[1][2] = "MarbleProduction,36,false"; // If the marble
															// production
															// exceeds 36 each
															// year
		map1Missions[2][0] = "0"; 
		}

	/**
	 * Checks if the given mission for the map is of the type inhabitants. If it
	 * is return true
	 * 
	 * @param map
	 * @param currentMission
	 * @param mission
	 * @param inhabLevel
	 *            level of the inhabitants
	 * @return if inhabitant mission
	 */
	public boolean isMissionInhabitants(int map, int currentMission,
			int mission, int inhabLevel) {
		boolean inhabitants = true;
		String subString;
		switch (map) {
		case 1:
			subString = map1Missions[currentMission][mission].substring(0, 5);
			subString.replace(",", "");
			if (!subString.equals("Inhab"))
				inhabitants = false;
			break;
		}

		return inhabitants;
	}

	/**
	 * Checks if the given mission for the map is of the type food. If it is
	 * return true
	 * 
	 * @param map
	 * @param currentMission
	 * @param mission
	 * @return if food mission
	 */
	public boolean isMissionFood(int map, int currentMission, int mission) {
		boolean food = true;
		switch (map) {
		case 1:
			String[] strings = map1Missions[currentMission][mission].split(",");
			if (!strings[0].equals("Food"))
				food = false;
			break;
		}

		return food;
	}

	/**
	 * Checks if the given mission for the map is of the type marble production. If it is
	 * return true
	 * 
	 * @param map
	 * @param currentMission
	 * @param mission
	 *            level of the inhabitants
	 * @return if marble production mission
	 */
	public boolean isMissionMarbleProduction(int map, int currentMission, int mission){
		boolean marbleProd = true;
		switch(map){
		case 1:
			String[] strings = map1Missions[currentMission][mission].split(",");
			if(!strings[0].equals("MarbleProduction"))
				marbleProd = false;
			break;
		}
		return marbleProd;
	}
	/**
	 * Returns if the given mission is completed
	 * 
	 * @param map
	 * @param currentMission
	 * @return completed
	 */
	public boolean isMissionsCompleted(int map, int currentMission, int mission) {
		boolean completed = true;
		switch (map) {
		case 1:
			String[] strings = map1Missions[currentMission][mission].split(",");
			Debug.e(strings[strings.length-1]);
			if (strings[strings.length - 1].equals("false"))
				completed = false;

		}
		return completed;
	}

	/**
	 * Return the value of the mission
	 * 
	 * @param map
	 * @param currentMission
	 * @param mission
	 * @return
	 */
	public double getValueOfMission(int map, int currentMission, int mission) {
		double value = 0.0;
		switch (map) {
		case 1:
			String[] strings = map1Missions[currentMission][mission].split(",");
			if (strings[2].equals("false"))
				value = (Double.parseDouble(strings[1]));
			break;
		}
		return value;
	}

	/**
	 * Makes a mission marked completed
	 * 
	 * @param map
	 * @param currentMission
	 * @param mission
	 */
	public void setMissionCompleted(int map, int currentMission, int mission) {
		switch (map) {
		case 1:
			String[] strings = map1Missions[currentMission][mission].split(",");
			strings[strings.length - 1] = "true";
			String res = "";
			for (int i = 0; i < strings.length; i++) {
				if (i != strings.length - 1)
					res += strings[i] + ",";
				else
					res += strings[i];
			}
			map1Missions[currentMission][mission] = res;
			break;
		}
	}

	/**
	 * Return the ammount of missions for the current level
	 * 
	 * @param map
	 * @param currentMission
	 * @return
	 */
	public int getAmmountOfMissions(int map, int currentMission) {
		switch (map) {
		case 1:
			return Integer.parseInt(map1Missions[currentMission][0]);
		}
		return 0;
	}
	
	/**
	 * Returns a string in form of a description of the mission goal
	 * @param map
	 * @param currentMissionLevel
	 * @param mission
	 * @return
	 */
	public String getDescriptionOfMission(int map, int currentMissionLevel, int mission){
		String res = "";
		switch(map){
		case 1:
			String strings[] = map1Missions[currentMissionLevel][mission].split(",");
			// Create description for inhabitants mission
			if(strings[0].contains("Inhab")){
				res="Have a total inhabitants of: " + strings[1] + " or more";
				if(strings[0].length()>5)
					res="\nAll of them have to live in houses above level: " + strings[0].charAt(5);
			}
			else if (strings[0].equals("Food")){
				double value = Double.parseDouble(strings[1])*100;
				res = value +"% of your inhabitants must have access to food";
			}
			else if(strings[0].equals("MarbleProduction")){
				double value = Double.parseDouble(strings[1]);
				res = "You need a yearly marble production of: " + value;
			}
			if(strings[2].equals("true"))
				res+= " *completed";
		}
		return res;
	}

	public String[][] getMissions() {
		return map1Missions;
	}
}
