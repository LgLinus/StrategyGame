package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.constants.Constant;


public class CityIcon extends Sprite {

	public int index;
	public Text nameText;
	public ArrayList<Text> soldierTexts;
	public String messages[] = {"Test1","Test2","Test3","Test4","Test5","Test6","Test7","Test8","Test9","Test10"};
	int monthsNoWar = 0;
	int cash = 0;
	double chanceofAttack = 0;
	public int monthSinceAttack = 0;
	boolean atWar = false;
	private MainActivity main;
	public CityIcon(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,int index, MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		this.index = index;
		this.main = main;
		nameText = new Text(this.mX+4,this.mY+this.getHeight()+2, main.smallFont, "", 30, pVertexBufferObjectManager);

		soldierTexts = new ArrayList<Text>();
	}
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if(pSceneTouchEvent.isActionUp()){
			main.removeCityIconTexts();
			pressed();
			updateMessage("Pressed !");
			showMessage();
			showAttack();
			showSoldierText();
		}
		return true;
	}

	private void showAttack() {
		if(main.menuMapAttackButton!=null){
			main.inGameHUD.unregisterTouchArea(main.menuMapAttackButton);
			main.removeEntity(main.menuMapAttackButton);}
		main.menuMapAttackButton  = new MenuMapAttackButton(main.menuMapHUD.getX()-120, 277, main.getImages().getMenuMapAttackButtonImage(), this.getVertexBufferObjectManager(), this,main);
		main.inGameHUD.registerTouchArea(main.menuMapAttackButton);
		main.inGameHUD.attachChild(main.menuMapAttackButton);
	}
	public void updateMessage(String message){
		for(int i = messages.length-1; i >= 0; i--){
			if(i==messages.length){
				messages[i] = "";
			}
			else if(i==0){
				messages[i] = message;
			}
			else{
				messages[i] = messages[i-1];
			}
		}
	}

	public void showMessage(){
		for(int i = 0; i < messages.length; i++){
			main.cityMessages.get(i).setColor(Color.BLACK);
			main.cityMessages.get(i).setText(messages[i]);
		}
	}
	public void setText(){
		if(Integer.parseInt(main.CITY[index][8])==1){
			main.removeEntity(this.nameText, main.inGameHUD);
			nameText = new Text(this.mX-12,this.mY+this.getHeight()+2, main.smallFont, "", 30, getVertexBufferObjectManager());
			main.inGameHUD.attachChild(this.nameText);
			nameText.setColor(Color.BLACK);
			nameText.setText(main.CITY[index][0]);
		}
	}
	
	public void showSoldierText(){
		Text temp;
		int i =0;
		if(main.CITY[index][11]!=""){
			temp = new Text(main.menuMapHUD.getX()+4,376+i*32, main.smallFont, "Slingers : " + main.CITY[index][11], 100, getVertexBufferObjectManager());
			main.inGameHUD.attachChild(temp);
			this.soldierTexts.add(temp);
			i+=1;
		}	
		if(main.CITY[index][12]!=""){
			temp = new Text(main.menuMapHUD.getX()+4,376+i*32, main.smallFont, "Hoplites : " + main.CITY[index][12], 100, getVertexBufferObjectManager());
			main.inGameHUD.attachChild(temp);
			this.soldierTexts.add(temp);
			i++;
		}
	}
	public void removeText(){
		nameText.setText("");
		this.removeSoldierText();
	}
	public void removeSoldierText() {
		for(int i = 0; i <this.soldierTexts.size();i++){
			main.removeEntity(this.soldierTexts.get(i), main.inGameHUD);
		}
		this.soldierTexts = new ArrayList<Text>();
	}
	private void pressed() {
		Debug.e("Pressed");
		main.moveText.setText(getName());
		main.moveText.setPosition(4+main.menuMap.getWidth(),40);
		main.moveTextSmall.setText(getRelation());
		main.moveTextSmall.setColor(Color.BLACK);
		main.moveTextSmall.setPosition(40+main.menuMap.getWidth(),72);
	}

	public void update(){
		if(main.CITY[index][8]=="1"){
		attackAI();
		}
	}

	public String toString(){
		String res;
		res ="";
		res+=getName();
		res+=getType();
		res+=getRelation();


		Debug.e(res);
		return res;}

	public int getCash(){
		return cash;
	}
	public String getRelation() {
		String res = "";
		if(Integer.parseInt(main.CITY[index][2]) < 20 ){
			res+=" Hostile"; 
		}
		else if(Integer.parseInt(main.CITY[index][2]) >= 20  && Integer.parseInt(main.CITY[index][2]) < 50){
			return " Neutral"; 
		}
		else if(Integer.parseInt(main.CITY[index][2]) >= 50  && Integer.parseInt(main.CITY[index][2]) < 90){
			return" Friendly"; 
		}
		else if(Integer.parseInt(main.CITY[index][2]) >= 90  && Integer.parseInt(main.CITY[index][2]) <= 120){
			return " Allies"; }
		return "NO RELATION";

	}

	public String getName(){
		return main.CITY[index][0];
	}
	public String getType() {	
		String res="";
		if(Integer.parseInt(main.CITY[index][1]) == 0){
			return " Peacefull";
		}
		else if (Integer.parseInt(main.CITY[index][1]) == 1){
			return " Religious";
		}
		else if(Integer.parseInt(main.CITY[index][1]) == 2){
			return " Military";
		}
		return " NOTYPE ";
	}

	public int getMilitaryPower(){
		int power = 0;
		power+= Integer.parseInt(main.CITY[index][11]) * Constant.POWERSLINGER;
		power+= Integer.parseInt(main.CITY[index][12]) * Constant.POWERHOPLITE;

		return power;
	}

	public void attackAI(){
		if(main.freeMonths<=0){
			if(this.getTribute()!=2){
				if(!atWar){
					this.monthSinceAttack++;
					double relation = Integer.parseInt(main.CITY[index][2]);
					double relationMinus = 100-relation;
					if(main.CITY[index][1] == "2"){

						chanceofAttack += ((0.01*((relationMinus*relationMinus)/5000)) * (monthSinceAttack/10));
					}
					if(chanceofAttack>=1){
						main.bribe = 20000;
						main.MessagePopUpChoice(this.getName() +" is planning an attack against you. They will reach your city in " +  this.getDistance() + " months.\nWould you like to bribe them off for: "+ main.bribe +" gold?" , Color.WHITE, this, "Attack Warning", main.inGameHUD);
						atWar = true;
						this.monthSinceAttack = Integer.parseInt(this.getDistance());
					}
				}
				else{
					if(this.monthSinceAttack<=0){
						main.messagePopUp("Your city is under attack!", Color.WHITE);
						main.getMessageConfirmButton().calculateWinnerCityAttacked(this);
					}
					this.monthSinceAttack--;
				}
			}
		}
		else
			main.freeMonths--;}
	private int getTribute() {
		return Integer.parseInt(main.CITY[index][9]);
	}
	private String getDistance() {
		// TODO Auto-generated method stub
		return main.CITY[index][13];
	}
}
