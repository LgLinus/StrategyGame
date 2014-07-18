package com.ligr.strategygame.buildings;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;


public class FoodMarket extends SpriteObject {

	public int FoodAmmount = 0;
	private boolean pressedDown = false;
	public int MaxFoodAmmount = 1000;

	public FoodMarket(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main);
		if (!load)
			main.getController().updateWorkers(
					ConstantBuildings.WORKERSFOODMARKET, 0);
		yBaseStart = 23;
	}

	public FoodMarket(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, int number) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main);
		FoodAmmount = number;
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			pressedDown = true;
		}
		if (pSceneTouchEvent.isActionUp()) {

			if (main.boolplacebuilding == false && pressedDown
					&& main.removeBuildings == false) {
				pressedDown = false;
				main.addBuildingDescription("Food Market",
						"A food market distributes your city's food to the people.");
			} else if (main.removeBuildings == true
					&& main.boolplacebuilding == false && pressedDown) {
				main.MessagePopUpChoice(
						"Are you sure you want to remove the building?",
						Color.WHITE, this, "Remove", main.inGameHUD);
			}

		}
		return true;
	}

	public void removeEntity() {
		main.getFoodMarkets().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(
				-ConstantBuildings.WORKERSFOODMARKET, 0);
	}

	public void update() {
		checkForStocks();
	}

	public void save() {
		main.getController().getDataBase().add(name(), this.getX(), this.getY(), this.id,
				this.FoodAmmount);
	}

	private String name() {
		return "Food Market";
	}

	public void checkForStocks() {
		for (int i = 0; i < main.getSilos().size(); i++) {
			double distance = main.calculateDistance(main.getSilos().get(i),
					this);
			if (distance < 512 && main.getSilos().get(i).FoodAmmount > 0) {
				for (this.FoodAmmount = this.FoodAmmount; this.FoodAmmount < this.MaxFoodAmmount; this.FoodAmmount++) {
					// main.Silos.get(i).FoodAmmount-=1;

					// this.FoodAmmount +=1;
					if (main.getSilos().get(i).FoodAmmount <= 0) {
						break;
					}

				}

			}
		}
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
