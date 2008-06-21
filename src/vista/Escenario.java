package vista;

import java.awt.image.ImageObserver;

import Recursos.SoundCache;
import Recursos.SpriteCache;

public interface Escenario extends ImageObserver  {

	 public static final int PLAY_HEIGHT = 543; 
	 public final int WIDTH = 1220;
	 public final int HEIGHT = 600;
	 public final int REFRESH = 10;
	
	 public SpriteCache getSpriteCache();
	 public SoundCache getSoundCache();
	 
}
