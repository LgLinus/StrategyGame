package other;

import java.util.ArrayList;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.FishSpot;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Mission;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.Buttons.CityIcon;
import com.ligr.strategygame.Buttons.MainMenuLoadButton;
import com.ligr.strategygame.Buttons.MainMenuPlayButton;
import com.ligr.strategygame.buildings.Barrack;
import com.ligr.strategygame.buildings.BrickFoundry;
import com.ligr.strategygame.buildings.Butcher;
import com.ligr.strategygame.buildings.Farm;
import com.ligr.strategygame.buildings.FishingHut;
import com.ligr.strategygame.buildings.FoodMarket;
import com.ligr.strategygame.buildings.Fountain;
import com.ligr.strategygame.buildings.House;
import com.ligr.strategygame.buildings.HuntersLodge;
import com.ligr.strategygame.buildings.MineDepositClay;
import com.ligr.strategygame.buildings.Road;
import com.ligr.strategygame.buildings.Silo;
import com.ligr.strategygame.buildings.Skinner;
import com.ligr.strategygame.buildings.Stock;
import com.ligr.strategygame.buildings.StoneCutter;
import com.ligr.strategygame.buildings.Theatre;
import com.ligr.strategygame.buildings.WoodCutter;
import com.ligr.strategygame.constants.Constant;
import com.ligr.strategygame.constants.ConstantBuildings;
import com.ligr.strategygame.constants.ResourceImage;

/**
 * Class responsible of handling some of the logic in the game
 * 
 * @author LgLinuss
 * 
 */
public class Controller {
	MainActivity main;
	String menu = null;
	private ArrayList<CityIcon> cityIcons;
	TimerHandler TimerHandlerMonthly; // The timer that updates each month
	private TimerHandler TimerHandlerPause;
	private boolean pause = false;
	public static float gameSpeed = 5f; // The gamespeed, decides how often we
										// want to update the months
	private DataBase dataBase;
	private int month = 1;
	public int monthInDebt = 0;
	public static float income = 0;
	public static float incomeInhabitants = 0;
	public static float expenses = 0;
	public static float monthlygain = 0;
	private static float totalIncome = 0;
	private int maxWorkers = 0;
	public int workers = 0; // Total ammount of workers
	public int militaryWorkers = 0;
	public int buildingWorkers = 0;
	public int HouseLevel = 1; // Keeps track on what kind of housing
	// The ammount of inhabitants
	public static int Inhabitants[] = { 0, 0, 0, 0, 0, 0, 0 }; 
	public static int InhabitantsSize = 7;
	private int gold;
	private ResourceImage images;
	private Mission mission;
	// Various resources which are used in the game
	public int Marble = 0, Wood = 0, Armor = 0, Skin = 0, Sculptures = 0;
	public int Fish = 0, Bronze = 0, Brick = 0, Meat = 0, Clay = 0;
	private int militaryHoplite = 0;
	private int militarySlinger = 0;
	private static int militaryHopliteWar = 0;
	private static final int militarySlingerWar = 0;

	/**
	 * Construct the controller with a given activity
	 * 
	 * @param mainActivity
	 */
	public Controller(MainActivity mainActivity) {
		this.main = mainActivity;
		images = new ResourceImage(main);
		gold = Constant.startGold;
		// Create our mission controll
		mission = new Mission(this);
		dataBase = new DataBase(main);
	}

	private void timerHandlerPause() {
		this.main.getEngine().registerUpdateHandler(
				TimerHandlerPause = new TimerHandler(1, new ITimerCallback() {
					@Override
					public void onTimePassed(final TimerHandler pTimerHandler) {
						if (pause) {
							main.getEngine().unregisterUpdateHandler(
									TimerHandlerMonthly);
							TimerHandlerPause.reset();
						} else if (!pause) {
							main.getEngine().unregisterUpdateHandler(
									TimerHandlerPause);
							timerHandlerMonthly();
						}
					}

				}));
	}

