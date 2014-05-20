package ru.brostudios.yourburger.screens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity.RestInfo;
import ru.brostudios.yourburger.File;
import ru.brostudios.yourburger.R;

public class RestaurantInfo extends ScreenInterface {
	
	private RestInfo info;
	
	public RestaurantInfo(Application application, RestInfo info) {
		super(application);
		this.info = info;
	}

	@Override
	public void create() {
		application.setContentView(R.layout.restaurant_info);
		
		ImageView imageView = (ImageView) application.findViewById(R.id.restaurant_icon);
		Bitmap bitmap = BitmapFactory.decodeStream(File.LoadFileFromAsset(application.getAssets(), info.picturePath));
		imageView.setImageBitmap(bitmap);
		
		TextView textView = (TextView) application.findViewById(R.id.restaurant_name);
		textView.setText(info.name);
		
		textView = (TextView) application.findViewById(R.id.restaurant_description);
		textView.setText(info.description);
		
		OnClickListener listenerShowOnMap = new OnClickListener() {
			@Override
			public void onClick(View v) {
				application.setScreen(new RestMap(application));
			}
		};
		Button button = (Button)application.findViewById(R.id.restaurant_showinmap);
		button.setOnClickListener(listenerShowOnMap);
		
		OnClickListener listenerGoToMainMenu = new OnClickListener() {
			@Override
			public void onClick(View v) {
				application.GoToMainMenu();
			}
		};
		button = (Button)application.findViewById(R.id.restaurant_mainmenu);
		button.setOnClickListener(listenerGoToMainMenu);
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