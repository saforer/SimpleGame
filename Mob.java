import java.util.*;

class Mob {
	public String name;
	public int HP;
	public int damage;
	public List<ValidMove> moveList = new ArrayList<ValidMove>();
	public List<StatEffect> effectList = new ArrayList<StatEffect>();
	public void getMoveList() {}
	
	public void takeTurn() {
		int randomSelection = (int)(Math.random() * moveList.size());
		ValidMove moveToDo = moveList.get(randomSelection);
		
		Mob target;
		if (MoveManager.offensive(moveToDo)) {
			target = Main.currentGame.player;
		} else {
			target = Main.currentGame.enemy;
		}
		
		MoveManager.doMove(moveToDo, target, this);
	}
}

class Player extends Mob {
	public Player() {
		getMoveList();
		name = "Player";
		HP = 100;
		damage = 10;
	}
	
	public void getMoveList() {
		moveList.add(ValidMove.OVERT);
		moveList.add(ValidMove.FEINT);
		moveList.add(ValidMove.BLOCK);
	}
}

class Skeleton extends Mob {
	public Skeleton() {
		getMoveList();
		name = "Skeleton";
		HP = 100;
		damage = 10;
	}
	
	public void getMoveList() {
		moveList.add(ValidMove.OVERT);
		moveList.add(ValidMove.FEINT);
		moveList.add(ValidMove.BLOCK);
		moveList.add(ValidMove.YAWN);
	}
}