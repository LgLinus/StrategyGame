package com.ligr.strategygame;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;


public class CustomScene extends Scene {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CustomScene(final int pLayerCount) {
		super(pLayerCount);
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void attachChild(final IEntity pEntity) {
		// manually set index
		if (this.mChildren != null) {
			pEntity.setZIndex(this.mChildren.size());
		}
		super.attachChild(pEntity);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public IEntity checkOverlap(IEntity entity, float touchX, float touchY) {
		if (isHighestDepth(entity)) {
			return entity;
		}
		
		ArrayList<IEntity> list = new ArrayList<IEntity>();
		Sprite sprite;
		int length = this.mChildren.size();
		for (int i = 0; i < length; i++) {
			// check if other object is overlapped
			if (this.mChildren.get(i) instanceof Sprite) {
				sprite = (Sprite)this.mChildren.get(i);
				// add sprites which placed in the same touch point area 
				if (touchX >= sprite.getX() && touchX <= sprite.getX() + sprite.getWidth() && 
					touchY >= sprite.getY() && touchY <= sprite.getY() + sprite.getHeight()) {
					list.add(sprite);
				}
			}
		}
		
		// check which one has the highest z index
		if (list.size() > 0) {
			int highestIdx = list.get(0).getZIndex();
			for (int i = 0; i < list.size(); i++) {
				highestIdx = Math.max(highestIdx, list.get(i).getZIndex());
			}
			return getEntityAtZIndex(highestIdx);
		}

		return entity;
	}
	
	public void setHighestDepth(IEntity entity) {
		//if (isHighestDepth(entity)) {
			//return;
		
		
		// set max index to selected one
		int maxIdx = this.mChildren.size() - 1;
		int selectedIdx = entity.getZIndex();
		entity.setZIndex(maxIdx);
		
		// decrement index of the sprite which is greater than selected one
		Entity child;
		int length = this.mChildren.size();
		for (int i = 0; i < length; i++) {
	      child = (Entity)this.mChildren.get(i);
	      if (!child.equals(entity) && child.getZIndex() > selectedIdx) {
	    	  child.setZIndex(child.getZIndex() - 1);
	      }
	    }
		
		// update the view
		this.sortChildren();
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	private Boolean isHighestDepth(IEntity entity) {
		return (entity.getZIndex() == this.mChildren.size() - 1);
	}
	
	private IEntity getEntityAtZIndex(int idx) {
		IEntity entity = null;
		int length = this.mChildren.size();
		for (int i = 0; i < length; i++) {
			if (idx == this.mChildren.get(i).getZIndex()) {
				entity = this.mChildren.get(i);
				break;
			}
		}
		return entity;
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.getVertexBufferObjectManager();
	}
	
//	public void updateDepth(){
//		Debug.e("XAXAX");
//		for (int i = 0;i < this.mChildren.size();i++){
//			for(int j = 0; j < this.mChildren.size(); j++){
//				// If y1 < y2, replace y1 with 
//				if(this.mChildren.get(i).getY()<this.mChildren.get(j).getY()){
//					IEntity obj = this.mChildren.get(i);
//					this.mChildren.set(i, this.mChildren.get(j));
//					this.mChildren.set(j, obj);
//				}
//					
//			}
//		} }
	
}
