package ru.brostudios.framework.gui;

import java.util.List;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.InputInterface.TouchEvent;

public abstract class UserInterface {
	
	protected Application game;
	protected int state;
	protected boolean active;
	
	public UserInterface(Application game) {
		this.game = game;
		active = true;
	}
	
	public abstract void present();
	
	public void update(List<TouchEvent> touchEvents) {}
	
}