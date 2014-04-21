package com.ligr.strategygame;

import java.util.ArrayList;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;



public class Stock extends SpriteObject {
	
	private boolean pressedDown = false;
	public String globalKind = "";
	
	public Stock(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if(!load)
		MainActivity.updateWorkers(ConstantBuildings.WORKERSSTOCK,0);
		this.mY += 62;
	realwidth = this.getWidth()/3;
	realheight = this.getHeight()/3-2;
	Stockchilds = new ArrayList<StockChild>();
	stockchild = new StockChild(this.mX+realwidth ,this.mY-realheight,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
	Stockchilds.add(stockchild);stockchild.stopAnimation(0);
	MainActivity.mScene.attachChild((stockchild));
	
		stockchild = new StockChild(this.mX+realwidth /2,this.mY-realheight/2,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));

		stockchild = new StockChild((float) (this.mX+(realwidth *1.5)),this.mY-realheight/2,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));

		

		stockchild = new StockChild(this.mX,this.mY,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));

		stockchild = new StockChild(this.mX+realwidth *1,this.mY,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));

		stockchild = new StockChild(this.mX+realwidth*2,this.mY,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));


		stockchild = new StockChild(this.mX+realwidth /2,this.mY+realheight/2,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));

		stockchild = new StockChild((float) (this.mX+(realwidth *1.5)),this.mY+realheight/2,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));

		stockchild = new StockChild(this.mX+realwidth ,this.mY+realheight,MainActivity.stockImage,this.getVertexBufferObjectManager(),this);
		Stockchilds.add(stockchild);stockchild.stopAnimation(0);
		MainActivity.mScene.attachChild((stockchild));
		this.setAlpha(0);
		MainActivity.MakeToast("Type: " + MainActivity.tempGlobalKind);
	}
	public ArrayList<StockChild> Stockchilds;
	private StockChild stockchild;
	private float realwidth;
	private float realheight;
	private boolean gotSpace = true;
	public int Marble = 0;
	public int Wood = 0;
	public int Brick = 0;
	public int Bronze = 0;
	public int Skin = 0;
	public int Meat = 0;
	public int Armor = 0;
	public int Clay = 0;

	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown()){
			pressedDown = true;
		}
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false && pressedDown && MainActivity.removeBuildings==false){ pressedDown = false;
		MainActivity.addBuildingDescription();
			MainActivity.buildingDescriptionTitleString = "Stock";
			MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
			MainActivity.buildingDescriptionDetailString = "Stocks stores all of your cities resources, like wood and marble.";
			MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);
			
			for(int i = 0;i<Stockchilds.size();i++){
				String string = Stockchilds.get(i).Kind;
				int ammount = Stockchilds.get(i).Ammount;
				int maxammount = Stockchilds.get(i).maxAmmount;
				MainActivity.stockSpaceTexts.get(i).setColor(Color.BLACK);
			MainActivity.stockSpaceTexts.get(i).setText(string+Integer.toString(ammount)+("/")+(Integer.toString(maxammount)));}
		}
		else if(MainActivity.removeBuildings==true && MainActivity.boolplacebuilding == false && pressedDown){
			MainActivity.MessagePopUpChoice("Are you sure you want to remove the building?", Color.WHITE, this, "Remove", MainActivity.inGameHUD);
		}
	
	}
	return true;
}
	public void removeEntity(){
		for(int i = 0; i < this.Stockchilds.size();i++){
			this.Stockchilds.get(i).detachSelf();
		}
		MainActivity.Stocks.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSSTOCK, 0);
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);

	}
	public void save(){
		String kind="",ammount="";
		for(int i = 0; i < Stockchilds.size();i++){
			if(Stockchilds.get(i).Kind!="")
			kind+= Stockchilds.get(i).Kind+ "|";
			else
				kind+= "none|";
			ammount += Stockchilds.get(i).Ammount + "|";
		}
		Debug.e(kind + " adpallpal " + ammount);
		MainActivity.dataBase.add(name(), this.getX(), this.getY(),this.id,kind,ammount,globalKind);
	}
	private String name() {
		return "Stock";
	}
	/**
	 * Checks if there is any space in our stock
	 * @param string
	 * @return
	 */
	public boolean checkSpace(String string) {
		gotSpace = false;
		if(globalKind.contains(string) || globalKind==""){
		for(int i = 0;i<Stockchilds.size();i++)
		{
			if(Stockchilds.get(i).Kind == string && Stockchilds.get(i).Ammount!=4){
				Stockchilds.get(i).stopAnimation(Stockchilds.get(i).getCurrentTileIndex()+1);
				if(string =="Marble"){
					Marble +=1;	
				}
				else if (string == "Wood"){
					Wood +=1;
				}else if (string == "Brick"){
					Brick  	+=1;

				}
				else if (string == "Clay"){
					Clay 	+=1;

				}
				else if (string == "Bronze"){
					Brick  	+=1;
				}else if (string == "Armor"){
					Armor  	+=1;
				}else if (string == "Skin"){
					Skin  	+=1;
				}
				Stockchilds.get(i).Ammount+=1;
				gotSpace = true;
				break;
			}
			else if(Stockchilds.get(i).Kind == "" && Stockchilds.get(i).Ammount!=4){
				
				if(string =="Marble"){
					Marble +=1;	
					Stockchilds.get(i).stopAnimation(1);
					Stockchilds.get(i).Kind ="Marble";
				}
				else if (string == "Wood"){
					Wood +=1;
					Stockchilds.get(i).stopAnimation(5);
					Stockchilds.get(i).Kind ="Wood";
				}else if (string == "Brick"){
					Stockchilds.get(i).stopAnimation(9);
					Stockchilds.get(i).Kind ="Brick";
					Brick+=1;
				}else if (string == "Bronze"){
					Stockchilds.get(i).stopAnimation(9);
					Stockchilds.get(i).Kind ="Bronze";
					Brick+=1;
				}else if (string == "Skin"){
					Stockchilds.get(i).stopAnimation(14);
					Stockchilds.get(i).Kind ="Skin";
					Skin +=1;
				}else if (string == "Meat"){
					Stockchilds.get(i).stopAnimation(1);
					Stockchilds.get(i).Kind ="Meat";
					Meat  +=1;
				}else if (string == "Armor"){
					Stockchilds.get(i).stopAnimation(22);
					Stockchilds.get(i).Kind ="Armor";
					Armor  +=1;
				}else if (string == "Clay"){
					Stockchilds.get(i).stopAnimation(18);
					Stockchilds.get(i).Kind ="Clay";
					Clay   +=1;
				}
				Stockchilds.get(i).Ammount+=1;
				gotSpace = true;
				break;
			}
		}}
		return gotSpace;
	}
	
	/**
	 * 
	 * @param kind What type of resource 'wood' 'marble' etc
	 * @param ammount The ammount to be removed
	 */
	public void removeResource(String kind, int ammount){
		int Ammount = ammount;
		for(int i = 0;i<Stockchilds.size();i++){
			if(Ammount == 0){
				break;
			}
			else{
				if(Stockchilds.get(i).Kind==kind){
					for(int j = 0; j<Stockchilds.get(i).Ammount+1;j++){
						Stockchilds.get(i).stopAnimation(Stockchilds.get(i).getCurrentTileIndex()-1);
						Stockchilds.get(i).Ammount--;
						Ammount --;
						Debug.e("REMOVE CLAY");
						if(Stockchilds.get(i).Kind == "Wood" && MainActivity.Wood == 0 ){
							Stockchilds.get(i).stopAnimation(0);
							Stockchilds.get(i).Ammount = 0;
						}
						if(Stockchilds.get(i).Ammount == 0){
							Debug.e("Stock sold out");
							Stockchilds.get(i).Kind ="";
							Stockchilds.get(i).stopAnimation(0);
						}
						if(Ammount ==0){
							break;
						}
					}
				}
				
			}
		}
	}
	public void load(String[] kinds, int[] ammounts) {
		for(int i = 0; i < Stockchilds.size();i++){
			Debug.e("LOAD "+ kinds[i]);
			Stockchilds.get(i).Kind = kinds[i];
			Stockchilds.get(i).Ammount = ammounts[i];
			Stockchilds.get(i).setAnimation();
		}
	}
	
}
