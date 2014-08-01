package com.ligr.strategygame.buildings;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;


public class Silo extends SpriteObject {

	private boolean pressedDown = false;
	public int FoodAmmount = 0;
	public int MaxFoodAmmount = 10000;
	public Silo(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSSILO,0);
		yBaseStart = 37;}
	public Silo(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main,int food) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		FoodAmmount = food;
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(main.boolplacebuilding == false && pressedDown && main.removeBuildings==false){ pressedDown = false;
		Debug.e("SILO FOOD: " + this.FoodAmmount);
		main.addBuildingDescription("Silo", "A Silo stores wheat inside of it.");
	
		}
		else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
			main.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", main.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		main.getSilos().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(-ConstantBuildings.WORKERSSILO, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);

		
	}
	public void save(){
		main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id,this.FoodAmmount);
	}
	private String name() {
		return "Silo";
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
