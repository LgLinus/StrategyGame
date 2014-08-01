package com.ligr.strategygame.Buttons;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;

public class Message extends Sprite {
	public static final int POPUPNORMAL = 0, NOPOPUPNORMAL = 1, POPUPNOREMOVE = 2, NOPOPUPREMOVE = 3;
	protected MainActivity main;
	protected String message;
	protected Text messageText;
	private float startX,startY,alphaSpeed;
	protected int popUp;
	Message messageClass;
	float speed = 0.01f;
	TimerHandler timer;
	 float speedY;
	private MessageOkButton messageOkButton;
	 public Message(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main, String message, int popUp) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		this.main = main;
		main.PAUSE = true;
		this.message = message;
		this.messageClass = this;
		this.popUp = popUp;
		startX = pX;
		startY = pY;
		if((popUp== 0) || popUp==2){
			popup();
		}else{
			display();
		}
		
	}
	public int getPopUp(){
		return popUp;
	}
	private void popup() {
		this.setScaleX(0.1f);
		this.setScaleY(0.1f);
		this.setAlpha(0.1f);
		
		final float speedYIncrease = calcAcceleratedSpeed((main.CAMERA_HEIGHT-128-this.getY()),0.9f,0.05f,0.01f,+2);

		this.setY(main.CAMERA_HEIGHT-128);
		final float alphaIncrease = calcAcceleratedSpeed((0.9f),0.9f,0.05f,0.01f,+2);
		speedY = speedYIncrease;
		main.getEngine().registerUpdateHandler(
				timer = new TimerHandler(0.025f,
						new ITimerCallback() {
					
							@Override
							public void onTimePassed(
									final TimerHandler pTimerHandler) {
								messageClass.setScaleX(messageClass.getScaleX()+speed);
								messageClass.setScaleY(messageClass.getScaleY()+speed);
								messageClass.setY(messageClass.getY()-speedY);
								speed+=0.05f;
								speedY+=speedYIncrease;
								messageClass.setAlpha(messageClass.getAlpha()+alphaSpeed);
								alphaSpeed+=alphaIncrease;
								if(messageClass.getScaleX()>=1){
									messageClass.setScale(1,1);
									messageClass.setX(startX);
									messageClass.setAlpha(0.9f);
									display();
								}else{
									timer.reset();
								}
								
							
	}
				}));
	}

	private float calcAcceleratedSpeed(float f, float g, float h, float i, int constant){
		return ((f)/ ((2f*((caclSteps(g,h,i)+constant)^2))));
	}
	/**
	 * Start goal, end goal, acc speed, start speed
	 */
	private int caclSteps(float g, float h, float i) {
		int steps = 0;
		float currSpeed = i;
		float f = 0;
		while(f<g){
			f +=currSpeed;
			currSpeed+=h;
			steps++;
			
		}
		return steps;
	}

	protected void display(){
		messageText = new Text(60 + 8, 70 + 24, main.getSmallerFont(), "", 5000,
				main.getVertexBufferObjectManager());
		messageClass.attachChild(messageText);
		messageText.setText(message);
		messageText.setColor(1f, 1f, 1f);

		if(!(this instanceof MessageChoice)){
			messageOkButton = new MessageOkButton(256,
					240, main.getImages().getMessageOkButtonImage(),
					main.getVertexBufferObjectManager(), main);
			this.attachChild(messageOkButton);
			main.getInGameHUD().registerTouchArea(messageOkButton);
		}
	}
public void remove() {
		
		main.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				messageClass.detachSelf();
				if(messageOkButton!=null){
					main.getInGameHUD().unregisterTouchArea(messageOkButton);
					messageOkButton = null;}
			}

		});
	}
				}