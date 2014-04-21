package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.LevelInfo;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

public class MenuQuestButton extends Sprite{

	public MenuQuestButton(float pX, float pY, ITextureRegion pTextureRegion,
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
	
		int level = MainActivity.getLevel();
		if(level!=-1){
			MainActivity.menuQuestText.setColor(Color.BLACK);
			MainActivity.menuQuestText.setText(LevelInfo.level1goals[level]);
		}
		MainActivity.buildingDescriptionHUD.setAlpha(1);
		MainActivity.buildingDescriptionCancel.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(MainActivity.buildingDescriptionCancel);
	}
}
