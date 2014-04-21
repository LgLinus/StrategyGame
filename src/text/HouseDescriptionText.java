package text;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.House;
import com.ligr.strategygame.MainActivity;

public class HouseDescriptionText extends Text{

	public boolean remove = false;
	public House house=null; // House with info
	
	public HouseDescriptionText(float pX, float pY, IFont pFont, CharSequence pText,
			int pCharactersMaximum,
			VertexBufferObjectManager pVertexBufferObjectManager, House house) {
		super(pX, pY, pFont, pText, pCharactersMaximum, pVertexBufferObjectManager);
		this.house = house;
	}
	
	
}
