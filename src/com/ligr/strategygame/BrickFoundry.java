package com.ligr.strategygame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import other.Cloud;

public class BrickFoundry extends SpriteObject {

boolean pressedDown = false;
boolean produced = false;boolean gotWood = false;
int month = 0;
Cloud cloud;
	public BrickFoundry(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		MainActivity.expenses += 10;
		createChild();
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSBRICKFOUNDRY,0);
			}
	private void createChild() {
		cloud = new Cloud(this.mX+66, this.mY+13-MainActivity.cloudImage.getHeight(), MainActivity.cloudImage, this.getVertexBufferObjectManager());
		MainActivity.mScene.attachChild(cloud);
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
		MainActivity.buildingDescriptionTitleString = "Brick foundry";
		MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
		MainActivity.buildingDescriptionDetailString = "The brick foundry forms clay into bricks, which is needed for some buildings";
		MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
	
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		MainActivity.brickFoundrys.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSBRICKFOUNDRY, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}

	public void save(){
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Brick Foundry";
	}
	public void checkForStocks(){
		month++;
		if(month>1)
		{
			if(MainActivity.Wood>=ConstantBuildings.COSTBRICKFOUNDRYWOODMONTHLY){
				
			
				MainActivity.buybutton.RemoveResources("Wood", ConstantBuildings.COSTBRICKFOUNDRYWOODMONTHLY);
				gotWood = true;}
			else
				gotWood = false;
			month = 0;
		}
		if(gotWood){
		if(!produced)
		{
			for(int i = 0;i<MainActivity.Stocks.size();i++){
				if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<512){

					if(MainActivity.Stocks.get(i).Clay>=2){
						MainActivity.buybutton.RemoveResources("Clay", 2, MainActivity.Stocks.get(i));
						produced = true;
						break;
					}
				}
			}	
		}
		else if(produced){
		for(int i = 0;i<MainActivity.Stocks.size();i++){
			if(MainActivity.calculateDistance(this, MainActivity.Stocks.get(i))<512){

				if(MainActivity.Stocks.get(i).checkSpace("Brick") ==true){
					MainActivity.Brick +=1;
					produced = false;
					gotWood = false;
					break;
				}
			}
}}}
	
		
	}
}
