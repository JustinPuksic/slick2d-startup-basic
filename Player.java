
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Player extends Gameobject {

	private Input steuerung;
	private boolean sprung = false;
	private float speed_v = 0.0f;

	public Player(int x_Pos, int y_Pos, Input steuerung, String Imagepath)
			throws SlickException {
		super(x_Pos, y_Pos);
		this.steuerung = steuerung;
		sheet = new SpriteSheet(Imagepath, 175, 175);
		ani = new Animation(sheet,20);
		this.hitbox = new Rectangle(x_Pos, y_Pos, ani.getWidth() -125,
				ani.getHeight()-125);
	}

	public void laufen(int geschwindigkeit, int delta) {
		// Macht Bewegung auf Y Ebene möglich
		y_Pos += speed_v;

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
		if (steuerung.isKeyPressed(Input.KEY_W) && !sprung) {
			speed_v = -0.3f * delta;
			sprung = true;

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

		// Sprunggrenze Y
		if (y_Pos <= 250) {
			sprung = false;
			speed_v = +0.3f * delta;
		}

		hitbox.setLocation(x_Pos, y_Pos);
	};

	public void draw() {

		ani.draw(x_Pos, y_Pos,50,50);
	}

	public void update() {
		
		ani.update(-8);

	}

}
