

import java.util.LinkedList;

import org.newdawn.slick.*;

public class Spiel extends BasicGame {

	private Image level;
	private Player player;
	private Input steuerung;
	private Monster[] monster;
	protected LinkedList<Schuss> schuss_anzahl;
	private Music music;
	private Sound sound;
	private int cooldown;

	@Override
	public void init(GameContainer gc) throws SlickException {
		level = new Image("hintergrund.png");
		monster = new Monster[10];
		steuerung = gc.getInput();
		player = new Player(740, 395, steuerung, "ghost.png");
		for (int i = 0; i < monster.length; i++) {
			monster[i] = new Monster(400 + (i * 50), 428, "goomba.png");
		}
		schuss_anzahl = new LinkedList<Schuss>();
		music = new Music("kirby.ogg");
		music.loop();
		music.setVolume(0.5f);
		sound = new Sound("gun.wav");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {

		cooldown += delta;

		player.update();

		for (int i = 0; i < monster.length; i++) {
			monster[i].laufen(3);

		}

		player.laufen(3, delta);

		if (steuerung.isMousePressed(Input.MOUSE_LEFT_BUTTON)
				&& cooldown < 10000) {
			schuss_anzahl.add(new Schuss(player.x_Pos + 40, player.y_Pos,
					"Schuss.png"));
			sound.play();

		}

		// Schuss wird nur dann bewegt wenn das Objekt existiert
		if (!(schuss_anzahl.isEmpty())) {
			for (int i = 0; i < schuss_anzahl.size(); i++) {
				if (!(schuss_anzahl.get(i).y_Pos < 0)) {
					schuss_anzahl.get(i).laufen(5, schuss_anzahl);
				}
			}
		}

	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		gfx.setColor(Color.magenta);
		level.draw(0, 0);

		gfx.drawString("Zeit: " + cooldown, 10, 50);

		for (int i = 0; i < monster.length; i++) {
			monster[i].draw();
			gfx.draw(monster[i].getHitbox());
			if (monster[i].getHitbox().intersects(player.getHitbox())) {
				gfx.drawString("Treffer", 10, 30);
			}
		}
		player.draw();
		if (!(schuss_anzahl.isEmpty())) {
			for (int i = 0; i < schuss_anzahl.size(); i++) {
				schuss_anzahl.get(i).draw();
				gfx.draw(schuss_anzahl.get(i).getHitbox());
			}
		}
		gfx.draw(player.getHitbox());
	}

	public Spiel(String title) {
		super(title);

	}

	public static void main(String[] args) {
		try {
			AppGameContainer game = new AppGameContainer(new Spiel("Test"));
			game.setDisplayMode(800, 600, false);
			game.setTargetFrameRate(60);
			game.setVSync(true);
			game.setIcon("icon.png");
			game.setAlwaysRender(true);
			game.setMouseGrabbed(false);
			game.setShowFPS(true);
			game.start();
		} catch (SlickException e) {

			e.printStackTrace();
		}

	}

}
