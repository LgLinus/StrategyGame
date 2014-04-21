package com.ligr.strategygame;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

public class Butcher extends SpriteObject {

boolean pressedDown = false;
public boolean gotprovider = false;

	public Butcher(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSBUTCHER,0);
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Butcher";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "A butcher collect animals and produces meat, it also brings hide to the skinner";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.butchers.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSBUTCHER, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Butcher";
	}
	public void checkForStocks(){
		if(this.gotprovider == true){
		for(int i = 0;i<MainActivity.Stocks.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<ConstantBuildings.RANGE){

				if(MainActivity.Stocks.get(i).checkSpace("Meat") ==true){
					MainActivity.Meat+=1;
					break;
				}
			}
				for(int j = 0;j<MainActivity.skinners.size();j++){
					if(MainActivity.calculateDistance(this, MainActivity.skinners.get(j))<512 && MainActivity.skinners.get(j).gotprovider == false){
						MainActivity.skinners.get(j).gotprovider = true;
						Debug.e("Provide");
							break;
						
					}
				}
		}
		}
	}
}
