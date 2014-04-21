package com.ligr.strategygame.maptiles;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Tree extends AnimatedSprite {
	public boolean used = false;

	public Tree(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
			
	}

	public void cutWood(){
		if(this.getCurrentTileIndex()!= this.getTileCount()-1)
		this.stopAnimation(this.getCurrentTileIndex()+1);
		used = true;
	}
	public boolean used(){
		return used;
	}
}
