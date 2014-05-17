package ru.brostudios.yourburger.screens;
/**
 * Автор: Евстратов Роман
 * Дата: 17.05.2014
 */

import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.R;

public class BurgerInfo extends ScreenInterface {

	public BurgerInfo(Application application) {
		super(application);
	}

	@Override
	public void present() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		application.setContentView(R.layout.burgerinfo);		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
