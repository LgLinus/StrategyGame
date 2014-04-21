package com.ligr.strategygame.Buttons.Buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.PlaceBuilding;

public class SiloButton extends Sprite {

	private static String currentbuilding;
	
	public SiloButton(float pX, float pY, ITextureRegion pTextureRegion,
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
			MainActivity.removeBuildings = false;
			MainActivity.createBuildingHUD("Silo");
			MainActivity.removeBuildingTouchAreas();
			//	MainActivity.MoreInfoText.setText("The silo stores the wheat from your farms");
			//MainActivity.MoreInfoText.setColor(Color.BLACK);
		}
		return true;
	
	}
	
	
}
