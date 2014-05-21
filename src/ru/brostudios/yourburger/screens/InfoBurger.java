package ru.brostudios.yourburger.screens;
/**
 * Автор: Евстратов Роман
 * Дата: 17.05.2014
 */

import android.app.backup.RestoreObserver;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity.RestInfo.BurgerInfo;
import ru.brostudios.yourburger.R;

public class InfoBurger extends ScreenInterface {

	public BurgerInfo burgerinfo;
	
	public InfoBurger(Application application, BurgerInfo burgerinfo) {
		super(application);
		
		this.burgerinfo = burgerinfo;
	}

	@Override
	public void create() {
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
	public void resume() {	
		application.setContentView(R.layout.burgerinfo);
		
		TextView burgername = (TextView) application.findViewById(R.id.burger_name);
		//burgername.setText(text);
		
		Button button1 = (Button) application.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				application.setScreen(new RestaurantInfo(application, burgerinfo.parent));
			}
		});
		
		Button button2 = (Button) application.findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				application.GoToMainMenu();
				
			}
		});
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
