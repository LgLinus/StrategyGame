package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.buildings.House;
import com.ligr.strategygame.buildings.Road;

public class BuildingDescriptionCancel extends Sprite {

	private static String currentbuilding;
	private static MainActivity main;
	
	public BuildingDescriptionCancel(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		MainActivity.inGameHUD.registerTouchArea(this);
		this.main = main;
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		if(this.getAlpha() == 1){

			Cancel();
			this.setAlpha(0);
			}
		}
		return true;
	
	}
	public static void Cancel(){
		main.removeBuildingDescriptionHUD();
//		MainActivity.mScene.unregisterTouchArea(MainActivity.buildingDescriptionCancel);
		MainActivity.inGameHUD.getMenuButton().cancel();
//		MainActivity.buildingDescriptionHUD.setAlpha(0);
//		MainActivity.removeEntity(MainActivity.houseInfo);
//		MainActivity.removeEntity(MainActivity.houseNeeds);
//		MainActivity.removeEntity(MainActivity.houseSatisfied);
//		MainActivity.buildingDescriptionTitle.setAlpha(0);
//		MainActivity.buildingDescriptionDetail.setAlpha(0);;
//		MainActivity.upgradeButton.setAlpha(0);
//		MainActivity.buildingDescriptionCancel.setAlpha(0);
//		for(int i = 0;i<9;i++){
//		main.getStockSpaceTexts().get(i).setText("");}
}
	
}
