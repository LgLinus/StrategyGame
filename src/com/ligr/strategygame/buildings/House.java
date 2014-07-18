package com.ligr.strategygame.buildings;

import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.UpgradeArrowHouse;
import com.ligr.strategygame.constants.ConstantBuildings;


import text.HouseDescriptionText;

public class House extends AnimatedSpriteObject {
	UpgradeArrowHouse arrow;
	private int Inhabitants, totalInhabitants;
	private boolean pressedDown = false;
	private int monthlyFood = 0;
//	private String needs = ""; // Text for whatever the House needs to upgrade.
//	private String satisfied = ""; // Whatever cravings that are satisfied.
//	private String info = ""; // Info of the house income, inhabitants etc.

	private int ID = 0;
	private int monthsWithoutFood = 0;

	public House(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main,boolean load) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager,main);
		// TODO Auto-generated constructor stub public House(float pX, float pY,
		// ITiledTextureRegion pTextureRegion,
		yBaseStart = 23;
		this.stopAnimation(0);
		// If the unemployment is below 60% people will imigrate or if there are
		// less than 100 unemployed workers
		Inhabitants = 8;
		totalInhabitants = Inhabitants;
		updateInhabitantsPreset();
		if (!load) {
//			if (main.getController().getMaxWorkers() != 0 && main.getController().getWorkers() != 0) {
//				if (main.getController().getWorkers() / main.getController().getMaxWorkers() >= .60
//						|| main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
//					Inhabitants = 8;
//					totalInhabitants = Inhabitants;
//					updateInhabitantsPreset();
//				} else {
//					main
//							.messagePopUp(
//									"The unemployment is too high, people won't come to your city!",
//									Color.WHITE);
//					Inhabitants = 0;
				}
