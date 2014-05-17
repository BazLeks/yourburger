//package ru.brostudios.yourburger.screens;
//
//import ru.brostudios.framework.Application;
//import ru.brostudios.framework.Input;
//import ru.brostudios.framework.game.Sprite;
//import ru.brostudios.framework.interfaces.ScreenInterface;
//import ru.brostudios.yourburger.Textures;
//
//public class QuestionsScreen extends ScreenInterface {
//
//	private Sprite background;
//	
//	
//	public QuestionsScreen(Application application) {
//		super(application);
//		background = new Sprite(application, Textures.questions, 1, 1);
//	}
//
//	@Override
//	public void present() {
//		background.present(application.getGraphics().getGL(), 0, 0, application.getGraphics().getHeight());
//	}
//
//	@Override
//	public void update() {
//		Input input = application.getInput();
//	}
//
//	@Override
//	public void resume() {
//		
//	}
//
//	@Override
//	public void pause() {
//		
//	}
//
//	@Override
//	public void destroy() {
//		
//	}
//
//}



package ru.brostudios.yourburger.screens;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import ru.brostudios.framework.File;
import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.yourburger.R;
import ru.brostudios.yourburger.R.string;
import ru.brostudios.yourburger.Screen;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class QuestionsScreen extends Activity {
	
	RadioGroup radiogroup;
	TextView tvInfo;
	TextView choise0;
	TextView choise1;
	TextView choise2;
	TextView choise3;
	TextView buttonText;
	OnClickListener radioListener;
	Button btn;
	int choise;
	
	int number; // номер вопроса
	String result;	// номер ответа
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_screen);
        
     //   GLSurfaceView glSurfaceView = (GLSurfaceView) findViewById(R.id.glsurfaceview);


    //    glSurfaceView.setRenderer();  //no longer have to pass the object
        
        
        
        number=1;
        result="";
        
        btn = (Button)findViewById(R.id.button1);
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup2);
		//radiogroup.clearCheck();
		
		tvInfo = (TextView)findViewById(R.id.textView1);	
		choise0 = (TextView)findViewById(R.id.radio0);
		choise1 = (TextView)findViewById(R.id.radio1);	
		choise2 = (TextView)findViewById(R.id.radio2);	
		choise3 = (TextView)findViewById(R.id.radio3);
		buttonText =(TextView)findViewById(R.id.button1);
		
		this.quest(number);
		
	//	btn.setLayoutParams(params);
			radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
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
					// TODO Auto-generated method stub
					if(number<=5 && number>=1)
				    	result += String.valueOf(choise)+ "!";
						number++;
						quest(number);
				}
			});
			
	}
	
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
		    	// спрятать 2 радиобатона
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
		boolean openFile = file.open("burger");
		//if(openFile){
			//burgers = file.read();
			burgers =  "Биг маг!0!0!1!0!0!]Биг тейсти!0!1!0!0!0!]";
			
			int countBurger = 2; // здесь указываем сколько бургеров есть в базе.
			int countIngridient = 6; // здесь указываем сколько ингридиентов в строке + имя.
			int countQuestions = 5;   // Здесь указываем количество вопросов в опроснике
			int numberOfMatches = 0;
			
			
			String[] bur=new String[countBurger]; 
			String[] ingr= new String[countIngridient]; // здесь указываем сколько ингридиентов в строке + имя.
			String[] opros = new String[countQuestions];
			
			opros=result.split("!"); // парсинг строки ответов (то что наотвечали)
			bur=burgers.split("]"); //  парсинг всех бургеров (в bur будут все бургеры вида: Биг маг!0!1!0!1!1!)
			
			tvInfo.setText(""); // ПОТОМ УБРАТЬ!!!!
			for (int i=0;i<countBurger; i++, numberOfMatches=0){
				ingr=bur[i].split("!"); // парсинг каждой исходной строки с бургером
							
							
				for (int j=0;j<countQuestions;j++){
					if (ingr[j+1].equals(opros[j])){
						//tvInfo.setText(tvInfo.getText()+ "11 ");
						numberOfMatches++; // счётчик совпадений ответов и исходного бургера
					}
				}
		
				float sovpadenie =  ((float)numberOfMatches/(float)countQuestions)*100f;
				int sovp = (int) sovpadenie;
				if (sovpadenie >= 50){
					// ТУТ НАДО БУДЕТ СОХРАНИТЬ НАЗВАНИЕ БУРГЕРА ЧТОБЫ ПОТОМ ПЕРЕДАТЬ В ДРУГУЮ ФОРМУ И ВЫВЕСТИ СПИСОК ПОДХОДЯЩИХ БУРГЕРОВ!!!
					String str = String.valueOf(sovp);
					//tvInfo.setText(tvInfo.getText() + "Вам подходит: "+ingr[0]+". Процент совпадений: " + str +"%.");
					tvInfo.setText(tvInfo.getText() + ingr[0]+"." + str +"%");
					// если подходит, сохраняем имя и переходим к следующему
				}
				else {
					String str = String.valueOf(sovp);
					//tvInfo.setText(tvInfo.getText() + "Вам не подходит: "+ingr[0]+". Процент совпадений: " + str +"%.");
				}
		
			}
			
			
			
	//	}
		//else
		//	burgers="222";
		//tvInfo.setText(burgers);
	}
	
}

