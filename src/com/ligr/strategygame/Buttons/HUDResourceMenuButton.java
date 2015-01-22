package com.ligr.strategygame.Buttons;

import huds.InGameMainHUD;
import huds.ResourcesHUD;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.buildings.Farm;
import com.ligr.strategygame.buildings.Well;
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
		if(main.resourcesMenu!=null &&( main.resourcesMenu instanceof ResourcesHUD))
			this.setAlpha(0.5f);
		else
			this.setAlpha(1);
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionUp()){		
		//	main.leaveGame();
		if(main.resourcesMenu==null ||(main.resourcesMenu!=null && !(main.resourcesMenu instanceof ResourcesHUD))){
		
			main.closeMenus();	
			main.menuResourcesOpen = true;
			try{
				main.cancelButton.Cancel();}
				catch(NullPointerException ex1){}
			main.createResourcesMenu();

			/*
			 * Om resursen är mer än 0, y+=16; 
			 * Rita upp den då
			 */
			
	}
		else if(main.resourcesMenu!=null){
			cancel();
		}	}
		return true;
	
	}
	public void cancel(){
		main.menuResourcesOpen = false;
		main.resourcesMenu.setAlpha(0);
		main.resourceBrick.setAlpha(0);
		main.resourceWood.setAlpha(0);
		main.resourceSkin.setAlpha(0);
		main.resourceMarble.setAlpha(0);
		main.resourceBronze.setAlpha(0);
		main.getResourceArmor().setAlpha(0);
		main.resourceWoodText.setText("");
		main.resourceBrickText.setText("");
		main.resourceMarbleText.setText("");
		main.resourceSkinText.setText("");
		main.resourceBronzeText.setText("");
		main.getResourceArmorText().setText("");
		main.removeResourcesMenu();
			}

	public void display() {
		Debug.e("Display resources");
		int i = 32;
		if(main.getController().Wood>0){
			main.reattachEntity(main.resourceWood,main.getInGameHUD());
			main.reattachEntity(main.resourceWoodText,main.getInGameHUD());
			main.resourceWood.setAlpha(1);
			main.resourceWood.setPosition(0,69+20+i);
			main.resourceWoodText.setText(Integer.toString(main.getController().Wood));
			main.resourceWoodText.setPosition(76,69+24+i);
			main.resourceWoodText.setColor(Color.BLACK);
				i+=72;}
		if(main.getController().Marble>0){
			main.reattachEntity(main.resourceMarble,main.getInGameHUD());
			main.reattachEntity(main.resourceMarbleText,main.getInGameHUD());
				main.resourceMarble.setAlpha(1);
				Debug.e("SHOW ME MARBLE");
				main.resourceMarble.setPosition(0,69+20+i);
				main.resourceMarbleText.setText(Integer.toString(main.getController().Marble));
				main.resourceMarbleText.setPosition(76,69+24+i);
				main.resourceMarbleText.setColor(Color.BLACK);
				
				i+=72;}
		if(main.getController().Brick>0){
			main.reattachEntity(main.resourceBrick,main.getInGameHUD());
			main.reattachEntity(main.resourceBrickText,main.getInGameHUD());
			main.resourceBrick.setAlpha(1);
			main.resourceBrick.setPosition(0,69+20+i);
			main.resourceBrickText.setText(Integer.toString(main.getController().Brick));
			main.resourceBrickText.setPosition(76,69+24+i);
			main.resourceBrickText.setColor(Color.BLACK);
			
			i+=72;}
		if(main.getController().Skin>0){
			main.reattachEntity(main.resourceSkin,main.getInGameHUD());
			main.reattachEntity(main.resourceSkinText,main.getInGameHUD());
			main.resourceSkin.setAlpha(1);
			main.resourceSkin.setPosition(0,69+20+i);
			main.resourceSkinText.setText(Integer.toString(main.getController().Skin));
			main.resourceSkinText.setPosition(76,69+24+i);
			main.resourceSkinText.setColor(Color.BLACK);
				
			i+=72;}
		if(main.getController().Bronze>0){
			main.reattachEntity(main.resourceBronze,main.getInGameHUD());
			main.reattachEntity(main.resourceBronzeText,main.getInGameHUD());
			main.resourceBronze.setAlpha(1);
			main.resourceBronze.setPosition(0,69+20+i);
			main.resourceBronzeText.setText(Integer.toString(main.getController().Bronze));
			main.resourceBronzeText.setPosition(76,69+24+i);
			main.resourceBronzeText.setColor(Color.BLACK);
				
			i+=72;}
		if(main.getController().Armor>0){
			main.reattachEntity(main.resourceArmor,main.getInGameHUD());
			main.reattachEntity(main.resourceArmorText,main.getInGameHUD());
			main.getResourceArmor().setAlpha(1);
			main.resourceArmor.setPosition(0,69+20+i);
			main.resourceArmorText.setText(Integer.toString(main.getController().Armor));
			main.resourceArmorText.setPosition(76,69+24+i);
			main.resourceArmorText.setColor(Color.BLACK);
				
			i+=72;}
	}

	
}
