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

import com.ligr.strategygame.buildings.House;

/**
 * Upgrade arrow for a house. When pressed the user will be  given an option if he/she wants to upgrade the house or not
 * @author LgLinuss
 *
 */
public class UpgradeArrowHouse extends UpgradeArrow {

boolean pressedDown = false;
private House parent;
boolean toucharea = false;
String reqlvl4 = "Upgrading the house costs 2 skin. Click again to confirm.";
int ID;
private MainActivity main;

/**
 * 
 * @param pX x position
 * @param pY y position
 * @param pTextureRegion image
 * @param pVertexBufferObjectManager
 * @param pparent house building, which the arrow is related too
 * @param main MainActivity class
 */
	public UpgradeArrowHouse(float pX, float pY, ITiledTextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, House pparent,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,pparent);
		this.main = main;
		parent = pparent;
		ID = parent.getID();
		}
	/**
	 * When touch give the user a popup message with two choices
	 */
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp() && pressedDown){
		main.tempID = ID;
		main.MessagePopUpChoice("Upgrading the house costs 2 skins\n are you sure?", Color.WHITE, parent, "Upgdradehouselvl4", MainActivity.inGameHUD);
		}
		return false;
	
	}
	/**
	 * Animate the upgrade arrow
	 */
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
