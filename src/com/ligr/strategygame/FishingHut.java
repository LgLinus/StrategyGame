package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

public class FishingHut extends SpriteObject {

boolean pressedDown = false;
private boolean gotBoat = false;
private int costofBoatWood = 2;
private Boat child;
private boolean timerStarted = false;
private int timer;
public int ammount=0,maxammount=2000;

	public FishingHut(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSFISHINGHUT,0);
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Fishing hut";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "The fishing hut gathers fish, which will give your inhabitants food";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.FishingHuts.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSFISHINGHUT, 0);
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Fishing Hut";
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		if(!timerStarted && gotBoat == false && MainActivity.Wood >= costofBoatWood){
			timerStarted = true;
			timer = 120;
			MainActivity.buybutton.RemoveResources("Wood", costofBoatWood);
			}
		else if(timerStarted)
		{
			timer--;
			if(gotBoat == false && timer<=0){
			timerStarted = false;
			createBoat();
			timer = 120;
			}
		}
	}
	public void checkForStocks(FishingHut brickFoundry){
		for(int i = 0;i<MainActivity.Stocks.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<512){

				if(MainActivity.Stocks.get(i).checkSpace("Fish") ==true){
					MainActivity.Fish +=1;
					break;
				}
			}
		}
	}
	
	// Creates a boat if the fishing hut doesn't have one
	public void createBoat(){
		if(!gotBoat ){
			if(MainActivity.Wood>=costofBoatWood){
				child = new Boat(this.mX+104, this.mY+60, MainActivity.boatImage, this.getVertexBufferObjectManager(), this);
				MainActivity.mScene.attachChild(child);
				gotBoat = true;
			}
		}
	}
	// Tells the fishing hut if we have the boat or not
	public void setGotBoat(boolean setGotBoat){
		gotBoat = setGotBoat;
		if(!gotBoat){
			MainActivity.mScene.detachChild(child);
		}
	}
}
