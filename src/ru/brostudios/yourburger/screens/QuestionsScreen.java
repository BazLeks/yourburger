package ru.brostudios.yourburger.screens;
<<<<<<< HEAD
/**
 * Автор: Юра Леонтьев
 * Дата: 17.05.2014
 */
=======

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
>>>>>>> origin/lex
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import ru.brostudios.framework.Application;
import ru.brostudios.framework.File;
import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.yourburger.R;


public class QuestionsScreen extends ScreenInterface {
	
	StringBuilder builder = new StringBuilder();
	
	private RadioGroup radiogroup;
	private TextView tvInfo, buttonText;
	private TextView choise0, choise1, choise2, choise3;
	//private OnClickListener radioListener;
	private Button btn;
	private int choise;
	
<<<<<<< HEAD
	private int number; 	// номер вопроса
	private String result;	// номер ответа 
	private int howBurgers;	// сколько бургеров подходит
=======
	private int number; 	// РЅРѕРјРµСЂ РІРѕРїСЂРѕСЃР°
	private String result;	// РЅРѕРјРµСЂ РѕС‚РІРµС‚Р° 
>>>>>>> origin/lex
// *************************************************************
	
	public QuestionsScreen(Application application) {
		super(application);
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
		number = 1;	// РЅРѕРјРµСЂ РІРѕРїСЂРѕСЃР° РІ РѕРїСЂРѕСЃРЅРёРєРµ
		result = ""; // СЃС‚СЂРѕРєР° РѕС‚РІРµС‚Р° РІ РѕРїСЂРѕСЃРЅРёРєРµ
		
		application.setContentView(R.layout.questions_screen);
		
		btn = (Button)application.findViewById(R.id.button1);
		radiogroup = (RadioGroup)application.findViewById(R.id.radioGroup2);
		// radiogroup.clearCheck();
		
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
					break;
				case R.id.radio1:
					choise = 1;
					break;
				case R.id.radio2:
					choise = 2;
					break;
				case R.id.radio3:
					choise = 3;
					break;
				}
			}
		});
				
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(number>=1 && number<=5) result += String.valueOf(choise)+ "!";
				number++;
				quest(number);
				if(number == 7) {
					QuestionsScreen.this.process();
					// отрезаем последний символ в строке
					String burgers = null;
					Log.d("yourburger", "builder: "+builder+" size: "+builder.length());
					if(builder!=null && builder.length()!=0) {
						burgers = builder.substring(0, builder.length()-1);
					}
					application.setScreen(new CompBurgers(application, burgers, howBurgers));
					// очищаем переменные результата для нового опроса
					howBurgers = 0;
					builder.setLength(0);
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
		    	// СЃРїСЂСЏС‚Р°С‚СЊ 2 СЂР°РґРёРѕР±Р°С‚С‚РѕРЅР°
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
		    default:
		    	tvInfo.setText("РЎРїР°СЃРёР±Рѕ Р·Р° РѕС‚РІРµС‚С‹! РњС‹ СѓС‡Р»Рё РІР°С€Рё РїРѕР¶РµР»Р°РЅРёСЏ!");
		    	buttonText.setText("РџРѕСЃРјРѕС‚СЂРµС‚СЊ");
		    //	tvInfo.setVisibility(android.view.View.INVISIBLE);
				choise0.setVisibility(android.view.View.INVISIBLE);
				choise1.setVisibility(android.view.View.INVISIBLE);
				choise2.setVisibility(android.view.View.INVISIBLE);
				choise3.setVisibility(android.view.View.INVISIBLE);
		 }
	}
	
	public void process(){
		
		File file = new File();
		String burgers="";
		//boolean openFile = file.open("burger");
		//if(openFile){
			//burgers = file.read();
			burgers =  "Р‘РёРі РјР°Рє!0!0!1!0!0!]Р‘РёРі С‚РµР№СЃС‚Рё!0!1!0!0!0!]";
			
<<<<<<< HEAD
			int countBurger = 2; 		// здесь указываем сколько бургеров есть в базе.
			int countIngredient = 6;	// здесь указываем сколько ингредиентов в строке + имя.
			int countQuestions = 5;		// здесь указываем количество вопросов в опроснике.
			int numberOfMatches = 0;
			
			String[] bur = new String[countBurger]; 
			String[] ingr = new String[countIngredient]; // здесь указываем сколько ингредиентов в строке + имя.
=======
			int countBurger = 2; 		// Р·РґРµСЃСЊ СѓРєР°Р·С‹РІР°РµРј СЃРєРѕР»СЊРєРѕ Р±СѓСЂРіРµСЂРѕРІ РµСЃС‚СЊ РІ Р±Р°Р·Рµ.
			int countIngridient = 6;	// Р·РґРµСЃСЊ СѓРєР°Р·С‹РІР°РµРј СЃРєРѕР»СЊРєРѕ РёРЅРіСЂРёРґРёРµРЅС‚РѕРІ РІ СЃС‚СЂРѕРєРµ + РёРјСЏ.
			int countQuestions = 5;		// Р·РґРµСЃСЊ СѓРєР°Р·С‹РІР°РµРј РєРѕР»РёС‡РµСЃС‚РІРѕ РІРѕРїСЂРѕСЃРѕРІ РІ РѕРїСЂРѕСЃРЅРёРєРµ.
			int numberOfMatches = 0;
			
			String[] bur = new String[countBurger]; 
			String[] ingr = new String[countIngridient]; // Р·РґРµСЃСЊ СѓРєР°Р·С‹РІР°РµРј СЃРєРѕР»СЊРєРѕ РёРЅРіСЂРёРґРёРµРЅС‚РѕРІ РІ СЃС‚СЂРѕРєРµ + РёРјСЏ.
>>>>>>> origin/lex
			String[] opros = new String[countQuestions];
			
			opros=result.split("!"); // РїР°СЂСЃРёРЅРі СЃС‚СЂРѕРєРё РѕС‚РІРµС‚РѕРІ (С‚Рѕ С‡С‚Рѕ РЅР°РѕС‚РІРµС‡Р°Р»Рё)
			bur=burgers.split("]"); // РїР°СЂСЃРёРЅРі РІСЃРµС… Р±СѓСЂРіРµСЂРѕРІ (РІ bur Р±СѓРґСѓС‚ РІСЃРµ Р±СѓСЂРіРµСЂС‹ РІРёРґР°: Р…РёРі РјР°Рі!0!1!0!1!1!)
			
<<<<<<< HEAD
			tvInfo.setText(""); // ПОТОМ УБРАТЬ!!!
			for (int i=0;i<countBurger; i++, numberOfMatches = 0) {
				ingr=bur[i].split("!"); // парсинг каждой исходной строки с бургером
				String name = ingr[0];
=======
			tvInfo.setText(""); // РџРћРўРћРњ РЈР‘Р РђРўР¬!!!
			for (int i=0;i<countBurger; i++, numberOfMatches=0){
				ingr=bur[i].split("!"); // РїР°СЂСЃРёРЅРі РєР°Р¶РґРѕР№ РёСЃС…РѕРґРЅРѕР№ СЃС‚СЂРѕРєРё СЃ Р±СѓСЂРіРµСЂРѕРј
							
							
>>>>>>> origin/lex
				for (int j=0;j<countQuestions;j++){
					if (ingr[j+1].equals(opros[j])){
						numberOfMatches++; // СЃС‡РµС‚С‡РёРє СЃРѕРІРїР°РґРµРЅРёР№ РѕС‚РІРµС‚РѕРІ Рё РёСЃС…РѕРґРЅРѕРіРѕ Р±СѓСЂРіРµСЂР°
					}
				}
		
				float sovpadenie =  ((float)numberOfMatches/(float)countQuestions)*100f;
				int sovp = (int) sovpadenie;
				if (sovpadenie >= 50){
					// РўРЈРў РќРђР”Рћ Р‘РЈР”Р•Рў РЎРћРҐР РђРќР�РўР¬ РќРђР—Р’РђРќР�Р• Р‘РЈР Р“Р•Р Рђ Р§РўРћР‘Р« РџРћРўРћРњ РџР•Р Р•Р”РђРўР¬ Р’ Р”Р РЈР“РЈР® Р¤РћР РњРЈ Р� Р’Р«Р’Р•РЎРўР� РЎРџР�РЎРћРљВ РџРћР”РҐРћР”РЇР©Р�РҐ Р‘РЈР Р“Р•Р РћР’!!!
					String str = String.valueOf(sovp);
<<<<<<< HEAD
					//tvInfo.setText(tvInfo.getText() + "Вам подходит: "+ingr[0]+". Процент совпадений: " + str +"%.");
					//tvInfo.setText(tvInfo.getText() + ingr[0]+"." + str +"%");
					builder.append(name+"!"+Math.round(sovpadenie)+"!");
					howBurgers++;
					// если подходит, сохраняем имя и переходим к следующему
=======
					//tvInfo.setText(tvInfo.getText() + "Р’Р°Рј РїРѕРґС…РѕРґРёС‚: "+ingr[0]+". РџСЂРѕС†РµРЅС‚ СЃРѕРІРїР°РґРµРЅРёР№: " + str +"%.");
					tvInfo.setText(tvInfo.getText() + ingr[0]+"." + str +"%");
					// РµСЃР»Рё РїРѕРґС…РѕРґРёС‚, СЃРѕС…СЂР°РЅСЏРµРј РёРјСЏ Рё РїРµСЂРµС…РѕРґРёРј Рє СЃР»РµРґСѓСЋС‰РµРјСѓ
>>>>>>> origin/lex
				}
				else {
					//String str = String.valueOf(sovp);
					//tvInfo.setText(tvInfo.getText() + "Р’Р°Рј РЅРµ РїРѕРґС…РѕРґРёС‚: "+ingr[0]+". РџСЂРѕС†РµРЅС‚ СЃРѕРІРїР°РґРµРЅРёР№: " + str +"%.");
				}
			}
	//	}
		//else
		//	burgers="222";
		//tvInfo.setText(burgers);
	}


	
}

