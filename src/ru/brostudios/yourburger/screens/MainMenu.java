package ru.brostudios.yourburger.screens;

import java.util.List;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.Input;
import ru.brostudios.framework.game.Sprite;
import ru.brostudios.framework.gui.Button;
import ru.brostudios.framework.interfaces.InputInterface.TouchEvent;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.Textures;

public class MainMenu extends ScreenInterface {

	public static final int Burger_State = 0;
	public static final int Restaurant_State = 1;
	
	private Sprite background, title;
	private Button burger, restaurant;
	private float x;	// [0,0.5]-бургер, [0.5,1.0]-ресторан
	private float fixed_x;
	private float delta_x;
	private boolean complete = false;
	private int state = 0;

// ******************************************************	

	public MainMenu(Application application) {
		super(application);
		background = new Sprite(application, Textures.back, 1, 1);
		title = new Sprite(application, Textures.title, 1, 1);
		
		burger = new Button(application, Textures.burger, application.getGraphics().getWidth()/2f);
		restaurant = new Button(application, Textures.restaurant, application.getGraphics().getWidth()/2f);
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
		
		//if(events.size()==0) if(x!=0f || x!=1f) x+=delta_x;
		burger.update(events);
		restaurant.update(events);
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