	/**
	 * This will be called for every time we go into a new month
	 */
	public void timerHandlerMonthly() {
		int i = 0;
		main.getEngine().registerUpdateHandler(
				TimerHandlerMonthly = new TimerHandler(gameSpeed,
						new ITimerCallback() {
							@Override
							public void onTimePassed(
									final TimerHandler pTimerHandler) {
								if (getMenu().equals("Game")) {
									if (!pause) {
										TimerHandlerMonthly.reset();
									} else {
										timerHandlerPause();
									}
									for (int i = 0; i < getCityIcons().size(); i++) {
										getCityIcons().get(i).update();
									}
									CheckForDebt();
									UpdateMonth();
									updateGoldMonthly();
									mission.checkMission(main.getMap(),
											main.currentMission);
									updateLevelInhabitants();
									CheckHouseLevel();
									// Update the main.Houses
									for (int i = 0; i < main.main.getHouses()
											.size(); i++) {
										main.getHouses()
												.get(i)
												.Update(main.getHouses().get(i));
									}
									// July, harvest wheat
									if (month == 7) {
										for (int i = 0; i < main.main
												.getFarms().size(); i++) {
											main.getFarms().get(i);
											main.farm.ProduceWheat(main
													.getFarms().get(i));
										}

									}
								} else
									TimerHandlerMonthly.reset();
							}

							private void CheckForDebt() {
								if (gold < 0)
									monthInDebt++;

								else
									monthInDebt = 0;

								if (monthInDebt >= 12) {
									gameOver();
								}
							}

							private void gameOver() {
								main.PAUSE = true;
								main.messagePopUp(
										"Your city has been in debt for more than a year, you lost.",
										org.andengine.util.color.Color.RED);
								main.gameOver = true;
							}
						}));
	}

	protected void UpdateMonth() {
		for (int i = 0; i < main.sObjects.size(); i++) {
			main.sObjects.get(i).checkForStocks();
		}
		for (int i = 0; i < main.asObjects.size(); i++) {
			main.asObjects.get(i).checkForStocks();
		}
		month += 1;
		if (month >= 13) {
			month = 1;
			yearlyTribute();
		}
		String res = "Month: ";
		if (month == 1) {
			res += "January";
		} else if (month == 2) {
			res += "February";
		} else if (month == 3) {
			res += "Mars";
		} else if (month == 4) {
			res += "April";
		} else if (month == 5) {
			res += "May";
		} else if (month == 6) {
			res += "June";
		} else if (month == 7) {
			res += "July";
		} else if (month == 8) {
			res += "August";
		} else if (month == 9) {
			res += "September";
		} else if (month == 10) {
			res += "October";
		} else if (month == 11) {
			res += "November";
		} else if (month == 12) {
			res += "December";
		}
		main.getMonthText().setColor(1f, 1f, 1f);
		main.getMonthText().setText(res);

	}

	private void setExpenses() {
		expenses = 0;
		expenses += main.getBarracks().size()
				* ConstantBuildings.EXPENSESBARRACK;
		expenses += main.getBrickFoundrys().size()
				* ConstantBuildings.EXPENSESBRICKFOUNNDRY;
		expenses += main.getButchers().size()
				* ConstantBuildings.EXPENSESBUTCHER;
		expenses += main.getFarms().size() * ConstantBuildings.EXPENSESFARM;
		expenses += main.getFishingHuts().size()
				* ConstantBuildings.EXPENSESFISHINGHUT;
		expenses += main.getFoodMarkets().size()
				* ConstantBuildings.EXPENSESFOODMARKET;
		expenses += main.getFountains().size()
				* ConstantBuildings.EXPENSESFOUNTAIN;
		expenses += main.huntersLodges.size()
				* ConstantBuildings.EXPENSESHUNTERSLODGE;
		expenses += main.getMineDepositClays().size()
				* ConstantBuildings.EXPENSESMINEDEPOSITCLAY;
		expenses += main.getMineDepositBronzes().size()
				* ConstantBuildings.EXPENSESMINEDEPOSITBRONZE;
		expenses += main.getSilos().size() * ConstantBuildings.EXPENSESSILO;
		expenses += main.getSkinners().size()
				* ConstantBuildings.EXPENSESSKINNER;
		expenses += main.getStocks().size() * ConstantBuildings.EXPENSESSTOCK;
		expenses += main.getStoneCutters().size()
				* ConstantBuildings.EXPENSESSTONECUTTER;
		expenses += main.getTheatres().size()
				* ConstantBuildings.EXPENSESTHEATRE;
		expenses += main.getWoodCutters().size()
				* ConstantBuildings.EXPENSESWOODCUTTER;
		expenses += militaryHoplite * ConstantBuildings.EXPENSESHOPLITE;
		// expenses+=granarys.size()*main.EXPENSESGRANARY;

	}

