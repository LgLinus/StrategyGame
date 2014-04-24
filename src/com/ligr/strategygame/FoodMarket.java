package com.ligr.strategygame;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import constants.ConstantBuildings;

public class FoodMarket extends SpriteObject {

	public int FoodAmmount = 0;
	private boolean pressedDown = false;
	public int MaxFoodAmmount = 1000;
	public FoodMarket(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSFOODMARKET,0);
		}

	public FoodMarket(float pX, float pY, ITextureRegion siloImage,
			VertexBufferObjectManager pVertexBufferObjectManager, int number) {
		super(pX, pY, siloImage, pVertexBufferObjectManager);
		FoodAmmount = number;
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Food Market";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "A food market distributes your city's food to the people.";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.FoodMarkets.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSFOODMARKET, 0);
	}
	public void update(){
		checkForStocks();
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id,this.FoodAmmount);
	}
	private String name() {
		return "Food Market";
	}
	public void checkForStocks(){
		for(int i = 0;i<MainActivity.Silos.size();i++){
			double distance = MainActivity.calculateDistance(MainActivity.Silos.get(i), this );
			if(distance<512 && MainActivity.Silos.get(i).FoodAmmount > 0){
				for(this.FoodAmmount = this.FoodAmmount;this.FoodAmmount<this.MaxFoodAmmount;this.FoodAmmount++){
				//MainActivity.Silos.get(i).FoodAmmount-=1;
				
				//this.FoodAmmount +=1;
				if(MainActivity.Silos.get(i).FoodAmmount <=0 )
				{
					break;
				}
				
	}
	
}}}}
