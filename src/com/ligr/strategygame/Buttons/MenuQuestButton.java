package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.LevelInfo;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

/**
 * @author LgLinuss
 *
 *MenuQuestButton
 *- Responsible of showing the quest objectives when pressed
 *
 */
public class MenuQuestButton extends Sprite{

	MainActivity main;
	private Text menuQuestText=null;
	/**
	 * Construct our button with the given image and position
	 */
	public MenuQuestButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			showMenu();
		}
		return true;
		}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
		super.onManagedUpdate(pSecondsElapsed);
		if(thisMenu())
			this.setAlpha(0.5f);
		else if(this.getAlpha()!=1.0f){
			removeObjectivesHUD();
			this.setAlpha(1.0f);}
	}
	/**
	 * Show the quest menu
	 */
	public void showMenu(){
		removeObjectivesHUD();
		if(thisMenu()){
			main.setCurrentMenu("");
			this.setAlpha(1.0f);
			return;}
		if(!thisMenu()){
		int level = main.getLevel();
	
		main.createBuildingHUD("Empty");
		if(level!=-1){
			if(menuQuestText!=null)
				detachText();
			menuQuestText = new Text(32 + 4, 2 + 48 + 32 + 32, main.getSmallFont(), "", 100,
					this.getVertexBufferObjectManager());
			menuQuestText.setColor(Color.BLACK);
			menuQuestText.setText(main.getController().getMission().createDescriptionOfMissions());
		}
		if(menuQuestText!=null)
			main.getInGameHUD().attachChild(menuQuestText);
		main.setCurrentMenu("objectives");}
//		MainActivity.buildingDescriptionHUD.setAlpha(1);
//		MainActivity.buildingDescriptionCancel.setAlpha(1);
//		MainActivity.inGameHUD.registerTouchArea(MainActivity.buildingDescriptionCancel);
	}
	
	private boolean thisMenu() {
		return main.getCurrentMenu().equals("objectives");
	}

	public void detachChildren(){
		detachText();
	}
	
	public void removeObjectivesHUD(){
		if(main.getBuildingDescriptionHUD() != null){
			main.getBuildingDescriptionHUD().remove();
			main.setBuildingDescriptionHUD(null);
			
		}
	}
	
	public void detachText(){
		if(menuQuestText!=null){
			main.removeEntity(menuQuestText);
			menuQuestText = null;
		}
	}

	
}
