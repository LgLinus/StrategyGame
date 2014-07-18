package com.ligr.strategygame.Buttons;

import huds.InGameMainHUD;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.buildings.Farm;
import com.ligr.strategygame.buildings.Fountain;
import com.ligr.strategygame.buildings.House;
import com.ligr.strategygame.buildings.Road;
import com.ligr.strategygame.buildings.Silo;

public class HUDResourceMenuButton extends Sprite {

	private static String currentbuilding;
	private MainActivity main;
	public HUDResourceMenuButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,InGameMainHUD inGameMainHUD, MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		main.getScene().registerTouchArea(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
		if(main.resourcesMenu.getAlpha()==1)
			this.setAlpha(0.5f);
		else
			this.setAlpha(1);
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionUp()){		
		//	main.leaveGame();
		if(main.resourcesMenu.getAlpha() == 0){
		
			main.closeMenus();	
			main.menuResourcesOpen = true;
			try{
				main.cancelButton.Cancel();}
				catch(NullPointerException ex1){}
//			main.buildingDescriptionCancel.Cancel();
			main.resourcesMenu.setAlpha(1);	
			
			int i = 32;
			if(main.getController().Wood>0){
				main.woodResource.setAlpha(1);
				main.woodResource.setPosition(0,69+20+i);
				main.woodResourceText.setText(Integer.toString(main.getController().Wood));
				main.woodResourceText.setPosition(76,69+24+i);
				main.woodResourceText.setColor(Color.BLACK);
					i+=72;}
			if(main.getController().Marble>0){
					main.marbleResource.setAlpha(1);
					Debug.e("SHOW ME MARBLE");
					main.marbleResource.setPosition(0,69+20+i);
					main.marbleResourceText.setText(Integer.toString(main.getController().Marble));
					main.marbleResourceText.setPosition(76,69+24+i);
					main.marbleResourceText.setColor(Color.BLACK);
					
					i+=72;}
			if(main.getController().Brick>0){
				main.brickResource.setAlpha(1);
				main.brickResource.setPosition(0,69+20+i);
				main.brickResourceText.setText(Integer.toString(main.getController().Brick));
				main.brickResourceText.setPosition(76,69+24+i);
				main.brickResourceText.setColor(Color.BLACK);
				
				i+=72;}
			if(main.getController().Skin>0){
				main.skinResource.setAlpha(1);
				main.skinResource.setPosition(0,69+20+i);
				main.skinResourceText.setText(Integer.toString(main.getController().Skin));
				main.skinResourceText.setPosition(76,69+24+i);
				main.skinResourceText.setColor(Color.BLACK);
					
				i+=72;}
			/*
			 * Om resursen är mer än 0, y+=16; 
			 * Rita upp den då
			 */
			
	}
		else if(main.resourcesMenu.getAlpha() == 1){
			Cancel();
		}	}
		return true;
	
	}
	public void Cancel(){
		main.menuResourcesOpen = false;
		main.resourcesMenu.setAlpha(0);
		main.brickResource.setAlpha(0);
		main.woodResource.setAlpha(0);
		main.skinResource.setAlpha(0);
		main.marbleResource.setAlpha(0);
		main.woodResourceText.setText("");
		main.brickResourceText.setText("");
		main.marbleResourceText.setText("");
		main.skinResourceText.setText("");
			
			}
	
}
