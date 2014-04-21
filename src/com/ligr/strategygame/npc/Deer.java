package com.ligr.strategygame.npc;

import java.util.Random;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

public class Deer extends AnimatedSprite {

	double range = 196;
	String direction = "";
	float movesteps,startx,starty;
	
	public Deer(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		startx = this.mX;
		starty = this.mY;
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
	if(MainActivity.PAUSE == false){
	if(direction =="Left")
		moveLeft();
	else if (direction=="Right")
		moveRight();
	else if (direction=="Up")
		moveUp();
	else if (direction=="Down")
		moveDown();
	else
		idle();
		
	}}
	public void idle(){
		movesteps--;
		if(movesteps <= 0)
			changeDirection();
	}
	public void moveLeft(){
		if(this.mX>(startx-range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mX-=1;
				movesteps -=1;
				this.stopAnimation(2);
			}
		}
		else{
			changeDirection();
		}
	}
	public void moveUp(){
		if(this.mY>(starty-range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mY-=1;
				movesteps -=1;
				this.stopAnimation(1);
			}
		}
		else{
			changeDirection();
		}
	}
	public void moveDown(){
		if(this.mY<(starty+range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mY+=1;
				movesteps -=1;
				this.stopAnimation(0);
			}
		}
		else{
			changeDirection();
		}
	}
	public void moveRight(){
		if(this.mX<(startx+range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mX+=1;
				movesteps -=1;
				this.stopAnimation(3);
			}
		}
		else{
			changeDirection();
		}
	}
	public void changeDirection(){
		Random rand = new Random();
		int randint = rand.nextInt(15);
		if(randint ==0){
			direction = "Left";
			movesteps = rand.nextInt(50)+20;
		}
		else if(randint ==1){
			direction = "Right";
			movesteps = rand.nextInt(50)+20;
		}
		else if(randint == 2){
			direction = "Up";
			movesteps = rand.nextInt(50)+20;
		}
		else if(randint ==3){
			direction = "Down";
			movesteps = rand.nextInt(50)+20;
		}
		else{
			direction="";
			movesteps = rand.nextInt(150)+10;
		}
	}
}
