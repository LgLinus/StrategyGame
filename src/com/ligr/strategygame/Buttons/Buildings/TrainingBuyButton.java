package com.ligr.strategygame.Buttons.Buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.PlaceBuilding;
import com.ligr.strategygame.Buttons.BuyButton;
import com.ligr.strategygame.buildings.House;
import com.ligr.strategygame.constants.ConstantBuildings;


public class TrainingBuyButton extends BuyButton {

	private static String currentbuilding;
	
	public TrainingBuyButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){

	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp() && this.getAlpha()!=0){
				if(main.trainingHopliteButton.getAlpha()==1){
					if (main.getController().Armor>=ConstantBuildings.COSTHOPLITEARMOR && main.getController().getGold()>=ConstantBuildings.COSTHOPLITECOIN);
					{
						main.getController().updateGold(ConstantBuildings.COSTHOPLITECOIN);
						this.RemoveResources("Armor", ConstantBuildings.COSTHOPLITEARMOR);
						main.getController().setMilitaryHoplite(main.getController().getMilitaryHoplite()+1);
						Debug.e(String.valueOf(main.getController().getMilitaryHoplite()));
					}
				}
		}
		return true;
	}
	
	
}
