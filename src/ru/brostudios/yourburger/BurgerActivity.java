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
<<<<<<< HEAD
		Textures.restaurant = new Texture(this, "restaurant.png");
		Textures.burger = new Texture(this, "burger.png");
		Textures.back = new Texture(this, "back.png");
		Textures.title = new Texture(this, "title.png");
		Textures.list = new Texture(this, "list.png");
		Textures.ingredients = new Texture(this, "ingredients.png");
		
		// рестораны
		Textures.kfc = new Texture(this, "kfc.png");
		Textures.mcdonalds = new Texture(this, "mcdonalds.png");
		Textures.carlsjr = new Texture(this, "carlsjr.png");
		Textures.burgerking = new Texture(this, "burgerking.png");
		Textures.subway = new Texture(this, "subway.png");
=======
		// Textures.restaurant = new Texture(this, "MainMenu/restaurant.png");
		// Textures.burger = new Texture(this, "MainMenu/burger.png");
		Textures.back = new Texture(this, "back.png");
		Textures.questions = new Texture(this, "questions.png");
>>>>>>> origin/Yura
	}
}