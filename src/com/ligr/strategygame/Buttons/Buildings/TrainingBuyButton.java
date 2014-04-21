package com.ligr.strategygame.Buttons.Buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.ConstantBuildings;
import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.PlaceBuilding;
import com.ligr.strategygame.Buttons.BuyButton;

public class TrainingBuyButton extends BuyButton {

	private static String currentbuilding;
	
	public TrainingBuyButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){

	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp() && this.getAlpha()!=0){
				if(MainActivity.trainingHopliteButton.getAlpha()==1){
					if (MainActivity.Armor>=ConstantBuildings.COSTHOPLITEARMOR && MainActivity.Gold>=ConstantBuildings.COSTHOPLITECOIN);
					{
						MainActivity.Gold-=ConstantBuildings.COSTHOPLITECOIN;
						this.RemoveResources("Armor", ConstantBuildings.COSTHOPLITEARMOR);
						MainActivity.militaryHoplite++;
						Debug.e(String.valueOf(MainActivity.militaryHoplite));
					}
				}
		}
		return true;
	}
	
	
}
