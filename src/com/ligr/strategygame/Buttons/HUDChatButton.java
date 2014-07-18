package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;
/**
 * Button responsible of displaying the chat history when pressed. Pressing it again will remove the chat history
 * @author LgLinuss
 *
 */
public class HUDChatButton extends Sprite {
	private MainActivity main;

	public HUDChatButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		main.getInGameHUD().registerTouchArea(this);
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if (pSceneTouchEvent.isActionUp()) {
			if (!thisMenu())
				showChat();
			else{
				closeChat();
				resetCurrentMenu();}
		}
		return true;
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if (thisMenu())
			this.setAlpha(0.5f);
		else if (this.getAlpha() != 1.0f) {
			closeChat();
			this.setAlpha(1.0f);
		}
	}

	/**
	 * Displays the chat history
	 */
	public void showChat() {
		
		main.setCurrentMenu("chat");
		Text tempText;
		String res = "";
		int yPos = 128;
		for (int i = 0; i < MainActivity.messageHistory.length; i++) {
			res += MainActivity.messageHistory[i];
			tempText = new Text(128, yPos, MainActivity.gameFont,
					main.messageHistory[i], 300,
					this.getVertexBufferObjectManager());
			main.getInGameHUD().attachChild(tempText);
			main.chatHistoryTexts.add(tempText);
			yPos += tempText.getHeight();
			res = "";
		}
	}

	/**
	 * Close the chat
	 */
	public void closeChat() {
		for (int i = 0; i < MainActivity.chatHistoryTexts.size(); i++) {
			if (MainActivity.chatHistoryTexts.get(i) != null)
				main.removeEntity(MainActivity.chatHistoryTexts.get(i));
		}
		main.chatHistoryTexts = new ArrayList<Text>();
	}

	private void resetCurrentMenu(){
		this.main.setCurrentMenu("");
	}
	
	private boolean thisMenu() {
		return main.getCurrentMenu().equals("chat");
	}
}
