import java.util.*;

class Mob {
	public String name;
	public int HP;
	public int damage;
	public List<validMove> moveList = new ArrayList<validMove>();
	public List<StatEffect> effectList = new ArrayList<StatEffect>();
	public void GetMoveList() {}
	
	public void TakeTurn() {
		int randomSelection = (int)(Math.random() * moveList.size());
		validMove moveToDo = moveList.get(randomSelection);
		
		Mob target;
		if (MoveManager.Offensive(moveToDo)) {
			target = Main.currentGame.player;
		} else {
			target = Main.currentGame.enemy;
		}
		
		MoveManager.DoMove(moveToDo, target, this);
	}
}

class Player extends Mob {
	public Player() {
		GetMoveList();
		name = "Player";
		HP = 100;
		damage = 10;
	}
	
	public void GetMoveList() {
		moveList.add(validMove.overt);
		moveList.add(validMove.feint);
		moveList.add(validMove.block);
	}
}

class Skeleton extends Mob {
	public Skeleton() {
		GetMoveList();
		name = "Skeleton";
		HP = 100;
		damage = 10;
	}
	
	public void GetMoveList() {
		moveList.add(validMove.overt);
		moveList.add(validMove.feint);
		moveList.add(validMove.block);
		moveList.add(validMove.yawn);
	}
}