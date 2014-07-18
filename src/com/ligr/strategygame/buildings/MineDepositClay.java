package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;
import com.ligr.strategygame.maptiles.ClayTile;
import com.ligr.strategygame.maptiles.MarbleTile;


public class MineDepositClay extends SpriteObject {
	
	private boolean pressedDown = false;
	private int time = 0;public int clayMonth = 4; // When 4 get new marble
	public boolean clayInRange = false;
	private ClayTile target;
	
	private int producetime = 1;
	public MineDepositClay(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		main.getController().expenses += 5;
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSMINEDEPOSITCLAY,0);
		yBaseStart = 24;}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown  = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(main.boolplacebuilding == false && pressedDown && main.removeBuildings==false){ pressedDown = false;
		main.addBuildingDescription("Clay mine", "The clay mine, mines clay");
	
		}
		else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
			main.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", main.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		main.getClayMines().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(-ConstantBuildings.WORKERSMINEDEPOSITCLAY, 0);
	}
	@Override
protected void onManagedUpdate(final float pSecondsElapsed){
super.onManagedUpdate(pSecondsElapsed);
	

	
}
public void save(){
	main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id);
}
private String name() {
	return "Mine Deposit Clay";
}public boolean getNewMarble(){
	double temprange= ConstantBuildings.RANGE+1;
	if(target==null){
	for(int i = 0; i < main.ClayTiles.size();i++){
		if(main.calculateDistance(this, main.ClayTiles.get(i)) < ConstantBuildings.RANGE){
			clayInRange = true;
			if(!main.ClayTiles.get(i).used){
				if(temprange > main.calculateDistance(this, main.ClayTiles.get(i))){
					target = main.ClayTiles.get(i);
					temprange = main.calculateDistance(this, main.ClayTiles.get(i));}
				
			}
			
		}
	}
	if(target!=null)
		target.getResource();
	return true;
	}
	else if(target.used==true){
		for(int i = 0; i < main.ClayTiles.size();i++){
			if(main.calculateDistance(this, main.ClayTiles.get(i)) < ConstantBuildings.RANGE){
				clayInRange = true;
				if(!main.ClayTiles.get(i).used){
					if(temprange > main.calculateDistance(this, main.ClayTiles.get(i))){
						target = main.ClayTiles.get(i);
						temprange = main.calculateDistance(this, main.ClayTiles.get(i));}
					
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
	for(int i = 0;i<main.getStocks().size();i++){
		if(main.calculateDistance(this, main.getStocks().get(i))<ConstantBuildings.RANGE){				
			if(main.getStocks().get(i).checkSpace("Clay") ==true){

				main.getController().Clay +=1;
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
}	public float getZ(){
	return this.z + yBaseStart;
}
}

