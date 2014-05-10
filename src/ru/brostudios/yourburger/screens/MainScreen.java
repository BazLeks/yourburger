package ru.brostudios.yourburger.screens;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ru.brostudios.yourburger.BurgerActivity;
import ru.brostudios.yourburger.Screen;

public class MainScreen extends Screen {
	
	private FloatBuffer buffer;
	private int glId;

// *******************************************************
	
	public MainScreen(BurgerActivity activity) {
		super(activity);
	}
	
	@Override
	public void create(GL10 gl) {
		buffer = ByteBuffer.allocateDirect(4*4*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(new float[] { 
				-activity.getWidth()/1.5f, +activity.getHeight()/2.0f, 0.0f, 0.0f,
				-activity.getWidth()/1.5f, -activity.getHeight()/2.0f, 0.0f, 1.0f,
				+activity.getWidth()/1.5f, +activity.getHeight()/2.0f, 1.0f, 0.0f,
				+activity.getWidth()/1.5f, -activity.getHeight()/2.0f, 1.0f, 1.0f });
		buffer.position(0);
		
		glId = activity.LoadTexture(gl, "back1.png");
	}
		
	
	@Override
	public void present(GL10 gl) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, glId);
		activity.DrawInScreenCoords(gl, buffer, glId, 0, 0, 1.0f);
	}




}
