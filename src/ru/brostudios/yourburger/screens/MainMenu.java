package ru.brostudios.yourburger.screens;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.Input;
import ru.brostudios.framework.game.Sprite;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.Textures;

public class MainMenu extends ScreenInterface {

	private Sprite background;
	
	public MainMenu(Application application) {
		super(application);
		background = new Sprite(application, Textures.back, 1, 1);
	}

	@Override
	public void present() {
		background.present(application.getGraphics().getGL(), 0, 0, application.getGraphics().getWidth());
	}

	@Override
	public void update() {
		Input input = application.getInput();
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void destroy() {
		
	}

}
