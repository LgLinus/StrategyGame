package com.ligr.strategygame.Buttons;

import org.andengine.entity.Entity;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.AnimatedSpriteObject;
import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

public class MessageChoice extends Message {
	private MessageCancelButton messageCancelButton;
	private MessageConfirmButton messageConfirmButton;

	public MessageChoice(float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, String message, int popup) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, main, message, popup);
		// TODO Auto-generated constructor stub
		main.PAUSE = true;

	}

	protected void display() {
		messageCancelButton = new MessageCancelButton(48, 240, main.getImages()
				.getMessageCancelButtonImage(),
				main.getVertexBufferObjectManager(), main);
		messageConfirmButton = new MessageConfirmButton(409, 240, main
				.getImages().getMessageConfirmButtonImage(),
				main.getVertexBufferObjectManager(), main);
		this.attachChild(messageConfirmButton);
		this.attachChild(messageCancelButton);
		this.setAlpha(0.9f);
		messageConfirmButton.setAlpha(1);
		messageCancelButton.setAlpha(1);
		main.getInGameHUD().registerTouchArea(messageCancelButton);
		main.getInGameHUD().registerTouchArea(messageConfirmButton);
		super.display();
	}

	public void remove() {
		
		main.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				main.getInGameHUD().unregisterTouchArea(messageCancelButton);
				main.getInGameHUD().unregisterTouchArea(messageConfirmButton);
				messageClass.detachSelf();
			}

		});
	}
}
