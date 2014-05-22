package ru.brostudios.yourburger.screens;
/**
 * Автор: Юра Леонтьев
 * Дата: 17.05.2014
 */

import java.util.Hashtable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.File;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.BurgerActivity.*;
import ru.brostudios.yourburger.BurgerActivity.RestInfo.BurgerInfo;
import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.yourburger.R;


public class QuestionsScreen extends ScreenInterface {
	
	StringBuilder builder = new StringBuilder();
	
	private RadioGroup radiogroup;
	private TextView tvInfo, buttonText;
	private TextView choise0, choise1, choise2, choise3;
	//private OnClickListener radioListener;
	private Button btn;
	private int choise;
	private boolean choice; // выбрана ли радиобатон
	private int number; 	// номер вопроса
	private String result;	// номер ответа 
	private int howBurgers;	// сколько бургеров подходит
	
	RestInfo[] restoran;
	
	private Hashtable<BurgerInfo, String> compatibles;	// хэш-таблица подходящих бургеров
	
// *************************************************************
	
	public QuestionsScreen(Application application) {
		super(application);
		compatibles = new Hashtable<BurgerInfo, String>();
		//create();
	}
	
	@Override
	public void present() { }

	@Override
	public void update() { }

	@Override
	public void create() { 
	}

	@Override
	public void resume() {
		number = 1;	// номер вопроса в опроснике
		result = ""; // строка ответа в опроснике
		choice = false;
		
		application.setContentView(R.layout.questions_screen);
		
		btn = (Button)application.findViewById(R.id.button1);
		radiogroup = (RadioGroup)application.findViewById(R.id.radioGroup2);
	    radiogroup.clearCheck();
		
		tvInfo = (TextView)application.findViewById(R.id.restaurant_name);	
		choise0 = (TextView)application.findViewById(R.id.radio0);
		choise1 = (TextView)application.findViewById(R.id.radio1);	
		choise2 = (TextView)application.findViewById(R.id.radio2);	
		choise3 = (TextView)application.findViewById(R.id.radio3);
		buttonText = (TextView)application.findViewById(R.id.button1);
		this.quest(number);
		
		radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio0:
					choise = 0;
					choice = true;
					break;
				case R.id.radio1:
					choise = 1;
					choice = true;
					break;
				case R.id.radio2:
					choise = 2;
					choice = true;
					break;
				case R.id.radio3:
					choise = 3;
					choice = true;
					break;
				default:
					if (number == 9)
						choice = true;
					else 
						choice = false;
					break;
				}
				
					
			}
		});
				
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (choice){
					if(number>=1 && number<=8) result += String.valueOf(choise)+ "!";
					number++;
					quest(number);
					radiogroup.clearCheck();
					if(number == 10) {
						QuestionsScreen.this.process();
						// отрезаем последний символ в строке
						String burgers = null;
						Log.d("yourburger", "builder: "+builder+" size: "+builder.length());
						if(builder!=null && builder.length()!=0) {
							burgers = builder.substring(0, builder.length()-1);
						}	
						application.setScreen(new CompBurgers(application, compatibles));
						compatibles.clear();
						// очищаем переменные результата для нового опроса
						howBurgers = 0;
						builder.setLength(0);
					}
				}
			}
		});
	}

	@Override
	public void pause() { }

	@Override
	public void destroy() { }
	
	public void quest (int number){
		//radiogroup.clearCheck();
		 switch(number){
		    case 1:			
				tvInfo.setText(R.string.questionName1);
				choise0.setText(R.string.questionChoise1_0);
				choise1.setText(R.string.questionChoise1_1);
				choise2.setText(R.string.questionChoise1_2);
				choise3.setText(R.string.questionChoise1_3);
				break;
		    case 2:
		    	// спрятать 2 радиобаттона
				choise2.setVisibility(android.view.View.INVISIBLE);
				choise3.setVisibility(android.view.View.INVISIBLE);
				
				tvInfo.setText(R.string.questionName2);
				choise0.setText(R.string.questionChoise2_0);
				choise1.setText(R.string.questionChoise2_1);
		    	break;
		    case 3:
				tvInfo.setText(R.string.questionName3);
				choise0.setText(R.string.questionChoise3_0);
				choise1.setText(R.string.questionChoise3_1);
				break;
		    case 4:
				tvInfo.setText(R.string.questionName4);
				choise0.setText(R.string.questionChoise4_0);
				choise1.setText(R.string.questionChoise4_1);
				break;
		    case 5:
				tvInfo.setText(R.string.questionName5);
				choise0.setText(R.string.questionChoise5_0);
				choise1.setText(R.string.questionChoise5_1);
				break;
		    case 6:
				tvInfo.setText(R.string.questionName6);
				choise0.setText(R.string.questionChoise6_0);
				choise1.setText(R.string.questionChoise6_1);
				break;
		    case 7:
				tvInfo.setText(R.string.questionName7);
				choise0.setText(R.string.questionChoise7_0);
				choise1.setText(R.string.questionChoise7_1);
				break;
		    case 8:
				tvInfo.setText(R.string.questionName8);
				choise0.setText(R.string.questionChoise8_0);
				choise1.setText(R.string.questionChoise8_1);
				break;				
		    default:
		    	tvInfo.setText("Спасибо за ответы! Мы учли ваши пожелания!");
		    	buttonText.setText("Посмотреть");
		    //	tvInfo.setVisibility(android.view.View.INVISIBLE);
				choise0.setVisibility(android.view.View.INVISIBLE);
				choise1.setVisibility(android.view.View.INVISIBLE);
				choise2.setVisibility(android.view.View.INVISIBLE);
				choise3.setVisibility(android.view.View.INVISIBLE);
		 }
	}
	
	public void process(){
		String burgers="";

		//	burgers =  "Биг мак!0!0!1!0!0!]Биг тейсти!0!1!0!0!0!]";
// !!! ПИЗДА, ЛУЧШЕ НЕ СМОТРЕТЬ!!!!			
		restoran = ((BurgerActivity)application).restaurants;
		
		int countburger = 0;
		int countBurger = 0; 		// здесь указываем сколько бургеров есть в базе.
		
		countburger = restoran[RestInfo.MCDONALDS].burgersInfo.size();
		countBurger += countburger;
		for(int i=0;i<countburger;i++){
			burgers += restoran[RestInfo.MCDONALDS].burgersInfo.get(i).string;
		}
		
		countburger = 0;
		
		countburger = restoran[RestInfo.KFC].burgersInfo.size();
		countBurger += countburger;
		for(int i=0;i<countburger;i++){
			burgers += restoran[RestInfo.KFC].burgersInfo.get(i).string;
		}			

		countburger = 0;
		
		
		countburger = restoran[RestInfo.BURGERKING].burgersInfo.size();
		countBurger += countburger;
		for(int i=0;i<countburger;i++){
			burgers += restoran[RestInfo.BURGERKING].burgersInfo.get(i).string;
		}

		countburger = 0;
		
		countburger = restoran[RestInfo.CARLSJR].burgersInfo.size();
		countBurger += countburger;
		for(int i=0;i<countburger;i++){
			burgers += restoran[RestInfo.CARLSJR].burgersInfo.get(i).string;
		}
		
		countburger = 0;
		countburger = restoran[RestInfo.SUBWAY].burgersInfo.size();
		countBurger += countburger;
		for(int i=0;i<countburger;i++){
			burgers += restoran[RestInfo.SUBWAY].burgersInfo.get(i).string;
		}
			
// !!!!!!!	 ДАЛЬШЕ МОЖНО СМОТРЕТЬ			
		
			int countIngredient = 9;	// здесь указываем сколько ингредиентов в строке + имя.
			int countQuestions = 8;		// здесь указываем количество вопросов в опроснике.
			int numberOfMatches = 0;
			
			String[] bur = new String[countBurger]; 
			String[] ingr = new String[countIngredient]; // здесь указываем сколько ингредиентов в строке + имя.
			String[] opros = new String[countQuestions];
			
			opros=result.split("!"); //парсинг строки ответов (то что наотвечали)
			bur=burgers.split("]"); // парсинг всех бургеров (в bur будут все бургеры вида: Ѕиг маг!0!1!0!1!1!)
			
			tvInfo.setText(""); // ПОТОМ УБРАТЬ!!!
			for (int i=0;i<countBurger; i++, numberOfMatches = 0) {
				ingr=bur[i].split("!"); // парсинг каждой исходной строки с бургером
				String name = ingr[0];

				for (int j=0;j<countQuestions;j++){
					if (ingr[j+1].equals(opros[j])){
						numberOfMatches++; // счетчик совпадений ответов и исходного бургера
					}
				}
		
				float sovpadenie =  ((float)numberOfMatches/(float)countQuestions)*100f;
				int sovp = (int) sovpadenie;
				if (sovpadenie >= 50){
					this.poisk(name, sovp);
					// ТУТ НАДО БУДЕТ СОХРАНИТЬ НАЗВАНИЕ БУРГЕРА ЧТОБЫ ПОТОМ ПЕРЕДАТЬ В ДРУГУЮ ФОРМУ И ВЫВЕСТИ СПИСОК ПОДХОДЯЩИХ БУРГЕРОВ!!!
					//tvInfo.setText(tvInfo.getText() + "Вам подходит: "+ingr[0]+". Процент совпадений: " + str +"%.");
					//tvInfo.setText(tvInfo.getText() + ingr[0]+"." + str +"%");
					builder.append(name+"!"+Math.round(sovpadenie)+"!");
					howBurgers++;
					// если подходит, сохраняем имя и переходим к следующему
				}
			}

	}

