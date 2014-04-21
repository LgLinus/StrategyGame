package com.ligr.strategygame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class SpriteObject extends Sprite{
	
	public boolean remove = false;
	public int id =0;
	public boolean called = false;
	public SpriteObject(float pX, float pY,ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		this.id = MainActivity.objectAmount + MainActivity.startID;
		MainActivity.sObjects.add(this);

		MainActivity.objectAmount++;}

	public void removeTouchArea(){
		MainActivity.mScene.unregisterTouchArea(this);
	}
	public void addTouchArea(){
		MainActivity.mScene.registerTouchArea(this);
	}
	
	public boolean remove(){
		return remove;
	}
	public void detach(){
		this.detachSelf();
		MainActivity.sObjects.remove(this);
	}	public void setCalled(boolean bol){
		called = bol;
	}
	public void checkForStocks(){
		
	}	public void save(){
	}
	public void removeEntity(){
		
	}
	public void setID(int i) {
		this.id = i;
	}
}
