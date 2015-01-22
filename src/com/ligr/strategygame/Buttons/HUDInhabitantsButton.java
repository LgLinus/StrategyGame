package com.ligr.strategygame.Buttons;

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

public class HUDInhabitantsButton extends Sprite {

	private static String currentbuilding;
	private MainActivity main;
	public HUDInhabitantsButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		main.inGameHUD.registerTouchArea(this);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
//		if(main.HUDWorkers.getAlpha()==1)
//			this.setAlpha(0.5f);
//		else
//			this.setAlpha(1);
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionUp()){	
			main.closeMenus();	
		//	main.leaveGame();
		if(main.getHUDWorkesr().getAlpha() == 0){
//			try{
//			main.cancelbutton.Cancel();}
//			catch(NullPointerException ex1){}
//			main.buildingDescriptionCancel.Cancel();
//			main.HUDWorkers.animate(100, false);
//			main.HUDWorkers.setAlpha(1);
//			main.HUDWorkersText.setText(Integer.toString(main.getController().getWorkers)+"/"+Integer.toString(main.MaxWorkers));
			Color color = Color.WHITE;
			String res = main.getController().getWorkers() + "/" + main.getController().getMaxWorkers() +"\nAt the moment you have: " + main.getController().getWorkers() + " workers, the Maximum ammount of workers are: " + main.getController().getMaxWorkers() + "\nThe total ammount of workers you can have depends on how many \ninhabitants you have";
			
//			main.hudMoreInfoWorkers.setAlpha(1);
//			main.hudMoreInfoInhabitants.setAlpha(1);
//			main.inGameHUD.registerTouchArea(main.hudMoreInfoWorkers);
//			main.inGameHUD.registerTouchArea(main.hudMoreInfoInhabitants);
//			
			if(main.getController().getMaxWorkers()!=0 && main.getController().getWorkers() !=0){
				if(main.getController().getWorkers()/main.getController().getMaxWorkers() >= .60 || main.getController().getWorkers() - main.getController().getMaxWorkers() >= -1){
					}
					else	
					{
//					main.HUDWorkersText.setColor(Color.RED);
					color = Color.RED;
					res+="\n\nYou need to create more jobs before people will come to your city!";
				}}
//			else					
//				main.HUDWorkersText.setColor(Color.BLACK);
			Debug.e(" " + main.getController().getWorkers());
			main.messagePopUp(res, color);
			
	}
		else if(main.HUDWorkers.getAlpha() == 1){
			Cancel();
		}
		}
		return true;
	
	}
	public static void Cancel(){
		//ainActivity.HUDWorkersText.setText("");
	}
	
}
