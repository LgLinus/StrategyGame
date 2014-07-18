package com.ligr.strategygame.buildings;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ligr.strategygame.MainActivity;
import com.ligr.strategygame.SpriteObject;

public class MineDepositBronze extends SpriteObject {
	
	public MineDepositBronze(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, MainActivity main, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager,main);
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	void save() {
		// TODO Auto-generated method stub
		
	}
	public float getZ(){
		return this.z + yBaseStart;
	}
}
