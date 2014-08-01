package scenery;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

/**
 * A class representing an difference in the heigh of the map.
 * Different values on the variable choice in the constructor will give the sprite
 * a different image
 * @author LgLinuss
 *
 */
public class MountainLevel{

	private MainActivity main;
	private ITextureRegion topTexture,botTexture;
	private Sprite spriteTop,spriteBot;
	private final int DOWNRIGHT = 0,DOWNLEFT = 1,UPLEFT = 2,UPRIGHT = 3,DOWNRIGHTLEFTCORNER = 4;
	public MountainLevel(int x, int y, VertexBufferObjectManager vobj, MainActivity main, int choice){
		this.main = main;
		
		switch(choice){
	
		case DOWNRIGHT:
			topTexture = main.getImages().getMountainLevelDownRightTopImage();
			botTexture = main.getImages().getMountainLevelDownRightBotImage();
			break;
			
		case DOWNLEFT:

			topTexture = main.getImages().getMountainLevelDownRightTopImage();
			botTexture = main.getImages().getMountainLevelDownRightBotImage();
			break;
			
		case UPLEFT:
			topTexture = main.getImages().getMountainLevelDownRightTopImage();
			botTexture = main.getImages().getMountainLevelDownRightBotImage();
			break;
			
		case UPRIGHT:
			topTexture = main.getImages().getMountainLevelDownRightTopImage();
			botTexture = main.getImages().getMountainLevelDownRightBotImage();
			break;
		case DOWNRIGHTLEFTCORNER:
			topTexture = main.getImages().getMountainLevelDownRightLeftCornerTopImage();
			botTexture = main.getImages().getMountainLevelDownRightLeftCornerBotImage();
			y-=29;  	   
			break;
	
		}
		spriteTop = new Sprite(x,y,topTexture,vobj);
		spriteBot = new Sprite(x,y+64,botTexture,vobj);
		main.getScene().attachChild(spriteTop);
		main.getScene().attachChild(spriteBot);
	}
}
