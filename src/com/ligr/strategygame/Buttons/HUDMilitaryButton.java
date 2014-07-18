package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

public class HUDMilitaryButton extends Sprite {

	private boolean chatShowed = false;
	private MainActivity main;

	public HUDMilitaryButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
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

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if (pSceneTouchEvent.isActionUp()) {
			if (!thisMenu())
				showChat();
			else {
				closeChat();
				resetCurrentMenu();
			}
		}
		return true;
	}

	public void showChat() {
		main.setCurrentMenu("military");
	}

	public void closeChat() {
	}

	private boolean thisMenu() {
		return main.getCurrentMenu().equals("military");
	}

	private void resetCurrentMenu() {
		main.setCurrentMenu("");
	}
}
