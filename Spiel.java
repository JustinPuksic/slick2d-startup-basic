import java.util.LinkedList;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class Spiel extends BasicGame {

	private Image level;
	private Player player;
	private Input steuerung;
	protected LinkedList<Monster> monster_anzahl;
	protected LinkedList<Schuss> schuss_anzahl;
	protected Pawn pawn;
	private Music music;
	private Sound sound;
	private Score highscore;
	@SuppressWarnings("unused")
	private TiledMap map;

	@Override
	public void init(GameContainer gc) throws SlickException {
		level = new Image("hintergrund.png");
		steuerung = gc.getInput();
		player = new Player(740, 395, steuerung, "ghost.png");
		schuss_anzahl = new LinkedList<Schuss>();
		monster_anzahl = new LinkedList<Monster>();
		pawn = new Pawn();
		music = new Music("kirby.ogg");
		music.loop();
		music.setVolume(0.0f);
		sound = new Sound("gun.wav");
		highscore = new Score();
		// map = new TiledMap("map.tmx");
		for (int i = 0; i < 5; i++) {
			monster_anzahl.add(new Monster(400 + (i * 50), 28, "goomba.png"));
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {

		player.update();
		player.laufen(3, delta);
		if (!(monster_anzahl.isEmpty())) {

			for (int i = 0; i < monster_anzahl.size(); i++) {
				if (!(monster_anzahl.get(i).y_Pos < 0)) {
					monster_anzahl.get(i).laufen(2);
				}
			}
			if (steuerung.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				schuss_anzahl.add(new Schuss(player.x_Pos + 10, player.y_Pos,
						"Schuss.png"));
				sound.play();
			}

			// Schuss wird nur dann bewegt wenn das Objekt existiert
			if (!(schuss_anzahl.isEmpty())) {
				for (int i = 0; i < schuss_anzahl.size(); i++) {
					if (!(schuss_anzahl.get(i).y_Pos < 0)) {
						schuss_anzahl.get(i).laufen(5);
						pawn.check_Col(schuss_anzahl, monster_anzahl, highscore);

					}
				}

			}

		}

	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		gfx.setColor(Color.magenta);
		level.draw(0, 0);
        gfx.drawString("Punkte: " + highscore.getScore(),10,70);
		player.draw();
		if (!(schuss_anzahl.isEmpty())) {
			for (int i = 0; i < schuss_anzahl.size(); i++) {
				schuss_anzahl.get(i).draw();
				gfx.draw(schuss_anzahl.get(i).getHitbox());
			}
		}
		if (!(monster_anzahl.isEmpty())) {
			for (int i = 0; i < monster_anzahl.size(); i++) {
				monster_anzahl.get(i).draw();
				gfx.draw(monster_anzahl.get(i).getHitbox());
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
			game.setMouseGrabbed(false);
			game.setShowFPS(true);
			game.start();
		} catch (SlickException e) {

			e.printStackTrace();
		}

	}

}
