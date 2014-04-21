package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;

public class MenuButton extends Sprite{

	public MenuButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			showMenu();
		}
		return true;
		}
	
	public void showMenu(){
		MainActivity.showMenu();
		MainActivity.PAUSE = true;
		MainActivity.buildingDescriptionCancel.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(MainActivity.buildingDescriptionCancel);
	}
	
	public void cancel(){
		MainActivity.removeMenu();
		Debug.e("CANCEL");
		MainActivity.menuQuestText.setText("");
		MainActivity.PAUSE = false;
	}
}
