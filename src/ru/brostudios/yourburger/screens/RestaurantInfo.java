package ru.brostudios.yourburger.screens;

import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.File;
import ru.brostudios.yourburger.R;

public class RestaurantInfo extends ScreenInterface {
	
	String name;
	
	public RestaurantInfo(Application application, String name) {
		super(application);
		this.name = name;
	}

	@Override
	public void create() {
		application.setContentView(R.layout.restaurant_info);
		
//		RelativeLayout layout = (RelativeLayout) application.findViewById(R.layout.restaurant_info);
		
		TextView textView = (TextView) application.findViewById(R.id.restaurant_name);
		textView.setText(name);
		
		textView = (TextView) application.findViewById(R.id.restaurant_description);
		textView.setText("Описание скоро будет..");
	}

	@Override
	public void present() {}

	@Override
	public void update() {}

	@Override
	public void resume() {}

	@Override
	public void pause() {}

	@Override
	public void destroy() {}

}