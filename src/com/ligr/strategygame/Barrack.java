package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

public class Barrack extends SpriteObject {

boolean pressedDown = false;

	public Barrack(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSBARRACK,0);
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings!=true){ pressedDown = false;
			MainActivity.addTrainingHUD();}
			else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
				MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
			}
		
		}
		return true;
	}
		public void removeEntity(){
			MainActivity.barracks.remove(this);
			MainActivity.mScene.unregisterTouchArea(this);
			MainActivity.removeEntity(this);
		}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Barrack";
	}
}
