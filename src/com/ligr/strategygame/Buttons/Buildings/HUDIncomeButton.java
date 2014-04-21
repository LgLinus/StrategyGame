package com.ligr.strategygame.Buttons.Buildings;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.PlaceBuilding;

public class HUDIncomeButton extends Sprite {

	private static String currentbuilding;
	private ArrayList<Text> incomes;
	private ArrayList<Text> expensesText;
	private boolean menuOpen = false;
	private Sprite incomeHUD = new Sprite(0,69,MainActivity.incomeHUDImage,this.getVertexBufferObjectManager());
	private MainActivity main;
	private Text total = new Text(48,incomeHUD.getHeight()+incomeHUD.getY()-32,main.smallerFont,"",100,this.getVertexBufferObjectManager());
	
	private Text totalIncome = new Text(48,incomeHUD.getHeight()+incomeHUD.getY()-64,main.gameFont,"",20,this.getVertexBufferObjectManager());
	
	public HUDIncomeButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		expensesText = new ArrayList<Text>();incomes = new ArrayList<Text>();
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			if(!menuOpen)
				open();
			else
				close();
		}
		return true;
	
	}
	public void close() {
		MainActivity.menuIncomeOpen = false;
		menuOpen = false;
		for(int i = 0; i < incomes.size();i++){
			MainActivity.removeEntity(incomes.get(i));
			}
		incomes = new ArrayList<Text>();

		for(int i = 0; i < expensesText.size();i++){
			MainActivity.removeEntity(expensesText.get(i));
			}
		expensesText = new ArrayList<Text>();
	MainActivity.removeEntity(incomeHUD);
 MainActivity.removeEntity(total);
		MainActivity.removeEntity(totalIncome);
	
	}
	public void open() {
		MainActivity.closeMenus();
		MainActivity.menuIncomeOpen = true;
		menuOpen = true;expensesText = new ArrayList<Text>();incomes = new ArrayList<Text>();
		main.inGameHUD.attachChild(incomeHUD);
		openIncome();
		openExpenses();
		//Text totalIncome = new Text(48,incomeHUD.getHeight()+incomeHUD.getY()-64,main.gameFont,"",20,this.getVertexBufferObjectManager());
		totalIncome.setText("Total");
		totalIncome.setColor(Color.YELLOW);
		//Text total = new Text(48,incomeHUD.getHeight()+incomeHUD.getY()-32,main.smallerFont,"",100,this.getVertexBufferObjectManager());
		String res  =""+(main.income-main.expenses);
		Color color = Color.YELLOW;
		res = ""+(main.monthlygain);
		total.setText("Total income: " + res);
		if(main.monthlygain<0)
			total.setColor(Color.RED);
		main.inGameHUD.attachChild(total);
		main.inGameHUD.attachChild(totalIncome);
		
	}

	private void openIncome() {
		float startX = 0;
		float startY = incomeHUD.getY()+48;
		int tempY=1;
		for(int i = 0; i < main.InhabitantsSize;i++){
			if(main.Inhabitants[i] != 0){
				tempY++;
				Text temp = new Text(startX,startY+tempY*24,main.smallerFont,"Inhabitants level " + i + "| " + MainActivity.Inhabitants[i] * 9 * (0.75+(0.25*i)),200,this.getVertexBufferObjectManager());
				temp.setColor(Color.WHITE);
				incomes.add(temp);
				main.inGameHUD.attachChild(temp);
			}
		}
	}
	
	private void openExpenses() {
		float startX = 0;
		float startY = incomeHUD.getY()+256+24;
		int tempY=1;
		Text temp = new Text(startX,startY,main.smallerFont,"",1000,this.getVertexBufferObjectManager());
		expensesText.add(temp);
		String res ="";
		res+=(String.valueOf(main.expenses));
//		if(main.barracks.size()!=0)
//		res+="Barrack: " + main.barracks.size()*MainActivity.EXPENSESBARRACK+"\n";
//		if(main.brickFoundrys.size()!=0)
//		res+="Brick foundry: " + main.brickFoundrys.size()*MainActivity.EXPENSESBRICKFOUNNDRY+"\n";
//		if(main.butchers.size()!=0)
//		res+="Butcher: " + main.butchers.size()*MainActivity.EXPENSESBUTCHER+"\n";
//		if(main.Farms.size()!=0)
//		res+="Farm: " + main.Farms.size()*MainActivity.EXPENSESFARM+"\n";
//		if(main.FishingHuts.size()!=0)
//		res+="Fishing hut: " + main.FishingHuts.size()*MainActivity.EXPENSESFISHINGHUT+"\n";
//		if(main.FoodMarkets.size()!=0)
//		res+="Food market: " + main.FoodMarkets.size()*MainActivity.EXPENSESFOODMARKET+"\n";
//		if(main.Fountains.size()!=0)
//		res+="Fountain: " + main.Fountains.size()*MainActivity.EXPENSESFOUNTAIN+"\n";
//		if(main.huntersLodges.size()!=0)
//		res+="Hunters lodge: " + main.huntersLodges.size()*MainActivity.EXPENSESHUNTERSLODGE+"\n";
//		if(main.mineDepositClays.size()!=0)
//		res+="Clay mine: " + main.mineDepositClays.size()*MainActivity.EXPENSESMINEDEPOSITCLAY+"\n";
//		if(main.mineDepositBronzes.size()!=0)
//		res+="Bronze mine: " + main.mineDepositBronzes.size()*MainActivity.EXPENSESMINEDEPOSITBRONZE+"\n";
//		if(main.Silos.size()!=0)
//		res+="Silo: " + main.Silos.size()*MainActivity.EXPENSESSILO+"\n";
//		if(main.skinners.size()!=0)
//		res+="Skinner: " + main.skinners.size()*MainActivity.EXPENSESSKINNER+"\n";
//		if(main.Stocks.size()!=0)
//		res+="Stock: " + main.Stocks.size()*MainActivity.EXPENSESSTOCK+"\n";
//		if(main.StoneCutters.size()!=0)
//		res+="Stone cutter " + main.StoneCutters.size()*MainActivity.EXPENSESSTONECUTTER+"\n";
//		if(main.Theatres.size()!=0)
//		res+="Theatre: " + main.Theatres.size()*MainActivity.EXPENSESTHEATRE+"\n";
//		if(main.WoodCutters.size()!=0)
//		res+="Wood cutter: " + main.WoodCutters.size()*MainActivity.EXPENSESWOODCUTTER+"\n";
//		if(main.militaryHoplite!=0)
//		res+="Hoplite: " + MainActivity.militaryHoplite*MainActivity.EXPENSESHOPLITE+"\n";
//		
		temp.setText(res);
		temp.setColor(Color.RED);
		main.inGameHUD.attachChild(temp);
	}
	
}
