package ru.brostudios.framework.game;

import ru.brostudios.framework.Application;

public abstract class GameObject {
	
	protected final Application game;
	protected final World world;
	
	protected int state;
	
	protected Sprite[] sprites;
	
	protected float x, y, z;
	protected float SX, SY, SCALE;
	protected float size;
	protected float speed;
	
// *****************************************************
	public GameObject(Application game, World world) {
		this.game = game; this.world = world;
	}
	
	public void moveTo(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void moveOn(float dx, float dy, float dz) {
		this.x += dx;
		this.y += dy;
		this.z += dz;
	}
	
	public void moveToCenter() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public void resize(float size) {
		this.size = size;
	}
	
	public void present() {
		
		// если произошла какая-либо ошибка в спрайте, то не рисуем
		if(state>=sprites.length || sprites[state]==null) return;
		
		// нормализуем координаты (приводим к единичным размерам)
		float x = this.x/(float)world.width;
		float y = this.y/(float)world.height;
		float z = this.z/(float)world.depth;
		
		// получаем размеры экрана
		int screenW = game.getGraphics().getWidth();
		int screenH = game.getGraphics().getHeight();

		// нормализуем размер объекта - зависит от ширины экрана
		float size = this.size/(float)world.width*game.getGraphics().getWidth();
		
		// расчёт экранных координат относительно игровых с поправкой на размеры
		SX = (1f-z)*((1f-x)*(-screenW/2f+size/2f)+x*(screenW/2f-size/2f))+
				 z*((1f-x)*(-screenW/3f+size/2f)+x*(screenW/3f-size/2f));
		SY = (1f-y)*((1f-z)*(-screenH/2f+size/2f)+z*(-screenH/4f-size/2f))+
						  y*((1f-z)*(+screenH/4f+size/2f)+z*(+screenH/2f-size/2f));
		SCALE = (1f-z/2f); // zmin=0.5, zmax=1.0
		
		// рисуем спрайт на экране
		sprites[state].present(game.getGraphics().getGL(), SX, SY, size*SCALE);
		sprites[state].nextFrame();
	}
	
	public boolean hitTestObject(GameObject object) {
		float dx = object.getX() - x;
		float dy = object.getY() - y;
		float dz = object.getZ() - z;
		if(Math.sqrt(dx*dx+dy*dy+dz*dz)<(this.size+object.getSize())/2f) return true;
		else return false;
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getZ() { return z; }
	public float getSize() { return size; }

	public abstract void update();
	
}