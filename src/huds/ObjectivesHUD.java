package huds;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.color.Color;

import com.ligr.strategygame.MainActivity;
/**
 * Creates the HUD that shows the current objectives
 * @author LgLinuss
 *
 */
public class ObjectivesHUD extends Sprite{

	private MainActivity main;
	private Rectangle blackRect;
	private Text menuObjectivesText;
	public ObjectivesHUD(int x, int y, TextureRegion ObjectivesHUDImage, MainActivity main) {
		super(x,y,ObjectivesHUDImage,main.getVertexBufferObjectManager());
		this.main = main;
		blackRect = new Rectangle(0,0,main.CAMERA_WIDTH,main.CAMERA_HEIGHT, main.getVertexBufferObjectManager());
		blackRect.setColor(Color.BLACK);
		blackRect.setAlpha(0.9f);
		main.getInGameHUD().attachChild(blackRect);
		main.getInGameHUD().attachChild(this);
		createObjectivesText();
		main.setCurrentMenu("Objectives");
		main.getInGameHUD().registerTouchArea(this);
}

	private void createObjectivesText() {
		Text text = new Text(32,8,main.getGameFont(),"Objectives",50,this.getVertexBufferObjectManager());
		text.setColor(Color.WHITE);
		attachChild(text);
		if(main.getLevel()!=-1){ 
	menuObjectivesText = new Text(32,8 + 48, main.getSmallerFont(), "", 100,
			this.getVertexBufferObjectManager());
	menuObjectivesText.setColor(Color.WHITE);
	menuObjectivesText.setText(main.getController().getMission().createDescriptionOfMissions());
	attachChild(menuObjectivesText);
		}}
	
	public void remove(){
		if(menuObjectivesText!=null)
		main.removeEntity(menuObjectivesText);
		main.getInGameHUD().unregisterTouchArea(this);
		main.removeEntity(blackRect);
		this.main.removeEntity(this);
		main.setObjectivesHUD(null);
	}
}
