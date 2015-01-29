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
	ValidMove heldMove;
	public MoveToMenu (ValidMove inMove) {
		advanceTurn = true;
		heldMove = inMove;
		text = " : " + MoveManager.findMove(heldMove).name;
	}
	
	public void execute() {
		Mob target;
		
		if (MoveManager.offensive(heldMove)) {
			target = Main.currentGame.enemy;
		} else {
			target = Main.currentGame.player;
		}
		
		MoveManager.doMove(heldMove, target, Main.currentGame.player);
	}
}