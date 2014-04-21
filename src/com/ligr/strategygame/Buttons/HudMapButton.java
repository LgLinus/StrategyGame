package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Map;
import com.ligr.strategygame.SpriteObject;

public class HudMapButton extends Sprite{

	public HudMapButton(float pX, float pY, ITextureRegion pTextureRegion,
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
		if(MainActivity.menuMap==null){
		MainActivity.menuMap = new Map(0, 0, MainActivity.menuMapImage,
				this.getVertexBufferObjectManager());
		MainActivity.menuMapHUD = new Sprite(0 + MainActivity.menuMap.getWidth(), 0,
				MainActivity.menuMapHUDImage,
				this.getVertexBufferObjectManager());
		MainActivity.inGameHUD.attachChild(MainActivity.menuMap);
		MainActivity.inGameHUD.attachChild(MainActivity.menuMapHUD);
		for (int i = 0; i < MainActivity.cityMessageSize; i++) {
			Text tempText = new Text(MainActivity.menuMap.getWidth() + 4, 354 +  104 +  i * 25,
					MainActivity.smallFont, "", 200, this.getVertexBufferObjectManager());
			MainActivity.cityMessages.add(tempText);
			MainActivity.inGameHUD.attachChild(MainActivity.cityMessages.get(i));
		}
		for(int i = 0; i < MainActivity.cityIcons.size();i++){
			MainActivity.inGameHUD.attachChild(MainActivity.cityIcons.get(i));
			MainActivity.cityIcons.get(i).setText();
		}
		MainActivity.addMapTouchAreas();
		MainActivity.removeMenu();
		MainActivity.removeHudTouchAreas();
		} 
		}


}
