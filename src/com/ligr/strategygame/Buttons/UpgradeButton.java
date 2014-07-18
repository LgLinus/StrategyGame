package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.buildings.House;
import com.ligr.strategygame.buildings.Road;

public class UpgradeButton extends Sprite {

	private static String currentbuilding;
	public int level=0;
	public String type ="";
	private MainActivity main;
	public House parentHouse;
	public UpgradeButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
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
		if(main.getController().Skin>=2 && main.getController().Marble >= 1){
			parentHouse.upgradetolvl4();
		}
	}

	public  void Cancel(){
		MainActivity.mScene.unregisterTouchArea(this);
//		MainActivity.mScene.unregisterTouchArea(MainActivity.buildingDescriptionCancel);
		MainActivity.buildingDescriptionHUD.setAlpha(0);
		main.removeEntity(MainActivity.houseInfo);
		main.removeEntity(MainActivity.houseNeeds);
	main.removeEntity(MainActivity.houseSatisfied);
//		MainActivity.buildingDescriptionCancel.setAlpha(0);
//		MainActivity.buildingDescriptionTitle.setAlpha(0);
//		MainActivity.buildingDescriptionDetail.setAlpha(0);
//		MainActivity.buildingDescriptionCancel.setAlpha(0);
		this.setAlpha(0);
		for(int i = 0;i<9;i++){
		main.getStockSpaceTexts().get(i).setText("");}
	
}
	
}
