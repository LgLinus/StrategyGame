package huds;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;

public class MoveInSprite extends Sprite {
	public static final int POPUPNORMAL = 0, NOPOPUPNORMAL = 1,
			POPUPNOREMOVE = 2, NOPOPUPREMOVE = 3;
	protected MainActivity main;
	protected String message;
	protected Text messageText;
	protected float gotoX, gotoY, alphaSpeed;
	protected boolean scaleX = false, scaleY = false, raiseAlpha;;
	protected float speedYIncrease = 0;
	protected int popUp;
	MoveInSprite moveInSpriteClass;
	float speed = 0.01f;
	TimerHandler timer;
	float speedY;
	private float speedXIncrease;

	public MoveInSprite(float pX, float pY, TextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, float gotoX, float gotoY, boolean scaleX,
			boolean scaleY, boolean raiseAlpha, int popUp) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		main.inGameHUD.attachChild(this);
		this.main = main;
		this.moveInSpriteClass = this;
		this.popUp = popUp;
		this.gotoX = gotoX;
		this.gotoY = gotoY;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.raiseAlpha = raiseAlpha;
		Debug.e("Menu created");
		if ((popUp == 0) || popUp == 2) {
			popup();
		} else {
			display();
		}

	}

	public int getPopUp() {
		return popUp;
	}

	private void popup() {
		if (scaleX)
			this.setScaleX(0.1f);
		if (scaleY)
			this.setScaleY(0.1f);
		if (raiseAlpha)
			this.setAlpha(0.1f);
		int steps = caclSteps(this.gotoY- this.getY(), 5, 5);
		if (gotoY != 0) {
			speedYIncrease = calcAcceleratedSpeed(this.gotoY- this.getY(),
					this.gotoY- this.getY(), 5, 5, +2);
			speedY = speedYIncrease;
		}
		if (gotoX != 0) {
			speedXIncrease = calcAcceleratedSpeed(this.getX() - this.gotoX,
					this.getX() - this.gotoX, 5, 5, +2);
		}
		final float alphaIncrease = calcAcceleratedSpeed((0.9f), 0.9f, 0.05f,
				0.01f, +2);
		main.getEngine().registerUpdateHandler(
				timer = new TimerHandler(0.010f, new ITimerCallback() {

					private float speedX;

					@Override
					public void onTimePassed(final TimerHandler pTimerHandler) {
						if (scaleX) {
							moveInSpriteClass.setScaleX(moveInSpriteClass.getScaleX()
									+ speed);
						}
						if (scaleY) {
							moveInSpriteClass.setScaleY(moveInSpriteClass.getScaleY()
									+ speed);
						}
						if (gotoY != 0) {
							speedY += speedYIncrease;
							moveInSpriteClass.setY(moveInSpriteClass.getY() + speedY);
						}
						if (gotoX != 0) {
							speedX += speedXIncrease;
							moveInSpriteClass.setX(moveInSpriteClass.getX() - speedX);
						}
						speed += 0.05f;
						if (raiseAlpha) {
							moveInSpriteClass.setAlpha(moveInSpriteClass.getAlpha()
									+ alphaSpeed);
							alphaSpeed += alphaIncrease;
						}
						if ((scaleX &&moveInSpriteClass.getScaleX() >= 1)||(scaleY && moveInSpriteClass.getScaleY()>=1) ||(gotoY!=0 && gotoY<getY()) || (gotoX != 0 && gotoX<getX())) {
				
							display();
						} else {
							timer.reset();
						}

					}
				}));
	}

	private float calcAcceleratedSpeed(float f, float g, float h, float i,
			int constant) {
		return ((f) / ((2f * ((caclSteps(g, h, i) + constant) ^ 2))));
	}

	/**
	 * Start goal, end goal, acc speed, start speed
	 */
	private int caclSteps(float g, float h, float i) {
		int steps = 0;
		float currSpeed = i;
		float f = 0;
		while (f < g) {
			f += currSpeed;
			currSpeed += h;
			steps++;

		}
		return steps;
	}

	protected void display() {
		moveInSpriteClass.setScale(1, 1);
		if (gotoX != 0)
			moveInSpriteClass.setX(gotoX);
		if (gotoY != 0)
			moveInSpriteClass.setY(gotoY);
		if (raiseAlpha)
			moveInSpriteClass.setAlpha(0.9f);
	}

	public void remove() {
		Debug.e("#REMOVE RESOURCES");
		main.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				moveInSpriteClass.detachSelf();

			}

		});
	}
}