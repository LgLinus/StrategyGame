package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;

public class HudRemoveBuildingButton extends Sprite{

	private MainActivity main;
	
	public HudRemoveBuildingButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			removeActivated();
		}
		return true;
		}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
	if(thisMenu())
		this.setAlpha(0.5f);
	else if (this.getAlpha() !=1.0f){
		this.setAlpha(1.0f);
		main.removeBuildings=false;}
	
	if(MainActivity.boolplacebuilding){
		MainActivity.removeBuildings=false;
		if(thisMenu())
			resetCurrentMenu();}
	}
	private void removeActivated() {
		if(MainActivity.removeBuildings == false){
		main.setCurrentMenu("remove");
		main.makeToast("Click on buildings to remove them!");
		main.removeBuildings = true;
		main.boolplacebuilding = false;
		main.addBuildingTouchAreas();
		main.boolplacebuilding = false;
		main.removePlaceBuildings();
		}
		else{
			main.removeBuildings = false;
			resetCurrentMenu();
		}}
	
	private void resetCurrentMenu() {
		main.setCurrentMenu("");
	}

	private boolean thisMenu() {
		return main.getCurrentMenu().equals("remove");
	}
}
