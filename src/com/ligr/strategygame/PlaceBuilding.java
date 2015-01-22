package com.ligr.strategygame;

import java.util.ArrayList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.primitive.Ellipse;
import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
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
		createEllipseRange();
		
		building = main.getCurrentBuilding();
		createPolygon();
	}

	private void createEllipseRange() {
		if(MainActivity.currentBuilding == ConstantBuildings.TITLESTONECUTTER)
			RANGE = ConstantBuildings.RANGESTONECUTTER;
		else if(MainActivity.currentBuilding == "Wood Cutter")
			RANGE = ConstantBuildings.RANGEWOODCUTTER;
		range = new Ellipse(this.mX+(this.getWidth()),this.mY+this.getHeight(),RANGE,RANGE/2, this.getVertexBufferObjectManager());
		range.setColor(Color.BLACK);
		MainActivity.mScene.attachChild(range);
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
		//if(currentBuilding == ConstantBuildings.TITLESTONECUTTER)
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
					if(getBounds().collidesWith(MainActivity.asObjects.get(i).getBounds())){
						Debug.e("Colliede");
						ret =false;
						break;
					}
				}
				for(int i = 0; i < MainActivity.sObjects.size();i++){
					if(getBounds().collidesWith(MainActivity.sObjects.get(i).getBounds())){
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

	public boolean collides() {
		
		boolean collide = false;
		ArrayList<AnimatedSpriteObject> asSprites = main.getAsObjects();
		ArrayList<SpriteObject> sSprites = main.getSObjects();
		for(int i = 0; i < asSprites.size();i++){
			if(polygon.collidesWith(asSprites.get(i).getBounds())){
				Debug.e("collision2");
				collide = true;
				break;}
		}
		for(int i = 0; i < sSprites.size();i++){
			if(polygon.collidesWith(sSprites.get(i).getBounds())){
				Debug.e("collision2");
				collide = true;
				break;}
		}
		return collide;
	}
}
