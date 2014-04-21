package com.ligr.strategygame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.maptiles.ClayTile;
import com.ligr.strategygame.maptiles.MarbleTile;

public class MineDepositClay extends SpriteObject {
	
	private boolean pressedDown = false;
	private int time = 0;public int clayMonth = 4; // When 4 get new marble
	public boolean clayInRange = false;
	private ClayTile target;
	
	private int producetime = 1;
	public MineDepositClay(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		MainActivity.expenses += 5;
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSMINEDEPOSITCLAY,0);
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown  = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Clay mine";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "The clay mine, mines clay";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.clayMines.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSMINEDEPOSITCLAY, 0);
	}
	@Override
protected void onManagedUpdate(final float pSecondsElapsed){
super.onManagedUpdate(pSecondsElapsed);
	

	
}
public void save(){
	MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
}
private String name() {
	return "Mine Deposit Clay";
}public boolean getNewMarble(){
	double temprange= ConstantBuildings.RANGE+1;
	if(target==null){
	for(int i = 0; i < MainActivity.ClayTiles.size();i++){
		if(MainActivity.calculateDistance(this, MainActivity.ClayTiles.get(i)) < ConstantBuildings.RANGE){
			clayInRange = true;
			if(!MainActivity.ClayTiles.get(i).used){
				if(temprange > MainActivity.calculateDistance(this, MainActivity.ClayTiles.get(i))){
					target = MainActivity.ClayTiles.get(i);
					temprange = MainActivity.calculateDistance(this, MainActivity.ClayTiles.get(i));}
				
			}
			
		}
	}
	if(target!=null)
		target.getResource();
	return true;
	}
	else if(target.used==true){
		for(int i = 0; i < MainActivity.ClayTiles.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.ClayTiles.get(i)) < ConstantBuildings.RANGE){
				clayInRange = true;
				if(!MainActivity.ClayTiles.get(i).used){
					if(temprange > MainActivity.calculateDistance(this, MainActivity.ClayTiles.get(i))){
						target = MainActivity.ClayTiles.get(i);
						temprange = MainActivity.calculateDistance(this, MainActivity.ClayTiles.get(i));}
					
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
	if(this.clayMonth<4 && clayInRange){				
	for(int i = 0;i<MainActivity.Stocks.size();i++){
		if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<ConstantBuildings.RANGE){				
			if(MainActivity.Stocks.get(i).checkSpace("Clay") ==true){

			MainActivity.Clay +=1;
			this.clayMonth++;
				break;
			}
		}
	}
	
	}
	else{
		getNewMarble();
		this.clayMonth=0;
	}
}
}

