package ru.brostudios.yourburger;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.internal.bu;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.game.Texture;
import ru.brostudios.yourburger.screens.MainMenu;
import ru.brostudios.framework.interfaces.ScreenInterface;

public class BurgerActivity extends Application {
	
	public static RestInfo[] restaurants;
	
	public class RestInfo {
		
		public static final int MCDONALDS = 0;
		public static final int KFC = 1;
		public static final int BURGERKING = 2;
		public static final int CARLSJR = 3;
		public static final int SUBWAY = 4;
		
		public class BurgerInfo {
			public String name;
			public String picturePath;
			public String description;
			public String string;			// ДЛЯ МАТЬ ЕГО ЮРЫ
			public RestInfo parent;
			
			BurgerInfo(String name, String picturePath, String description, String string, RestInfo parent) {
				this.name = name;
				this.picturePath = picturePath;
				this.description = description;
				this.string = string;
				this.parent = parent;
			}
		}
		
		public String name;
		public String picturePath;
		public String description;
		public List<BurgerInfo> burgersInfo;
		
		public RestInfo(String name, String picturePath, String description) {
			this.name = name;
			this.picturePath = picturePath;
			this.description = description;
			burgersInfo = new ArrayList<BurgerInfo>();
		}
		
		public void addBurger(String name, String picturePath, String description, String string) {
			burgersInfo.add(new BurgerInfo(name, picturePath, description, string, this));
		}
	}
	
	
	
	@Override
	public ScreenInterface getStartScreen() {
		restaurants = new RestInfo[5];
		restaurants[RestInfo.MCDONALDS] = new RestInfo("McDonald's", "mcdonalds/logo.png", "Описание макдака");
		restaurants[RestInfo.MCDONALDS].addBurger("Биг мак", "mcdonalds/bigmac.png", "Описание бигмака", "");
		restaurants[RestInfo.MCDONALDS].addBurger("Биг тейсти", "mcdonalds/bigtasty.png", "Описание биг тейсти", "");
		
		restaurants[RestInfo.KFC] = new RestInfo("KFC", "kfc/logo.png", "Описание кфц");
		restaurants[RestInfo.KFC].addBurger("Сандерс", "kfc/sanders.png", "Описание сандерс", "");
		restaurants[RestInfo.KFC].addBurger("Зингер", "kfc/zinder.png", "Описание зингера", "");
		
		restaurants[RestInfo.BURGERKING] = new RestInfo("Burger King", "burgerking/logo.png", "Описание бургер кинга");
		restaurants[RestInfo.BURGERKING].addBurger("Воппер", "burgerking/wopper.png", "Описание воппера", "");
		restaurants[RestInfo.BURGERKING].addBurger("Стейкхаус", "burgerking/steik.png", "Описание стейкхауса", "");
		
		restaurants[RestInfo.CARLSJR] = new RestInfo("Carl's Jr.", "carlsjr/logo.png", "Описание карс");
		restaurants[RestInfo.CARLSJR].addBurger("Гуакамоле", "carlsjr/guakamole.png", "Описание гуакамоле", "");
		restaurants[RestInfo.CARLSJR].addBurger("Биг марбл", "carlsjr/bigmurble.png", "Описание биг марбл", "");
		
		restaurants[RestInfo.SUBWAY] = new RestInfo("SUBWAY", "subway/logo.png", "Описание сабвея");
		restaurants[RestInfo.SUBWAY].addBurger("Сабвей клаб", "subway/club.png", "Описание сабвей клаб", "");
		restaurants[RestInfo.SUBWAY].addBurger("Тунец", "subway/tunets.png", "Описание тунец", "");
		
		return new MainMenu(this);
	}

	@Override
	public void loadGameTextures() {
		Textures.restaurant = new Texture(this, "restaurant.png");
		Textures.burger = new Texture(this, "burger.png");
		Textures.back = new Texture(this, "back.png");
		Textures.title = new Texture(this, "title.png");
		Textures.list = new Texture(this, "list.png");
		Textures.ingredients = new Texture(this, "ingredients.png");
		
		// рестораны
		Textures.kfc = new Texture(this, "kfc/logo.png");
		Textures.mcdonalds = new Texture(this, "mcdonalds/logo.png");
		Textures.carlsjr = new Texture(this, "carlsjr/logo.png");
		Textures.burgerking = new Texture(this, "burgerking/logo.png");
		Textures.subway = new Texture(this, "subway/logo.png");
	}
}