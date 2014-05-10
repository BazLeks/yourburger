package ru.brostudios.yourburger;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ru.brostudios.yourburger.screens.MainScreen;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.util.Log;

public class BurgerActivity extends Activity implements GLSurfaceView.Renderer {

	private GL10 gl;
	private Screen screen;
	private GLSurfaceView view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new GLSurfaceView(this);
		view.setRenderer(this);
		view.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		setContentView(view);
	}
		
	public int LoadTexture(GL10 gl, String fileName) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(File.LoadFileFromAsset(getAssets(), fileName));
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			bitmap.recycle();
			return textures[0];
		} catch(Exception e) {
			Log.d("Load texture", e.getMessage());
			return 0;
		}
	}
	
	public void DrawInScreenCoords(GL10 gl, FloatBuffer floatBuffer, 
									int glId, int x, int y, float scale) {
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		floatBuffer.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 4*4, floatBuffer);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		floatBuffer.position(2);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 4*4, floatBuffer);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glTranslatef(x, y, 0);
		//gl.glScalef(scale, scale, 1.0f);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, glId);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glPopMatrix();
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		}
	
	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(screen!=null) screen.present(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.gl = gl;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-width/2f, width/2f, -height/2f, height/2f, -1f, 1f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		this.gl = gl;
		gl.glEnable(GL10.GL_TEXTURE_2D);

		screen = new MainScreen(this);
		screen.create(gl);
	}
	
	public void setScreen(Screen screen) {
		if(screen==null) return;
		this.screen = screen;
		screen.create(gl);
	}
	
	public int getWidth() {
		return view.getWidth();
	}
	
	public int getHeight() {
		return view.getHeight();
	}
	
}