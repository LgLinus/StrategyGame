package com.ligr.strategygame.Buttons;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.buildings.Armory;
import com.ligr.strategygame.buildings.Barrack;
import com.ligr.strategygame.buildings.BrickFoundry;
import com.ligr.strategygame.buildings.BronzeMine;
import com.ligr.strategygame.buildings.Butcher;
import com.ligr.strategygame.buildings.Farm;
import com.ligr.strategygame.buildings.FishingHut;
import com.ligr.strategygame.buildings.FoodMarket;
import com.ligr.strategygame.buildings.Well;
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
			if(main.placeBuilding.collides()){
				Debug.e("Collision");
				return false;}
			HouseLevel = main.getController().getHouseLevel();
			if(main.placeBuilding.gotSpace()){
			if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEHOUSE){
				if(main.getController().getGold()>=ConstantBuildings.COSTHOUSECOIN){
					
					main.setHouseexample(new House(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getHouseImage(), this.getVertexBufferObjectManager(),main, false));
					main.getHouses().add(main.getHouseexample());
					main.mScene.attachChild(main.getHouseexample());
					main.getController().updateGold(-100);
					
					main.getHouseexample().createChilds();
				}
				else{
					main.boolplacebuilding=false;
				}
			
				
			}
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEROAD){
				main.road = new Road(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getRoadimage(), this.getVertexBufferObjectManager(), main, mFlippedHorizontal);
				main.getRoads().add(main.road);
				main.mScene.attachChild(main.road);
			
			}
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEWELL){
				main.fountain = new Well(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getFountainImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getFountains().add(main.fountain);
				main.mScene.attachChild(main.fountain);
				
			}
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEFARM){
				main.farm = new Farm(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getFarmImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getFarms().add(main.farm);
				main.mScene.attachChild(main.farm);
				main.farm.animate(1000);
			
			
			}else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLESILO){
				main.silo = new Silo(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getSiloImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getSilos().add(main.silo);
				main.mScene.attachChild(main.silo);
			
			
			}else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEFOODMARKET){
				main.foodmarket = new FoodMarket(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getFoodMarketImage(), this.getVertexBufferObjectManager(),main,  false);
				main.getFoodMarkets().add(main.foodmarket);
				main.mScene.attachChild(main.foodmarket);
				
			
			}else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLETHEATRE){
				if(main.getController().Marble >= ConstantBuildings.COSTTHEATREMARBLE && main.getController().getGold()>= ConstantBuildings.COSTTHEATRECOIN && HouseLevel >=2){
				main.setTheatre(new Theatre(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getTheatreImage(), this.getVertexBufferObjectManager(),main,  false));
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLESTONECUTTER){
				if(HouseLevel>=2 && main.getController().getGold() >= ConstantBuildings.COSTSTONECUTTERCOIN){
				main.stonecutter = new StoneCutter(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getStoneCutterImage(), this.getVertexBufferObjectManager(),main, false);
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLESTOCK){
				if(main.getController().getGold() >= ConstantBuildings.COSTSTOCKCOIN){
					main.stock = new Stock(main.placeBuilding.getX(), main.placeBuilding.getY()-32, main.getImages().getStockplaceImage(), this.getVertexBufferObjectManager(),main, false);
					main.getStocks().add(main.stock);
					main.stock.globalKind = main.tempGlobalKind;
					main.mScene.attachChild(main.stock);
					main.getController().updateGold(-ConstantBuildings.COSTSTOCKCOIN);
					}
				else
					main.Message("You cannot afford a Stock\nYou need "+ ConstantBuildings.COSTSTOCKCOIN + " gold", Color.RED);
						
					
			}else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEWOODCUTTER){
				if(HouseLevel >=2 && main.getController().getGold() >= ConstantBuildings.COSTWOODCUTTERCOIN){
				main.woodcutter = new WoodCutter(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getWoodCutterImage(), this.getVertexBufferObjectManager(),main, false);
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEHUNTERSLODGE ){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTHUNTERSLODGECOIN && main.getController().Wood >= ConstantBuildings.COSTHUNTERSLODGEWOOD){
				main.huntersLodge = new HuntersLodge(main.placeBuilding.getX(), main.placeBuilding.getY(),main.getImages().getHuntersLodgeImage(), this.getVertexBufferObjectManager(),main, false);
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
			}else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLESKINNER ){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTSKINNERCOIN && main.getController().Wood >= ConstantBuildings.COSTSKINNERWOOD){
				main.setSkinner(new Skinner(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getSkinnerImage(), this.getVertexBufferObjectManager(),main, false));
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
			}else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEBUTCHER){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTSKINNERCOIN && main.getController().Wood >= ConstantBuildings.COSTSKINNERWOOD){
				main.butcher = new Butcher(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getButcherImage(), this.getVertexBufferObjectManager(),main, false);
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEFISHINGHUT){
				if(HouseLevel >=3 && main.getController().getGold() >= ConstantBuildings.COSTHUNTERSLODGECOIN && main.getController().Wood >= ConstantBuildings.COSTFISHINGHUTWOOD){
				main.fishinghut = new FishingHut(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getFishingHutButtonImage(), this.getVertexBufferObjectManager(),main, false);
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEBARRACK){
				if(HouseLevel >=4 && main.getController().getGold() >= ConstantBuildings.COSTBARRACKCOIN && main.getController().Wood >= ConstantBuildings.COSTBARRACKWOOD && main.getController().Marble >= ConstantBuildings.COSTBARRACKMARBLE && main.getController().Brick >= ConstantBuildings.COSTBARRACKBRICK){
				main.barrack = new Barrack(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getBarrackImage(), this.getVertexBufferObjectManager(),main, false);
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEMINEDEPOSITCLAY){
				if(HouseLevel >=4 && main.getController().getGold() >= ConstantBuildings.COSTCLAYMINECOIN && main.getController().Wood >= ConstantBuildings.COSTCLAYMINEWOOD && main.getController().Marble >= ConstantBuildings.COSTCLAYMINEMARBLE ){
				main.clayMine = new MineDepositClay(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getClayMineImage(), this.getVertexBufferObjectManager(),main, false);
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEBRICKFOUNDRY ){
				if(HouseLevel >=4 && main.getController().getGold() >= ConstantBuildings.COSTBRICKFOUNDRYCOIN && main.getController().Wood >= ConstantBuildings.COSTBRICKFOUNDRYWOOD && main.getController().Marble >= ConstantBuildings.COSTBRICKFOUNDRYMARBLE ){
				main.brickFoundry = new BrickFoundry(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getBrickFoundryImage(), this.getVertexBufferObjectManager(),main, false);
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
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEBRONZEMINE ){
				if(HouseLevel >=ConstantBuildings.HOUSEREQBRONZEMINE && main.getController().getGold() >= ConstantBuildings.COSTBRONZEMINECOIN && main.getController().Wood >= ConstantBuildings.COSTBRONZEMINEWOOD && main.getController().Marble >= ConstantBuildings.COSTBRONZEMINEMARBLE ){
				main.bronzeMine = new BronzeMine(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getBronzeMineImage(), this.getVertexBufferObjectManager(),main, false);
				main.getBronzeMines().add(main.bronzeMine);
				main.mScene.attachChild(main.bronzeMine);
				main.getController().updateGold(ConstantBuildings.COSTBRONZEMINECOIN);
				RemoveResources("Wood",ConstantBuildings.COSTBRONZEMINEWOOD);
				RemoveResources("Marble",ConstantBuildings.COSTBRONZEMINEMARBLE);
				RemoveResources("Brick",ConstantBuildings.COSTBRONZEMINEBRICK);
				}
				else if (HouseLevel <ConstantBuildings.HOUSEREQBRONZEMINE){
					main.Message("You need a house with a level 5 upgrade in order to build a bronze mine",Color.RED);	
					}
				else{
					main.Message("You cannot afford a bronze mine.", Color.RED);
				}}
			else if(main.placeBuilding.currentBuilding==ConstantBuildings.TITLEARMORY){
				if(HouseLevel >=ConstantBuildings.HOUSEREQARMORY && main.getController().getGold() >= ConstantBuildings.COSTARMORYCOIN  && main.getController().Marble >= ConstantBuildings.COSTARMORYMARBLE ){
				main.armory = new Armory(main.placeBuilding.getX(), main.placeBuilding.getY(), main.getImages().getArmoryImage(), this.getVertexBufferObjectManager(),main, false);
				main.getArmories().add(main.armory);
				main.mScene.attachChild(main.armory);
				main.getController().updateGold(ConstantBuildings.COSTARMORYCOIN);
				RemoveResources("Marble",ConstantBuildings.COSTARMORYMARBLE);
				RemoveResources("Brick",ConstantBuildings.COSTARMORYBRICK);
				}
				else if (HouseLevel <ConstantBuildings.HOUSEREQARMORY){
					main.Message("You need a house with a level 5 upgrade in order to build a bronze mine",Color.RED);	
					}
				else{
					main.Message("You cannot afford a bronze mine.", Color.RED);
				}}
			
		}}
		return true;
	
	}
	public void RemoveResources(String kind, int ammount) {
			main.getController().removeResources(kind,ammount);
		}


	public void RemoveResources(String kind, int ammount, Stock stock) {
		main.getController().removeResources(kind, ammount, stock);
	}
	}