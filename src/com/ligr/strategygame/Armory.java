package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

public class Armory extends SpriteObject {

boolean pressedDown = false;

	public Armory(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSARMORY,0);
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings!=true){ pressedDown = false;
		MainActivity.buildingDescriptionHUD.setAlpha(1);
		MainActivity.buildingDescriptionCancel.setAlpha(1);
		MainActivity.buildingDescriptionTitle.setAlpha(1);
		MainActivity.buildingDescriptionDetail.setAlpha(1);
		MainActivity.mScene.registerTouchArea(MainActivity.buildingDescriptionCancel);
		MainActivity.buildingDescriptionTitleString = "Armory";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "The armory creates weapons and armor from bronze, armor is needed to create soldiers";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		//MainActivity.Armorys.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSARMORY, 0);
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Barrack";
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public void CheckForStocks(Armory brickFoundry){
		for(int i = 0;i<MainActivity.Stocks.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<512){

				if(MainActivity.Stocks.get(i).checkSpace("Armor") ==true){
					MainActivity.Armor +=1;
					break;
				}
			}
		}
	}
}
