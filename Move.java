import java.util.*;

class Move {
	public String name;
	public void execute(Target inTarget) {}
}

class MoveManager {
	public static int moveNumber(ValidMove inMove) {
		return 1;
	}
	
	public static Move findMove(ValidMove inMove) {
		switch (inMove) {
			default:
				return new Punch();
		}		
	}
	
	public static void executeMove(ValidMove inMove, Target inTarget) {
		findMove(inMove).execute(inTarget);
	}
}

class Punch extends Move {
	public Punch() {
		name = "Punch";
	}
	
	public void execute(Target inTarget) {
		System.out.println(inTarget.caster.name + " delivers a mean uppercut to " + inTarget.targetList.get(0).name);
	}
}