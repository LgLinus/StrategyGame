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

import huds.InGameMainHUD;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
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
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import other.DataBase;
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
import com.ligr.strategygame.Buttons.BuildingDescriptionCancel;
import com.ligr.strategygame.Buttons.BuyButton;
import com.ligr.strategygame.Buttons.CancelButton;
import com.ligr.strategygame.Buttons.CityIcon;
import com.ligr.strategygame.Buttons.HUDChatButton;
import com.ligr.strategygame.Buttons.HUDInhabitantsButton;
import com.ligr.strategygame.Buttons.HUDMilitaryButton;
import com.ligr.strategygame.Buttons.HUDMoreInfoInhabitants;
import com.ligr.strategygame.Buttons.HUDMoreInfoWorkers;
import com.ligr.strategygame.Buttons.HUDResourceMenuButton;
import com.ligr.strategygame.Buttons.HudMapButton;
import com.ligr.strategygame.Buttons.HudRemoveBuildingButton;
import com.ligr.strategygame.Buttons.MainMenuButton;
import com.ligr.strategygame.Buttons.MainMenuLoadButton;
import com.ligr.strategygame.Buttons.MainMenuPlayButton;
import com.ligr.strategygame.Buttons.MenuBattleReturnButton;
import com.ligr.strategygame.Buttons.MenuButton;
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
import com.ligr.strategygame.Buttons.Buildings.HUDIncomeButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuCollectButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuCultureButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuMilitaryButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuProductionButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuStorageButton;
import com.ligr.strategygame.Buttons.Buildings.HUDMenuUtilityButton;
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
import com.ligr.strategygame.maptiles.ClayTile;
import com.ligr.strategygame.maptiles.MarbleTile;
import com.ligr.strategygame.maptiles.Tree;
import com.ligr.strategygame.npc.Hoplite;

import constants.Constant;
import constants.ConstantBuildings;

