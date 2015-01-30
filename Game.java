import java.util.*;

class Game {
	List<Mob> allyList = new ArrayList<Mob>();
	List<Mob> enemyList = new ArrayList<Mob>();
	List<StatEffect> toRemove = new ArrayList<StatEffect>();
	
	
	public Game() {
		allyList.add(new Player(false));
		allyList.add(new Player(false));
		allyList.add(new Player(false));
		
		enemyList.add(new Skeleton(true));
		enemyList.add(new Skeleton(true));
		enemyList.add(new Skeleton(true));
	}
		
	public void tick() {
		
	}
}