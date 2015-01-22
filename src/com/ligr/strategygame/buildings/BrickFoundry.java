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

import other.Cloud;
import other.GameMath;

/**
 * Brickfoundry is a building that can produce the resource bricks from clay
 * @author LgLinuss
 *
 */
public class BrickFoundry extends SpriteObject {

	boolean pressedDown = false;
	boolean produced = false;
	boolean gotWood = false;
	int month = 0;
	Cloud cloud;

	public BrickFoundry(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main);
		main.getController().expenses += 10;
		createChild();
		if (!load)
			main.getController().updateWorkers(
					ConstantBuildings.WORKERSBRICKFOUNDRY, 0);
		yBaseStart = 39;
	}

	// Create a "cloud" which looks like smoke
	private void createChild() {
		cloud = new Cloud(this.mX + 66, this.mY + 13
				- main.getImages().getCloudImage().getHeight(), main
				.getImages().getCloudImage(),
				this.getVertexBufferObjectManager());
		main.mScene.attachChild(cloud);
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
				main.addBuildingDescription("Brick foundry",
						"The brick foundry forms clay into bricks, which is needed for some buildings");

			} else if (main.removeBuildings == true
					&& main.boolplacebuilding == false && pressedDown) {
				main.MessagePopUpChoice(
						"Are you sure you want to remove the building?",
						Color.WHITE, this, "Remove", main.inGameHUD);
			}

		}
		return true;
	}

	/**
	 * Remove our brickfoundry and the cloud
	 */
	public void removeEntity() {
		main.getBrickFoundrys().remove(this);
		main.mScene.unregisterTouchArea(this);
		main.removeEntity(this);
		main.getController().updateWorkers(
				-ConstantBuildings.WORKERSBRICKFOUNDRY, 0);
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

	}

	/**
	 * Save our brickfoundry
	 */
	public void save() {
		main.getController().getDataBase()
				.add(name(), this.getX(), this.getY(), this.id);
	}

	private String name() {
		return "Brick Foundry";
	}

	public void checkForStocks() {
		month++;
		if (month > 3) {
			if (main.getController().Wood >= ConstantBuildings.COSTBRICKFOUNDRYWOODMONTHLY) {
				Debug.e(String.valueOf(main.getController().Wood));
				main.RemoveResources("Wood", ConstantBuildings.COSTBRICKFOUNDRYWOODMONTHLY);
				Debug.e("Remove wood for resource");
				Debug.e(String.valueOf(main.getController().Wood));
				gotWood = true;
			} else
				gotWood = false;
			month = 0;
		}
		if (gotWood) {
			if (!produced) {
				for (int i = 0; i < main.getStocks().size(); i++) {
					if (GameMath.calculateRange(this, main.getStocks().get(i)) < 512) {

						if (main.getStocks().get(i).Clay >= 2) {
							main.getController().removeResources("Clay", 2, main.getStocks().get(i));
							
							produced = true;
							break;
						}
					}
				}
			} else if (produced) {
				for (int i = 0; i < main.getStocks().size(); i++) {
					if (GameMath.calculateRange(this, main.getStocks().get(i)) < 512) {

						if (main.getStocks().get(i).checkSpace("Brick") == true) {
							main.getController().Brick += 1;
							produced = false;
							gotWood = false;
							break;
						}
					}
				}
			}
		}

	}

	public float getZ() {
		return this.z + yBaseStart;
	}
}
