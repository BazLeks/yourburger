package ru.brostudios.framework.gui;

import java.util.ArrayList;
import java.util.List;

import ru.brostudios.framework.Application;
import ru.brostudios.framework.game.Texture;
import ru.brostudios.framework.interfaces.InputInterface.TouchEvent;

public class BroButton extends UserInterface implements Runnable {
	
	private List<Button> buttons;
	private List<String> names;
	private List<Coord> coords, coordsTo;
	private float speed;
	private boolean runnable;
	private Thread thread;
	
// ***************************************************
	
	public class Coord {
		public float x, y;
		
		public Coord(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}
		
// ***************************************************
	
	public BroButton(Application game, String name, Texture texture, float scale, float x, float y, float speed) {
		super(game);
		buttons = new ArrayList<Button>();
		names = new ArrayList<String>();
		coords = new ArrayList<Coord>();
		coordsTo = new ArrayList<Coord>();
		Button button = new Button(game, texture, scale);
		button.MoveInScreenCoords(x, y);
		button.SetActive(false);
		buttons.add(button);
		names.add(name);
		coords.add(new Coord(x, y));
		coordsTo.add(new Coord(x, y));
		if(speed >= 0.0f && speed <= 1.0f) this.speed = speed;
		else speed = 1.0f;
	}

	@Override
	public void present() {
		if(buttons.get(0).isActive() || runnable) for(int i=1; i<buttons.size(); i++) buttons.get(i).Draw();
		buttons.get(0).Draw();
	}
	
	@Override
	public void update(List<TouchEvent> touchEvents) {
		if(buttons.get(0).isActive()) for(int i=1; i<buttons.size(); i++) buttons.get(i).Update(touchEvents);
		buttons.get(0).Update(touchEvents);
	}
	
	@Override
	public void run() {
		while(runnable) {
			boolean flag = true;
			for(int i=1; i<buttons.size(); i++) {
				float nowX = buttons.get(i).getX();
				float nowY = buttons.get(i).getY();
				float toX = coordsTo.get(i).x;
				float toY = coordsTo.get(i).y;
				float offsetX = (toX - nowX)*speed;
				float offsetY = (toY - nowY)*speed;
				if(Math.abs(nowX - toX) <= 2.0f && Math.abs(nowY - toY) <= 2.0f) continue;
				float newX = nowX + offsetX;
				float newY = nowY + offsetY;
				buttons.get(i).MoveInScreenCoords(newX, newY);
				flag = false;
			}
			if(flag) break;
		}
		
		for(int i=0; i<coords.size(); i++) {
			Coord temp = new Coord(coords.get(i).x, coords.get(i).y);
			coords.set(i, coordsTo.get(i));
			coordsTo.set(i, temp);
		}
		
		runnable = false;
	}

	public void addButton(String name, Texture texture, float scale, float toX, float toY) {
		float x = buttons.get(0).getX();
		float y = buttons.get(0).getY();
		Button button = new Button(game, texture, scale);
		button.MoveInScreenCoords(x, y);
		button.SetActive(false);
		buttons.add(button);
		names.add(name);
		coords.add(new Coord(x, y));
		coordsTo.add(new Coord(toX, toY));
	}
	
	public void active() {
		if(thread != null) while(thread.isAlive()) { runnable = false; }
		thread = new Thread(this);
		runnable = true;
		thread.start();
	}
	
	public Button getButton(String name) {
		return buttons.get(names.indexOf(name));
	}
	
}