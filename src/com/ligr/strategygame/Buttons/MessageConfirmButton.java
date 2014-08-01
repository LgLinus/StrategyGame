package com.ligr.strategygame.Buttons;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.buildings.House;

public class MessageConfirmButton extends Sprite {

	private static String currentbuilding;
	private MainActivity main;
	private MessageCancelButton messageCancelButton;
	private Text messageText;
	private Sprite messageHUD;
	private MessageConfirmButton messageConfirmButton;
	public MessageConfirmButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		this.main = main;
		main.PAUSE = true;
	}

	public void display(){
	
		}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionUp()){
			
			Choice();}
			return true;
		
		}
		public void Choice(){
			if(main.choice =="Upgdradehouselvl4"){
				upgradehouseLvl4();
			}
			else if(main.choice =="Attack City"){
				attackCity();
			}else if(main.choice =="Save"){
				main.getController().saveState();
			}else if(main.choice =="Return"){
				main.getController().returnGameFromBattleField();
			}else if(main.choice =="Attack Warning"){
					attackWarning();
			}else if(main.choice =="Remove"){
				if(main.ID instanceof AnimatedSpriteObject)
				main.removeAnimatedSpriteObject();
				else
				main.removeSpriteObject();
			}
			main.removeMessage();
		}



		private void attackWarning() {
			if(main.getController().getGold()>=main.bribe){
			CityIcon target;
		    target = (CityIcon)main.ID;
			target.atWar = false;
			target.monthSinceAttack=0;
			target.chanceofAttack = 0;
			main.getController().updateGold(-main.bribe);}
			else{
				main.makeToast("You can't afford the bribe. The attack will continue as planned");
			}
		}



		private void attackCity() {			
			main.getController().setMilitaryHopliteWar(main.getController().getMilitaryHoplite());
			main.getController().setMilitaryHoplite(0);
//			main.saveState();
//			main.inGameHUD.detachChildren();
//			main.inGameHUD.clearTouchAreas();
//			main.resetArrayLists();
//			
			CityIcon target;
			MenuMapAttackButton id = (MenuMapAttackButton)main.ID;
			target = id.getCity();
//			main.enterBattlefield(target);
			main.getController().calculateWinner(target);
			Debug.e(target.getName()+"xaxax");
			
		}






		private void upgradehouseLvl4() {
			Entity ID = main.ID;
			House tempHouse = (House)ID;
			if(tempHouse.HouseLevel==3){
				if(main.getController().Skin>=2 && main.getController().Marble >= 1){
				tempHouse.upgradetolvl4();
				}
			
		}
		}
		
		
	
	
}
