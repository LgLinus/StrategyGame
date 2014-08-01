package com.ligr.strategygame.Buttons.Buildings;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.PlaceBuilding;
import com.ligr.strategygame.buildings.House;

public class HUDIncomeButton extends Sprite {

	private static String currentbuilding;
	private ArrayList<Text> incomes;
	private ArrayList<Text> expensesText;
	private boolean menuOpen = false;
	private MainActivity main;
	private Text total;
	
	private Text totalIncome;
	
	public HUDIncomeButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		this.main = main;
		expensesText = new ArrayList<Text>();incomes = new ArrayList<Text>();
		total  = new Text(48,main.getImages().getIncomeHUDImage().getHeight()+69-32,main.smallerFont,"",100,this.getVertexBufferObjectManager());
		totalIncome  = new Text(48,main.getImages().getIncomeHUDImage().getHeight()+69-64,main.gameFont,"",20,this.getVertexBufferObjectManager());
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			if(!menuOpen){
				main.closeMenus();
				main.openIncomeMenu();}
			else
				close();
		}
		return true;
	
	}
	public void close() {
		main.menuIncomeOpen = false;
		menuOpen = false;
		for(int i = 0; i < incomes.size();i++){
			main.removeEntity(incomes.get(i));
			}
		incomes = new ArrayList<Text>();

		for(int i = 0; i < expensesText.size();i++){
			main.removeEntity(expensesText.get(i));
			}
		expensesText = new ArrayList<Text>();
		main.removeResourcesMenu();
		main.removeEntity(total);
		main.removeEntity(totalIncome);
	
	}
	public void open() {
		main.menuIncomeOpen = true;
		menuOpen = true;expensesText = new ArrayList<Text>();incomes = new ArrayList<Text>();
		openIncome();
		openExpenses();
		//Text totalIncome = new Text(48,incomeHUD.getHeight()+incomeHUD.getY()-64,main.gameFont,"",20,this.getVertexBufferObjectManager());
		totalIncome.setText("Total");
		totalIncome.setColor(Color.YELLOW);
		//Text total = new Text(48,incomeHUD.getHeight()+incomeHUD.getY()-32,main.smallerFont,"",100,this.getVertexBufferObjectManager());
		String res  =""+(main.getController().income-main.getController().expenses);
		Color color = Color.YELLOW;
		res = ""+(main.getController().getMonthlygain());
		total.setText("Total income: " + res);
		if(main.getController().monthlygain<0)
			total.setColor(Color.RED);
		main.inGameHUD.attachChild(total);
		main.inGameHUD.attachChild(totalIncome);
		
	}

	private void openIncome() {
		float startX = 0;
		float startY = 69+48;
		int tempY=1;
		for(int i = 0; i < main.getController().InhabitantsSize;i++){
			if(main.getController().Inhabitants[i] != 0){
				tempY++;
				Text temp = new Text(startX,startY+tempY*24,main.smallerFont,"Inhabitants level " + i + "| " + main.getController().Inhabitants[i] * 9 * (0.75+(0.25*i)),200,this.getVertexBufferObjectManager());
				temp.setColor(Color.WHITE);
				incomes.add(temp);
				main.inGameHUD.attachChild(temp);
			}
		}
	}
	
	private void openExpenses() {
		float startX = 0;
		float startY = 69+256+24;
		int tempY=1;
		Text temp = new Text(startX,startY,main.smallerFont,"",1000,this.getVertexBufferObjectManager());
		expensesText.add(temp);
		String res ="";
		res+=(String.valueOf(main.getController().getExpenses()));
//		if(main.barracks.size()!=0)
//		res+="Barrack: " + main.barracks.size()*main.EXPENSESBARRACK+"\n";
//		if(main.brickFoundrys.size()!=0)
//		res+="Brick foundry: " + main.brickFoundrys.size()*main.EXPENSESBRICKFOUNNDRY+"\n";
//		if(main.butchers.size()!=0)
//		res+="Butcher: " + main.butchers.size()*main.EXPENSESBUTCHER+"\n";
//		if(main.Farms.size()!=0)
//		res+="Farm: " + main.Farms.size()*main.EXPENSESFARM+"\n";
//		if(main.FishingHuts.size()!=0)
//		res+="Fishing hut: " + main.FishingHuts.size()*main.EXPENSESFISHINGHUT+"\n";
//		if(main.FoodMarkets.size()!=0)
//		res+="Food market: " + main.FoodMarkets.size()*main.EXPENSESFOODMARKET+"\n";
//		if(main.Fountains.size()!=0)
//		res+="Fountain: " + main.Fountains.size()*main.EXPENSESFOUNTAIN+"\n";
//		if(main.huntersLodges.size()!=0)
//		res+="Hunters lodge: " + main.huntersLodges.size()*main.EXPENSESHUNTERSLODGE+"\n";
//		if(main.mineDepositClays.size()!=0)
//		res+="Clay mine: " + main.mineDepositClays.size()*main.EXPENSESMINEDEPOSITCLAY+"\n";
//		if(main.mineDepositBronzes.size()!=0)
//		res+="Bronze mine: " + main.mineDepositBronzes.size()*main.EXPENSESMINEDEPOSITBRONZE+"\n";
//		if(main.Silos.size()!=0)
//		res+="Silo: " + main.Silos.size()*main.EXPENSESSILO+"\n";
//		if(main.skinners.size()!=0)
//		res+="Skinner: " + main.skinners.size()*main.EXPENSESSKINNER+"\n";
//		if(main.Stocks.size()!=0)
//		res+="Stock: " + main.Stocks.size()*main.EXPENSESSTOCK+"\n";
//		if(main.StoneCutters.size()!=0)
//		res+="Stone cutter " + main.StoneCutters.size()*main.EXPENSESSTONECUTTER+"\n";
//		if(main.Theatres.size()!=0)
//		res+="Theatre: " + main.Theatres.size()*main.EXPENSESTHEATRE+"\n";
//		if(main.WoodCutters.size()!=0)
//		res+="Wood cutter: " + main.WoodCutters.size()*main.EXPENSESWOODCUTTER+"\n";
//		if(main.militaryHoplite!=0)
//		res+="Hoplite: " + main.militaryHoplite*main.EXPENSESHOPLITE+"\n";
//		
		temp.setText(res);
		temp.setColor(Color.RED);
		main.inGameHUD.attachChild(temp);
	}
	
}
