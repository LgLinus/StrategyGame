package other;

import org.andengine.entity.primitive.Ellipse;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Class representing an ellipse, given a unique ID for identification
 * @author LgLinuss
 *
 */
public class EllipseCustom extends Ellipse {

	private int ID = 0;
	public static int REMOVEBACK = 0;
	
	public EllipseCustom(float pX, float pY, float pRadiusA, float pRadiusB,
			VertexBufferObjectManager pVertexBufferObjectManager, int ID) {
		super(pX, pY, pRadiusA, pRadiusB, pVertexBufferObjectManager);
		
		this.ID = ID;
	}
	
	public int getID(){
		return ID;
	}

}
