package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import constants.ConstantBuildings;

/* 
 * 	Mines bronze from the bronze deposit
 */
public class BronzeMine extends SpriteObject {

boolean pressedDown = false;
private MineDepositBronze deposit;
public int ammount=0,maxammount = 4;

	public BronzeMine(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSMINEDEPOSITBRONZE,0);
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Bronze mine";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "The bronze mine mines for bronze";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			removeEntity();
		}
	
	}
	return true;
}
	public void removeEntity(){
		//MainActivity.bronzeMines.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		this.detachSelf();
		MainActivity.removeEntity(this);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public void CheckForStocks(BronzeMine brickFoundry){
		for(int i = 0;i<MainActivity.Stocks.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<512){

				if(MainActivity.Stocks.get(i).checkSpace("Bronze") ==true){
					MainActivity.Bronze +=1;
					break;
				}
			}
		}
	}
	@Override
	public
	void save() {
		// TODO Auto-generated method stub
		
	}
}
