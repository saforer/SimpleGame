class Move {
	public String name;
	public boolean offensive;
	public void execute(Mob target, Mob self) {}
}

class MoveManager {
	public static Move findMove (ValidMove inMove) {
		switch (inMove) {
			//Add moves here to find them from ValidMove
			case BLOCK:
				return new Block();
			case FEINT:
				return new Feint();
			case OVERT:
				return new Overt();
			case HEAL:
				return new Heal();
			default: 
				return new Yawn();
		}
	}
	
	public static boolean offensive(ValidMove inMove) {
		return findMove(inMove).offensive;
	}
	
	public static void doMove(ValidMove inMove, Mob inTarget, Mob inSelf) {
		findMove(inMove).execute(inTarget, inSelf);
	}
}

class Block extends Move {
	public Block() {
		offensive = false;
		name = "Block";
	}
	
	public void execute(Mob inTarget, Mob inSelf) {
		System.out.println(inSelf.name + " starts blocking.");
		StatEffect temp = null;
		for (StatEffect effect : inTarget.effectList) {
			if (effect.heldEffect == ValidEffect.BLOCKING) {
				temp = effect;
			}
		}
		
		if (temp == null) {
			inSelf.effectList.add(new StatEffect(ValidEffect.BLOCKING, 3, "Blocking"));
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
	
	public void execute(Mob inTarget, Mob inSelf) {
		boolean targetBlocking = false;
		
		for (StatEffect effect : inTarget.effectList) {
			if (effect.heldEffect == ValidEffect.BLOCKING) {
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
	
	public void execute(Mob inTarget, Mob inSelf) {
		boolean targetBlocking = false;
		
		for (StatEffect effect : inTarget.effectList) {
			if (effect.heldEffect == ValidEffect.BLOCKING) {
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
	
	public void execute(Mob inTarget, Mob inSelf) {
		System.out.println(inSelf.name + " yawns loudly at " + inTarget.name);
	}
}

class Heal extends Move {
	public Heal() {
		offensive = false;
		name = "Heal";
	}
	
	public void execute(Mob inTarget, Mob inSelf) {
		int healVal = 5;
		System.out.println(inSelf.name + " heals himself for " + healVal);
		inTarget.HP += healVal;
	}
}