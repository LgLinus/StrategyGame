package com.ligr.strategygame;

import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Super-class for all of the animated sprites who needs extra functions. 
 * Example the animated buildings
 * @author LgLinuss
 *
 */
public abstract  class AnimatedSpriteObject extends AnimatedSprite{
	
	public boolean remove = false;
	public boolean called = false;
	public int id = 0;
	protected float z = 0;
	protected int yBaseStart = 0;
	protected MainActivity main;
	protected Polygon polygon;
	public AnimatedSpriteObject(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		this.id = MainActivity.objectAmount + MainActivity.startID;
		this.main = main;
		main.getAsObjects().add(this);
		MainActivity.objectAmount++;	
		z = this.getY();
		main.updateScreen();
		float[] xVertice = {0,47,96,47};
		float[] yVertice = {23,0,23,47};
		polygon = new Polygon(this.mX, this.mY, xVertice, yVertice, this.getVertexBufferObjectManager());
		polygon.setColor(0.5f, 1f, 0.3f);
		MainActivity.mScene.attachChild(polygon);
		}

	/**
	 * Detach the sprite and remove it from the animated sprite object arraylist
	 */
	public void detach(){
		this.detachSelf();
		main.getAsObjects().remove(this);
	}
	/**
	 * Remove the touch area for our sprite object
	 */
	public void removeTouchArea(){
		main.getScene().unregisterTouchArea(this);
	}
	/**
	 * Add the touch area for our spriteobject
	 */
	public void addTouchArea(){
		main.getScene().registerTouchArea(this);
	}
	// Get-set methods
	public float getZ(){
		return z;
	}
	public boolean remove(){
		return this.remove;
	}
	public void removeEntity(){
		this.detachSelf();
	}
	public void detachNoArray(){
		this.detachSelf();
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
	public Polygon getBounds(){
		return polygon;
	}
}
