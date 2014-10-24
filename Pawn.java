
import java.util.LinkedList;

public class Pawn {

	public Pawn() {

	}

	public void check_Col(LinkedList<Schuss> schuss_anzahl,
			LinkedList<Monster> monster_anzahl, Score highscore) {

		if (monster_anzahl.get(0).getHitbox()
				.intersects(schuss_anzahl.getFirst().getHitbox())) {

			monster_anzahl.remove();
			schuss_anzahl.removeFirst();
			highscore.setScore(highscore.getScore()+ highscore.getMonster_punkte());
		} else if (schuss_anzahl.getFirst().y_Pos <= 0) {
			schuss_anzahl.removeFirst();
		} else if (monster_anzahl.size() <= 0) {
			schuss_anzahl.add(null);
		}
	}

}
