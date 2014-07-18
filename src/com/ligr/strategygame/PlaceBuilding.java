package com.ligr.strategygame;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.primitive.Ellipse;
import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.shape.Shape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.ligr.strategygame.constants.ConstantBuildings;

/**
 * Class responsible of creating a ghost image of the given building.(So you can see how much space the building takes up)
 * @author LgLinuss
 *
 */
public class PlaceBuilding extends Sprite {
	public boolean remove = false;
	public Ellipse range;
	public int RANGE;
	private Polygon polygon;
	private String building;
	private MainActivity main;
	private boolean ret = true;
	public PlaceBuilding(float pX, float pY,
			ITextureRegion pITextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main) {
		super(pX, pY, pITextureRegion, pVertexBufferObjectManager);
		RANGE = 0;
		this.main = main;
		if(MainActivity.currentBuilding == "Stone Cutter")
			RANGE = ConstantBuildings.RANGESTONECUTTER;
		range = new Ellipse(this.mX+(this.getWidth()),this.mY+this.getHeight(),RANGE,RANGE, this.getVertexBufferObjectManager());
		range.setColor(Color.BLACK);
		MainActivity.mScene.attachChild(range);
		building = main.getCurrentBuilding();
		createPolygon();
	}

	public void updatePolygon(){
		polygon.setX(this.mX);
		polygon.setY(this.mY);
	}
	
	private void createPolygon() {
		float[] xVertice = {0,47,96,47};
		float[] yVertice = {23,0,23,47};
		polygon = new Polygon(this.mX, this.mY, xVertice, yVertice, this.getVertexBufferObjectManager());
		polygon.setColor(0.5f, 1f, 0.3f);
		MainActivity.mScene.attachChild(polygon);
	}

	public static String currentBuilding = "";
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
	super.onManagedUpdate(pSecondsElapsed);
		currentBuilding = MainActivity.currentBuilding;
		//if(currentBuilding == "Stone Cutter")
			//range.setAlpha(1);
		range.setX(this.mX+this.getWidth()/2);
		range.setY(this.mY+this.getHeight()/2);
		
	}
	public boolean gotSpace() {
		final PhysicsHandler physicsHandler = new PhysicsHandler(polygon);
		polygon.registerUpdateHandler(physicsHandler);
		main.getScene().registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void reset() { }

			@Override
			public void onUpdate(final float pSecondsElapsed) {
				for(int i = 0; i < MainActivity.asObjects.size();i++){
					if(getBounds().collidesWith(MainActivity.asObjects.get(i).getPolygon())){
						Debug.e("Colliede");
						ret =false;
						break;
					}
				}
				for(int i = 0; i < MainActivity.sObjects.size();i++){
					if(getBounds().collidesWith(MainActivity.sObjects.get(i).getPolygon())){
						ret= false;
						break;
					}
				}
			}
		});
		return ret;
	}
	public void remove(){
		if(remove){
			main.removeEntity(range);
			main.removeEntity(this);
			range.detachSelf();
			}
	}

	/**
	 * Return our polygon
	 * @return
	 */
	public Polygon getBounds(){
		return this.polygon;
	}
}
