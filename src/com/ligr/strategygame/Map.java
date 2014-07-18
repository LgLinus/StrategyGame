package com.ligr.strategygame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import other.Controller;

/**
 * The map for the game
 * @author LgLinuss
 *
 */
public class Map extends Sprite{

boolean pressedDown = false;

	Controller controller;
	public Map(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Controller controller) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.controller = controller;}

	public void cancel(){
	controller.getActivity().leaveMap();
	}
//	@Override
//	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
//
//		if(pSceneTouchEvent.isActionUp()){
//		}
//		return false;
//	}
}