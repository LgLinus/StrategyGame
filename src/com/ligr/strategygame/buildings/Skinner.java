package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import other.GameMath;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;


public class Skinner extends SpriteObject {

boolean pressedDown = false;
public boolean gotprovider = false;

	public Skinner(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSSKINNER,0);
		yBaseStart = 31;}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(main.boolplacebuilding == false && pressedDown && main.removeBuildings==false){ pressedDown = false;
		main.addBuildingDescription("Skinner", "A skinner tans the hide from animals");
	
		}
		else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
			main.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", main.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		main.getSkinners().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(-ConstantBuildings.WORKERSSKINNER, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public void save(){
		main.getController().getDataBase().add(name(), this.getX(), this.getY(),this.id);
	}
	private String name() {
		return "Skinner";
	}
	public void checkForStocks(){
		if(this.gotprovider == true){
		for(int i = 0;i<main.getStocks().size();i++){
			if(GameMath.calculateRange(this, main.getStocks().get(i))<512){

				if(main.getStocks().get(i).checkSpace("Skin") ==true){
					System.out.println("Got skin!!");
					main.getController().Skin +=1;
					break;
				}
			}
		}
		}
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
