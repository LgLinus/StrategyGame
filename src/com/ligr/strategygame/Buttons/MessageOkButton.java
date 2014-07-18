package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

public class MessageOkButton extends Sprite {

	private static String currentbuilding;
	private MainActivity main;
	public MessageOkButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		// TODO Auto-generated constructor stub
	}


	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		Cancel();}
		return true;
	
	}
	public void Cancel(){
		if(MainActivity.gameOver){
			main.getController().goToMainMenu();
		}
		else{
		main.removeMessage(); main.removeRecentMessage();}

	}
	
	
}
