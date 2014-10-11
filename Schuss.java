import java.util.LinkedList;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Schuss extends Gameobject {

	public Schuss(int x_Pos, int y_Pos, String Imagepath) throws SlickException {
		super(x_Pos, y_Pos);
		gfx_char = new Image(Imagepath);
		this.hitbox = new Rectangle(x_Pos, y_Pos, gfx_char.getWidth(),
				gfx_char.getHeight());
	}

	public void draw() {
		gfx_char.draw(x_Pos, y_Pos);
	}

	public void laufen(int geschwindigkeit, LinkedList<Schuss> schuss_anzahl) {
		this.y_Pos = y_Pos - geschwindigkeit;

		// Wenn Schuss außerhalb der Y Begrenzung fliegt ->
		// lösche das erste Element(logischerweise immer der erste Schuss
		if (y_Pos <= 0) {
			schuss_anzahl.removeFirst();
		}

		hitbox.setLocation(x_Pos, y_Pos);
	}

}
