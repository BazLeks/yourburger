package ru.brostudios.yourburger.screens;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
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
		
		String path = "";
		String description = "Описания пока нет.";
		
		try {
			InputStream is = File.LoadFileFromAsset(application.getAssets(), "restaurants.txt");
			byte[] buffer = new byte[is.available()];
			is.read(buffer, 0, is.available());
			String str = "#SUBWAY|burger.png|SUBWAY|#KFC|burger.png|Description|";
//			str = buffer.toString();
			int i = str.indexOf('#') + 1;
			while(i < str.length() && i >= 0) {
				// имя
				int k = str.indexOf('|', i);
				String temp = str.substring(i, k);
				if(temp.equals(name)) {
					// путь к иконке
					i = k+1;
					k = str.indexOf('|', k+1);
					path = str.substring(i, k);
					// описание
					i = k+1;
					k = str.indexOf('|', k+1);
					description = str.substring(i, k);
					break;
				}
				i = str.indexOf('#', i) + 1;
			}						
		} catch (IOException e) { e.printStackTrace(); }
		
		TextView textView = (TextView) application.findViewById(R.id.restaurant_description);
		textView.setText(description);
		
		textView = (TextView) application.findViewById(R.id.restaurant_name);
		textView.setText(name);
		
		ImageView imageView = (ImageView) application.findViewById(R.id.restaurant_icon);
		Bitmap bitmap = BitmapFactory.decodeStream(File.LoadFileFromAsset(application.getAssets(), path));
		imageView.setImageBitmap(bitmap);
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