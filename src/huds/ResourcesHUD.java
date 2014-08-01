package huds;

import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

public class ResourcesHUD extends MoveInSprite {
	public ResourcesHUD(float pX, float pY, TextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			MainActivity main, float gotoX, float gotoY, boolean scaleX,
			boolean scaleY, boolean raiseAlpha, int popUp) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main,gotoX,gotoY,scaleX,scaleY,raiseAlpha,popUp);
	}
	

	protected void display() {
		super.display();
		main.inGameHUD.getHudResources().display();
	}

	public void remove() {
		
		main.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				
				moveInSpriteClass.detachSelf();
			}

		});
	}
}
