/**
 * 
 * @author Linus Granath
 * Date: 2013
 * 
 * Main class file, keeping track of creating various objects like Cameras, HUDS and buttons.
 * Also contains various methods for updating the game
 * 
 */

package com.ligr.strategygame;

import huds.BuildingDescriptionHUD;
import huds.InGameMainHUD;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import other.Controller;
import shapes.RectangleModified;
import text.HouseDescriptionText;
import text.RemoveableText;
import Maps.Levels;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ligr.strategygame.Buttons.BuildingCancelButton;
import com.ligr.strategygame.Buttons.BuildingConfirmChoiceButton;
import com.ligr.strategygame.Buttons.BuyButton;
import com.ligr.strategygame.Buttons.CancelButton;
import com.ligr.strategygame.Buttons.CityIcon;
import com.ligr.strategygame.Buttons.HUDInhabitantsButton;
import com.ligr.strategygame.Buttons.HUDMoreInfoInhabitants;
import com.ligr.strategygame.Buttons.HUDMoreInfoWorkers;
import com.ligr.strategygame.Buttons.MainMenuButton;
import com.ligr.strategygame.Buttons.MainMenuLoadButton;
import com.ligr.strategygame.Buttons.MainMenuPlayButton;
import com.ligr.strategygame.Buttons.MenuBattleReturnButton;
import com.ligr.strategygame.Buttons.MenuMapAttackButton;
import com.ligr.strategygame.Buttons.MenuMapButton;
import com.ligr.strategygame.Buttons.MenuQuestButton;
import com.ligr.strategygame.Buttons.MenuSaveButton;
import com.ligr.strategygame.Buttons.MessageCancelButton;
import com.ligr.strategygame.Buttons.MessageConfirmButton;
import com.ligr.strategygame.Buttons.MessageOkButton;
import com.ligr.strategygame.Buttons.StockChoiceButton;
import com.ligr.strategygame.Buttons.UpgradeButton;
import com.ligr.strategygame.Buttons.Buildings.BarrackButton;
import com.ligr.strategygame.Buttons.Buildings.BrickFoundryButton;
import com.ligr.strategygame.Buttons.Buildings.ButcherButton;
import com.ligr.strategygame.Buttons.Buildings.ClayMineButton;
import com.ligr.strategygame.Buttons.Buildings.FarmButton;
import com.ligr.strategygame.Buttons.Buildings.FishingHutButton;
import com.ligr.strategygame.Buttons.Buildings.FoodMarketButton;
import com.ligr.strategygame.Buttons.Buildings.FountainButton;
import com.ligr.strategygame.Buttons.Buildings.HouseButton;
import com.ligr.strategygame.Buttons.Buildings.HuntersLodgeButton;
import com.ligr.strategygame.Buttons.Buildings.RoadButton;
import com.ligr.strategygame.Buttons.Buildings.SiloButton;
import com.ligr.strategygame.Buttons.Buildings.SkinnerButton;
import com.ligr.strategygame.Buttons.Buildings.StockButton;
import com.ligr.strategygame.Buttons.Buildings.StoneCutterButton;
import com.ligr.strategygame.Buttons.Buildings.TheatreButton;
import com.ligr.strategygame.Buttons.Buildings.TrainingBuyButton;
import com.ligr.strategygame.Buttons.Buildings.TrainingNextButton;
import com.ligr.strategygame.Buttons.Buildings.TrainingPreviousButton;
import com.ligr.strategygame.Buttons.Buildings.WoodCutterButton;
import com.ligr.strategygame.buildings.Barrack;
import com.ligr.strategygame.buildings.BrickFoundry;
import com.ligr.strategygame.buildings.Butcher;
import com.ligr.strategygame.buildings.Farm;
import com.ligr.strategygame.buildings.FishingHut;
import com.ligr.strategygame.buildings.FoodMarket;
import com.ligr.strategygame.buildings.Fountain;
import com.ligr.strategygame.buildings.House;
import com.ligr.strategygame.buildings.HuntersLodge;
import com.ligr.strategygame.buildings.MineDepositBronze;
import com.ligr.strategygame.buildings.MineDepositClay;
import com.ligr.strategygame.buildings.Road;
import com.ligr.strategygame.buildings.Silo;
import com.ligr.strategygame.buildings.Skinner;
import com.ligr.strategygame.buildings.Stock;
import com.ligr.strategygame.buildings.StoneCutter;
import com.ligr.strategygame.buildings.Theatre;
import com.ligr.strategygame.buildings.WoodCutter;
import com.ligr.strategygame.constants.ConstantBuildings;
import com.ligr.strategygame.constants.ResourceImage;
import com.ligr.strategygame.maptiles.ClayTile;
import com.ligr.strategygame.maptiles.MarbleTile;
import com.ligr.strategygame.maptiles.Tree;
import com.ligr.strategygame.npc.Hoplite;


