package com.ligr.strategygame;

import org.andengine.util.debug.Debug;

public class Mission {

	private MainActivity main;

	public Mission(MainActivity mainActivity) {
		this.main = mainActivity;
	}

	/**
	 * Update and check if the missions have been completed
	 * 
	 * @param map
	 *            that is played
	 * @param number
	 *            mission number
	 */
	public void checkMission(String map, int number) {
		if (map.equals("Map1")) {
			checkMissionMap1(number);
		}
	}

	/**
	 * Check the mission criteria for map1
	 * 
	 * @param number
	 *            missionnumber
	 */
	public void checkMissionMap1(int number) {
		switch (number) {
		case 1:
			Debug.e(String.valueOf(getNumberOfInhabitantsByLevel(1,1)));
			if (getNumberOfInhabitantsByLevel(0,0) >= 150 && main.getAccsessToFood() >= .6) {
				Debug.e("NEXT MISSION");
				main.CurrentMission = 2;
			}
			break;
		case 2:

			if ((main.Inhabitants[1] + main.Inhabitants[2]
					+ main.Inhabitants[3] + main.Inhabitants[4] + main.Inhabitants[5]) >= 200
					&& main.getMarbleProductionYearly() >= 36) {
				Debug.e("NEXT MISSION TO 3");
				// CurrentMission =3;

			}
		}
	}
	
	/**
	 * Returns the amount of inhabitants in all levels from min-max
	 * @param levelmin minimum level to check for
	 * @param levelmax max level to check for
	 * @return amount of inhabitants
	 */
	public int getNumberOfInhabitantsByLevel(int min,int max){
		int res = 0;
		for(int i = min;i <= max;i++){
			res += main.Inhabitants[i];
		}
		return res;
	}
}
