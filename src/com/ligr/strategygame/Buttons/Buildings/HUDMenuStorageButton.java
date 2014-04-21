package com.ligr.strategygame.Buttons.Buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.PlaceBuilding;

public class HUDMenuStorageButton extends Sprite {

	private static String currentbuilding;
	
	public HUDMenuStorageButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
		if(MainActivity.menu=="Storage")
			this.setAlpha(0.5f);
		else
			this.setAlpha(1);
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp() && this.getAlpha()!=0){
			
			MainActivity.menu="Storage";
			// Set the image of our placebuilding to the House image
			MainActivity.MakeToast("This menu contains buildings that store various resources.");
			MainActivity.unRegisterBuildingButtons();
			MainActivity.menuhudstorage();
		}
		return true;
	
	}
	
	
}
