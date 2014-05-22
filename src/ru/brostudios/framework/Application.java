package ru.brostudios.framework;
import java.util.Stack;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.google.android.gms.maps.MapView;
import ru.brostudios.framework.interfaces.FrameworkInterface;
import ru.brostudios.framework.interfaces.ScreenInterface;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public abstract class Application extends Activity implements FrameworkInterface, GLSurfaceView.Renderer {
	
	protected final boolean DEBUG = false; 
	
	private Stack<ScreenInterface> screens;
	private ScreenInterface currentScreen;	
	private GLSurfaceView glView;
	private Graphics graphics;
	private MapView mapView;
	private Input input;
	
// ********************************************************************************
	
	@Override
	public void onBackPressed() {
		Log.d("yourburger", "Нажата кнопка назад");
		try {
			if(screens.isEmpty()) super.onBackPressed();
			else {
				currentScreen.pause();
				currentScreen = screens.pop();
				currentScreen.resume();
			}
		} catch(Exception e) {
			Toast toast = Toast.makeText(this, e.getMessage()+": "+e.getCause(), Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	public final MapView getMapView() { return mapView; }
	
	public void goToMainMenu() {
		screens = new Stack<ScreenInterface>();
		currentScreen = getStartScreen();
		currentScreen.create();
		currentScreen.resume();
	}
	
	public Application() {
		Log.d("BroStudios", "new Application.Application()");
	}
	
	// Activity methods
	// ------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("BroStudios", "application.onCreate()");
		super.onCreate(savedInstanceState);
		screens = new Stack<ScreenInterface>();

		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLcurrentScreen, WindowManager.LayoutParams.FLAG_FULLcurrentScreen);
		//getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		
		mapView = new MapView(this);
		mapView.onCreate(savedInstanceState);
		glView = new GLSurfaceView(this);
		glView.setRenderer(this);
		glView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		currentScreen = getStartScreen();
		//setContentView(glView);
		
		graphics = new Graphics(glView);
		input = new Input(this);
	}

	@Override
	public void onResume() {
		Log.d("BroStudios", "application.onResume()");
		super.onResume();
		if(currentScreen!=null) currentScreen.resume();
	}

	@Override
	public void onPause() {
		Log.d("BroStudios", "application.onPause()");
		if(currentScreen!=null) currentScreen.pause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		Log.d("BroStudios", "application.onDestroy()");
		Toast toast = Toast.makeText(this, "Вы закрыли приложение", Toast.LENGTH_SHORT);
		toast.show();
		if(currentScreen!=null) currentScreen.destroy();
		super.onDestroy();	
	}
	
	// ------------------------------------------------------------
	
	public abstract ScreenInterface getStartScreen();
	public abstract void loadGameTextures();
	
	public ScreenInterface getCurrentScreen() {
		return currentScreen;
	}
	
	public void setScreen(final ScreenInterface currentScreen) {
		
		this.runOnUiThread(new Runnable() {	
			@Override
			public void run() {
				synchronized (this) {
					if(currentScreen == null) { Log.d("BroStudios", "Устанавливаемый экран равен null"); return; }
					screens.push(Application.this.currentScreen);
					Application.this.currentScreen.pause();
					Application.this.currentScreen.destroy();
					currentScreen.create();
					currentScreen.resume();
					currentScreen.update();
					Application.this.currentScreen = currentScreen;
				}
			}
		});
	}
	

	public final Graphics getGraphics() { return graphics; }
	public final Input getInput() { return input; }

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		currentScreen.update();
		currentScreen.present();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		graphics.setGL(gl);
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-width/2.0f, width/2.0f, -height/2.0f, height/2.0f, -1.0f, 1.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		graphics.setGL(gl);
		loadGameTextures();
		currentScreen.create();
	}
}