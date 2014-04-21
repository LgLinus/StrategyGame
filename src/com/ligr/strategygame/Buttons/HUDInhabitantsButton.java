package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.Farm;
import com.ligr.strategygame.Fountain;
import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Road;
import com.ligr.strategygame.Silo;

public class HUDInhabitantsButton extends Sprite {

	private static String currentbuilding;
	private MainActivity mainActivity;
	public HUDInhabitantsButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity mainactivity) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		mainActivity = mainactivity;
		mainActivity.inGameHUD.registerTouchArea(this);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
//		if(MainActivity.HUDWorkers.getAlpha()==1)
//			this.setAlpha(0.5f);
//		else
//			this.setAlpha(1);
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionUp()){	
			MainActivity.closeMenus();	
		//	mainActivity.leaveGame();
		if(MainActivity.HUDWorkers.getAlpha() == 0){
//			try{
//			MainActivity.cancelbutton.Cancel();}
//			catch(NullPointerException ex1){}
//			MainActivity.buildingDescriptionCancel.Cancel();
//			MainActivity.HUDWorkers.animate(100, false);
//			MainActivity.HUDWorkers.setAlpha(1);
//			MainActivity.HUDWorkersText.setText(Integer.toString(MainActivity.Workers)+"/"+Integer.toString(MainActivity.MaxWorkers));
			Color color = Color.WHITE;
			String res = MainActivity.Workers + "/" + MainActivity.MaxWorkers +"\nAt the moment you have: " + MainActivity.Workers + " workers, the maximum ammount of workers are: " + MainActivity.MaxWorkers + "\nThe total ammount of workers you can have depends on how many \ninhabitants you have";
			
//			MainActivity.hudMoreInfoWorkers.setAlpha(1);
//			MainActivity.hudMoreInfoInhabitants.setAlpha(1);
//			MainActivity.inGameHUD.registerTouchArea(MainActivity.hudMoreInfoWorkers);
//			MainActivity.inGameHUD.registerTouchArea(MainActivity.hudMoreInfoInhabitants);
//			
			if(MainActivity.MaxWorkers!=0 && MainActivity.Workers !=0){
				if(MainActivity.Workers/MainActivity.MaxWorkers >= .60 || MainActivity.Workers - MainActivity.MaxWorkers >= -1){
					}
					else	
					{
//					MainActivity.HUDWorkersText.setColor(Color.RED);
					color = Color.RED;
					res+="\n\nYou need to create more jobs before people will come to your city!";
				}}
//			else					
//				MainActivity.HUDWorkersText.setColor(Color.BLACK);
			
			MainActivity.MessagePopUp(res, color);
			
	}
		else if(MainActivity.HUDWorkers.getAlpha() == 1){
			Cancel();
		}
		}
		return true;
	
	}
	public static void Cancel(){
		//ainActivity.HUDWorkersText.setText("");
	}
	
}
