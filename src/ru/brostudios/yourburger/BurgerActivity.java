package ru.brostudios.yourburger;

import java.util.ArrayList;
import java.util.List;
//import com.google.android.gms.internal.bu;
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
			public String string;			// ДЛЯ МАТЬ ЕГО ЮРЫ (Юра хороший((((()
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
		restaurants[RestInfo.MCDONALDS] = new RestInfo("McDonald's", "mcdonalds/logo.png", "McDonald’s Corporation  — американская корпорация, до 2010 года крупнейшая в мире сеть ресторанов быстрого питания. По итогам 2010 года компания занимает 2-е место по количеству ресторанов во всём мире после ресторанной сети Subway. Входит в список Fortune Global 500 2011 года (403-е место)");
		restaurants[RestInfo.MCDONALDS].addBurger("Биг мак", "mcdonalds/bigmac.png", "Описание бигмака", "Биг мак!0!0!1!0!0!0!1!0]");
		restaurants[RestInfo.MCDONALDS].addBurger("Биг тейсти", "mcdonalds/bigtasty.png", "Описание биг тейсти", "Биг тейсти!0!1!0!0!1!0!1!0]");
		
		restaurants[RestInfo.KFC] = new RestInfo("KFC", "kfc/logo.png", "KFC — американская сеть кафе общественного питания, специализирующихся на блюдах из курицы. Была основана в 1952 году Харландом Сандерсом под вывеской Kentuсky Fried Chicken (рус. Жареный цыпленок Кентукки).В 1991 бренд сократил название до KFC. С 1997 года сеть принадлежит крупной ресторанной компании Yum! Brands, владеющей также такими брендами, как Pizza Hut и Taco Bell. Сегодня сеть KFC представлена в 110 странах мира — это более 16 000 точек, в которых ежедневно обслуживаются около 12 000 000 клиентов. Главный офис находится в Луисвилле, штат Кентукки.");
		restaurants[RestInfo.KFC].addBurger("Сандерс", "kfc/sanders.png", "Описание сандерс", "Сандерс!1!0!1!1!0!0!0!0]");
		restaurants[RestInfo.KFC].addBurger("Зингер", "kfc/zinder.png", "Описание зингера", "Зингер!1!1!1!0!1!1!1!0]");
		
		restaurants[RestInfo.BURGERKING] = new RestInfo("Burger King", "burgerking/logo.png", "Burger King Corporation — американская компания, владелец сети ресторанов быстрого питания Burger King. Штаб-квартира — в Майами, штат Флорида.");
		restaurants[RestInfo.BURGERKING].addBurger("Воппер", "burgerking/wopper.png", "Описание воппера", "Воппер!0!0!0!0!1!1!1!0]");
		restaurants[RestInfo.BURGERKING].addBurger("Стейкхаус", "burgerking/steik.png", "Описание стейкхауса", "Стейкхаус!0!1!0!0!0!0!1!0]");
		
		restaurants[RestInfo.CARLSJR] = new RestInfo("Carl's Jr.", "carlsjr/logo.png", "Carl’s Jr. — крупная сеть ресторанов быстрого обслуживания, представленная более чем 3100 точек в США, Эквадоре, Мексике, Сингапуре, Таиланде и России. Она была основана Карлом Карчером в 1941 году, теперь является дочерней сетью компании CKE Restaurants.");
		restaurants[RestInfo.CARLSJR].addBurger("Гуакамоле", "carlsjr/guakamole.png", "Описание гуакамоле", "Гуакамоле!0!0!0!0!1!1!1!0]");
		restaurants[RestInfo.CARLSJR].addBurger("Биг марбл", "carlsjr/bigmurble.png", "Описание биг марбл", "Биг марбл!0!1!0!0!0!0!1!0]");
		
		restaurants[RestInfo.SUBWAY] = new RestInfo("SUBWAY", "subway/logo.png", "Subway (Са́бвэй) — сеть ресторанов быстрого питания, работающая по принципу франчайзинга. Крупнейшая по количеству точек предприятий общественного питания, на февраль 2011 года, сеть в мире. Основной продаваемой продукцией являются сэндвичи и салаты");
		restaurants[RestInfo.SUBWAY].addBurger("Сабвей клаб", "subway/club.png", "Описание сабвей клаб", "Сабвей клаб!0!0!0!0!0!0!0!0]");
		restaurants[RestInfo.SUBWAY].addBurger("Тунец", "subway/tunets.png", "Описание тунец", "Тунец!2!0!0!0!0!0!0!0]");
		

//	 String f =	restaurants[RestInfo.MCDONALDS].burgersInfo.get(0).string;
		
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