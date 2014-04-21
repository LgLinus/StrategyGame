package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Road;

public class UpgradeButton extends Sprite {

	private static String currentbuilding;
	public int level=0;
	public String type ="";
	public House parentHouse;
	public UpgradeButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		if(this.getAlpha() == 1){
			Upgrade();
			Cancel();
			}
		}
		return true;
	
	}
	public void Upgrade(){
		if(type=="House");
			UpgradeHouse();
	}
	private void UpgradeHouse() {
		// TODO Auto-generated method stub
		if(MainActivity.Skin>=2 && MainActivity.Marble >= 1){
			parentHouse.upgradetolvl4();
		}
	}

	public  void Cancel(){
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.mScene.unregisterTouchArea(MainActivity.buildingDescriptionCancel);
		MainActivity.buildingDescriptionHUD.setAlpha(0);
		MainActivity.removeEntity(MainActivity.houseInfo);
		MainActivity.removeEntity(MainActivity.houseNeeds);
		MainActivity.removeEntity(MainActivity.houseSatisfied);
		MainActivity.buildingDescriptionCancel.setAlpha(0);
		MainActivity.buildingDescriptionTitle.setAlpha(0);
		MainActivity.buildingDescriptionDetail.setAlpha(0);
		MainActivity.buildingDescriptionCancel.setAlpha(0);
		this.setAlpha(0);
		for(int i = 0;i<9;i++){
		MainActivity.stockSpaceTexts.get(i).setText("");}
	
}
	
}
