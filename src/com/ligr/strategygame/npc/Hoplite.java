package com.ligr.strategygame.npc;

import java.util.Random;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;

public class Hoplite extends AnimatedSprite {

	/*
	 * Row 1 = 0-7 = Right
	 * Row 2 = 0-7 = Up
	 * Row 3 = 0-7 =  UpRight
	 * Row 4 = 0-7 = Upleft
	 * Row 5 = 0-7 = Down
	 * Row 6 = 0-7 = DownRight
	 * Row7 = 0-7 = DownLeft
	 * Row 8 = 0-7 = Left
	 */
	double range = 196;
	String direction = "";
	float movesteps,startx,starty;
	private int ROW = 0;
	private long frameSpeed = 150;
	public Hoplite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		startx = this.mX;
		starty = this.mY;
		
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
	//if(MainActivity.PAUSE == false){
	if(direction =="Left")
		moveLeft();
	else if (direction=="Right")
		moveRight();
	else if (direction=="Up")
		moveUp();
	else if (direction=="Down")
		moveDown();
	else if (direction=="RightUp")
		moveRightUp();
	else if (direction=="RightDown")
		moveRightDown();
	else if (direction=="LeftDown")
		moveLeftDown();
	else if (direction=="LeftUp")
		moveLeftUp();
	else
		idle();
		
	}
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
				
			}
		}
		else{
			changeDirection();
		}
	}
	public void moveLeftDown(){
		if(this.mX<(startx+range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mX-=1;
				this.mY+=1;
				movesteps -=1;
				
			}
		}
		else{
			changeDirection();
		}
	}
	public void moveLeftUp(){
		if(this.mX<(startx+range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mX-=1;
				this.mY-=1;
				movesteps -=1;
			}
		}
		else{
			changeDirection();
		}
	}
	public void moveRightDown(){
		if(this.mX<(startx+range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mX+=1;
				this.mY+=1;
				movesteps -=1;
				
			}
		}
		else{
			changeDirection();
		}
	}
	public void moveRightUp(){
		if(this.mX<(startx+range)){
			if(movesteps<0){
				changeDirection();
			}
			else{
				this.mX+=1;
				this.mY-=1;
				movesteps -=1;
				
			}
		}
		else{
			changeDirection();
		}
	}
	public void changeDirection(){
		Random rand = new Random();
		int randint = rand.nextInt(25);
		if(randint ==0){
			direction = "Left";
			ROW = 7;
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7, true);
			movesteps = rand.nextInt(50)+20;
		}
		else if(randint ==1){
			direction = "Right";
			ROW = 0;
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7, true);
			movesteps = rand.nextInt(50)+20;
		}
		else if(randint == 2){
			direction = "Up";
			ROW = 1;
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7,true);
			movesteps = rand.nextInt(50)+20;
		}
		else if(randint ==3){
			direction = "Down";
			ROW = 4; 	
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7, true);
			movesteps = rand.nextInt(50)+20;
		}else if(randint ==4){
			direction = "RightUp";
			ROW = 2; 	
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7, true);
			movesteps = rand.nextInt(50)+20;
		}else if(randint ==5){
			direction = "RightDown";
			ROW = 5; 	
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7, true);
			movesteps = rand.nextInt(50)+20;
		}else if(randint ==6){
			direction = "LeftUp";
			ROW = 3; 	
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7, true);
			movesteps = rand.nextInt(50)+20;
		}else if(randint ==7){
			direction = "LeftDown";
			ROW = 6; 	
			this.animate(new long[]{frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed,frameSpeed}, ROW*8, ROW*8 + 7, true);
			movesteps = rand.nextInt(50)+20;
		}
		else{
			direction="";
			this.stopAnimation(ROW*8);
			movesteps = rand.nextInt(1);
		}
	}
}
