package com.ligr.strategygame;

import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;
/**
 * Super-class for all of the sprites who needs extra functions. 
 * Example the buildings
 * @author LgLinuss
 *
 */
public class SpriteObject extends Sprite{
	
	public boolean remove = false;
	public int id =0;
	public boolean called = false;
	protected MainActivity main;
	protected int yBaseStart = 0;
	protected float z = 0;
	protected Polygon polygon;
	public SpriteObject(float pX, float pY,ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.id = MainActivity.objectAmount + MainActivity.startID;
		main.getSObjects().add(this);
		this.main = main;
		main.objectAmount++;
		z = this.getY();
		main.updateScreen();
		float[] xVertice = {0,47,96,47};
		float[] yVertice = {23,0,23,47};
		polygon = new Polygon(this.mX, this.mY, xVertice, yVertice, this.getVertexBufferObjectManager());
		polygon.setColor(0.5f, 1f, 0.3f);
		MainActivity.mScene.attachChild(polygon);
		}
	/**
	 * Detach our spriteobject
	 */
	public void detach(){
		this.detachSelf();
		MainActivity.sObjects.remove(this);
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
		return remove;
	}
	public void detachNoArray(){
		this.detachSelf();
	}
	public void setCalled(boolean bol){
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
	public Polygon getBounds(){
		return polygon;
	}

}
