package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.Farm;
import com.ligr.strategygame.Fountain;
import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Road;
import com.ligr.strategygame.Silo;

public class HUDResourceMenuButton extends Sprite {

	private static String currentbuilding;
	private MainActivity mainActivity;
	public HUDResourceMenuButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity mainactivity) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		mainActivity = mainactivity;
		mainActivity.inGameHUD.registerTouchArea(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
		if(MainActivity.resourcesMenu.getAlpha()==1)
			this.setAlpha(0.5f);
		else
			this.setAlpha(1);
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionUp()){		
		//	mainActivity.leaveGame();
		if(MainActivity.resourcesMenu.getAlpha() == 0){
		
			MainActivity.closeMenus();	
			MainActivity.menuResourcesOpen = true;
			try{
				MainActivity.cancelbutton.Cancel();}
				catch(NullPointerException ex1){}
			MainActivity.buildingDescriptionCancel.Cancel();
			MainActivity.resourcesMenu.setAlpha(1);	
			
			int i = 32;
			if(MainActivity.Wood>0){
				MainActivity.woodResource.setAlpha(1);
				MainActivity.woodResource.setPosition(0,69+20+i);
				MainActivity.woodResourceText.setText(Integer.toString(MainActivity.Wood));
				MainActivity.woodResourceText.setPosition(76,69+24+i);
				MainActivity.woodResourceText.setColor(Color.BLACK);
					i+=72;}
			if(MainActivity.Marble>0){
					MainActivity.marbleResource.setAlpha(1);
					Debug.e("SHOW ME MARBLE");
					MainActivity.marbleResource.setPosition(0,69+20+i);
					MainActivity.marbleResourceText.setText(Integer.toString(MainActivity.Marble));
					MainActivity.marbleResourceText.setPosition(76,69+24+i);
					MainActivity.marbleResourceText.setColor(Color.BLACK);
					
					i+=72;}
			if(MainActivity.Brick>0){
				MainActivity.brickResource.setAlpha(1);
				MainActivity.brickResource.setPosition(0,69+20+i);
				MainActivity.brickResourceText.setText(Integer.toString(MainActivity.Brick));
				MainActivity.brickResourceText.setPosition(76,69+24+i);
				MainActivity.brickResourceText.setColor(Color.BLACK);
				
				i+=72;}
			if(MainActivity.Skin>0){
				MainActivity.skinResource.setAlpha(1);
				MainActivity.skinResource.setPosition(0,69+20+i);
				MainActivity.skinResourceText.setText(Integer.toString(MainActivity.Skin));
				MainActivity.skinResourceText.setPosition(76,69+24+i);
				MainActivity.skinResourceText.setColor(Color.BLACK);
					
				i+=72;}
			/*
			 * Om resursen är mer än 0, y+=16; 
			 * Rita upp den då
			 */
			
	}
		else if(MainActivity.resourcesMenu.getAlpha() == 1){
			Cancel();
		}	}
		return true;
	
	}
	public static void Cancel(){
		MainActivity.menuResourcesOpen = false;
		MainActivity.resourcesMenu.setAlpha(0);
		MainActivity.brickResource.setAlpha(0);
		MainActivity.woodResource.setAlpha(0);
		MainActivity.skinResource.setAlpha(0);
		MainActivity.marbleResource.setAlpha(0);
		MainActivity.woodResourceText.setText("");
		MainActivity.brickResourceText.setText("");
		MainActivity.marbleResourceText.setText("");
		MainActivity.skinResourceText.setText("");
			
			}
	
}
