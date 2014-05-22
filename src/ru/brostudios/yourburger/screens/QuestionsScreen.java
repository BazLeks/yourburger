package ru.brostudios.yourburger.screens;
/**
 * Автор: Юра Леонтьев
 * Дата: 17.05.2014
 */

import ru.brostudios.yourburger.BurgerActivity.RestInfo.BurgerInfo;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity.*;
import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.framework.Application;
import android.view.View.OnClickListener;
import ru.brostudios.yourburger.R;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import java.util.Hashtable;
import android.view.View;

public class QuestionsScreen extends ScreenInterface {
	
	// GUI
	private TextView choice0, choice1, choice2, choice3;
	private TextView quiestionField;	// поле текста вопроса
	private RadioGroup radiogroup;
	private Button btn;
	
	// Logic
	private int choice, number; // номер вопроса
	private boolean isChoice;	// выбрана ли радиобатон
	private String results;		// строка с выбранными ответами
	private Hashtable<BurgerInfo, String> compatibles;	// хэш-таблица подходящих бургеров
	
	
// *********************************************************************************
	
	public QuestionsScreen(Application application) {
		super(application);
		compatibles = new Hashtable<BurgerInfo, String>();
	}
	
	@Override
	public void present() { }

	@Override
	public void update() { }

	@Override
	public void create() { }

	@Override
	public void resume() {
		number = 1;	// номер вопроса в опроснике
		results = ""; // строка ответа в опроснике
		isChoice = false;
		
		application.setContentView(R.layout.questions_screen);
		
		btn = (Button)application.findViewById(R.id.button1);
		radiogroup = (RadioGroup)application.findViewById(R.id.radioGroup1);
	    radiogroup.clearCheck();
		
		quiestionField = (TextView)application.findViewById(R.id.question);	
		choice0 = (TextView)application.findViewById(R.id.radio0);
		choice1 = (TextView)application.findViewById(R.id.radio1);	
		choice2 = (TextView)application.findViewById(R.id.radio2);	
		choice3 = (TextView)application.findViewById(R.id.radio3);
		
		this.quest(number);
		
		// обрабатываем выбор радиобуттонов
		radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio0: choice = 0; isChoice = true; break;
				case R.id.radio1: choice = 1; isChoice = true; break;
				case R.id.radio2: choice = 2; isChoice = true; break;
				case R.id.radio3: choice = 3; isChoice = true; break;
				default:
					// что за гавно, Юра?
					if (number == 9) isChoice = true;
					else isChoice = false;
					break;
				}
			}
		});
		
		// обрабатываем нажатие на кнопку
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isChoice) {
					if(number>=1 && number<=7) { results += String.valueOf(choice)+ "!"; }
					if(number == 8) { results += String.valueOf(choice); }
					quest(++number);
					radiogroup.clearCheck();
					if(number == 10) {
						QuestionsScreen.this.process();
						application.setScreen(new CompBurgers(application, compatibles));
					}
				}
			}
		});
	}

	@Override
	public void pause() { }

	@Override
	public void destroy() { }
	
	public void quest (int number) {
		 switch(number){
		    case 1:
		    	// очищаем переменные результата для нового опроса
				compatibles.clear();
				quiestionField.setText(R.string.questionName1);
				choice0.setText(R.string.questionChoise1_0);
				choice1.setText(R.string.questionChoise1_1);
				choice2.setText(R.string.questionChoise1_2);
				choice3.setText(R.string.questionChoise1_3);
				break;
		    case 2:
		    	// спрятать 2 радиобаттона
				choice2.setVisibility(android.view.View.INVISIBLE);
				choice3.setVisibility(android.view.View.INVISIBLE);
				
				quiestionField.setText(R.string.questionName2);
				choice0.setText(R.string.questionChoise2_0);
				choice1.setText(R.string.questionChoise2_1);
		    	break;
		    case 3:
				quiestionField.setText(R.string.questionName3);
				choice0.setText(R.string.questionChoise3_0);
				choice1.setText(R.string.questionChoise3_1);
				break;
		    case 4:
				quiestionField.setText(R.string.questionName4);
				choice0.setText(R.string.questionChoise4_0);
				choice1.setText(R.string.questionChoise4_1);
				break;
		    case 5:
				quiestionField.setText(R.string.questionName5);
				choice0.setText(R.string.questionChoise5_0);
				choice1.setText(R.string.questionChoise5_1);
				break;
		    case 6:
				quiestionField.setText(R.string.questionName6);
				choice0.setText(R.string.questionChoise6_0);
				choice1.setText(R.string.questionChoise6_1);
				break;
		    case 7:
				quiestionField.setText(R.string.questionName7);
				choice0.setText(R.string.questionChoise7_0);
				choice1.setText(R.string.questionChoise7_1);
				break;
		    case 8:
				quiestionField.setText(R.string.questionName8);
				choice0.setText(R.string.questionChoise8_0);
				choice1.setText(R.string.questionChoise8_1);
				break;				
		    default:
		    	quiestionField.setText("Спасибо за ответы! Мы учли ваши пожелания!");
		    	btn.setText("Посмотреть");
				choice0.setVisibility(android.view.View.INVISIBLE);
				choice1.setVisibility(android.view.View.INVISIBLE);
				choice2.setVisibility(android.view.View.INVISIBLE);
				choice3.setVisibility(android.view.View.INVISIBLE);
		 }
	}
	
	// обрабатывает результаты опроса
	public void process() {
		final int countQuestions = 8;	// здесь указываем количество вопросов в опроснике.
		// ищем подходящие бургеры среди всех зарегистрированных бургеров
		RestInfo[] restaurants = BurgerActivity.restaurants;
		// для каждого ресторана проверяем каждый бургер
		for(int i=0;i<restaurants.length;i++) {
			for(int j=0;j<restaurants[i].burgersInfo.size();j++) {
				int coincidence = 0;	// кол-во совпадений
				String[] answers = results.split("!"); // парсим то, что наотвечали
				String[] check = restaurants[i].burgersInfo.get(j).string.split("!"); // парсим строку из БД
				// проверяем на соответствие
				for(int k=0;k<answers.length;k++) {
					if(answers[k].equals(check[k])) coincidence++;
					else { if(k == 0) break; } // если тип котлеты не совпадает, то убираем из списка подходящих 
				}
				// если количество совпадений больше 0, то добавляем в список подходящих
				float result = ((float)coincidence/(float)countQuestions)*100f;
				if(result>=50f) {
					compatibles.put(restaurants[i].burgersInfo.get(j), result+"");
				}
			} // конец цикла бургеров
		} // конец цикла ресторанов
	}
}

