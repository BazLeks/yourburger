package ru.brostudios.yourburger.screens;

import java.util.Hashtable;
import java.util.List;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.yourburger.BurgerActivity.RestInfo.BurgerInfo;
import ru.brostudios.yourburger.R;

public class CompBurgers extends ScreenInterface {
	
	private String[] burgers;
	
	// -------------------------------------------------------
	
	public class BurgerItem extends LinearLayout {
		
		private TextView tView1, tView2;
		private ImageView iView;
		private Button button;
		
	// ************************************************************************
		
		public BurgerItem(final Application application, final BurgerInfo burger, String compatible) {
			
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
			tView1 = new TextView(application);
			tView1.setTextSize(24);
			tView1.setText(burger.name);
			
			// поле - насколько подходит данный бургер
			tView2 = new TextView(application); 
			tView2.setText("Соответствие: "+compatible+"%");
			
			// параметры текста
			LinearLayout layout = new LinearLayout(application);
			layout.setLayoutParams(new LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.MATCH_PARENT));
			
			// устанавливаем текст друг под другом
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.addView(tView1);
			layout.addView(tView2);
			
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
			addView(layout);
			addView(button, buttonParams);
		}
	}
	
	// -------------------------------------------------------
	
// ***********************************************************
	
	public CompBurgers(Application application, Hashtable<BurgerInfo, String> compatibles) {
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
		
		LinearLayout layout = new LinearLayout(application);
		layout.setOrientation(LinearLayout.VERTICAL);
		if(burgers == null) {
			TextView view = new TextView(application);
			view.setText("К сожалению, под ваш запрос ничего не найдено. " +
					"Попробуйте пройти тест заново");
			view.setTextSize(20);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER;
			layout.setLayoutParams(params);
			layout.addView(view);
		}
		else {
			for(int i=0;i<burgers.length;i+=2) {
//				BurgerItem item = new BurgerItem(application);
//				layout.addView(item);
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
