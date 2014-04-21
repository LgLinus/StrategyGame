package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;

public class HudRemoveBuildingButton extends Sprite{

	public HudRemoveBuildingButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
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
	if(MainActivity.removeBuildings)
		this.setAlpha(0.5f);
	else
		this.setAlpha(1);
	
	if(MainActivity.boolplacebuilding)
		MainActivity.removeBuildings=false;
	}
	private void removeActivated() {
		if(MainActivity.removeBuildings == false){
		
		MainActivity.MakeToast("Click on buildings to remove them!");
		MainActivity.removeBuildings = true;
		MainActivity.boolplacebuilding = false;
		MainActivity.addBuildingTouchAreas();
		MainActivity.boolplacebuilding = false;
		MainActivity.removePlaceBuildings();
		}
		else
			MainActivity.removeBuildings = false;
	}
	
	
}
