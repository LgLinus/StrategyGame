package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.DetachableObjects;
import com.ligr.strategygame.MainActivity;

public class MenuBattleReturnButton extends DetachableObjects {

	private static String currentbuilding;
	public MenuBattleReturnButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}


	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		Return();}
		return true;
	
	}
	
	public void Return(){
		MainActivity.MessagePopUpChoice("Are you sure you want to leave the battlefield?\n half of your alive troops will die ", Color.WHITE, this, "Return", MainActivity.battleHUD);
	}
	
	
}
