package com.ligr.strategygame.constants;

public class ConstantBuildings {
	/*
	 * Workers needed for each building
	 */
	public static final int WORKERSARMORY = 0;
	public static final int WORKERSBARRACK = 12;
	public static final int WORKERSBRICKFOUNDRY = 10;
	public static final int WORKERSBUTCHER = 10;
	public static final int WORKERSFARM = 11;
	public static final int WORKERSFISHINGHUT = 12;
	public static final int WORKERSFOODMARKET = 5;
	public static final int WORKERSWELL = 4;
	public static final int WORKERSHUNTERSLODGE = 12;
	public static final int WORKERSBRONZEMINE = 0;
	public static final int WORKERSCLAYMINE = 12;
	public static final int WORKERSSILO = 5;
	public static final int WORKERSSKINNER = 10;
	public static final int WORKERSSTOCK = 8;
	public static final int WORKERSSTONECUTTER = 8;
	public static final int WORKERSTHEATRE = 0;
	public static final int WORKERSWOODCUTTER = 8;
	public static final int WORKERSGRANARY = 10;
	
	/*
	 * Range for certain buildings
	 */
	
	public static final int RANGESTONECUTTER = 384;
	public static final int RANGEWOODCUTTER = 384;
	/*
	 * Expenses for each building
	 */

	public static final int EXPENSESARMORY = 350;
	public static final int EXPENSESBARRACK = 400;
	public static final int EXPENSESBRICKFOUNNDRY = 350;
	public static final int EXPENSESBUTCHER = 275;
	public static final int EXPENSESFARM = 150;
	public static final int EXPENSESFISHINGHUT= 350;
	public static final int EXPENSESFOODMARKET = 100;
	public static final int EXPENSESWELL = 60;
	public static final int EXPENSESHOUSE = 0;
	public static final int EXPENSESHUNTERSLODGE = 250;
	public static final int EXPENSESBRONZEMINE = 400;
	public static final int EXPENSESMINEDEPOSITCLAY = 300;
	public static final int EXPENSESSILO = 80;
	public static final int EXPENSESSKINNER = 275;
	public static final int EXPENSESSTOCK= 200;
	public static final int EXPENSESSTONECUTTER = 200;
	public static final int EXPENSESTHEATRE = 250;
	public static final int EXPENSESWOODCUTTER = 250;
	public static final int EXPENSESGRANARY = 150;
	public static final int EXPENSESHOPLITE = 75;
	
	/*
	 * Descriptions for buildings
	 */
	public static final String DESCRIPTIONARMORY = "An armory creates armor out of bronze, which is needed for some soldiers";
	public static final String DESCRIPTIONBARRACK = "You can train soldiers in the barrack";
	public static final String DESCRIPTIONBRICKFOUNDRY = "The brick foundry creates bricks out of clay";
	public static final String DESCRIPTIONBUTCHER = "The butcher collects the animals collected by \nhunter lodges";
	public static final String DESCRIPTIONFARM = "The farm provides your city with food";
	public static final String DESCRIPTIONFISHINGHUT = "The fishing hut provides your city with food";
	public static final String DESCRIPTIONFOODMARKET = "The food market distibutes the city food";
	public static final String DESCRIPTIONHOUSE = "People live in the houses. By meeting certain \nconditions you can upgrade the houses\nthis will increase your gold income and population";
	public static final String DESCRIPTIONHUNTERSLODGE = "The hunters lodge hunts animals";
	public static final String DESCRIPTIONBRONZEMINE = "The bronz emine collects bronze, which is used in armor and sculptures.";
	public static final String DESCRIPTIONCLAYMINE= "The clay mine collects clay, which is used in bricks";
	public static final String DESCRIPTIONROAD= "A road connects your city";
	public static final String DESCRIPTIONSILO= "The silo stores wheat";
	public static final String DESCRIPTIONSKINNER= "The skinner produces skin abd ckithes from \nthe butcher";
	public static final String DESCRIPTIONSTONECUTTER= "The stone cutter produces marble, \nbuild it close to a marble deposit";
	public static final String DESCRIPTIONSTOCK= "The stock stores various resources like marble,\n wood and clay";
	public static final String DESCRIPTIONTHEATRE= "The theatre spreads amusement in your city. Build \nit close to your houses to fill\ntheir cultural needs";
	public static final String DESCRIPTIONWELL = "The well provides nearby houses with water";
	public static final String DESCRIPTIONWOODCUTTER= "The wood cutter harvest trees,\n build it close to trees for maximum efficency";
	
	/* 
	 * Title for the buildings
	 */

