package com.ligr.strategygame.maptiles;

/**
 * The farm harvest wheat
 */

// Each farm can give food for 56 inhabitants during a year.
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import other.DataBase;

public class ClayTile extends AnimatedSprite {

	public boolean used = false;

	public ClayTile(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		}
	
	public void getResource(){
		if(this.getCurrentTileIndex()< 8)
		this.stopAnimation(this.getCurrentTileIndex()+1);
		else
			used = true;
		
	}
}