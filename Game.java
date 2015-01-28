import java.util.*;

class Game {
	Mob player;
	Mob enemy;
	List<StatEffect> toRemove = new ArrayList<StatEffect>();
	
	public Game() {
		player = new Player();
		enemy = new Skeleton();
	}
	
	
	
	public void Tick() {
		for (StatEffect effect : player.effectList) {
			effect.durationLeft--;
			if (effect.durationLeft <= 0) {
				toRemove.add(effect);
			}
		}
		
		player.effectList.removeAll(toRemove);
		toRemove.clear();
		
		for (StatEffect effect : enemy.effectList) {
			effect.durationLeft--;
			if (effect.durationLeft <= 0) {
				toRemove.add(effect);
			}
		}
		
		enemy.effectList.removeAll(toRemove);
		toRemove.clear();
	}
}