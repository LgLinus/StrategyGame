package com.ligr.strategygame.Buttons.Buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.PlaceBuilding;

public class HuntersLodgeButton extends Sprite {

	private static String currentbuilding;
	
	public HuntersLodgeButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.setAlpha(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){

	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp() && this.getAlpha()!=0){
				
			MainActivity.boolplacebuilding = true;
			MainActivity.createBuildingHUD("Hunters Lodge");
			MainActivity.removeBuildingTouchAreas();
			//	MainActivity.MoreInfoText.setText("A hunters lodge collect animals, delivering them to either the butcher. ");
			MainActivity.MoreInfoText.setColor(Color.BLACK);
			
		}
		return true;
	
	}
	
	
}
