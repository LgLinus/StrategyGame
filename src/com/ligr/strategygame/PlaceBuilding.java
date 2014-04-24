package com.ligr.strategygame;

import org.andengine.entity.primitive.Ellipse;
import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import constants.ConstantBuildings;

public class PlaceBuilding extends Sprite {





	public boolean remove = false;
	public Ellipse range;
	public int RANGE;
	public PlaceBuilding(float pX, float pY,
			ITextureRegion pITextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pITextureRegion, pVertexBufferObjectManager);
		RANGE = 0;
		if(MainActivity.currentBuilding == "Stone Cutter")
			RANGE = ConstantBuildings.RANGESTONECUTTER;
		range = new Ellipse(this.mX+(this.getWidth()),this.mY+this.getHeight(),RANGE,RANGE, this.getVertexBufferObjectManager());
		range.setColor(Color.BLACK);
		MainActivity.mScene.attachChild(range);
		
		// TODO Auto-generated constructor stub
	}

	public static String currentBuilding = "";
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		currentBuilding = MainActivity.currentBuilding;
		//if(currentBuilding == "Stone Cutter")
			//range.setAlpha(1);
		range.setX(this.mX+this.getWidth()/2);
		range.setY(this.mY+this.getHeight()/2);
		
	}
	public boolean gotSpace() {
		for(int i = 0; i < MainActivity.asObjects.size();i++){
			if(MainActivity.asObjects.get(i).getX() == this.mX && MainActivity.asObjects.get(i).getY() == this.mY){
				return false;
			}}
		for(int i = 0; i < MainActivity.sObjects.size();i++){
			
			if(MainActivity.sObjects.get(i).getX() == this.mX && MainActivity.sObjects.get(i).getY() == this.mY){
				return false;
			}
		}
		return true;
	}
	public void remove(){
		Debug.e("REMOVE PLEASEE");
		if(remove){
			MainActivity.removeEntity(range);
			MainActivity.removeEntity(this);
			range.detachSelf();
			}
	}
	
}