public class MainActivity extends SimpleBaseGameActivity implements
		IOnSceneTouchListener, IScrollDetectorListener,
		IPinchZoomDetectorListener {

	private SurfaceScrollDetector mScrollDetector;
	private PinchZoomDetector mPinchZoomDetector;
	private float mPinchZoomStartedCameraZoomFactor;
	private int monthInDebt = 0;
	public static String[] recentMessages = new String[20];
	public static int placeBuildingJumpX = 0;
	public static int placeBuildingJumpY = 0;
	private Mission checkMission;
	public ArrayList<RectangleModified> rectanglesModified = new ArrayList<RectangleModified>();
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
	private static final int CAMERA_HEIGHT = 720; // Defines the height of the
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

	// Keeps track of what menu we are at
	public static String Menu = "Game";

	// Variables for the previous location where we pressed
	private static float mouseprevx = CAMERA_WIDTH / 2;
	private static float mouseprevy = CAMERA_HEIGHT / 2;

	public static final float GRIDSIZE = 48;
	public static ITextureRegion clayMineImage;

	public static String Map = "Map1"; // Tells us which map we are playing on
	public static int Month = 1; // Current month
	public static int Year = 2000; // Current year
	public static float GameSpeed = 5f; // The gamespeed, decides how often we
										// want to update the months

	public static int Workers = 0; // Total ammount of workers
	public static int militaryWorkers = 0;
	public static int buildingWorkers = 0;
	public static int MaxWorkers = 0; // The maximum ammount of workers we need
	public static int Inhabitants[] = { 0, 0, 0, 0, 0, 0, 0 }; // The ammount of
																// inhabitants
	public static int InhabitantsSize = 7;
	public static int CurrentMission = 1; // Defines the current mission
	public static int HouseLevel = 1; // Keeps track on what kind of housing
										// levels we have reached
	public static int tempID = 0; // Temporary ids that we store for different
									// reasons

	/*
	 * military strength
	 */

	public static int militaryHoplite = 0;
									public static int militarySlinger = 0;
									public static int militaryHopliteWar = 0;
									public static final int militarySlingerWar = 0;
	
	// Various resources which are used in the game
	public static int Marble = 0,Wood = 0, Armor = 0,Skin = 0,Sculptures = 0;
	public static int Fish = 0,Bronze = 0,Brick = 0,Meat = 0,Clay = 0;

	TimerHandler TimerHandlerMonthly; // The timer that updates each month

	private static int startGold = 10000;
	public static int Gold = startGold; // Total ammount of gold

	private static Text GoldText; // Text that is changed to the ammount of gold
									// we have
	public static Text InhabitantsText;
	public static Text buildingDescriptionTitle;
	public static Text buildingDescriptionDetail;
	public static Text buildingDescriptionHouseInhabitantsText;
	public static Text buildingDescriptionHouseWheatText;
	public static Text buildingDescriptionHouseWaterText;
	public static Text buildingDescriptionHouseCultureText;
	public static Text buildingDescriptionHouseSkinText;
	public static Text HUDWorkersText;
	public static Text HUDWorkersDescriptionText;
	public static Text MoreInfoText;
	public static Text MonthText;
	public static Text woodResourceText;
	public static Text marbleResourceText;
	public static Text brickResourceText;
	public static Text skinResourceText;
	public static Text menuQuestText;
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

	public static final InGameMainHUD inGameHUD = new InGameMainHUD();
	private static final int HOUSEREQROAD = 1;
	public static ArrayList<ClayTile> ClayTiles;
	ITextureRegion inGameHUDImage;

	private static Sprite inGameHUDSprite;
	private Sprite mouse;
	public static Sprite buildingDescriptionHUD;
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
	public static BuildingDescriptionCancel buildingDescriptionCancel;
	public static FountainButton fountainButton;
	public static MenuQuestButton menuQuestButton;
	public static FarmButton farmButton;
	public static Fountain fountain;
	public static Farm farm;
	public static SiloButton siloButton;
	public static StoneCutterButton stoneCutterButton;
	public static Tree tree;
	public static Sprite buildingHUD;
	public static ITextureRegion buildingHUDImage;
	public static BuildingConfirmChoiceButton buildingConfirmChoiceButton;
	public static BuildingCancelButton buildingCancelButton;
	public static HuntersLodgeButton huntersLodgeButton;
	private static FishingHutButton fishingHutButton;
	public static HUDMoreInfoWorkers hudMoreInfoWorkers;
	public static HUDMoreInfoInhabitants hudMoreInfoInhabitants;
	private static Sprite mainMenuBackground;
	public static Line rect;
	public Sprite aresTemple;
	private ITextureRegion buildingDescriptionHUDImage;
	private ITextureRegion cancelImage;
	private ITextureRegion houseButtonImage;
	private static ITextureRegion mainMenuBackgroundImage;
	public static ITextureRegion fountainImage;
	public static ITextureRegion menuMapHUDImage;
	public static ITiledTextureRegion farmImage;
	public static ITextureRegion farmButtonImage;
	public static ITiledTextureRegion hudWorkersImage;
	public static ITextureRegion theatreImage;
	public static ITiledTextureRegion stoneCutterImage;
	public static ITiledTextureRegion HopliteImage;
	public static ITiledTextureRegion stockImage;
	public static ITiledTextureRegion HouseImage;
	public static ITiledTextureRegion fishSpotImage;
	private TextureRegion resourcesMenuImage;
	public static ITiledTextureRegion placebuildingimage;
	public static ITiledTextureRegion boatImage;
	public static ITiledTextureRegion npcStoneCutter;
	private ITiledTextureRegion treeImage;
	private ITextureRegion buildingDescriptionHouseWaterImage;
	private ITextureRegion buildingDescriptionHouseWheatImage;
	private ITextureRegion buildingDescriptionHouseCultureImage;
	private ITextureRegion buildingDescriptionHouseSkinImage;
	private ITextureRegion buildingDescriptionHouseInhabitantsImage;
	private ITextureRegion upgradeButtonImage;
	private ITextureRegion MoreInfoImage;
	public static  ITextureRegion hudMenuCollect;
	public static  ITextureRegion hudMenuUtility;
	private ITextureRegion hudMenuCulture;
	private ITextureRegion hudMenuProduction;
	private ITextureRegion trainingHUDImage;
	private ITextureRegion trainingNextButtonImage;
	private ITextureRegion trainingPreviousButtonImage;
	private ITextureRegion trainingBuyButtonImage;
	public static  ITextureRegion hudMenuStorage;
	private ITextureRegion siloButtonImage;
	private ITextureRegion fountainButtonImage;
	public static ITextureRegion menuQuestButtonImage;
	private static ITextureRegion menuMainMenuImage;
	private static ITextureRegion menuSaveButtonImage;
	public static ITextureRegion menuMapImage;
	public static ITextureRegion skinnerImage;
	public static MenuSaveButton menuSaveButton;
	private ITextureRegion stockButtonImage;
	private ITextureRegion huntersLodgeButtonImage;
	private ITextureRegion woodCutterButtonImage;
	private ITextureRegion skinnerButtonImage;
	public static ITextureRegion cancelbuttonimage;
	public static ITextureRegion mainMenuPlayButtonImage;
	private static ITextureRegion buybuttonimage;
	private ITextureRegion woodResourceImage;
	private ITextureRegion brickResourceImage;
	private ITextureRegion skinResourceImage;
	private ITextureRegion marbleResourceImage;
	private ITextureRegion theatreButtonImage;
	public static ITextureRegion menuButtonImage;
	private ITextureRegion grassTileImage;


	public static CustomScene mScene;

	BuildableBitmapTextureAtlas GameAtlas;

	public static ArrayList<PlaceBuilding> placeBuildings;
	public static ArrayList<Text> stockSpaceTexts;
	public static ArrayList<MineDepositBronze> mineDepositBronzes;
	public static ArrayList<Fountain> Fountains;
	public static ArrayList<Farm> Farms;
	public static ArrayList<Tree> Trees;
	public static ArrayList<Theatre> Theatres;
	public static ArrayList<Sprite> grassTiles;
	public static ArrayList<Stock> Stocks;
	public static ArrayList<AnimatedSprite> animatedSprites;
	public static ArrayList<Barrack> barracks;
	public static ArrayList<MarbleTile> marbleTiles;
	public static Theatre theatre;
	public static House Houseexample;
	public static ArrayList<House> Houses;
	public static ArrayList<Skinner> skinners;
	public static Skinner skinner;
	public static ArrayList<Butcher> butchers;
	public static Butcher butcher;
	public static ITextureRegion roadimage;
	public static ITiledTextureRegion fishingHutImage;
	public static FishingHut fishinghut;
	public static ArrayList<FishingHut> FishingHuts;
	public static ArrayList<MineDepositClay> clayMines;
	public static Road road;
	public static ArrayList<Road> Roads;
	public static Silo silo;
	public static ArrayList<Silo> Silos;
	public static ITextureRegion siloImage;
	public static FoodMarket foodmarket;
	public static ArrayList<FoodMarket> FoodMarkets;
	public static ITextureRegion FoodMarketImage;
	public static ArrayList<StoneCutter> StoneCutters;
	public static StoneCutter stonecutter;
	public static ITextureRegion woodCutterImage;
	public static ArrayList<WoodCutter> WoodCutters;
	public static WoodCutter woodcutter;
	public static ITextureRegion huntersLodgeImage;
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
	public static CancelButton cancelbutton;
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
	public static String buildingDescriptionTitleString = "";
	public static String buildingDescriptionDetailString = "";
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
	private ITextureRegion marbleImage;
	private ITextureRegion stockplacebuttonImage;
	private FishSpot fishspot;
	private TimerHandler TimerHandlerPause;
	private static TextureRegion menuMapButtonImage;
	private TextureRegion cityIconImage;
	public static ArrayList<CityIcon> cityIcons;
	private CityIcon cityIcon;
	public static int cityMessageSize = 10;
	private ITextureRegion coinSymbolImage;
	private TextureRegion menuInhabitantsButtonImage;
	private static ITextureRegion messageOkButtonImage;
	private TextureRegion foodMarketButtonImage;
	private TextureRegion stoneCutterButtonImage;
	private TextureRegion roadButtonImage;
	private TextureRegion barrackButtonImage;
	public  static ITextureRegion hudMenuMilitaryImage;
	private TiledTextureRegion hopliteImage;
	private TiledTextureRegion DeerImage;
	private TextureRegion trainingHopliteButtonImage;
	private TextureRegion trainingSlingerImage;
	public static ArrayList<MineDepositClay> mineDepositClays;
	public static ArrayList<BrickFoundry> brickFoundrys;
	public static ITextureRegion brickFoundryImage;
	private TextureRegion mineDepositClayImage;
	public static TextureRegion hudResourcesButtonImage;
	private static ITextureRegion mainMenuLoadButtonImage;
	private static MainMenuLoadButton mainMenuLoadButton;
	private TextureRegion stockChoiceWoodImage;
	private TextureRegion stockChoiceAllImage;
	private TextureRegion stockChoiceBrickImage;
	private TextureRegion stockChoiceMarbleImage;
	private TextureRegion stockChoiceArmorImage;
	private TextureRegion stockChoiceClayImage;
	private TextureRegion stockChoiceSkinImage;
	private TiledTextureRegion marbleTileImage;
	public static TextureRegion hudRemoveBuildingButtonImage;
	public static TextureRegion hudMapButtonImage;
//	public static HudMapButton hudMapButton;
	public static TextureRegion incomeButtonImage;
	private TextureRegion brickFoundryButtonImage;
	public static TextureRegion hudChatButtonImage;
	private TextureRegion clayButtonImage;
	private TextureRegion clayBuildingImage;
	private TiledTextureRegion clayTileImage;
	public static ITextureRegion hudMilitaryButtonImage;
	public static boolean menuResourcesOpen = false;
	public static AnimatedSprite mainMenuDoor;
	private static ITiledTextureRegion mainMenuDoorImage;
	public static TextureRegion incomeHUDImage;
	public static TiledTextureRegion cloudImage;
	public static boolean canBuild;
	public static TextureRegion battleHUDImage;
	public static TextureRegion menuMapAttackButtonImage;
	public static BarrackButton barrackButton;
	public static TextureRegion barrackImage;
	private static MessageOkButton messageOkButton;
	public static TextureRegion messageCancelButtonImage;
	public static TextureRegion messageConfirmButtonImage;
	public static TextureRegion messageHUDImage;
	public static MainMenuButton menuMainMenuButton;
	public static MessageCancelButton messageCancelButton;
	public static MessageConfirmButton messageConfirmButton;
	public static Sprite messageHUD;
	public static ITextureRegion stockplaceImage;
	public static ITextureRegion butcherImage;
	public static ITextureRegion butcherButtonImage;
	public static ITextureRegion aresTempleImage;
	public static ITextureRegion fishingHutButtonImage;

	private static MainMenuPlayButton mainMenuPlayButton;
	public static ITiledTextureRegion upgradeArrowImage;
	public static ArrayList<FishSpot> fishspots;
	public static ITextureRegion gotFoodSymbolImage;
	public static MenuMapButton menuMapButton;
	public static Barrack barrack;
	public static int trainingUnitCount = 1;
	private static ArrayList<RemoveableText> trainingTexts;
	public static String currentBuilding = "";
	public static ArrayList<DetachableObjects> detachableObjects;
	public static MenuMapAttackButton menuMapAttackButton;
	public static DataBase dataBase;
	public static BrickFoundry brickFoundry;
	private static MineDepositClay mineDepositClay;
	public static int startID = 15;
	public static MineDepositClay clayMine;
	public static BrickFoundryButton brickFoundryButton;
	public static ClayMineButton clayMineButton;
	private static MenuBattleReturnButton menuBattleReturnButton;
	private static ITextureRegion menuBattleReturnButtonImage;
	public static HUD battleHUD;
	private static Entity battleHUDSprite;
	public static Rectangle rectangleBlackScreen;

	/**
	 * If we press back button
	 */

	public static float income = 0;
	public static float incomeInhabitants = 0;
	public static float expenses = 0;
	public static float monthlygain = 0;
	private static ITextureRegion buildingConfirmChoiceButtonImage;
	private static ITextureRegion buildingCancelButtonImage;
	private static String message;
	public static boolean gameOver = false;
	private static float totalIncome = 0;

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
	public static void removeEntity(Entity entity) {
		if (entity != null) {

			final Entity ent = entity;
			MainActivity.main.runOnUpdateThread(new Runnable() {

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

	public static void removeEntity(Entity entity, final HUD hud) {
		final Entity ent = entity;
		MainActivity.main.runOnUpdateThread(new Runnable() {

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

	public static void addEntityScene(Entity entity) {
		final Entity ent = entity;
		MainActivity.main.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				MainActivity.mScene.attachChild(ent);
			}

		});
	}

	private void backPressed() {
		if (gameOver) {
			MainActivity.goToMainMenu();
			MainActivity.Menu = "MainMenu";
		} else {

			MainActivity.removeBuildingHUD();
			inGameHUD.gethudChatButton().closeChat();
			if (messageCancelButton != null)
				removeEntity(messageCancelButton);

			if (menuMapAttackButton != null) {
				removeEntity(menuMapAttackButton);
				MainActivity.inGameHUD.unregisterTouchArea(menuMapAttackButton);
			}
			if (MainActivity.menuMap != null)
				MainActivity.leaveMap();
			else if (MainActivity.buildingDescriptionCancel.getAlpha() == 1) {
				BuildingDescriptionCancel.Cancel();
			} else if (MainActivity.menuIncomeOpen) {
				inGameHUD.getIncomeButton().close();
			} else if (MainActivity.hudMoreInfoInhabitants.getAlpha() == 1
					|| MainActivity.resourcesMenu.getAlpha() == 1) {
				hideHudMenu();
			} else if (MainActivity.buybutton != null) {
				CancelButton.Cancel();
			} else if (MainActivity.trainingHUD.getAlpha() == 1) {
				MainActivity.removeTrainingHUD();
			} else if (MainActivity.menuMainMenuButton != null
					&& MainActivity.menuMainMenuButton.getAlpha() == 0) {
				inGameHUD.getMenuButton().showMenu();
			} else if (MainActivity.menuMainMenuButton == null) {
				showMenu();
			} else
				MainActivity.removeMenu();

		}
	}

	/**
	 * Hides the submenus
	 */
	private void hideHudMenu() {
		HUDResourceMenuButton.Cancel();
		HUDInhabitantsButton.Cancel();
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		// Creates our camera, with the position x = 0 and y = 0 and the
		// width/height of CAMERA_WIDTH/CAMERA_HEIGHT
		camera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		// Create our mission controll
		checkMission = new Mission(this);

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
		dataBase = new DataBase(this);

		GameAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(),
				4096, 4096);

		// Calls the methods that initialize our images
		initializeImages();
		initializeTiledImages();
		initializeArrayLists();
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
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		this.mEngine.registerUpdateHandler(new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {
				if (Menu == "Game") {
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
					for (int i = 0; i < MainActivity.asObjects.size(); i++) {
						if (asObjects.get(i).remove) {
							asObjects.get(i).detach();
						}
					}
					for (int i = 0; i < MainActivity.sObjects.size(); i++) {
						if (sObjects.get(i).remove) {
							sObjects.get(i).detach();
						}
					}
					for (int i = 0; i < MainActivity.removeableTexts.size(); i++) {
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
		MainActivity.mScene.setOnAreaTouchTraversalFrontToBack();
		this.mScrollDetector = new SurfaceScrollDetector(this);
		this.mPinchZoomDetector = new PinchZoomDetector(this);
		MainActivity.main = this;
		mScene.setBackground(new Background(0.0f, 2.0f, 0.2f));

		MainActivity.menu = "MainMenu";
		// EnterGame();
		startGame();
		return mScene;
	}

	@Override
	public void onScrollStarted(final ScrollDetector pScollDetector,
			final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = MainActivity.camera.getZoomFactor();
		MainActivity.camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY
				/ zoomFactor);
	}

	@Override
	public void onScroll(final ScrollDetector pScollDetector,
			final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = MainActivity.camera.getZoomFactor();
		MainActivity.camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY
				/ zoomFactor);
	}

	@Override
	public void onScrollFinished(final ScrollDetector pScollDetector,
			final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = MainActivity.camera.getZoomFactor();
		MainActivity.camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY
				/ zoomFactor);
	}

	@Override
	public void onPinchZoomStarted(final PinchZoomDetector pPinchZoomDetector,
			final TouchEvent pTouchEvent) {
		this.mPinchZoomStartedCameraZoomFactor = MainActivity.camera
				.getZoomFactor();
	}

	@Override
	public void onPinchZoom(final PinchZoomDetector pPinchZoomDetector,
			final TouchEvent pTouchEvent, final float pZoomFactor) {
		MainActivity.camera
				.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor
						* pZoomFactor);
	}

	@Override
	public void onPinchZoomFinished(final PinchZoomDetector pPinchZoomDetector,
			final TouchEvent pTouchEvent, final float pZoomFactor) {
		MainActivity.camera
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
				placebuilding
						.setPosition(touchx - houseButtonImage.getWidth() / 2
								+ placeBuildingJumpX,
								(float) (touchy - houseButtonImage.getHeight()
										/ .75 + placeBuildingJumpY));
			if (PlaceBuilding.currentBuilding == "Road")
				placebuilding
						.setPosition(
								touchx - roadimage.getWidth() / 2
										+ placeBuildingJumpX,
								(float) (touchy - roadimage.getHeight() / .75 + placeBuildingJumpY));
			placebuilding.setAlpha((float) 0.6);
			if (PlaceBuilding.currentBuilding == "Fountain")
				placebuilding
						.setPosition(
								touchx - roadimage.getWidth()
										+ placeBuildingJumpX / 2,
								(float) (touchy - roadimage.getHeight() + placeBuildingJumpY / .75));
			placebuilding.setAlpha((float) 0.6);
			if (PlaceBuilding.currentBuilding == "Farm")
				placebuilding
						.setPosition(
								touchx - farmImage.getWidth()
										+ placeBuildingJumpX / 2,
								(float) (touchy - farmImage.getHeight() + placeBuildingJumpY / .75));
			else {
				placebuilding
						.setPosition(
								touchx - houseButtonImage.getWidth()
										+ placeBuildingJumpX / 2,
								(float) (touchy - houseButtonImage.getHeight() + placeBuildingJumpY / .75));
			}

		}
		if (pSceneTouchEvent.isActionMove()) {
			// If we are inside the game we should be able to move the view
			if (Menu == "Game") {
				if (mouseprevx - pSceneTouchEvent.getX() > 15) {
					if (camera.getZoomFactor() >= 0.2)
						mouse.setPosition(mouse.getX() + 5 * 6, mouse.getY());
					else
						mouse.setPosition(mouse.getX() + 5 * 6 * 5,
								mouse.getY());
				}
				if (mouseprevx - pSceneTouchEvent.getX() < -15) {
					if (camera.getZoomFactor() >= 0.2)
						mouse.setPosition(mouse.getX() - 5 * 6, mouse.getY());
					else
						mouse.setPosition(mouse.getX() - 5 * 6 * 5,
								mouse.getY());
				}
				if (mouseprevy - pSceneTouchEvent.getY() > 15) {
					if (camera.getZoomFactor() >= 0.2)
						mouse.setPosition(mouse.getX(), (mouse.getY() + 5 * 6));
					else
						mouse.setPosition(mouse.getX(),
								(mouse.getY() + 5 * 6 * 5));
				}
				if (mouseprevy - pSceneTouchEvent.getY() < -15) {
					if (camera.getZoomFactor() >= 0.2)
						mouse.setPosition(mouse.getX(), (mouse.getY() - 5 * 6));
					else
						mouse.setPosition(mouse.getX(),
								(mouse.getY() - 5 * 6 * 5));

				}
				mouseprevx = pSceneTouchEvent.getX();
				mouseprevy = pSceneTouchEvent.getY();
				camera.setCenter(mouse.getX(), mouse.getY());
			}
		}
		return false;
	}

	/**
	 * Updates our gold and changes the goldtext
	 */
	public static void updateGoldMonthly() {
		incomeInhabitants = 0;
		militaryWorkers = getTotalMilitaryWorkers();
		Workers = MainActivity.militaryWorkers + MainActivity.buildingWorkers;
		for (int i = 1; i < MainActivity.Inhabitants.length; i++) {
			incomeInhabitants += (float) (MainActivity.Inhabitants[i] * 9 * (0.75 + (0.25 * i)));
		}
		updateIncome();
		setExpenses();
		MainActivity.monthlygain = totalIncome - expenses;
		Gold += monthlygain;

		// Change the gold text color if you gain or lose money
		if (MainActivity.monthlygain >= 0)
			GoldText.setColor(0f, 1f, 0f);
		else
			GoldText.setColor(1f, 0f, 0f);
		GoldText.setText(Integer.toString(Gold));
	}

	private static int getTotalMilitaryWorkers() {
		return MainActivity.militaryHoplite + MainActivity.militarySlinger ;
	}

	private static void updateIncome() {
		totalIncome = income + incomeInhabitants;
	}

	private static void setExpenses() {
		expenses = 0;
		expenses += barracks.size() * ConstantBuildings.EXPENSESBARRACK;
		expenses += brickFoundrys.size()
				* ConstantBuildings.EXPENSESBRICKFOUNNDRY;
		expenses += butchers.size() * ConstantBuildings.EXPENSESBUTCHER;
		expenses += Farms.size() * ConstantBuildings.EXPENSESFARM;
		expenses += FishingHuts.size() * ConstantBuildings.EXPENSESFISHINGHUT;
		expenses += FoodMarkets.size() * ConstantBuildings.EXPENSESFOODMARKET;
		expenses += Fountains.size() * ConstantBuildings.EXPENSESFOUNTAIN;
		expenses += huntersLodges.size()
				* ConstantBuildings.EXPENSESHUNTERSLODGE;
		expenses += mineDepositClays.size()
				* ConstantBuildings.EXPENSESMINEDEPOSITCLAY;
		expenses += mineDepositBronzes.size()
				* ConstantBuildings.EXPENSESMINEDEPOSITBRONZE;
		expenses += Silos.size() * ConstantBuildings.EXPENSESSILO;
		expenses += skinners.size() * ConstantBuildings.EXPENSESSKINNER;
		expenses += Stocks.size() * ConstantBuildings.EXPENSESSTOCK;
		expenses += StoneCutters.size() * ConstantBuildings.EXPENSESSTONECUTTER;
		expenses += Theatres.size() * ConstantBuildings.EXPENSESTHEATRE;
		expenses += WoodCutters.size() * ConstantBuildings.EXPENSESWOODCUTTER;
		expenses += militaryHoplite
				* ConstantBuildings.EXPENSESHOPLITE;
		// expenses+=granarys.size()*main.EXPENSESGRANARY;

	}

	public static void updateGold(int gold) {
		Gold += gold;
		if (MainActivity.monthlygain >= 0)
			GoldText.setColor(0f, 1f, 0f);
		else
			GoldText.setColor(1f, 0f, 0f);
		GoldText.setText(Integer.toString(Gold));
	}

	public static void setGold(int gold) {
		Gold = gold;
		GoldText.setColor(1f, 1f, 1f);
		GoldText.setText(Integer.toString(Gold));
	}

	public static void updateInhabitants(int inhabitantss, int houseLevel,
			int totalInhabitants) {
		Inhabitants[0] += inhabitantss;
		Inhabitants[houseLevel] += totalInhabitants;
		InhabitantsText.setColor(1f, 1f, 1f);
		if (houseLevel > 1) {
			Inhabitants[houseLevel - 1] -= totalInhabitants - inhabitantss;
		}
		InhabitantsText.setText(Integer.toString(Inhabitants[0]));
		MaxWorkers = (int) ((int) MainActivity.Inhabitants[0] * 0.75);
	}

	/**
	 * Update each level of the inhabitants
	 */
	public void updateLevelInhabitants(){
		for(int j = 0; j < Inhabitants.length;j++){
			Inhabitants[j] = 0;
		for(int i = 0; i < this.Houses.size();i++){
			Inhabitants[j] += Houses.get(i).getInhabitants(j);
		}}
	}
	
	public static void updateWorkers(int workers, int maxworkers) {
		buildingWorkers += workers;
		MaxWorkers = (int) ((int) MainActivity.Inhabitants[0] * 0.75);
		Workers = buildingWorkers + militaryWorkers;
	}

	// This method calculates a distance between 2 objects and returns the
	// distance
	public static double calculateDistance(Entity object1, Entity object2) {

		float length;
		float height;
		length = object1.getX() - object2.getX();
		height = object1.getX() - object2.getX();
		double distance = Math.sqrt((length * length) + (height * height));

		return distance;
	}

	public static void spawnArmy() {
		for (int i = 0; i < militaryHopliteWar; i++) {
			Random rand = new Random();
			int x, y;
			x = rand.nextInt(96);
			y = rand.nextInt(96);
			Hoplite = new Hoplite(x, y, main.hopliteImage,
					main.getVertexBufferObjectManager());
			Hoplites.add(Hoplite);
			mScene.attachChild(Hoplite);
		}
	}

	private void CreateMap() {
		// TODO Auto-generated method stub
		if (Map == "Map1") {
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
					tree = new Tree(i * 48, j * 48, treeImage,
							this.getVertexBufferObjectManager());
					Trees.add(tree);
					mScene.attachChild(tree);
					tree.stopAnimation(2);
				}
				if (Levels.Map1[k] == 2) {
					marble = new Sprite(i * 48, j * 48, marbleImage,
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
					+ marbleTileImage.getWidth() * i, mineDepositStartY,
					marbleTileImage, main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() / 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					+ marbleTileImage.getHeight() / 2, marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 1
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					+ marbleTileImage.getHeight(), marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 1
					+ marbleTileImage.getWidth() / 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					+ marbleTileImage.getHeight() + marbleTileImage.getHeight()
					/ 2, marbleTileImage, main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					+ marbleTileImage.getHeight() * 2, marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 2
					+ marbleTileImage.getWidth() / 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					+ marbleTileImage.getHeight() * 2
					+ marbleTileImage.getHeight() / 2, marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() / 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					- marbleTileImage.getHeight() / 2, marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 1
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					- marbleTileImage.getHeight(), marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 1
					+ marbleTileImage.getWidth() / 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					- marbleTileImage.getHeight() - marbleTileImage.getHeight()
					/ 2, marbleTileImage, main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					- marbleTileImage.getHeight() * 2, marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempMarbleTile = new MarbleTile(mineDepositStartX
					+ marbleTileImage.getWidth() * 2
					+ marbleTileImage.getWidth() / 2
					+ marbleTileImage.getWidth() * i, mineDepositStartY
					- marbleTileImage.getHeight() * 2
					- marbleTileImage.getHeight() / 2, marbleTileImage,
					main.getVertexBufferObjectManager());
			marbleTiles.add(tempMarbleTile);
			mScene.attachChild(tempMarbleTile);
			tempMarbleTile.stopAnimation(0);
		}

	}

	private void createClayTiles() {
		int mineDepositStartX = 800, mineDepositStartY = 500, sizeX = 6, sizeY = 20;
		ClayTile tempClaytile;
		for (int i = 0; i < sizeX; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * i, mineDepositStartY,
					clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() / 2 + clayTileImage.getWidth()
					* i, mineDepositStartY + clayTileImage.getHeight() / 2,
					clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 1 + clayTileImage.getWidth()
					* i, mineDepositStartY + clayTileImage.getHeight(),
					clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 1 + clayTileImage.getWidth()
					/ 2 + clayTileImage.getWidth() * i,
					mineDepositStartY + clayTileImage.getHeight()
							+ clayTileImage.getHeight() / 2, clayTileImage,
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 2 + clayTileImage.getWidth()
					* i, mineDepositStartY + clayTileImage.getHeight() * 2,
					clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 2 + clayTileImage.getWidth()
					/ 2 + clayTileImage.getWidth() * i, mineDepositStartY
					+ clayTileImage.getHeight() * 2 + clayTileImage.getHeight()
					/ 2, clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 1; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() / 2 + clayTileImage.getWidth()
					* i, mineDepositStartY - clayTileImage.getHeight() / 2,
					clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 2; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 1 + clayTileImage.getWidth()
					* i, mineDepositStartY - clayTileImage.getHeight(),
					clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 3; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 1 + clayTileImage.getWidth()
					/ 2 + clayTileImage.getWidth() * i,
					mineDepositStartY - clayTileImage.getHeight()
							- clayTileImage.getHeight() / 2, clayTileImage,
					main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 4; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 2 + clayTileImage.getWidth()
					* i, mineDepositStartY - clayTileImage.getHeight() * 2,
					clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}
		for (int i = 0; i < sizeX - 5; i++) {
			tempClaytile = new ClayTile(mineDepositStartX
					+ clayTileImage.getWidth() * 2 + clayTileImage.getWidth()
					/ 2 + clayTileImage.getWidth() * i, mineDepositStartY
					- clayTileImage.getHeight() * 2 - clayTileImage.getHeight()
					/ 2, clayTileImage, main.getVertexBufferObjectManager());
			ClayTiles.add(tempClaytile);
			mScene.attachChild(tempClaytile);
			tempClaytile.stopAnimation(0);
		}

	}

	public Engine getEngine() {
		return this.mEngine;
	}

	private void timerHandlerPause() {
		this.mEngine
				.registerUpdateHandler(TimerHandlerPause = new TimerHandler(1,
						new ITimerCallback() {
							@Override
							public void onTimePassed(
									final TimerHandler pTimerHandler) {
								if (PAUSE) {
									mEngine.unregisterUpdateHandler(TimerHandlerMonthly);
									TimerHandlerPause.reset();
								} else if (!PAUSE) {
									mEngine.unregisterUpdateHandler(TimerHandlerPause);
									timerHandlerMonthly();
								}
							}

						}));
	}

	public static void enterBattlefield(CityIcon enemy) {
		MainActivity.mScene.clearEntityModifiers();
		MainActivity.mScene.clearTouchAreas();
		mScene.registerTouchArea(null); // 0000 0001
		mScene.clearTouchAreas();
		MainActivity.mScene.clearChildScene();
		MainActivity.mScene.detachChildren();
		MainActivity.camera.setHUD(null);
		MainActivity.camera.reset();

		spawnArmy();
		createBattleHUD();
		// Create resume button
		// HUD
		// spawnArmyEnemy(enemy);
	}

	/**
	 * Creates the battle HUD
	 */
	public static void createBattleHUD() {
		battleHUD = new HUD();
		battleHUDSprite = new Sprite(0, 0, battleHUDImage,
				main.getVertexBufferObjectManager());
		battleHUD.attachChild(battleHUDSprite);
		camera.setHUD(battleHUD);
		menuBattleReturnButton = new MenuBattleReturnButton(1085, 531,
				menuBattleReturnButtonImage,
				main.getVertexBufferObjectManager());
		battleHUD.attachChild(menuBattleReturnButton);
		battleHUD.registerTouchArea(menuBattleReturnButton);
		battleHUD.registerTouchArea(inGameHUDSprite);

	}

	// Updates the month, along with many other methods
	private void timerHandlerMonthly() {
		int i = 0;
		this.mEngine
				.registerUpdateHandler(TimerHandlerMonthly = new TimerHandler(
						GameSpeed, new ITimerCallback() {
							@Override
							public void onTimePassed(
									final TimerHandler pTimerHandler) {
								if (MainActivity.Menu == "Game") {
									if (!PAUSE) {
										TimerHandlerMonthly.reset();
									} else {
										timerHandlerPause();
									}
									for (int i = 0; i < MainActivity.cityIcons
											.size(); i++) {
										MainActivity.cityIcons.get(i).update();
									}
									CheckForDebt();
									UpdateMonth();
									updateGoldMonthly(); 
									checkMission.checkMission(Map, CurrentMission);
									updateLevelInhabitants();
									CheckHouseLevel();
									// Update the houses
									for (int i = 0; i < MainActivity.Houses
											.size(); i++) {
										Houses.get(i).Update(Houses.get(i));
									}
									// July, harvest wheat
									if (Month == 7) {
										for (int i = 0; i < MainActivity.Farms
												.size(); i++) {
											Farms.get(i);
											Farm.ProduceWheat(Farms.get(i));
										}

									}
								} else
									TimerHandlerMonthly.reset();
							}

							private void CheckForDebt() {
								if (MainActivity.Gold < 0)
									main.monthInDebt++;

								else
									main.monthInDebt = 0;

								if (main.monthInDebt >= 12) {
									gameOver();
								}
							}

							private void gameOver() {
								MainActivity.PAUSE = true;
								MainActivity
										.MessagePopUp(
												"Your city has been in debt for more than a year, you lost.",
												org.andengine.util.color.Color.RED);
								MainActivity.gameOver = true;
							}
						}));
	}

	protected void UpdateMonth() {
		for (int i = 0; i < MainActivity.sObjects.size(); i++) {
			// if(MainActivity.sObjects.get(i).called == false){
			MainActivity.sObjects.get(i).checkForStocks();
			// MainActivity.sObjects.get(i).called = true;}
			// else
			// MainActivity.sObjects.get(i).called = false;
		}
		for (int i = 0; i < MainActivity.asObjects.size(); i++) {
			// if(MainActivity.asObjects.get(i).called == false){
			MainActivity.asObjects.get(i).checkForStocks();
			// MainActivity.asObjects.get(i).called = true;}
			// else
			// MainActivity.asObjects.get(i).called = false;
		}
		Month += 1;
		if (Month >= 13) {
			Month = 1;
			yearlyTribute();
		}
		String res = "Month: ";
		if (Month == 1) {
			res += "January";
		} else if (Month == 2) {
			res += "February";
		} else if (Month == 3) {
			res += "Mars";
		} else if (Month == 4) {
			res += "April";
		} else if (Month == 5) {
			res += "May";
		} else if (Month == 6) {
			res += "June";
		} else if (Month == 7) {
			res += "July";
		} else if (Month == 8) {
			res += "August";
		} else if (Month == 9) {
			res += "September";
		} else if (Month == 10) {
			res += "October";
		} else if (Month == 11) {
			res += "November";
		} else if (Month == 12) {
			res += "December";
		}
		MonthText.setColor(1f, 1f, 1f);
		MonthText.setText(res);
		// TODO Auto-generated method stub

	}

	private void yearlyTribute() {
		for (int i = 0; i < MainActivity.CITY.length; i++) {
			if (MainActivity.CITY[i][9] == "1") {
				if (MainActivity.CITY[i][10] == "Coin") {
					MainActivity.updateGold(15000);
					MainActivity.MessagePopUp(MainActivity.CITY[i][0]
							+ "Payed you: 15 000 coin for tribute ",
							new org.andengine.util.color.Color(1f, 1f, 1f));
				}
			} else if (MainActivity.CITY[i][9] == "2") {
				if (MainActivity.CITY[i][10] == "Coin") {
					MainActivity.updateGold(-15000);
					MainActivity.MessagePopUp("Your city payed 15 000 gold to "
							+ MainActivity.CITY[i][0] + " as tribute",
							new org.andengine.util.color.Color(1f, 1f, 1f));
				}
			}
		}

	}

	protected void CheckHouseLevel() {
		// TODO Auto-generated method stub
		int j = 0;
		for (int i = 0; i < MainActivity.Houses.size(); i++) {

			if (Houses.get(i).HouseLevel > j)
				j = Houses.get(i).HouseLevel;

			HouseLevel = j;
			if (buybutton != null)
				buybutton.HouseLevel = HouseLevel;
		}
	}

	public static void Message(String message,
			org.andengine.util.color.Color color) {
		MoreInfoText.setText(message);
		MoreInfoText.setColor(color);
	}

	public static void MessagePopUp(String message,
			org.andengine.util.color.Color color) {
		MainActivity.removeMessage();
		messageHUD = new Sprite(240, 180, messageHUDImage,
				main.getVertexBufferObjectManager());
		messageHUD.setAlpha(0.9f);
		messageOkButton = new MessageOkButton(messageHUD.getX() + 256,
				messageHUD.getY() + 240, messageOkButtonImage,
				main.getVertexBufferObjectManager());
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
		MainActivity.updateMessageHistory(message);
		updateRecentMessages(message);
		// Debug.e("Pop");
	}

	public static void MessagePopUpNoRemove(String message,
			org.andengine.util.color.Color color) {
		// updateRecentMessages(message);
		messageHUD = new Sprite(240, 180, messageHUDImage,
				main.getVertexBufferObjectManager());
		messageHUD.setAlpha(0.9f);
		messageOkButton = new MessageOkButton(messageHUD.getX() + 256,
				messageHUD.getY() + 240, messageOkButtonImage,
				main.getVertexBufferObjectManager());
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

	public static void updateRecentMessages(String message) {
		String res = "";
		for (int i = 0; i < MainActivity.recentMessages.length; i++) {
			if (MainActivity.recentMessages[i] == ""
					|| MainActivity.recentMessages[i] == null) {
				MainActivity.recentMessages[i] = message;
				res += MainActivity.recentMessages[i] + " " + "";
				break;
			}
			res += MainActivity.recentMessages[i] + " " + "";
		}
		Debug.e(res);
	}

	public static void MessagePopUpChoice(String message,
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

		MainActivity.choice = choice;
		MainActivity.ID = parent;
		messageHUD = new Sprite(240, 180, messageHUDImage,
				main.getVertexBufferObjectManager());
		messageConfirmButton = new MessageConfirmButton(
				messageHUD.getX() + 409, messageHUD.getY() + 240,
				messageConfirmButtonImage, main.getVertexBufferObjectManager());
		messageCancelButton = new MessageCancelButton(messageHUD.getX() + 48,
				messageHUD.getY() + 240, messageCancelButtonImage,
				main.getVertexBufferObjectManager());
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
	 * for(int i = 0;i<MainActivity.FoodMarkets.size();i++){
	 * //FoodMarkets.get(i).UpdateFood(FoodMarkets.get(i)); } } protected void
	 * UpdateStoneCutters() { // TODO Auto-generated method stub for(int i =
	 * 0;i<MainActivity.StoneCutters.size();i++){
	 * StoneCutters.get(i).CheckForStocks(StoneCutters.get(i)); } } protected
	 * void UpdateHuntersLodges() { // TODO Auto-generated method stub for(int i
	 * = 0;i<MainActivity.huntersLodges.size();i++){
	 * huntersLodges.get(i).CheckForStocks(huntersLodges.get(i)); } } protected
	 * void UpdateSkinners() { // TODO Auto-generated method stub for(int i =
	 * 0;i<MainActivity.skinners.size();i++){
	 * skinners.get(i).CheckForStocks(skinners.get(i)); } } protected void
	 * UpdateWoodCutters() { // TODO Auto-generated method stub for(int i =
	 * 0;i<MainActivity.WoodCutters.size();i++){
	 * WoodCutters.get(i).CheckForStocks(WoodCutters.get(i)); } } protected void
	 * UpdateButchers() { // TODO Auto-generated method stub for(int i =
	 * 0;i<MainActivity.butchers.size();i++){
	 * butchers.get(i).CheckForStocks(butchers.get(i)); } } /* protected void
	 * UpdateStocks() { // TODO Auto-generated method stub for(int i =
	 * 0;i<MainActivity.StoneCutters.size();i++){
	 * UpdateStocks.get(i).CheckForStocks(StoneCutters.get(i)); } }
	 */


	int getMarbleProductionYearly() {
		return MainActivity.StoneCutters.size() * 12;
	}

	/**
	 * Checks to see if the inhabitants have accses to food
	 * 
	 * @return true or false if we have food
	 */
	double getAccsessToFood() {
		if (MainActivity.Houses.size() != 0) {
			double percentoffood = 0;
			double gotfoodammount = 0;
			for (int i = 0; i < MainActivity.Houses.size(); i++) {
				if (MainActivity.Houses.get(i).checkFood()) {
					gotfoodammount += 1;
				}
			}
			percentoffood = (gotfoodammount / MainActivity.Houses.size());
			if (gotfoodammount != 0)
				return percentoffood;
		}
		return 0;
	}

	/**
	 * Adds the touch areas for our buildings
	 */
	public static void addBuildingTouchAreas() {

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
	public static void removeBuildingTouchAreas() {
		// Makes it so we can't touch the buildings anymore, for example when we
		// got a menu open.
		for (int i = 0; i < sObjects.size(); i++) {
			sObjects.get(i).removeTouchArea();
		}
		for (int i = 0; i < asObjects.size(); i++) {
			asObjects.get(i).removeTouchArea();
		}
	}

	/**
	 * Cleares the current information of the game
	 */
	public static void wipeInGameInfo() {
		MainActivity.Inhabitants = new int[MainActivity.InhabitantsSize];
		MainActivity.Gold = MainActivity.startGold;
		MainActivity.Workers = 0;
		MainActivity.buildingWorkers = 0;
		MainActivity.militaryWorkers = 0;
		MainActivity.messageHistory = new String[100];
		for (int i = 0; i < MainActivity.chatHistoryTexts.size(); i++) {
			MainActivity.removeEntity(MainActivity.chatHistoryTexts.get(i),
					inGameHUD);
		}
		MainActivity.chatHistoryTexts = new ArrayList<Text>();
		cityIcons.clear();
		cityMessages.clear();
		initializeArrayLists();
	}

	// This method creates all the necessary objects that we need to run the
	// game.
	public void EnterGame() {
		if (Menu == "Game") {
			timerHandlerMonthly();
			Gold = MainActivity.startGold;
			// Creates a new scene
			mScene.setBackground(new Background(0.25490196078f, 0.72f, 0.22f));
			mScene.setOnSceneTouchListener(this);
			setUpHUD(); // Initializes the HUD and it's components

			aresTemple = new Sprite(0, 0, aresTempleImage,
					this.getVertexBufferObjectManager());
			mScene.attachChild(aresTemple);
			fishspot = new FishSpot(128, 128, fishSpotImage,
					this.getVertexBufferObjectManager());
			fishspot.animate(200);
			fishspots.add(fishspot);
			mScene.attachChild(fishspot);
			fishspot = new FishSpot(196, 256, fishSpotImage,
					this.getVertexBufferObjectManager());
			fishspot.animate(200);
			fishspots.add(fishspot);
			mScene.attachChild(fishspot);

			mouse = new Sprite(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, HouseImage,
					this.getVertexBufferObjectManager());
			mouse.attachChild(mouse);

			camera.setCenter(mouse.getX(), mouse.getY());
			CreateMap();
		}

	}

	public static void leaveMainMenu(String string) {
		inGameHUD.clearChildScene();
		inGameHUD.clearTouchAreas();
		mScene.clearEntityModifiers();
		mScene.clearChildScene();
		mScene.detachChildren();
		mScene.registerTouchArea(null); // 0000 0001
		mScene.clearTouchAreas();
		MainActivity.camera.reset();
		Menu = "Game";
		MainActivity.PAUSE = false;
		main.EnterGame();
		if (string == "load")
			main.loadFarm();
	}

	public void loadFarm() {
		dataBase = new DataBase(this);
		String load;
		int i = 0;
		float x = 0, y = 0;
		for (i = 0; i < dataBase.getSize(); i++) {
			if (!dataBase.checkIfDone(i)) {
				x = dataBase.readDataBaseX(i);
				y = dataBase.readDataBaseY(i);
				load = dataBase.readDataBaseType(i);
				int number = dataBase.readDataBaseInt(i);
				// if (load == "-1" || x == -99999 || y == -99999 || number ==
				// -99999) {
				// break;
				// }//Next row are values, like marble ammount etc.
				if (load.contains("Gold")) {
					MainActivity.setGold((int) x);
				} else if (load.contains("Inhabitants")) {
					MainActivity.updateInhabitants((int) x, 0, 0);
				} else if (load.contains("Building Workers")) {
					MainActivity.buildingWorkers = (int) x;
					MainActivity.updateWorkers(0, 0);
				} else if (load.contains("MilitaryHoplite")) {
					militaryHoplite = (int) x;
				} else if (load.contains("MilitarySlinger")) {
					militarySlinger = (int) x;
				} else if (load.contains("Barrack")) {
					MainActivity.barrack = new Barrack(x, y, barrackImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.barrack);
					barracks.add(MainActivity.barrack);
					// sObjects.add(MainActivity.barrack);
					mScene.registerTouchArea(barrack);

				} else if (load.contains("Brick Foundry")) {
					MainActivity.brickFoundry = new BrickFoundry(x, y,
							brickFoundryImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.brickFoundry);
					brickFoundrys.add(MainActivity.brickFoundry);
					// sObjects.add(MainActivity.brickFoundry);
					mScene.registerTouchArea(brickFoundry);

				} else if (load.contains("Butcher")) {
					MainActivity.butcher = new Butcher(x, y, butcherImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.butcher);
					butchers.add(MainActivity.butcher);
					// sObjects.add(MainActivity.butcher);
					mScene.registerTouchArea(butcher);
				} else if (load.contains("Farm")) {
					MainActivity.farm = new Farm(x, y, farmImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.farm);
					Farms.add(MainActivity.farm);
					MainActivity.farm.animate(1000);
					// a//sObjects.add(MainActivity.farm);
					mScene.registerTouchArea(farm);
				} else if (load.contains("Fishing Hut")) {
					MainActivity.fishinghut = new FishingHut(x, y,
							fishingHutImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.fishinghut);
					FishingHuts.add(MainActivity.fishinghut);
					// sObjects.add(MainActivity.fishinghut);
					mScene.registerTouchArea(fishinghut);

				} else if (load.contains("Food Market")) {
					MainActivity.foodmarket = new FoodMarket(x, y,
							FoodMarketImage,
							this.getVertexBufferObjectManager(), number);
					mScene.attachChild(MainActivity.foodmarket);
					FoodMarkets.add(MainActivity.foodmarket);
					// sObjects.add(MainActivity.foodmarket);
					mScene.registerTouchArea(foodmarket);

				} else if (load.contains("Fountain")) {
					MainActivity.fountain = new Fountain(x, y, fountainImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.fountain);
					Fountains.add(MainActivity.fountain);
					// sObjects.add(MainActivity.fountain);
					mScene.registerTouchArea(fountain);

				} else if (load.contains("House")) {
					int houselevel = dataBase.readDataBaseInt(i);
					MainActivity.Houseexample = new House(x, y, HouseImage,
							this.getVertexBufferObjectManager(), true);
					// MainActivity.main.addEntityScene(MainActivity.Houseexample);
					mScene.attachChild(MainActivity.Houseexample);
					Houses.add(MainActivity.Houseexample);
					// a//sObjects.add(MainActivity.Houseexample);
					MainActivity.Houseexample.createChilds();
					if (houselevel != -99999) {
						Houseexample.setLevel(houselevel);
					}
					mScene.registerTouchArea(Houseexample);
				} else if (load.contains("Hunters Lodge")) {
					MainActivity.huntersLodge = new HuntersLodge(x, y,
							huntersLodgeImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.huntersLodge);
					huntersLodges.add(MainActivity.huntersLodge);
					// sObjects.add(MainActivity.huntersLodge);
					mScene.registerTouchArea(huntersLodge);

				} else if (load.contains("Mine Deposit Clay")) {
					MainActivity.mineDepositClay = new MineDepositClay(x, y,
							mineDepositClayImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.mineDepositClay);
					mineDepositClays.add(MainActivity.mineDepositClay);
					// sObjects.add(MainActivity.mineDepositClay);
					mScene.registerTouchArea(mineDepositClay);

				} else if (load.contains("Road")) {
					MainActivity.road = new Road(x, y, roadimage,
							this.getVertexBufferObjectManager());
					mScene.attachChild(MainActivity.road);
					Roads.add(MainActivity.road);
					// sObjects.add(MainActivity.road);

				} else if (load.contains("Silo")) {
					MainActivity.silo = new Silo(x, y, siloImage,
							this.getVertexBufferObjectManager(), number);
					mScene.attachChild(MainActivity.silo);
					Silos.add(MainActivity.silo);
					// sObjects.add(MainActivity.silo);
					mScene.registerTouchArea(silo);

				} else if (load.contains("Stock")) {
					Debug.e("Load stock");
					MainActivity.stock = new Stock(x, y,
							MainActivity.stockplaceImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.stock);
					Stocks.add(MainActivity.stock);
					// sObjects.add(MainActivity.stock);
					mScene.registerTouchArea(stock);
					String kind = dataBase.readDataBaseString1(i);
					Debug.e(kind);
					String ammount = dataBase.readDataBaseString2(i);
					String kinds[] = { "", "", "", "", "", "", "", "", "", "" };
					int ammounts[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					String globalKind = dataBase.readDataBaseString3(i);
					stock.globalKind = globalKind;
					String writeKind = "";
					String tempString = "", tempStringrest = kind;
					int index1 = 0;
					int index2 = tempStringrest.indexOf("|") + 1;
					for (int j = 0; j < kinds.length; j++) {
						writeKind = tempStringrest.substring(0, index2);
						kinds[j] = writeKind;
						index1 = index2;
						tempStringrest = tempStringrest.substring(index2);
						index2 = tempStringrest.indexOf("|") + 1;
						if (kinds[j].contains("none"))
							kinds[j] = "";
					}
					// String[] kindss;
					// Debug.e("Kind text: " + kind);
					//
					// kindss = kind.split("|");
					// String temp = kindss[0];
					// Debug.e("SASD  " + temp + kindss[1]);
					tempStringrest = ammount;
					index2 = tempStringrest.indexOf("|") + 1;
					for (int j = 0; j < ammounts.length; j++) {
						writeKind = tempStringrest.substring(0, index2);
						writeKind = writeKind.replace("|", "");
						if (writeKind != "" && kinds[j] != "") {
							if (writeKind.contains("1")
									|| writeKind.contains("2")
									|| writeKind.contains("3")
									|| writeKind.contains("4")) {
								ammounts[j] = Integer.parseInt(writeKind);
								Debug.e("Ammount: " + j + "  " + ammounts[j]);
							}
						} else
							ammounts[j] = 0;
						index1 = index2;
						tempStringrest = tempStringrest.substring(index2);
						index2 = tempStringrest.indexOf("|") + 1;
					}
					Debug.e("WHAT KIND " + kinds[0] + "  " + kinds[2]
							+ ammounts[2]);
					stock.load(kinds, ammounts);

				} else if (load.contains("Skinner")) {
					MainActivity.skinner = new Skinner(x, y, skinnerImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.skinner);
					skinners.add(MainActivity.skinner);
					// sObjects.add(MainActivity.skinner);
					mScene.registerTouchArea(skinner);

				} else if (load.contains("Stone Cutter")) {
					MainActivity.stonecutter = new StoneCutter(x, y,
							stoneCutterImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.stonecutter);
					StoneCutters.add(MainActivity.stonecutter);
					// a//sObjects.add(MainActivity.stonecutter);
					stonecutter.createNpc();
					mScene.registerTouchArea(stonecutter);

				} else if (load.contains("Theatre")) {
					MainActivity.theatre = new Theatre(x, y, theatreImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.theatre);
					Theatres.add(MainActivity.theatre);
					// sObjects.add(MainActivity.theatre);
					mScene.registerTouchArea(theatre);

				} else if (load.contains("Wood Cutter")) {
					MainActivity.woodcutter = new WoodCutter(x, y,
							woodCutterImage,
							this.getVertexBufferObjectManager(), true);
					mScene.attachChild(MainActivity.woodcutter);
					WoodCutters.add(MainActivity.woodcutter);
					// sObjects.add(MainActivity.woodcutter);
					mScene.registerTouchArea(woodcutter);

				} else if (load.contains("Marble")) {
					Debug.e("I SHALL LOAD MARBLE ammount: " + String.valueOf(x));
					MainActivity.Marble = (int) x;

				} else if (load.contains("Wood")) {
					MainActivity.Wood = (int) x;
				} else if (load.contains("Skin")) {
					MainActivity.Armor = (int) x;
				} else if (load.contains("Sculptures")) {
					MainActivity.Sculptures = (int) x;
				} else if (load.contains("Fish")) {
					MainActivity.Fish = (int) x;
				} else if (load.contains("Bronze")) {
					MainActivity.Bronze = (int) x;
				} else if (load.contains("Brick")) {
					MainActivity.Brick = (int) x;
				} else if (load.contains("Brick")) {
					MainActivity.Brick = (int) x;
				} else if (load.contains("Meat")) {
					MainActivity.Meat = (int) x;
				} else if (load.contains("Clay")) {
					MainActivity.Clay = (int) x;
				} else if (load.contains("Clay")) {
					MainActivity.Clay = (int) x;
				}
			} else
				break;
		}
	}

	private void startGame() {
		mainMenuBackground = new Sprite(0, 0, mainMenuBackgroundImage,
				this.getVertexBufferObjectManager());
		mScene.attachChild(mainMenuBackground);
		mainMenuPlayButton = new MainMenuPlayButton(160, 135,
				mainMenuPlayButtonImage, this.getVertexBufferObjectManager(),
				this);
		mainMenuDoor = new AnimatedSprite(575, 439, mainMenuDoorImage,
				this.getVertexBufferObjectManager());
		mainMenuDoor.stopAnimation(0);
		mainMenuPlayButton = new MainMenuPlayButton(160 + 256, 135 + 256,
				mainMenuPlayButtonImage, this.getVertexBufferObjectManager(),
				this);
		mainMenuLoadButton = new MainMenuLoadButton(160 + 256, 135 + 256 + 256,
				mainMenuLoadButtonImage, this.getVertexBufferObjectManager(),
				this);
		mScene.attachChild(mainMenuPlayButton);
		mScene.registerTouchArea(mainMenuPlayButton);
		mScene.attachChild(mainMenuLoadButton);
		mScene.registerTouchArea(mainMenuLoadButton);
		mScene.attachChild(mainMenuDoor);
		menu = "MainMenu";
	}

	public static void EnterMainMenu() {
		removeMenu();
		camera.setCenter(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2);
		if (Menu == "MainMenu") {
			mainMenuBackground = new Sprite(0, 0, mainMenuBackgroundImage,
					main.getVertexBufferObjectManager());
			mainMenuDoor = new AnimatedSprite(575, 439, mainMenuDoorImage,
					main.getVertexBufferObjectManager());
			mainMenuDoor.stopAnimation(0);
			mScene.attachChild(mainMenuBackground);
			mScene.attachChild(mainMenuDoor);
			mainMenuPlayButton = new MainMenuPlayButton(160 + 256, 135 + 256,
					mainMenuPlayButtonImage,
					main.getVertexBufferObjectManager(), main);
			mainMenuLoadButton = new MainMenuLoadButton(160 + 256,
					135 + 256 + 256, mainMenuLoadButtonImage,
					main.getVertexBufferObjectManager(), main);
			mScene.attachChild(mainMenuPlayButton);
			mScene.registerTouchArea(mainMenuPlayButton);
			mScene.attachChild(mainMenuLoadButton);
			mScene.registerTouchArea(mainMenuLoadButton);
		}

	}

	public static void leaveGame() {
		MainActivity.mScene.clearEntityModifiers();
		mScene.registerTouchArea(null); // 0000 0001
		mScene.clearTouchAreas();
		MainActivity.mScene.clearChildScene();
		MainActivity.mScene.detachChildren();
		MainActivity.camera.setHUD(null);
		MainActivity.camera.reset();
		Menu = "MainMenu";
		EnterMainMenu();
	}

	public static void unRegisterBuildingButtons() {
		// TODO Auto-generated method stub
		MainActivity.housebutton.setAlpha(0);
		MainActivity.skinnerButton.setAlpha(0);
		MainActivity.siloButton.setAlpha(0);
		MainActivity.foodMarketButton.setAlpha(0);
		MainActivity.theatreButton.setAlpha(0);
		MainActivity.huntersLodgeButton.setAlpha(0);
		MainActivity.fountainButton.setAlpha(0);
		MainActivity.farmButton.setAlpha(0);
		MainActivity.stockButton.setAlpha(0);
		MainActivity.woodCutterButton.setAlpha(0);
		MainActivity.stoneCutterButton.setAlpha(0);
		MainActivity.roadbutton.setAlpha(0);
		MainActivity.fishingHutButton.setAlpha(0);
		MainActivity.skinnerButton.setAlpha(0);
		MainActivity.butcherButton.setAlpha(0);
		MainActivity.barrackButton.setAlpha(0);
		brickFoundryButton.setAlpha(0);
		clayMineButton.setAlpha(0);

		MainActivity.inGameHUD.unregisterTouchArea(housebutton);
		MainActivity.inGameHUD.unregisterTouchArea(barrackButton);
		MainActivity.inGameHUD.unregisterTouchArea(skinnerButton);
		MainActivity.inGameHUD.unregisterTouchArea(siloButton);
		MainActivity.inGameHUD.unregisterTouchArea(foodMarketButton);
		MainActivity.inGameHUD.unregisterTouchArea(theatreButton);
		MainActivity.inGameHUD.unregisterTouchArea(huntersLodgeButton);
		MainActivity.inGameHUD.unregisterTouchArea(fountainButton);
		MainActivity.inGameHUD.unregisterTouchArea(farmButton);
		MainActivity.inGameHUD.unregisterTouchArea(stockButton);
		MainActivity.inGameHUD.unregisterTouchArea(woodCutterButton);
		MainActivity.inGameHUD.unregisterTouchArea(stoneCutterButton);
		MainActivity.inGameHUD.unregisterTouchArea(roadbutton);
		MainActivity.inGameHUD.unregisterTouchArea(fishingHutButton);
		MainActivity.inGameHUD.unregisterTouchArea(skinnerButton);
		MainActivity.inGameHUD.unregisterTouchArea(butcherButton);
		MainActivity.inGameHUD.unregisterTouchArea(brickFoundryButton);
		MainActivity.inGameHUD.unregisterTouchArea(clayMineButton);
	}

	// Makes our building buttons visible
	public static void menuhudcollect() {
		MainActivity.inGameHUD.registerTouchArea(stoneCutterButton);
		MainActivity.inGameHUD.registerTouchArea(woodCutterButton);
		MainActivity.inGameHUD.registerTouchArea(farmButton);
		MainActivity.inGameHUD.registerTouchArea(huntersLodgeButton);
		MainActivity.inGameHUD.registerTouchArea(fishingHutButton);
		MainActivity.inGameHUD.registerTouchArea(brickFoundryButton);
		MainActivity.inGameHUD.registerTouchArea(clayMineButton);
		woodCutterButton.setAlpha(1);
		stoneCutterButton.setAlpha(1);
		farmButton.setAlpha(1);
		huntersLodgeButton.setAlpha(1);
		fishingHutButton.setAlpha(1);
		brickFoundryButton.setAlpha(1);
		clayMineButton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(skinnerButton);
		skinnerButton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(butcherButton);
		butcherButton.setAlpha(1);
	}

	public static void menuhudmilitary() {
		MainActivity.inGameHUD.registerTouchArea(barrackButton);
		barrackButton.setAlpha(1);
	}

	public static void menuhudproduction() {
		MainActivity.inGameHUD.registerTouchArea(housebutton);
		housebutton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(foodMarketButton);
		foodMarketButton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(skinnerButton);
		skinnerButton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(butcherButton);
		butcherButton.setAlpha(1);
	}

	public static void menuhudculture() {
		MainActivity.inGameHUD.registerTouchArea(theatreButton);
		theatreButton.setAlpha(1);
	}

	public static void menuhudutility() {
		MainActivity.inGameHUD.registerTouchArea(roadbutton);
		roadbutton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(fountainButton);
		fountainButton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(housebutton);
		housebutton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(theatreButton);
		theatreButton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(foodMarketButton);
		foodMarketButton.setAlpha(1);

	}

	public static void menuhudstorage() {
		MainActivity.inGameHUD.registerTouchArea(stockButton);
		stockButton.setAlpha(1);
		MainActivity.inGameHUD.registerTouchArea(siloButton);
		siloButton.setAlpha(1);

	}

	public void initializeImages() {
		inGameHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/ingamehud.png");
		battleHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/battlehud.png");
		buybuttonimage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/buybutton.png");
		roadimage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/road.png");
		cancelbuttonimage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/cancelbutton.png");
		buildingDescriptionHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/housemenu.png");
		cancelImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/cancel.png");
		fountainImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/fountain.png");
		houseButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/housebutton.png");
		buildingDescriptionHouseWaterImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/buildingdescriptionhousewater.png");
		buildingDescriptionHouseWheatImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/buildingdescriptionhousewheat.png");
		buildingDescriptionHouseSkinImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/buildingdescriptionhouseskin.png");
		buildingDescriptionHouseCultureImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/buildingdescriptionhouseculture.png");
		buildingDescriptionHouseInhabitantsImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/buildingdescriptionhouseinhabitants.png");
		farmButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/farmbutton.png");
		siloImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/silo.png");
		MoreInfoImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/moreinformationbutton.png");
		FoodMarketImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/foodmarket.png");
		mainMenuBackgroundImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/mainmenubackground.png");
		mainMenuPlayButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/mainmenuplaygamebutton.png");
		theatreImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/theatre.png");
		marbleImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/marblemap1.png");
		stockplaceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockplace.png");
		woodCutterImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/woodcutter.png");
		stockplacebuttonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockplacebutton.png");
		huntersLodgeImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hunterslodge.png");
		hudMenuProduction = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudmenuproduction.png");
		hudMenuCollect = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudmenucollect.png");
		hudMenuCulture = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudmenuculture.png");
		hudMenuUtility = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudmenuutility.png");
		hudMenuStorage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/menuhudstorage.png");
		aresTempleImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/arestemple.png");
		fountainButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/fountainbutton.png");
		huntersLodgeButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hunterslodgebutton.png");
		woodCutterButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/woodcutterbutton.png");
		siloButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/silobutton.png");
		theatreButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/theatrebutton.png");
		woodResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/woodresource.png");
		marbleResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/marbleresource.png");
		brickResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/brickresource.png");
		skinResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/skinresource.png");
		fishingHutButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/fishinghutbutton.png");
		skinnerButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/skinnerbutton.png");
		butcherButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/skinnerbutton.png");
		skinnerImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/skinner.png");
		butcherImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/skinner.png");
		upgradeButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/upgradebutton.png");
		menuButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/menubutton.png");
		menuQuestButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudobjectives.png");
		menuMapButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/menumap.png");
		menuMainMenuImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/menumainmenu.png");
		gotFoodSymbolImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/housenofood.png");
		menuMapImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/map.png");
		menuMapHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/maphud.png");
		cityIconImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/cityicon.png");
		coinSymbolImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/coinsymbol.png");
		resourcesMenuImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/resourcesmenu.png");
		messageHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/messagehud.png");
		messageConfirmButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/messageconfirm.png");
		messageCancelButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/messagecancel.png");
		menuInhabitantsButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/menuinhabitants.png");
		messageOkButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/messageok.png");
		foodMarketButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/foodmarketbutton.png");
		stoneCutterButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stonecutterbutton.png");
		roadButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/roadbutton.png");
		barrackButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/barrackbutton.png");
		barrackImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/barrack.png");
		hudMenuMilitaryImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudmenumilitary.png");
		trainingHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/traininghud.png");
		trainingNextButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/trainingnext.png");
		trainingPreviousButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/trainingprevious.png");
		trainingHopliteButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/trainingrecruithoplite.png");
		trainingSlingerImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/trainingrecruitslinger.png");
		brickFoundryImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/brickfoundry.png");
		menuMapAttackButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/menumapattackbutton.png");
		menuSaveButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/menusavebutton.png");
		mineDepositClayImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/claybuilding.png");
		hudResourcesButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudresources.png");
		clayMineImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameAtlas, this, "gfx/claybuilding.png");
		grassTileImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/grasstile.png");
		mainMenuLoadButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/mainmenuloadbutton.png");

		menuBattleReturnButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/hudbattlereturnbutton.png");
		buildingHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/buildinghud.png");
		buildingCancelButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/buildingcancelbutton.png");
		buildingConfirmChoiceButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this,
						"gfx/buildingconfirmchoicebutton.png");
		stockChoiceWoodImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockwoodchoice.png");
		stockChoiceAllImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockallchoice.png");
		stockChoiceBrickImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockbrickchoice.png");
		stockChoiceMarbleImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockmarblechoice.png");
		stockChoiceArmorImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockarmorchoice.png");
		stockChoiceClayImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockclaychoice.png");
		stockChoiceSkinImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/stockskinchoice.png");
		hudRemoveBuildingButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudremovebuildings.png");
		hudMapButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudmap.png");
		incomeHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/incomehud.png");
		incomeButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/incomebutton.png");
		brickFoundryButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/brickfoundrybutton.png");
		hudChatButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudchatbutton.png");
		hudMilitaryButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/hudmilitarybutton.png");
		clayButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GameAtlas, this, "gfx/claybuildingbutton.png");
	}

	public void initializeTiledImages() {
		HouseImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/house.png", 19, 1);
		stockImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/stock.png", 18, 2);
		upgradeArrowImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/upgradearrow.png",
						5, 1);
		stoneCutterImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/stonecutter.png",
						5, 1);
		fishingHutImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/fishinghut.png", 2,
						1);
		DeerImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/deer.png", 4, 1);
		treeImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/tree.png", 4, 1);
		hudWorkersImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/workershud.png", 2,
						1);
		farmImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/farm.png", 6, 1);
		placebuildingimage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this,
						"gfx/placebuildingtiles.png", 20, 1);
		fishSpotImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/fishspot.png", 10,
						1);
		boatImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/boat.png", 8, 1);
		npcStoneCutter = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this,
						"gfx/npcstonecutter.png", 18, 1);
		hopliteImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/hoplite.png", 8, 8);
		cloudImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/cloud.png", 8, 1);
		marbleTileImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/marbletile.png", 9,
						1);
		clayTileImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/claytile.png", 9, 1);
		mainMenuDoorImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(GameAtlas, this, "gfx/mainmenudoor.png",
						6, 1);
	}

	public static void initializeArrayLists() {
		placeBuildings = new ArrayList<PlaceBuilding>();
		Houses = new ArrayList<House>();
		Roads = new ArrayList<Road>();
		Fountains = new ArrayList<Fountain>();
		Farms = new ArrayList<Farm>();
		Silos = new ArrayList<Silo>();
		Trees = new ArrayList<Tree>();
		Theatres = new ArrayList<Theatre>();
		FoodMarkets = new ArrayList<FoodMarket>();
		StoneCutters = new ArrayList<StoneCutter>();
		WoodCutters = new ArrayList<WoodCutter>();
		Stocks = new ArrayList<Stock>();
		mineDepositClays = new ArrayList<MineDepositClay>();
		brickFoundrys = new ArrayList<BrickFoundry>();
		Stocks = new ArrayList<Stock>();
		stockSpaceTexts = new ArrayList<Text>();
		FishingHuts = new ArrayList<FishingHut>();
		huntersLodges = new ArrayList<HuntersLodge>();
		skinners = new ArrayList<Skinner>();
		Hoplites = new ArrayList<Hoplite>();
		butchers = new ArrayList<Butcher>();
		fishspots = new ArrayList<FishSpot>();
		mineDepositBronzes = new ArrayList<MineDepositBronze>();
		asObjects = new ArrayList<AnimatedSpriteObject>();
		sObjects = new ArrayList<SpriteObject>();
		cityIcons = new ArrayList<CityIcon>();
		cityMessages = new ArrayList<Text>();
		barracks = new ArrayList<Barrack>();
		removeableTexts = new ArrayList<RemoveableText>();
		trainingTexts = new ArrayList<RemoveableText>();
		detachableObjects = new ArrayList<DetachableObjects>();
		grassTiles = new ArrayList<Sprite>();
		marbleTiles = new ArrayList<MarbleTile>();
		ClayTiles = new ArrayList<ClayTile>();
		clayMines = new ArrayList<MineDepositClay>();
		brickFoundrys = new ArrayList<BrickFoundry>();
		stockChoiceButtons = new ArrayList<StockChoiceButton>();
		chatHistoryTexts = new ArrayList<Text>();
		for (int i = 0; i < MainActivity.messageHistory.length; i++)
			MainActivity.messageHistory[i] = "";
	}

	public static int getLevel() {
		if (CurrentMission - 1 <= LevelInfo.level1goals.length)
			return CurrentMission - 1;
		else
			return -1;
	}

	public void setUpHUD() {
		inGameHUDSprite = new Sprite(0, 0, inGameHUDImage,
				this.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(final GLState pGLState, final Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		inGameHUD.attachChild(inGameHUDSprite);
		// mScene.registerTouchArea(inGameHUDSprite);
		for (int i = -2000; i < 2000; i += main.grassTileImage.getWidth()) {
			for (int j = -2000; j < 2000; j += main.grassTileImage.getHeight()) {
				grassTile = new Sprite(i, j, grassTileImage,
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
		GoldText = new Text(450, 16, gameFont, Integer.toString(Gold), 20,
				this.getVertexBufferObjectManager());
		GoldText.setColor(0f, 1f, 0f);
		menuQuestText = new Text(48 + 4, 2 + 48 + 32 + 32, smallFont, "", 100,
				this.getVertexBufferObjectManager());
		InhabitantsText = new Text(659, 16, gameFont, "1000", 20,
				this.getVertexBufferObjectManager());
		InhabitantsText.setColor(1f, 1f, 1f);
		MonthText = new Text(800 - 24, 16, gameFont, "January", 20,
				this.getVertexBufferObjectManager());
		MonthText.setColor(1f, 1f, 1f);
		buildingDescriptionHUD = new Sprite(0, 69, buildingDescriptionHUDImage,
				this.getVertexBufferObjectManager());
		buildingDescriptionHUD.setAlpha(1f);
		buildingDescriptionCancel = new BuildingDescriptionCancel(48
				+ buildingDescriptionHUD.getWidth() - cancelImage.getWidth(),
				81 + 1, cancelImage, this.getVertexBufferObjectManager());

		buildingDescriptionTitle = new Text(48 + 24, 82, gameFont,
				buildingDescriptionTitleString, 30,
				this.getVertexBufferObjectManager());
		buildingDescriptionDetail = new Text(48 + 8, 82 + 238 + 4, smallFont,
				buildingDescriptionDetailString, 10000,
				this.getVertexBufferObjectManager());
		buildingDescriptionDetail.setColor(0, 0, 0);
		HUDInhabitants = new HUDInhabitantsButton(605, 0,
				menuInhabitantsButtonImage,
				this.getVertexBufferObjectManager(), this);
		HUDInhabitants.setAlpha(0);

		
	

		HUDWorkers = new AnimatedSprite(
				640 - 256 - buildingDescriptionHouseInhabitantsImage.getWidth(),
				2 + 32, hudWorkersImage, this.getVertexBufferObjectManager());
		HUDWorkers.setAlpha(0);
		HUDWorkers.stopAnimation();
		resourcesMenu = new Sprite(0, 69, resourcesMenuImage,
				this.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(final GLState pGLState, final Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		resourcesMenu.setAlpha(0);
		hudMoreInfoWorkers = new HUDMoreInfoWorkers(
				640 - 256 - buildingDescriptionHouseInhabitantsImage.getWidth(),
				2 + 32 + 99, MoreInfoImage, this.getVertexBufferObjectManager());
		hudMoreInfoInhabitants = new HUDMoreInfoInhabitants(
				640 - 256 - buildingDescriptionHouseInhabitantsImage.getWidth(),
				2 + 32 + 197, MoreInfoImage, this
						.getVertexBufferObjectManager());

		woodResource = new Sprite(4, 0, woodResourceImage,
				this.getVertexBufferObjectManager());
		woodResource.setAlpha(0);
		marbleResource = new Sprite(4, 0, marbleResourceImage,
				this.getVertexBufferObjectManager());
		marbleResource.setAlpha(0);
		brickResource = new Sprite(4, 0, brickResourceImage,
				this.getVertexBufferObjectManager());
		brickResource.setAlpha(0);
		skinResource = new Sprite(4, 0, skinResourceImage,
				this.getVertexBufferObjectManager());
		skinResource.setAlpha(0);
		upgradeButton = new UpgradeButton(
				640 - 128 - 48 - buildingDescriptionHUD.getWidth(),
				80 + buildingDescriptionHUD.getHeight(), upgradeButtonImage,
				this.getVertexBufferObjectManager());
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

		inGameHUD.attachChild(GoldText);
		inGameHUD.attachChild(InhabitantsText);
		inGameHUD.attachChild(MonthText);
		inGameHUD.attachChild(HUDInhabitants);
		inGameHUD.attachChild(buildingDescriptionHUD);
		inGameHUD.attachChild(buildingDescriptionCancel);
		inGameHUD.attachChild(buildingDescriptionTitle);
		inGameHUD.attachChild(buildingDescriptionDetail);
		inGameHUD.attachChild(menuQuestText);
		inGameHUD.attachChild(HUDWorkers);
//		inGameHUD.attachChild(hudMapButton);
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
			stockspacetext = new Text(
					640 - 256 - buildingDescriptionHUDImage.getWidth() + 128,
					9 + 112 + 18 * i, smallFont, "", 200,
					this.getVertexBufferObjectManager());
			stockSpaceTexts.add(stockspacetext);
			inGameHUD.attachChild(stockspacetext);
		}
		buildingDescriptionHUD.setAlpha(0);
		buildingDescriptionCancel.setAlpha(0);
		buildingDescriptionTitle.setAlpha(0);
		buildingDescriptionDetail.setAlpha(0);

		// All the buttons in the game

		housebutton = new HouseButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				houseButtonImage, this.getVertexBufferObjectManager());
		barrackButton = new BarrackButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				barrackButtonImage, this.getVertexBufferObjectManager());
		barrackButton.setAlpha(0);
		roadbutton = new RoadButton(CAMERA_WIDTH - 97 * 2, 67 * 4,
				roadButtonImage, this.getVertexBufferObjectManager());
		fountainButton = new FountainButton(CAMERA_WIDTH - 97, 67 * 2,
				fountainButtonImage, this.getVertexBufferObjectManager());

		foodMarketButton = new FoodMarketButton(CAMERA_WIDTH - 97, 67 * 3,
				foodMarketButtonImage, this.getVertexBufferObjectManager());

		theatreButton = new TheatreButton(CAMERA_WIDTH - 97 * 2, 67 * 3,
				theatreButtonImage, this.getVertexBufferObjectManager());

		stockButton = new StockButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				stockplacebuttonImage, this.getVertexBufferObjectManager());
		siloButton = new SiloButton(CAMERA_WIDTH - 97, 67 * 2, siloButtonImage,
				this.getVertexBufferObjectManager());

		farmButton = new FarmButton(CAMERA_WIDTH - 97 * 2, 67 * 2,
				farmButtonImage, this.getVertexBufferObjectManager());
		fishingHutButton = new FishingHutButton(CAMERA_WIDTH - 97 * 2, 67 * 5,
				fishingHutButtonImage, this.getVertexBufferObjectManager());
		stoneCutterButton = new StoneCutterButton(CAMERA_WIDTH - 97, 67 * 2,
				stoneCutterButtonImage, this.getVertexBufferObjectManager());
		woodCutterButton = new WoodCutterButton(CAMERA_WIDTH - 97 * 2, 67 * 3,
				woodCutterButtonImage, this.getVertexBufferObjectManager());
		clayMineButton = new ClayMineButton(CAMERA_WIDTH - 97 * 1, 67 * 5,
				clayButtonImage, this.getVertexBufferObjectManager());
		brickFoundryButton = new BrickFoundryButton(CAMERA_WIDTH - 97 * 2,
				67 * 6, brickFoundryButtonImage,
				this.getVertexBufferObjectManager());
		skinnerButton = new SkinnerButton(CAMERA_WIDTH - 97, 67 * 4,
				skinnerButtonImage, this.getVertexBufferObjectManager());
		butcherButton = new ButcherButton(CAMERA_WIDTH - 97 * 2, 67 * 4,
				butcherButtonImage, this.getVertexBufferObjectManager());

		huntersLodgeButton = new HuntersLodgeButton(CAMERA_WIDTH - 97, 67 * 3,
				huntersLodgeButtonImage, this.getVertexBufferObjectManager());

	
		trainingHUD = new Sprite(89, 143, trainingHUDImage,
				this.getVertexBufferObjectManager());
		trainingNextButton = new TrainingNextButton(trainingHUD.getX() + 192,
				trainingHUD.getY() + 287, trainingNextButtonImage,
				this.getVertexBufferObjectManager());
		trainingPreviousButton = new TrainingPreviousButton(
				trainingHUD.getX() + 63, trainingHUD.getY() + 287,
				trainingPreviousButtonImage,
				this.getVertexBufferObjectManager());
		trainingHopliteButton = new Sprite(trainingHUD.getX() + 63,
				trainingHUD.getY() + 64, trainingHopliteButtonImage,
				this.getVertexBufferObjectManager());
		trainingSlinger = new Sprite(trainingHUD.getX() + 63,
				trainingHUD.getY() + 64, trainingSlingerImage,
				this.getVertexBufferObjectManager());
		trainingBuyButton = new TrainingBuyButton(trainingHUD.getX() + 635,
				trainingHUD.getY() + 270, buybuttonimage,
				this.getVertexBufferObjectManager());

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
		addCityIcons();

		camera.setHUD(inGameHUD);

	}

	private void addCityIcons() {
		if (cityIcons.size() < CITY.length) {
			for (int i = 0; i < CITY.length; i++) {
				cityIcon = new CityIcon(Float.parseFloat((CITY[i][6])),
						Float.parseFloat(CITY[i][7]), cityIconImage,
						this.getVertexBufferObjectManager(), i);
				cityIcons.add(cityIcon);
				cityIcon.setAlpha(0);
			}
		}
	}

	/**
	 * Reattachs some objects after exiting and entering the game
	 */
	public static void attachStuffEnterGame() {
		/*
		 * for(int i = 0; i < cityIcons.size();i++){
		 * inGameHUD.attachChild(cityIcons.get(i)); }
		 */
	}

	public static void saveState() {
		dataBase.deleteDataBase();
		MainActivity.objectAmount = -1;
		// Sort objects for y coordinates
		for (int i = 0; i < MainActivity.sObjects.size(); i++) {
			Debug.e(sObjects.toString());
			for (int j = 0; j < MainActivity.sObjects.size(); j++) {
				// If y1 < y2, replace y1 with
				if (sObjects.get(i).getY() < sObjects.get(j).getY()) {
					SpriteObject obj = sObjects.get(i);
					sObjects.set(i, sObjects.get(j));
					sObjects.set(j, obj);
				}

			}
		}
		for (int i = 0; i < MainActivity.sObjects.size(); i++) {
			sObjects.get(i).setID(
					MainActivity.objectAmount + MainActivity.startID + 1);
			MainActivity.objectAmount++;
			Debug.e(String.valueOf(sObjects.get(i).id));
		}
		for (int i = 0; i < MainActivity.asObjects.size(); i++) {
			for (int j = 0; j < MainActivity.asObjects.size(); j++) {
				AnimatedSpriteObject obj = asObjects.get(i);
				// If y1 < y2, replace y1 with
				if (asObjects.get(i).getY() < asObjects.get(j).getY()) {
					asObjects.set(i, asObjects.get(j));
					asObjects.set(j, obj);
					// Debug.e(asObjects.toString());
				}

			}
		}
		for (int i = 0; i < MainActivity.asObjects.size(); i++) {
			asObjects.get(i).setID(
					MainActivity.objectAmount + MainActivity.startID + 1);
			MainActivity.objectAmount++;
			Debug.e(String.valueOf(asObjects.get(i).id));
		}
		int[] array = { 2, 6, 3, 7, 9, 3, 5, 1, 0, 43, 6 };
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i] < array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;

				}
			}

		}
		String res = "";
		for (int i = 0; i < array.length; i++) {
			res += String.valueOf(array[i]) + " ";
			Debug.e(res);
		}
		saveInfo();
		for (int i = 0; i < sObjects.size(); i++) {
			sObjects.get(i).save();
		}
		for (int i = 0; i < asObjects.size(); i++) {
			asObjects.get(i).save();
		}
	}

	public static void saveInfo() {
		MainActivity.dataBase.add("Gold", MainActivity.Gold, 0);
		MainActivity.dataBase
				.add("Inhabitants", MainActivity.Inhabitants[0], 1);
		MainActivity.dataBase.add("Building Workers",
				MainActivity.buildingWorkers, 2);
		MainActivity.dataBase.add("MilitaryHoplite",
				militaryHoplite, 3);
		MainActivity.dataBase.add("MilitarySlinger",
				militarySlinger, 4);
		MainActivity.dataBase.add("Marble", MainActivity.Marble, 5);
		MainActivity.dataBase.add("Wood", MainActivity.Wood, 6);
		MainActivity.dataBase.add("Armor", MainActivity.Armor, 7);
		MainActivity.dataBase.add("Skin", MainActivity.Skin, 8);
		MainActivity.dataBase.add("Sculptures", MainActivity.Sculptures, 9);
		MainActivity.dataBase.add("Fish", MainActivity.Fish, 10);
		MainActivity.dataBase.add("Bronze", MainActivity.Bronze, 11);
		MainActivity.dataBase.add("Brick", MainActivity.Brick, 12);
		MainActivity.dataBase.add("Meat", MainActivity.Meat, 13);
		MainActivity.dataBase.add("Clay", MainActivity.Clay, 14);
	}

	/**
	 * ADds the neccesery touch areas for the map
	 */
	public static void addMapTouchAreas() {

		for (int i = 0; i < cityIcons.size(); i++) {
			if (Integer.valueOf(CITY[i][8]) == 1) {
				inGameHUD.registerTouchArea(cityIcons.get(i));
				cityIcons.get(i).setAlpha(1);
			}
		}
	}

	public static void removeMapTouchAreas() {
		for (int i = 0; i < cityIcons.size(); i++) {
			inGameHUD.unregisterTouchArea(cityIcons.get(i));
			cityIcons.get(i).setAlpha(0);
		}
	}

	/**
	 * Remove the in game menu
	 */
	public static void removeMenu() {
		if (menuMainMenuButton != null) {
			MainActivity.removeEntity(menuMainMenuButton);
			MainActivity.removeEntity(menuSaveButton);
			MainActivity.removeEntity(rectangleBlackScreen);
			Debug.e("Remove menu");
			inGameHUD.unregisterTouchArea(MainActivity.menuMainMenuButton);
			inGameHUD.unregisterTouchArea(MainActivity.menuSaveButton);
			menuMainMenuButton = null;
			menuSaveButton = null;
			MainActivity.addBuildingTouchAreas();
			MainActivity.addHudTouchAreas();
		}
	}

	/**
	 * Shows the in game menu
	 */
	public static void showMenu() {
		if (menuMainMenuButton == null) {
			menuMainMenuButton = new MainMenuButton(396, 256 - 96 - 64,
					menuMainMenuImage, main.getVertexBufferObjectManager(),
					main);
			menuSaveButton = new MenuSaveButton(396, 256 + (96 + 64) * 2,
					menuSaveButtonImage, main.getVertexBufferObjectManager());

			rectangleBlackScreen = new Rectangle(0, 0,
					MainActivity.CAMERA_WIDTH, MainActivity.CAMERA_HEIGHT,
					main.getVertexBufferObjectManager());
			rectangleBlackScreen.setColor(0f, 0f, 0f);
			rectangleBlackScreen.setAlpha(0.95f);
			MainActivity.removeBuildingTouchAreas();
			MainActivity.removeHudTouchAreas();
			MainActivity.inGameHUD.attachChild(rectangleBlackScreen);
			MainActivity.inGameHUD.attachChild(menuMainMenuButton);
			MainActivity.inGameHUD.attachChild(menuSaveButton);
			// MainActivity.sObjects.add(menuQuestButton);
			// MainActivity.sObjects.add(menuMainMenuButton);
			// MainActivity.sObjects.add(menuMapButton);
			inGameHUD.registerTouchArea(MainActivity.menuMainMenuButton);
			inGameHUD.registerTouchArea(MainActivity.menuSaveButton);
		}
	}

	/**
	 * Goes to the main menu
	 */
	public static void goToMainMenu() {
		wipeInGameInfo();
		leaveGame();
		Menu = "MainMenu";
	}

	/**
	 * Removes the popup message
	 */
	public static void removeMessage() {
		String temp;

		MainActivity.PAUSE = false;
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

	public static void removeRecentMessage() {
		for (int i = 0; i < MainActivity.recentMessages.length; i++) {
			String temp = MainActivity.recentMessages[i];
			if (i != MainActivity.recentMessages.length - 1) {
				MainActivity.recentMessages[i] = MainActivity.recentMessages[i + 1];
			} else if (i == MainActivity.recentMessages.length - 1) {
				MainActivity.recentMessages[i - 1] = MainActivity.recentMessages[i];
			}
		}
		for (int i = 0; i < MainActivity.recentMessages.length; i++) {
			if (MainActivity.recentMessages[i] != ""
					&& MainActivity.recentMessages[i] != null) {
				Debug.e("WHY NO LOAD");
				MainActivity.MessagePopUpNoRemove(
						MainActivity.recentMessages[i],
						org.andengine.util.color.Color.WHITE);
				MainActivity.recentMessages[0] = "";
				break;
			}
		}
	}

	/**
	 * Removes the HUD for recruiting soldiers
	 */
	public static void removeTrainingHUD() {
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
	public static void addTrainingHUD() {
		trainingHUD.setAlpha(1);
		trainingNextButton.setAlpha(1);
		trainingPreviousButton.setAlpha(1);
		trainingSlinger.setAlpha(1);
		trainingBuyButton.setAlpha(1);
		inGameHUD.registerTouchArea(trainingNextButton);
		inGameHUD.registerTouchArea(trainingPreviousButton);
		inGameHUD.registerTouchArea(trainingBuyButton);
		MainActivity.trainingUnitCount = 1;
	}

	/**
	 * Updates the choice of Unit in recruitment
	 */
	public static void trainingHUDUpdateUnit() {
		removeTrainingHUDUnits();
		if (trainingUnitCount < 1)
			trainingUnitCount = 1;
		if (trainingUnitCount == 1) {
			MainActivity.trainingSlinger.setAlpha(1);
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
			MainActivity.trainingHopliteButton.setAlpha(1);
		}
	}

	/**
	 * Removes the units in recruitment
	 */
	private static void removeTrainingHUDUnits() {
		for (int i = 0; i < trainingTexts.size(); i++) {
			trainingTexts.get(i).remove = true;
		}
		MainActivity.trainingHopliteButton.setAlpha(0);
		MainActivity.trainingSlinger.setAlpha(0);

	}

	public static void leaveMap() {
		removeMapTouchAreas();
		MainActivity.addHudTouchAreas();
		MainActivity.inGameHUD.unregisterTouchArea(MainActivity.menuMap);
		MainActivity.menuMapHUD.setAlpha(0);
		MainActivity.menuMap.setAlpha(0);
		MainActivity.moveText.setText("");
		MainActivity.moveTextSmall.setText("");
		MainActivity.moveTextSmall2.setText("");
		for (int i = 0; i < MainActivity.cityMessageSize; i++) {
			removeEntity(MainActivity.cityMessages.get(i));
		}
		MainActivity.cityMessages = new ArrayList<Text>();
		MainActivity.inGameHUD.unregisterTouchArea(menuMap);
		removeEntity(menuMap);
		removeEntity(menuMapHUD);

		for (int i = 0; i < MainActivity.cityIcons.size(); i++) {
			removeEntity(MainActivity.cityIcons.get(i));
			MainActivity.cityIcons.get(i).removeText();
		}
		for (int i = 0; i < MainActivity.cityMessages.size(); i++) {
			MainActivity.cityMessages.get(i).setText("");
		}
		menuMap = null;
		menuMapHUD = null;
	}

	public static void removePlaceBuildings() {
		for (int i = 0; i < placeBuildings.size(); i++) {
			if (placeBuildings.get(i) != null) {
				placeBuildings.get(i).remove = true;
				placeBuildings.get(i).remove();
				placeBuildings.remove(placeBuildings.get(i));
			}
		}
	}

	public static void changePlaceBuilding(String string) {//1
		removePlaceBuildings();
		MainActivity.boolplacebuilding = true;
		placeBuildingJumpY = 0;
		placeBuildingJumpX = 0;
		MainActivity.currentBuilding = string;
		if (MainActivity.currentBuilding == "Barrack")
			placebuilding = new PlaceBuilding(touchx, touchy, barrackImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "House")
			placebuilding = new PlaceBuilding(touchx, touchy, HouseImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Farm")
			placebuilding = new PlaceBuilding(touchx, touchy, farmImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Food Market")
			placebuilding = new PlaceBuilding(touchx, touchy, FoodMarketImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Fountain")
			placebuilding = new PlaceBuilding(touchx, touchy, fountainImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Silo") {
			placebuilding = new PlaceBuilding(touchx, touchy, siloImage,
					main.getVertexBufferObjectManager());
			placeBuildingJumpY = -11;
		} else if (MainActivity.currentBuilding == "Skinner")
			placebuilding = new PlaceBuilding(touchx, touchy, skinnerImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Stock")
			placebuilding = new PlaceBuilding(touchx, touchy, stockplaceImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Road")
			placebuilding = new PlaceBuilding(touchx, touchy, roadimage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Butcher")
			placebuilding = new PlaceBuilding(touchx, touchy, butcherImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Fishing Hut")
			placebuilding = new PlaceBuilding(touchx, touchy, fishingHutImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Stone Cutter")
			placebuilding = new PlaceBuilding(touchx, touchy, stoneCutterImage,
					main.getVertexBufferObjectManager());
		else if (MainActivity.currentBuilding == "Theatre") {
			placebuilding = new PlaceBuilding(touchx, touchy, theatreImage,
					main.getVertexBufferObjectManager());
			placeBuildingJumpX = 6;
			placeBuildingJumpY = -59;
		} else if (MainActivity.currentBuilding == "Hunters Lodge")
			placebuilding = new PlaceBuilding(touchx, touchy,
					huntersLodgeImage, main.getVertexBufferObjectManager());
		else
			placebuilding = new PlaceBuilding(touchx, touchy, woodCutterImage,
					main.getVertexBufferObjectManager());

		placeBuildings.add(placebuilding);
		mScene.attachChild(placebuilding);
	}

	public static void returnGameFromBattleField() {
		battleHUD.clearChildScene();
		battleHUD.clearTouchAreas();
		MainActivity.camera.setHUD(null);
		leaveMainMenu("load");
		militaryHoplite += militaryHopliteWar / 2;
		Menu = "Game";
	}

	public static void resetArrayLists() {
		placeBuildings = new ArrayList<PlaceBuilding>();
		Houses = new ArrayList<House>();
		Roads = new ArrayList<Road>();
		Fountains = new ArrayList<Fountain>();
		Farms = new ArrayList<Farm>();
		Silos = new ArrayList<Silo>();
		Trees = new ArrayList<Tree>();
		Theatres = new ArrayList<Theatre>();
		FoodMarkets = new ArrayList<FoodMarket>();
		StoneCutters = new ArrayList<StoneCutter>();
		WoodCutters = new ArrayList<WoodCutter>();
		Stocks = new ArrayList<Stock>();
		mineDepositClays = new ArrayList<MineDepositClay>();
		brickFoundrys = new ArrayList<BrickFoundry>();
		Stocks = new ArrayList<Stock>();
		stockSpaceTexts = new ArrayList<Text>();
		FishingHuts = new ArrayList<FishingHut>();
		huntersLodges = new ArrayList<HuntersLodge>();
		skinners = new ArrayList<Skinner>();
		Hoplites = new ArrayList<Hoplite>();
		butchers = new ArrayList<Butcher>();
		fishspots = new ArrayList<FishSpot>();
		mineDepositBronzes = new ArrayList<MineDepositBronze>();
		asObjects = new ArrayList<AnimatedSpriteObject>();
		sObjects = new ArrayList<SpriteObject>();
		cityIcons = new ArrayList<CityIcon>();
		cityMessages = new ArrayList<Text>();
		barracks = new ArrayList<Barrack>();
		removeableTexts = new ArrayList<RemoveableText>();
		trainingTexts = new ArrayList<RemoveableText>();
		detachableObjects = new ArrayList<DetachableObjects>();
		grassTiles = new ArrayList<Sprite>();
		clayMines = new ArrayList<MineDepositClay>();
		brickFoundrys = new ArrayList<BrickFoundry>();
		stockChoiceButtons = new ArrayList<StockChoiceButton>();
	}

	public static void createBuildingHUD(String building) {
		removeBuildingHUD();
		buildingHUDTexts = new ArrayList<Text>();
		buildingHUD = new Sprite(0, 102, buildingHUDImage,
				main.getVertexBufferObjectManager());
		buildingConfirmChoiceButton = new BuildingConfirmChoiceButton(
				buildingHUD.getX() + 480, buildingHUD.getY() + 368,
				buildingConfirmChoiceButtonImage,
				main.getVertexBufferObjectManager(), building);
		buildingCancelButton = new BuildingCancelButton(
				buildingHUD.getX() + 768, buildingHUD.getY() + 368,
				buildingCancelButtonImage, main.getVertexBufferObjectManager());
		inGameHUD.attachChild(buildingHUD);
		inGameHUD.attachChild(buildingCancelButton);
		inGameHUD.attachChild(buildingConfirmChoiceButton);
		inGameHUD.registerTouchArea(buildingConfirmChoiceButton);
		inGameHUD.registerTouchArea(buildingCancelButton);
		buybutton = new BuyButton(CAMERA_WIDTH - 195
				- buybuttonimage.getWidth(), CAMERA_HEIGHT
				- buybuttonimage.getHeight(), buybuttonimage,
				main.getVertexBufferObjectManager());
		cancelbutton = new CancelButton(CAMERA_WIDTH - 195
				- buybuttonimage.getWidth(), CAMERA_HEIGHT
				- (buybuttonimage.getHeight() * 2), cancelbuttonimage,
				main.getVertexBufferObjectManager());
		inGameHUD.attachChild(buybutton);
		inGameHUD.attachChild(cancelbutton);
		inGameHUD.registerTouchArea(buildingHUD);
		inGameHUD.registerTouchArea(buybutton);
		inGameHUD.registerTouchArea(cancelbutton);
		
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQBARRACK) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSBARRACK > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTBARRACKCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Marble < ConstantBuildings.COSTBARRACKMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (MainActivity.Wood < ConstantBuildings.COSTBARRACKWOOD) {
				canBuild = false;
				buildingHUDTexts.get(4).setColor(1, 0, 0);
			}
			if (MainActivity.Brick < ConstantBuildings.COSTBARRACKBRICK) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQARMORY) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSARMORY > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTARMORYCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Marble < ConstantBuildings.COSTARMORYMARLBE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (MainActivity.Brick < ConstantBuildings.COSTARMORYBRICK) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQBRICKFOUNDRY) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTBRICKFOUNDRYCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSBRICKFOUNDRY > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Marble < ConstantBuildings.COSTBRICKFOUNDRYMARBLE) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQBUTCHER) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSBUTCHER > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTBUTCHERCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Wood < ConstantBuildings.COSTBUTCHERWOOD) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQFARM) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSFARM > MainActivity.MaxWorkers) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQFISHINGHUT) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSFISHINGHUT > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTFISHINGHUTCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Wood < ConstantBuildings.COSTFISHINGHUTWOOD) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQFOODMARKET) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSFOODMARKET > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTFOODMARKETCOIN) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQFOUNTAIN) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTFOUNTAINCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSFOUNTAIN > MainActivity.MaxWorkers) {
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
			if (MainActivity.Gold < ConstantBuildings.COSTHOUSE)
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			if (MainActivity.Inhabitants[0] > MainActivity.Workers + 400) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQHUNTERSLODGE) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTHUNTERSLODGECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSHUNTERSLODGE > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Wood < ConstantBuildings.COSTHUNTERSLODGEWOOD) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQMINEDEPOSITCLAY) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSMINEDEPOSITCLAY > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTCLAYMINECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Marble < ConstantBuildings.COSTCLAYMINEMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (MainActivity.Wood < ConstantBuildings.COSTCLAYMINEWOOD) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQMINEDEPOSITBRONZE) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers
					+ ConstantBuildings.WORKERSMINEDEPOSITBRONZE > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTBRONZEMINECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Marble < ConstantBuildings.COSTBRONZEMINEMARBLE) {
				canBuild = false;
				buildingHUDTexts.get(3).setColor(1, 0, 0);
			}
			if (MainActivity.Brick < ConstantBuildings.COSTBRONZEMINEBRICK) {
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
			if (MainActivity.Gold < ConstantBuildings.COSTROADCOIN)
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			if (MainActivity.Gold < ConstantBuildings.COSTROADCOIN) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQSILO) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSSILO > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTSILO) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQSKINNER) {
				buildingHUDTexts.get(0).setColor(1, 0, 0);
				canBuild = false;
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSSKINNER > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTSKINNERCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Wood < ConstantBuildings.COSTSKINNERWOOD) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQSTOCK) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSSTOCK > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTSTOCKCOIN) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQSTONECUTTER) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSSTONECUTTER > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTSTONECUTTERCOIN) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQTHEATRE) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSTHEATRE > MainActivity.MaxWorkers)
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			if (MainActivity.Gold < ConstantBuildings.COSTTHEATRECOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (MainActivity.Marble < ConstantBuildings.COSTTHEATREMARBLE) {
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
			if (MainActivity.HouseLevel < ConstantBuildings.HOUSEREQWOODCUTTER) {
				canBuild = false;
				buildingHUDTexts.get(0).setColor(1, 0, 0);
			}
			if (MainActivity.Workers + ConstantBuildings.WORKERSWOODCUTTER > MainActivity.MaxWorkers) {
				canBuild = false;
				buildingHUDTexts.get(1).setColor(1, 0, 0);
			}
			if (MainActivity.Gold < ConstantBuildings.COSTWOODCUTTERCOIN) {
				canBuild = false;
				buildingHUDTexts.get(2).setColor(1, 0, 0);
			}
			if (!MainActivity.canBuild) {
				buildingHUDTexts.get(11).setText(
						"You do not fulfill the requirments to construct \na "
								+ building);
				buildingHUDTexts.get(11).setColor(1.0f, 0f, 0f);

			}
		}
	}

	public static void removeBuildingHUD() {
		if (MainActivity.cancelbutton != null) {
			CancelButton.Cancel();
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

	public static void removeBuildingHUDKeepBuyButton() {

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
	public static void MakeToast(String Msg) {
		message = Msg;
		Handles.sendEmptyMessage(0);
	}
	
	/**
	 * Creates a toast msg
	 * 
	 */
	public static void MakeToast(String Msg, int time) {
		message = Msg;
		Handles.sendEmptyMessage(0);
	}

	static Handler Handles = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (msg.what == 0) {
				Toast.makeText(main, message, Toast.LENGTH_SHORT).show();
			}
		};
	};
	public static boolean removeBuildings = false;
	public static int bribe = 0;
	public static boolean menuIncomeOpen = false;

	public static void addStockchoices() {
		StockChoiceButton temp = new StockChoiceButton(
				MainActivity.buildingHUD.getX() + 32,
				MainActivity.buildingHUD.getY() + 431
						- main.stockChoiceWoodImage.getHeight() * 2,
				main.stockChoiceMarbleImage,
				main.getVertexBufferObjectManager(), "Marble");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(MainActivity.buildingHUD.getX() + 32
				+ main.stockChoiceWoodImage.getWidth(),
				MainActivity.buildingHUD.getY() + 431
						- main.stockChoiceWoodImage.getHeight() * 2,
				main.stockChoiceWoodImage, main.getVertexBufferObjectManager(),
				"Wood");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(MainActivity.buildingHUD.getX() + 32
				+ main.stockChoiceWoodImage.getWidth() * 2,
				MainActivity.buildingHUD.getY() + 431
						- main.stockChoiceWoodImage.getHeight(),
				main.stockChoiceBrickImage,
				main.getVertexBufferObjectManager(), "Brick");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(MainActivity.buildingHUD.getX() + 32
				+ main.stockChoiceWoodImage.getWidth() * 4,
				MainActivity.buildingHUD.getY() + 431
						- main.stockChoiceWoodImage.getHeight(),
				main.stockChoiceAllImage, main.getVertexBufferObjectManager(),
				"");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(MainActivity.buildingHUD.getX() + 32
				+ main.stockChoiceWoodImage.getWidth() * 3,
				MainActivity.buildingHUD.getY() + 431
						- main.stockChoiceWoodImage.getHeight(),
				main.stockChoiceArmorImage,
				main.getVertexBufferObjectManager(), "Armor");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(MainActivity.buildingHUD.getX() + 32,
				MainActivity.buildingHUD.getY() + 431
						- main.stockChoiceWoodImage.getHeight(),
				main.stockChoiceSkinImage, main.getVertexBufferObjectManager(),
				"Skin");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
		temp = new StockChoiceButton(MainActivity.buildingHUD.getX() + 32
				+ main.stockChoiceWoodImage.getWidth() * 1,
				MainActivity.buildingHUD.getY() + 431
						- main.stockChoiceWoodImage.getHeight(),
				main.stockChoiceClayImage, main.getVertexBufferObjectManager(),
				"Clay");
		stockChoiceButtons.add(temp);
		inGameHUD.attachChild(temp);
		inGameHUD.registerTouchArea(temp);
	}

	public static void removeSpriteObject() {
		SpriteObject obj = (SpriteObject) ID;
		obj.removeEntity();
	}

	public static void removeAnimatedSpriteObject() {
		AnimatedSpriteObject obj = (AnimatedSpriteObject) ID;
		obj.removeEntity();
	}

	public static void updateMessageHistory(String str) {

		for (int i = MainActivity.messageHistory.length - 1; i >= 0; i--) {
			if (str.equals(MainActivity.messageHistory[0]))
				break;
			if (i == MainActivity.messageHistory.length) {
				MainActivity.messageHistory[i] = "";
			} else if (i == 0) {
				MainActivity.messageHistory[i] = str;
			} else {
				MainActivity.messageHistory[i] = MainActivity.messageHistory[i - 1];
			}
		}
		MainActivity.messageHistory[0] = str;
	}

	public static void cancelButtonCancel() {
		CancelButton.Cancel();
	}

	public static int getMilitaryOffensivePower() {
		int power = 0;
		power += militarySlingerWar * Constant.POWERSLINGER;
		power += militaryHopliteWar * Constant.POWERHOPLITE;

		return power;
	}

	public static void closeMenus() {
		if (MainActivity.menuIncomeOpen)
			inGameHUD.getIncomeButton().close();
		if (MainActivity.menuResourcesOpen)
			HUDResourceMenuButton.Cancel();
	}

	public static void removeHudTouchAreas() {
		inGameHUD.unregisterTouchAreas();
//		MainActivity.inGameHUD.unregisterTouchArea(hudMapButton);
//		MainActivity.inGameHUD.unregisterTouchArea(incomeButton);
//		MainActivity.inGameHUD.unregisterTouchArea(MainActivity.menuButton);
//		MainActivity.inGameHUD
//				.unregisterTouchArea(main.hudRemoveBuildingButton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.hudMilitaryButton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.hudObjectivesButton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.hudmenumilitarybutton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.hudmenucollectbutton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.hudmenustoragebutton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.hudmenuutilitybutton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.menuButton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.incomeButton);
//		MainActivity.inGameHUD.unregisterTouchArea(main.HUDResources);
		MainActivity.menu = "";
		unRegisterBuildingButtons();

	}

	public static void addHudTouchAreas() {
		inGameHUD.registerTouchAreas();
	}

	public static void removeCityIconTexts() {
		for (int i = 0; i < MainActivity.cityIcons.size(); i++) {
			MainActivity.cityIcons.get(i).removeSoldierText();
		}
	}

	public static void finishBuy() {
		if (cancelbutton != null) {
			inGameHUD.unregisterTouchArea(cancelbutton);
			removeEntity(cancelbutton);
			cancelbutton = null;
		}
		if (buybutton != null) {
			inGameHUD.unregisterTouchArea(buybutton);
			removeEntity(buybutton);
			buybutton = null;
		}
	}

	public static void RemoveResources(String kind, int ammount) {
		for (int i = 0; i < MainActivity.Stocks.size(); i++) {
			if (kind == "Marble") {
				if (MainActivity.Stocks.get(i).Marble >= ammount) {
					MainActivity.Stocks.get(i).Marble -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Marble -= ammount;
					break;
				}
			}
			if (kind == "Wood") {
				if (MainActivity.Stocks.get(i).Wood >= ammount) {
					MainActivity.Stocks.get(i).Wood -= ammount;
					Debug.e("GOGOGO");
					MainActivity.Wood -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					break;
				}
			}
			if (kind == "Brick") {
				if (MainActivity.Stocks.get(i).Brick >= ammount) {
					MainActivity.Stocks.get(i).Brick -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Brick -= ammount;
					break;
				}
			}
			if (kind == "Bronze") {
				if (MainActivity.Stocks.get(i).Bronze >= ammount) {
					MainActivity.Stocks.get(i).Bronze -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Bronze -= ammount;
					break;
				}
			}

			if (kind == "Skin") {
				if (MainActivity.Stocks.get(i).Skin >= ammount) {
					MainActivity.Stocks.get(i).Skin -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Skin -= ammount;
					break;
				}
			}
			if (kind == "Armor") {
				if (MainActivity.Stocks.get(i).Armor >= ammount) {
					MainActivity.Stocks.get(i).Armor -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Armor -= ammount;
					break;
				}
			}
			if (kind == "Clay") {
				if (MainActivity.Stocks.get(i).Clay >= ammount) {
					MainActivity.Stocks.get(i).Clay -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Clay -= ammount;
					break;
				}
			}

		}
	}

	public static void RemoveResources(String kind, int ammount, Stock stock) {
		if (kind == "Marble") {
			if (stock.Marble >= ammount) {
				stock.Marble -= ammount;
				stock.removeResource(kind, ammount);
				MainActivity.Marble -= ammount;
			}
		}
		if (kind == "Wood") {
			if (stock.Wood >= ammount) {
				stock.Wood -= ammount;
				MainActivity.Wood -= ammount;
				stock.removeResource(kind, ammount);
			}
		}
		if (kind == "Brick") {
			if (stock.Brick >= ammount) {
				stock.Brick -= ammount;
				stock.removeResource(kind, ammount);
				MainActivity.Brick -= ammount;
			}
		}
		if (kind == "Bronze") {
			if (stock.Bronze >= ammount) {
				stock.Bronze -= ammount;
				stock.removeResource(kind, ammount);
				MainActivity.Bronze -= ammount;
			}
		}

		if (kind == "Skin") {
			if (stock.Skin >= ammount) {
				stock.Skin -= ammount;
				stock.removeResource(kind, ammount);
				MainActivity.Skin -= ammount;
			}
		}
		if (kind == "Armor") {
			if (stock.Armor >= ammount) {
				stock.Armor -= ammount;
				stock.removeResource(kind, ammount);
				MainActivity.Armor -= ammount;
			}
		}
		if (kind == "Clay") {
			if (stock.Clay >= ammount) {
				stock.Clay -= ammount;
				stock.removeResource(kind, ammount);
				MainActivity.Clay -= ammount;
			}
		}
	}

	/**
	 * Create the HUD for our building description
	 */
	public static void addBuildingDescription() {
		MainActivity.buildingDescriptionHUD.setAlpha(0.95f);
		MainActivity.buildingDescriptionCancel.setAlpha(1);
		MainActivity.buildingDescriptionTitle.setAlpha(1);
		MainActivity.buildingDescriptionDetail.setAlpha(1);
		MainActivity.mScene
				.registerTouchArea(MainActivity.buildingDescriptionCancel);
	}
}
/*
 * Old Code
 * 
 * //SetupHUD(){ //buildingDescriptionHouseWaterText = new Text( // 48 + 24 +
 * buildingDescriptionHouseWater.getWidth() + 8, // 82 + 48 * 2 + 8, smallFont,
 * "", 10, // this.getVertexBufferObjectManager());
 * //buildingDescriptionHouseWheatText = new Text( // 48 + 24 +
 * buildingDescriptionHouseWater.getWidth() + 8, // 82 + 48 * 3 + 8, smallFont,
 * "", 10, // this.getVertexBufferObjectManager());
 * //buildingDescriptionHouseCultureText = new Text( // 48 + 24 +
 * buildingDescriptionHouseWater.getWidth() + 8, // 82 + 48 * 4 + 8, smallFont,
 * "", 10, // this.getVertexBufferObjectManager());
 * //buildingDescriptionHouseSkinText = new Text(48 + 210 + 24 // +
 * buildingDescriptionHouseWater.getWidth() + 8, // 82 + 48 * 1 + 8, smallFont,
 * "", 10, // this.getVertexBufferObjectManager());
 * //buildingDescriptionHouseInhabitantsText = new Text( // 48 + 24 +
 * buildingDescriptionHouseWater.getWidth() + 8, // 82 + 48 + 8, smallFont, "",
 * 10, // this.getVertexBufferObjectManager()); //HUDWorkersText = new Text( //
 * 640 - 256 - buildingDescriptionHouseInhabitantsImage.getWidth()-36, // 50 +
 * 33, smallFont, "", 200, this // .getVertexBufferObjectManager());
 * //HUDWorkersDescriptionText = new Text( // 640 - 256 -
 * buildingDescriptionHouseInhabitantsImage.getWidth(), // 33 + 112, smallFont,
 * "", 200, this // .getVertexBufferObjectManager());
 * 
 * SetupHUD()
 */
