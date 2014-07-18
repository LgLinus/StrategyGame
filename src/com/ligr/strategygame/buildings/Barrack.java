package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;

/**
 * A building which will allow the user to train military units
 * @author LgLinuss
 *
 */
public class Barrack extends SpriteObject {

boolean pressedDown = false;

	public Barrack(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSBARRACK,0);
		yBaseStart = 48;}
	/**
	 * Add a training HUD if the options is to not remove the building. 
	 * 
	 */
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			if(main.boolplacebuilding == false && pressedDown && main.removeBuildings!=true){ pressedDown = false;
			main.addTrainingHUD();}
			else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
				main.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", main.inGameHUD);
			}
		
		}
		return true;
	}
	/**
	 * Remove our barrack
	 */
	public void removeEntity(){
		main.getBarracks().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);	
	}
	/**
	 * Save our barrack
	 */
	public void save(){
		main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Barrack";
	}	
	public float getZ(){
		return this.z + yBaseStart;
	}
}
