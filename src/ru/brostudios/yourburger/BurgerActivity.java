package ru.brostudios.yourburger;

import java.util.ArrayList;
import java.util.List;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.game.Texture;
import ru.brostudios.yourburger.screens.CompBurgers;
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
		restaurants[RestInfo.MCDONALDS].addBurger("Биг мак", "mcdonalds/bigmac.png", 
				"Большой рубленый бифштекс из натуральной цельной говядины с двумя кусочками " +
				"сыра «Чеддер» на карамелизованной булочке, заправленной горчицей, кетчупом, " +
				"луком и двумя кусочками маринованного огурчика.", 
				"Биг мак!0!0!1!0!0!0!1!0]");
		restaurants[RestInfo.MCDONALDS].addBurger("Биг тейсти", "mcdonalds/tasty.png", 
				"Это сандвич с большим, рубленым бифштексом из 100% говядины на большой булочке " +
				"с кунжутом. Особенный вкус сандвичу придают три кусочка сыра «эмменталь», два " +
				"ломтика помидора, свежий салат, лук и пикантный соус «Гриль».", 
				"Биг тейсти!0!1!0!0!1!0!1!0]");
		
		restaurants[RestInfo.KFC] = new RestInfo("KFC", "kfc/logo.png", "KFC — американская сеть кафе общественного питания, специализирующихся на блюдах из курицы. Была основана в 1952 году Харландом Сандерсом под вывеской Kentuсky Fried Chicken (рус. Жареный цыпленок Кентукки).В 1991 бренд сократил название до KFC. С 1997 года сеть принадлежит крупной ресторанной компании Yum! Brands, владеющей также такими брендами, как Pizza Hut и Taco Bell. Сегодня сеть KFC представлена в 110 странах мира — это более 16 000 точек, в которых ежедневно обслуживаются около 12 000 000 клиентов. Главный офис находится в Луисвилле, штат Кентукки.");
		restaurants[RestInfo.KFC].addBurger("Сандерс", "kfc/sanders.png", 
				"Новый сандвич «Сандерс» - ярко, как никогда прежде, раскрывает знаменитый вкус, " +
				"созданный самим полковником Сандерсом. Он приготовлен вручную из свежего куриного " +
				"мяса по знаменитому секретному рецепту «11 трав и специй».   Нежное куриное филе " +
				"с хрустящими маринованными огурчиками, сочным луком, заправленные кетчупом и " +
				"майонезным соусом в мягкой кунжутной булочке …ммм…получилось SO GOOD!", 
				"Сандерс!1!0!1!1!0!0!0!0]");
		restaurants[RestInfo.KFC].addBurger("Зингер", "kfc/zinger.png", 
				"Хочешь острых ощущений? Сочное свежее куриное филе в обжигающе острой хрустящей " +
				"панировке внутри булочки с кунжутом — взрывной вкус для самых дерзких!", 
				"Зингер!1!1!1!0!1!1!1!0]");
		
		restaurants[RestInfo.BURGERKING] = new RestInfo("Burger King", "burgerking/logo.png", "Burger King Corporation — американская компания, владелец сети ресторанов быстрого питания Burger King. Штаб-квартира — в Майами, штат Флорида.");
		restaurants[RestInfo.BURGERKING].addBurger("Воппер", "burgerking/wopper.png", 
				"Вкуснейшая приготовленная на огне 100% говядина с сочными помидорами, свежим нарезанным " +
				"листовым салатом, густым майонезом, хрустящими маринованными огурчиками и рубленым белым " +
				"луком на нежной булочке с кунжутной посыпкой.", 
				"Воппер!0!0!0!0!1!1!1!0]");
		restaurants[RestInfo.BURGERKING].addBurger("Стейкхаус", "burgerking/steik.png", 
				"Сочетание нашей фирменной, приготовленной на огне 100% говядины с ломтиками бекона, " +
				"а также специальным соусом «Барбекю», майонезом, сыром, листьями свежего салата, " +
				"дольками помидоров и хрустящим луком на воздушной булочке, посыпанной кукурузной " +
				"крошкой.", 
				"Стейкхаус!0!1!0!0!0!0!1!0]");
		
		restaurants[RestInfo.CARLSJR] = new RestInfo("Carl's Jr.", "carlsjr/logo.png", "Carl’s Jr. — крупная сеть ресторанов быстрого обслуживания, представленная более чем 3100 точек в США, Эквадоре, Мексике, Сингапуре, Таиланде и России. Она была основана Карлом Карчером в 1941 году, теперь является дочерней сетью компании CKE Restaurants.");
		restaurants[RestInfo.CARLSJR].addBurger("Гуакамоле", "carlsjr/guakamole.png", 
				"Сочная котлета из говядины, булочка с кунжутом, лук, листья салата, помидор, швейцарский " +
				"сыр, соус Санта Фе, соус Гуакамоле, поджаристый бекон.", 
				"Гуакамоле!0!0!0!0!1!1!1!0]");
		restaurants[RestInfo.CARLSJR].addBurger("Биг марбл", "carlsjr/bigmurble.png", 
				"Сочная котлета из мраморной говядины, булочка с кунжутом, лук, листья салата, " +
				"помидор, американский сыр, горчица, майонез, кетчуп, маринованные огурчики.", 
				"Биг марбл!0!1!0!0!0!0!1!0]");
		
		restaurants[RestInfo.SUBWAY] = new RestInfo("SUBWAY", "subway/logo.png", "Subway (Са́бвэй) — сеть ресторанов быстрого питания, работающая по принципу франчайзинга. Крупнейшая по количеству точек предприятий общественного питания, на февраль 2011 года, сеть в мире. Основной продаваемой продукцией являются сэндвичи и салаты");
		restaurants[RestInfo.SUBWAY].addBurger("Сабвей клаб", "subway/club.png", 
				"Соблазнительное сочетание ростбифа, ветчины и индейки, свежие овощи по Вашему выбору на " +
				"свежевыпеченном хлебе в комбинации с Вашим любимым соусом.", 
				"Сабвей клаб!0!0!0!0!0!0!0!0]");
		restaurants[RestInfo.SUBWAY].addBurger("Тунец", "subway/tunets.png", 
				"Этот сэндвич просто подарок моря. Отжатый и перемешанный в правильной пропорции с " +
				"майонезом тунец дает неповторимый вкус. Попробовав сэндвич с тунцом один раз, " +
				"вы будете выбирать его снова и снова.", 
				"Тунец!2!0!0!0!0!0!0!0]");
		
		return new CompBurgers(this, null);
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