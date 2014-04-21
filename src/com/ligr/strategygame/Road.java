package com.ligr.strategygame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Road extends SpriteObject {

	private boolean GotFood = false;
	
	public Road(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		
		Road roadprev;
		Road roadcurr;
		
		if(MainActivity.Roads.size()>0){
			
			roadprev = MainActivity.Roads.get(MainActivity.Roads.size()-1);
			roadcurr = this;
			
			// To the right of our road
			if(roadprev.mX > this.mX){
				//To the right, below our road
				if(roadprev.mY > this.mY){
					MainActivity.placebuilding.setPosition(MainActivity.placebuilding.getX()-24,MainActivity.placebuilding.getY()-12);
				}
				if(roadprev.mY < this.mY){
					MainActivity.placebuilding.setPosition(MainActivity.placebuilding.getX()-24,MainActivity.placebuilding.getY()+12);
				}
				}
			else if(roadprev.mX < this.mX){
					//To the right, below our road
				if(roadprev.mY > this.mY){
						MainActivity.placebuilding.setPosition(MainActivity.placebuilding.getX()+24,MainActivity.placebuilding.getY()-12);
				}
				if(roadprev.mY < this.mY){
						MainActivity.placebuilding.setPosition(MainActivity.placebuilding.getX()+24,MainActivity.placebuilding.getY()+12);
				}
			}
			}
			
		
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Road";
	}
}
