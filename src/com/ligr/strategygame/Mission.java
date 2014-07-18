package com.ligr.strategygame;

import java.util.Random;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import other.Controller;

import shapes.RectangleModified;

/**
 * Class responsible for checking if we have completed the missions for the give campaign
 * @author LgLinuss
 *
 */
public class Mission {

	private MainActivity main;
	private MissionInfo missionInfo;
	private int currentMissionLevel = 0;

	public Mission(Controller controller) {
		this.main = controller.getActivity();
		this.missionInfo = new MissionInfo();
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
		int missionsCompleted = 0;
		if (map.equals("Map1")) {
			for (int i = 1; i < missionInfo.getAmmountOfMissions(1,
					currentMissionLevel)+1; i++) {
				if (missionInfo.isMissionsCompleted(1, currentMissionLevel, i)){
					missionsCompleted++;
					continue;
					}
				if (missionInfo.isMissionInhabitants(1, currentMissionLevel, i, 0)) {
					if(checkInhabitantsCompleted(1,currentMissionLevel,i,0)) // Break the loop if we completed the mission
						{
						break;}
					else
					continue;
				}
				if (missionInfo.isMissionFood(1, currentMissionLevel, i)) {
					if(checkFoodCompleted(1,currentMissionLevel,i,0)) // Break the loop if we completed the mission
						break;
					else
					continue;
					}
				
				if(missionInfo.isMissionMarbleProduction(1, currentMissionLevel, i)){
					if(checkMarbleProductionCompleted(1,currentMissionLevel,i)){
						break;}
					else
						continue;
				}
			}
			// If all of the current missions are completed, move forward
			if(missionsCompleted==missionInfo.getAmmountOfMissions(1, currentMissionLevel)){
				gotoNextLevel();
			}
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
			if (getNumberOfInhabitantsByLevel(0, 0) >= 150
					&& main.getAccsessToFood() >= .6) {
				main.makeToast("First mission completed!");
			}
			break;
		case 2:

			if ((getNumberOfInhabitantsByLevel(0, 0) >= 200)
					&& main.getMarbleProductionYearly() >= 36) {
				main.makeToast("2d mission completed!");
				// CurrentMission =3;

			}
		}
	}

	/**
	 * Add a rectangle
	 */
	public void addRectangle(float x, float y, float width, float height,
			VertexBufferObjectManager vbom, String name, Color color,
			int lineWidth) {

		RectangleModified rect = new RectangleModified(x, y, width, height,
				vbom, name, color, lineWidth,main);
		MainActivity.mScene.attachChild(rect);
		main.rectanglesModified.add(rect);
	}

	/**
	 * Returns the amount of inhabitants in all levels from min-max (0,0) = All
	 * inhabitants
	 * 
	 * @param levelmin
	 *            minimum level to check for
	 * @param levelmax
	 *            max level to check for
	 * @return amount of inhabitants
	 */
	public int getNumberOfInhabitantsByLevel(int min, int max) {
		int res = 0;
		for (int i = min; i <= max; i++) {
			res += main.getController().Inhabitants[i];
		}
		return res;
	}

	/**
	 * Remove the rectangle(s) with the given name
	 */
	public void removeRectangles(String name) {
		for (int i = main.rectanglesModified.size() - 1; i >= 0; i--) {
			if (main.rectanglesModified.get(i).checkName(name)) {
				main.rectanglesModified.get(i).remove();
				Debug.e("Remove rectangle");
				main.rectanglesModified.remove(main.rectanglesModified.get(i));
			}
		}
	}

	/**
	 * Returns a string that is a description of all of the current missions
	 * 
	 * @return string mission descriptions
	 */
	public String createDescriptionOfMissions() {
		String res = "";
		for (int i = 1; i < missionInfo.getAmmountOfMissions(1,
				currentMissionLevel) + 1; i++) {
			res += missionInfo.getDescriptionOfMission(1, currentMissionLevel,
					i) + "\n";
		}
		return res;
	}
	

	
	private void gotoNextLevel() {
		main.makeToast("You completed all of your current missions!\nCheck your new objectives in the objectives menu");
		currentMissionLevel++;
	}

	private boolean checkFoodCompleted(int map, int currentMissionLevel, int i,
			int j) {
		if (missionInfo.isMissionFood(1, currentMissionLevel, i)) {
			
			boolean completedAllOtherMissions = true;
			for (int k = 1; k < missionInfo.getAmmountOfMissions(1,
					currentMissionLevel); k++) {
				if (!missionInfo.isMissionsCompleted(1,
						currentMissionLevel, k)) {
					completedAllOtherMissions = false;
					break;
				}
			}
			if (completedAllOtherMissions) {
				if (main.getAccsessToFood() >= missionInfo
						.getValueOfMission(1, currentMissionLevel, i)) {
					main.makeToast("Completed: "
							+ missionInfo.getDescriptionOfMission(1,
									currentMissionLevel, i),1);String res = "";
					missionInfo.setMissionCompleted(1,
							currentMissionLevel, i);
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkInhabitantsCompleted(int map, int currentMissionLevel,
			int i, int j) {
		if(missionInfo.getMissions()[currentMissionLevel][i].contains("true")){ return false;}
		if (getNumberOfInhabitantsByLevel(0, 0) >= missionInfo.getValueOfMission(1, currentMissionLevel, i)) {
			main.makeToast("Completed: "+ missionInfo.getDescriptionOfMission(1,currentMissionLevel, i),1);
			missionInfo.setMissionCompleted(1, currentMissionLevel, i);
			return true;
		}
		return false;
	}
	private boolean checkMarbleProductionCompleted(int map,
			int currentMissionLevel, int i) {
		if (getMarbleProductionYearly() >= missionInfo.getValueOfMission(1, currentMissionLevel, i)) {
			main.makeToast("Completed: "+ missionInfo.getDescriptionOfMission(1,currentMissionLevel, i),1);
			missionInfo.setMissionCompleted(1, currentMissionLevel, i);
			return true;
		}return false;
	}

	private double getMarbleProductionYearly() {
		return main.getStoneCutters().size()*12;
	}
}
