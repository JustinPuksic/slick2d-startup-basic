import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Player extends Gameobject {

	private Input steuerung;

	public Player(int x_Pos, int y_Pos, Input steuerung, String Imagepath)
			throws SlickException {
		super(x_Pos, y_Pos);
		this.steuerung = steuerung;
		gfx_char = new Image(Imagepath);
		this.hitbox = new Rectangle(x_Pos, y_Pos, gfx_char.getWidth(),
				gfx_char.getHeight());
	}

	public void laufen(int geschwindigkeit) {
		// Hier steht die Bewegung auf horizontaler Ebene.
		// Pfeiltaste links
		if (steuerung.isKeyDown(Input.KEY_A)) {
			x_Pos = x_Pos - geschwindigkeit;
		}

		// Pfeiltaste rechts
		if (steuerung.isKeyDown(Input.KEY_D)) {
			x_Pos = x_Pos + geschwindigkeit;
		}

		// Hier steht die Bewegung auf vertikale Ebene.
		// Pfeiltaste oben
		if (steuerung.isKeyDown(Input.KEY_W)) {
			y_Pos = y_Pos - geschwindigkeit;
		}

		// Pfeiltaste unten
		if (steuerung.isKeyDown(Input.KEY_S)) {
			y_Pos = y_Pos + geschwindigkeit;
		}

		if (x_Pos <= 0) {
			x_Pos = 0;
		} else if (x_Pos >= 770) {
			x_Pos = 770;
		}
		 if (y_Pos <= 0) {
		 y_Pos = 0;
		 } else if (y_Pos >= 395) {
		 y_Pos = 395;
		 }

		hitbox.setLocation(x_Pos, y_Pos);
	};

	public void draw() {

		gfx_char.draw(x_Pos, y_Pos);
	}

}
