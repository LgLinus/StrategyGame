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


public class HuntersLodge extends SpriteObject {

	private boolean pressedDown = false;
	private boolean providingskin = false;

	public HuntersLodge(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main);
		if (!load)
			main.getController().updateWorkers(
					ConstantBuildings.WORKERSHUNTERSLODGE, 0);
		yBaseStart = 72;
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
				main.addBuildingDescription("Hunters Lodge",
						"A hunters lodge hunts animals and delivers them to the butcher");
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
		main.huntersLodges.remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(
				-ConstantBuildings.WORKERSHUNTERSLODGE, 0);
	}

	public void save() {
		main.getController().getDataBase().add(name(), this.getX(), this.getY(), this.id);
	}

	private String name() {
		return "Hunters Lodge";
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

	}

	public void checkForStocks() {
		if (this.providingskin == false) {
			for (int i = 0; i < main.getButchers().size(); i++) {
				if (main.calculateDistance(this, main.getButchers().get(i)) < ConstantBuildings.RANGE
						&& main.getButchers().get(i).gotprovider == false) {
					main.getButchers().get(i).gotprovider = true;
					System.out.println("provide");
					Debug.e("Provide");
					this.providingskin = true;
					break;
				}
			}
		}
	}	public float getZ(){
		return this.z + yBaseStart;
	}
}
