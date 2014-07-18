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

/**
 * Arrow displayed on top of a building if it can be upgraded manually
 * @author LgLinuss
 *
 */
public class UpgradeArrow extends AnimatedSprite {

boolean pressedDown = false;
Entity parent;


	public UpgradeArrow(float pX, float pY, ITiledTextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Entity entity) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		parent = entity;
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown){ pressedDown = false;
			
		}
		}
		return true;
	
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	}
	
	public void save() {

	}

}
