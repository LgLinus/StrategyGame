package com.ligr.strategygame;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.buildings.BronzeMine;
import com.ligr.strategygame.buildings.FishingHut;

public class NPCBronzeMiner extends AnimatedSprite{

	BronzeMine parent;
//	MineDepositBronze deposit;
	private boolean fishing,idle,delivering,movinghome,moving;
	private Entity target;
	private int timer = 40,direction,ammount=0,speed = 3;
	private MainActivity main;
	public NPCBronzeMiner(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, FishingHut parent) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		// TODO Auto-generated constructor stub
	}


	
	// Move the miner
	public void move(){
		if(moving){
			if(movinghome){
				target = parent;	
				
			}
//			else if(!movinghome){
//				if(deposit==null){
//					deposit = main.getMineDepositBronzes().get(0);
//				}
//				target = deposit;
				//direction = Math.atan2((this.mX-deposit.mX), (this.mY-deposit.mY));
//			}
		}
		if(fishing){
			timer --;
			if(timer<=0){
				ammount = 4;
			}
		}
		else if(delivering){
			timer --;
			if(timer<=0){
				if(parent.maxammount>=parent.ammount+1){
				parent.ammount+=1;}
				else{
					for(int i = parent.ammount;i<parent.maxammount;i++){
					parent.ammount+=1;
					ammount-=1;
					idle = true;
				}
			}
		}
		else if(idle){
			timer--;
		}
	}
}
}