package com.ligr.strategygame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import constants.ConstantBuildings;

import text.HouseDescriptionText;

public class House extends AnimatedSpriteObject {
	UpgradeArrowHouse arrow;
	private int Inhabitants, totalInhabitants;
	private boolean pressedDown = false;
	private int monthlyFood = 0;
	private String needs = ""; // Text for whatever the House needs to upgrade.
	private String satisfied = ""; // Whatever cravings that are satisfied.
	private String info = ""; // Info of the house income, inhabitants etc.

	int ID = 0;

	private int monthsWithoutFood = 0;

	public House(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub public House(float pX, float pY,
		// ITiledTextureRegion pTextureRegion,
		this.stopAnimation(0);

		// If the unemployment is below 60% people will imigrate or if there are
		// less than 100 unemployed workers
		if (!load) {
			if (MainActivity.MaxWorkers != 0 && MainActivity.Workers != 0) {
				if (MainActivity.Workers / MainActivity.MaxWorkers >= .60
						|| MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
					Inhabitants = 8;
					totalInhabitants = Inhabitants;
					updateInhabitantsPreset();
				} else {
					MainActivity
							.MessagePopUp(
									"The unemployment is too high, people won't come to your city!",
									Color.WHITE);
					Inhabitants = 0;
				}
			} else if (MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
				Inhabitants = 8;
				updateInhabitantsPreset();
			} else {
				MainActivity
						.MessagePopUp(
								"The unemployment is too high, people won't come to your city!",
								Color.WHITE);
				Inhabitants = 0;
			}
		}
	}

	public void save() {
		MainActivity.dataBase.add(name(), this.getX(), this.getY(), this.id,
				this.HouseLevel);
		Debug.e(String.valueOf(id));
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
	public boolean GotWheat = false;
	public int WheatAmmount = 0;
	public int maxWheatAmmount = 40;
	public int Range = 512;
	public int HouseLevel = 1;
	// private SpriteObject gotFoodSymbol;
	private Sprite gotFoodSymbol;

	public void createChilds() {
		ID = MainActivity.Houses.size();
		arrow = new UpgradeArrowHouse(this.mX + this.getWidth() / 2
				- MainActivity.upgradeArrowImage.getWidth() / 2, this.mY
				+ this.getHeight() / 2
				- MainActivity.upgradeArrowImage.getHeight() / 2 + 8,
				MainActivity.upgradeArrowImage,
				this.getVertexBufferObjectManager(), this);
		arrow.setAlpha(0);
		MainActivity.mScene.attachChild(arrow);
		// gotFoodSymbol = new SpriteObject(this.mX + this.getWidth()/2 -
		// MainActivity.gotFoodSymbolImage.getWidth()/2, this.mY +
		// 12,MainActivity.gotFoodSymbolImage,this.getVertexBufferObjectManager());
		gotFoodSymbol = new Sprite(this.mX + this.getWidth() / 2
				- MainActivity.gotFoodSymbolImage.getWidth() / 2, this.mY + 12,
				MainActivity.gotFoodSymbolImage,
				this.getVertexBufferObjectManager());
		MainActivity.mScene.attachChild(gotFoodSymbol);
		// MainActivity.asObjects.add(arrow);
		// MainActivity.sObjects.add(gotFoodSymbol);
	}

	// Method that updates the ammount of inhabitants and workers
	private void updateInhabitants() {
		Inhabitants = ConstantBuildings.HOUSEINHABITANTSGAINLVL[HouseLevel];
		totalInhabitants += Inhabitants;
		MainActivity.updateInhabitants(Inhabitants, HouseLevel,
				totalInhabitants);
	}

	private void updateInhabitantsPreset() {
		totalInhabitants += Inhabitants;
		MainActivity.updateInhabitants(Inhabitants, HouseLevel,
				totalInhabitants);
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if (pSceneTouchEvent.isActionDown()) {
			pressedDown = true;
		}
		if (pSceneTouchEvent.isActionUp()) {
			needs = ""; // Text for whatever the House needs to upgrade.
			satisfied = ""; // Whatever cravings that are satisfied.
			info = ""; // Info of the house income, inhabitants etc.
			if (arrow.getAlpha() != 1 && MainActivity.removeBuildings != true) {
				if (MainActivity.boolplacebuilding == false && pressedDown
						&& MainActivity.removeBuildings == false) {
					pressedDown = false;
					MainActivity.buildingDescriptionHUD.setAlpha(1);
					MainActivity.houseInfo = new HouseDescriptionText(256 + 96,
							96 + 64, MainActivity.smallerFont, this.info, 400,
							this.getVertexBufferObjectManager(), this);
					MainActivity.houseNeeds = new HouseDescriptionText(0,
							96 + 256 + 64 + 12, MainActivity.smallerFont,
							this.needs, 400,
							this.getVertexBufferObjectManager(), this);
					MainActivity.houseSatisfied = new HouseDescriptionText(0,
							96 + 12, MainActivity.smallerFont, this.satisfied,
							400, this.getVertexBufferObjectManager(), this);
					MainActivity.inGameHUD.attachChild(MainActivity.houseInfo);
					MainActivity.inGameHUD.attachChild(MainActivity.houseNeeds);
					MainActivity.inGameHUD
							.attachChild(MainActivity.houseSatisfied);
					this.info = "Population: " + this.totalInhabitants;
					this.info += "\nWheat: " + this.WheatAmmount + "/"
							+ this.maxWheatAmmount;
					MainActivity.buildingDescriptionCancel.setAlpha(1);
					MainActivity.buildingDescriptionTitle.setAlpha(1);
					// MainActivity.buildingDescriptionDetail.setAlpha(1);
					MainActivity.mScene
							.registerTouchArea(MainActivity.buildingDescriptionCancel);
					MainActivity.buildingDescriptionTitleString = "House";
					MainActivity.buildingDescriptionTitle
							.setText(MainActivity.buildingDescriptionTitleString);
					// MainActivity.buildingDescriptionDetailString =
					// "The inhabitants need accsess to water and food in order to upgrade";
					// MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);;
					if (HouseLevel >= 1) {
						// MainActivity.buildingDescriptionHouseWater.setAlpha(1);
						// MainActivity.buildingDescriptionHouseWheat.setAlpha(1);
						// MainActivity.buildingDescriptionHouseInhabitants.setAlpha(1);
					}
					if (GotWater) {
						this.satisfied += "*Your house have accsess to water\n";
					} else if (!GotWater) {
						this.needs += "*Your house needs accsess to water. \nIn order to upgrade, build a fountain!\n";

					}
					if (GotWheat) {
						this.satisfied += "*Your house have accsess to food\n";
					} else if (!GotWheat) {
						this.needs += "*Your house is out of food, \nyou need to build Wheat fields!\n";

					}
				}
				if (HouseLevel >= 2) {
					if (GotTheatre) {
						this.satisfied += "*Your house have culture\n";
					} else if (!GotTheatre) {
						this.needs += "*Your house needs accsess toculture \nin order to upgrade. Build a theatre\n";

					}
				}
				if (HouseLevel >= 3) {
					if (GotSkin) {
						this.satisfied += "*Your house have accsess to skin\n";
					} else if (!GotSkin) {
						this.needs += "*You need skin in order to upgrade the house.\n";

					}
				}
				try {
					MainActivity.houseInfo.setText(this.info);
					MainActivity.houseNeeds.setText(this.needs);
					MainActivity.houseSatisfied.setText(this.satisfied);
				} catch (NullPointerException e) {

				}
			} else if (MainActivity.removeBuildings == true
					&& MainActivity.boolplacebuilding == false && pressedDown) {
				MainActivity.MessagePopUpChoice(
						"Are you sure you want to remove the building?",
						Color.WHITE, this, "Remove", MainActivity.inGameHUD);
			}

			// IF we can upgrade
			else if (MainActivity.boolplacebuilding == false && pressedDown
					&& MainActivity.removeBuildings == false
					&& MainActivity.removeBuildings != true) {
				pressedDown = false;
				Debug.e("Can upgrade");
				String cost = "";
				if (HouseLevel == 3)
					cost = "2 skin, in order to upgrade the house";
				else if (HouseLevel == 4)
					cost = "3 skin and 5 marble in order to upgrade the house";
				MainActivity.MessagePopUpChoice(
						"Upgrading the house costs 2 skins. Are you sure?",
						Color.WHITE, this, "Upgdradehouselvl4",
						MainActivity.inGameHUD);
				Debug.e("After message");
				// MainActivity.buildingDescriptionHUD.setAlpha(1);
				// MainActivity.houseInfo = new
				// HouseDescriptionText(0,0,MainActivity.gameFont,this.info,400,this.getVertexBufferObjectManager(),
				// this);
				// MainActivity.houseNeeds = new
				// HouseDescriptionText(0,0,MainActivity.gameFont,this.needs,400,this.getVertexBufferObjectManager(),
				// this);
				// MainActivity.houseSatisfied = new
				// HouseDescriptionText(0,0,MainActivity.gameFont,this.satisfied,400,this.getVertexBufferObjectManager(),
				// this);
				//
				MainActivity.upgradeButton.parentHouse = this;
				MainActivity.upgradeButton.level = HouseLevel;
				MainActivity.upgradeButton.type = "House";
				/*
				 * MainActivity.buildingDescriptionTitleString =
				 * "Upgrade house to Level "+(HouseLevel+1);
				 * MainActivity.mScene.
				 * registerTouchArea(MainActivity.buildingDescriptionCancel);
				 * MainActivity.buildingDescriptionCancel.setAlpha(1);
				 * MainActivity.buildingDescriptionTitle.setAlpha(1);
				 * MainActivity.buildingDescriptionDetail.setAlpha(1);
				 * MainActivity.upgradeButton.setAlpha(1);
				 * MainActivity.buildingDescriptionTitle
				 * .setText(MainActivity.buildingDescriptionTitleString);
				 * MainActivity.buildingDescriptionDetailString =
				 * "It will cost you " + cost;
				 * MainActivity.buildingDescriptionDetail
				 * .setText(MainActivity.buildingDescriptionDetailString);;
				 */

			}

		}
		return true;
	}

	public void removeEntity() {
		this.arrow.detachSelf();

		MainActivity.mScene.unregisterTouchArea(this);
		this.gotFoodSymbol.detachSelf();
		MainActivity.Houses.remove(this);
		MainActivity.removeEntity(this);
		MainActivity.updateInhabitants(-this.totalInhabitants, this.HouseLevel,
				0);
	}

	private void drawRange() {
		// TODO Auto-generated method stub
		/*
		 * if(drawRange == true &&
		 * MainActivity.buildingDescriptionCancel.getAlpha() == 0) drawRange =
		 * false;
		 */

		/*
		 * if(drawRange == false){ Debug.e("Before");
		 * MainActivity.mScene.detachChild(circle); Debug.e("After"); circle =
		 * null;
		 * 
		 * }
		 */
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if (HouseLevel == 3) {
			if (GotFood && GotWater && GotTheatre && MainActivity.Skin >= 2) {
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
		for (int i = 0; i < MainActivity.FoodMarkets.size(); i++) {

			double distance = MainActivity.calculateDistance(
					MainActivity.FoodMarkets.get(i), house);
			if (distance < 512
					&& MainActivity.FoodMarkets.get(i).FoodAmmount > 0) {
				if (WheatAmmount > 0) {
					maxWheatAmmount = 12 * monthlyFood;
					house.GotWheat = true;
					house.GotFood = true;
					if (monthsWithoutFood > 0)
						monthsWithoutFood = 0;
					else
						monthsWithoutFood--;
				}
				if (MainActivity.Silos.size() > i
						&& MainActivity.Silos.get(i) != null
						&& MainActivity.Silos.get(i).FoodAmmount >= monthlyFood * 6
						&& WheatAmmount <= maxWheatAmmount / 2) {
					WheatAmmount += monthlyFood * 6;
					MainActivity.Silos.get(i).FoodAmmount -= monthlyFood * 6;
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
		for (int i = 0; i < MainActivity.Theatres.size(); i++) {
			double distance = MainActivity.calculateDistance(
					MainActivity.Theatres.get(i), house);
			if (distance < 512) {
				house.GotTheatre = true;
				break;
			} else {
				house.GotTheatre = false;
			}
		}
		if (MainActivity.Fountains.size() == 0)
			GotWater = false;
		if (MainActivity.Theatres.size() == 0)
			GotTheatre = false;
		for (int i = 0; i < MainActivity.Fountains.size(); i++) {
			double distance = MainActivity.calculateDistance(
					MainActivity.Fountains.get(i), house);
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
			if (MainActivity.MaxWorkers != 0 && MainActivity.Workers != 0) {
				if (MainActivity.Workers / MainActivity.MaxWorkers >= .60
						|| MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
					updateInhabitants();
					house.animate(
							new long[] { 50L, 50L, 50L, 50L },
							ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
							ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
							true);
					house.HouseLevel = 2;
				} else {
					MainActivity
							.MessagePopUp(
									"The unenployment is too high, your houses won't upgrade!",
									Color.WHITE);
					Inhabitants = 0;
				}
			} else if (MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
				updateInhabitants();
				house.animate(new long[] { 50L, 50L, 50L, 50L },
						ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
						ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
						true);
				house.HouseLevel = 2;
			} else {
				MainActivity
						.MessagePopUp(
								"The unenployment is too high, your houses won't upgrade!",
								Color.WHITE);
				Inhabitants = 0;
			}
		} else if (house.HouseLevel == 2 && house.GotWheat && house.GotWater
				&& house.GotTheatre) {
			if (MainActivity.MaxWorkers != 0 && MainActivity.Workers != 0) {
				if (MainActivity.Workers / MainActivity.MaxWorkers >= .60
						|| MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
					updateInhabitants();
					house.animate(
							new long[] { 50L, 50L, 50L, 50L },
							ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
							ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
							true);
					house.HouseLevel = 3;
				} else {
					MainActivity
							.MessagePopUp(
									"The unenployment is too high, your houses won't upgrade!",
									Color.WHITE);
					Inhabitants = 0;
				}
			} else if (MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
				updateInhabitants();
				house.animate(new long[] { 50L, 50L, 50L, 50L },
						ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
						ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
						true);
				house.HouseLevel = 3;
			} else {
				MainActivity
						.MessagePopUp(
								"The unenployment is too high, your houses won't upgrade!",
								Color.WHITE);
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
		if (MainActivity.MaxWorkers != 0 && MainActivity.Workers != 0) {
			if (MainActivity.Workers / MainActivity.MaxWorkers >= .60
					|| MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
				updateInhabitants();
				this.animate(new long[] { 50L, 50L, 50L, 50L },
						ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
						ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel],
						true);
				MainActivity.RemoveResources("Skin", 2);
				arrow.setAlpha(0);
				ArrowUpgrade();
				this.HouseLevel = 4;
			} else {
				MainActivity
						.MessagePopUp(
								"The unenployment is too high, your houses won't upgrade!",
								Color.WHITE);
				Inhabitants = 0;
			}
		} else if (MainActivity.Workers - MainActivity.MaxWorkers >= -100) {
			updateInhabitants();
			arrow.setAlpha(0);

			this.animate(new long[] { 50L, 50L, 50L, 50L },
					ConstantBuildings.HOUSEANIMATIONLVLSTART[HouseLevel],
					ConstantBuildings.HOUSEANIMATIONLVLEND[HouseLevel], true);
			MainActivity.buybutton.RemoveResources("Skin", 2);

			ArrowUpgrade();
			this.HouseLevel = 4;
		} else {
			MainActivity.MessagePopUp(
					"The unenployment is too high, your houses won't upgrade!",
					Color.WHITE);
			Inhabitants = 0;
		}
	}

	private void ArrowUpgrade() {
		// TODO Auto-generated method stub
		MainActivity.mScene.unregisterTouchArea(arrow);
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
}
