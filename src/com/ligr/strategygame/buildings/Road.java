package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

public class Road extends SpriteObject {

	private boolean GotFood = false;
	
	public Road(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		// TODO Auto-generated constructor stub
		
		Road roadprev;
		Road roadcurr;
		yBaseStart = 12;
		if(main.getRoads().size()>0){
			
			roadprev = main.getRoads().get(main.getRoads().size()-1);
			roadcurr = this;
			
			// To the right of our road
			if(roadprev.mX > this.mX){
				//To the right, below our road
				if(roadprev.mY > this.mY){
					main.placebuilding.setPosition(main.placebuilding.getX()-24,main.placebuilding.getY()-12);
				}
				if(roadprev.mY < this.mY){
					main.placebuilding.setPosition(main.placebuilding.getX()-24,main.placebuilding.getY()+12);
				}
				}
			else if(roadprev.mX < this.mX){
					//To the right, below our road
				if(roadprev.mY > this.mY){
						main.placebuilding.setPosition(main.placebuilding.getX()+24,main.placebuilding.getY()-12);
				}
				if(roadprev.mY < this.mY){
						main.placebuilding.setPosition(main.placebuilding.getX()+24,main.placebuilding.getY()+12);
				}
			}
			}
			
		
	}
	public void save(){
		main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Road";
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
