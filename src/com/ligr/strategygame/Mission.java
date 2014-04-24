package com.ligr.strategygame;

import java.util.Random;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import shapes.RectangleModified;

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
			if (getNumberOfInhabitantsByLevel(0,0) >= 150 && main.getAccsessToFood() >= .6) {
				MainActivity.MakeToast("First mission completed!");
				
			}
			break;
		case 2:

			if ((getNumberOfInhabitantsByLevel(0,0) >= 200)
					&& main.getMarbleProductionYearly() >= 36) {
				MainActivity.MakeToast("2d mission completed!");
				// CurrentMission =3;

			}
		}
	}
	
	/**
	 * Add a rectangle
	 */
	public void addRectangle(float x, float y, float width, float height, VertexBufferObjectManager vbom, String name, Color color, int lineWidth) {

		RectangleModified rect = new RectangleModified(x, y, width, height, vbom, name,color, lineWidth);
		MainActivity.mScene.attachChild(rect);
		main.rectanglesModified.add(rect);
	}

	/**
	 * Returns the amount of inhabitants in all levels from min-max (0,0) = All inhabitants
	 * @param levelmin minimum level to check for
	 * @param levelmax max level to check for
	 * @return amount of inhabitants
	 */
	public int getNumberOfInhabitantsByLevel(int min,int max){
		int res = 0;
		for(int i = min;i <= max;i++){
			res += MainActivity.Inhabitants[i];
		}
		return res;
	}
	
	/**
	 * Remove the rectangle(s) with the given name*/
	public void removeRectangles(String name){
		for(int i = main.rectanglesModified.size()-1; i >= 0;i--){
			if(main.rectanglesModified.get(i).checkName(name)){
				main.rectanglesModified.get(i).remove();
				Debug.e("Remove rectangle");
				main.rectanglesModified.remove(main.rectanglesModified.get(i));
			}
		}
	}
}
