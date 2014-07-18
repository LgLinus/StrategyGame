package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;


/**
 * 	Mines bronze from the bronze deposit
 */
public class BronzeMine extends SpriteObject {

boolean pressedDown = false;
private MineDepositBronze deposit;
public int ammount=0,maxammount = 4;

	public BronzeMine(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		if(!load)
		main.getController().updateWorkers(ConstantBuildings.WORKERSMINEDEPOSITBRONZE,0);
		}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(main.boolplacebuilding == false && pressedDown && main.removeBuildings==false){ pressedDown = false;
		main.addBuildingDescription("Bronze mine", "The bronze mine mines for bronze");
	
		}
		else if(main.removeBuildings==true && main.boolplacebuilding == false && pressedDown){
			removeEntity();
		}
	
	}
	return true;
}
	public void removeEntity(){
		//main.bronzeMines.remove(this);
		main.mScene.unregisterTouchArea(this);
		this.detachSelf();
		main.removeEntity(this);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		
	
		
	}
	public void CheckForStocks(BronzeMine brickFoundry){
		for(int i = 0;i<main.getStocks().size();i++){
			if(main.calculateDistance(this, main.getStocks().get(i))<512){

				if(main.getStocks().get(i).checkSpace("Bronze") ==true){
					main.getController().Bronze +=1;
					break;
				}
			}
		}
	}
	@Override
	public
	void save() {
		// TODO Auto-generated method stub
		
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
