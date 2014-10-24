
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Monster extends Gameobject {


	public Monster(int x_Pos, int y_Pos, String Imagepath)
			throws SlickException {
		super(x_Pos, y_Pos);
		gfx_char = new Image(Imagepath);
		this.hitbox = new Rectangle(x_Pos, y_Pos, gfx_char.getWidth(),
				gfx_char.getHeight());
		
		
	}

	public void draw() {
		gfx_char.draw(x_Pos, y_Pos);

	}

	public void laufen(int geschwindigkeit) {
		x_Pos = x_Pos - geschwindigkeit;
		if (x_Pos <= 0) {
			x_Pos = 800;
		}

		hitbox.setLocation(x_Pos, y_Pos);
	}
}
