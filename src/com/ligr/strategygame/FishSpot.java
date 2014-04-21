package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class FishSpot extends AnimatedSprite {

	public boolean Used;
	public int boats = 0;
	public FishSpot(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	public boolean gotSpace() {
		// TODO Auto-generated method stub
		boolean bool = false;
		if (boats <2)
			bool = true;
		return bool;
	}
	
}
