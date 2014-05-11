package ru.brostudios.yourburger;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.game.Texture;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.screens.MainMenu;

public class BurgerActivity extends Application {

	@Override
	public ScreenInterface getStartScreen() {
		return new MainMenu(this);
	}

	@Override
	public void loadGameTextures() {
		Textures.restaurant = new Texture(this, "MainMenu/restaurant.png");
		Textures.burger = new Texture(this, "MainMenu/burger.png");
		Textures.back = new Texture(this, "MainMenu/back.png");
	}
}