// ищет нужный бургер и добавляет в хештайбл
	public void poisk(String name, int sovp){
		int countSize = 0;
		int countItog = 0;
		
		countSize = restoran[RestInfo.MCDONALDS].burgersInfo.size();
		countItog += countSize;
		for(int i=0;i<countSize;i++){
			if (name.equals(restoran[RestInfo.MCDONALDS].burgersInfo.get(i).string)) {
				compatibles.put(restoran[RestInfo.MCDONALDS].burgersInfo.get(i), String.valueOf(sovp));
			}
		}
		
		
		countSize = restoran[RestInfo.KFC].burgersInfo.size();
		countItog += countSize;
		for(int i=0;i<countSize;i++){
			if (name.equals(restoran[RestInfo.KFC].burgersInfo.get(i).string)) {
				compatibles.put(restoran[RestInfo.KFC].burgersInfo.get(i), String.valueOf(sovp));
			}
		}
		
		countSize = restoran[RestInfo.BURGERKING].burgersInfo.size();
		countItog += countSize;
		for(int i=0;i<countSize;i++){
			if (name.equals(restoran[RestInfo.BURGERKING].burgersInfo.get(i).string)) {
				compatibles.put(restoran[RestInfo.BURGERKING].burgersInfo.get(i), String.valueOf(sovp));
			}
		}
		
		countSize = restoran[RestInfo.CARLSJR].burgersInfo.size();
		countItog += countSize;
		for(int i=0;i<countSize;i++){
			if (name.equals(restoran[RestInfo.CARLSJR].burgersInfo.get(i).string)) {
				compatibles.put(restoran[RestInfo.CARLSJR].burgersInfo.get(i), String.valueOf(sovp));
			}
		}
				
		countSize = restoran[RestInfo.SUBWAY].burgersInfo.size();
		countItog += countSize;
		for(int i=0;i<countSize;i++){
			if (name.equals(restoran[RestInfo.SUBWAY].burgersInfo.get(i).string)) {
				compatibles.put(restoran[RestInfo.MCDONALDS].burgersInfo.get(i), String.valueOf(sovp));
			}
		}
	}
	
}

