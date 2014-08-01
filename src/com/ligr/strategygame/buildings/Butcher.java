package com.ligr.strategygame.buildings;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;


public class Butcher extends SpriteObject {

boolean pressedDown = false;
public boolean gotprovider = false;

	public Butcher(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSBUTCHER,0);
		yBaseStart = 31;}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(main.boolplacebuilding == false && pressedDown && main.removeBuildings==false){ pressedDown = false;
		main.addBuildingDescription("Butcher", "A butcher collect animals and produces meat, it also brings hide to the skinner");

	
		}
		else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
			main.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", main.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		main.getButchers().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(-ConstantBuildings.WORKERSBUTCHER, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public void save(){
		main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Butcher";
	}
	public void checkForStocks(){
		if(this.gotprovider == true){
		for(int i = 0;i<main.getStocks().size();i++){
			if(main.getController().calculateDistance(this, main.getStocks().get(i))<ConstantBuildings.RANGE){

				if(main.getStocks().get(i).checkSpace("Meat") ==true){
					main.getController().Meat+=1;
					break;
				}
			}
				for(int j = 0;j<main.getSkinners().size();j++){
					if(main.getController().calculateDistance(this, main.getSkinners().get(j))<512 && main.getSkinners().get(j).gotprovider == false){
						main.getSkinners().get(j).gotprovider = true;
						Debug.e("Provide");
							break;
						
					}
				}
		}
		}
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
