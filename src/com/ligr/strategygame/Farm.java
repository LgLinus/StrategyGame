package com.ligr.strategygame;

/**
 * The farm harvest wheat
 */

// Each farm can give food for 56 inhabitants during a year.
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import other.DataBase;

public class Farm extends AnimatedSpriteObject {

	private boolean pressedDown = false;
	public Farm(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSFARM,0);
		}
	


	private boolean GotFood = false;
	private boolean CanProduceWheat = true;

	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Farm";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "A farm provides the people with food";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.Farms.remove(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSFARM, 0);
	}
	public String name(){
		return "Farm";
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	public static void ProduceWheat(Entity thisfarm){
		for(int i = 0;i<MainActivity.Silos.size();i++){
			double distance = MainActivity.calculateDistance(MainActivity.Silos.get(i), thisfarm);
			if(distance<512 && MainActivity.Silos.get(i).MaxFoodAmmount > MainActivity.Silos.get(i).FoodAmmount){
				MainActivity.Silos.get(i).FoodAmmount+=2500;
				Debug.e("produced wheat");
				if(MainActivity.Silos.get(i).MaxFoodAmmount < MainActivity.Silos.get(i).FoodAmmount){
					MainActivity.Silos.get(i).FoodAmmount = MainActivity.Silos.get(i).MaxFoodAmmount;
				}
	}
		}}}
