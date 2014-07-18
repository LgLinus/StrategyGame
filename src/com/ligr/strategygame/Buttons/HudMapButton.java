package com.ligr.strategygame.Buttons;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.Map;
import com.ligr.strategygame.SpriteObject;
/**
 * Button that displays our map when pressed
 * @author LgLinuss
 *
 */
public class HudMapButton extends Sprite{

	private MainActivity main;
	
	public HudMapButton(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,MainActivity main) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.main = main;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if(pSceneTouchEvent.isActionUp()){
			showMenu();
		}
		return true;
	}

	/**
	 * Show the map
	 */
	public void showMenu(){
		if(main.menuMap==null){
		main.menuMap = new Map(0, 0, main.getImages().getMenuMapImage(),
				this.getVertexBufferObjectManager(),main.getController());
		main.menuMapHUD = new Sprite(0 + main.menuMap.getWidth(), 0,
				main.getImages().getMenuMapHUDImage(),
				this.getVertexBufferObjectManager());
		main.inGameHUD.attachChild(main.menuMap);
		main.inGameHUD.attachChild(main.menuMapHUD);
		for (int i = 0; i < main.cityMessageSize; i++) {
			Text tempText = new Text(main.menuMap.getWidth() + 4, 354 +  104 +  i * 25,
					main.smallFont, "", 200, this.getVertexBufferObjectManager());
			main.cityMessages.add(tempText);
			main.inGameHUD.attachChild(main.cityMessages.get(i));
		}
		for(int i = 0; i < main.getController().getCityIcons().size();i++){
			main.inGameHUD.attachChild(main.getController().getCityIcons().get(i));
			main.getController().getCityIcons().get(i).setText();
		}
		main.getController().addMapTouchAreas();
		main.removeMenu();
		main.removeHudTouchAreas();
		} 
		}


}
