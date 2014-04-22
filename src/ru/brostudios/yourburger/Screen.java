package ru.brostudios.yourburger;

import javax.microedition.khronos.opengles.GL10;

public abstract class Screen {
	
	protected BurgerActivity activity;
	
	public Screen(BurgerActivity activity) {
		this.activity = activity; 
	}
	
	public abstract void create(GL10 gl);
	public abstract void present(GL10 gl);
}
