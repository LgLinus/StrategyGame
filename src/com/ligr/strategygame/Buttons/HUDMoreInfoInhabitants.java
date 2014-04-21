package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.Farm;
import com.ligr.strategygame.Fountain;
import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Road;
import com.ligr.strategygame.Silo;

public class HUDMoreInfoInhabitants extends Sprite {


	public HUDMoreInfoInhabitants(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){

			MainActivity.MoreInfoText.setText(MainActivity.InhabitantsDescriptionString);
			MainActivity.MoreInfoText.setColor(Color.BLACK);
			
		
		}
		return true;
	
	}
	
	
}
