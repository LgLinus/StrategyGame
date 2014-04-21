package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


import com.ligr.strategygame.MainActivity;

public class BuildingConfirmChoiceButton extends Sprite {

	private static String choice;
	
	public BuildingConfirmChoiceButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, String choice) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.choice = choice;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
	if(MainActivity.canBuild)
		this.setAlpha(1);
	else
		this.setAlpha(0.5f);
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		Cancel();}
		return true;
	
	}
	public static void Cancel(){
		if(MainActivity.canBuild){
		MainActivity.changePlaceBuilding(choice);
		MainActivity.removeBuildingHUDKeepBuyButton();}
		else
			MainActivity.MakeToast("You can't build the building!");
	}
	
	
}
