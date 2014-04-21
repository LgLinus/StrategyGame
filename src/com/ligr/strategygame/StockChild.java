package com.ligr.strategygame;

import java.util.ArrayList;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;



public class StockChild extends AnimatedSprite {
	private Stock Parent;
	public String Kind ="";
	public int Ammount = 0;
	public int maxAmmount;
	public StockChild(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,Stock parent) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		Parent = parent;
		maxAmmount = 4;
	}
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			
		if(MainActivity.boolplacebuilding == false){
			MainActivity.addBuildingDescription();
			MainActivity.buildingDescriptionTitleString = "Stock";
			MainActivity.buildingDescriptionTitle.setText(MainActivity.buildingDescriptionTitleString);
			MainActivity.buildingDescriptionDetailString = "Stocks stores all of your cities resources, like wood and marble.";
			MainActivity.buildingDescriptionDetail.setText(MainActivity.buildingDescriptionDetailString);
		}
		}
		return true;
	
	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);

	}
	public void setAnimation() {
		if(Kind.contains("Marble")){
			this.stopAnimation(1+Ammount-1);
			Kind ="Marble";}
		else if(Kind.contains("Wood")){
			this.stopAnimation(5+Ammount-1);
			Kind ="Wood";}
		else if(Kind.contains("Brick")){
			this.stopAnimation(9+Ammount-1);
			Kind ="Brick";}
		else if(Kind.contains("Skin")){
			this.stopAnimation(14+Ammount-1);
			Kind ="Skin";}
		else if(Kind.contains("Meat")){
			this.stopAnimation(1+Ammount-1);
			Kind ="Meat";}
		else if(Kind.contains("Armor")){
			this.stopAnimation(22+Ammount-1);
			Kind ="Armor";}
		else if(Kind.contains("Clay")){
			this.stopAnimation(18+Ammount-1);
			Kind ="Clay";}
		else{
			this.stopAnimation(0);
			Kind ="";}
	}

	
}
