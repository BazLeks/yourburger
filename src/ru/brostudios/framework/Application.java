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
		Log.d("", "Нажата кнопка назад");
		try {
			currentScreen.pause();
			currentScreen = screens.pop();
			if(currentScreen==null) super.onBackPressed();
			currentScreen.resume();
		} catch(Exception e) {
			Toast toast = Toast.makeText(this, e.getCause()+"", Toast.LENGTH_SHORT);
			toast.show();
			super.onBackPressed();
		}
	}
	
	public final MapView getMapView() { return mapView; }
	
	public Application() {
		Log.d("BroStudios", "AndroidGame.AndroidGame()");
	}
	
	// Activity methods
	// ------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("BroStudios", "AndroidGame.onCreate()");
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
		Log.d("BroStudios", "FrameworkActivity.onResume()");
		super.onResume();
		if(currentScreen!=null) currentScreen.resume();
	}

	@Override
	public void onPause() {
		Log.d("BroStudios", "FrameworkActivity.onPause()");
		if(currentScreen!=null) currentScreen.pause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		Log.d("BroStudios", "FrameworkActivity.onDestroy()");
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
					if(currentScreen == null) { Log.d("BroStudios", "Unable to set currentScreen = null"); return; }
					screens.push(Application.this.currentScreen);
					Application.this.currentScreen.pause();
					Application.this.currentScreen.destroy();
					currentScreen.resume();
					currentScreen.update();
					Application.this.currentScreen = currentScreen;
				}
			}
		});
	}
	

	public Graphics getGraphics() {
		return graphics;
	}
	
	public Input getInput() {
		return input;
	}

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