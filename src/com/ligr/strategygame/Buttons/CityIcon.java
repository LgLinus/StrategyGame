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

import constants.Constant;

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
	public CityIcon(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,int index) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		this.index = index;
		nameText = new Text(this.mX+4,this.mY+this.getHeight()+2, MainActivity.smallFont, "", 30, pVertexBufferObjectManager);

		soldierTexts = new ArrayList<Text>();
	}
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if(pSceneTouchEvent.isActionUp()){
			MainActivity.removeCityIconTexts();
			pressed();
			updateMessage("Pressed !");
			showMessage();
			showAttack();
			showSoldierText();
		}
		return true;
	}

	private void showAttack() {
		if(MainActivity.menuMapAttackButton!=null){
			MainActivity.inGameHUD.unregisterTouchArea(MainActivity.menuMapAttackButton);
			MainActivity.removeEntity(MainActivity.menuMapAttackButton);}
		MainActivity.menuMapAttackButton  = new MenuMapAttackButton(MainActivity.menuMapHUD.getX()-120, 277, MainActivity.menuMapAttackButtonImage, this.getVertexBufferObjectManager(), this);
		MainActivity.inGameHUD.registerTouchArea(MainActivity.menuMapAttackButton);
		MainActivity.inGameHUD.attachChild(MainActivity.menuMapAttackButton);
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
			MainActivity.cityMessages.get(i).setColor(Color.BLACK);
			MainActivity.cityMessages.get(i).setText(messages[i]);
		}
	}
	public void setText(){
		if(Integer.parseInt(MainActivity.CITY[index][8])==1){
			MainActivity.removeEntity(this.nameText, MainActivity.inGameHUD);
			nameText = new Text(this.mX-12,this.mY+this.getHeight()+2, MainActivity.smallFont, "", 30, getVertexBufferObjectManager());
			MainActivity.inGameHUD.attachChild(this.nameText);
			nameText.setColor(Color.BLACK);
			nameText.setText(MainActivity.CITY[index][0]);
		}
	}
	
	public void showSoldierText(){
		Text temp;
		int i =0;
		if(MainActivity.CITY[index][11]!=""){
			temp = new Text(MainActivity.menuMapHUD.getX()+4,376+i*32, MainActivity.smallFont, "Slingers : " + MainActivity.CITY[index][11], 100, getVertexBufferObjectManager());
			MainActivity.inGameHUD.attachChild(temp);
			this.soldierTexts.add(temp);
			i+=1;
		}	
		if(MainActivity.CITY[index][12]!=""){
			temp = new Text(MainActivity.menuMapHUD.getX()+4,376+i*32, MainActivity.smallFont, "Hoplites : " + MainActivity.CITY[index][12], 100, getVertexBufferObjectManager());
			MainActivity.inGameHUD.attachChild(temp);
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
			MainActivity.removeEntity(this.soldierTexts.get(i), MainActivity.inGameHUD);
		}
		this.soldierTexts = new ArrayList<Text>();
	}
	private void pressed() {
		Debug.e("Pressed");
		MainActivity.moveText.setText(getName());
		MainActivity.moveText.setPosition(4+MainActivity.menuMap.getWidth(),40);
		MainActivity.moveTextSmall.setText(getRelation());
		MainActivity.moveTextSmall.setColor(Color.BLACK);
		MainActivity.moveTextSmall.setPosition(40+MainActivity.menuMap.getWidth(),72);
	}

	public void update(){
		if(MainActivity.CITY[index][8]=="1"){
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
		if(Integer.parseInt(MainActivity.CITY[index][2]) < 20 ){
			res+=" Hostile"; 
		}
		else if(Integer.parseInt(MainActivity.CITY[index][2]) >= 20  && Integer.parseInt(MainActivity.CITY[index][2]) < 50){
			return " Neutral"; 
		}
		else if(Integer.parseInt(MainActivity.CITY[index][2]) >= 50  && Integer.parseInt(MainActivity.CITY[index][2]) < 90){
			return" Friendly"; 
		}
		else if(Integer.parseInt(MainActivity.CITY[index][2]) >= 90  && Integer.parseInt(MainActivity.CITY[index][2]) <= 120){
			return " Allies"; }
		return "NO RELATION";

	}

	public String getName(){
		return MainActivity.CITY[index][0];
	}
	public String getType() {	
		String res="";
		if(Integer.parseInt(MainActivity.CITY[index][1]) == 0){
			return " Peacefull";
		}
		else if (Integer.parseInt(MainActivity.CITY[index][1]) == 1){
			return " Religious";
		}
		else if(Integer.parseInt(MainActivity.CITY[index][1]) == 2){
			return " Military";
		}
		return " NOTYPE ";
	}

	public int getMilitaryPower(){
		int power = 0;
		power+= Integer.parseInt(MainActivity.CITY[index][11]) * Constant.POWERSLINGER;
		power+= Integer.parseInt(MainActivity.CITY[index][12]) * Constant.POWERHOPLITE;

		return power;
	}

	public void attackAI(){
		if(MainActivity.freeMonths<=0){
			if(this.getTribute()!=2){
				if(!atWar){
					this.monthSinceAttack++;
					double relation = Integer.parseInt(MainActivity.CITY[index][2]);
					double relationMinus = 100-relation;
					if(MainActivity.CITY[index][1] == "2"){

						chanceofAttack += ((0.01*((relationMinus*relationMinus)/5000)) * (monthSinceAttack/10));
					}
					if(chanceofAttack>=1){
						MainActivity.bribe = 20000;
						MainActivity.MessagePopUpChoice(this.getName() +" is planning an attack against you. They will reach your city in " +  this.getDistance() + " months.\nWould you like to bribe them off for: "+ MainActivity.bribe +" gold?" , Color.WHITE, this, "Attack Warning", MainActivity.inGameHUD);
						atWar = true;
						this.monthSinceAttack = Integer.parseInt(this.getDistance());
					}
				}
				else{
					if(this.monthSinceAttack<=0){
						MainActivity.MessagePopUp("Your city is under attack!", Color.WHITE);
						MessageConfirmButton.calculateWinnerCityAttacked(this);
					}
					this.monthSinceAttack--;
				}
			}
		}
		else
			MainActivity.freeMonths--;}
	private int getTribute() {
		return Integer.parseInt(MainActivity.CITY[index][9]);
	}
	private String getDistance() {
		// TODO Auto-generated method stub
		return MainActivity.CITY[index][13];
	}
}
