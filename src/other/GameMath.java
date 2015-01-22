package other;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

public class GameMath {
	/**
	 * Calculate the range between two objects
	 * @param ent object 1
	 * @param ent2 object 2
	 * @return range
	 */
	public static int calculateRange(Sprite ent, Sprite ent2){
		int range = -1;
		int x1,x2,y1,y2,xdist,ydist;
		x1 = (int)(ent.getX()+ (ent.getWidth()/2));
		x2 = (int)(ent2.getX()+ (ent2.getWidth()/2));
		y1 = (int)(ent.getY()+ (ent.getHeight()/2));
		y2 = (int)(ent2.getY()+ (ent2.getHeight()/2));
		xdist = x1-x2;
		ydist = y1-y2;
		range = (int)Math.sqrt((xdist*xdist) + (ydist*ydist));
		return range;
	}
	
	/**
	 * Calculate the range between two objects
	 * @param ent object 1
	 * @param ent2 object 2
	 * @param mx x-factor
	 * @param my y-factor
	 * @return range
	 */
	public static int calculateRange(Sprite ent, Sprite ent2, int mx, int my){
		int range = -1;
		int x1,x2,y1,y2,xdist,ydist;
		x1 = (int)(ent.getX()+ (ent.getWidth()/2));
		x2 = (int)(ent2.getX()+ (ent2.getWidth()/2));
		y1 = (int)(ent.getY()+ (ent.getHeight()/2));
		y2 = (int)(ent2.getY()+ (ent2.getHeight()/2));
		xdist = x1-x2;
		ydist = y1-y2;
		range = (int)Math.sqrt((((xdist*xdist*mx*mx)) + ((ydist*ydist)*my*my)));
		return range;
	}
}
