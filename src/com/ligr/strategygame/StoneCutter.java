package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.maptiles.MarbleTile;

import constants.ConstantBuildings;

public class StoneCutter extends AnimatedSpriteObject {

	private boolean pressedDown = false;
	public int marbleMonth = 4; // When 4 get new marble
	public int MaxWorkers = 8;
	public int Workers = 8;
	public boolean marbleInRange = false;
	private AnimatedSprite npcStoneCutter;
	private MarbleTile target;
	public StoneCutter(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSSTONECUTTER,0);
		
	}
	public void createNpc(){
		npcStoneCutter = new AnimatedSprite(this.mX + this.getWidth()/2 - 8, this.mY + this.getHeight()/2 + 8, MainActivity.npcStoneCutter, this.getVertexBufferObjectManager());
		MainActivity.mScene.attachChild(npcStoneCutter);
		npcStoneCutter.animate((long) (26*MainActivity.GameSpeed));
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Stone Cutter";
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Stone Cutter";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "The stone cutter provides the city with Marble, which can be used for Tempels and Houses.";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.StoneCutters.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSSTONECUTTER, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public boolean getNewMarble(){
		double temprange= ConstantBuildings.RANGE+1;
		if(target==null){
		for(int i = 0; i < MainActivity.marbleTiles.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.marbleTiles.get(i)) < ConstantBuildings.RANGE){
				marbleInRange = true;
				if(!MainActivity.marbleTiles.get(i).used){
					if(temprange > MainActivity.calculateDistance(this, MainActivity.marbleTiles.get(i))){
						target = MainActivity.marbleTiles.get(i);
						temprange = MainActivity.calculateDistance(this, MainActivity.marbleTiles.get(i));}
					
				}
				
			}
		}
		if(target!=null)
			target.getResource();
		return true;
		}
		else if(target.used==true){
			for(int i = 0; i < MainActivity.marbleTiles.size();i++){
				if(MainActivity.calculateDistance(this, MainActivity.marbleTiles.get(i)) < ConstantBuildings.RANGE){
					marbleInRange = true;
					if(!MainActivity.marbleTiles.get(i).used){
						if(temprange > MainActivity.calculateDistance(this, MainActivity.marbleTiles.get(i))){
							target = MainActivity.marbleTiles.get(i);
							temprange = MainActivity.calculateDistance(this, MainActivity.marbleTiles.get(i));}
						
					}
					
				}
			}
			target.getResource();
			return true;
			}
		else
			target.getResource();
		return false;
	}
	public void checkForStocks(){
		if(this.marbleMonth<4 && marbleInRange){				
		for(int i = 0;i<MainActivity.Stocks.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<ConstantBuildings.RANGE){				
				if(MainActivity.Stocks.get(i).checkSpace("Marble") ==true){

				MainActivity.Marble +=1;
				this.marbleMonth++;
					break;
				}
			}
		}
		
		}
		else{
			getNewMarble();
			this.marbleMonth=0;
		}
	}
}
