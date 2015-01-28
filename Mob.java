import java.util.*;

class Mob {
	public String name;
	public int HP = 100;
	public List<validMove> moveList = new ArrayList<validMove>();
	
	public void TakeTurn() {
		validMove moveToDo = moveList.get(0);
		
		Mob target;
		if (MoveManager.Offensive(moveToDo)) {
			target = Main.currentGame.player;
		} else {
			target = Main.currentGame.enemy;
		}
		
		MoveManager.DoMove(moveToDo, target);
	}
}

class Player extends Mob {
	public Player() {
		name = "Player";
		moveList.add(validMove.attack);
		moveList.add(validMove.waddle);
	}
}

class Skeleton extends Mob {
	public Skeleton() {
		name = "Skeleton";
		moveList.add(validMove.attack);
		moveList.add(validMove.waddle);
	}
}