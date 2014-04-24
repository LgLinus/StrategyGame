package huds;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.database.sqlite.SQLiteOpenHelper;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Buttons.HUDChatButton;
import com.ligr.strategygame.Buttons.HUDMilitaryButton;
import com.ligr.strategygame.Buttons.HUDResourceMenuButton;
import com.ligr.strategygame.Buttons.HudMapButton;
import com.ligr.strategygame.Buttons.HudRemoveBuildingButton;
import com.ligr.strategygame.Buttons.MenuButton;
import com.ligr.strategygame.Buttons.MenuQuestButton;
import com.ligr.strategygame.Buttons.Buildings.HUDIncomeButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuCollectButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuMilitaryButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuStorageButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuUtilityButton;
import com.ligr.strategygame.Buttons.Buildings.HouseButton;

public class InGameMainHUD extends HUD {

	private HudMapButton hudMapButton;
	private HUDIncomeButton incomeButton;
	private HudRemoveBuildingButton hudRemoveBuildingButton;
	private HUDMenuMilitaryButton hudmenumilitarybutton;
	private HUDMenuCollectButton hudmenucollectbutton;
	private HUDMenuStorageButton hudmenustoragebutton;
	private HUDMenuUtilityButton hudmenuutilitybutton;
	private HUDMilitaryButton hudMilitaryButton;
	private MenuQuestButton hudObjectivesButton;
	private MenuButton menuButton;
	private HUDResourceMenuButton HUDResources;
	private HUDChatButton hudChatButton;

	public InGameMainHUD() {
		// initializeObjects();
		// attachHUD();
	}

	public void initializeObjects() {
		VertexBufferObjectManager manager = MainActivity.main
				.getVertexBufferObjectManager();

		menuButton = new MenuButton(0, 0, MainActivity.menuButtonImage,
				manager);
		hudRemoveBuildingButton = new HudRemoveBuildingButton(0, 597,
				MainActivity.hudRemoveBuildingButtonImage, manager);
		hudMapButton = new HudMapButton(hudRemoveBuildingButton.getWidth(), 597,
				MainActivity.hudMapButtonImage,
				MainActivity.main.getVertexBufferObjectManager());
		hudObjectivesButton = new MenuQuestButton(
				0 + hudRemoveBuildingButton.getWidth() * 2, 597,
				MainActivity.menuQuestButtonImage, manager);
		hudChatButton = new HUDChatButton(
				0 + hudRemoveBuildingButton.getWidth() * 3, 597,
				MainActivity.hudChatButtonImage, manager);
		hudMilitaryButton = new HUDMilitaryButton(
				0 + hudRemoveBuildingButton.getWidth() * 4, 597,
				MainActivity.hudMilitaryButtonImage, manager);
		HUDResources = new HUDResourceMenuButton(198, 0,
				MainActivity.hudResourcesButtonImage, manager,
				this);
		incomeButton = new HUDIncomeButton(359, 0, MainActivity.incomeButtonImage,
				manager);
		incomeButton.setAlpha(0);
		menuButton = new MenuButton(0, 0, MainActivity.menuButtonImage,
				manager);
		hudmenucollectbutton = new HUDMenuCollectButton(MainActivity.CAMERA_WIDTH
				- MainActivity.hudMenuCollect.getWidth(), 1 + MainActivity.hudMenuCollect.getHeight(),
				MainActivity.hudMenuCollect, manager);
		hudmenuutilitybutton = new HUDMenuUtilityButton(MainActivity.CAMERA_WIDTH
				- MainActivity.hudMenuCollect.getWidth() * 2, MainActivity.hudMenuCollect.getHeight(),
				MainActivity.hudMenuUtility, manager);
		hudmenustoragebutton = new HUDMenuStorageButton(MainActivity.CAMERA_WIDTH
				- MainActivity.hudMenuCollect.getWidth(), 1, MainActivity.hudMenuStorage,
				manager);
		hudmenumilitarybutton = new HUDMenuMilitaryButton(MainActivity.CAMERA_WIDTH
				- MainActivity.hudMenuCollect.getWidth() * 2, 1, MainActivity.hudMenuMilitaryImage,
				manager);
	}

	/**
	 * Attach the children to the hud
	 */
	public void attachHUD() {
		attachChild(hudMapButton);
		attachChild(hudObjectivesButton);
		attachChild(hudChatButton);
		attachChild(hudMilitaryButton);
		attachChild(hudRemoveBuildingButton);
		attachChild(incomeButton);
		attachChild(HUDResources);
		attachChild(hudmenuutilitybutton);
		attachChild(hudmenustoragebutton);
		attachChild(hudmenumilitarybutton);
		attachChild(menuButton);
		attachChild(hudmenucollectbutton);
}

	
	/**
	 * Remove the touch areas for the hud
	 */
	public void unregisterTouchAreas() {
		unregisterTouchArea(hudMapButton);
		unregisterTouchArea(hudRemoveBuildingButton);
		unregisterTouchArea(hudmenumilitarybutton);
		unregisterTouchArea(hudmenucollectbutton);
		unregisterTouchArea(hudmenustoragebutton);
		unregisterTouchArea(hudmenuutilitybutton);
		unregisterTouchArea(hudMilitaryButton);
		unregisterTouchArea(hudObjectivesButton);
		unregisterTouchArea(menuButton);
		unregisterTouchArea(incomeButton);
		unregisterTouchArea(HUDResources);
	}

	/**
	 * Add the touch areas for the hud
	 */
	public void registerTouchAreas() {
		registerTouchArea(hudMapButton);
		registerTouchArea(hudRemoveBuildingButton);
		registerTouchArea(hudmenumilitarybutton);
		registerTouchArea(hudmenucollectbutton);
		registerTouchArea(hudmenustoragebutton);
		registerTouchArea(hudmenuutilitybutton);
		registerTouchArea(hudMilitaryButton);
		registerTouchArea(hudObjectivesButton);
		registerTouchArea(menuButton);
		registerTouchArea(incomeButton);
		registerTouchArea(HUDResources);
	}

	public HUDChatButton gethudChatButton() {
		return hudChatButton;
	}

	public MenuButton getMenuButton() {
		return menuButton;
	}

	public HUDIncomeButton getIncomeButton() {
		// TODO Auto-generated method stub
		return incomeButton;
	}
}