	/**
	 * Load our game from a database
	 */
	public void loadGame() {
		dataBase = new DataBase(main);
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
					setGold((int) x);
				} else if (load.contains("Inhabitants")) {
					updateInhabitants((int) x, 0, 0);
				} else if (load.contains("Building Workers")) {
					buildingWorkers = (int) x;
					updateWorkers(0, 0);
				} else if (load.contains("MilitaryHoplite")) {
					setMilitaryHoplite((int) x);
				} else if (load.contains("MilitarySlinger")) {
					setMilitarySlinger((int) x);
				} else if (load.contains("Barrack")) {
					Barrack barrack = new Barrack(x, y,
							images.getBarrackImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(barrack);
					main.getBarracks().add(barrack);
					// sObjects.add(barrack);
					main.getScene().registerTouchArea(barrack);

				} else if (load.contains("Brick Foundry")) {
					BrickFoundry brickFoundry = new BrickFoundry(x, y,
							images.getBrickFoundryImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(brickFoundry);
					main.getBrickFoundrys().add(brickFoundry);
					// sObjects.add(brickFoundry);
					main.getScene().registerTouchArea(brickFoundry);

				} else if (load.contains("Butcher")) {
					Butcher butcher = new Butcher(x, y,
							images.getButcherImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(butcher);
					main.getButchers().add(butcher);
					// sObjects.add(butcher);
					main.getScene().registerTouchArea(butcher);
				} else if (load.contains("Farm")) {
					Farm farm = new Farm(x, y, images.getFarmImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(farm);
					main.getFarms().add(farm);
					farm.animate(1000);
					// a//sObjects.add(farm);
					main.getScene().registerTouchArea(farm);
				} else if (load.contains("Fishing Hut")) {
					FishingHut fishinghut = new FishingHut(x, y,
							images.getFishingHutImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(fishinghut);
					main.getFishingHuts().add(fishinghut);
					// sObjects.add(fishinghut);
					main.getScene().registerTouchArea(fishinghut);

				} else if (load.contains("Food Market")) {
					FoodMarket foodmarket = new FoodMarket(x, y,
							images.getFoodMarketImage(),
							main.getVertexBufferObjectManager(), main, number);
					main.getScene().attachChild(foodmarket);
					main.getFoodMarkets().add(foodmarket);
					// sObjects.add(foodmarket);
					main.getScene().registerTouchArea(foodmarket);

				} else if (load.contains("Fountain")) {
					Fountain fountain = new Fountain(x, y,
							images.getFountainImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(fountain);
					main.getFountains().add(fountain);
					// sObjects.add(fountain);
					main.getScene().registerTouchArea(fountain);

				} else if (load.contains("House")) {
					int houselevel = dataBase.readDataBaseInt(i);
					main.setHouseexample(new House(x, y,
							images.getHouseImage(), main
									.getVertexBufferObjectManager(), main, true));
					// main.addEntityScene(Houseexample);
					main.getScene().attachChild(main.getHouseexample());
					main.getHouses().add(main.getHouseexample());
					// a//sObjects.add(Houseexample);
					main.getHouseexample().createChilds();
					if (houselevel != -99999) {
						main.getHouseexample().setLevel(houselevel);
					}
					main.getScene().registerTouchArea(main.getHouseexample());
				} else if (load.contains("Hunters Lodge")) {
					HuntersLodge huntersLodge = new HuntersLodge(x, y,
							images.getHuntersLodgeImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(huntersLodge);
					main.huntersLodges.add(huntersLodge);
					// sObjects.add(huntersLodge);
					main.getScene().registerTouchArea(huntersLodge);

				} else if (load.contains("Mine Deposit Clay")) {
					MineDepositClay mineDepositClay = new MineDepositClay(x, y,
							images.getMineDepositClayImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(mineDepositClay);
					main.getMineDepositClays().add(mineDepositClay);
					// sObjects.add(mineDepositClay);
					main.getScene().registerTouchArea(mineDepositClay);

				} else if (load.contains("Road")) {
					Road road = new Road(x, y, images.getRoadimage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(road);
					main.getRoads().add(road);
					// sObjects.add(road);

				} else if (load.contains("Silo")) {
					Silo silo = new Silo(x, y, images.getSiloImage(),
							main.getVertexBufferObjectManager(), main, number);
					main.getScene().attachChild(silo);
					main.getSilos().add(silo);
					// sObjects.add(silo);
					main.getScene().registerTouchArea(silo);

				} else if (load.contains("Stock")) {
					Debug.e("Load stock");
					Stock stock = new Stock(x, y, images.getStockplaceImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(stock);
					main.getStocks().add(stock);
					// sObjects.add(stock);
					main.getScene().registerTouchArea(stock);
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
					main.setSkinner(new Skinner(x, y, images.getSkinnerImage(),
							main.getVertexBufferObjectManager(), main, true));
					main.getScene().attachChild(main.getSkinner());
					main.getSkinners().add(main.getSkinner());
					// sObjects.add(skinner);
					main.getScene().registerTouchArea(main.getSkinner());

				} else if (load.contains("Stone Cutter")) {
					StoneCutter stonecutter = new StoneCutter(x, y,
							images.getStoneCutterImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(stonecutter);
					main.getStoneCutters().add(stonecutter);
					// a//sObjects.add(stonecutter);
					stonecutter.createNpc();
					main.getScene().registerTouchArea(stonecutter);

				} else if (load.contains("Theatre")) {
					main.setTheatre(new Theatre(x, y, images.getTheatreImage(),
							main.getVertexBufferObjectManager(), main, true));
					main.getScene().attachChild(main.getTheatre());
					main.getTheatres().add(main.getTheatre());
					// sObjects.add(theatre);
					main.getScene().registerTouchArea(main.getTheatre());

				} else if (load.contains("Wood Cutter")) {
					WoodCutter woodcutter = new WoodCutter(x, y,
							images.getWoodCutterImage(),
							main.getVertexBufferObjectManager(), main, true);
					main.getScene().attachChild(woodcutter);
					main.getWoodCutters().add(woodcutter);
					// sObjects.add(woodcutter);
					main.getScene().registerTouchArea(woodcutter);

				} else if (load.contains("Marble")) {
					Debug.e("I SHALL LOAD MARBLE ammount: " + String.valueOf(x));
					Marble = (int) x;

				} else if (load.contains("Wood")) {
					Wood = (int) x;
				} else if (load.contains("Skin")) {
					Armor = (int) x;
				} else if (load.contains("Sculptures")) {
					Sculptures = (int) x;
				} else if (load.contains("Fish")) {
					Fish = (int) x;
				} else if (load.contains("Bronze")) {
					Bronze = (int) x;
				} else if (load.contains("Brick")) {
					Brick = (int) x;
				} else if (load.contains("Brick")) {
					Brick = (int) x;
				} else if (load.contains("Meat")) {
					Meat = (int) x;
				} else if (load.contains("Clay")) {
					Clay = (int) x;
				} else if (load.contains("Clay")) {
					Clay = (int) x;
				}
			} else
				break;
		}
	}

	/**
	 * Save the game
	 */
	public void saveState() {
		dataBase.deleteDataBase();
		main.setObjectAmount(main.getObjectAmount()-1);
		// Sort objects for y coordinates
		for (int i = 0; i < main.getSObjects().size(); i++) {
			Debug.e(main.getSObjects().toString());
			for (int j = 0; j <main.getSObjects().size(); j++) {
				// If y1 < y2, replace y1 with
				if (main.getSObjects().get(i).getY() < main.getSObjects().get(j).getY()) {
					SpriteObject obj = main.getSObjects().get(i);
					main.getSObjects().set(i, main.getSObjects().get(j));
					main.getSObjects().set(j, obj);
				}

			}
		}
		for (int i = 0; i < main.getSObjects().size(); i++) {
			main.getSObjects().get(i).setID(main.getObjectAmount() + main.getStartID() + 1);
			main.setObjectAmount(main.getObjectAmount()+1);
			Debug.e(String.valueOf(main.getSObjects().get(i).id));
		}
		for (int i = 0; i < main.getAsObjects().size(); i++) {
			for (int j = 0; j < main.getAsObjects().size(); j++) {
				AnimatedSpriteObject obj = main.getAsObjects().get(i);
				// If y1 < y2, replace y1 with
				if (main.getAsObjects().get(i).getY() < main.getAsObjects().get(j).getY()) {
					main.getAsObjects().set(i, main.getAsObjects().get(j));
					main.getAsObjects().set(j, obj);
					// Debug.e(main.getAsObjects()..toString());
				}

			}
		}
		for (int i = 0; i < main.getAsObjects().size(); i++) {
			main.getAsObjects().get(i).setID(main.getObjectAmount() + main.getStartID() + 1);
			main.setObjectAmount(main.getObjectAmount()+1);
			Debug.e(String.valueOf(main.getAsObjects().get(i).id));
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
		for (int i = 0; i < main.getSObjects().size(); i++) {
			main.getSObjects().get(i).save();
		}
		for (int i = 0; i < main.getAsObjects().size(); i++) {
			main.getAsObjects().get(i).save();
		}
	}

	/**
	 * Save each information resources, gold etc.
	 */
	public void saveInfo() {
		dataBase.add("Gold", getGold(), 0);
		dataBase.add("Inhabitants", Inhabitants[0], 1);
		dataBase.add("Building Workers", buildingWorkers, 2);
		dataBase.add("MilitaryHoplite", getMilitaryHoplite(), 3);
		dataBase.add("MilitarySlinger", getMilitarySlinger(), 4);
		dataBase.add("Marble", Marble, 5);
		dataBase.add("Wood", Wood, 6);
		dataBase.add("Armor", Armor, 7);
		dataBase.add("Skin", Skin, 8);
		dataBase.add("Sculptures", Sculptures, 9);
		dataBase.add("Fish", Fish, 10);
		dataBase.add("Bronze", Bronze, 11);
		dataBase.add("Brick", Brick, 12);
		dataBase.add("Meat", Meat, 13);
		dataBase.add("Clay", Clay, 14);
	}

	/**
	 * Update the gold
	 * @param gold gold to add
	 */
	public void updateGold(int gold) {
		this.gold += gold;
		if (monthlygain >= 0)
			main.getGoldText().setColor(0f, 1f, 0f);
		else
			main.getGoldText().setColor(1f, 0f, 0f);
		main.getGoldText().setText(Integer.toString(this.gold));
	}

	/**
	 * Sets our gold to a speicfic ammount
	 * @param gold 
	 */
	public void setGold(int gold) {
		this.gold = gold;
		main.getGoldText().setColor(1f, 1f, 1f);
		main.getGoldText().setText(Integer.toString(this.gold));
	}

	/**
	 * Updates our inhabitants 
	 * @param inhabitantss to add
	 * @param houseLevel to add inhabitants to
	 * @param totalInhabitants
	 */
	public void updateInhabitants(int inhabitantss, int houseLevel,
			int totalInhabitants) {
		Inhabitants[0] += inhabitantss;
		Inhabitants[houseLevel] += totalInhabitants;
		main.getInhabitantsText().setColor(1f, 1f, 1f);
		if (houseLevel > 1) {
			Inhabitants[houseLevel - 1] -= totalInhabitants - inhabitantss;
		}
		main.getInhabitantsText().setText(Integer.toString(Inhabitants[0]));
		maxWorkers = (int) ((int) Inhabitants[0] * 0.75);

	}

	/**
	 * Updates our gold and changes the goldtext
	 */
	public void updateGoldMonthly() {
		incomeInhabitants = 0;
		militaryWorkers = getTotalMilitaryWorkers();
		workers = militaryWorkers + buildingWorkers;
		for (int i = 1; i < Inhabitants.length; i++) {
			incomeInhabitants += (float) (Inhabitants[i] * 9 * (0.75 + (0.25 * i)));
		}
		updateIncome();
		setExpenses();
		monthlygain = totalIncome - expenses;
		this.gold += monthlygain;

		// Change the gold text color if you gain or lose money
		if (monthlygain >= 0)
			main.getGoldText().setColor(0f, 1f, 0f);
		else
			main.getGoldText().setColor(1f, 0f, 0f);
		main.getGoldText().setText(Integer.toString(this.gold));
	}

	/**
	 * Goes to the main menu
	 */
	public void goToMainMenu() {
		wipeInGameInfo();
		leaveGame();
		menu = "MainMenu";
	}

	protected void CheckHouseLevel() {
		int j = 0;
		for (int i = 0; i < main.main.getHouses().size(); i++) {

			if (main.getHouses().get(i).HouseLevel > j)
				j = main.getHouses().get(i).HouseLevel;

			HouseLevel = j;
			if (main.getBuybutton() != null)
				main.getBuybutton().HouseLevel = HouseLevel;
		}
	}

	/**
	 * Cleares the current information of the game
	 */
	public void wipeInGameInfo() {
		Inhabitants = new int[InhabitantsSize];
		gold = Constant.startGold;
		workers = 0;
		buildingWorkers = 0;
		militaryWorkers = 0;
		main.messageHistory = new String[100];
		for (int i = 0; i < main.chatHistoryTexts.size(); i++) {
			main.removeEntity(main.chatHistoryTexts.get(i), main.getInGameHUD());
		}
		main.chatHistoryTexts = new ArrayList<Text>();
		cityIcons.clear();
		main.getCityMessages().clear();
		main.initializeArrayLists();
	}

	/**
	 * Leave the game
	 */
	public void leaveGame() {
		main.mScene.clearEntityModifiers();
		main.getScene().registerTouchArea(null); // 0000 0001
		main.getScene().clearTouchAreas();
		main.mScene.clearChildScene();
		main.mScene.detachChildren();
		main.camera.setHUD(null);
		main.camera.reset();
		menu = "MainMenu";
		EnterMainMenu();
	}

	/**
	 * Enter the main menu
	 */
	public void EnterMainMenu() {
		removeMenu();
		main.camera.setCenter(main.CAMERA_WIDTH / 2, main.CAMERA_HEIGHT / 2);
		if (menu == "MainMenu") {
			main.setMainMenuBackground(new Sprite(0, 0, images
					.getMainMenuBackgroundImage(), main
					.getVertexBufferObjectManager()));
			main.setMainMenuDoor(new AnimatedSprite(575, 439, images
					.getMainMenuDoorImage(), main
					.getVertexBufferObjectManager()));
			main.getMainMenuDoor().stopAnimation(0);
			main.getScene().attachChild(main.getMainMenuBackground());
			main.getScene().attachChild(main.getMainMenuDoor());
			main.setMainMenuPlayButton(new MainMenuPlayButton(160 + 256,
					135 + 256, images.getMainMenuPlayButtonImage(), main
							.getVertexBufferObjectManager(), main));
			main.setMainMenuLoadButton(new MainMenuLoadButton(160 + 256,
					135 + 256 + 256, images.getMainMenuLoadButtonImage(), main
							.getVertexBufferObjectManager(), main));
			main.getScene().attachChild(main.getMainMenuPlayButton());
			main.getScene().registerTouchArea(main.getMainMenuPlayButton());
			main.getScene().attachChild(main.getMainMenuLoadButton());
			main.getScene().registerTouchArea(main.getMainMenuLoadButton());
		}

	}

	/**
	 * Remove the in game menu
	 */
	public void removeMenu() {
		if (main.getMenuMainMenuButton() != null) {
			main.removeEntity(main.getMenuMainMenuButton());
			main.removeEntity(main.getMenuSaveButton());
			main.removeEntity(main.getRectangleBlackScreen());
			main.getInGameHUD().unregisterTouchArea(main.menuMainMenuButton);
			main.getInGameHUD().unregisterTouchArea(main.menuSaveButton);
			main.setMenuNull();
			main.addBuildingTouchAreas();
			main.addHudTouchAreas();
		}
	}

	/**
	 * ADds the neccesery touch areas for the map
	 */
	public void addMapTouchAreas() {

		for (int i = 0; i < cityIcons.size(); i++) {
			if (Integer.valueOf(main.CITY[i][8]) == 1) {
				main.getInGameHUD().registerTouchArea(cityIcons.get(i));
				cityIcons.get(i).setAlpha(1);
			}
		}
	}

	private void yearlyTribute() {
		for (int i = 0; i < main.CITY.length; i++) {
			if (main.CITY[i][9] == "1") {
				if (main.CITY[i][10] == "Coin") {
					updateGold(15000);
					main.messagePopUp(main.CITY[i][0]
							+ "Payed you: 15 000 coin for tribute ",
							new org.andengine.util.color.Color(1f, 1f, 1f));
				}
			} else if (main.CITY[i][9] == "2") {
				if (main.CITY[i][10] == "Coin") {
					updateGold(-15000);
					main.messagePopUp("Your city payed 15 000 gold to "
							+ main.CITY[i][0] + " as tribute",
							new org.andengine.util.color.Color(1f, 1f, 1f));
				}
			}
		}

	}

	/**
	 * Remove the map icons
	 */
	public void removeMapTouchAreas() {
		for (int i = 0; i < cityIcons.size(); i++) {
			main.getInGameHUD().unregisterTouchArea(cityIcons.get(i));
			cityIcons.get(i).setAlpha(0);
		}
	}

	/**
	 * Add our city icons
	 */
	public void addCityIcons() {
		if (getCityIcons().size() < main.CITY.length) {
			for (int i = 0; i < main.CITY.length; i++) {
				CityIcon cityIcon = new CityIcon(
						Float.parseFloat((main.CITY[i][6])),
						Float.parseFloat(main.CITY[i][7]),
						images.getCityIconImage(),
						main.getVertexBufferObjectManager(), i, main);
				getCityIcons().add(cityIcon);
				cityIcon.setAlpha(0);
			}
		}
	}

	/**
	 * Leave main menu
	 * 
	 * @param string
	 *            menu to go to
	 */
	public void leaveMainMenu(String string) {
		main.getInGameHUD().clearChildScene();
		main.getInGameHUD().clearTouchAreas();
		main.getScene().clearEntityModifiers();
		main.getScene().clearChildScene();
		main.getScene().detachChildren();
		main.getScene().registerTouchArea(null); // 0000 0001
		main.getScene().clearTouchAreas();
		main.camera.reset();
		menu = "Game";
		main.PAUSE = false;
		EnterGame();
		if (string.equals("load"))
			loadGame();
	}

	/**
	 * Update each level of the inhabitants
	 */
	public void updateLevelInhabitants() {
		for (int j = 0; j < Inhabitants.length; j++) {
			Inhabitants[j] = 0;
			for (int i = 0; i < this.main.getHouses().size(); i++) {
				Inhabitants[j] += main.getHouses().get(i).getInhabitants(j);
			}
		}
	}

	private int getTotalMilitaryWorkers() {
		return militaryHoplite + militarySlinger;
	}

	private void updateIncome() {
		totalIncome = income + incomeInhabitants;
	}

	/**
	 * Updates our workers
	 * @param workers to add
	 * @param maxworkers 
	 */
	public void updateWorkers(int workers, int maxworkers) {
		buildingWorkers += workers;
		maxworkers = (int) ((int) Inhabitants[0] * 0.75);
		this.workers = buildingWorkers + militaryWorkers;
	}

	/**
	 * Creates the game
	 */
	public void EnterGame() {
		if (menu.equals("Game")) {
			timerHandlerMonthly();
			// Gold = main.startGold;
			// Creates a new scene
			main.getScene().setBackground(
					new Background(0.25490196078f, 0.72f, 0.22f));
			main.getScene().setOnSceneTouchListener(main);
			main.setUpHUD(); // Initializes the HUD and it's components

			main.setAresTemple(new Sprite(0, 0, images.getAresTempleImage(),
					main.getVertexBufferObjectManager()));
			main.getScene().attachChild(main.getAresTemple());
			// fishspot = new FishSpot(128, 128, fishSpotImage(),
			// main.getVertexBufferObjectManager());
			// fishspot.animate(200);
			// fishspots.add(fishspot);
			// main.getScene().attachChild(fishspot);
			// fishspot = new FishSpot(196, 256, fishSpotImage(),
			// main.getVertexBufferObjectManager());
			// fishspot.animate(200);
			// fishspots.add(fishspot);
			// main.getScene().attachChild(fishspot);

			main.setMouse(new Sprite(main.CAMERA_WIDTH / 2,
					main.CAMERA_HEIGHT / 2, images.getHouseImage(), main
							.getVertexBufferObjectManager()));
			main.getMouse().attachChild(main.getMouse());

			main.camera.setCenter(main.getMouse().getX(), main.getMouse()
					.getY());
			main.CreateMap();
		}

	}

	/**
	 * Return to the game after the battlefield
	 */
	public void returnGameFromBattleField() {
		main.getBattleHUD().clearChildScene();
		main.getBattleHUD().clearTouchAreas();
		main.camera.setHUD(null);
		leaveMainMenu("load");
		militaryHoplite += militaryHopliteWar / 2;
		menu = "Game";
	}

	/**
	 * Return the offensive power of the city
	 * 
	 * @return
	 */
	public int getMilitaryOffensivePower() {
		int power = 0;
		power += militarySlingerWar * Constant.POWERSLINGER;
		power += militaryHopliteWar * Constant.POWERHOPLITE;

		return power;
	}

	public ArrayList<CityIcon> getCityIcons() {
		return this.cityIcons;
	}

	public void resetGameInfo() {
		cityIcons = new ArrayList<CityIcon>();
	}

	public String getMenu() {
		return this.menu;
	}

	public int getMaxWorkers() {
		return maxWorkers;
	}

	public void setMaxWorkers(int maxWorkers) {
		this.maxWorkers = maxWorkers;
	}

	public int getHouseLevel() {
		return HouseLevel;
	}

	public int getWorkers() {
		updateWorkers(0,0);
		return this.workers;
	}

	public String getMonthlygain() {
		return String.valueOf(this.monthlygain);
	}

	public float getGameSpeed() {
		return gameSpeed;
	}

	public ResourceImage getImages() {
		return images;
	}

	public int getGold() {
		return this.gold;
	}

	public float getExpenses() {
		return expenses;
	}

	public MainActivity getActivity() {
		return this.main;
	}

	public Mission getMission() {
		return this.mission;
	}

	public int getMilitaryHopliteWar() {
		return this.militaryHopliteWar;
	}

	public void setMilitaryHoplite(int x) {
		this.militaryHoplite = x;
	}

	public void setMilitarySlinger(int x) {
		this.militarySlinger = x;
	}

	public int getMilitaryHoplite() {
		return this.getMilitaryHoplite();
	}

	public int getMilitarySlinger() {
		return this.getMilitarySlinger();
	}

	public void setMilitaryHopliteWar(int militaryHoplite2) {
		this.militaryHopliteWar = militaryHoplite2;
	}

	public DataBase getDataBase() {
		return this.dataBase;
	}

	/**
	 * Re-attaches all of our sprites.
	 * @param sObjects arraylist to update
	 * @param asObjects arraylist to update
	 * @param scene to attach sprites
	 */
	public void updateScreen(ArrayList<SpriteObject> sObjects,
			ArrayList<AnimatedSpriteObject> asObjects, Scene scene) {
//		ArrayList<SpriteObject> sObject = new ArrayList<SpriteObject>();
//		ArrayList<AnimatedSpriteObject> asObject = new ArrayList<AnimatedSpriteObject>();
		for(int i = 0; i < sObjects.size();i++){
			for(int j = 1; j < sObjects.size();j++){
				if(sObjects.get(j).getZ() < (sObjects.get(i).getZ()) ){
					SpriteObject tempSObject = sObjects.get(i);
					sObjects.set(i, sObjects.get(j));
					sObjects.set(j, tempSObject);
				}
			}
		}
//		for(int i = 0; i < sObjects.size();i++){
//			sObject.add(sObjects.get(i));
//		}
		
		for(int i = 0; i < asObjects.size();i++){
			for(int j = 1; j < asObjects.size();j++){
				if(asObjects.get(j).getZ() < (asObjects.get(i).getZ()) ){
					AnimatedSpriteObject tempSObject = asObjects.get(i);
					asObjects.set(i, asObjects.get(j));
					asObjects.set(j, tempSObject);
				}
			}
		}
//		for(int i = 0; i < asObjects.size();i++){
//			asObject.add(asObjects.get(i));
//		}
		int lastnumber = 0;
		for(int i = 0; i < sObjects.size();i++){
			for(int j = lastnumber; j < asObjects.size();j++){
				if(sObjects.get(i).getZ() <= asObjects.get(j).getZ()){
					Debug.e("ASObject Y: " + asObjects.get(j).getZ());
					main.removeEntityNotFromArray(asObjects.get(j),scene);
				}
				else{
					lastnumber = j;
					break;
				}
			}
				main.removeEntityNotFromArray(sObjects.get(i),scene);
				if(i == sObjects.size()-1){
					for(int j = lastnumber; j < asObjects.size();j++){
							Debug.e("ASObject Y: " + asObjects.get(j).getZ());
							main.removeEntityNotFromArray(asObjects.get(j),scene);
					}
				}
		}
		
	}

}
