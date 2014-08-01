package com.ligr.strategygame.buildings;

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

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.constants.ConstantBuildings;


import other.DataBase;

public class Farm extends AnimatedSpriteObject {

	private boolean pressedDown = false;
	public Farm(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main,boolean load) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager,main);
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSFARM,0);
		yBaseStart = 60;}
	


	private boolean GotFood = false;
	private boolean CanProduceWheat = true;

	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(main.boolplacebuilding == false && pressedDown && main.removeBuildings==false){ pressedDown = false;
		main.addBuildingDescription("Farm", "A farm provides the people with food");
	
		}
		else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
			main.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", main.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		main.mScene.unregisterTouchArea(this);
		main.getFarms().remove(this);
		main.removeEntity(this);
		main.getController().updateWorkers(-ConstantBuildings.WORKERSFARM, 0);
	}
	public String name(){
		return "Farm";
	}
	public void save(){
		main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id);
	}
	public void ProduceWheat(Entity thisfarm){
		for(int i = 0;i<main.getSilos().size();i++){
			double distance = main.getController().calculateDistance(main.getSilos().get(i), thisfarm);
			if(distance<512 && main.getSilos().get(i).MaxFoodAmmount > main.getSilos().get(i).FoodAmmount){
				main.getSilos().get(i).FoodAmmount+=2500;
				Debug.e("produced wheat");
				if(main.getSilos().get(i).MaxFoodAmmount < main.getSilos().get(i).FoodAmmount){
					main.getSilos().get(i).FoodAmmount = main.getSilos().get(i).MaxFoodAmmount;
				}
	}
		}}	public float getZ(){
			return this.z + yBaseStart;
		}}
