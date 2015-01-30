import java.util.*;

class Game {
	List<Mob> allyList = new ArrayList<Mob>();
	List<Mob> enemyList = new ArrayList<Mob>();
	List<StatEffect> toRemove = new ArrayList<StatEffect>();
	
	
	public Game() {
		for (int i = 0; i<5; i++) {
			allyList.add(new Player(false));
		}
		
	for (int i = 0; i<5; i++) {
			enemyList.add(new Skeleton(true));
		}
	}
		
	public void tick() {
		
	}
}