public class MainActivity extends SimpleBaseGameActivity implements
		IOnSceneTouchListener, IScrollDetectorListener,
		IPinchZoomDetectorListener {

	private SurfaceScrollDetector mScrollDetector;
	private PinchZoomDetector mPinchZoomDetector;
	private float mPinchZoomStartedCameraZoomFactor;
	public static String[] recentMessages = new String[20];
	public static int placeBuildingJumpX = 0;
	public static int placeBuildingJumpY = 0;
	private static int toastTime = Toast.LENGTH_SHORT;
	public ArrayList<RectangleModified> rectanglesModified = new ArrayList<RectangleModified>();
	private String currentMenu = "";
	/**
	 * 
	 * City information, {Name,Type,Relation,wealth,militaryoffensivestrength,
	 * militarydefencivestrength, distanceinmonths},x,y,availible Types: 0 =
	 * Peacfull, 1 = Reglious, 2 = Military Relation 0-19 = Hostile, 20 - 49,
	 * 50-69 - Neutral, 70 - 89 - Friendly, 90 - 100 Allies , Tribute, 0 -
	 * nothing , 1 - they pay, 2 - you pay, type of tribute, slingers, hoplites
	 */
	public static String[][] CITY = {
			{ "Sparta", "2", "20", "2", "4", "5", "396", "418", "1", "0",
					"Coin", "10", "15", "6" },
			{ "Athen", "0", "50", "4", "3", "4", "485", "245", "1", "0",
					"Coin", "10", "0", "4" },
			{ "Cnossus", "2", "1", "1", "2", "3", "762", "637", "1", "0",
					"Coin", "10", "15", "8" },
			{ "Miletus", "2", "90", "1", "3", "2", "940", "325", "1", "0",
					"Coin", "10", "25", "10" },
			{ "Olympia", "0", "50", "3", "1", "3", "252", "331", "0", "0",
					"Coin", "0", "0", "3" },
			{ "Pylos", "1", "70", "2", "2", "2", "290", "428", "0", "0",
					"Coin", "10", "0", "4" },
			{ "Troya", "0", "15", "5", "3", "6", "853", "33", "1", "0", "Coin",
					"40", "35", "10" }, };
	// Constant variables that will not change

	public static final int CAMERA_WIDTH = 1280; // Defines the width of the
													// screen
	public static final int CAMERA_HEIGHT = 720; // Defines the height of the
													// screen
	private static final int MAP_WIDTH = 4000; // Defines the width of the
												// current map
	static final int MAP_HEIGHT = 4000; // Defines the height of the current map
	public static int freeMonths = 24; // Amounts of month cities can't attack
										// you
	public static boolean PAUSE = false;

	public static String tempGlobalKind = "";
	public static String[] messageHistory = new String[100];

	public static String choice = "";
	public static Entity ID = null;

	// Variables for the previous location where we pressed
	private static float mouseprevx = CAMERA_WIDTH / 2;
	private static float mouseprevy = CAMERA_HEIGHT / 2;

	public static final float GRIDSIZE = 48;

	public static String map = "Map1"; // Tells us which map we are playing on
	public static int Month = 1; // Current month
	public static int Year = 2000; // Current year

	public static int currentMission = 1; // Defines the current mission
	public static int tempID = 0; // Temporary ids that we store for different
									// reasons





//	public static int gold = Constant.startGold; // Total ammount of gold

	private Text goldText; // Text that is changed to the ammount of gold
									// we have
	public static Text InhabitantsText;
	public static Text buildingDescriptionHouseInhabitantsText;
	public static Text buildingDescriptionHouseWheatText;
	public static Text buildingDescriptionHouseWaterText;
	public static Text buildingDescriptionHouseCultureText;
	public static Text buildingDescriptionHouseSkinText;
	public static Text HUDWorkersText;
	public static Text HUDWorkersDescriptionText;
	public static Text MoreInfoText;
	private Text monthText;
	public static Text woodResourceText;
	public static Text marbleResourceText;
	public static Text brickResourceText;
	public static Text skinResourceText;
	public static Text moveText;
	public static Text moveText2;
	public static Text moveText3;
	public static Text moveTextSmall;
	public static Text moveTextSmall2;
	public static Text moveTextSmall3;
	public static Text messageText;
	public static HouseDescriptionText houseSatisfied;
	public static HouseDescriptionText houseNeeds;
	public static HouseDescriptionText houseInfo;
	public static ArrayList<Text> buildingHUDTexts;

	public static Text stockspacetext;

	public static Font gameFont;
	public static Font smallFont;
	public static Font smallerFont;

	public static ZoomCamera camera;

	public static InGameMainHUD inGameHUD = null;
	private static final int HOUSEREQROAD = 1;
	public static ArrayList<ClayTile> ClayTiles;

	private static Sprite inGameHUDSprite;
	private Sprite mouse;
	public static BuildingDescriptionHUD buildingDescriptionHUD;
	public static Sprite buildingDescriptionHouseWater;
	public static Sprite buildingDescriptionHouseWheat;
	public static Sprite buildingDescriptionHouseSkin;
	public static Sprite buildingDescriptionHouseCulture;
	public static Sprite buildingDescriptionHouseInhabitants;
	public static MainActivity main;
	public static Map menuMap;
	public static Sprite menuMapHUD;
	public static Sprite grassTile;
	public static UpgradeButton upgradeButton;
	public static HUDInhabitantsButton HUDInhabitants;
	public static SkinnerButton skinnerButton;
	public static AnimatedSprite HUDWorkers;
	public static Sprite resourcesMenu;
	public static FountainButton fountainButton;
	public static MenuQuestButton menuQuestButton;
	public static FarmButton farmButton;
	public static Fountain fountain;
	public static Farm farm;
	public static SiloButton siloButton;
	public static StoneCutterButton stoneCutterButton;
	public static Tree tree;
	public static Sprite buildingHUD;
	public static BuildingConfirmChoiceButton buildingConfirmChoiceButton;
	public static BuildingCancelButton buildingCancelButton;
	public static HuntersLodgeButton huntersLodgeButton;
	private static FishingHutButton fishingHutButton;
	public static HUDMoreInfoWorkers hudMoreInfoWorkers;
	public static HUDMoreInfoInhabitants hudMoreInfoInhabitants;
	private static Sprite mainMenuBackground;
	public static Line rect;
	private Sprite aresTemple;
	public static MenuSaveButton menuSaveButton;

	public static CustomScene mScene;

	BuildableBitmapTextureAtlas GameAtlas;

	private ArrayList<PlaceBuilding> placeBuildings;
	private ArrayList<Text> stockSpaceTexts;
	private ArrayList<MineDepositBronze> mineDepositBronzes;
	private ArrayList<Fountain> fountains;
	private ArrayList<Farm> farms;
	private ArrayList<Tree> trees;
	private ArrayList<Theatre> theatres;
	private ArrayList<Sprite> grassTiles;
	private ArrayList<Stock> stocks;
	private ArrayList<AnimatedSprite> animatedSprites;
	private ArrayList<Barrack> barracks;
	private ArrayList<MarbleTile> marbleTiles;
	private ArrayList<MineDepositClay> mineDepositClays;
	private ArrayList<BrickFoundry> brickFoundrys;
	private ArrayList<WoodCutter> woodCutters;
	private ArrayList<StoneCutter> StoneCutters;
	private ArrayList<FoodMarket> foodMarkets;
	private ArrayList<Silo> silos;
	private ArrayList<Road> roads;
	private ArrayList<FishingHut> fishingHuts;
	private ArrayList<MineDepositClay> clayMines;
	private Theatre theatre;
	private House Houseexample;
	private ArrayList<House> houses;
	private ArrayList<Skinner> skinners;
	private Skinner skinner;
	private ArrayList<Butcher> butchers;
	public static Butcher butcher;
	public static FishingHut fishinghut;
	public Road road;
	public static Silo silo;
	public static FoodMarket foodmarket;
	public static StoneCutter stonecutter;
	public static WoodCutter woodcutter;
	public static ArrayList<HuntersLodge> huntersLodges;
	public static HuntersLodge huntersLodge;
	public static ArrayList<Hoplite> Hoplites;
	public static Hoplite Hoplite;
	public static ArrayList<AnimatedSpriteObject> asObjects;
	public static ArrayList<SpriteObject> sObjects;
	public static PlaceBuilding placebuilding;
	public static BuyButton buybutton;
	public static TrainingBuyButton trainingBuyButton;
	public static Sprite trainingHUD;
	public static Sprite trainingHopliteButton;
	public static Sprite trainingSlinger;
	public static TrainingNextButton trainingNextButton;
	public static TrainingPreviousButton trainingPreviousButton;
	public static int objectAmount = 0;
	public static CancelButton cancelButton;
	private static TheatreButton theatreButton;

	public static Sprite woodResource;
	public static Sprite brickResource;
	public static Sprite marbleResource;
	public static Sprite skinResource;

	public static boolean boolplacebuilding = false;

	public static ArrayList<Text> cityMessages;
	public static ArrayList<Text> chatHistoryTexts;
	public static ArrayList<RemoveableText> removeableTexts;
	public static ArrayList<StockChoiceButton> stockChoiceButtons;
	public static String menu = "Buildings";
	public static String WorkersDescriptionString = "You need workers to run the city. To the left you can see how many workers\n you need and to the right you can see how many workers you have in total.";
	public static String InhabitantsDescriptionString = "The inhabitants are the total ammount of people living in your city.\nThe inhabitants will pay tax and work for you, but they also require resources,\nlike food and water.";
	public static HouseButton housebutton;
	public static RoadButton roadbutton;
	public static FoodMarketButton foodMarketButton;
	public static StockButton stockButton;
	public static Stock stock;
	public static WoodCutterButton woodCutterButton;
	public static ButcherButton butcherButton;

	private static float touchx;
	private static float touchy;
	private Sprite marble;
	private FishSpot fishspot;
	private CityIcon cityIcon;
	public static int cityMessageSize = 10;
	private static MainMenuLoadButton mainMenuLoadButton;
	private static Controller controller;
	public static boolean menuResourcesOpen = false;
	private AnimatedSprite mainMenuDoor;
	private ResourceImage images;
	public static boolean canBuild;
	public static BarrackButton barrackButton;
	private static MessageOkButton messageOkButton;
	public static MainMenuButton menuMainMenuButton;
	public static MessageCancelButton messageCancelButton;
	public static MessageConfirmButton messageConfirmButton;
	public static Sprite messageHUD;

	private static MainMenuPlayButton mainMenuPlayButton;
	public static ArrayList<FishSpot> fishspots;
	public static MenuMapButton menuMapButton;
	public static Barrack barrack;
	public static int trainingUnitCount = 1;
	private static ArrayList<RemoveableText> trainingTexts;
	public static String currentBuilding = "";
	public static ArrayList<DetachableObjects> detachableObjects;
	public static MenuMapAttackButton menuMapAttackButton;
	public static BrickFoundry brickFoundry;
	private static MineDepositClay mineDepositClay;
	public static int startID = 15;
	public static MineDepositClay clayMine;
	public static BrickFoundryButton brickFoundryButton;
	public static ClayMineButton clayMineButton;
	private static MenuBattleReturnButton menuBattleReturnButton;
	public static HUD battleHUD;
	private static Entity battleHUDSprite;
	public static Rectangle rectangleBlackScreen;

	/**
	 * If we press back button
	 */

	private static String message;
	public static boolean gameOver = false;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			backPressed();
			return true;

		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Make sure to remove touch areas before
	 * 
	 * @param entity
	 */
	public void removeEntity(Entity entity) {
		if (entity != null) {

			final Entity ent = entity;
			main.runOnUpdateThread(new Runnable() {

				@Override
				public void run() {
					if (ent instanceof SpriteObject) {
						SpriteObject obj = (SpriteObject) ent;
						mScene.unregisterTouchArea(obj);
						main.sObjects.remove(obj);
					}
					if (ent instanceof AnimatedSpriteObject) {
						AnimatedSpriteObject obj = (AnimatedSpriteObject) ent;
						mScene.unregisterTouchArea(obj);
						main.asObjects.remove(obj);
					}
					ent.detachSelf();
				}

			});
		}
	}
	
	/**
	 * Make sure to remove touch areas before
	 * 
	 * @param entity
	 */
	public void removeEntityNotFromArray(Entity entity, final Scene scene) {
		if (entity != null) {

			final Entity ent = entity;
			main.runOnUpdateThread(new Runnable()  {
					
				@Override
				public void run() {
					if (ent instanceof SpriteObject) {
						SpriteObject obj = (SpriteObject) ent;
						mScene.unregisterTouchArea(obj);
					}
					if (ent instanceof AnimatedSpriteObject) {
						AnimatedSpriteObject obj = (AnimatedSpriteObject) ent;
						mScene.unregisterTouchArea(obj);
					}
					ent.detachSelf();
					scene.attachChild(ent);
				}

			});
		}
	}

	public  void removeEntity(Entity entity, final HUD hud) {
		final Entity ent = entity;
		main.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				if (ent instanceof SpriteObject) {
					SpriteObject obj = (SpriteObject) ent;
					hud.unregisterTouchArea(obj);
				}
				if (ent instanceof AnimatedSpriteObject) {
					AnimatedSpriteObject obj = (AnimatedSpriteObject) ent;
					hud.unregisterTouchArea(obj);
				}
				ent.detachSelf();
			}

		});
	}

	public  void addEntityScene(Entity entity) {
		final Entity ent = entity;
		main.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				mScene.attachChild(ent);
			}

		});
	}

	private void backPressed() {
		if (gameOver) {
			controller.goToMainMenu();
		} else {

			removeBuildingHUD();
			inGameHUD.gethudChatButton().closeChat();
			if (messageCancelButton != null)
				removeEntity(messageCancelButton);
			
			if (menuMapAttackButton != null) {
				removeEntity(menuMapAttackButton);
				inGameHUD.unregisterTouchArea(menuMapAttackButton);
			}
			if (menuMap != null)
				leaveMap();
			if(buildingDescriptionHUD != null){
				buildingDescriptionHUD.remove();
				buildingDescriptionHUD = null;
			}
			else if (this.buildingDescriptionHUD != null) {
				buildingDescriptionHUD.remove();
			} 
			else if (menuIncomeOpen) {
				inGameHUD.getIncomeButton().close();
			} else if (hudMoreInfoInhabitants.getAlpha() == 1
					|| resourcesMenu.getAlpha() == 1) {
				hideHudMenu();
			} else if (buybutton != null) {
				cancelButton.Cancel();
			} else if (trainingHUD.getAlpha() == 1) {
				removeTrainingHUD();
			} else if (menuMainMenuButton != null
					&& menuMainMenuButton.getAlpha() == 0) {
				inGameHUD.getMenuButton().showMenu();
			} else if (menuMainMenuButton == null) {
				showMenu();
			} else
				removeMenu();

		}
	}

	/**
	 * Hides the submenus
	 */
	private void hideHudMenu() {
//		HUDResourceMenuButton.cancel();
	
		this.HUDInhabitants.Cancel();
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		// Creates our camera, with the position x = 0 and y = 0 and the
		// width/height of CAMERA_WIDTH/CAMERA_HEIGHT
		camera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);


		// Creates some options for our app, for example screenorientation and
		// the ratio
		// return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
		// new FillResolutionPolicy(), camera);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
	

		Debug.e("before gameatlas");
		GameAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(),
				4096, 4096);

		// Calls the methods that initialize our images
		controller = new Controller(this);
		images = controller.getImages();
		initializeArrayLists();
		Debug.e("after initialize arrayList");

		// mEngine.getTextureManager().loadTexture(GameAtlas);
		try {
			GameAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 1));
			GameAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Log.e("", "" + e);
		}
		// Creates the array list for our objects
		gameFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 36,
				Color.WHITE);
		gameFont.load();
		smallFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 24,
				Color.WHITE);
		smallFont.load();
		smallerFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256, Typeface.DEFAULT, 18,
				Color.WHITE);
		smallerFont.load();
		inGameHUD = new InGameMainHUD(this);
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		this.mEngine.registerUpdateHandler(new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {
				if (controller.getMenu() == "Game") {
					for (int i = 0; i < placeBuildings.size(); i++) {
						if (placeBuildings.get(i).remove) {
							placeBuildings.get(i).detachSelf();
						}
					}
					for (int i = 0; i < detachableObjects.size(); i++) {
						if (detachableObjects.get(i).remove) {
							detachableObjects.get(i).detachSelf();
						}
					}
					for (int i = 0; i < asObjects.size(); i++) {
						if (asObjects.get(i).remove) {
							asObjects.get(i).detach();
						}
					}
					for (int i = 0; i < sObjects.size(); i++) {
						if (sObjects.get(i).remove) {
							sObjects.get(i).detach();
						}
					}
					for (int i = 0; i < removeableTexts.size(); i++) {
						if (removeableTexts.get(i).remove) {
							removeableTexts.get(i).detach();
						}
					}
				}
			}

			@Override
			public void reset() {
			}

		});

		// Creates a new scene
		mScene = new CustomScene(0);
		mScene.registerTouchArea(null); // 0000 0001
		mScene.clearTouchAreas();
		mScene.setOnAreaTouchTraversalFrontToBack();
		this.mScrollDetector = new SurfaceScrollDetector(this);
		this.mPinchZoomDetector = new PinchZoomDetector(this);
		main = this;
		mScene.setBackground(new Background(0.0f, 2.0f, 0.2f));

		menu = "MainMenu";
		// EnterGame();
		startGame();
		return mScene;
	}

	@Override
	public void onScrollStarted(final ScrollDetector pScollDetector,
			final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = camera.getZoomFactor();
		camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY
				/ zoomFactor);
	}

	@Override
	public void onScroll(final ScrollDetector pScollDetector,
			final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = camera.getZoomFactor();
		camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY
				/ zoomFactor);
	}

	@Override
	public void onScrollFinished(final ScrollDetector pScollDetector,
			final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = camera.getZoomFactor();
		camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY
				/ zoomFactor);
	}

	@Override
	public void onPinchZoomStarted(final PinchZoomDetector pPinchZoomDetector,
			final TouchEvent pTouchEvent) {
		this.mPinchZoomStartedCameraZoomFactor = camera
				.getZoomFactor();
	}

	@Override
	public void onPinchZoom(final PinchZoomDetector pPinchZoomDetector,
			final TouchEvent pTouchEvent, final float pZoomFactor) {
		camera
				.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor
						* pZoomFactor);
	}

	@Override
	public void onPinchZoomFinished(final PinchZoomDetector pPinchZoomDetector,
			final TouchEvent pTouchEvent, final float pZoomFactor) {
		camera
				.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor
						* pZoomFactor);
	}

	/**
	 * This happens when we touch the screen.
	 * 
	 * Basically checks if we drag the screen or try to put out an object.
	 */
	@Override
	public boolean onSceneTouchEvent(final Scene pScene,
			final TouchEvent pSceneTouchEvent) {
		this.mPinchZoomDetector.onTouchEvent(pSceneTouchEvent);
		if (this.mPinchZoomDetector.isZooming()) {
			this.mScrollDetector.setEnabled(false);
		} else {
			if (pSceneTouchEvent.isActionDown()) {
				this.mScrollDetector.setEnabled(true);
			}
			this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
		}

		if (pSceneTouchEvent.isActionDown()) {
			// Set the previous X and Y
			mouseprevx = pSceneTouchEvent.getX();
			mouseprevy = pSceneTouchEvent.getY();

		}
		// IF we are draggin the screen
		if (pSceneTouchEvent.isActionMove()) {
			if (PlaceBuilding.currentBuilding == "House"
					|| PlaceBuilding.currentBuilding == "Silo"
					|| PlaceBuilding.currentBuilding == "Stone Cutter") {
				touchx = pSceneTouchEvent.getX() / GRIDSIZE;
				touchx = Math.round(touchx);
				touchx = touchx * GRIDSIZE;
				touchy = (float) ((float) pSceneTouchEvent.getY() / (GRIDSIZE * 0.5));
				touchy = Math.round(touchy);

				touchy = (float) ((float) touchy * GRIDSIZE * 0.5);
				double touchy2;
				touchy2 = touchy * (0.25 / 2)
						- Math.round((touchy * (0.25 / 2)));
				if ((touchy2) != 0) {
					if (touchy2 < 0)
						touchx += GRIDSIZE / 2;
					else
						touchx -= GRIDSIZE / 2;
				}
			} else if (PlaceBuilding.currentBuilding == "Road") {
				touchx = pSceneTouchEvent.getX() / GRIDSIZE * 2;
				touchx = Math.round(touchx);
				touchx = touchx * GRIDSIZE / 2;
				touchy = (float) ((float) pSceneTouchEvent.getY() / (GRIDSIZE * 0.25));
				touchy = Math.round(touchy);

				touchy = (float) ((float) touchy * GRIDSIZE * 0.25 - 24);
				double touchy2;
				touchy2 = touchy * (0.25 / 2)
						- Math.round((touchy * (0.25 / 2)));
				if ((touchy2) != 0) {
					if (touchy2 < 0) {
						// touchx+=GRIDSIZE/8;
					} else
						touchx -= GRIDSIZE / 8;
				}
			} else {
				touchx = pSceneTouchEvent.getX() / GRIDSIZE;
				touchx = Math.round(touchx);
				touchx = touchx * GRIDSIZE;
				touchy = (float) ((float) pSceneTouchEvent.getY() / (GRIDSIZE * 0.5));
				touchy = Math.round(touchy);

				touchy = (float) ((float) touchy * GRIDSIZE * 0.5);
				double touchy2;
				touchy2 = touchy * (0.25 / 2)
						- Math.round((touchy * (0.25 / 2)));
				if ((touchy2) != 0) {
					if (touchy2 < 0)
						touchx += GRIDSIZE / 2;
					else
						touchx -= GRIDSIZE / 2;
				}
			}

		}
		if (boolplacebuilding) {
			if (PlaceBuilding.currentBuilding == "House"
					|| PlaceBuilding.currentBuilding == "Silo"
					|| PlaceBuilding.currentBuilding == "Food Market"
					|| PlaceBuilding.currentBuilding == "Theatre"
					|| PlaceBuilding.currentBuilding == "Stone Cutter"
					|| PlaceBuilding.currentBuilding == "Stock")
				placebuilding.setPosition(touchx
						- images.getHouseButtonImage().getWidth() / 2
						+ placeBuildingJumpX,
						(float) (touchy
								- images.getHouseButtonImage().getHeight()
								/ .75 + placeBuildingJumpY));
			if (PlaceBuilding.currentBuilding == "Road")
				placebuilding.setPosition(touchx
						- images.getRoadimage().getWidth() / 2
						+ placeBuildingJumpX,
						(float) (touchy - images.getRoadimage().getHeight()
								/ .75 + placeBuildingJumpY));
			placebuilding.setAlpha((float) 0.6);
			if (PlaceBuilding.currentBuilding == "Fountain")
				placebuilding
						.setPosition(
								touchx - images.getRoadimage().getWidth()
										+ placeBuildingJumpX / 2,
								(float) (touchy
										- images.getRoadimage().getHeight() + placeBuildingJumpY / .75));
			placebuilding.setAlpha((float) 0.6);
			if (PlaceBuilding.currentBuilding == "Farm")
				placebuilding
						.setPosition(
								touchx - images.getFarmImage().getWidth()
										+ placeBuildingJumpX / 2,
								(float) (touchy
										- images.getFarmImage().getHeight() + placeBuildingJumpY / .75));
			else {
				placebuilding
						.setPosition(
								touchx
										- images.getHouseButtonImage()
												.getWidth()
										+ placeBuildingJumpX / 2,
								(float) (touchy
										- images.getHouseButtonImage()
												.getHeight() + placeBuildingJumpY / .75));
			}
			placebuilding.updatePolygon();
		}
		if (pSceneTouchEvent.isActionMove()) {
			// If we are inside the game we should be able to move the view
			if (controller.getMenu() == "Game") {
				if (mouseprevx - pSceneTouchEvent.getX() > 15) {
					if (camera.getZoomFactor() >= 0.2)
						getMouse().setPosition(getMouse().getX() + 5 * 6,
								getMouse().getY());
					else
						getMouse().setPosition(getMouse().getX() + 5 * 6 * 5,
								getMouse().getY());
				}
				if (mouseprevx - pSceneTouchEvent.getX() < -15) {
					if (camera.getZoomFactor() >= 0.2)
						getMouse().setPosition(getMouse().getX() - 5 * 6,
								getMouse().getY());
					else
						getMouse().setPosition(getMouse().getX() - 5 * 6 * 5,
								getMouse().getY());
				}
				if (mouseprevy - pSceneTouchEvent.getY() > 15) {
					if (camera.getZoomFactor() >= 0.2)
						getMouse().setPosition(getMouse().getX(),
								(getMouse().getY() + 5 * 6));
					else
						getMouse().setPosition(getMouse().getX(),
								(getMouse().getY() + 5 * 6 * 5));
				}
				if (mouseprevy - pSceneTouchEvent.getY() < -15) {
					if (camera.getZoomFactor() >= 0.2)
						getMouse().setPosition(getMouse().getX(),
								(getMouse().getY() - 5 * 6));
					else
						getMouse().setPosition(getMouse().getX(),
								(getMouse().getY() - 5 * 6 * 5));

				}
				mouseprevx = pSceneTouchEvent.getX();
				mouseprevy = pSceneTouchEvent.getY();
				camera.setCenter(getMouse().getX(), getMouse().getY());
			}
		}
		return false;
	}

	// This method calculates a distance between 2 objects and returns the
	// distance
	public double calculateDistance(Entity object1, Entity object2) {

		float length;
		float height;
		length = object1.getX() - object2.getX();
		height = object1.getX() - object2.getX();
		double distance = Math.sqrt((length * length) + (height * height));

		return distance;
	}

	public void spawnArmy() {
		for (int i = 0; i < controller.getMilitaryHopliteWar(); i++) {
			Random rand = new Random();
			int x, y;
			x = rand.nextInt(96);
			y = rand.nextInt(96);
			Hoplite = new Hoplite(x, y, images.getHopliteImage(),
					main.getVertexBufferObjectManager());
			Hoplites.add(Hoplite);
			mScene.attachChild(Hoplite);
		}
	}

	public void CreateMap() {
		// TODO Auto-generated method stub
		if (map == "Map1") {
			int i, j, k;
			i = 0;
			j = 0;
			k = 0;
			int l = 0;
			Random rand = new Random();
			createMarbleTiles();
			createClayTiles();

			while (j < 80) {
				if (Levels.Map1[k] == 9) {
					tree = new Tree(i * 48, j * 48, images.getTreeImage(),
							this.getVertexBufferObjectManager());
					getTrees().add(tree);
					mScene.attachChild(tree);
					tree.stopAnimation(2);
				}
				if (Levels.Map1[k] == 2) {
					marble = new Sprite(i * 48, j * 48,
							images.getMarbleImage(),
							this.getVertexBufferObjectManager());
					mScene.attachChild(marble);
				}
				i++;
				k++;
				if (i >= 80) {
					i = 0;
					j++;

				}

			}

		}

	}

	private void createMarbleTiles() {
		int mineDepositStartX = 400, mineDepositStartY = 200, sizeX = 6, sizeY = 20;
		MarbleTile tempMarbleTile;
		for (int i = 0; i < sizeX; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY, images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() / 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY + images.getMarbleTileImage().getHeight()
							/ 2, images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempMarbleTile = new MarbleTile(
					mineDepositStartX + images.getMarbleTileImage().getWidth()
							* 1 + images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY + images.getMarbleTileImage().getHeight(),
					images.getMarbleTileImage(), main
							.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() * 1
					+ images.getMarbleTileImage().getWidth() / 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY + images.getMarbleTileImage().getHeight()
							+ images.getMarbleTileImage().getHeight() / 2,
					images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() * 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY + images.getMarbleTileImage().getHeight()
							* 2, images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() * 2
					+ images.getMarbleTileImage().getWidth() / 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY + images.getMarbleTileImage().getHeight()
							* 2 + images.getMarbleTileImage().getHeight() / 2,
					images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() / 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY - images.getMarbleTileImage().getHeight()
							/ 2, images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempMarbleTile = new MarbleTile(
					mineDepositStartX + images.getMarbleTileImage().getWidth()
							* 1 + images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY - images.getMarbleTileImage().getHeight(),
					images.getMarbleTileImage(), main
							.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() * 1
					+ images.getMarbleTileImage().getWidth() / 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY - images.getMarbleTileImage().getHeight()
							- images.getMarbleTileImage().getHeight() / 2,
					images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() * 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY - images.getMarbleTileImage().getHeight()
							* 2, images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ images.getMarbleTileImage().getWidth() * 2
					+ images.getMarbleTileImage().getWidth() / 2
					+ images.getMarbleTileImage().getWidth() * i,
					mineDepositStartY - images.getMarbleTileImage().getHeight()
							* 2 - images.getMarbleTileImage().getHeight() / 2,
					images.getMarbleTileImage(),
					main.getVertexBufferObjectManager());
			getMarbleTiles().add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}

	}

	private void createClayTiles() {
		int mineDepositStartX = 800, mineDepositStartY = 500, sizeX = 6, sizeY = 20;
		ClayTile tempClaytile;
		for (int i = 0; i < sizeX; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY, images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() / 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY + images.getClayTileImage().getHeight()
							/ 2, images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 1
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY + images.getClayTileImage().getHeight(),
					images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 1
					+ images.getClayTileImage().getWidth() / 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY + images.getClayTileImage().getHeight()
							+ images.getClayTileImage().getHeight() / 2,
					images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY + images.getClayTileImage().getHeight()
							* 2, images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 2
					+ images.getClayTileImage().getWidth() / 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY + images.getClayTileImage().getHeight()
							* 2 + images.getClayTileImage().getHeight() / 2,
					images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() / 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY - images.getClayTileImage().getHeight()
							/ 2, images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 1
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY - images.getClayTileImage().getHeight(),
					images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 1
					+ images.getClayTileImage().getWidth() / 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY - images.getClayTileImage().getHeight()
							- images.getClayTileImage().getHeight() / 2,
					images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY - images.getClayTileImage().getHeight()
							* 2, images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ images.getClayTileImage().getWidth() * 2
					+ images.getClayTileImage().getWidth() / 2
					+ images.getClayTileImage().getWidth() * i,
					mineDepositStartY - images.getClayTileImage().getHeight()
							* 2 - images.getClayTileImage().getHeight() / 2,
					images.getClayTileImage(),
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}

	}

	public Engine getEngine() {
		return this.mEngine;
	}

	public void enterBattlefield(CityIcon enemy) {
		mScene.clearEntityModifiers();
		mScene.clearTouchAreas();
		mScene.registerTouchArea(null); // 0000 0001
		mScene.clearTouchAreas();
		mScene.clearChildScene();
		mScene.detachChildren();
		camera.setHUD(null);
		camera.reset();

		Debug.e("COMMENTED SPAWNARMY");
		// spawnArmy();
		// createBattleHUD();
		// Create resume button
		// HUD
		// spawnArmyEnemy(enemy);
	}

	/**
	 * Creates the battle HUD
	 */
	public void createBattleHUD() {
		battleHUD = new HUD();
		battleHUDSprite = new Sprite(0, 0, images.getBattleHUDImage(),
				main.getVertexBufferObjectManager());
		battleHUD.attachChild(battleHUDSprite);
		camera.setHUD(battleHUD);
		menuBattleReturnButton = new MenuBattleReturnButton(1085, 531,
				images.getMenuBattleReturnButtonImage(),
				main.getVertexBufferObjectManager(), main);
		battleHUD.attachChild(menuBattleReturnButton);
		battleHUD.registerTouchArea(menuBattleReturnButton);
		battleHUD.registerTouchArea(inGameHUDSprite);

	}

	public void Message(String message,
			org.andengine.util.color.Color color) {
		MoreInfoText.setText(message);
		MoreInfoText.setColor(color);
	}

	public void messagePopUp(String message,
			org.andengine.util.color.Color color) {
		removeMessage();
		messageHUD = new Sprite(240, 180, images.getMessageHUDImage(),
				main.getVertexBufferObjectManager());
		messageHUD.setAlpha(0.95f);
		messageOkButton = new MessageOkButton(messageHUD.getX() + 256,
				messageHUD.getY() + 240, images.getMessageOkButtonImage(),
				main.getVertexBufferObjectManager(), this);
		messageText = new Text(300 + 8 - 64, 256 + 24, smallerFont, "", 5000,
				main.getVertexBufferObjectManager());
		messageText.setText(message);
		messageText.setColor(color);
		inGameHUD.registerTouchArea(messageOkButton);
		inGameHUD.attachChild(messageHUD);
		inGameHUD.attachChild(messageOkButton);
		inGameHUD.attachChild(messageText);
		this.updateMessageHistory(message);
		updateRecentMessages(message);
	}

	public void MessagePopUpNoRemove(String message,
			org.andengine.util.color.Color color) {
		// updateRecentMessages(message);
		messageHUD = new Sprite(240, 180, images.getMessageHUDImage(),
				main.getVertexBufferObjectManager());
		messageHUD.setAlpha(0.9f);
		messageOkButton = new MessageOkButton(messageHUD.getX() + 256,
				messageHUD.getY() + 240, images.getMessageOkButtonImage(),
				main.getVertexBufferObjectManager(), this);
		messageText = new Text(300 + 8 - 64, 256 + 24, smallerFont, "", 5000,
				main.getVertexBufferObjectManager());
		messageText.setText(message);
		messageText.setColor(color);
		// inGameHUD.registerTouchArea(messageCancelButton);
		// inGameHUD.registerTouchArea(messageConfirmButton);
		inGameHUD.registerTouchArea(messageOkButton);
		inGameHUD.attachChild(messageHUD);
		inGameHUD.attachChild(messageOkButton);
		inGameHUD.attachChild(messageText);
		// Debug.e("Pop");
	}

	public void updateRecentMessages(String message) {
		String res = "";
		for (int i = 0; i < recentMessages.length; i++) {
			if (recentMessages[i] == ""
					|| recentMessages[i] == null) {
				recentMessages[i] = message;
				res += recentMessages[i] + " " + "";
				break;
			}
			res += recentMessages[i] + " " + "";
		}
		Debug.e(res);
	}

	public void MessagePopUpChoice(String message,
			org.andengine.util.color.Color color, Entity parent, String choice,
			HUD hud) {
		Debug.e("AD");
		if (messageHUD != null) {
			removeEntity(messageHUD);
			inGameHUD.unregisterTouchArea(messageHUD);
		}
		if (messageCancelButton != null) {
			removeEntity(messageCancelButton);
			inGameHUD.unregisterTouchArea(messageCancelButton);
		}
		if (messageOkButton != null) {
			removeEntity(messageOkButton);
			inGameHUD.unregisterTouchArea(messageOkButton);
		}
		if (messageConfirmButton != null) {
			removeEntity(messageConfirmButton);
			inGameHUD.unregisterTouchArea(messageConfirmButton);
		}

		this.choice = choice;
		ID = parent;
		messageHUD = new Sprite(240, 180, images.getMessageHUDImage(),
				main.getVertexBufferObjectManager());
		messageConfirmButton = new MessageConfirmButton(
				messageHUD.getX() + 409, messageHUD.getY() + 240,
				images.getMessageConfirmButtonImage(),
				main.getVertexBufferObjectManager(), this);
		messageCancelButton = new MessageCancelButton(messageHUD.getX() + 48,
				messageHUD.getY() + 240, images.getMessageCancelButtonImage(),
				main.getVertexBufferObjectManager(),this);
		messageText = new Text(300 + 8, 256 + 24, smallerFont, "", 5000,
				main.getVertexBufferObjectManager());

		inGameHUD.attachChild(messageHUD);
		inGameHUD.attachChild(messageConfirmButton);
		inGameHUD.attachChild(messageCancelButton);
		inGameHUD.attachChild(messageText);
		messageHUD.setAlpha(0.9f);
		messageConfirmButton.setAlpha(1);
		messageCancelButton.setAlpha(1);
		messageText.setText(message);
		messageText.setColor(1f, 1f, 1f);
		hud.registerTouchArea(messageCancelButton);
		hud.registerTouchArea(messageConfirmButton);
		// Debug.e("Pop");
	}/*
	 * protected void UpdateFoodMarket() { // TODO Auto-generated method stub
	 * for(int i = 0;i<FoodMarkets.size();i++){
	 * //FoodMarkets.get(i).UpdateFood(FoodMarkets.get(i)); } } protected void
	 * UpdateStoneCutters() { // TODO Auto-generated method stub for(int i =
	 * 0;i<StoneCutters.size();i++){
	 * StoneCutters.get(i).CheckForStocks(StoneCutters.get(i)); } } protected
	 * void UpdateHuntersLodges() { // TODO Auto-generated method stub for(int i
	 * = 0;i<huntersLodges.size();i++){
	 * huntersLodges.get(i).CheckForStocks(huntersLodges.get(i)); } } protected
	 * void UpdateSkinners() { // TODO Auto-generated method stub for(int i =
	 * 0;i<skinners.size();i++){
	 * skinners.get(i).CheckForStocks(skinners.get(i)); } } protected void
	 * UpdateWoodCutters() { // TODO Auto-generated method stub for(int i =
	 * 0;i<WoodCutters.size();i++){
	 * WoodCutters.get(i).CheckForStocks(WoodCutters.get(i)); } } protected void
	 * UpdateButchers() { // TODO Auto-generated method stub for(int i =
	 * 0;i<butchers.size();i++){
	 * butchers.get(i).CheckForStocks(butchers.get(i)); } } /* protected void
	 * UpdateStocks() { // TODO Auto-generated method stub for(int i =
	 * 0;i<StoneCutters.size();i++){
	 * UpdateStocks.get(i).CheckForStocks(StoneCutters.get(i)); } }
	 */

	int getMarbleProductionYearly() {
		return getStoneCutters().size() * 12;
	}

	/**
	 * Checks to see if the inhabitants have accses to food
	 * 
	 * @return true or false if we have food
	 */
	double getAccsessToFood() {
		if (getHouses().size() != 0) {
			double percentoffood = 0;
			double gotfoodammount = 0;
			for (int i = 0; i < getHouses().size(); i++) {
				if (getHouses().get(i).checkFood()) {
					gotfoodammount += 1;
				}
			}
			percentoffood = (gotfoodammount / getHouses().size());
			if (gotfoodammount != 0)
				return percentoffood;
		}
		return 0;
	}

	/**
	 * Adds the touch areas for our buildings
	 */
	public void addBuildingTouchAreas() {

		for (int i = 0; i < sObjects.size(); i++) {
			sObjects.get(i).addTouchArea();
		}
		for (int i = 0; i < asObjects.size(); i++) {
			asObjects.get(i).addTouchArea();
		}
	}

	/**
	 * Removes the touch areas for our buildings
	 */
	public void removeBuildingTouchAreas() {
		// Makes it so we can't touch the buildings anymore, for example when we
		// got a menu open.
		for (int i = 0; i < sObjects.size(); i++) {
			sObjects.get(i).removeTouchArea();
		}
		for (int i = 0; i < asObjects.size(); i++) {
			asObjects.get(i).removeTouchArea();
		}
	}

	
	private void startGame() {
		setMainMenuBackground(new Sprite(0, 0,
				images.getMainMenuBackgroundImage(),
				this.getVertexBufferObjectManager()));
		mScene.attachChild(getMainMenuBackground());
		setMainMenuPlayButton(new MainMenuPlayButton(160, 135,
				images.getMainMenuPlayButtonImage(),
				this.getVertexBufferObjectManager(), this));
		setMainMenuDoor(new AnimatedSprite(575, 439,
				images.getMainMenuDoorImage(),
				this.getVertexBufferObjectManager()));
		getMainMenuDoor().stopAnimation(0);
		setMainMenuPlayButton(new MainMenuPlayButton(160 + 256, 135 + 256,
				images.getMainMenuPlayButtonImage(),
				this.getVertexBufferObjectManager(), this));
		setMainMenuLoadButton(new MainMenuLoadButton(160 + 256,
				135 + 256 + 256, images.getMainMenuLoadButtonImage(),
				this.getVertexBufferObjectManager(), this));
		mScene.attachChild(getMainMenuPlayButton());
		mScene.registerTouchArea(getMainMenuPlayButton());
		mScene.attachChild(getMainMenuLoadButton());
		mScene.registerTouchArea(getMainMenuLoadButton());
		mScene.attachChild(getMainMenuDoor());
		menu = "MainMenu";	Debug.e("after start gamne");

	}

	public void unRegisterBuildingButtons() {
		housebutton.setAlpha(0);
		skinnerButton.setAlpha(0);
		siloButton.setAlpha(0);
		foodMarketButton.setAlpha(0);
		theatreButton.setAlpha(0);
		huntersLodgeButton.setAlpha(0);
		fountainButton.setAlpha(0);
		farmButton.setAlpha(0);
		stockButton.setAlpha(0);
		woodCutterButton.setAlpha(0);
		stoneCutterButton.setAlpha(0);
		roadbutton.setAlpha(0);
		fishingHutButton.setAlpha(0);
		skinnerButton.setAlpha(0);
		butcherButton.setAlpha(0);
		barrackButton.setAlpha(0);
		brickFoundryButton.setAlpha(0);
		clayMineButton.setAlpha(0);

		inGameHUD.unregisterTouchArea(housebutton);
		inGameHUD.unregisterTouchArea(barrackButton);
		inGameHUD.unregisterTouchArea(skinnerButton);
		inGameHUD.unregisterTouchArea(siloButton);
		inGameHUD.unregisterTouchArea(foodMarketButton);
		inGameHUD.unregisterTouchArea(theatreButton);
		inGameHUD.unregisterTouchArea(huntersLodgeButton);
		inGameHUD.unregisterTouchArea(fountainButton);
		inGameHUD.unregisterTouchArea(farmButton);
		inGameHUD.unregisterTouchArea(stockButton);
		inGameHUD.unregisterTouchArea(woodCutterButton);
		inGameHUD.unregisterTouchArea(stoneCutterButton);
		inGameHUD.unregisterTouchArea(roadbutton);
		inGameHUD.unregisterTouchArea(fishingHutButton);
		inGameHUD.unregisterTouchArea(skinnerButton);
		inGameHUD.unregisterTouchArea(butcherButton);
		inGameHUD.unregisterTouchArea(brickFoundryButton);
		inGameHUD.unregisterTouchArea(clayMineButton);
	}

	// Makes our building buttons visible
	public void menuhudcollect() {
		inGameHUD.registerTouchArea(stoneCutterButton);
		inGameHUD.registerTouchArea(woodCutterButton);
		inGameHUD.registerTouchArea(farmButton);
		inGameHUD.registerTouchArea(huntersLodgeButton);
		inGameHUD.registerTouchArea(fishingHutButton);
		inGameHUD.registerTouchArea(brickFoundryButton);
		inGameHUD.registerTouchArea(clayMineButton);
		woodCutterButton.setAlpha(1);
		stoneCutterButton.setAlpha(1);
		farmButton.setAlpha(1);
		huntersLodgeButton.setAlpha(1);
		fishingHutButton.setAlpha(1);
		brickFoundryButton.setAlpha(1);
		clayMineButton.setAlpha(1);
		inGameHUD.registerTouchArea(skinnerButton);
		skinnerButton.setAlpha(1);
		inGameHUD.registerTouchArea(butcherButton);
		butcherButton.setAlpha(1);
	}

	public  void menuhudmilitary() {
		inGameHUD.registerTouchArea(barrackButton);
		barrackButton.setAlpha(1);
	}

	public void menuhudproduction() {
		inGameHUD.registerTouchArea(housebutton);
		housebutton.setAlpha(1);
		inGameHUD.registerTouchArea(foodMarketButton);
		foodMarketButton.setAlpha(1);
		inGameHUD.registerTouchArea(skinnerButton);
		skinnerButton.setAlpha(1);
		inGameHUD.registerTouchArea(butcherButton);
		butcherButton.setAlpha(1);
	}

	public void menuhudculture() {
		inGameHUD.registerTouchArea(theatreButton);
		theatreButton.setAlpha(1);
	}

	public void menuhudutility() {
		inGameHUD.registerTouchArea(roadbutton);
		roadbutton.setAlpha(1);
		inGameHUD.registerTouchArea(fountainButton);
		fountainButton.setAlpha(1);
		inGameHUD.registerTouchArea(housebutton);
		housebutton.setAlpha(1);
		inGameHUD.registerTouchArea(theatreButton);
		theatreButton.setAlpha(1);
		inGameHUD.registerTouchArea(foodMarketButton);
		foodMarketButton.setAlpha(1);

	}

	public void menuhudstorage() {
		inGameHUD.registerTouchArea(stockButton);
		stockButton.setAlpha(1);
		inGameHUD.registerTouchArea(siloButton);
		siloButton.setAlpha(1);

	}

	public void initializeArrayLists() {
		placeBuildings = new ArrayList<PlaceBuilding>();
		setHouses(new ArrayList<House>());
		setRoads(new ArrayList<Road>());
		setFountains(new ArrayList<Fountain>());
		setFarms(new ArrayList<Farm>());
		setSilos(new ArrayList<Silo>());
		setTrees(new ArrayList<Tree>());
		setTheatres(new ArrayList<Theatre>());
		setFoodMarkets(new ArrayList<FoodMarket>());
		setStoneCutters(new ArrayList<StoneCutter>());
		setWoodCutters(new ArrayList<WoodCutter>());
		setStocks(new ArrayList<Stock>());
		setMineDepositClays(new ArrayList<MineDepositClay>());
		setBrickFoundrys(new ArrayList<BrickFoundry>());
		setStocks(new ArrayList<Stock>());
		setStockSpaceTexts(new ArrayList<Text>());
		setFishingHuts(new ArrayList<FishingHut>());
		huntersLodges = new ArrayList<HuntersLodge>();
		setSkinners(new ArrayList<Skinner>());
		Hoplites = new ArrayList<Hoplite>();
		setButchers(new ArrayList<Butcher>());
		fishspots = new ArrayList<FishSpot>();
		setMineDepositBronzes(new ArrayList<MineDepositBronze>());
		asObjects = new ArrayList<AnimatedSpriteObject>();
		sObjects = new ArrayList<SpriteObject>();
		controller.resetGameInfo();
		cityMessages = new ArrayList<Text>();
		setBarracks(new ArrayList<Barrack>());
		removeableTexts = new ArrayList<RemoveableText>();
		trainingTexts = new ArrayList<RemoveableText>();
		detachableObjects = new ArrayList<DetachableObjects>();
		grassTiles = new ArrayList<Sprite>();
		marbleTiles=new ArrayList<MarbleTile>();
		ClayTiles = new ArrayList<ClayTile>();
		setClayMines(new ArrayList<MineDepositClay>());
		setBrickFoundrys(new ArrayList<BrickFoundry>());
		stockChoiceButtons = new ArrayList<StockChoiceButton>();
		chatHistoryTexts = new ArrayList<Text>();
		for (int i = 0; i < messageHistory.length; i++)
			messageHistory[i] = "";
	}

	public int getLevel() {
		if (currentMission - 1 <= LevelInfo.level1goals.length)
			return currentMission - 1;
		else
			return -1;
	}

	public void setUpHUD() {
		inGameHUDSprite = new Sprite(0, 0, images.getInGameHUDImage(),
				this.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(final GLState pGLState, final Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		inGameHUD.attachChild(inGameHUDSprite);
		// mScene.registerTouchArea(inGameHUDSprite);
		for (int i = -2000; i < 2000; i += images.getGrassTileImage()
				.getWidth()) {
			for (int j = -2000; j < 2000; j += images.getGrassTileImage()
					.getHeight()) {
				grassTile = new Sprite(i, j, images.getGrassTileImage(),
						this.getVertexBufferObjectManager());
				grassTiles.add(grassTile);
				mScene.attachChild(grassTile);
			}
		}
		moveText = new Text(0, 0, gameFont, "", 2000,
				this.getVertexBufferObjectManager());
		moveText2 = new Text(0, 0, gameFont, "", 2000,
				this.getVertexBufferObjectManager());
		moveText3 = new Text(0, 0, gameFont, "", 2000,
				this.getVertexBufferObjectManager());
		for (int i = 0; i < cityMessages.size(); i++) {
			removeEntity(cityMessages.get(i));
		}
		cityMessages.clear();

		moveTextSmall = new Text(0, 0, smallFont, "", 2000,
				this.getVertexBufferObjectManager());
		moveTextSmall2 = new Text(0, 0, smallFont, "", 2000,
				this.getVertexBufferObjectManager());
		moveTextSmall3 = new Text(0, 0, smallFont, "", 2000,
				this.getVertexBufferObjectManager());
		setGoldText(new Text(450, 16, gameFont, Integer.toString(this.controller.getGold()), 20,
				this.getVertexBufferObjectManager()));
		getGoldText().setColor(0f, 1f, 0f);
		
		InhabitantsText = new Text(659, 16, gameFont, "1000", 20,
				this.getVertexBufferObjectManager());
		InhabitantsText.setColor(1f, 1f, 1f);
		setMonthText(new Text(800 - 24, 16, gameFont, "January", 20,
				this.getVertexBufferObjectManager()));

		HUDInhabitants = new HUDInhabitantsButton(605, 0,
				images.getMenuInhabitantsButtonImage(),
				this.getVertexBufferObjectManager(), this);
		HUDInhabitants.setAlpha(0);

		HUDWorkers = new AnimatedSprite(640 - 256 - images
				.getBuildingDescriptionHouseInhabitantsImage().getWidth(),
				2 + 32, images.getHudWorkersImage(),
				this.getVertexBufferObjectManager());
		HUDWorkers.setAlpha(0);
		HUDWorkers.stopAnimation();
		resourcesMenu = new Sprite(0, 69, images.getResourcesMenuImage(),
				this.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(final GLState pGLState, final Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		resourcesMenu.setAlpha(0);
		hudMoreInfoWorkers = new HUDMoreInfoWorkers(640 - 256 - images
				.getBuildingDescriptionHouseInhabitantsImage().getWidth(),
				2 + 32 + 99, images.getMoreInfoImage(),
				this.getVertexBufferObjectManager());
		hudMoreInfoInhabitants = new HUDMoreInfoInhabitants(640 - 256 - images
				.getBuildingDescriptionHouseInhabitantsImage().getWidth(),
				2 + 32 + 197, images.getMoreInfoImage(),
				this.getVertexBufferObjectManager());

		woodResource = new Sprite(4, 0, images.getWoodResourceImage(),
				this.getVertexBufferObjectManager());
		woodResource.setAlpha(0);
		marbleResource = new Sprite(4, 0, images.getMarbleResourceImage(),
				this.getVertexBufferObjectManager());
		marbleResource.setAlpha(0);
		brickResource = new Sprite(4, 0, images.getBrickResourceImage(),
				this.getVertexBufferObjectManager());
		brickResource.setAlpha(0);
		skinResource = new Sprite(4, 0, images.getSkinResourceImage(),
				this.getVertexBufferObjectManager());
		skinResource.setAlpha(0);
		upgradeButton = new UpgradeButton(
				640 - 128 - 48 - images.getBuildingDescriptionHUDImage().getWidth(),
				80 + images.getBuildingDescriptionHUDImage().getHeight(),
				images.getUpgradeButtonImage(),
				this.getVertexBufferObjectManager(), this);
		upgradeButton.setAlpha(0);

		MoreInfoText = new Text(8, 650, smallFont, "", 500,
				this.getVertexBufferObjectManager());
		woodResourceText = new Text(20, 0, smallFont, "", 5,
				this.getVertexBufferObjectManager());
		marbleResourceText = new Text(30, 0, smallFont, "", 5,
				this.getVertexBufferObjectManager());
		brickResourceText = new Text(40, 0, smallFont, "", 5,
				this.getVertexBufferObjectManager());
		skinResourceText = new Text(50, 0, smallFont, "", 5,
				this.getVertexBufferObjectManager());
		messageText = new Text(300 + 8, 256 + 24, smallerFont, "", 5000,
				this.getVertexBufferObjectManager());

		inGameHUD.attachChild(getGoldText());
		inGameHUD.attachChild(InhabitantsText);
		inGameHUD.attachChild(getMonthText());
		inGameHUD.attachChild(HUDInhabitants);
		inGameHUD.attachChild(HUDWorkers);
		// inGameHUD.attachChild(hudMapButton);
		inGameHUD.attachChild(resourcesMenu);
		inGameHUD.attachChild(upgradeButton);
		inGameHUD.attachChild(woodResource);
		inGameHUD.attachChild(brickResource);
		inGameHUD.attachChild(marbleResource);
		inGameHUD.attachChild(skinResource);

		inGameHUD.attachChild(marbleResourceText);
		inGameHUD.attachChild(woodResourceText);
		inGameHUD.attachChild(brickResourceText);
		inGameHUD.attachChild(skinResourceText);
		for (int i = 0; i < 9; i++) {
			stockspacetext = new Text(640 - 256 - images
					.getBuildingDescriptionHUDImage().getWidth() + 128,
					9 + 112 + 18 * i, smallFont, "", 200,
					this.getVertexBufferObjectManager());
			getStockSpaceTexts().add(stockspacetext);
			inGameHUD.attachChild(stockspacetext);
		}

		// All the buttons in the game

		housebutton = new HouseButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				images.getHouseButtonImage(),
				this.getVertexBufferObjectManager(), this);
		barrackButton = new BarrackButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				images.getBarrackButtonImage(),
				this.getVertexBufferObjectManager(), this);
		barrackButton.setAlpha(0);
		roadbutton = new RoadButton(CAMERA_WIDTH - 97 * 2, 67 * 4,
				images.getRoadButtonImage(),
				this.getVertexBufferObjectManager(), this);
		fountainButton = new FountainButton(CAMERA_WIDTH - 97, 67 * 2,
				images.getFountainButtonImage(),
				this.getVertexBufferObjectManager(), this);

		foodMarketButton = new FoodMarketButton(CAMERA_WIDTH - 97, 67 * 3,
				images.getFoodMarketButtonImage(),
				this.getVertexBufferObjectManager(), this);

		theatreButton = new TheatreButton(CAMERA_WIDTH - 97 * 2, 67 * 3,
				images.getTheatreButtonImage(),
				this.getVertexBufferObjectManager(), this);

		stockButton = new StockButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				images.getStockplacebuttonImage(),
				this.getVertexBufferObjectManager(), this);
		siloButton = new SiloButton(CAMERA_WIDTH - 97, 67 * 2,
				images.getSiloButtonImage(),
				this.getVertexBufferObjectManager(), this);

		farmButton = new FarmButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				images.getFarmButtonImage(),
				this.getVertexBufferObjectManager(), this);
		fishingHutButton = new FishingHutButton(CAMERA_WIDTH - 97 * 2, 67 * 5,
				images.getFishingHutButtonImage(),
				this.getVertexBufferObjectManager(), this);
		stoneCutterButton = new StoneCutterButton(CAMERA_WIDTH - 97, 67 * 2,
				images.getStoneCutterButtonImage(),
				this.getVertexBufferObjectManager(), this);
		woodCutterButton = new WoodCutterButton(CAMERA_WIDTH - 97 * 2, 67 * 3,
				images.getWoodCutterButtonImage(),
				this.getVertexBufferObjectManager(), this);
		clayMineButton = new ClayMineButton(CAMERA_WIDTH - 97 * 1, 67 * 5,
				images.getClayButtonImage(),
				this.getVertexBufferObjectManager(), this);
		brickFoundryButton = new BrickFoundryButton(CAMERA_WIDTH - 97 * 2,
				67 * 6, images.getBrickFoundryButtonImage(),
				this.getVertexBufferObjectManager(), this);
		skinnerButton = new SkinnerButton(CAMERA_WIDTH - 97, 67 * 4,
				images.getSkinnerButtonImage(),
				this.getVertexBufferObjectManager(), this);
		butcherButton = new ButcherButton(CAMERA_WIDTH - 97 * 2, 67 * 4,
				images.getButcherButtonImage(),
				this.getVertexBufferObjectManager(), this);

		huntersLodgeButton = new HuntersLodgeButton(CAMERA_WIDTH - 97, 67 * 3,
				images.getHuntersLodgeButtonImage(),
				this.getVertexBufferObjectManager(), this);

		trainingHUD = new Sprite(89, 143, images.getTrainingHUDImage(),
				this.getVertexBufferObjectManager());
		trainingNextButton = new TrainingNextButton(trainingHUD.getX() + 192,
				trainingHUD.getY() + 287, images.getTrainingNextButtonImage(),
				this.getVertexBufferObjectManager(),this);
		trainingPreviousButton = new TrainingPreviousButton(
				trainingHUD.getX() + 63, trainingHUD.getY() + 287,
				images.getTrainingPreviousButtonImage(),
				this.getVertexBufferObjectManager(),this);
		trainingHopliteButton = new Sprite(trainingHUD.getX() + 63,
				trainingHUD.getY() + 64,
				images.getTrainingHopliteButtonImage(),
				this.getVertexBufferObjectManager());
		trainingSlinger = new Sprite(trainingHUD.getX() + 63,
				trainingHUD.getY() + 64, images.getTrainingSlingerImage(),
				this.getVertexBufferObjectManager());
		trainingBuyButton = new TrainingBuyButton(trainingHUD.getX() + 635,
				trainingHUD.getY() + 270, images.getBuybuttonimage(),
				this.getVertexBufferObjectManager(), this);

		inGameHUD.attachChild(housebutton);
		inGameHUD.attachChild(stockButton);
		inGameHUD.attachChild(roadbutton);
		inGameHUD.attachChild(fountainButton);
		inGameHUD.attachChild(farmButton);
		inGameHUD.attachChild(siloButton);
		inGameHUD.attachChild(foodMarketButton);
		inGameHUD.attachChild(theatreButton);
		inGameHUD.attachChild(stoneCutterButton);
		inGameHUD.attachChild(woodCutterButton);
		inGameHUD.attachChild(huntersLodgeButton);
		inGameHUD.attachChild(skinnerButton);
		inGameHUD.attachChild(clayMineButton);
		inGameHUD.attachChild(brickFoundryButton);
		// inGameHUD.attachChild(hudmenuproductionbutton);
		// inGameHUD.attachChild(hudmenuculturebutton);
		inGameHUD.attachChild(fishingHutButton);
		inGameHUD.attachChild(butcherButton);
		inGameHUD.initializeObjects();
		inGameHUD.attachHUD();
		inGameHUD.registerTouchAreas();
		// inGameHUD.registerTouchArea(fishingHutButton);
		// inGameHUD.registerTouchArea(skinnerButton);
		// inGameHUD.registerTouchArea(butcherButton);
		inGameHUD.attachChild(MoreInfoText);
		inGameHUD.attachChild(barrackButton);
		inGameHUD.attachChild(trainingHUD);
		inGameHUD.attachChild(trainingBuyButton);
		inGameHUD.attachChild(trainingNextButton);
		inGameHUD.attachChild(trainingPreviousButton);
		inGameHUD.attachChild(trainingHopliteButton);
		inGameHUD.attachChild(trainingSlinger);
		trainingHUD.setAlpha(0);
		trainingBuyButton.setAlpha(0);
		trainingNextButton.setAlpha(0);
		trainingPreviousButton.setAlpha(0);
		trainingHopliteButton.setAlpha(0);
		trainingSlinger.setAlpha(0);
		inGameHUD.attachChild(messageText);
		inGameHUD.attachChild(moveTextSmall3);

		// Test
		// rect = new Line(0, 0, 32, 0, 3, this.getVertexBufferObjectManager());
		// rect.setColor(1f, 0, 0);
		// inGameHUD.attachChild(rect);

		hudMoreInfoWorkers.setAlpha(0);
		hudMoreInfoInhabitants.setAlpha(0);
		controller.addCityIcons();

		camera.setHUD(inGameHUD);

	}

	/**
	 * Reattachs some objects after exiting and entering the game
	 */
	public void attachStuffEnterGame() {
		/*
		 * for(int i = 0; i < cityIcons.size();i++){
		 * inGameHUD.attachChild(cityIcons.get(i)); }
		 */
	}

	
	

	/**
	 * Remove the in game menu
	 */
	public void removeMenu() {
		if (menuMainMenuButton != null) {
			removeEntity(menuMainMenuButton);
			removeEntity(menuSaveButton);
			removeEntity(rectangleBlackScreen);
			Debug.e("Remove menu");
			inGameHUD.unregisterTouchArea(menuMainMenuButton);
			inGameHUD.unregisterTouchArea(menuSaveButton);
			menuMainMenuButton = null;
			menuSaveButton = null;
			addBuildingTouchAreas();
			addHudTouchAreas();
		}
	}

	/**
	 * Shows the in game menu
	 */
	public void showMenu() {
		if (menuMainMenuButton == null) {
			menuMainMenuButton = new MainMenuButton(396, 256 - 96 - 64,
					images.getMenuMainMenuImage(),
					main.getVertexBufferObjectManager(), main);
			menuSaveButton = new MenuSaveButton(396, 256 + (96 + 64) * 2,
					images.getMenuSaveButtonImage(),
					main.getVertexBufferObjectManager(), main);

			rectangleBlackScreen = new Rectangle(0, 0,
					CAMERA_WIDTH, CAMERA_HEIGHT,
					main.getVertexBufferObjectManager());
			rectangleBlackScreen.setColor(0f, 0f, 0f);
			rectangleBlackScreen.setAlpha(0.95f);
			removeBuildingTouchAreas();
			removeHudTouchAreas();
			inGameHUD.attachChild(rectangleBlackScreen);
			inGameHUD.attachChild(menuMainMenuButton);
			inGameHUD.attachChild(menuSaveButton);
			// sObjects.add(menuQuestButton);
			// sObjects.add(menuMainMenuButton);
			// sObjects.add(menuMapButton);
			inGameHUD.registerTouchArea(menuMainMenuButton);
			inGameHUD.registerTouchArea(menuSaveButton);
		}
	}

	/**
	 * Removes the popup message
	 */
	public void removeMessage() {
		String temp;

		PAUSE = false;
		if (messageHUD != null) {
			removeEntity(messageHUD);
			inGameHUD.unregisterTouchArea(messageHUD);
		}
		if (messageCancelButton != null) {
			removeEntity(messageCancelButton);
			inGameHUD.unregisterTouchArea(messageCancelButton);
		}
		if (messageOkButton != null) {
			removeEntity(messageOkButton);
			inGameHUD.unregisterTouchArea(messageOkButton);
		}
		if (messageConfirmButton != null) {
			removeEntity(messageConfirmButton);
			inGameHUD.unregisterTouchArea(messageConfirmButton);
		}
		if (messageText != null)
			removeEntity(messageText);

	}

	public void removeRecentMessage() {
		for (int i = 0; i < recentMessages.length; i++) {
			String temp = recentMessages[i];
			if (i != recentMessages.length - 1) {
				recentMessages[i] = recentMessages[i + 1];
			} else if (i == recentMessages.length - 1) {
				recentMessages[i - 1] = recentMessages[i];
			}
		}
		for (int i = 0; i < recentMessages.length; i++) {
			if (recentMessages[i] != ""
					&& recentMessages[i] != null) {
				Debug.e("WHY NO LOAD");
				MessagePopUpNoRemove(recentMessages[i],
						org.andengine.util.color.Color.WHITE);
				recentMessages[0] = "";
				break;
			}
		}
	}

	/**
	 * Removes the HUD for recruiting soldiers
	 */
	public void removeTrainingHUD() {
		for (int i = 0; i < trainingTexts.size(); i++) {
			trainingTexts.get(i).remove = true;
		}
		trainingHUD.setAlpha(0);
		trainingNextButton.setAlpha(0);
		trainingPreviousButton.setAlpha(0);
		trainingHopliteButton.setAlpha(0);
		trainingSlinger.setAlpha(0);
		trainingBuyButton.setAlpha(0);
		inGameHUD.unregisterTouchArea(trainingNextButton);
		inGameHUD.unregisterTouchArea(trainingPreviousButton);
		inGameHUD.unregisterTouchArea(trainingBuyButton);

	}

	/**
	 * Adds the HUD for recruiting soldiers
	 */
	public void addTrainingHUD() {
		trainingHUD.setAlpha(1);
		trainingNextButton.setAlpha(1);
		trainingPreviousButton.setAlpha(1);
		trainingSlinger.setAlpha(1);
		trainingBuyButton.setAlpha(1);
		inGameHUD.registerTouchArea(trainingNextButton);
		inGameHUD.registerTouchArea(trainingPreviousButton);
		inGameHUD.registerTouchArea(trainingBuyButton);
		trainingUnitCount = 1;
	}

	/**
	 * Updates the choice of Unit in recruitment
	 */
	public void trainingHUDUpdateUnit() {
		removeTrainingHUDUnits();
		if (trainingUnitCount < 1)
			trainingUnitCount = 1;
		if (trainingUnitCount == 1) {
			trainingSlinger.setAlpha(1);
		} else if (trainingUnitCount == 2) {
			RemoveableText text = new RemoveableText(trainingHUD.getX() + 550,
					trainingHUD.getY() + 96, smallFont, "Coin: "
							+ ConstantBuildings.COSTHOPLITECOIN, 200,
					main.getVertexBufferObjectManager());
			trainingTexts.add(text);
			inGameHUD.attachChild(text);
			text = new RemoveableText(trainingHUD.getX() + 635,
					trainingHUD.getY() + 65, gameFont, "Cost", 200,
					main.getVertexBufferObjectManager());
			trainingTexts.add(text);
			inGameHUD.attachChild(text);
			text = new RemoveableText(trainingHUD.getX() + 550,
					trainingHUD.getY() + 96 + 36, smallFont, "Armor: "
							+ ConstantBuildings.COSTHOPLITEARMOR, 200,
					main.getVertexBufferObjectManager());
			trainingTexts.add(text);
			inGameHUD.attachChild(text);
			text = new RemoveableText(trainingHUD.getX() + 350,
					trainingHUD.getY() + 65, gameFont, "Stats", 200,
					main.getVertexBufferObjectManager());
			trainingTexts.add(text);
			inGameHUD.attachChild(text);
			trainingHopliteButton.setAlpha(1);
		}
	}

	/**
	 * Removes the units in recruitment
	 */
	private void removeTrainingHUDUnits() {
		for (int i = 0; i < trainingTexts.size(); i++) {
			trainingTexts.get(i).remove = true;
		}
		trainingHopliteButton.setAlpha(0);
		trainingSlinger.setAlpha(0);

	}

	public void leaveMap() {
		controller.removeMapTouchAreas();
		addHudTouchAreas();
		inGameHUD.unregisterTouchArea(menuMap);
		menuMapHUD.setAlpha(0);
		menuMap.setAlpha(0);
		moveText.setText("");
		moveTextSmall.setText("");
		moveTextSmall2.setText("");
		for (int i = 0; i < cityMessageSize; i++) {
			removeEntity(cityMessages.get(i));
		}
		cityMessages = new ArrayList<Text>();
		inGameHUD.unregisterTouchArea(menuMap);
		removeEntity(menuMap);
		removeEntity(menuMapHUD);

		for (int i = 0; i < controller.getCityIcons().size(); i++) {
			removeEntity(controller.getCityIcons().get(i));
			controller.getCityIcons().get(i).removeText();
		}
		for (int i = 0; i < cityMessages.size(); i++) {
			cityMessages.get(i).setText("");
		}
		menuMap = null;
		menuMapHUD = null;
	}

	public void removePlaceBuildings() {
		for (int i = 0; i < placeBuildings.size(); i++) {
			if (placeBuildings.get(i) != null) {
				placeBuildings.get(i).remove = true;
				placeBuildings.get(i).remove();
				placeBuildings.remove(placeBuildings.get(i));
			}
		}
	}

	public void changePlaceBuilding(String string) {// 1
		removePlaceBuildings();
		boolplacebuilding = true;
		placeBuildingJumpY = 0;
		placeBuildingJumpX = 0;
		currentBuilding = string;
		if (currentBuilding == "Barrack")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getBarrackImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "House")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getHouseImage(), main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Farm")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getFarmImage(), main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Food Market")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getFoodMarketImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Fountain")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getFountainImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Silo") {
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getSiloImage(), main.getVertexBufferObjectManager(),this);
			placeBuildingJumpY = -11;
		} else if (currentBuilding == "Skinner")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getSkinnerImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Stock")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getStockplaceImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Road")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getRoadimage(), main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Butcher")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getButcherImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Fishing Hut")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getFishingHutImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Stone Cutter")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getStoneCutterImage(),
					main.getVertexBufferObjectManager(),this);
		else if (currentBuilding == "Theatre") {
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getTheatreImage(),
					main.getVertexBufferObjectManager(),this);
			placeBuildingJumpX = 6;
			placeBuildingJumpY = -59;
		} else if (currentBuilding == "Hunters Lodge")
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getHuntersLodgeImage(),
					main.getVertexBufferObjectManager(),this);
		else
			placebuilding = new PlaceBuilding(touchx, touchy,
					images.getWoodCutterImage(),
					main.getVertexBufferObjectManager(),this);

		placeBuildings.add(placebuilding);
		mScene.attachChild(placebuilding);
	}

	public void resetArrayLists() {
		placeBuildings = new ArrayList<PlaceBuilding>();
		setHouses(new ArrayList<House>());
		setRoads(new ArrayList<Road>());
		setFountains(new ArrayList<Fountain>());
		setFarms(new ArrayList<Farm>());
		setSilos(new ArrayList<Silo>());
		setTrees(new ArrayList<Tree>());
		setTheatres(new ArrayList<Theatre>());
		setFoodMarkets(new ArrayList<FoodMarket>());
		setStoneCutters(new ArrayList<StoneCutter>());
		setWoodCutters(new ArrayList<WoodCutter>());
		setStocks(new ArrayList<Stock>());
		setMineDepositClays(new ArrayList<MineDepositClay>());
		setBrickFoundrys(new ArrayList<BrickFoundry>());
		setStocks(new ArrayList<Stock>());
		setStockSpaceTexts(new ArrayList<Text>());
		setFishingHuts(new ArrayList<FishingHut>());
		huntersLodges = new ArrayList<HuntersLodge>();
		setSkinners(new ArrayList<Skinner>());
		Hoplites = new ArrayList<Hoplite>();
		setButchers(new ArrayList<Butcher>());
		fishspots = new ArrayList<FishSpot>();
		setMineDepositBronzes(new ArrayList<MineDepositBronze>());
		asObjects = new ArrayList<AnimatedSpriteObject>();
		sObjects = new ArrayList<SpriteObject>();
		controller.resetGameInfo();
		cityMessages = new ArrayList<Text>();
		setBarracks(new ArrayList<Barrack>());
		removeableTexts = new ArrayList<RemoveableText>();
		trainingTexts = new ArrayList<RemoveableText>();
		detachableObjects = new ArrayList<DetachableObjects>();
		grassTiles = new ArrayList<Sprite>();
		setClayMines(new ArrayList<MineDepositClay>());
		setBrickFoundrys(new ArrayList<BrickFoundry>());
		stockChoiceButtons = new ArrayList<StockChoiceButton>();
	}

	public void createBuildingHUD(String building) {
		removeBuildingHUD();
		if(!building.equals("Empty")){
		buildingHUDTexts = new ArrayList<Text>();
		buildingHUD = new Sprite(0, 102, images.getBuildingHUDImage(),
				main.getVertexBufferObjectManager());
		buildingConfirmChoiceButton = new BuildingConfirmChoiceButton(
				buildingHUD.getX() + 480, buildingHUD.getY() + 368,
				images.getBuildingConfirmChoiceButtonImage(),
				main.getVertexBufferObjectManager(), building, main);
		buildingCancelButton = new BuildingCancelButton(
				buildingHUD.getX() + 768, buildingHUD.getY() + 368,
				images.getBuildingCancelButtonImage(),
				main.getVertexBufferObjectManager(),this);
		inGameHUD.attachChild(buildingHUD);
		inGameHUD.attachChild(buildingCancelButton);
		inGameHUD.attachChild(buildingConfirmChoiceButton);
		inGameHUD.registerTouchArea(buildingConfirmChoiceButton);
		inGameHUD.registerTouchArea(buildingCancelButton);
		buybutton = new BuyButton(CAMERA_WIDTH - 195
				- images.getBuybuttonimage().getWidth(), CAMERA_HEIGHT
				- images.getBuybuttonimage().getHeight(),
				images.getBuybuttonimage(),
				main.getVertexBufferObjectManager(), main);
		cancelButton = new CancelButton(CAMERA_WIDTH - 195
				- images.getBuybuttonimage().getWidth(), CAMERA_HEIGHT
				- (images.getBuybuttonimage().getHeight() * 2),
				images.getCancelbuttonimage(),
				main.getVertexBufferObjectManager(), main);
		inGameHUD.attachChild(buybutton);
		inGameHUD.attachChild(cancelButton);
		inGameHUD.registerTouchArea(buildingHUD);
		inGameHUD.registerTouchArea(buybutton);
		inGameHUD.registerTouchArea(cancelButton);

		Text tempText;
		for (int i = 0; i < 10; i++) {
			tempText = new Text(buildingHUD.getX() + 48, buildingHUD.getY()
					+ 64 + i * 24, smallFont, "", 200,
					main.getVertexBufferObjectManager());
			buildingHUDTexts.add(tempText);
			inGameHUD.attachChild(tempText);
		}
		tempText = new Text(buildingHUD.getX() + 487, buildingHUD.getY() + 48,
				smallFont, "", 200, main.getVertexBufferObjectManager());
		buildingHUDTexts.add(tempText);
		inGameHUD.attachChild(tempText);
		tempText = new Text(buildingHUD.getX() + 487, buildingHUD.getY() + 256,
				smallFont, "", 200, main.getVertexBufferObjectManager());
		buildingHUDTexts.add(tempText);
		inGameHUD.attachChild(tempText);
		tempText = new Text(buildingHUD.getX() + 487, buildingHUD.getY(),
				gameFont, "", 200, main.getVertexBufferObjectManager());
		buildingHUDTexts.add(tempText);
		inGameHUD.attachChild(tempText);
		if (building == "Barrack") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQBARRACK);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSBARRACK);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTBARRACKCOIN);
			buildingHUDTexts.get(3).setText(
					"Marble coins: " + ConstantBuildings.COSTBARRACKMARBLE);
			buildingHUDTexts.get(4).setText(
					"Wood needed: " + ConstantBuildings.COSTBARRACKWOOD);
			buildingHUDTexts.get(5).setText(
					"Brick needed: " + ConstantBuildings.COSTBARRACKBRICK);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONBARRACK);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQBARRACK) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSBARRACK > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold() < ConstantBuildings.COSTBARRACKCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Marble < ConstantBuildings.COSTBARRACKMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (controller.Wood < ConstantBuildings.COSTBARRACKWOOD) {
				canBuild = false;
				buildingHUDTexts.get(4).setColor(1, 0, 0);
			}
			if (controller.Brick < ConstantBuildings.COSTBARRACKBRICK) {
				canBuild = false;
				buildingHUDTexts.get(5).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Armory") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQARMORY);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSARMORY);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTARMORYCOIN);
			buildingHUDTexts.get(3).setText(
					"Marble coins: " + ConstantBuildings.COSTARMORYMARLBE);
			buildingHUDTexts.get(4).setText(
					"Wood used/month "
							+ ConstantBuildings.COSTARMORYWOODMONTHLY);
			buildingHUDTexts.get(5).setText(
					"Brick needed: " + ConstantBuildings.COSTARMORYBRICK);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONARMORY);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQARMORY) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSARMORY > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTARMORYCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Marble < ConstantBuildings.COSTARMORYMARLBE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (controller.Brick < ConstantBuildings.COSTARMORYBRICK) {
				canBuild = false;
				buildingHUDTexts.get(5).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}

		if (building == "Brick Foundry") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQBRICKFOUNDRY);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSBRICKFOUNDRY);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTBRICKFOUNDRYCOIN);
			buildingHUDTexts.get(3).setText(
					"Cost marble: " + ConstantBuildings.COSTBRICKFOUNDRYMARBLE);
			buildingHUDTexts.get(4).setText(
					"Cost wood " + ConstantBuildings.COSTBRICKFOUNDRYWOOD);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONBRICKFOUNDRY);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQBRICKFOUNDRY) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTBRICKFOUNDRYCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSBRICKFOUNDRY > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.Marble < ConstantBuildings.COSTBRICKFOUNDRYMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Butcher") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQBUTCHER);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSBUTCHER);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTBUTCHERCOIN);
			buildingHUDTexts.get(3).setText(
					"Cost wood: " + ConstantBuildings.COSTBUTCHERWOOD);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONBUTCHER);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQBUTCHER) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSBUTCHER > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTBUTCHERCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Wood < ConstantBuildings.COSTBUTCHERWOOD) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Farm") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQFARM);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSFARM);
			buildingHUDTexts.get(10).setText(ConstantBuildings.DESCRIPTIONFARM);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQFARM) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSFARM > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Fishing Hut") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQFISHINGHUT);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSFISHINGHUT);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTFISHINGHUTCOIN);
			buildingHUDTexts.get(3).setText(
					"Cost wood " + ConstantBuildings.COSTFISHINGHUTWOOD);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONFISHINGHUT);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQFISHINGHUT) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSFISHINGHUT > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTFISHINGHUTCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Wood < ConstantBuildings.COSTFISHINGHUTWOOD) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Food Market") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQFOODMARKET);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSFOODMARKET);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTFOODMARKETCOIN);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONFOODMARKET);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQFOODMARKET) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSFOODMARKET > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTFOODMARKETCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Fountain") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQFOUNTAIN);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSFOUNTAIN);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTFOUNTAINCOIN);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONFOUNTAIN);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQFOUNTAIN) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.getGold() < ConstantBuildings.COSTFOUNTAINCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSFOUNTAIN > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "House") {
			canBuild = true;
			buildingHUDTexts.get(1).setText(
					"Cost coins: " + ConstantBuildings.COSTHOUSE);
			buildingHUDTexts.get(10)
					.setText(ConstantBuildings.DESCRIPTIONHOUSE);
			buildingHUDTexts.get(12).setText(building);
			if (controller.getGold()  < ConstantBuildings.COSTHOUSE)
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			if (controller.Inhabitants[0] > controller.workers + 400) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Hunters Lodge") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQHUNTERSLODGE);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSHUNTERSLODGE);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTHUNTERSLODGECOIN);
			buildingHUDTexts.get(3).setText(
					"Cost wood " + ConstantBuildings.COSTHUNTERSLODGEWOOD);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONHUNTERSLODGE);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQHUNTERSLODGE) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTHUNTERSLODGECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSHUNTERSLODGE > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.Wood < ConstantBuildings.COSTHUNTERSLODGEWOOD) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Clay Mine") {
			canBuild = true;
			buildingHUDTexts
					.get(0)
					.setText(
							"House Level: "
									+ ConstantBuildings.HOUSEREQMINEDEPOSITCLAY);
			buildingHUDTexts.get(1).setText(
					"Workers needed: "
							+ ConstantBuildings.WORKERSMINEDEPOSITCLAY);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTCLAYMINECOIN);
			buildingHUDTexts.get(3).setText(
					"Cost marble " + ConstantBuildings.COSTCLAYMINEMARBLE);
			buildingHUDTexts.get(4).setText(
					"Cost wood " + ConstantBuildings.COSTCLAYMINEWOOD);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONMINEDEPOSITCLAY);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQMINEDEPOSITCLAY) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSMINEDEPOSITCLAY > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTCLAYMINECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Marble < ConstantBuildings.COSTCLAYMINEMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (controller.Wood < ConstantBuildings.COSTCLAYMINEWOOD) {
				canBuild = false;
				buildingHUDTexts.get(4).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}

		if (building == "Bronze Mine") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: "
							+ ConstantBuildings.HOUSEREQMINEDEPOSITBRONZE);
			buildingHUDTexts.get(1).setText(
					"Workers needed: "
							+ ConstantBuildings.WORKERSMINEDEPOSITBRONZE);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTBRONZEMINECOIN);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONMINEDEPOSITBRONZE);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQMINEDEPOSITBRONZE) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSMINEDEPOSITBRONZE > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTBRONZEMINECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Marble < ConstantBuildings.COSTBRONZEMINEMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (controller.Brick < ConstantBuildings.COSTBRONZEMINEBRICK) {
				canBuild = false;
				buildingHUDTexts.get(4).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Road") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"Cost coins: " + ConstantBuildings.COSTROADCOIN);
			buildingHUDTexts.get(12).setText(building);
			buildingHUDTexts.get(10).setText(ConstantBuildings.DESCRIPTIONROAD);
			if (controller.getGold() < ConstantBuildings.COSTROADCOIN)
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			if (controller.getGold() < ConstantBuildings.COSTROADCOIN) {
				canBuild = false;
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}

		if (building == "Silo") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQSILO);
			buildingHUDTexts.get(1).setText(
					"Workers needed:  " + ConstantBuildings.WORKERSSILO);
			buildingHUDTexts.get(10).setText(ConstantBuildings.DESCRIPTIONSILO);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQSILO) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSSILO > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTSILO) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Skinner") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQSKINNER);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSSKINNER);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTSKINNERCOIN);
			buildingHUDTexts.get(3).setText(
					"Cost wood " + ConstantBuildings.COSTSKINNERWOOD);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONSKINNER);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQSKINNER) {
				buildingHUDTexts.get(0).setColor(1, 0, 0);
				canBuild = false;
			}
			if (controller.workers + ConstantBuildings.WORKERSSKINNER > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold() < ConstantBuildings.COSTSKINNERCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Wood < ConstantBuildings.COSTSKINNERWOOD) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Stock") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQSTOCK);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSSTOCK);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTSTOCKCOIN);
			buildingHUDTexts.get(10)
					.setText(ConstantBuildings.DESCRIPTIONSTOCK);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQSTOCK) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSSTOCK > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTSTOCKCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Stone Cutter") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQSTONECUTTER);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSSTONECUTTER);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTSTONECUTTERCOIN);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONSTONECUTTER);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQSTONECUTTER) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSSTONECUTTER > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTSTONECUTTERCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Theatre") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQTHEATRE);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSTHEATRE);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTTHEATRECOIN);
			buildingHUDTexts.get(3).setText(
					"Cost marble " + ConstantBuildings.COSTTHEATREMARBLE);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONTHEATRE);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQTHEATRE) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSTHEATRE > controller
					.getMaxWorkers())
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			if (controller.getGold()  < ConstantBuildings.COSTTHEATRECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (controller.Marble < ConstantBuildings.COSTTHEATREMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		if (building == "Wood Cutter") {
			canBuild = true;
			buildingHUDTexts.get(0).setText(
					"House Level: " + ConstantBuildings.HOUSEREQWOODCUTTER);
			buildingHUDTexts.get(1).setText(
					"Workers needed: " + ConstantBuildings.WORKERSWOODCUTTER);
			buildingHUDTexts.get(2).setText(
					"Cost coins: " + ConstantBuildings.COSTWOODCUTTERCOIN);
			buildingHUDTexts.get(10).setText(
					ConstantBuildings.DESCRIPTIONWOODCUTTER);
			buildingHUDTexts.get(12).setText(building);
			if (controller.HouseLevel < ConstantBuildings.HOUSEREQWOODCUTTER) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (controller.workers + ConstantBuildings.WORKERSWOODCUTTER > controller
					.getMaxWorkers()) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (controller.getGold()  < ConstantBuildings.COSTWOODCUTTERCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (!canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
		}
		else{
			buildingDescriptionHUD = new BuildingDescriptionHUD(0, 69,
					images.getBuildingDescriptionHUDImage(),
					this.getVertexBufferObjectManager(), this, "Empty","Quest");
		}
	}

	public void removeBuildingHUD() {
		if (cancelButton != null) {
			cancelButton.Cancel();
		}
		
		for (int i = 0; i < stockChoiceButtons.size(); i++) {
			if (stockChoiceButtons.get(i) != null) {
				removeEntity(stockChoiceButtons.get(i));
				inGameHUD.unregisterTouchArea(stockChoiceButtons.get(i));
			}
		}
		stockChoiceButtons = new ArrayList<StockChoiceButton>();
		if (buildingHUD != null) {
			removeEntity(buildingHUD);
			removeEntity(buildingConfirmChoiceButton);
			inGameHUD.unregisterTouchArea(buildingConfirmChoiceButton);
			removeEntity(buildingCancelButton);
			inGameHUD.unregisterTouchArea(buildingCancelButton);
			for (int i = 0; i < 13; i++) {
				if (buildingHUDTexts.get(i) != null)
					removeEntity(buildingHUDTexts.get(i));
			}
		}
	}

	public void removeBuildingHUDKeepBuyButton() {

		for (int i = 0; i < stockChoiceButtons.size(); i++) {
			if (stockChoiceButtons.get(i) != null) {
				removeEntity(stockChoiceButtons.get(i));
				inGameHUD.unregisterTouchArea(stockChoiceButtons.get(i));
			}
		}
		stockChoiceButtons = new ArrayList<StockChoiceButton>();
		if (buildingHUD != null) {
			removeEntity(buildingHUD);
			removeEntity(buildingConfirmChoiceButton);
			inGameHUD.unregisterTouchArea(buildingConfirmChoiceButton);
			removeEntity(buildingCancelButton);
			inGameHUD.unregisterTouchArea(buildingCancelButton);
			for (int i = 0; i < 13; i++) {
				if (buildingHUDTexts.get(i) != null)
					removeEntity(buildingHUDTexts.get(i));
			}
		}
	}

	/**
	 * Creates a toast msg
	 * 
	 */
	public void makeToast(String Msg) {
		message = Msg;
		toastTime = Toast.LENGTH_SHORT;
		Handles.sendEmptyMessage(0);
	}

	/**
	 * Creates a toast msg
	 * 
	 */
	public void makeToast(String Msg, int time) {
		message = Msg;
		toastTime = time;
		Handles.sendEmptyMessage(0);
	}

	static Handler Handles = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (msg.what == 0) {
				Toast.makeText(main, message, toastTime).show();
			}
		};
	};
	public static boolean removeBuildings = false;
	public static int bribe = 0;
	public static boolean menuIncomeOpen = false;

	public void addStockchoices() {
		StockChoiceButton temp = new StockChoiceButton(
				buildingHUD.getX() + 32,
				buildingHUD.getY() + 431
						- images.getStockChoiceWoodImage().getHeight() * 2,
				images.getStockChoiceMarbleImage(),
				main.getVertexBufferObjectManager(), "Marble");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(buildingHUD.getX() + 32
				+ images.getStockChoiceWoodImage().getWidth(),
				buildingHUD.getY() + 431
						- images.getStockChoiceWoodImage().getHeight() * 2,
				images.getStockChoiceWoodImage(),
				main.getVertexBufferObjectManager(), "Wood");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(buildingHUD.getX() + 32
				+ images.getStockChoiceWoodImage().getWidth() * 2,
				buildingHUD.getY() + 431
						- images.getStockChoiceWoodImage().getHeight(),
				images.getStockChoiceBrickImage(),
				main.getVertexBufferObjectManager(), "Brick");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(buildingHUD.getX() + 32
				+ images.getStockChoiceWoodImage().getWidth() * 4,
				buildingHUD.getY() + 431
						- images.getStockChoiceWoodImage().getHeight(),
				images.getStockChoiceAllImage(),
				main.getVertexBufferObjectManager(), "");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(buildingHUD.getX() + 32
				+ images.getStockChoiceWoodImage().getWidth() * 3,
				buildingHUD.getY() + 431
						- images.getStockChoiceWoodImage().getHeight(),
				images.getStockChoiceArmorImage(),
				main.getVertexBufferObjectManager(), "Armor");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(buildingHUD.getX() + 32,
				buildingHUD.getY() + 431
						- images.getStockChoiceWoodImage().getHeight(),
				images.getStockChoiceSkinImage(),
				main.getVertexBufferObjectManager(), "Skin");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(buildingHUD.getX() + 32
				+ images.getStockChoiceWoodImage().getWidth() * 1,
				buildingHUD.getY() + 431
						- images.getStockChoiceWoodImage().getHeight(),
				images.getStockChoiceClayImage(),
				main.getVertexBufferObjectManager(), "Clay");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
	}

	public void removeSpriteObject() {
		SpriteObject obj = (SpriteObject) ID;
		obj.removeEntity();
	}

	public void removeAnimatedSpriteObject() {
		AnimatedSpriteObject obj = (AnimatedSpriteObject) ID;
		obj.removeEntity();
	}

	public void updateMessageHistory(String str) {

		for (int i = messageHistory.length - 1; i >= 0; i--) {
			if (str.equals(messageHistory[0]))
				break;
			if (i == messageHistory.length) {
				messageHistory[i] = "";
			} else if (i == 0) {
				messageHistory[i] = str;
			} else {
				messageHistory[i] = messageHistory[i - 1];
			}
		}
		messageHistory[0] = str;
	}

	public void cancelButtonCancel() {
		cancelButton.Cancel();
	}

	

	public void closeMenus() {
		if (menuIncomeOpen)
			inGameHUD.getIncomeButton().close();
//		if (menuResourcesOpen)
//			HUDResourceMenuButton.Cancel();
	}

	public void removeHudTouchAreas() {
		inGameHUD.unregisterTouchAreas();
		menu = "";
		unRegisterBuildingButtons();

	}

	public void addHudTouchAreas() {
		inGameHUD.registerTouchAreas();
	}

	public void removeCityIconTexts() {
		for (int i = 0; i < controller.getCityIcons().size(); i++) {
			controller.getCityIcons().get(i).removeSoldierText();
		}
	}

	public  void finishBuy() {
		if (cancelButton != null) {
			inGameHUD.unregisterTouchArea(cancelButton);
			removeEntity(cancelButton);
			cancelButton = null;
		}
		if (buybutton != null) {
			inGameHUD.unregisterTouchArea(buybutton);
			removeEntity(buybutton);
			buybutton = null;
		}
	}

	public void RemoveResources(String kind, int ammount) {
		for (int i = 0; i < getStocks().size(); i++) {
			if (kind == "Marble") {
				if (getStocks().get(i).Marble >= ammount) {
					getStocks().get(i).Marble -= ammount;
					getStocks().get(i).removeResource(kind, ammount);
					controller.Marble -= ammount;
					break;
				}
			}
			if (kind == "Wood") {
				if (getStocks().get(i).Wood >= ammount) {
					getStocks().get(i).Wood -= ammount;
					Debug.e("GOGOGO");
					controller.Wood -= ammount;
					getStocks().get(i).removeResource(kind, ammount);
					break;
				}
			}
			if (kind == "Brick") {
				if (getStocks().get(i).Brick >= ammount) {
					getStocks().get(i).Brick -= ammount;
					getStocks().get(i).removeResource(kind, ammount);
					controller.Brick -= ammount;
					break;
				}
			}
			if (kind == "Bronze") {
				if (getStocks().get(i).Bronze >= ammount) {
					getStocks().get(i).Bronze -= ammount;
					getStocks().get(i).removeResource(kind, ammount);
					controller.Bronze -= ammount;
					break;
				}
			}

			if (kind == "Skin") {
				if (getStocks().get(i).Skin >= ammount) {
					getStocks().get(i).Skin -= ammount;
					getStocks().get(i).removeResource(kind, ammount);
					controller.Skin -= ammount;
					break;
				}
			}
			if (kind == "Armor") {
				if (getStocks().get(i).Armor >= ammount) {
					getStocks().get(i).Armor -= ammount;
					getStocks().get(i).removeResource(kind, ammount);
					controller.Armor -= ammount;
					break;
				}
			}
			if (kind == "Clay") {
				if (getStocks().get(i).Clay >= ammount) {
					getStocks().get(i).Clay -= ammount;
					getStocks().get(i).removeResource(kind, ammount);
					controller.Clay -= ammount;
					break;
				}
			}

		}
	}

	/**
	 * Rearrange all sprites
	 */
	public void updateScreen(){
		controller.updateScreen(sObjects,asObjects,this.getScene());
	}
	
	public void RemoveResources(String kind, int ammount, Stock stock) {
		if (kind == "Marble") {
			if (stock.Marble >= ammount) {
				stock.Marble -= ammount;
				stock.removeResource(kind, ammount);
				controller.Marble -= ammount;
			}
		}
		if (kind == "Wood") {
			if (stock.Wood >= ammount) {
				stock.Wood -= ammount;
				controller.Wood -= ammount;
				stock.removeResource(kind, ammount);
			}
		}
		if (kind == "Brick") {
			if (stock.Brick >= ammount) {
				stock.Brick -= ammount;
				stock.removeResource(kind, ammount);
				controller.Brick -= ammount;
			}
		}
		if (kind == "Bronze") {
			if (stock.Bronze >= ammount) {
				stock.Bronze -= ammount;
				stock.removeResource(kind, ammount);
				controller.Bronze -= ammount;
			}
		}

		if (kind == "Skin") {
			if (stock.Skin >= ammount) {
				stock.Skin -= ammount;
				stock.removeResource(kind, ammount);
				controller.Skin -= ammount;
			}
		}
		if (kind == "Armor") {
			if (stock.Armor >= ammount) {
				stock.Armor -= ammount;
				stock.removeResource(kind, ammount);
				controller.Armor -= ammount;
			}
		}
		if (kind == "Clay") {
			if (stock.Clay >= ammount) {
				stock.Clay -= ammount;
				stock.removeResource(kind, ammount);
				controller.Clay -= ammount;
			}
		}
	}

	/**
	 * Create the HUD for our building description
	 */
	public void addBuildingDescription(String title, String detail) {
		if (buildingDescriptionHUD != null) {
			buildingDescriptionHUD.remove();
		}
		buildingDescriptionHUD = new BuildingDescriptionHUD(0, 69,
				images.getBuildingDescriptionHUDImage(),
				this.getVertexBufferObjectManager(), this, title, detail);

	}

	/**
	 * @return current scene
	 */
	public Scene getScene() {
		return this.mScene;
	}

	public HUD getInGameHUD() {
		return inGameHUD;
	}

	public MainMenuButton getMenuMainMenuButton() {
		return menuMainMenuButton;
	}

	public MenuSaveButton getMenuSaveButton() {
		return menuSaveButton;
	}

	public Rectangle getRectangleBlackScreen() {
		return rectangleBlackScreen;
	}

	public void setMenuNull() {
		menuMainMenuButton = null;
		menuSaveButton = null;
	}

	public HUD getBattleHUD() {
		return this.battleHUD;
	}

	public ArrayList<Text> getCityMessages() {
		return this.cityMessages;
	}

	public MainMenuPlayButton getMainMenuPlayButton() {
		return mainMenuPlayButton;
	}

	public void setMainMenuPlayButton(
			MainMenuPlayButton mainMenuPlayButton) {
		this.mainMenuPlayButton = mainMenuPlayButton;
	}

	public  MainMenuLoadButton getMainMenuLoadButton() {
		return mainMenuLoadButton;
	}

	public void setMainMenuLoadButton(MainMenuLoadButton mainMenuLoadButton) {
		this.mainMenuLoadButton = mainMenuLoadButton;
	}

	public Sprite getMainMenuBackground() {
		return mainMenuBackground;
	}

	public void setMainMenuBackground(Sprite mainMenuBackground) {
		this.mainMenuBackground = mainMenuBackground;
	}

	public AnimatedSprite getMainMenuDoor() {
		return mainMenuDoor;
	}

	public void setMainMenuDoor(AnimatedSprite mainMenuDoor) {
		this.mainMenuDoor = mainMenuDoor;
	}

	public Sprite getAresTemple() {
		return aresTemple;
	}

	public void setAresTemple(Sprite aresTemple) {
		this.aresTemple = aresTemple;
	}

	public Sprite getMouse() {
		return mouse;
	}

	public void setMouse(Sprite mouse) {
		this.mouse = mouse;
	}

	public Controller getController() {
		return controller;
	}

	public Mission getCheckMission() {
		return controller.getMission();
	}

	public String getMap() {
		return this.map;
	}

	public ArrayList<House> getHouses() {
		return houses;
	}

	public void setHouses(ArrayList<House> houses) {
		this.houses = houses;
	}

	public Text getMonthText() {
		return monthText;
	}

	public void setMonthText(Text monthText) {
		this.monthText = monthText;
	}

	public ArrayList<Farm> getFarms() {
		return farms;
	}

	public void setFarms(ArrayList<Farm> farms) {
		this.farms = farms;
	}

	public Text getGoldText() {
		return goldText;
	}

	public void setGoldText(Text goldText) {
		this.goldText = goldText;
	}

	public ArrayList<Barrack> getBarracks() {
		return barracks;
	}

	public void setBarracks(ArrayList<Barrack> barracks) {
		this.barracks = barracks;
	}

	public ArrayList<Butcher> getButchers() {
		return butchers;
	}

	public void setButchers(ArrayList<Butcher> butchers) {
		this.butchers = butchers;
	}

	public ArrayList<Skinner> getSkinners() {
		return skinners;
	}

	public void setSkinners(ArrayList<Skinner> skinners) {
		this.skinners = skinners;
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}

	public ArrayList<Theatre> getTheatres() {
		return theatres;
	}

	public void setTheatres(ArrayList<Theatre> theatres) {
		this.theatres = theatres;
	}

	public ArrayList<Fountain> getFountains() {
		return fountains;
	}

	public void setFountains(ArrayList<Fountain> fountains) {
		this.fountains = fountains;
	}

	public ArrayList<MineDepositClay> getMineDepositClays() {
		return mineDepositClays;
	}

	public void setMineDepositClays(ArrayList<MineDepositClay> mineDepositClays) {
		this.mineDepositClays = mineDepositClays;
	}

	public ArrayList<MineDepositBronze> getMineDepositBronzes() {
		return mineDepositBronzes;
	}

	public void setMineDepositBronzes(
			ArrayList<MineDepositBronze> mineDepositBronzes) {
		this.mineDepositBronzes = mineDepositBronzes;
	}

	public ArrayList<BrickFoundry> getBrickFoundrys() {
		return brickFoundrys;
	}

	public void setBrickFoundrys(ArrayList<BrickFoundry> brickFoundrys) {
		this.brickFoundrys = brickFoundrys;
	}

	public ArrayList<FoodMarket> getFoodMarkets() {
		return foodMarkets;
	}

	public void setFoodMarkets(ArrayList<FoodMarket> foodMarkets) {
		this.foodMarkets = foodMarkets;
	}

	public ArrayList<Silo> getSilos() {
		return silos;
	}

	public void setSilos(ArrayList<Silo> silos) {
		this.silos = silos;
	}

	public ArrayList<StoneCutter> getStoneCutters() {
		return StoneCutters;
	}

	public void setStoneCutters(ArrayList<StoneCutter> stoneCutters) {
		this.StoneCutters = stoneCutters;
	}

	public ArrayList<WoodCutter> getWoodCutters() {
		return woodCutters;
	}

	public void setWoodCutters(ArrayList<WoodCutter> woodCutters) {
		this.woodCutters = woodCutters;
	}

	public ArrayList<FishingHut> getFishingHuts() {
		return fishingHuts;
	}

	public void setFishingHuts(ArrayList<FishingHut> fishingHuts) {
		this.fishingHuts = fishingHuts;
	}

	public BuyButton getBuybutton() {
		return this.buybutton;
	}

	public Text getInhabitantsText() {
		return this.InhabitantsText;
	}

	public ArrayList<MineDepositClay> getClayMines() {
		return clayMines;
	}

	public void setClayMines(ArrayList<MineDepositClay> clayMines) {
		this.clayMines = clayMines;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	public ArrayList<Text> getStockSpaceTexts() {
		return stockSpaceTexts;
	}

	public void setStockSpaceTexts(ArrayList<Text> stockSpaceTexts) {
		this.stockSpaceTexts = stockSpaceTexts;
	}

	public ArrayList<Tree> getTrees() {
		return trees;
	}

	public void setTrees(ArrayList<Tree> trees) {
		this.trees = trees;
	}

	public House getHouseexample() {
		return Houseexample;
	}

	public void setHouseexample(House houseexample) {
		this.Houseexample = houseexample;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Skinner getSkinner() {
		return skinner;
	}

	public void setSkinner(Skinner skinner) {
		this.skinner = skinner;
	}

	public MessageConfirmButton getMessageConfirmButton() {
		return this.messageConfirmButton;
	}

	public ArrayList<MarbleTile> getMarbleTiles() {
		return marbleTiles;
	}

	public BuildableBitmapTextureAtlas getGameAtlas() {
		return GameAtlas;
	}

	public ResourceImage getImages() {
		return images;
	}

	public void createHouseDescriptionHUD(House house) {
		if (this.buildingDescriptionHUD != null) {
			buildingDescriptionHUD.remove();
		}
		buildingDescriptionHUD = new BuildingDescriptionHUD(0, 69,
				images.getBuildingDescriptionHUDImage(),
				this.getVertexBufferObjectManager(), this, house);
	}

	public IFont getGameFont() {
		return gameFont;
	}

	public IFont getSmallerFont() {
		return this.smallerFont;
	}

	public void removeBuildingDescriptionHUD() {
		if (this.buildingDescriptionHUD != null) {
			buildingDescriptionHUD.remove();
		}
	}

	public void setBuildingDescriptionHUD(
			BuildingDescriptionHUD buildingDescriptionHUD2) {
		this.buildingDescriptionHUD = buildingDescriptionHUD2;
	}

	public IFont getSmallFont() {
		return this.smallFont;
	}

	public String getCurrentBuilding() {
		return this.currentBuilding;
	}

	public int getGold() {
		return controller.getGold();
	}

	public AnimatedSprite getHUDWorkesr() {
		return this.HUDWorkers;
	}

	public MenuQuestButton getMenuQuestButton() {
		return inGameHUD.getHudObjectivesButton();
	}

	public int getObjectAmount() {
		return objectAmount;
	}

	public void setObjectAmount(int i) {
		objectAmount = i;
	}

	public ArrayList<AnimatedSpriteObject> getAsObjects() {
		return this.asObjects;
	}

	public ArrayList<SpriteObject> getSObjects() {
		return this.sObjects;
	}

	public int getStartID() {
		return this.startID;
	}

	public BuildingDescriptionHUD getBuildingDescriptionHUD() {
		return buildingDescriptionHUD;
	}

	public String getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(String currentMenu) {
		this.currentMenu = currentMenu;
	}

	
}