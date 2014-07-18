package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;
import com.ligr.strategygame.UpgradeArrowHouse;
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
import com.ligr.strategygame.constants.ConstantBuildings;


/**
 * When buybutton is pressed it creates the selected building.
 * @author LgLinuss
 *
 */
public class BuyButton extends Sprite {

	private String currentbuilding;
	public int HouseLevel;
	public MainActivity main;
	public BuyButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		HouseLevel = main.getController().getHouseLevel();
	}

	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			HouseLevel = main.getController().getHouseLevel();
			if(main.placebuilding.gotSpace()){
			if(main.placebuilding.currentBuilding=="House"){
				if(main.getController().getGold()>=ConstantBuildings.COSTHOUSE){
					
					main.setHouseexample(new House(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getHouseImage(), this.getVertexBufferObjectManager(),main, false));
					main.getHouses().add(main.getHouseexample());
					main.mScene.attachChild(main.getHouseexample());
					main.getController().updateGold(-100);
					
					main.getHouseexample().createChilds();
				}
				else{
					main.boolplacebuilding=false;
				}
			
				
			}
			else if(main.placebuilding.currentBuilding=="Road"){
				main.road = new Road(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getRoadimage(), this.getVertexBufferObjectManager(), main, mFlippedHorizontal);
				main.getRoads().add(main.road);
				main.mScene.attachChild(main.road);
			
			}
			else if(main.placebuilding.currentBuilding=="Fountain"){
				main.fountain = new Fountain(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getFountainImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getFountains().add(main.fountain);
				main.mScene.attachChild(main.fountain);
				
			}
			else if(main.placebuilding.currentBuilding=="Farm"){
				main.farm = new Farm(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getFarmImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getFarms().add(main.farm);
				main.mScene.attachChild(main.farm);
				main.farm.animate(1000);
			
			
			}else if(main.placebuilding.currentBuilding=="Silo"){
				main.silo = new Silo(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getSiloImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getSilos().add(main.silo);
				main.mScene.attachChild(main.silo);
			
			
			}else if(main.placebuilding.currentBuilding=="Food Market"){
				main.foodmarket = new FoodMarket(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getFoodMarketImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getFoodMarkets().add(main.foodmarket);
				main.mScene.attachChild(main.foodmarket);
				
			
			}else if(main.placebuilding.currentBuilding=="Theatre"){
				if(main.getController().Marble >= ConstantBuildings.COSTTHEATREMARBLE && main.getController().getGold()>= ConstantBuildings.COSTTHEATRECOIN && HouseLevel >=2){
				main.setTheatre(new Theatre(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getTheatreImage(), this.getVertexBufferObjectManager(),main,  false));
				main.getTheatres().add(main.getTheatre());
				main.mScene.attachChild(main.getTheatre());
				main.getController().updateGold(-ConstantBuildings.COSTTHEATRECOIN);
				RemoveResources("Marble",ConstantBuildings.COSTTHEATREMARBLE);

				}
				else if (HouseLevel <2){
					main.Message("You need a house with a level 2 upgrade in order to build a Theatre",Color.RED);	
				}
				else{
					main.Message("You cannot afford the Theatre",Color.RED);	
					
				}
			
			}
			else if(main.placebuilding.currentBuilding=="Stone Cutter"){
				if(HouseLevel>=2 && main.getController().getGold() >= ConstantBuildings.COSTSTONECUTTERCOIN){
				main.stonecutter = new StoneCutter(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getStoneCutterImage(), this.getVertexBufferObjectManager(),main, false);
				main.getStoneCutters().add(main.stonecutter);
				main.mScene.attachChild(main.stonecutter);
				main.getController().updateGold(-ConstantBuildings.COSTSTONECUTTERCOIN);
				;
				main.stonecutter.createNpc();
			}
			else if (HouseLevel <2){
				main.Message("You need a house with a level 2 upgrade in order to build a Stone Cutter",Color.RED);	
			}
			else if (main.getController().getGold() < ConstantBuildings.COSTSTONECUTTERCOIN){
				main.Message("You cannot afford a Stone Cutter building", Color.RED);
				}
			}
			else if(main.placebuilding.currentBuilding=="Stock" ){
				if(main.getController().getGold() >= ConstantBuildings.COSTSTOCKCOIN){
					main.stock = new Stock(main.placebuilding.getX(), main.placebuilding.getY()-32, main.getImages().getStockplaceImage(), this.getVertexBufferObjectManager(),main, false);
					main.getStocks().add(main.stock);
					main.stock.globalKind = main.tempGlobalKind;
					main.mScene.attachChild(main.stock);
					main.getController().updateGold(-ConstantBuildings.COSTSTOCKCOIN);
					}
				else
					main.Message("You cannot afford a Stock\nYou need "+ ConstantBuildings.COSTSTOCKCOIN + " gold", Color.RED);
						
					
			}else if(main.placebuilding.currentBuilding=="Wood Cutter"){
				if(HouseLevel >=2 && main.getController().getGold() >= ConstantBuildings.COSTWOODCUTTERCOIN){
				main.woodcutter = new WoodCutter(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getWoodCutterImage(), this.getVertexBufferObjectManager(),main, false);
				main.getWoodCutters().add(main.woodcutter);
				main.mScene.attachChild(main.woodcutter);
				main.getController().updateGold(-ConstantBuildings.COSTWOODCUTTERCOIN);
				}
			else if (HouseLevel <3){
				main.Message("You need a house with a level 3 upgrade in order to build a Wood Cutter",Color.RED);	
					
			}
			else
				main.Message("You cannot afford a Wood cutter building\nYou need "+ ConstantBuildings.COSTWOODCUTTERCOIN + " gold", Color.RED);
				}
			else if(main.placebuilding.currentBuilding=="Hunters Lodge" ){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTHUNTERSLODGECOIN && main.getController().Wood >= ConstantBuildings.COSTHUNTERSLODGEWOOD){
				main.huntersLodge = new HuntersLodge(main.placebuilding.getX(), main.placebuilding.getY(),main.getImages().getHuntersLodgeImage(), this.getVertexBufferObjectManager(),main, false);
				main.huntersLodges.add(main.huntersLodge);
				main.mScene.attachChild(main.huntersLodge);
				main.getController().updateGold(-ConstantBuildings.COSTHUNTERSLODGECOIN);
				RemoveResources("Wood",ConstantBuildings.COSTHUNTERSLODGEWOOD);
				}
				else if (HouseLevel <3){
					main.Message("You need a house with a level 3 upgrade in order to build a Hunters Lodge",Color.RED);	
					}
				else{
					main.Message("You cannot afford a Hunters lodge.\nYou need "+ ConstantBuildings.COSTHUNTERSLODGECOIN + " gold, and " + ConstantBuildings.COSTHUNTERSLODGEWOOD + " wood" , Color.RED);
				}
			}else if(main.placebuilding.currentBuilding=="Skinner" ){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTSKINNERCOIN && main.getController().Wood >= ConstantBuildings.COSTSKINNERWOOD){
				main.setSkinner(new Skinner(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getSkinnerImage(), this.getVertexBufferObjectManager(),main, false));
				main.getSkinners().add(main.getSkinner());
				main.mScene.attachChild(main.getSkinner());
				main.getController().updateGold(-ConstantBuildings.COSTSKINNERCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTSKINNERWOOD);
				}
				else if (HouseLevel <3){
					main.Message("You need a house with a level 3 upgrade in order to build a Skinner",Color.RED);	
					}
				else{
					main.Message("You cannot afford a Skinner.\nYou need "+ ConstantBuildings.COSTSKINNERCOIN + " gold, and " + ConstantBuildings.COSTSKINNERWOOD + " wood" , Color.RED);
				}
			}else if(main.placebuilding.currentBuilding=="Butcher" ){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTSKINNERCOIN && main.getController().Wood >= ConstantBuildings.COSTSKINNERWOOD){
				main.butcher = new Butcher(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getButcherImage(), this.getVertexBufferObjectManager(),main, false);
				main.getButchers().add(main.butcher);
				main.mScene.attachChild(main.butcher);
				main.getController().updateGold(-ConstantBuildings.COSTBUTCHERCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTBUTCHERWOOD);

				}
				else if (HouseLevel <3){
					main.Message("You need a house with a level 3 upgrade in order to build a butcher",Color.RED);	
					}
				else{
					main.Message("You cannot afford a butcher.\nYou need "+ ConstantBuildings.COSTBUTCHERCOIN + " gold, and " + ConstantBuildings.COSTBUTCHERWOOD + " wood" , Color.RED);
				}
			}
			else if(main.placebuilding.currentBuilding=="Fishing Hut" ){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTHUNTERSLODGECOIN && main.getController().Wood >= ConstantBuildings.COSTFISHINGHUTWOOD){
				main.fishinghut = new FishingHut(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getFishingHutButtonImage(), this.getVertexBufferObjectManager(),main, false);
				main.getFishingHuts().add(main.fishinghut);
				main.mScene.attachChild(main.fishinghut);
				main.getController().updateGold(ConstantBuildings.COSTFISHINGHUTWOOD);
				RemoveResources("Wood",ConstantBuildings.COSTFISHINGHUTWOOD);
				}
				else if (HouseLevel <3){
					main.Message("You need a house with a level 3 upgrade in order to build a Fishing hut",Color.RED);	
					}
				else{
					main.Message("You cannot afford a Fishing hut.\nYou need "+ ConstantBuildings.COSTFISHINGHUTCOIN + " gold, and " + ConstantBuildings.COSTFISHINGHUTWOOD + " wood" , Color.RED);
				}}
			else if(main.placebuilding.currentBuilding=="Barrack" ){
				if(HouseLevel >=4 && main.getController().getGold() >= ConstantBuildings.COSTBARRACKCOIN && main.getController().Wood >= ConstantBuildings.COSTBARRACKWOOD && main.getController().Marble >= ConstantBuildings.COSTBARRACKMARBLE && main.getController().Brick >= ConstantBuildings.COSTBARRACKBRICK){
				main.barrack = new Barrack(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getBarrackImage(), this.getVertexBufferObjectManager(),main, false);
				main.getBarracks().add(main.barrack);
				main.mScene.attachChild(main.barrack);
				main.getController().updateGold(ConstantBuildings.COSTBARRACKCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTBARRACKWOOD);
				RemoveResources("Brick",ConstantBuildings.COSTBARRACKBRICK);
				RemoveResources("Marble",ConstantBuildings.COSTBARRACKMARBLE);
				}
				else if (HouseLevel <4){
					main.Message("You need a house with a level 4 upgrade in order to build a Barrack",Color.RED);	
					}
				else{
					main.Message("You cannot afford a Fishing hut.\nYou need "+ ConstantBuildings.COSTBARRACKCOIN + " gold, and " + ConstantBuildings.COSTBARRACKWOOD + " wood"+ ConstantBuildings.COSTBARRACKBRICK + " bricks" , Color.RED);
				}}
			else if(main.placebuilding.currentBuilding=="Clay Mine" ){
				if(HouseLevel >=4 && main.getController().getGold() >= ConstantBuildings.COSTCLAYMINECOIN && main.getController().Wood >= ConstantBuildings.COSTCLAYMINEWOOD && main.getController().Marble >= ConstantBuildings.COSTCLAYMINEMARBLE ){
				main.clayMine = new MineDepositClay(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getClayMineImage(), this.getVertexBufferObjectManager(),main, false);
				main.getClayMines().add(main.clayMine);
				main.mScene.attachChild(main.clayMine);
				main.getController().updateGold(ConstantBuildings.COSTCLAYMINECOIN);
				RemoveResources("Wood",ConstantBuildings.COSTCLAYMINEWOOD);
				RemoveResources("Marble",ConstantBuildings.COSTCLAYMINEMARBLE);
				}
				else if (HouseLevel <4){
					main.Message("You need a house with a level 4 upgrade in order to build a clay mine",Color.RED);	
					}
				else{
					main.Message("You cannot afford a clay mine.\nYou need "+ ConstantBuildings.COSTCLAYMINECOIN + " gold, and " + ConstantBuildings.COSTCLAYMINEWOOD + " wood" , Color.RED);
				}}
			else if(main.placebuilding.currentBuilding=="Brick Foundry" ){
				if(HouseLevel >=4 && main.getController().getGold() >= ConstantBuildings.COSTBRICKFOUNDRYCOIN && main.getController().Wood >= ConstantBuildings.COSTBRICKFOUNDRYWOOD && main.getController().Marble >= ConstantBuildings.COSTBRICKFOUNDRYMARBLE ){
				main.brickFoundry = new BrickFoundry(main.placebuilding.getX(), main.placebuilding.getY(), main.getImages().getBrickFoundryImage(), this.getVertexBufferObjectManager(),main, false);
				main.getBrickFoundrys().add(main.brickFoundry);
				main.mScene.attachChild(main.brickFoundry);
				main.getController().updateGold(ConstantBuildings.COSTBRICKFOUNDRYCOIN);
				RemoveResources("Wood",ConstantBuildings.COSTBRICKFOUNDRYWOOD);
				RemoveResources("Marble",ConstantBuildings.COSTBRICKFOUNDRYMARBLE);
				}
				else if (HouseLevel <4){
					main.Message("You need a house with a level 4 upgrade in order to build a brick foundry",Color.RED);	
					}
				else{
					main.Message("You cannot afford a brick foundry.\nYou need "+ ConstantBuildings.COSTBRICKFOUNDRYCOIN + " gold, and " + ConstantBuildings.COSTBRICKFOUNDRYWOOD + " wood" , Color.RED);
				}}
			
		}}
		return true;
	
	}
	public void RemoveResources(String kind, int ammount) {
	for(int i = 0;i<main.getStocks().size();i++){
			if(kind =="Marble"){
				if(main.getStocks().get(i).Marble >= ammount){
					main.getStocks().get(i).Marble -= ammount;
					main.getStocks().get(i).removeResource(kind, ammount);
					main.getController().Marble -= ammount;
					break;
				}}
			if(kind =="Wood"){
				if(main.getStocks().get(i).Wood >= ammount){
					main.getStocks().get(i).Wood -= ammount;
					Debug.e("GOGOGO");
					main.getController().Wood -= ammount;
					main.getStocks().get(i).removeResource(kind, ammount);
					break;
				}}
			if(kind =="Brick"){
				if(main.getStocks().get(i).Brick >= ammount){
					main.getStocks().get(i).Brick -= ammount;
					main.getStocks().get(i).removeResource(kind, ammount);
					main.getController().Brick -= ammount;
					break;
				}}
			if(kind =="Bronze"){
				if(main.getStocks().get(i).Bronze >= ammount){
					main.getStocks().get(i).Bronze -= ammount;
					main.getStocks().get(i).removeResource(kind, ammount);
					main.getController().Bronze -= ammount;
					break;
				}}

			if(kind =="Skin"){
				if(main.getStocks().get(i).Skin >= ammount){
					main.getStocks().get(i).Skin -= ammount;
					main.getStocks().get(i).removeResource(kind, ammount);
					main.getController().Skin -= ammount;
					break;
				}}
			if(kind =="Armor"){
				if(main.getStocks().get(i).Armor >= ammount){
					main.getStocks().get(i).Armor -= ammount;
					main.getStocks().get(i).removeResource(kind, ammount);
					main.getController().Armor -= ammount;
					break;
				}}
			if(kind =="Clay"){
				if(main.getStocks().get(i).Clay >= ammount){
					main.getStocks().get(i).Clay -= ammount;
					main.getStocks().get(i).removeResource(kind, ammount);
					main.getController().Clay-= ammount;
					break;
				}}

	
		}}

	public void RemoveResources(String kind, int ammount, Stock stock) {
			if(kind =="Marble"){
					if(stock.Marble >= ammount){
						stock.Marble -= ammount;
						stock.removeResource(kind, ammount);
						main.getController().Marble -= ammount;
					}}
				if(kind =="Wood"){
					if(stock.Wood >= ammount){
						stock.Wood -= ammount;
						main.getController().Wood -= ammount;
						stock.removeResource(kind, ammount);
					}}
				if(kind =="Brick"){
					if(stock.Brick >= ammount){
						stock.Brick -= ammount;
						stock.removeResource(kind, ammount);
						main.getController().Brick -= ammount;
					}}
				if(kind =="Bronze"){
					if(stock.Bronze >= ammount){
						stock.Bronze -= ammount;
						stock.removeResource(kind, ammount);
						main.getController().Bronze -= ammount;
					}}

				if(kind =="Skin"){
					if(stock.Skin >= ammount){
						stock.Skin -= ammount;
						stock.removeResource(kind, ammount);
						main.getController().Skin -= ammount;
					}}
				if(kind =="Armor"){
					if(stock.Armor >= ammount){
						stock.Armor -= ammount;
						stock.removeResource(kind, ammount);
						main.getController().Armor -= ammount;
					}}
				if(kind =="Clay"){
					if(stock.Clay >= ammount){
						stock.Clay -= ammount;
						stock.removeResource(kind, ammount);
						main.getController().Clay-= ammount;
					}}
}}