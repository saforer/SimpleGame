class Move {
	public String name;
	public boolean offensive;
	public void Do(Mob target) {}
}

class MoveManager {
	public static Move findMove (validMove inMove) {
		switch (inMove) {
			case attack:
				return new Attack();
			default:
				return new Waddle();
		}
	}
	
	public static boolean Offensive(validMove inMove) {
		return findMove(inMove).offensive;
	}
	
	public static void DoMove(validMove inMove, Mob inTarget) {
		findMove(inMove).Do(inTarget);
	}
}

class Attack extends Move {
	public Attack() {
		offensive = true;
		name = "Attack";
	}
	
	public void Do(Mob target) {
		target.HP = target.HP - 10;
		System.out.println(target.name + "'s spine has been stabbed");
	}
}

class Waddle extends Move {
	public Waddle() {
		offensive = false;
		name = "Waddle";
	}
	
	public void Do(Mob target) {
		System.out.println(target.name + " waddles around aimlessly");
	}
}