package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

public class StockChoiceButton extends Sprite {

	private String kind;
	public StockChoiceButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, String kind) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.kind = kind;
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
	if(MainActivity.tempGlobalKind.contains(kind))
		this.setAlpha(0.5f);
	else
		this.setAlpha(1);
	}
		
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		pressed();}
		return true;
	
	}
	public void pressed(){
		if(MainActivity.tempGlobalKind.contains(this.kind))
			MainActivity.tempGlobalKind.replace(kind, "");
		else
			MainActivity.tempGlobalKind+=" " + this.kind;
		
		if(this.kind=="")
			MainActivity.tempGlobalKind="";
			
	}
	
	
}
