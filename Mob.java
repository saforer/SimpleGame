import java.util.*;

class Mob {
	public String name;
	public int HP;
	public int damage;
	public List<ValidMove> moveList = new ArrayList<ValidMove>();
	public List<StatEffect> effectList = new ArrayList<StatEffect>();
	public void getMoveList() {}
	public boolean isEnemy;
	
	public void takeTurn() {
		
	}
}

class Player extends Mob {
	public Player(boolean inIsEnemy) {
		isEnemy = inIsEnemy;
		getMoveList();
		name = "Player";
		HP = 100;
		damage = 10;
	}
	
	public void getMoveList() {
		moveList.add(ValidMove.PUNCH);
		moveList.add(ValidMove.SHIELD);
		moveList.add(ValidMove.BACKHAND);
	}
}

class Skeleton extends Mob {
	public Skeleton(boolean inIsEnemy) {
		isEnemy = inIsEnemy;
		getMoveList();
		name = "Skeleton";
		HP = 100;
		damage = 10;
	}
	
	public void getMoveList() {
		
	}
}