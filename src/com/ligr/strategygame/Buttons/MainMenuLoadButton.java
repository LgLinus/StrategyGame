package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

/**
 * Class responsible of loading the game
 * @author LgLinuss
 *
 */
public class MainMenuLoadButton extends Sprite{

	
	private MainActivity main;


	public MainMenuLoadButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity mainactivity) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		main = mainactivity;
	}

	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			main.PAUSE = false;
			main.getController().leaveMainMenu("load");
			main.attachStuffEnterGame();
			}
		return true;
	}
	
	
}
