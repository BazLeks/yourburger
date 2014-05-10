package ru.brostudios.framework.interfaces;

import ru.brostudios.framework.Application;

public abstract class ScreenInterface {
	
	protected Application application;
	
	public ScreenInterface(Application application) {
		this.application = application;
	}
	
	public abstract void present();
	public abstract void update();
	public abstract void resume();
	public abstract void pause();
	public abstract void destroy();
}