package com.ligr.strategygame.constants;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import com.ligr.strategygame.MainActivity;

public class ResourceImage {

	private MainActivity main;
	private ITextureRegion inGameHUDImage;
	private TextureRegion battleHUDImage;
	private TextureRegion buybuttonimage;
	private TextureRegion roadimage;
	private TextureRegion cancelbuttonimage;
	private TextureRegion buildingDescriptionHUDImage;
	private TextureRegion cancelImage;
	private TextureRegion fountainImage;
	private TextureRegion houseButtonImage;
	private TextureRegion buildingDescriptionHouseWaterImage;
	private TextureRegion buildingDescriptionHouseWheatImage;
	private TextureRegion buildingDescriptionHouseSkinImage;
	private TextureRegion buildingDescriptionHouseCultureImage;
	private TextureRegion buildingDescriptionHouseInhabitantsImage;
	private TextureRegion farmButtonImage;
	private TextureRegion siloImage;
	private TextureRegion MoreInfoImage;
	private TextureRegion FoodMarketImage;
	private TextureRegion mainMenuBackgroundImage;
	private TextureRegion mainMenuPlayButtonImage;
	private TextureRegion theatreImage;
	private TextureRegion marbleImage;
	private TextureRegion stockplaceImage;
	private TextureRegion woodCutterImage;
	private TextureRegion stockplacebuttonImage;
	private TextureRegion huntersLodgeImage;
	private TextureRegion hudMenuProduction;
	private TextureRegion hudMenuCollect;
	private TextureRegion hudMenuCulture;
	private TextureRegion hudMenuUtility;
	private TextureRegion hudMenuStorage;
	private TextureRegion aresTempleImage;
	private TextureRegion fountainButtonImage;
	private TextureRegion huntersLodgeButtonImage;
	private TextureRegion woodCutterButtonImage;
	private TextureRegion siloButtonImage;
	private TextureRegion theatreButtonImage;
	private TextureRegion woodResourceImage;
	private TextureRegion marbleResourceImage;
	private TextureRegion brickResourceImage;
	private TextureRegion skinResourceImage;
	private TextureRegion fishingHutButtonImage;
	private TextureRegion skinnerButtonImage;
	private TextureRegion butcherButtonImage;
	private TextureRegion skinnerImage;
	private TextureRegion butcherImage;
	private TextureRegion upgradeButtonImage;
	private TextureRegion menuButtonImage;
	private TextureRegion menuQuestButtonImage;
	private TextureRegion menuMapButtonImage;
	private TextureRegion menuMainMenuImage;
	private TextureRegion gotFoodSymbolImage;
	private TextureRegion menuMapImage;
	private TextureRegion menuMapHUDImage;
	private TextureRegion cityIconImage;
	private TextureRegion coinSymbolImage;
	private TextureRegion resourcesMenuImage;
	private TextureRegion messageHUDImage;
	private TextureRegion messageConfirmButtonImage;
	private TextureRegion messageCancelButtonImage;
	private TextureRegion menuInhabitantsButtonImage;
	private TextureRegion messageOkButtonImage;
	private TextureRegion foodMarketButtonImage;
	private TextureRegion stoneCutterButtonImage;
	private TextureRegion roadButtonImage;
	private TextureRegion barrackButtonImage;
	private TextureRegion barrackImage;
	private TextureRegion hudMenuMilitaryImage;
	private TextureRegion trainingHUDImage;
	private TextureRegion trainingNextButtonImage;
	private TextureRegion trainingPreviousButtonImage;
	private TextureRegion trainingHopliteButtonImage;
	private TextureRegion trainingSlingerImage;
	private TextureRegion brickFoundryImage;
	private TextureRegion menuMapAttackButtonImage;
	private TextureRegion menuSaveButtonImage;
	private TextureRegion mineDepositClayImage;
	private TextureRegion hudResourcesButtonImage;
	private TextureRegion clayMineImage;
	private TextureRegion grassTileImage;
	private TextureRegion menuBattleReturnButtonImage;
	private TextureRegion buildingHUDImage;
	private TextureRegion buildingCancelButtonImage;
	private TextureRegion mainMenuLoadButtonImage;
	private TextureRegion buildingConfirmChoiceButtonImage;
	private TextureRegion stockChoiceWoodImage;
	private TextureRegion stockChoiceAllImage;
	private TextureRegion stockChoiceBrickImage;
	private TextureRegion stockChoiceMarbleImage;
	private TextureRegion stockChoiceArmorImage;
	private TextureRegion stockChoiceClayImage;
	private TextureRegion stockChoiceSkinImage;
	private TextureRegion hudRemoveBuildingButtonImage;
	private TextureRegion hudMapButtonImage;
	private TextureRegion incomeHUDImage;
	private TextureRegion incomeButtonImage;
	private TextureRegion brickFoundryButtonImage;
	private TextureRegion hudChatButtonImage;
	private TextureRegion hudMilitaryButtonImage;
	private TextureRegion clayButtonImage;
	private TiledTextureRegion houseImage;
	private TiledTextureRegion stockImage;
	private TiledTextureRegion upgradeArrowImage;
	private TiledTextureRegion stoneCutterImage;
	private TiledTextureRegion fishingHutImage;
	private TiledTextureRegion deerImage;
	private TiledTextureRegion treeImage;
	private TiledTextureRegion hudWorkersImage;
	private TiledTextureRegion farmImage;
	private TiledTextureRegion placebuildingimage;
	private TiledTextureRegion fishSpotImage;
	private TiledTextureRegion boatImage;
	private TiledTextureRegion npcStoneCutter;
	private TiledTextureRegion hopliteImage;
	private TiledTextureRegion cloudImage;
	private TiledTextureRegion marbleTileImage;
	private TiledTextureRegion clayTileImage;
	private TiledTextureRegion mainMenuDoorImage;
	
