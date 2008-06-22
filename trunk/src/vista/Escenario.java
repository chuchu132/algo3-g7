package vista;

import java.awt.image.ImageObserver;

import Recursos.SoundCache;
import Recursos.SpriteCache;

public interface Escenario extends ImageObserver  {

	 public static final int PLAY_HEIGHT = 543; 
	 public final int WIDTH = 640;
	 public final int HEIGHT = 480;
	 public final int REFRESH = 50;
	
	 public SpriteCache getSpriteCache();
	 public SoundCache getSoundCache();
	 
}
