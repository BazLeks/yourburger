package ru.brostudios.yourburger.screens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.yourburger.BurgerActivity.RestInfo.BurgerInfo;
import ru.brostudios.yourburger.File;
import ru.brostudios.yourburger.R;
import ru.brostudios.yourburger.BurgerActivity.RestInfo;

public class AllBurgers extends ScreenInterface {

	private String[] burgers;
	
	public class BurgerItem extends LinearLayout {
		
		private Button button;
		private TextView tView;
		private ImageView iView;
		
	// ************************************************************************
		
		public BurgerItem(final Application application, final BurgerInfo burger) {
			super(application);	
			
			// параметры всего слоя
			ViewGroup.LayoutParams paramsLayout = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT);
			paramsLayout.height = 100;
			setLayoutParams(paramsLayout);
			
			// картинка бургера
			iView = new ImageView(application);
			Bitmap bitmap = BitmapFactory.decodeStream(File.LoadFileFromAsset(application.getAssets(), burger.picturePath));
			iView.setImageBitmap(bitmap);
			// параметры слоя картинки
			LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			imageParams.width = 100;
		
			// поле названия бургера
			tView = new TextView(application);
			tView.setTextSize(24);
			tView.setText(burger.name);
			LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
			textParams.width = 200;
			
			
			// кнопка для поиска данного бургера
			button = new Button(application);
			button.setText("Просмотр");
			// заполняет оставшееся место
			LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			// обработчик вызывает окно и передаёт ему название бургера
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) { application.setScreen(new InfoBurger(application, burger)); }
			});
			
			
			addView(iView, imageParams);
			addView(tView, textParams);
			addView(button, buttonParams);
		}
	}
	
// **************************************************
	
	public AllBurgers(Application application) {
		super(application);
	}

	@Override
	public void present() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void create() {
		
	}

	@Override
	public void resume() {
		ScrollView view = new ScrollView(application);
		LinearLayout layout = new LinearLayout(application);
		layout.setVerticalScrollBarEnabled(true);
		layout.setOrientation(LinearLayout.VERTICAL);
		RestInfo[] rests = BurgerActivity.restaurants;
		for(int i=0;i<rests.length;i++) {
			for(int j=0;j<rests[i].burgersInfo.size();j++) {
				BurgerItem item = new BurgerItem(application, rests[i].burgersInfo.get(j));
				layout.addView(item);
			}			
		}
		
		view.addView(layout);
		
		application.setContentView(view);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void destroy() {
		
	}
}
