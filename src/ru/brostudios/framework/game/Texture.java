package ru.brostudios.framework.game;

import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.File;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class Texture {
	
	public Application game;
	public int glID, width, height;
	public String filePath;
	
// ***************************************************
	
	public Texture(Application game, String path) {
		this.game = game;
		this.filePath = path;
		load();
	}
	
	public void load() {
		GL10 gl = game.getGraphics().getGL();
		gl.glEnable(GL10.GL_TEXTURE_2D);
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		try {
			InputStream in = File.LoadFileFromAssets(game.getAssets(), filePath);
			// ������ ������ ������� ��������
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(in, null, options);
			in.close();
			int origWidth = options.outWidth; //�������� ������ 
			int origHeight = options.outHeight; //�������� ������ 
			int bytesPerPixel = 4; // 4 ����� �� ������ (argb-������)
			int maxSize = 480 * 800 * bytesPerPixel; //����������� ����������� ������ Bitmap
			int desiredWidth = 512; //������ ������
			int desiredHeight = 512; //������ ������
			int desiredSize = desiredWidth * desiredHeight * bytesPerPixel; //����������� ����������� ������ Bitmap ��� �������� width � height
			if (desiredSize < maxSize) maxSize = desiredSize;
			int scale = 1; //��������� ����������
//			int origSize = origWidth * origHeight * bytesPerPixel;
			//����������� ��������� ����������
			if (origWidth > origHeight) {
				scale = Math.round((float) origHeight / (float) desiredHeight);
			} else {
				scale = Math.round((float) origWidth / (float) desiredWidth);
			}
			options = new BitmapFactory.Options();
			options.inSampleSize = scale;
			options.inPreferredConfig = Config.ARGB_8888;
			
			// ����������� ��������� �����
			in = File.LoadFileFromAssets(game.getAssets(), filePath);
			Bitmap bitmap = BitmapFactory.decodeStream(in, null, options); //���������� Bitmap
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			this.width = bitmap.getWidth(); this.height = bitmap.getHeight();
			bitmap.recycle();
		} catch(Exception e) {
			Log.d("Load texture", e.getMessage());
		}
		Log.d("class Texture", "Good loading texture: "+filePath);
		glID = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}
	
	public final int getWidth() { return width; }
	public final int getHeight() { return height; }
}