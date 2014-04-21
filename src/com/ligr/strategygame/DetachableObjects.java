package com.ligr.strategygame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class DetachableObjects extends Sprite{
	public DetachableObjects(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		MainActivity.detachableObjects.add(this);
	}

	public boolean remove = false;
	
	public boolean remove(){
		return remove;
	}
}
