package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Road;

public class CancelButton extends Sprite {

	private static String currentbuilding;
	
	public CancelButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		Cancel();}
		return true;
	
	}
	public static void Cancel(){
		MainActivity.addBuildingTouchAreas();
		
		if(MainActivity.placebuilding!=null)
		MainActivity.placebuilding.setAlpha(0);
		MainActivity.boolplacebuilding = false;
		if(MainActivity.MoreInfoText!=null){
		MainActivity.MoreInfoText.setText("");
		MainActivity.MoreInfoText.setColor(Color.BLACK);}
		MainActivity.removePlaceBuildings();
		MainActivity.finishBuy();
	
	}
	
	
}
