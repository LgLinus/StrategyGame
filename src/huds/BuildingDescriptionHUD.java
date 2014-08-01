package huds;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import text.HouseDescriptionText;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Buttons.BuildingDescriptionCancel;
import com.ligr.strategygame.buildings.House;
/**
 * Class responsible of giving a description of a building
 * @author LgLinuss
 *
 */
public class BuildingDescriptionHUD extends Sprite {

	private House house;
	private MainActivity main;
	private HouseDescriptionText houseInfo, houseNeeds, houseSatisfied;
	private String info="", buildingDescriptionTitleString="",buildingDescriptionDetailString="",needs="",satisfied="";
	private BuildingDescriptionCancel buildingDescriptionCancel;
	private Text buildingDescriptionTitle,buildingDescriptionDetail;
	private int houseLevel;
	private String option ="none";

	/**
	 * Create the description of a house
	 */
	public BuildingDescriptionHUD(float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, House house) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		this.house = house;
		main.getInGameHUD().attachChild(this);
		this.houseLevel = house.getHouseLevel();
		this.setAlpha(0.95f);
		createHouseHUD();
	}
	
	/**
	 * Create the description of a building
	 */
	public BuildingDescriptionHUD(float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, String title, String detail) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		this.setAlpha(0.95f);
		main.getInGameHUD().attachChild(this);
		main.setBuildingDescriptionHUD(this);
		this.buildingDescriptionTitleString = title;
		this.buildingDescriptionDetailString = detail;
		createMainHUD();
		createBuildingHUD();
	}

	/**
	 * Create the house, takes an argument for a string for extra functionality
	 */
	public BuildingDescriptionHUD(int pX, int pY,
			TextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, String string, String option) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		this.setAlpha(1.0f);
		main.getInGameHUD().attachChild(this);
		main.setBuildingDescriptionHUD(this);
		createMainHUD();
		this.option  = option;
	}

	private void createBuildingHUD() {
		createMainHUD();
		buildingDescriptionTitle = new Text(48 + 24, 82, main.getGameFont(),
				buildingDescriptionTitleString, 30,
				this.getVertexBufferObjectManager());

		main.getInGameHUD().attachChild(buildingDescriptionTitle);
		buildingDescriptionDetail = new Text(48 + 8, 82 + 238 + 4, main.getSmallFont(),
				buildingDescriptionDetailString, 10000,
				this.getVertexBufferObjectManager());
		main.getInGameHUD().attachChild(buildingDescriptionDetail);
	}

	private void createMainHUD(){
		buildingDescriptionCancel = new BuildingDescriptionCancel(48,
				81 + 1, main.getImages().getCancelImage(), this.getVertexBufferObjectManager(),main);
//		main.getInGameHUD().attachChild(buildingDescriptionCancel);
//		main.getInGameHUD().registerTouchArea(buildingDescriptionCancel);
	}
	
	private void createHouseHUD() {
		houseInfo = new HouseDescriptionText(256 + 96, 96 + 64,
				main.getSmallerFont(), info, 400,
				this.getVertexBufferObjectManager(), house);
		houseNeeds = new HouseDescriptionText(0, 96 + 256 + 64 + 12,
				main.getSmallerFont(), needs, 400,
				this.getVertexBufferObjectManager(), house);
		houseSatisfied = new HouseDescriptionText(0, 96 + 12,
				main.getSmallerFont(), satisfied, 400,
				this.getVertexBufferObjectManager(), house);
	
		main.getInGameHUD().attachChild(houseInfo);
		main.getInGameHUD().attachChild(houseNeeds);
		main.getInGameHUD().attachChild(houseSatisfied);
		this.info = "Population: " + house.getTotalInhabitants();
		this.info += "\nWheat: " + house.getWheatAmmount() + "/"
				+ house.getMaxWheatAmmount();
		buildingDescriptionTitle = new Text(48 + 24, 82, main.getGameFont(),
				buildingDescriptionTitleString, 30,
				this.getVertexBufferObjectManager());

		main.getScene().attachChild(buildingDescriptionTitle);
//		main.getScene().registerTouchArea(buildingDescriptionCancel);
		buildingDescriptionTitleString = "House";
		buildingDescriptionTitle.setText(buildingDescriptionTitleString);
		// main.buildingDescriptionDetailString =
		// "The inhabitants need accsess to water and food in order to upgrade";
		// main.buildingDescriptionDetail.setText(main.buildingDescriptionDetailString);;
		if (houseLevel >= 1) {
			// main.buildingDescriptionHouseWater.setAlpha(1);
			// main.buildingDescriptionHouseWheat.setAlpha(1);
			// main.buildingDescriptionHouseInhabitants.setAlpha(1);
		}
		if (house.isGotWater()) {
			this.satisfied += "*Your house have accsess to water\n";
		} else if (!house.isGotWater()) {
			this.needs += "*Your house needs accsess to water. \nIn order to upgrade, build a fountain!\n";

		}
		if (house.isGotWheat()) {
			this.satisfied += "*Your house have accsess to food\n";
		} else if (!house.isGotWheat()) {
			this.needs += "*Your house is out of food, \nyou need to build Wheat fields!\n";

		}

		if (houseLevel >= 2) {
			if (house.isGotTheatre()) {
				this.satisfied += "*Your house have culture\n";
			} else if (!house.isGotTheatre()) {
				this.needs += "*Your house needs accsess toculture \nin order to upgrade. Build a theatre\n";

			}
		}
		if (houseLevel >= 3) {
			if (house.isGotSkin()) {
				this.satisfied += "*Your house have accsess to skin\n";
			} else if (!house.isGotSkin()) {
				this.needs += "*You need skin in order to upgrade the house.\n";

			}
		}
			houseInfo.setText(this.info);
			houseNeeds.setText(this.needs);
			houseSatisfied.setText(this.satisfied);
		
	}

	/**
	 * Remove all entities for this class
	 */
	public void remove() {
		main.removeEntity(this);
		if(houseInfo!=null)
		main.removeEntity(houseInfo);
		if(houseNeeds!=null)
		main.removeEntity(houseNeeds);
		if(houseSatisfied!=null)
		main.removeEntity(houseSatisfied);
		main.getScene().unregisterTouchArea(buildingDescriptionCancel);
		if(buildingDescriptionCancel!=null)
		main.removeEntity(buildingDescriptionCancel);
		if(buildingDescriptionTitle!=null)
		main.removeEntity(buildingDescriptionTitle);
		if(buildingDescriptionDetail!=null)
		main.removeEntity(buildingDescriptionDetail);
		main.setBuildingDescriptionHUD(null);
	}

}
