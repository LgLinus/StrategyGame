package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import other.GameMath;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.constants.ConstantBuildings;

public class FishingHut extends SpriteObject {

	boolean pressedDown = false;
	private boolean gotBoat = false;
	private int costofBoatWood = 2;
	private Boat child;
	private boolean timerStarted = false;
	private int timer;
	public int ammount = 0, maxammount = 2000;

	public FishingHut(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main);
		if (!load)
			main.getController().updateWorkers(
					ConstantBuildings.WORKERSFISHINGHUT, 0);
		yBaseStart = 89;
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
				main.addBuildingDescription("Fishing hut",
						"The fishing hut gathers fish, which will give your inhabitants food");

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
		main.getFishingHuts().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(
				-ConstantBuildings.WORKERSFISHINGHUT, 0);
	}

	public void save() {
		main.getController().getDataBase()
				.add(name(), this.getX(), this.getY(), this.id);
	}

	private String name() {
		return "Fishing Hut";
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if (!timerStarted && gotBoat == false
				&& main.getController().Wood >= costofBoatWood) {
			timerStarted = true;
			timer = 120;
			main.buybutton.RemoveResources("Wood", costofBoatWood);
		} else if (timerStarted) {
			timer--;
			if (gotBoat == false && timer <= 0) {
				timerStarted = false;
				createBoat();
				timer = 120;
			}
		}
	}

	public void checkForStocks(FishingHut brickFoundry) {
		for (int i = 0; i < main.getStocks().size(); i++) {
			if (GameMath.calculateRange(this, main.getStocks().get(i)) < 512) {

				if (main.getStocks().get(i).checkSpace("Fish") == true) {
					main.getController().Fish += 1;
					break;
				}
			}
		}
	}

	// Creates a boat if the fishing hut doesn't have one
	public void createBoat() {
		if (!gotBoat) {
			if (main.getController().Wood >= costofBoatWood) {
				child = new Boat(this.mX + 104, this.mY + 60, main.getImages()
						.getBoatImage(), this.getVertexBufferObjectManager(),
						this, main);
				main.mScene.attachChild(child);
				gotBoat = true;
			}
		}
	}

	// Tells the fishing hut if we have the boat or not
	public void setGotBoat(boolean setGotBoat) {
		gotBoat = setGotBoat;
		if (!gotBoat) {
			main.mScene.detachChild(child);
		}
	}

	public float getZ() {
		return this.z + yBaseStart;
	}
}
