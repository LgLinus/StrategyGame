package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.DetachableObjects;
import com.ligr.strategygame.MainActivity;

public class MenuMapAttackButton extends DetachableObjects {

	private static String currentbuilding;
	private MainActivity main;
	public CityIcon city;
	public MenuMapAttackButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, CityIcon city,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.city = city;
		this.main = main;
		// TODO Auto-generated constructor stub
	}


	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		Attack();}
		return true;
	
	}
	public CityIcon getCity(){
		return this.city;
	}
	public void Attack(){
		Debug.e(this.city.getName());
		main.MessagePopUpChoice("Are you sure you want to attack " + this.city.getName() +"\nThis action will save the game", Color.WHITE, this, "Attack City", MainActivity.inGameHUD);

	}
	
	
}
