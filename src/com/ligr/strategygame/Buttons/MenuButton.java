package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
/**
 * Class responsible of taking the user to the menu if pressed
 * @author LgLinuss
 *
 */
public class MenuButton extends Sprite{

	private MainActivity main;
	
	public MenuButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main) {
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
	
	/**
	 * Show the menu
	 */
	public void showMenu(){
		main.showMenu();
		main.PAUSE = true;
//		MainActivity.buildingDescriptionCancel.setAlpha(1);
//		MainActivity.inGameHUD.registerTouchArea(MainActivity.buildingDescriptionCancel);
	}
	
	/**
	 * Remove the menu
	 */
	public void cancel(){
		main.removeMenu();
		Debug.e("CANCEL");
		main.getMenuObjectivesButton().detachChildren();
		main.PAUSE = false;
	}
}
