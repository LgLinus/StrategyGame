package com.ligr.strategygame;

import org.andengine.util.debug.Debug;

public class City{
	
	int index;
	int monthsNoWar = 0;
	int cash = 0;
	public City(int index){
		this.index = index;
	}
	
	public void update(){
		
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
}