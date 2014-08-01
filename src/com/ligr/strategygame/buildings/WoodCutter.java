package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.AnimatedSprite;
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
import com.ligr.strategygame.maptiles.Tree;


public class WoodCutter extends SpriteObject {

	boolean pressedDown = false;
	private Tree target;
	private boolean woodInRange = false;
	private int woodMonth = 0;

	public WoodCutter(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main);
		if (!load)
			main.getController().updateWorkers(
					ConstantBuildings.WORKERSWOODCUTTER, 0);
		yBaseStart = 51;
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
				main.addBuildingDescription(
						"Wood Cutter",
						"The wood cutter provides the city with Wood, which can be used for building tempels and verious buildings.");

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
		main.getWoodCutters().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(
				-ConstantBuildings.WORKERSWOODCUTTER, 0);
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

	}

	public void save() {
		main.getController().getDataBase().add(name(), this.getX(), this.getY(), this.id);
		Debug.e("SAVE: " + name());
	}

	public boolean getNewMarble() {
		double temprange = ConstantBuildings.RANGE + 1;
		if (target == null) {
			for (int i = 0; i < main.getTrees().size(); i++) {
				if (main.getController().calculateDistance(this, main.getTrees().get(i)) < ConstantBuildings.RANGE) {
					woodInRange = true;
					if (!main.getTrees().get(i).used) {
						if (temprange > main.getController().calculateDistance(this, main
								.getTrees().get(i))) {
							target = main.getTrees().get(i);
							temprange = main.getController().calculateDistance(this, main
									.getTrees().get(i));
						}

					}

				}
			}
			if (target != null)
				target.cutWood();
			return true;
		} else if (target.used == true) {
			for (int i = 0; i < main.getTrees().size(); i++) {
				if (main.getController().calculateDistance(this, main.getTrees().get(i)) < ConstantBuildings.RANGE) {
					woodInRange = true;
					if (!main.getTrees().get(i).used) {
						if (temprange > main.getController().calculateDistance(this, main
								.getTrees().get(i))) {
							target = main.getTrees().get(i);
							temprange = main.getController().calculateDistance(this, main
									.getTrees().get(i));
						}

					}

				}
			}
			target.cutWood();
			return true;
		} else
			target.cutWood();
		return false;
	}

	private String name() {
		return "Wood Cutter";
	}

	public void checkForStocks() {
		if (this.woodMonth < 4 && woodInRange) {
			for (int i = 0; i < main.getStocks().size(); i++) {
				if (main.getController().calculateDistance(this, main.getStocks().get(i)) < ConstantBuildings.RANGE) {
					if (main.getStocks().get(i).checkSpace("Wood") == true) {

						main.getController().Wood += 1;
						this.woodMonth++;
						break;
					}
				}
			}

		} else {
			getNewMarble();
			this.woodMonth = 0;
		}
	}	public float getZ(){
		return this.z + this.yBaseStart;
	}
}
