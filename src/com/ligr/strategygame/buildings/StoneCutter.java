package com.ligr.strategygame.buildings;

import org.andengine.entity.primitive.Ellipse;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import other.EllipseCustom;
import other.GameMath;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.constants.ConstantBuildings;
import com.ligr.strategygame.maptiles.MarbleTile;


public class StoneCutter extends AnimatedSpriteObject {

	private boolean pressedDown = false;
	public int marbleMonth = 4; // When 4 get new marble
	public int MaxWorkers = 8;
	public int Workers = 8;
	public boolean marbleInRange = false;
	private AnimatedSprite npcStoneCutter;
	private MarbleTile target;
	private MainActivity main;
	public StoneCutter(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main, boolean load) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager,main);
		this.main = main;
		
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSSTONECUTTER,0);
		
		yBaseStart = 24;
	}
	public void createNpc(){
		npcStoneCutter = new AnimatedSprite(this.mX + this.getWidth()/2 - 8, this.mY + this.getHeight()/2 + 8, main.getImages().getNpcStoneCutter(), this.getVertexBufferObjectManager());
		main.mScene.attachChild(npcStoneCutter);
		npcStoneCutter.animate((long) (26*main.getController().getGameSpeed()));
	}
	public void save(){
		main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Stone Cutter";
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(main.boolplacebuilding == false && pressedDown && main.removeBuildings == false){ pressedDown = false;
		main.addBuildingDescription("Stone Cutter", "The stone cutter provides the city with Marble, which can be used for Tempels and Houses.");
		main.getController().addEllipseCustom(this.mX+(this.getWidth()),this.mY+this.getHeight(),ConstantBuildings.RANGESTONECUTTER,ConstantBuildings.RANGESTONECUTTER/2, this.getVertexBufferObjectManager(),EllipseCustom.REMOVEBACK);
		}
		else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
			main.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", main.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		main.getStoneCutters().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(-ConstantBuildings.WORKERSSTONECUTTER, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public boolean getNewMarble(){
		double temprange= ConstantBuildings.RANGESTONECUTTER+1;
		if(target==null){
		for(int i = 0; i < main.getMarbleTiles().size();i++){
			if(GameMath.calculateRange(this, main.getMarbleTiles().get(i),1,2) < ConstantBuildings.RANGESTONECUTTER){
				marbleInRange = true;
				if(!main.getMarbleTiles().get(i).used){
					if(temprange > GameMath.calculateRange(this, main.getMarbleTiles().get(i),1,2)){
						target = main.getMarbleTiles().get(i);
						temprange = GameMath.calculateRange(this, main.getMarbleTiles().get(i),1,2);}
					
				}
				
			}
		}
		if(target!=null)
			target.getResource();
		return true;
		}
		else if(target.used==true){
			for(int i = 0; i < main.getMarbleTiles().size();i++){
				if(GameMath.calculateRange(this, main.getMarbleTiles().get(i),1,2) < ConstantBuildings.RANGESTONECUTTER){
					marbleInRange = true;
					if(!main.getMarbleTiles().get(i).used){
						if(temprange > GameMath.calculateRange(this, main.getMarbleTiles().get(i),1,2)){
							target = main.getMarbleTiles().get(i);
							temprange = GameMath.calculateRange(this, main.getMarbleTiles().get(i),1,2);}
						
					}
					
				}
			}
			target.getResource();
			return true;
			}
		else
			target.getResource();
		return false;
	}
	public void checkForStocks(){
		if(this.marbleMonth<4 && marbleInRange){				
		for(int i = 0;i<main.getStocks().size();i++){
			if(GameMath.calculateRange(this, main.getStocks().get(i),1,2)<ConstantBuildings.RANGESTONECUTTER){				
				if(main.getStocks().get(i).checkSpace("Marble") ==true){

					main.getController().Marble +=1;
				this.marbleMonth++;
					break;
				}
			}
		}
		
		}
		else{
			getNewMarble();
			this.marbleMonth=0;
		}
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
