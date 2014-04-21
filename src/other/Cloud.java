package other;

import java.util.Random;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
/**
 * 
 * @author LgLinuss
 *Represents a smoke cloud from factories
 */
public class Cloud extends AnimatedSprite {

	public long speed = 0;
	public int steps = 0;
	
	public Cloud(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		// < snabbare
		this.speed = 200;
		this.animate(speed);
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);

		if(this.getCurrentTileIndex() == 7){
			this.stopAnimation();
			steps--;
			if(steps < 1){
				this.animate(speed);
			}
		}
		else
			steps = 20;
	
	}
}
