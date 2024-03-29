package vista;

import java.awt.image.ImageObserver;

import Recursos.SoundCache;
import Recursos.SpriteCache;

public interface Escenario extends ImageObserver  {

	public static final int ZONA_INFO = 478; 
	public final int WIDTH = 675;
	public final int HEIGHT = 550;
	public final int REFRESH = 50;

	public SpriteCache getSpriteCache();
	public SoundCache getSoundCache();

}
