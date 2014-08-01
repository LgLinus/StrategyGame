package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.LevelInfo;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

public class MenuSaveButton extends Sprite{

	private MainActivity main;
	public MenuSaveButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			showMenu();
		}
		return true;
		}
	
	public void showMenu(){
		main.MessagePopUpChoice("Are you sure you would like to save?", Color.WHITE, this, "Save", MainActivity.inGameHUD);
	}
}
