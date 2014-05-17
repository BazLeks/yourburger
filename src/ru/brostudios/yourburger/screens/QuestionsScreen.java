package ru.brostudios.yourburger.screens;
/**
 * Автор: Юра Леонтьев
 * Дата: 17.05.2014
 */

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
import ru.brostudios.yourburger.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class QuestionsScreen extends ScreenInterface {
	
	private RadioGroup radiogroup;
	private TextView tvInfo, buttonText;
	private TextView choise0, choise1, choise2, choise3;
	//private OnClickListener radioListener;
	private Button btn;
	private int choise;
	
	private int number; 	// номер вопроса
	private String result;	// номер ответа 
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
		number = 1;	// номер вопроса в опроснике
		result = ""; // строка ответа в опроснике
		
		application.setContentView(R.layout.questions_screen);
		
		btn = (Button)application.findViewById(R.id.button1);
		radiogroup = (RadioGroup)application.findViewById(R.id.radioGroup2);
		// radiogroup.clearCheck();
		
		tvInfo = (TextView)application.findViewById(R.id.textView1);	
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
				Log.d("", "click()");
				if(number<=5 && number>=1)
			    	result += String.valueOf(choise)+ "!";
					number++;
					quest(number);
			}
		});
		int a = 5;
	}

	@Override
	public void resume() {
		
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
		    	// �������� 2 �����������
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
		    	tvInfo.setText("Спасибо за ответы! Мы учли ваши пожелания!");
		    	buttonText.setText("Посмотреть");
		    //	tvInfo.setVisibility(android.view.View.INVISIBLE);
				choise0.setVisibility(android.view.View.INVISIBLE);
				choise1.setVisibility(android.view.View.INVISIBLE);
				choise2.setVisibility(android.view.View.INVISIBLE);
				choise3.setVisibility(android.view.View.INVISIBLE);
				
				this.process();
		 }
	}
	
	public void process(){
		File file = new File();
		String burgers="";
		//boolean openFile = file.open("burger");
		//if(openFile){
			//burgers = file.read();
			burgers =  "Биг мак!0!0!1!0!0!]Биг тейсти!0!1!0!0!0!]";
			
			int countBurger = 2; 		// здесь указываем сколько бургеров есть в базе.
			int countIngridient = 6;	// здесь указываем сколько ингридиентов в строке + имя.
			int countQuestions = 5;		// здесь указываем количество вопросов в опроснике.
			int numberOfMatches = 0;
			
			String[] bur = new String[countBurger]; 
			String[] ingr = new String[countIngridient]; // здесь указываем сколько ингридиентов в строке + имя.
			String[] opros = new String[countQuestions];
			
			opros=result.split("!"); // парсинг строки ответов (то что наотвечали)
			bur=burgers.split("]"); // парсинг всех бургеров (в bur будут все бургеры вида: Ѕиг маг!0!1!0!1!1!)
			
			tvInfo.setText(""); // ����� ������!!!!
			for (int i=0;i<countBurger; i++, numberOfMatches=0){
				ingr=bur[i].split("!"); // ������� ������ �������� ������ � ��������
							
							
				for (int j=0;j<countQuestions;j++){
					if (ingr[j+1].equals(opros[j])){
						numberOfMatches++; // ������� ���������� ������� � ��������� �������
					}
				}
		
				float sovpadenie =  ((float)numberOfMatches/(float)countQuestions)*100f;
				int sovp = (int) sovpadenie;
				if (sovpadenie >= 50){
					// ��� ���� ����� ��������� �������� ������� ����� ����� �������� � ������ ����� � ������� ������ ���������� ��������!!!
					String str = String.valueOf(sovp);
					//tvInfo.setText(tvInfo.getText() + "��� ��������: "+ingr[0]+". ������� ����������: " + str +"%.");
					tvInfo.setText(tvInfo.getText() + ingr[0]+"." + str +"%");
					// ���� ��������, ��������� ��� � ��������� � ����������
				}
				else {
					//String str = String.valueOf(sovp);
					//tvInfo.setText(tvInfo.getText() + "��� �� ��������: "+ingr[0]+". ������� ����������: " + str +"%.");
				}
		
			}
		
	//	}
		//else
		//	burgers="222";
		//tvInfo.setText(burgers);
	}


	
}

