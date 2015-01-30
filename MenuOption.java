import java.util.*;

class MenuOption {
	public boolean advanceTurn = false;
	public String text;	
	public void execute() {}
}

class Quit extends MenuOption{
	public Quit () {
		text = " : Quit";
	}
	
	public void execute() {
		Main.running = false;
	}
}

//Start the game
class StartGame extends MenuOption{
	public StartGame() {
		text = " : Start a New Game";
	}
	
	public void execute() {
		Main.currentGame = new Game();
		Main.currentMenu = new GameMenu();
	}
}

class MoveToMenu extends MenuOption {
	ValidMove moveHeld;
	public MoveToMenu (ValidMove inMove) {
		advanceTurn = true;
		//What move is held
		moveHeld = inMove;
		text = " : " + MoveManager.findMove(moveHeld).name;
	}
	
	public void execute() {
		//Target will be output
		Target target = new Target();
		
		//Make the caster the current player from the menu immediately before we forget.
		target.caster = Main.currentMenu.currentPlayer;
		
		//Figure out who needs to be allowed to be selected.
		boolean selfTarget = false;
		boolean allyTarget = false;
		boolean enemyTarget = true;
		List<Mob> tempList = new ArrayList<Mob>();
		List<Mob> outputList = new ArrayList<Mob>();
		
		while (outputList.size() < MoveManager.moveNumber(moveHeld)) {
			//if the spell targets allies, let allies be targetted as long as they aren't the caster.
			if (allyTarget) {
				for (Mob ally : Main.currentGame.allyList) {
					if (ally != target.caster) {
						tempList.add(ally);
					}
				}
			}
			//if the spell targets the caster, let the caster be targetable
			if (selfTarget) {
				tempList.add(target.caster);
			}
			//if the spell targets enemies, let the spell target enemies
			if (enemyTarget) {
				for (Mob enemy : Main.currentGame.enemyList) {
					tempList.add(enemy);
				}
			}
			
			//Remove from the options, any thing that has already been added
			tempList.removeAll(outputList);
			//if we have no options to ask the player, then just stop
		
			if (tempList.size() == 0) {
				break;
			}
			
			//List the options for the player to select from
			int j = 1;
			for (Mob mob : tempList) {
				System.out.println( j + " : " + mob.name);
				j++;
			}
			
			
			//Wait for the player to select who to add to the list
			Scanner scan = new Scanner(System.in);
			try {
				int selection = scan.nextInt() - 1;
				System.out.println(tempList.get(selection).name);
				outputList.add(tempList.get(selection));
			} catch (Exception err) {
				System.out.println("Please enter a valid option (Error in MenuOption.java)");
			}			
			
			tempList.clear();			
			System.out.println(outputList.size());
		}
		target.targetList = outputList;
		MoveManager.executeMove(moveHeld, target);
	}
}