	public ResourceImage(MainActivity main){
		this.main = main;
		initializeImages();
		initializeTiledImages();
	}

	/**
	 * Initialize all of our non-animated sprites
	 */
	public void initializeImages() {
		inGameHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/ingamehud.png");
		battleHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/battlehud.png");
		buybuttonimage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/buybutton.png");
		roadimage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/road.png");
		cancelbuttonimage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/cancelbutton.png");
		buildingDescriptionHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/housemenu.png");
		cancelImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/cancel.png");
		fountainImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/fountain.png");
		houseButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/housebutton.png");
		buildingDescriptionHouseWaterImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/buildingdescriptionhousewater.png");
		buildingDescriptionHouseWheatImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/buildingdescriptionhousewheat.png");
		buildingDescriptionHouseSkinImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/buildingdescriptionhouseskin.png");
		buildingDescriptionHouseCultureImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/buildingdescriptionhouseculture.png");
		buildingDescriptionHouseInhabitantsImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/buildingdescriptionhouseinhabitants.png");
		farmButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/farmbutton.png");
		siloImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/silo.png");
		MoreInfoImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/moreinformationbutton.png");
		FoodMarketImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/foodmarket.png");
		mainMenuBackgroundImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/mainmenubackground.png");
		mainMenuPlayButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/mainmenuplaygamebutton.png");
		theatreImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/theatre.png");
		marbleImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/marblemap1.png");
		stockplaceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockplace.png");
		woodCutterImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/woodcutter.png");
		stockplacebuttonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockplacebutton.png");
		huntersLodgeImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hunterslodge.png");
		hudMenuProduction = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudmenuproduction.png");
		hudMenuCollect = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudmenucollect.png");
		hudMenuCulture = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudmenuculture.png");
		hudMenuUtility = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudmenuutility.png");
		hudMenuStorage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/menuhudstorage.png");
		aresTempleImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/arestemple.png");
		fountainButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/fountainbutton.png");
		huntersLodgeButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hunterslodgebutton.png");
		woodCutterButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/woodcutterbutton.png");
		siloButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/silobutton.png");
		theatreButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/theatrebutton.png");
		woodResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/woodresource.png");
		marbleResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/marbleresource.png");
		brickResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/brickresource.png");
		skinResourceImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/skinresource.png");
		fishingHutButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/fishinghutbutton.png");
		skinnerButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/skinnerbutton.png");
		butcherButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/skinnerbutton.png");
		skinnerImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/skinner.png");
		butcherImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/skinner.png");
		upgradeButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/upgradebutton.png");
		menuButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/menubutton.png");
		menuQuestButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudobjectives.png");
		menuMapButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/menumap.png");
		menuMainMenuImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/menumainmenu.png");
		gotFoodSymbolImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/housenofood.png");
		menuMapImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/map.png");
		menuMapHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/maphud.png");
		cityIconImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/cityicon.png");
		coinSymbolImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/coinsymbol.png");
		resourcesMenuImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/resourcesmenu.png");
		messageHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/messagehud.png");
		messageConfirmButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/messageconfirm.png");
		messageCancelButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/messagecancel.png");
		menuInhabitantsButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/menuinhabitants.png");
		messageOkButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/messageok.png");
		foodMarketButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/foodmarketbutton.png");
		stoneCutterButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stonecutterbutton.png");
		roadButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/roadbutton.png");
		barrackButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/barrackbutton.png");
		barrackImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/barrack.png");
		hudMenuMilitaryImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudmenumilitary.png");
		trainingHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/traininghud.png");
		trainingNextButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/trainingnext.png");
		trainingPreviousButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/trainingprevious.png");
		trainingHopliteButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/trainingrecruithoplite.png");
		trainingSlingerImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/trainingrecruitslinger.png");
		brickFoundryImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/brickfoundry.png");
		menuMapAttackButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/menumapattackbutton.png");
		menuSaveButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/menusavebutton.png");
		mineDepositClayImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/claybuilding.png");
		hudResourcesButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudresources.png");
		clayMineImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				main.getGameAtlas(), main, "gfx/claybuilding.png");
		grassTileImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/grasstile.png");
		mainMenuLoadButtonImage=BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/mainmenuloadbutton.png");

		menuBattleReturnButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/hudbattlereturnbutton.png");
		buildingHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/buildinghud.png");
		buildingCancelButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/buildingcancelbutton.png");
		buildingConfirmChoiceButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main,
						"gfx/buildingconfirmchoicebutton.png");
		stockChoiceWoodImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockwoodchoice.png");
		stockChoiceAllImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockallchoice.png");
		stockChoiceBrickImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockbrickchoice.png");
		stockChoiceMarbleImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockmarblechoice.png");
		stockChoiceArmorImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockarmorchoice.png");
		stockChoiceClayImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockclaychoice.png");
		stockChoiceSkinImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/stockskinchoice.png");
		hudRemoveBuildingButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudremovebuildings.png");
		hudMapButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudmap.png");
		incomeHUDImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/incomehud.png");
		incomeButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/incomebutton.png");
		brickFoundryButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/brickfoundrybutton.png");
		hudChatButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudchatbutton.png");
		hudMilitaryButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/hudmilitarybutton.png");
		clayButtonImage = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(main.getGameAtlas(), main, "gfx/claybuildingbutton.png");
	}

	/**
	 * Initilaize our animated images
	 */
	public void initializeTiledImages() {
		houseImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/house.png", 19, 1);
		stockImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/stock.png", 18, 2);
		upgradeArrowImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/upgradearrow.png",
						5, 1);
		stoneCutterImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/stonecutter.png",
						5, 1);
		fishingHutImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/fishinghut.png", 2,
						1);
		deerImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/deer.png", 4, 1);
		treeImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/tree.png", 4, 1);
		hudWorkersImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/workershud.png", 2,
						1);
		farmImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/farm.png", 6, 1);
		placebuildingimage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main,
						"gfx/placebuildingtiles.png", 20, 1);
		fishSpotImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/fishspot.png", 10,
						1);
		boatImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/boat.png", 8, 1);
		npcStoneCutter = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main,
						"gfx/npcstonecutter.png", 18, 1);
		hopliteImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/hoplite.png", 8, 8);
		cloudImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/cloud.png", 8, 1);
		marbleTileImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/marbletile.png", 9,
						1);
		clayTileImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/claytile.png", 9, 1);
		mainMenuDoorImage = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(main.getGameAtlas(), main, "gfx/mainmenudoor.png",
						6, 1);
	}
	
	// Get methods
	public MainActivity getMain() {
		return main;
	}

	public TextureRegion getBattleHUDImage() {
		return battleHUDImage;
	}

	public TextureRegion getBuybuttonimage() {
		return buybuttonimage;
	}

	public TextureRegion getRoadimage() {
		return roadimage;
	}

	public TextureRegion getCancelbuttonimage() {
		return cancelbuttonimage;
	}

	public TextureRegion getBuildingDescriptionHUDImage() {
		return buildingDescriptionHUDImage;
	}

	public TextureRegion getCancelImage() {
		return cancelImage;
	}

	public TextureRegion getFountainImage() {
		return fountainImage;
	}

	public TextureRegion getHouseButtonImage() {
		return houseButtonImage;
	}

	public TextureRegion getBuildingDescriptionHouseWaterImage() {
		return buildingDescriptionHouseWaterImage;
	}

	public TextureRegion getBuildingDescriptionHouseWheatImage() {
		return buildingDescriptionHouseWheatImage;
	}

	public TextureRegion getBuildingDescriptionHouseSkinImage() {
		return buildingDescriptionHouseSkinImage;
	}

	public TextureRegion getBuildingDescriptionHouseCultureImage() {
		return buildingDescriptionHouseCultureImage;
	}

	public TextureRegion getBuildingDescriptionHouseInhabitantsImage() {
		return buildingDescriptionHouseInhabitantsImage;
	}

	public TextureRegion getFarmButtonImage() {
		return farmButtonImage;
	}

	public TextureRegion getSiloImage() {
		return siloImage;
	}

	public TextureRegion getMoreInfoImage() {
		return MoreInfoImage;
	}

	public TextureRegion getFoodMarketImage() {
		return FoodMarketImage;
	}

	public TextureRegion getMainMenuBackgroundImage() {
		return mainMenuBackgroundImage;
	}

	public TextureRegion getMainMenuPlayButtonImage() {
		return mainMenuPlayButtonImage;
	}

	public TextureRegion getTheatreImage() {
		return theatreImage;
	}

	public TextureRegion getMarbleImage() {
		return marbleImage;
	}

	public TextureRegion getStockplaceImage() {
		return stockplaceImage;
	}

	public TextureRegion getWoodCutterImage() {
		return woodCutterImage;
	}

	public TextureRegion getStockplacebuttonImage() {
		return stockplacebuttonImage;
	}

	public TextureRegion getHuntersLodgeImage() {
		return huntersLodgeImage;
	}

	public TextureRegion getHudMenuProduction() {
		return hudMenuProduction;
	}

	public TextureRegion getHudMenuCollect() {
		return hudMenuCollect;
	}

	public TextureRegion getHudMenuCulture() {
		return hudMenuCulture;
	}

	public TextureRegion getHudMenuUtility() {
		return hudMenuUtility;
	}

	public TextureRegion getHudMenuStorage() {
		return hudMenuStorage;
	}

	public TextureRegion getAresTempleImage() {
		return aresTempleImage;
	}

	public TextureRegion getFountainButtonImage() {
		return fountainButtonImage;
	}

	public TextureRegion getHuntersLodgeButtonImage() {
		return huntersLodgeButtonImage;
	}

	public TextureRegion getWoodCutterButtonImage() {
		return woodCutterButtonImage;
	}

	public TextureRegion getSiloButtonImage() {
		return siloButtonImage;
	}

	public TextureRegion getTheatreButtonImage() {
		return theatreButtonImage;
	}

	public TextureRegion getWoodResourceImage() {
		return woodResourceImage;
	}

	public TextureRegion getMarbleResourceImage() {
		return marbleResourceImage;
	}

	public TextureRegion getBrickResourceImage() {
		return brickResourceImage;
	}

	public TextureRegion getSkinResourceImage() {
		return skinResourceImage;
	}

	public TextureRegion getFishingHutButtonImage() {
		return fishingHutButtonImage;
	}

	public TextureRegion getSkinnerButtonImage() {
		return skinnerButtonImage;
	}

	public TextureRegion getButcherButtonImage() {
		return butcherButtonImage;
	}

	public TextureRegion getSkinnerImage() {
		return skinnerImage;
	}

	public TextureRegion getButcherImage() {
		return butcherImage;
	}

	public TextureRegion getUpgradeButtonImage() {
		return upgradeButtonImage;
	}

	public TextureRegion getMenuButtonImage() {
		return menuButtonImage;
	}

	public TextureRegion getMenuQuestButtonImage() {
		return menuQuestButtonImage;
	}

	public TextureRegion getMenuMapButtonImage() {
		return menuMapButtonImage;
	}

	public TextureRegion getMenuMainMenuImage() {
		return menuMainMenuImage;
	}

	public TextureRegion getGotFoodSymbolImage() {
		return gotFoodSymbolImage;
	}

	public TextureRegion getMenuMapImage() {
		return menuMapImage;
	}

	public TextureRegion getMenuMapHUDImage() {
		return menuMapHUDImage;
	}

	public TextureRegion getCityIconImage() {
		return cityIconImage;
	}

	public TextureRegion getCoinSymbolImage() {
		return coinSymbolImage;
	}

	public TextureRegion getResourcesMenuImage() {
		return resourcesMenuImage;
	}

	public TextureRegion getMessageHUDImage() {
		return messageHUDImage;
	}

	public TextureRegion getMessageConfirmButtonImage() {
		return messageConfirmButtonImage;
	}

	public TextureRegion getMessageCancelButtonImage() {
		return messageCancelButtonImage;
	}

	public TextureRegion getMenuInhabitantsButtonImage() {
		return menuInhabitantsButtonImage;
	}

	public TextureRegion getMessageOkButtonImage() {
		return messageOkButtonImage;
	}

	public TextureRegion getFoodMarketButtonImage() {
		return foodMarketButtonImage;
	}

	public TextureRegion getStoneCutterButtonImage() {
		return stoneCutterButtonImage;
	}

	public TextureRegion getRoadButtonImage() {
		return roadButtonImage;
	}

	public TextureRegion getBarrackButtonImage() {
		return barrackButtonImage;
	}

	public TextureRegion getBarrackImage() {
		return barrackImage;
	}

	public TextureRegion getHudMenuMilitaryImage() {
		return hudMenuMilitaryImage;
	}

	public TextureRegion getTrainingHUDImage() {
		return trainingHUDImage;
	}

	public TextureRegion getTrainingNextButtonImage() {
		return trainingNextButtonImage;
	}

	public TextureRegion getTrainingPreviousButtonImage() {
		return trainingPreviousButtonImage;
	}

	public TextureRegion getTrainingHopliteButtonImage() {
		return trainingHopliteButtonImage;
	}

	public TextureRegion getTrainingSlingerImage() {
		return trainingSlingerImage;
	}

	public TextureRegion getBrickFoundryImage() {
		return brickFoundryImage;
	}

	public TextureRegion getMenuMapAttackButtonImage() {
		return menuMapAttackButtonImage;
	}

	public TextureRegion getMenuSaveButtonImage() {
		return menuSaveButtonImage;
	}

	public TextureRegion getMineDepositClayImage() {
		return mineDepositClayImage;
	}

	public TextureRegion getHudResourcesButtonImage() {
		return hudResourcesButtonImage;
	}

	public TextureRegion getClayMineImage() {
		return clayMineImage;
	}

	public TextureRegion getGrassTileImage() {
		return grassTileImage;
	}

	public TextureRegion getMenuBattleReturnButtonImage() {
		return menuBattleReturnButtonImage;
	}

	public TextureRegion getBuildingHUDImage() {
		return buildingHUDImage;
	}

	public TextureRegion getBuildingCancelButtonImage() {
		return buildingCancelButtonImage;
	}

	public TextureRegion getMainMenuLoadButtonImage() {
		return mainMenuLoadButtonImage;
	}

	public TextureRegion getBuildingConfirmChoiceButtonImage() {
		return buildingConfirmChoiceButtonImage;
	}

	public TextureRegion getStockChoiceWoodImage() {
		return stockChoiceWoodImage;
	}

	public TextureRegion getStockChoiceAllImage() {
		return stockChoiceAllImage;
	}

	public TextureRegion getStockChoiceBrickImage() {
		return stockChoiceBrickImage;
	}

	public TextureRegion getStockChoiceMarbleImage() {
		return stockChoiceMarbleImage;
	}

	public TextureRegion getStockChoiceArmorImage() {
		return stockChoiceArmorImage;
	}

	public TextureRegion getStockChoiceClayImage() {
		return stockChoiceClayImage;
	}

	public TextureRegion getStockChoiceSkinImage() {
		return stockChoiceSkinImage;
	}

	public TextureRegion getHudRemoveBuildingButtonImage() {
		return hudRemoveBuildingButtonImage;
	}

	public TextureRegion getHudMapButtonImage() {
		return hudMapButtonImage;
	}

	public TextureRegion getIncomeHUDImage() {
		return incomeHUDImage;
	}

	public TextureRegion getIncomeButtonImage() {
		return incomeButtonImage;
	}

	public TextureRegion getBrickFoundryButtonImage() {
		return brickFoundryButtonImage;
	}

	public TextureRegion getHudChatButtonImage() {
		return hudChatButtonImage;
	}

	public TextureRegion getHudMilitaryButtonImage() {
		return hudMilitaryButtonImage;
	}

	public TextureRegion getClayButtonImage() {
		return clayButtonImage;
	}

	public TiledTextureRegion getHouseImage() {
		return houseImage;
	}

	public TiledTextureRegion getStockImage() {
		return stockImage;
	}

	public TiledTextureRegion getUpgradeArrowImage() {
		return upgradeArrowImage;
	}

	public TiledTextureRegion getStoneCutterImage() {
		return stoneCutterImage;
	}

	public TiledTextureRegion getFishingHutImage() {
		return fishingHutImage;
	}

	public TiledTextureRegion getDeerImage() {
		return deerImage;
	}

	public TiledTextureRegion getTreeImage() {
		return treeImage;
	}

	public TiledTextureRegion getHudWorkersImage() {
		return hudWorkersImage;
	}

	public TiledTextureRegion getFarmImage() {
		return farmImage;
	}

	public TiledTextureRegion getPlacebuildingimage() {
		return placebuildingimage;
	}

	public TiledTextureRegion getFishSpotImage() {
		return fishSpotImage;
	}

	public TiledTextureRegion getBoatImage() {
		return boatImage;
	}

	public TiledTextureRegion getNpcStoneCutter() {
		return npcStoneCutter;
	}

	public TiledTextureRegion getHopliteImage() {
		return hopliteImage;
	}

	public TiledTextureRegion getCloudImage() {
		return cloudImage;
	}

	public TiledTextureRegion getMarbleTileImage() {
		return marbleTileImage;
	}

	public TiledTextureRegion getClayTileImage() {
		return clayTileImage;
	}

	public TiledTextureRegion getMainMenuDoorImage() {
		return mainMenuDoorImage;
	}

	public ITextureRegion getInGameHUDImage(){
		return inGameHUDImage;
	}
}
