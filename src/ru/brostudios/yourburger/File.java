package ru.brostudios.yourburger;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.util.Log;

public class File {
	public static InputStream LoadFileFromAsset(AssetManager assets, String fileName) {
		try {
			return assets.open(fileName);
		} catch(IOException e) {
			Log.d("YourBurger", "Unable to load file from assets: "+fileName);
		}
		return null;
	}
}
