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
import com.ligr.strategygame.Buttons.MenuObjectivesButton;
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
	private MenuObjectivesButton hudObjectivesButton;
	private MenuButton menuButton;
	private HUDResourceMenuButton hudResources;
	public HUDResourceMenuButton getHudResources() {
		return hudResources;
	}

	private HUDChatButton hudChatButton;
	private MainActivity main;
	public InGameMainHUD(MainActivity main) {
		this.main = main;
		// initializeObjects();
		// attachHUD();
	}

	public void initializeObjects() {
		VertexBufferObjectManager manager = MainActivity.main
				.getVertexBufferObjectManager();

		menuButton = new MenuButton(0, 0,main.getImages().getMenuButtonImage(),
				manager,main);
		hudRemoveBuildingButton = new HudRemoveBuildingButton(0, 597,
				main.getImages().getHudRemoveBuildingButtonImage(), manager,main);
		hudMapButton = new HudMapButton(hudRemoveBuildingButton.getWidth(), 597,
				main.getImages().getHudMapButtonImage(),
				MainActivity.main.getVertexBufferObjectManager(),main);
		hudObjectivesButton = new MenuObjectivesButton(
				0 + hudRemoveBuildingButton.getWidth() * 2, 597,
				main.getImages().getMenuQuestButtonImage(), manager, main);
		hudChatButton = new HUDChatButton(
				0 + hudRemoveBuildingButton.getWidth() * 3, 597,
				main.getImages().getHudChatButtonImage(), manager,main);
		hudMilitaryButton = new HUDMilitaryButton(
				0 + hudRemoveBuildingButton.getWidth() * 4, 597,
				main.getImages().getHudMilitaryButtonImage(), manager,main);
		hudResources = new HUDResourceMenuButton(198, 0,
				main.getImages().getHudResourcesButtonImage(), manager,
				this,main);
		incomeButton = new HUDIncomeButton(359, 0, main.getImages().getIncomeButtonImage(),
				manager,main);
		incomeButton.setAlpha(0);
		menuButton = new MenuButton(0, 0, main.getImages().getMenuButtonImage(),
				manager,main);
		hudmenucollectbutton = new HUDMenuCollectButton(MainActivity.CAMERA_WIDTH
				- main.getImages().getHudMenuCollect().getWidth(), 1 + main.getImages().getHudMenuCollect().getHeight(),
				main.getImages().getHudMenuCollect(), manager,main);
		hudmenuutilitybutton = new HUDMenuUtilityButton(MainActivity.CAMERA_WIDTH
				- main.getImages().getHudMenuCollect().getWidth() * 2, main.getImages().getHudMenuCollect().getHeight(),
				main.getImages().getHudMenuUtility(), manager,main);
		hudmenustoragebutton = new HUDMenuStorageButton(MainActivity.CAMERA_WIDTH
				- main.getImages().getHudMenuCollect().getWidth(), 1,main.getImages().getHudMenuStorage(),
				manager,main);
		hudmenumilitarybutton = new HUDMenuMilitaryButton(MainActivity.CAMERA_WIDTH
				- main.getImages().getHudMenuCollect().getWidth() * 2, 1, main.getImages().getHudMenuMilitaryImage(),
				manager,main);
	}

	public HUDResourceMenuButton getHUDResources() {
		return hudResources;
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
		attachChild(hudResources);
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
		unregisterTouchArea(hudResources);
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
		registerTouchArea(hudResources);
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

	public MenuObjectivesButton getHudObjectivesButton() {
	return hudObjectivesButton;
	}
}
