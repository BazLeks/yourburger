//	╘ ALEXEY BORISOV
//мюуси ме лемърэ рср мхвецн!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//якедсчыхи йнд охяюк яюлши мсаяйхи ашдкнйндеп ян бяецн яберю, дюфе хмдсяш охьср ксвье
//бмхлюмхе дюкэье бяе мюгбюмхъ бяеу бюфмшу лернднб асдср мювхмюрэяъ я анкэьни асйбш,
//онрнлс врн лме рюй мпюбхряъ

package ru.brostudios.framework.gui;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.game.Texture;
import ru.brostudios.framework.interfaces.InputInterface.TouchEvent;

public class Button {
	//цнбмнймнойю
	//с мее рср ве_рн еярэ.. рейярспю рюл, цнбмнасттеп, еые йюйюъ_рн уепмъ
	private Application game;
	private Texture texture;
	private FloatBuffer floatBuffer;
	private enum STATE { BUTTON_UP, BUTTON_DOWN }
	private STATE state;
	private boolean active;
	private float x, y;
	private float width, height;
	private float scale;
	
//гдеяэ ашкю кхмхъ, ашкю мюуси
	
	public Button(Application game, Texture texture, float scale) {
		//цнбмнйнмярпсйрнп ймнойх
		this.game = game;
		active = false;
		state = STATE.BUTTON_UP;
		this.texture = texture;
		this.width = texture.width;
		this.height = texture.height;
		this.x = 0;
		this.y = 0;
		float screenW = game.getGraphics().getWidth();
		float screenH = game.getGraphics().getHeight();
		float scaleNew = 1.0f;
		if(screenW <= screenH) scaleNew = scale*screenW/texture.width;
		else scaleNew = scale*screenH/texture.height;
		this.scale = scaleNew;
		//дюкэье лш янгдюел цнбмнткнюрасттеп
		//лнфмн дюфе х еые пюгнй янгдюрэ.
		//ю онвелс аш х мер? с мюя фе днусъ бпелемх, врна бяе оепеохяшбюрэ ямнбю х ямнбю
		floatBuffer = ByteBuffer.allocateDirect((2+2)*4*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		floatBuffer.put(new float[] {
			-texture.width/2.0f, +texture.height/2.0f, 0.0f, 0.0f,
			-texture.width/2.0f, -texture.height/2.0f, 0.0f, 1.0f,
			+texture.width/2.0f, +texture.height/2.0f, 1.0f, 0.0f,
			+texture.width/2.0f, -texture.height/2.0f, 1.0f, 1.0f
		});
		//ме мюуеп кемэ еые ндхм янгдюбюрэ, йюй мхасдэ б якедсчыхи пюг
	}
	
	public void Update(List<TouchEvent> touchEvents) {
		//цнбмнюодеир
		//врн дюкэье гю бшяеп блеярн йндю ме онмърмн дюфе рюйнлс мсас йюй лме
		boolean isTouchDown = false;
		for(int i=0; i<touchEvents.size(); i++) {
			TouchEvent touchEvent = touchEvents.get(i);
			if(touchEvent.type != TouchEvent.TOUCH_DOWN) continue;
			float tx = touchEvent.x - game.getGraphics().getWidth()/2.0f;
			float ty = game.getGraphics().getHeight()/2.0f - touchEvent.y;
			float w = width*scale;
			float h = height*scale;
			if( tx >= x-w/2 && tx <= x+w/2 && ty >= y-h/2 && ty <= y+h/2 ) {
				//япюмюъ опнбепйю мю оноюдюмхе б жемрп йпсцю
				//дю бяе ймнойх рхон йпсцкше х мюяпюрэ мю рн, врн нмх лнцср ашрэ йбюдпюрмше
				//ъ ф опедсопефдюк б мювюке, врн дюфе нпюмцсрюмц охьер йнд ксвье, вел ъ
				//янаярбеммн онщрнлс лнфмн ее бшохкхбюрэ мюуеп
				isTouchDown = true;
				//рпс
			}
		}
		if(isTouchDown) {
			//щрн нвемэ бюфмюъ упемэ дкъ дюкэмеиьеи пюанрш цнбмнймнойх
			state = STATE.BUTTON_DOWN;
			active = !active;
		}
		else {
			//йюй х щрн рнфе
			state = STATE.BUTTON_UP;
		}
	}
	
	public void Draw() {
		//цнбмннрпхянбйю
		//дюкэье асдер ярпюьмн рюй, врн ъ сфе ме унвс охяюрэ щрнр бшяеп
		//ю лнфер мю йюмбюяе гюуепювхрэ нрпхянбйс, мс ю ускх
		GL10 gl = game.getGraphics().getGL();
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
		gl.glScalef(scale, scale, 1.0f);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture.glID);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glPopMatrix();
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		//тсу, бпнде мюпхянбюк цнбмнймнойс
	}
	
	public void MoveInScreenCoords(float x, float y) {
		//дбхцюел цнбмнймнойс
		this.x = x;
		this.y = y;
	}
	
	public void MoveInScreenPercent(float x, float y) {
		//дбхцюел цнбмнймнойс
		float screenW = game.getGraphics().getWidth();
		float screenH = game.getGraphics().getHeight();
		this.x = x*(screenW/2-texture.width*scale/2);
		this.y = y*(screenH/2-texture.height*scale/2);
	}
	
	public void SetScale(float scale) {
		//хглемъел цнбмнпюглеп цнбмнймнойх
		this.scale = scale;
	}
	
	public void SetState(STATE state) {
		//лемел цнбмнярнярнъмхе цнбмнймнойх
		this.state = state;
	}
	
	public void SetActive(boolean active) {
		//лемъел цнбмнюйрхбмнярэ цнбмнймнойх
		this.active = active;
	}
	
	public void ChangeTexture(Texture texture) {
		//лемъел цнбмнрейярспс цнбмнймнойх
		this.texture = texture;
	}
	
	public boolean isTouchDown() {
		if(state == STATE.BUTTON_DOWN) return true;
		else return false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public STATE getState() {
		return state;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public float getScale() {
		return scale;
	}
	
	//йнмеж цнбмнймнойх
}