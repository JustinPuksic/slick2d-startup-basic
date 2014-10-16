

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Gameobject {

	protected int x_Pos;
	protected int y_Pos;
	protected Image gfx_char;
	protected Shape hitbox;
	protected SpriteSheet sheet;
	protected Animation ani;

	public Gameobject(int x_Pos, int y_Pos) {
		this.x_Pos = x_Pos;
		this.y_Pos = y_Pos;
	}

	// Getter & Setter
	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	public abstract void draw();

}
