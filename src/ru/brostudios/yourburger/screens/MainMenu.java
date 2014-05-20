package ru.brostudios.yourburger.screens;

import java.util.List;

import android.util.Log;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.Input;
import ru.brostudios.framework.game.Sprite;
import ru.brostudios.framework.gui.Button;
import ru.brostudios.framework.gui.ButtonBoss;
import ru.brostudios.framework.interfaces.InputInterface.TouchEvent;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.yourburger.Textures;
import ru.brostudios.yourburger.BurgerActivity.RestInfo;

public class MainMenu extends ScreenInterface {

	// кнопки при выборе бургера
	private static final int Button_Ingredient = 0;
	private static final int Button_List = 1;
	// текущее состояние окна
	public static final int Burger_State = 0;
	public static final int Restaurant_State = 1;
	// GUI окна
	private Sprite background, title;
	private ButtonBoss burger, restaurant;	
	// шушера
	private float x;	// [0,0.5]-бургер, [0.5,1.0]-ресторан
	private float fixed_x;	// фиксация пальца
	private float delta_x;	
	private boolean complete = false;
	private int state = 0;

// ******************************************************	

	public MainMenu(Application application) {
		super(application);
	}
	
	@Override
	public void create() {
		background = new Sprite(application, Textures.back, 1, 1);
		title = new Sprite(application, Textures.title, 1, 1);
		
		// создаем кнопки при активации бургера
		burger = new ButtonBoss(application, Textures.burger, application.getGraphics().getWidth()/2f, 0.01f, application.getGraphics().getWidth()/2.5f, 220, 320);
		// -- кнопка по ингредиентам
		burger.addButton(new Button(application, Textures.ingredients, application.getGraphics().getWidth()/4f));
		// -- кнопка из списка
		burger.addButton(new Button(application, Textures.list, application.getGraphics().getWidth()/4f));

		// создаем кнопки при активации ресторана
		restaurant = new ButtonBoss(application, Textures.restaurant, application.getGraphics().getWidth()/2f, 0.01f, application.getGraphics().getWidth()/3f, 0, 360);
		// -- кнопка kfc
		restaurant.addButton(new Button(application, Textures.kfc, application.getGraphics().getWidth()/4f));
		// -- кнопка mcdonalds
		restaurant.addButton(new Button(application, Textures.mcdonalds, application.getGraphics().getWidth()/4f));
		// -- кнопка burgerking
		restaurant.addButton(new Button(application, Textures.burgerking, application.getGraphics().getWidth()/4f));
		// -- кнопка carlsjr
		restaurant.addButton(new Button(application, Textures.carlsjr, application.getGraphics().getWidth()/4f));
		// -- кнопка subway
		restaurant.addButton(new Button(application, Textures.subway, application.getGraphics().getWidth()/4f));
	}

	@Override
	public void present() {
		float delta_x = (0.5f-x)*application.getGraphics().getWidth();
		background.present(application.getGraphics().getGL(), delta_x, 0, application.getGraphics().getWidth()*3);
		burger.present();
		burger.MoveInScreenCoords(delta_x-application.getGraphics().getWidth()/2f, 0);
		restaurant.present();
		restaurant.MoveInScreenCoords(delta_x+application.getGraphics().getWidth()/2f, 0);
		title.present(application.getGraphics().getGL(), 0, application.getGraphics().getHeight()/3f, application.getGraphics().getWidth());
	}

	@Override
	public void update() {
		
		Input input = application.getInput();
		
		List<TouchEvent>events = input.getTouchEvents();
		for(int i=0;i<events.size();i++) {
			TouchEvent event = events.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN) { fixed_x = event.x; }
			if(state == Burger_State) x = (fixed_x-event.x)/application.getGraphics().getWidth();
			if(state == Restaurant_State) x = 1f-(event.x-fixed_x)/application.getGraphics().getWidth();
			if(x>1) this.x=1; if(x<0) this.x=0;
			if(event.type == TouchEvent.TOUCH_UP) {
				complete = true;
				delta_x = 0.05f;
			}	
		}
		
		if(complete) { 
			if(x>0.5f) { x += delta_x; if(x>=1) { x = 1; state = Restaurant_State; complete = false; }}
			else { x -= delta_x; if(x<=0) { x = 0; state = Burger_State; complete = false; }} 
		}
		
		// нажатия на кнопки "выбор бургера"
		// обработка нажатий раскрывающихся кнопок
		burger.update(events);
		if(burger.getButton(Button_Ingredient).isTouchReleased()) {
			Log.d("yourburger", "Нажали на Button_Ingredient");
			application.setScreen(new QuestionsScreen(application));
		}
		if(burger.getButton(Button_List).isTouchReleased()) {
			Log.d("yourburger", "Нажали на Button_List");
			application.setScreen(new AllBurgers(application, "Биг мак!Биг тейсти!Чизбургер!Гамбургер"));
		}
		// нажатия на кнопки "выбор ресторана"
		// обработка нажатий раскрывающихся кнопок
		restaurant.update(events);
		// -- кнопка kfc
		if(restaurant.getButton(0).isTouchReleased()) {
			application.setScreen(new RestaurantInfo(application, BurgerActivity.restaurants[RestInfo.KFC]));
			Log.d("yourburger", "Нажали на кнопку kfc");
		}
		// -- кнопка mcdonalds
		if(restaurant.getButton(1).isTouchReleased()) {
			application.setScreen(new RestaurantInfo(application, BurgerActivity.restaurants[RestInfo.MCDONALDS]));
			Log.d("yourburger", "Нажали на кнопку mcdonalds");
		}
		// -- кнопка burgerking
		if(restaurant.getButton(2).isTouchReleased()) {
			application.setScreen(new RestaurantInfo(application, BurgerActivity.restaurants[RestInfo.BURGERKING]));
			Log.d("yourburger", "Нажали на кнопку burgerking");
		}
		// -- кнопка carlsjr
		if(restaurant.getButton(3).isTouchReleased()) {
			application.setScreen(new RestaurantInfo(application, BurgerActivity.restaurants[RestInfo.CARLSJR]));
			Log.d("yourburger", "Нажали на кнопку carlsjr");
		}
		// -- кнопка subway
		if(restaurant.getButton(4).isTouchReleased()) {
			application.setScreen(new RestaurantInfo(application, BurgerActivity.restaurants[RestInfo.SUBWAY]));
			Log.d("yourburger", "Нажали на кнопку subway");	
		}		
	}

	@Override
	public void resume() {
		application.getGraphics().getView().onResume();
		application.setContentView(application.getGraphics().getView());
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void destroy() {
		
	}

}