	public static final String TITLEARMORY = "Armory";
	public static final String TITLEBARRACK = "Barrack";
	public static final String TITLEBRICKFOUNDRY = "Brick Foundry";
	public static final String TITLEBRONZEMINE = "Bronze Mine";
	public static final String TITLEBUTCHER = "Butcher";
	public static final String TITLEFARM = "Farm";
	public static final String TITLEFISHINGHUT = "Fishing Hut";
	public static final String TITLEFOODMARKET= "Food Market";
	public static final String TITLEHOUSE = "House";
	public static final String TITLEHUNTERSLODGE = "Hunters Lodge";
	public static final String TITLEMINEDEPOSITCLAY = "Mine Deposit Clay";
	public static final String TITLEROAD = "Road";
	public static final String TITLESILO = "Silo";
	public static final String TITLESKINNER = "Skinner";
	public static final String TITLESTOCK = "Stock";
	public static final String TITLESTONECUTTER= "Stone Cutter";
	public static final String TITLETHEATRE = "Theatre";
	public static final String TITLEWELL = "Well";
	public static final String TITLEWOODCUTTER = "Wood Cutter";
	
	/*
	 * Levels needed for various buildings
	 */	
	public static final int HOUSEREQARMORY = 4;
	public static final int HOUSEREQBARRACK = 4;
	public static final int HOUSEREQBRICKFOUNDRY = 4;
	public static final int HOUSEREQBUTCHER = 3;
	public static final int HOUSEREQFARM = 1;
	public static final int HOUSEREQFISHINGHUT = 3;
	public static final int HOUSEREQFOODMARKET = 1;
	public static final int HOUSEREQWELL = 1;
	public static final int HOUSEREQHUNTERSLODGE = 3;
	public static final int HOUSEREQBRONZEMINE = 4;
	public static final int HOUSEREQCLAYMINE = 4;
	public static final int HOUSEREQSILO = 1;
	public static final int HOUSEREQSKINNER = 3;
	public static final int HOUSEREQSTOCK = 1;
	public static final int HOUSEREQSTONECUTTER = 2;
	public static final int HOUSEREQTHEATRE = 2;
	public static final int HOUSEREQWOODCUTTER = 3;
	public static final int HOUSEREQGRANARY = 5;
		
	/*
	 * Different cost of resources/gold for various buildings
	 */

	public static final int COSTROADCOIN = 20;
	
	public static final int COSTTHEATREMARBLE = 2;
	
	public static final int COSTTHEATRECOIN = 650;
	
	public static final int COSTFARMCOIN = 300;
	
	public static final int COSTSILOCOIN = 200;
	
	public static final int COSTFOODMARKETCOIN = 200;
	
	public static final int COSTSTOCKCOIN = 425;
	
	public static final int COSTSTONECUTTERCOIN = 500;
	
	public static final int COSTWELLCOIN = 150;
	
	public static final int COSTWOODCUTTERCOIN = 750;
	
	public static final int COSTHUNTERSLODGECOIN = 550;
	public static final int COSTHUNTERSLODGEWOOD = 3;
	
	public static final int COSTFISHINGHUTWOOD = 5;
	public static final int COSTFISHINGHUTCOIN = 600;
	
	public static final int COSTHOUSECOIN = 100;
	
	public static final int COSTARMORYCOIN = 600;
	public static final int COSTARMORYBRICK = 2;
	public static final int COSTARMORYWOODMONTHLY = 1;
	public static final int COSTARMORYMARBLE = 1;
	
	public static final int COSTBRONZEMINEBRICK = 4;
	public static final int COSTBRONZEMINECOIN = 700;
	public static final int COSTBRONZEMINEMARBLE = 4;
	public static final int COSTBRONZEMINEWOOD = 3;
	
	public static final int COSTSKINNERWOOD = 3;
	public static final int COSTSKINNERCOIN = 500;
	
	public static final int COSTBUTCHERWOOD = 3;
	public static final int COSTBUTCHERCOIN = 475;
	
	public static final int COSTCLAYMINECOIN = 600;
	public static final int COSTCLAYMINEMARBLE = 4;
	public static final int COSTCLAYMINEWOOD = 4;
	
	public static final int COSTBRICKFOUNDRYCOIN = 700;
	public static final int COSTBRICKFOUNDRYMARBLE = 3;
	public static final int COSTBRICKFOUNDRYWOOD = 6;
	public static final int COSTBRICKFOUNDRYWOODMONTHLY = 1;
	
	public static final int COSTBARRACKCOIN = 1000;
	public static final int COSTBARRACKMARBLE = 5;
	public static final int COSTBARRACKWOOD = 6;
	public static final int COSTBARRACKBRICK = 4;
	
	public static final int COSTHOPLITECOIN = 250;
	public static final int COSTHOPLITEARMOR = 2;
	public static final int RANGE = 512;

	public static final int HOUSEINHABITANTSGAINLVL[] = { 8, 4, 6, 9 };
	public static final int HOUSEANIMATIONLVLSTART[] = { 0, 1, 5, 9 };
	public static final int HOUSEANIMATIONLVLEND[] = { 0, 4, 8, 12 };

}
