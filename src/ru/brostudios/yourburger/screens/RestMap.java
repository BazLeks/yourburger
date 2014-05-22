package ru.brostudios.yourburger.screens;

import ru.brostudios.framework.interfaces.ScreenInterface;
import ru.brostudios.framework.Application;
import com.google.android.gms.maps.MapView;

public class RestMap extends ScreenInterface {


	private MapView view;
	
// ******************************************************
	
	public RestMap(final Application application) {
		super(application);	
		view = application.getMapView();
	}

	@Override
	public void present() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void resume() {
		view.onResume();
		application.setContentView(view);
		view.getMap().setMyLocationEnabled(true);
	}

	@Override
	public void pause() {
		view.onPause();
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
}