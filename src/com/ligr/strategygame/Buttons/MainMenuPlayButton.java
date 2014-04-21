package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

public class MainMenuPlayButton extends Sprite{

	
	private MainActivity Mainactivity;


	public MainMenuPlayButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity mainactivity) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		Mainactivity = mainactivity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
		if(MainActivity.mainMenuDoor !=null)
		if(MainActivity.mainMenuDoor.getCurrentTileIndex() == MainActivity.mainMenuDoor.getTileCount()-1){
			MainActivity.PAUSE = false;
			Debug.e(String.valueOf(MainActivity.PAUSE));
			Mainactivity.leaveMainMenu("new");
			MainActivity.attachStuffEnterGame();
		}
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			MainActivity.mainMenuDoor.animate(50);
			}
		return true;
	}
	
	
}
