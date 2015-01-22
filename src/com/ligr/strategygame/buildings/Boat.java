package com.ligr.strategygame.buildings;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import other.GameMath;

import com.ligr.strategygame.FishSpot;
import com.ligr.strategygame.MainActivity;

public class Boat extends AnimatedSprite{

	FishingHut parent;
	FishSpot spot;
	private boolean fishing,idle,delivering,movinghome,moving;
	private Sprite target;
	private int timer = 1,ammount=0,speed = 1;
	float ty=0,tx=0,delta_x=0,delta_y=0;
	private double angle=0;
	private String direction;
	private int maxammount= 100;
	private MainActivity main;
	private int timer2 = 40;
	public Boat(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, FishingHut sparent, MainActivity main) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		this.moving = true;
		this.direction="left";
		this.parent = sparent;
		this.spot = (FishSpot) findSpot();
		this.target = spot;
		this.main = main;
	}

	/** Tells the fishing hut it doesn't have a boat anymore*/
	public void destroy(String str){
		parent.setGotBoat(false);
		spot.boats--;
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
			move();
		}
	// Move the boat
	public void move(){	
		timer--;
		if(moving){
			movementBoat();
			if(movinghome){
				
			}
			else if(!movinghome){
				if(spot==null){
				}
				//direction = Math.atan2((this.mX-spot.mX), (this.mY-spot.mY));
			}
		}
		else if(fishing){
			if(timer<=0){
				ammount = maxammount;
				target = parent;
				moving = true;
				fishing = false;
			}
		}
		else if(delivering){
			if(timer<=0){
				if(parent.maxammount>=parent.ammount+200){
				parent.ammount+=200;
				delivering = false;
				moving = true;
				this.ammount=0;
				target = spot;}
				else{
					for(int i = parent.ammount;i<parent.maxammount;i++){
					parent.ammount+=1;
					this.ammount-=1;
				}
			}
		}
		else if(idle){
			timer--;
		}
	}
}

	private void changeImage() {
		// TODO Auto-generated method stub
		// Going right
		if(direction =="up"){
			this.stopAnimation(3);
		}
		else if (direction =="down"){
			this.stopAnimation(1);
		}
		else if (direction =="left"){
			this.stopAnimation(0);
		}
		else if (direction =="right"){
			this.stopAnimation(2);
		}
		else if (direction =="downleft"){
			this.stopAnimation(4);
		}
		else if (direction =="downright"){
			this.stopAnimation(5);
		}
		else if (direction =="upleft"){
			this.stopAnimation(6);
		}
		else{
			this.stopAnimation(7);
		}
		
	}

public void movementBoat(){
	tx = target.getX()+target.getWidth()/2;
	ty = target.getY()+target.getHeight()/2;
	delta_x = this.mX-tx;
	delta_y = this.mY-ty;
	if(timer<1)
		{angle = Math.atan2(delta_y, delta_x);
	if (Math.sin(angle)>= -0.383 && Math.sin(angle) <= 0.383 && Math.cos(angle)>= 0.9238){
		direction = "right";
		angle = Math.PI*2;
		
	}
	else if (Math.sin(angle)>= 0.383 && Math.sin(angle) <= 0.927 && Math.cos(angle)<=0.92387 && Math.cos(angle)>=0.3746){
		direction = "upright";
		angle = Math.PI/4;
		
	}else if (Math.cos(angle)<= 0.375 && Math.cos(angle) >= -0.3907 && Math.sin(angle)>=0.9238){
		direction = "up";
		angle = Math.PI/2;
		
	}else if (Math.cos(angle)<= -0.391 && Math.cos(angle) >= -0.927 && Math.sin(angle) <= 0.9205 && Math.sin(angle)>= 0.3746){
		direction = "upleft";
	    angle = (Math.PI)-(Math.PI/4);
		
	}else if (Math.sin(angle)<= 0.375 && Math.sin(angle) >= -.391 && Math.cos(angle)<=-0.9238){
		direction = "left";
		angle = Math.PI;
		
	}else if (Math.sin(angle)<= -.391 && Math.sin(angle) >= -0.927 && Math.cos(angle)>=-0.9205 && Math.cos(angle)<=-0.3746){
		direction = "downleft";
		angle = Math.PI+(Math.PI/4);
		
	}else if (Math.cos(angle)>= -0.3746 && Math.cos(angle) <= 0.391 && Math.sin(angle)<=-0.9272){
		direction = "down";//
		angle = (Math.PI*3)/2;
		
	}else if(timer<1){
		direction ="downright";
		
		angle = Math.PI+((Math.PI*3)/4);
	}
	timer = 250;}
	if(GameMath.calculateRange(this, target)<48){
		checkCollision();
	}
	this.setY((float) (mY-(Math.sin(angle)*speed)));
	this.setX((float) (mX-(Math.cos(angle)*speed)));
	changeImage();
}

private void checkCollision() {
	// TODO Auto-generated method stub
	timer2 --;
	if(timer2<1){
	if(ammount == maxammount){
		delivering = true;
		moving = false;
		timer = 200;
		timer2 = 40;
		Debug.e("start deliver");
}
	else if(ammount != maxammount){
		moving = false;
		fishing = true;
		timer = 200;
		timer2 = 40;
		Debug.e("Start fishign");
		
	}
	}}
// Finds a fishing spot for the boat
private Sprite findSpot(){
	FishSpot res;
	double distance = 100000;
	int pos = -1;
	for (int i =0; i<main.fishspots.size();i++){
		if(main.fishspots.get(i).gotSpace())
			if(GameMath.calculateRange(this, main.fishspots.get(i))<distance){
					pos = i;}
		
	}
	if(pos!=-1)
		{ res = main.fishspots.get(pos);
		res.boats++;
		return res;}
	else
	return parent;
}
}
