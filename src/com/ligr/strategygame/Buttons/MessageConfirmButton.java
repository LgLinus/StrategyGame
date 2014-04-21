package com.ligr.strategygame.Buttons;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;

public class MessageConfirmButton extends Sprite {

	private static String currentbuilding;
	
	public MessageConfirmButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		MainActivity.PAUSE = true;
	}


	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionUp()){
			
			Choice();}
			return true;
		
		}
		public static void Choice(){
			if(MainActivity.choice =="Upgdradehouselvl4"){
				upgradehouseLvl4();
			}
			else if(MainActivity.choice =="Attack City"){
				attackCity();
			}else if(MainActivity.choice =="Save"){
				MainActivity.saveState();
			}else if(MainActivity.choice =="Return"){
				MainActivity.returnGameFromBattleField();
			}else if(MainActivity.choice =="Attack Warning"){
					attackWarning();
			}else if(MainActivity.choice =="Remove"){
				if(MainActivity.ID instanceof AnimatedSpriteObject)
				MainActivity.removeAnimatedSpriteObject();
				else
				MainActivity.removeSpriteObject();
			}
			MainActivity.removeMessage();
		}



		private static void attackWarning() {
			if(MainActivity.Gold>=MainActivity.bribe){
			CityIcon target;
		    target = (CityIcon)MainActivity.ID;
			target.atWar = false;
			target.monthSinceAttack=0;
			target.chanceofAttack = 0;
			MainActivity.updateGold(-MainActivity.bribe);}
			else{
				MainActivity.MakeToast("You can't afford the bribe. The attack will continue as planned");
			}
		}



		private static void attackCity() {			
			MainActivity.militaryHopliteWar = MainActivity.militaryHoplite;
			MainActivity.militaryHoplite = 0;
//			MainActivity.saveState();
//			MainActivity.inGameHUD.detachChildren();
//			MainActivity.inGameHUD.clearTouchAreas();
//			MainActivity.resetArrayLists();
//			
			CityIcon target;
			MenuMapAttackButton id = (MenuMapAttackButton)MainActivity.ID;
			target = id.getCity();
//			MainActivity.enterBattlefield(target);
			calculateWinner(target);
			Debug.e(target.getName()+"xaxax");
			
		}



		public static void calculateWinner(CityIcon target) {
			int powerPlayer = 0, powerEnemy = 0;
			powerPlayer = MainActivity.getMilitaryOffensivePower();
			powerEnemy = target.getMilitaryPower();
			if(powerPlayer>powerEnemy){
				MainActivity.MessagePopUp("You won the battle!", Color.WHITE);
				MainActivity.CITY[target.index][11] = "0";
				MainActivity.CITY[target.index][12] = "0";
				MainActivity.CITY[target.index][9] = "1";
			}
			else{
				MainActivity.MessagePopUp("You lost the battle..", Color.WHITE);
			}
		}

		public static void calculateWinnerCityAttacked(CityIcon target) {
			int powerPlayer = 0, powerEnemy = 0;
			powerPlayer = MainActivity.getMilitaryOffensivePower();
			powerEnemy = target.getMilitaryPower();
			if(powerPlayer>powerEnemy){
				MainActivity.MessagePopUp("You protected your city from the attack!", Color.WHITE);
				target.atWar  = false;
				target.monthSinceAttack = 0;
			}
			else{
				MainActivity.CITY[target.index][9] = "2";
				target.atWar = false;
				target.monthSinceAttack = 0;
				MainActivity.MessagePopUp("You lost the battle!\nYour city will know pay 15 000 coins as tribute yearly to " + target.getName() + "\nIf you decide to attack their city they will respond and try to destroy your city.", Color.WHITE);
			}
		}



		private static void upgradehouseLvl4() {
			Entity ID = MainActivity.ID;
			House tempHouse = (House)ID;
			if(tempHouse.HouseLevel==3){
				if(MainActivity.Skin>=2 && MainActivity.Marble >= 1){
				tempHouse.upgradetolvl4();
				}
			
		}
		}
		
		
	
	
}
