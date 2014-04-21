package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public abstract  class AnimatedSpriteObject extends AnimatedSprite{
	
	public boolean remove = false;
	public boolean called = false;
	public int id = 0;
	public AnimatedSpriteObject(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		MainActivity.asObjects.add(this);
		this.id = MainActivity.objectAmount + MainActivity.startID;
		MainActivity.objectAmount++;
	}

	public void removeTouchArea(){
		MainActivity.mScene.unregisterTouchArea(this);
	}
	public void addTouchArea(){
		MainActivity.mScene.registerTouchArea(this);
	}
	public boolean remove(){
		return this.remove;
	}
	public void removeEntity(){
		this.detachSelf();
	}
	public void detach(){
		this.detachSelf();
		MainActivity.asObjects.remove(this);
	}
	public void checkForStocks(){
		
	}
	public void setCalled(boolean bol){
		called = bol;
	}
	public abstract void save();

	public void setID(int i) {
		this.id = i;	
	}
}