//			} else if (main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
//				Inhabitants = 8;
//				updateInhabitantsPreset();
//			} else {
//				main
//						.messagePopUp(
//								"The unemployment is too high, people won't come to your city!",
//								Color.WHITE);
//				Inhabitants = 0;
//			}
//		}
//		createPolygon();
	}

	public void save() {
		main.getController().getDataBase().add(name(), this.getX(), this.getY(), this.id,
				this.HouseLevel);
		Debug.e(String.valueOf(id));
	}

	private void createPolygon() {
		float[] xVertice = {0,47,96,47};
		float[] yVertice = {23,0,23,47};
		polygon = new Polygon(this.mX, this.mY, xVertice, yVertice, this.getVertexBufferObjectManager());
		polygon.setColor(0.5f, 1f, 0.3f);
		MainActivity.mScene.attachChild(polygon);
		Polygon polygon2 = new Polygon(this.mX, this.mY, xVertice, yVertice, this.getVertexBufferObjectManager());
		polygon2.setColor(0.5f, 1f, 0.3f);
		MainActivity.mScene.attachChild(polygon2);
		if(polygon.collidesWith(polygon2))
			Debug.e("THEY COLLIDED");
	}

	private String name() {
		return "House";
	}

	// Some booleans to keep track on what kind of resources the houses have
	// accses too.
	public boolean GotFood = false;
	public boolean GotWater = false;
	public boolean GotWood = false;
	public boolean GotSkin = false;
	public boolean GotEntertainment = false;
	public boolean GotFood2 = false;
	public boolean GotTheatre = false;
	public int AmmountofEntertainment = 0;
	private boolean GotWheat = false;
	private int WheatAmmount = 0;
	private int maxWheatAmmount = 40;
	public int Range = 512;
	public int HouseLevel = 1;
	private Sprite gotFoodSymbol;

	public void createChilds() {
		ID = main.getHouses().size();
		arrow = new UpgradeArrowHouse(this.mX + this.getWidth() / 2
				- main.getImages().getUpgradeArrowImage().getWidth() / 2, this.mY
				+ this.getHeight() / 2
				- main.getImages().getUpgradeArrowImage().getHeight() / 2 + 8,
				main.getImages().getUpgradeArrowImage(),
				this.getVertexBufferObjectManager(), this,main);
		arrow.setAlpha(0);
		main.mScene.attachChild(arrow);
		gotFoodSymbol = new Sprite(this.mX + this.getWidth() / 2
				- main.getImages().getGotFoodSymbolImage().getWidth() / 2, this.mY + 12,
				main.getImages().getGotFoodSymbolImage(),
				this.getVertexBufferObjectManager());
		main.mScene.attachChild(gotFoodSymbol);
	}

	// Method that updates the ammount of inhabitants and workers
	private void updateInhabitants() {
		Inhabitants = ConstantBuildings.HOUSEINHABITANTSGAINLVL[HouseLevel];
		totalInhabitants += Inhabitants;
		main.getController().updateInhabitants(Inhabitants, HouseLevel,
				totalInhabitants);
	}

	private void updateInhabitantsPreset() {
		totalInhabitants += Inhabitants;
		main.getController().updateInhabitants(Inhabitants, HouseLevel,
				totalInhabitants);
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if (pSceneTouchEvent.isActionDown()) {
			pressedDown = true;
		}
		if (pSceneTouchEvent.isActionUp()) {
			if (arrow.getAlpha() != 1 && main.removeBuildings != true) {
				if (main.boolplacebuilding == false && pressedDown
						&& main.removeBuildings == false) {
					pressedDown = false;
					main.createHouseDescriptionHUD(this);

				}
			} else if (main.removeBuildings == true
					&& main.boolplacebuilding == false && pressedDown) {
				main.MessagePopUpChoice(
						"Are you sure you want to remove the building?",
						Color.WHITE, this, "Remove", main.inGameHUD);
			}

			// IF we can upgrade
			else if (main.boolplacebuilding == false && pressedDown
					&& main.removeBuildings == false
					&& main.removeBuildings != true) {
				pressedDown = false;
				Debug.e("Can upgrade");
				String cost = "";
				if (HouseLevel == 3)
					cost = "2 skin, in order to upgrade the house";
				else if (HouseLevel == 4)
					cost = "3 skin and 5 marble in order to upgrade the house";
				main.MessagePopUpChoice(
						"Upgrading the house costs 2 skins. Are you sure?",
						Color.WHITE, this, "Upgdradehouselvl4",
						main.inGameHUD);
				Debug.e("After message");
				// main.buildingDescriptionHUD.setAlpha(1);
				// main.houseInfo = new
				// HouseDescriptionText(0,0,main.gameFont,this.info,400,this.getVertexBufferObjectManager(),
				// this);
				// main.houseNeeds = new
				// HouseDescriptionText(0,0,main.gameFont,this.needs,400,this.getVertexBufferObjectManager(),
				// this);
				// main.houseSatisfied = new
				// HouseDescriptionText(0,0,main.gameFont,this.satisfied,400,this.getVertexBufferObjectManager(),
				// this);
				//
				main.upgradeButton.parentHouse = this;
				main.upgradeButton.level = HouseLevel;
				main.upgradeButton.type = "House";
				/*
				 * main.buildingDescriptionTitleString =
				 * "Upgrade house to Level "+(HouseLevel+1);
				 * main.mScene.
				 * registerTouchArea(main.buildingDescriptionCancel);
				 * main.buildingDescriptionCancel.setAlpha(1);
				 * main.buildingDescriptionTitle.setAlpha(1);
				 * main.buildingDescriptionDetail.setAlpha(1);
				 * main.upgradeButton.setAlpha(1);
				 * main.buildingDescriptionTitle
				 * .setText(main.buildingDescriptionTitleString);
				 * main.buildingDescriptionDetailString =
				 * "It will cost you " + cost;
				 * main.buildingDescriptionDetail
				 * .setText(main.buildingDescriptionDetailString);;
				 */

			}

		}
		return true;
	}

	public void removeEntity() {
		this.arrow.detachSelf();

		main.mScene.unregisterTouchArea(this);
		this.gotFoodSymbol.detachSelf();
		main.getHouses().remove(this);
		main.removeEntity(this);
		main.getController().updateInhabitants(-this.totalInhabitants, this.HouseLevel,
				0);
	}

	private void drawRange() {
		// TODO Auto-generated method stub
		/*
		 * if(drawRange == true &&
		 * main.buildingDescriptionCancel.getAlpha() == 0) drawRange =
		 * false;
		 */

		/*
		 * if(drawRange == false){ Debug.e("Before");
		 * main.mScene.detachChild(circle); Debug.e("After"); circle =
		 * null;
		 * 
		 * }
		 */
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if (HouseLevel == 3) {
			if (GotFood && GotWater && GotTheatre && main.getController().Skin >= 2) {
				arrow.setAlpha(1);
			}
		} else if (arrow.getAlpha() == 1)
			arrow.setAlpha(0);

	}

	/**
	 * updates each house every month
	 * 
	 * @param house
	 */
	public void Update(House house) {

		monthlyFood = totalInhabitants * 2;
		// Checks for nearby foodmarkets, and sets maxwheatammount to
		// 12*totalinhabitants/4
		for (int i = 0; i < main.getFoodMarkets().size(); i++) {

			double distance = main.calculateDistance(
					main.getFoodMarkets().get(i), house);
			if (distance < 512
					&& main.getFoodMarkets().get(i).FoodAmmount > 0) {
				if (WheatAmmount > 0) {
					maxWheatAmmount = 12 * monthlyFood;
					house.GotWheat = true;
					house.GotFood = true;
					if (monthsWithoutFood > 0)
						monthsWithoutFood = 0;
					else
						monthsWithoutFood--;
				}
				if (main.getSilos().size() > i
						&& main.getSilos().get(i) != null
						&& main.getSilos().get(i).FoodAmmount >= monthlyFood * 6
						&& WheatAmmount <= maxWheatAmmount / 2) {
					WheatAmmount += monthlyFood * 6;
					main.getSilos().get(i).FoodAmmount -= monthlyFood * 6;
					house.GotWheat = true;
					house.GotFood = true;
					if (monthsWithoutFood > 0)
						monthsWithoutFood = 0;
					else
						monthsWithoutFood--;
				}
			}

		}
		if (WheatAmmount >= monthlyFood) {
			WheatAmmount -= monthlyFood;
			maxWheatAmmount = 12 * monthlyFood;
			house.GotWheat = true;
			house.GotFood = true;
			monthsWithoutFood = 0;
		} else {
			house.GotFood = false;
			house.GotWheat = false;
			monthsWithoutFood++;
			if (monthsWithoutFood >= 4)
				deGradeLevel();
		}
		for (int i = 0; i < main.getTheatres().size(); i++) {
			double distance = main.calculateDistance(
					main.getTheatres().get(i), house);
			if (distance < 512) {
				house.GotTheatre = true;
				break;
			} else {
				house.GotTheatre = false;
			}
		}
		if (main.getFountains().size() == 0)
			GotWater = false;
		if (main.getTheatres().size() == 0)
			GotTheatre = false;
		for (int i = 0; i < main.getFountains().size(); i++) {
			double distance = main.calculateDistance(
					main.getFountains().get(i), house);
			if (distance < 512) {
				GotWater = true;
				break;
			} else
				GotWater = false;
		}
		CheckForLevels(house);

		if (GotFood) {
			gotFoodSymbol.setAlpha(0);
		} else
			gotFoodSymbol.setAlpha(1);
	}

	/**
	 * Turns the house down a level
	 */
	private void deGradeLevel() {
		if (HouseLevel != 1) {
			Inhabitants = -ConstantBuildings.HOUSEINHABITANTSGAINLVL[HouseLevel - 1];
			updateInhabitantsPreset();
			HouseLevel--;
			if (HouseLevel != 1)
				animate(new long[] { 50l, 50l, 50l, 50l },
						ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel - 1],
						ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel - 1],
						true);
			else
				stopAnimation(0);
		}
	}

	private void CheckForLevels(House house) {
		// TODO Auto-generated method stub
		if (house.HouseLevel == 1 && house.GotWheat && house.GotWater) {
			if (main.getController().getMaxWorkers() != 0 && main.getController().getWorkers() != 0) {
				if (main.getController().getWorkers() / main.getController().getMaxWorkers() >= .60
						|| main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
					updateInhabitants();
					house.animate(
							new long[] { 50L, 50L, 50L, 50L },
							ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
							ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
							true);
					house.HouseLevel = 2;
				} else {
//					main
//							.messagePopUp(
//									"The unenployment is too high, your houses won't upgrade!",
//									Color.WHITE);
					Inhabitants = 0;
				}
			} else if (main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
				updateInhabitants();
				house.animate(new long[] { 50L, 50L, 50L, 50L },
						ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
						ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
						true);
				house.HouseLevel = 2;
			} else {
//				main
//						.messagePopUp(
//								"The unenployment is too high, your houses won't upgrade!",
//								Color.WHITE);
				Inhabitants = 0;
			}
		} else if (house.HouseLevel == 2 && house.GotWheat && house.GotWater
				&& house.GotTheatre) {
			if (main.getController().getMaxWorkers() != 0 && main.getController().getWorkers() != 0) {
				if (main.getController().getWorkers() / main.getController().getMaxWorkers() >= .60
						|| main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
					updateInhabitants();
					house.animate(
							new long[] { 50L, 50L, 50L, 50L },
							ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
							ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
							true);
					house.HouseLevel = 3;
				} else {
//					main
//							.messagePopUp(
//									"The unenployment is too high, your houses won't upgrade!",
//									Color.WHITE);
					Inhabitants = 0;
				}
			} else if (main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
				updateInhabitants();
				house.animate(new long[] { 50L, 50L, 50L, 50L },
						ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
						ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
						true);
				house.HouseLevel = 3;
			} else {
//				main
//						.messagePopUp(
//								"The unenployment is too high, your houses won't upgrade!",
//								Color.WHITE);
				Inhabitants = 0;
			}
		}
	}

	public boolean checkFood() {
		// TODO Auto-generated method stub
		if (GotFood && monthsWithoutFood <= 0)
			return true;
		else
			return false;
	}

	public void upgradetolvl4() {
		// TODO Auto-generated method stub
		if (main.getController().getMaxWorkers() != 0 && main.getController().getWorkers() != 0) {
			if (main.getController().getWorkers() / main.getController().getMaxWorkers() >= .60
					|| main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
				updateInhabitants();
				this.animate(new long[] { 50L, 50L, 50L, 50L },
						ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
						ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
						true);
				main.RemoveResources("Skin", 2);
				arrow.setAlpha(0);
				ArrowUpgrade();
				this.HouseLevel = 4;
			} else {
				main
						.messagePopUp(
								"The unenployment is too high, your houses won't upgrade!",
								Color.WHITE);
				Inhabitants = 0;
			}
		} else if (main.getController().getWorkers() - main.getController().getMaxWorkers() >= -100) {
			updateInhabitants();
			arrow.setAlpha(0);

			this.animate(new long[] { 50L, 50L, 50L, 50L },
					ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
					ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel], true);
			main.buybutton.RemoveResources("Skin", 2);

			ArrowUpgrade();
			this.HouseLevel = 4;
		} else {
			main.messagePopUp(
					"The unenployment is too high, your houses won't upgrade!",
					Color.WHITE);
			Inhabitants = 0;
		}
	}

	private void ArrowUpgrade() {
		// TODO Auto-generated method stub
		main.mScene.unregisterTouchArea(arrow);
		arrow.setAlpha(0);

	}

	public void removeHouse() {
		// arrow.remove = true;
		// gotFoodSymbol.remove = true;
		remove = true;
	}

	public void setLevel(int houselevel2) {
		HouseLevel = houselevel2;
		if (HouseLevel > 1) {
			this.animate(new long[] { 50L, 50L, 50L, 50L },
					ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel - 1],
					ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel - 1],
					true);
		} else
			this.stopAnimation(0);
	}

	/**
	 * Return the amount of inhabitants of the house if the level matches
	 * @param i level 
	 * @return inhabitans
	 */
	public int getInhabitants(int i) {
		if(i == -1 || i == 0)
		return totalInhabitants;
		else if(i==HouseLevel)
			return totalInhabitants;
		else
			return 0;
	}
	
	public int getHouseLevel(){
		return HouseLevel;
	}


	public UpgradeArrowHouse getArrow() {
		return arrow;
	}

	public int getInhabitants() {
		return Inhabitants;
	}

	public int getTotalInhabitants() {
		return totalInhabitants;
	}

	public boolean isPressedDown() {
		return pressedDown;
	}

	public int getMonthlyFood() {
		return monthlyFood;
	}


	public int getID() {
		return ID;
	}

	public int getMonthsWithoutFood() {
		return monthsWithoutFood;
	}

	public boolean isGotFood() {
		return GotFood;
	}

	public boolean isGotWater() {
		return GotWater;
	}

	public boolean isGotWood() {
		return GotWood;
	}

	public boolean isGotSkin() {
		return GotSkin;
	}

	public boolean isGotEntertainment() {
		return GotEntertainment;
	}

	public boolean isGotFood2() {
		return GotFood2;
	}

	public boolean isGotTheatre() {
		return GotTheatre;
	}

	public int getAmmountofEntertainment() {
		return AmmountofEntertainment;
	}

	public boolean isGotWheat() {
		return GotWheat;
	}

	public int getWheatAmmount() {
		return WheatAmmount;
	}

	public int getMaxWheatAmmount() {
		return maxWheatAmmount;
	}

	public int getRange() {
		return Range;
	}

	public Sprite getGotFoodSymbol() {
		return gotFoodSymbol;
	}
	
	public Polygon getPolygon(){
		return polygon;
	}
	public float getZ(){
		return this.z + yBaseStart;
	}
	
}
