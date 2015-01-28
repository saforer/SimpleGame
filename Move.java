class Move {
	public String name;
	public boolean offensive;
	public void Do(Mob target, Mob self) {}
}

class MoveManager {
	public static Move findMove (validMove inMove) {
		switch (inMove) {
			//Add moves here to find them from validMove
			case block:
				return new Block();
			case feint:
				return new Feint();
			case overt:
				return new Overt();
			default: 
				return new Yawn();
		}
	}
	
	public static boolean Offensive(validMove inMove) {
		return findMove(inMove).offensive;
	}
	
	public static void DoMove(validMove inMove, Mob inTarget, Mob inSelf) {
		findMove(inMove).Do(inTarget, inSelf);
	}
}

class Block extends Move {
	public Block() {
		offensive = false;
		name = "Block";
	}
	
	public void Do(Mob inTarget, Mob inSelf) {
		System.out.println(inSelf.name + " starts blocking.");
		StatEffect temp = null;
		for (StatEffect effect : inTarget.effectList) {
			if (effect.heldEffect == validEffect.blocking) {
				temp = effect;
			}
		}
		
		if (temp == null) {
			inSelf.effectList.add(new StatEffect(validEffect.blocking, 3, "Blocking"));
		} else {
			temp.durationLeft = 3;
		}
	}
}

class Feint extends Move {
	public Feint() {
		offensive = true;
		name = "Feint";
	}
	
	public void Do(Mob inTarget, Mob inSelf) {
		boolean targetBlocking = false;
		
		for (StatEffect effect : inTarget.effectList) {
			if (effect.heldEffect == validEffect.blocking) {
				targetBlocking = true;
			}
		}

		if (targetBlocking) {
			inTarget.HP -= (inSelf.damage);
			System.out.println("You punch straight around their defenses.");
		} else {
			inTarget.HP -= (inSelf.damage);
			System.out.println(inTarget.name + " wasn't blocking");
		}
	}
}

class Overt extends Move {
	public Overt() {
		offensive = true;
		name = "Overt";
	}
	
	public void Do(Mob inTarget, Mob inSelf) {
		boolean targetBlocking = false;
		
		for (StatEffect effect : inTarget.effectList) {
			if (effect.heldEffect == validEffect.blocking) {
				targetBlocking = true;
			}
		}
		
		if (targetBlocking) {
			System.out.println( inTarget.name + "'s blocking, Try a feint.");
		} else {
			inTarget.HP -= (inSelf.damage * 2);
			System.out.println(inSelf.name + " delivers a punch to the jaw");
		}
	}
}

class Yawn extends Move {
	public Yawn() {
		offensive = true;
		name = "Yawn";
	}
	
	public void Do(Mob inTarget, Mob inSelf) {
		System.out.println(inSelf.name + " yawns loudly at " + inTarget.name);
	}
}