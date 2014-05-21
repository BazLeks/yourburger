package ru.brostudios.yourburger.screens;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.yourburger.R;
import ru.brostudios.yourburger.BurgerActivity.RestInfo;

public class AllBurgers extends ScreenInterface {

	private String[] burgers;
	
	public class BurgerItem extends LinearLayout {
		
		private Button button;
		private TextView tView;
		private ImageView iView;
		
	// ************************************************************************
		
		public BurgerItem(final Application application, String name) {
			super(application);	
			
			// параметры всего слоя
			ViewGroup.LayoutParams paramsLayout = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT);
			paramsLayout.height = 100;
			setLayoutParams(paramsLayout);
			
			// картинка бургера
			iView = new ImageView(application);
			iView.setImageResource(R.drawable.ic_launcher);
			// параметры слоя картинки
			LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			imageParams.width = 100;
		
			// поле названия бургера
			tView = new TextView(application);
			tView.setTextSize(24);
			tView.setText(name);
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
				public void onClick(View v) { application.setScreen(new InfoBurger(application, ((BurgerActivity)application).restaurants[0].burgersInfo.get(0))); }
			});
			
			
			addView(iView, imageParams);
			addView(tView, textParams);
			addView(button, buttonParams);
		}
	}
	
// **************************************************
	
	public AllBurgers(Application application, String burgers) {
		super(application);
		this.burgers = burgers.split("!");
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
		LinearLayout layout = new LinearLayout(application);
		layout.setOrientation(LinearLayout.VERTICAL);
		RestInfo[] rests = BurgerActivity.restaurants;
		for(int i=0;i<rests.length;i++) {
			for(int j=0;j<rests[i].burgersInfo.size();j++) {
				BurgerItem item = new BurgerItem(application, rests[i].burgersInfo.get(j).name);
				layout.addView(item);
			}			
		}
		
		application.setContentView(layout);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void destroy() {
		
	}
}
