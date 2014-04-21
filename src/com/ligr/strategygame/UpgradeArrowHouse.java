package com.ligr.strategygame;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

public class UpgradeArrowHouse extends UpgradeArrow {

boolean pressedDown = false;
House parent;
boolean toucharea = false;
String reqlvl4 = "Upgrading the house costs 2 skin. Click again to confirm.";
int ID;

	public UpgradeArrowHouse(float pX, float pY, ITiledTextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, House pparent) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,pparent);
		// TODO Auto-generated constructor stub
		parent = pparent;
		ID = parent.ID;
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){MainActivity.Message(reqlvl4, Color.BLACK);
		MainActivity.tempID = ID;
		MainActivity.MessagePopUpChoice("Upgrading the house costs 2 skins\n are you sure?", Color.WHITE, parent, "Upgdradehouselvl4", MainActivity.inGameHUD);
		}
		return false;
	
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	if(this.getAlpha() == 1){
			if(toucharea == false){
				this.animate(200);
				MainActivity.mScene.registerTouchArea(this);
				toucharea = true;}
		}
	
	

	}	
	
	
}
