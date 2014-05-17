package ru.brostudios.yourburger;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.game.Texture;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.screens.MainMenu;
import ru.brostudios.yourburger.screens.QuestionsScreen;

public class BurgerActivity extends Application {

	@Override
	public ScreenInterface getStartScreen() {
	 return new MainMenu(this);
	//	return null;
	}

	@Override
	public void loadGameTextures() {
		// Textures.restaurant = new Texture(this, "MainMenu/restaurant.png");
		// Textures.burger = new Texture(this, "MainMenu/burger.png");
		Textures.back = new Texture(this, "back.png");
		Textures.questions = new Texture(this, "questions.png");
	}
}