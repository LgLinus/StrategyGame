package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.buildings.House;
import com.ligr.strategygame.buildings.Road;

public class CancelButton extends Sprite {

	private static String currentbuilding;
	private MainActivity main;
	
	public CancelButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main =main;
	}

	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		Cancel();}
		return true;
	
	}
	public void Cancel(){
		main.addBuildingTouchAreas();
		
		if(main.placebuilding!=null)
		main.placebuilding.setAlpha(0);
		main.boolplacebuilding = false;
		if(main.MoreInfoText!=null){
		main.MoreInfoText.setText("");
		main.MoreInfoText.setColor(Color.BLACK);}
		main.removePlaceBuildings();
		main.finishBuy();
	
	}
	
	
}
