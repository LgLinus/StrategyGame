package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.maptiles.Tree;

public class WoodCutter extends SpriteObject {

boolean pressedDown = false;
private Tree target;
private boolean woodInRange = false;
private int woodMonth = 0;

	public WoodCutter(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSWOODCUTTER,0);
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Wood Cutter";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "The wood cutter provides the city with Wood, which can be used for building tempels and verious buildings.";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.WoodCutters.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSWOODCUTTER, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
		Debug.e("SAVE: " +name());
	}	public boolean getNewMarble(){
		double temprange= ConstantBuildings.RANGE+1;
		if(target==null){
		for(int i = 0; i < MainActivity.Trees.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.Trees.get(i)) < ConstantBuildings.RANGE){
				woodInRange  = true;
				if(!MainActivity.Trees.get(i).used){
					if(temprange > MainActivity.calculateDistance(this, MainActivity.Trees.get(i))){
						target = MainActivity.Trees.get(i);
						temprange = MainActivity.calculateDistance(this, MainActivity.Trees.get(i));}
					
				}
				
			}
		}
		if(target!=null)
			target.cutWood();
		return true;
		}
		else if(target.used==true){
			for(int i = 0; i < MainActivity.Trees.size();i++){
				if(MainActivity.calculateDistance(this, MainActivity.Trees.get(i)) < ConstantBuildings.RANGE){
					woodInRange = true;
					if(!MainActivity.Trees.get(i).used){
						if(temprange > MainActivity.calculateDistance(this, MainActivity.Trees.get(i))){
							target = MainActivity.Trees.get(i);
							temprange = MainActivity.calculateDistance(this, MainActivity.Trees.get(i));}
						
					}
					
				}
			}
			target.cutWood();
			return true;
			}
		else
			target.cutWood();
		return false;
	}
	private String name() {
		return "Wood Cutter";
	}
	public void checkForStocks(){
		Debug.e("XAXAXA");	
	if(this.woodMonth<4 && woodInRange){				
	for(int i = 0;i<MainActivity.Stocks.size();i++){
		if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<ConstantBuildings.RANGE){				
			if(MainActivity.Stocks.get(i).checkSpace("Wood") ==true){

			MainActivity.Wood +=1;
			this.woodMonth ++;
				break;
			}
		}
	}
	
	}
	else{
		getNewMarble();
		this.woodMonth=0;
	}
}
	}

