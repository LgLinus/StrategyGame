package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.Barrack;
import com.ligr.strategygame.BrickFoundry;
import com.ligr.strategygame.Butcher;
import com.ligr.strategygame.Farm;
import com.ligr.strategygame.FishingHut;
import com.ligr.strategygame.FoodMarket;
import com.ligr.strategygame.Fountain;
import com.ligr.strategygame.House;
import com.ligr.strategygame.HuntersLodge;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.MineDepositClay;
import com.ligr.strategygame.Road;
import com.ligr.strategygame.Silo;
import com.ligr.strategygame.Skinner;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.Stock;
import com.ligr.strategygame.StoneCutter;
import com.ligr.strategygame.Theatre;
import com.ligr.strategygame.UpgradeArrowHouse;
import com.ligr.strategygame.WoodCutter;

import constants.ConstantBuildings;

public class BuyButton extends Sprite {

	private static String currentbuilding;
	public int HouseLevel;
	public BuyButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		HouseLevel = MainActivity.HouseLevel;
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			HouseLevel = MainActivity.HouseLevel;
			if(MainActivity.placebuilding.gotSpace()){
			if(MainActivity.placebuilding.currentBuilding=="House"){
				if(MainActivity.Gold>=ConstantBuildings.COSTHOUSE){
					
					MainActivity.Houseexample = new House(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.HouseImage, this.getVertexBufferObjectManager(), false);
					MainActivity.Houses.add(MainActivity.Houseexample);
					MainActivity.mScene.attachChild(MainActivity.Houseexample);
					MainActivity.updateGold(-100);
					addASObject(MainActivity.Houseexample);
					MainActivity.Houseexample.createChilds();
				}
				else{
					MainActivity.boolplacebuilding=false;
				}
			
				
			}
			else if(MainActivity.placebuilding.currentBuilding=="Road"){
				MainActivity.road = new Road(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.roadimage, this.getVertexBufferObjectManager());
				MainActivity.Roads.add(MainActivity.road);
				MainActivity.mScene.attachChild(MainActivity.road);
			
			}
			else if(MainActivity.placebuilding.currentBuilding=="Fountain"){
				MainActivity.fountain = new Fountain(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.fountainImage, this.getVertexBufferObjectManager(), false);
				MainActivity.Fountains.add(MainActivity.fountain);
				MainActivity.mScene.attachChild(MainActivity.fountain);
				addSObject(MainActivity.fountain);
			}
			else if(MainActivity.placebuilding.currentBuilding=="Farm"){
				MainActivity.farm = new Farm(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.farmImage, this.getVertexBufferObjectManager(), false);
				MainActivity.Farms.add(MainActivity.farm);
				MainActivity.mScene.attachChild(MainActivity.farm);
				MainActivity.farm.animate(1000);
				addASObject(MainActivity.farm);
			
			}else if(MainActivity.placebuilding.currentBuilding=="Silo"){
				MainActivity.silo = new Silo(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.siloImage, this.getVertexBufferObjectManager(), false);
				MainActivity.Silos.add(MainActivity.silo);
				MainActivity.mScene.attachChild(MainActivity.silo);
				addSObject(MainActivity.silo);
			
			}else if(MainActivity.placebuilding.currentBuilding=="Food Market"){
				MainActivity.foodmarket = new FoodMarket(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.FoodMarketImage, this.getVertexBufferObjectManager(), false);
				MainActivity.FoodMarkets.add(MainActivity.foodmarket);
				MainActivity.mScene.attachChild(MainActivity.foodmarket);
				addSObject(MainActivity.foodmarket);
			
			}else if(MainActivity.placebuilding.currentBuilding=="Theatre"){
				if(MainActivity.Marble >= ConstantBuildings.COSTTHEATREMARBLE && MainActivity.Gold>= ConstantBuildings.COSTTHEATRECOIN && HouseLevel >=2){
				MainActivity.theatre = new Theatre(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.theatreImage, this.getVertexBufferObjectManager(), false);
				MainActivity.Theatres.add(MainActivity.theatre);
				MainActivity.mScene.attachChild(MainActivity.theatre);
				MainActivity.updateGold(-ConstantBuildings.COSTTHEATRECOIN);
				RemoveResources("Marble",ConstantBuildings.COSTTHEATREMARBLE);
				addSObject(MainActivity.theatre);
				}
				else if (HouseLevel <2){
					MainActivity.Message("You need a house with a level 2 upgrade in order to build a Theatre",Color.RED);	
				}
				else{
					MainActivity.Message("You cannot afford the Theatre",Color.RED);	
					
				}
			
			}
			else if(MainActivity.placebuilding.currentBuilding=="Stone Cutter"){
				if(HouseLevel>=2 && MainActivity.Gold >= ConstantBuildings.COSTSTONECUTTERCOIN){
				MainActivity.stonecutter = new StoneCutter(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.stoneCutterImage, this.getVertexBufferObjectManager(), false);
				MainActivity.StoneCutters.add(MainActivity.stonecutter);
				MainActivity.mScene.attachChild(MainActivity.stonecutter);
				MainActivity.updateGold(-ConstantBuildings.COSTSTONECUTTERCOIN);
				addASObject(MainActivity.stonecutter);
				MainActivity.stonecutter.createNpc();
			}
			else if (HouseLevel <2){
				MainActivity.Message("You need a house with a level 2 upgrade in order to build a Stone Cutter",Color.RED);	
			}
			else if (MainActivity.Gold < ConstantBuildings.COSTSTONECUTTERCOIN){
				MainActivity.Message("You cannot afford a Stone Cutter building", Color.RED);
				}
			}
			else if(MainActivity.placebuilding.currentBuilding=="Stock" ){
				if(MainActivity.Gold >= ConstantBuildings.COSTSTOCKCOIN){
					MainActivity.stock = new Stock(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY()-32, MainActivity.stockplaceImage, this.getVertexBufferObjectManager(), false);
					MainActivity.Stocks.add(MainActivity.stock);
					MainActivity.stock.globalKind = MainActivity.tempGlobalKind;
					MainActivity.mScene.attachChild(MainActivity.stock);
					MainActivity.updateGold(-ConstantBuildings.COSTSTOCKCOIN);
					addSObject(MainActivity.stock);}
				else
					MainActivity.Message("You cannot afford a Stock\nYou need "+ ConstantBuildings.COSTSTOCKCOIN + " gold", Color.RED);
						
					
			}else if(MainActivity.placebuilding.currentBuilding=="Wood Cutter"){
				if(HouseLevel >=2 && MainActivity.Gold >= ConstantBuildings.COSTWOODCUTTERCOIN){
				MainActivity.woodcutter = new WoodCutter(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.woodCutterImage, this.getVertexBufferObjectManager(), false);
				MainActivity.WoodCutters.add(MainActivity.woodcutter);
				MainActivity.mScene.attachChild(MainActivity.woodcutter);
				MainActivity.updateGold(-ConstantBuildings.COSTWOODCUTTERCOIN);
				addSObject(MainActivity.woodcutter);}
			else if (HouseLevel <3){
				MainActivity.Message("You need a house with a level 3 upgrade in order to build a Wood Cutter",Color.RED);	
					
			}
			else
				MainActivity.Message("You cannot afford a Wood cutter building\nYou need "+ ConstantBuildings.COSTWOODCUTTERCOIN + " gold", Color.RED);
				}
			else if(MainActivity.placebuilding.currentBuilding=="Hunters Lodge" ){
				if(HouseLevel >=3 && MainActivity.Gold >= ConstantBuildings.COSTHUNTERSLODGECOIN && MainActivity.Wood >= ConstantBuildings.COSTHUNTERSLODGEWOOD){
				MainActivity.huntersLodge = new HuntersLodge(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.huntersLodgeImage, this.getVertexBufferObjectManager(), false);
				MainActivity.huntersLodges.add(MainActivity.huntersLodge);
				MainActivity.mScene.attachChild(MainActivity.huntersLodge);
				MainActivity.updateGold(-ConstantBuildings.COSTHUNTERSLODGECOIN);
				RemoveResources("Wood",ConstantBuildings.COSTHUNTERSLODGEWOOD);
				addSObject(MainActivity.huntersLodge);
				}
				else if (HouseLevel <3){
					MainActivity.Message("You need a house with a level 3 upgrade in order to build a Hunters Lodge",Color.RED);	
					}
				else{
					MainActivity.Message("You cannot afford a Hunters lodge.\nYou need "+ ConstantBuildings.COSTHUNTERSLODGECOIN + " gold, and " + ConstantBuildings.COSTHUNTERSLODGEWOOD + " wood" , Color.RED);
				}
			}else if(MainActivity.placebuilding.currentBuilding=="Skinner" ){
				if(HouseLevel >=3 && MainActivity.Gold >= ConstantBuildings.COSTSKINNERCOIN && MainActivity.Wood >= ConstantBuildings.COSTSKINNERWOOD){
				MainActivity.skinner= new Skinner(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.skinnerImage, this.getVertexBufferObjectManager(), false);
				MainActivity.skinners.add(MainActivity.skinner);
				MainActivity.mScene.attachChild(MainActivity.skinner);
				MainActivity.updateGold(-ConstantBuildings.COSTSKINNERCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTSKINNERWOOD);
				addSObject(MainActivity.skinner);
				}
				else if (HouseLevel <3){
					MainActivity.Message("You need a house with a level 3 upgrade in order to build a Skinner",Color.RED);	
					}
				else{
					MainActivity.Message("You cannot afford a Skinner.\nYou need "+ ConstantBuildings.COSTSKINNERCOIN + " gold, and " + ConstantBuildings.COSTSKINNERWOOD + " wood" , Color.RED);
				}
			}else if(MainActivity.placebuilding.currentBuilding=="Butcher" ){
				if(HouseLevel >=3 && MainActivity.Gold >= ConstantBuildings.COSTSKINNERCOIN && MainActivity.Wood >= ConstantBuildings.COSTSKINNERWOOD){
				MainActivity.butcher = new Butcher(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.butcherImage, this.getVertexBufferObjectManager(), false);
				MainActivity.butchers.add(MainActivity.butcher);
				MainActivity.mScene.attachChild(MainActivity.butcher);
				MainActivity.updateGold(-ConstantBuildings.COSTBUTCHERCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTBUTCHERWOOD);
				addSObject(MainActivity.butcher);
				}
				else if (HouseLevel <3){
					MainActivity.Message("You need a house with a level 3 upgrade in order to build a butcher",Color.RED);	
					}
				else{
					MainActivity.Message("You cannot afford a butcher.\nYou need "+ ConstantBuildings.COSTBUTCHERCOIN + " gold, and " + ConstantBuildings.COSTBUTCHERWOOD + " wood" , Color.RED);
				}
			}
			else if(MainActivity.placebuilding.currentBuilding=="Fishing Hut" ){
				if(HouseLevel >=3 && MainActivity.Gold >= ConstantBuildings.COSTHUNTERSLODGECOIN && MainActivity.Wood >= ConstantBuildings.COSTFISHINGHUTWOOD){
				MainActivity.fishinghut = new FishingHut(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.fishingHutImage, this.getVertexBufferObjectManager(), false);
				MainActivity.FishingHuts.add(MainActivity.fishinghut);
				MainActivity.mScene.attachChild(MainActivity.fishinghut);
				MainActivity.updateGold(ConstantBuildings.COSTFISHINGHUTWOOD);
				RemoveResources("Wood",ConstantBuildings.COSTFISHINGHUTWOOD);
				addSObject(MainActivity.fishinghut);
				}
				else if (HouseLevel <3){
					MainActivity.Message("You need a house with a level 3 upgrade in order to build a Fishing hut",Color.RED);	
					}
				else{
					MainActivity.Message("You cannot afford a Fishing hut.\nYou need "+ ConstantBuildings.COSTFISHINGHUTCOIN + " gold, and " + ConstantBuildings.COSTFISHINGHUTWOOD + " wood" , Color.RED);
				}}
			else if(MainActivity.placebuilding.currentBuilding=="Barrack" ){
				if(HouseLevel >=4 && MainActivity.Gold >= ConstantBuildings.COSTBARRACKCOIN && MainActivity.Wood >= ConstantBuildings.COSTBARRACKWOOD && MainActivity.Marble >= ConstantBuildings.COSTBARRACKMARBLE && MainActivity.Brick >= ConstantBuildings.COSTBARRACKBRICK){
				MainActivity.barrack = new Barrack(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.barrackImage, this.getVertexBufferObjectManager(), false);
				MainActivity.barracks.add(MainActivity.barrack);
				MainActivity.mScene.attachChild(MainActivity.barrack);
				MainActivity.updateGold(ConstantBuildings.COSTBARRACKCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTBARRACKWOOD);
				RemoveResources("Brick",ConstantBuildings.COSTBARRACKBRICK);
				RemoveResources("Marble",ConstantBuildings.COSTBARRACKMARBLE);
				addSObject(MainActivity.barrack);
				}
				else if (HouseLevel <4){
					MainActivity.Message("You need a house with a level 4 upgrade in order to build a Barrack",Color.RED);	
					}
				else{
					MainActivity.Message("You cannot afford a Fishing hut.\nYou need "+ ConstantBuildings.COSTBARRACKCOIN + " gold, and " + ConstantBuildings.COSTBARRACKWOOD + " wood"+ ConstantBuildings.COSTBARRACKBRICK + " bricks" , Color.RED);
				}}
			else if(MainActivity.placebuilding.currentBuilding=="Clay Mine" ){
				if(HouseLevel >=4 && MainActivity.Gold >= ConstantBuildings.COSTCLAYMINECOIN && MainActivity.Wood >= ConstantBuildings.COSTCLAYMINEWOOD && MainActivity.Marble >= ConstantBuildings.COSTCLAYMINEMARBLE ){
				MainActivity.clayMine = new MineDepositClay(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.clayMineImage, this.getVertexBufferObjectManager(), false);
				MainActivity.clayMines.add(MainActivity.clayMine);
				MainActivity.mScene.attachChild(MainActivity.clayMine);
				MainActivity.updateGold(ConstantBuildings.COSTCLAYMINECOIN);
				RemoveResources("Wood",ConstantBuildings.COSTCLAYMINEWOOD);
				RemoveResources("Marble",ConstantBuildings.COSTCLAYMINEMARBLE);
				addSObject(MainActivity.clayMine);
				}
				else if (HouseLevel <4){
					MainActivity.Message("You need a house with a level 4 upgrade in order to build a clay mine",Color.RED);	
					}
				else{
					MainActivity.Message("You cannot afford a clay mine.\nYou need "+ ConstantBuildings.COSTCLAYMINECOIN + " gold, and " + ConstantBuildings.COSTCLAYMINEWOOD + " wood" , Color.RED);
				}}
			else if(MainActivity.placebuilding.currentBuilding=="Brick Foundry" ){
				if(HouseLevel >=4 && MainActivity.Gold >= ConstantBuildings.COSTBRICKFOUNDRYCOIN && MainActivity.Wood >= ConstantBuildings.COSTBRICKFOUNDRYWOOD && MainActivity.Marble >= ConstantBuildings.COSTBRICKFOUNDRYMARBLE ){
				MainActivity.brickFoundry = new BrickFoundry(MainActivity.placebuilding.getX(), MainActivity.placebuilding.getY(), MainActivity.brickFoundryImage, this.getVertexBufferObjectManager(), false);
				MainActivity.brickFoundrys.add(MainActivity.brickFoundry);
				MainActivity.mScene.attachChild(MainActivity.brickFoundry);
				MainActivity.updateGold(ConstantBuildings.COSTBRICKFOUNDRYCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTBRICKFOUNDRYWOOD);
				RemoveResources("Marble",ConstantBuildings.COSTBRICKFOUNDRYMARBLE);
				addSObject(MainActivity.brickFoundry);
				}
				else if (HouseLevel <4){
					MainActivity.Message("You need a house with a level 4 upgrade in order to build a brick foundry",Color.RED);	
					}
				else{
					MainActivity.Message("You cannot afford a brick foundry.\nYou need "+ ConstantBuildings.COSTBRICKFOUNDRYCOIN + " gold, and " + ConstantBuildings.COSTBRICKFOUNDRYWOOD + " wood" , Color.RED);
				}}
			
		}}
		return true;
	
	}

	/**
	 * Adds an animated sprite to our list of animated objects
	 * @param asObject Object to be added
	 */
	private void addASObject(AnimatedSpriteObject asObject) {
	//MainActivity.asObjects.add(asObject);
	//	MainActivity.mScene.updateDepth();
	}
	/**
	 * Adds an sprite to our list of sprite objects
	 * @param asObject Object to be added
	 */
	private void addSObject(SpriteObject sObject) {
	//MainActivity.sObjects.add(sObject);
	//	MainActivity.mScene.updateDepth();	
	}

	public void RemoveResources(String kind, int ammount) {
	for(int i = 0;i<MainActivity.Stocks.size();i++){
			if(kind =="Marble"){
				if(MainActivity.Stocks.get(i).Marble >= ammount){
					MainActivity.Stocks.get(i).Marble -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Marble -= ammount;
					break;
				}}
			if(kind =="Wood"){
				if(MainActivity.Stocks.get(i).Wood >= ammount){
					MainActivity.Stocks.get(i).Wood -= ammount;
					Debug.e("GOGOGO");
					MainActivity.Wood -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					break;
				}}
			if(kind =="Brick"){
				if(MainActivity.Stocks.get(i).Brick >= ammount){
					MainActivity.Stocks.get(i).Brick -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Brick -= ammount;
					break;
				}}
			if(kind =="Bronze"){
				if(MainActivity.Stocks.get(i).Bronze >= ammount){
					MainActivity.Stocks.get(i).Bronze -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Bronze -= ammount;
					break;
				}}

			if(kind =="Skin"){
				if(MainActivity.Stocks.get(i).Skin >= ammount){
					MainActivity.Stocks.get(i).Skin -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Skin -= ammount;
					break;
				}}
			if(kind =="Armor"){
				if(MainActivity.Stocks.get(i).Armor >= ammount){
					MainActivity.Stocks.get(i).Armor -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Armor -= ammount;
					break;
				}}
			if(kind =="Clay"){
				if(MainActivity.Stocks.get(i).Clay >= ammount){
					MainActivity.Stocks.get(i).Clay -= ammount;
					MainActivity.Stocks.get(i).removeResource(kind, ammount);
					MainActivity.Clay-= ammount;
					break;
				}}

	
		}}

	public void RemoveResources(String kind, int ammount, Stock stock) {
			if(kind =="Marble"){
					if(stock.Marble >= ammount){
						stock.Marble -= ammount;
						stock.removeResource(kind, ammount);
						MainActivity.Marble -= ammount;
					}}
				if(kind =="Wood"){
					if(stock.Wood >= ammount){
						stock.Wood -= ammount;
						MainActivity.Wood -= ammount;
						stock.removeResource(kind, ammount);
					}}
				if(kind =="Brick"){
					if(stock.Brick >= ammount){
						stock.Brick -= ammount;
						stock.removeResource(kind, ammount);
						MainActivity.Brick -= ammount;
					}}
				if(kind =="Bronze"){
					if(stock.Bronze >= ammount){
						stock.Bronze -= ammount;
						stock.removeResource(kind, ammount);
						MainActivity.Bronze -= ammount;
					}}

				if(kind =="Skin"){
					if(stock.Skin >= ammount){
						stock.Skin -= ammount;
						stock.removeResource(kind, ammount);
						MainActivity.Skin -= ammount;
					}}
				if(kind =="Armor"){
					if(stock.Armor >= ammount){
						stock.Armor -= ammount;
						stock.removeResource(kind, ammount);
						MainActivity.Armor -= ammount;
					}}
				if(kind =="Clay"){
					if(stock.Clay >= ammount){
						stock.Clay -= ammount;
						stock.removeResource(kind, ammount);
						MainActivity.Clay-= ammount;
					}}
}}