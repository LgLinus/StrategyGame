package shapes;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Line;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.ligr.strategygame.MainActivity;

/**
 * Modified rectangle that contains a name
 * @author LgLinuss
 *
 */
public class RectangleModified extends Entity {
	
	private String name;
	private float x,y,width,height;
	private int lineWidth;
	private Color color;
	private VertexBufferObjectManager vobm;
	public RectangleModified(float pX, float pY, float pWidth, float pHeight,VertexBufferObjectManager vobm,String name, Color color, int lineWidth) {
		super(pX, pY);
		this.vobm = vobm;
		this.x = pX;
		this.y = pY;
		this.width = pWidth;
		this.height = pHeight;
		this.color = color;
		this.lineWidth = lineWidth;
		createRectangle();
		this.name = name;
	}
	
	private void createRectangle() {
		
		Line line = new Line(x, y, x+width, y, vobm);
		this.attachChild(line);
		line.setColor(color);
		line.setLineWidth(lineWidth);
		line = new Line(x, y, x, y+height, vobm);
		this.attachChild(line);
		line.setColor(color);
		line.setLineWidth(lineWidth);
		line = new Line(x, y+height, x+width, y+height, vobm);
		this.attachChild(line);
		line.setColor(color);
		line.setLineWidth(lineWidth);
		line = new Line(x+width, y, x+width, y+height, vobm);
		this.attachChild(line);
		line.setColor(color);
		line.setLineWidth(lineWidth);
	}

	/**
	 * Return true if the given name is the same as this rectangles name
	 * @param name 
	 * @return true or false
	 */
	public boolean checkName(String name){
		return this.name.equals(name);
	}

	public void remove() {
		MainActivity.removeEntity(this);
	}

}
