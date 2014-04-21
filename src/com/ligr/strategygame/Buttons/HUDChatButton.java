package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

public class HUDChatButton extends Sprite{

	private boolean chatShowed = false;
	public HUDChatButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if(pSceneTouchEvent.isActionUp()){
			if(!chatShowed)
			showChat();
			else
				closeChat();
		}
		return true;
		}
	
	public void showChat(){
		chatShowed = true;
		Text tempText;	
		String res = "";
		int yPos = 128;
		for(int i  = 0; i < MainActivity.messageHistory.length;i++){
			res += MainActivity.messageHistory[i];
			tempText = new Text(128,yPos,MainActivity.gameFont,MainActivity.messageHistory[i],300,this.getVertexBufferObjectManager());
			MainActivity.inGameHUD.attachChild(tempText);
			MainActivity.chatHistoryTexts.add(tempText);
			yPos+=tempText.getHeight();
			res = "";
		}
}
	public void closeChat(){
		chatShowed = false;
		for(int i = 0; i < MainActivity.chatHistoryTexts.size();i++){
			if(MainActivity.chatHistoryTexts.get(i)!=null)
			MainActivity.removeEntity(MainActivity.chatHistoryTexts.get(i));
		}
		MainActivity.chatHistoryTexts = new ArrayList<Text>();
	}
	
	
}
