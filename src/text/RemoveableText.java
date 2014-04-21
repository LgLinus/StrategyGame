package text;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;

public class RemoveableText extends Text{

	public boolean remove = false;
	public RemoveableText(float pX, float pY, IFont pFont, CharSequence pText,
			int pCharactersMaximum,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pFont, pText, pCharactersMaximum, pVertexBufferObjectManager);
		MainActivity.removeableTexts.add(this);
	}
	public boolean remove(){
		return remove;
	}
	public void detach(){
		this.detachSelf();
		MainActivity.removeableTexts.remove(this);
	}
}
