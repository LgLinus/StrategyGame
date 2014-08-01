package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

/**
 * Class that starts the game when pressed
 * @author LgLinuss
 *
 */
public class MainMenuPlayButton extends Sprite{

	
	private MainActivity mainactivity;


	public MainMenuPlayButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity mainactivity) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.mainactivity = mainactivity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
		if(mainactivity.getMainMenuDoor() !=null)
		if(mainactivity.getMainMenuDoor().getCurrentTileIndex() == mainactivity.getMainMenuDoor().getTileCount()-1){
			mainactivity.PAUSE = false;
			mainactivity.getController().leaveMainMenu("new");
			mainactivity.attachStuffEnterGame();
		}
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			mainactivity.getMainMenuDoor().animate(50);
			}
		return true;
	}
	
	
}
