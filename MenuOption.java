class MenuOption {
	public String text;	
	public void Execute() {}
}

class Quit extends MenuOption{
	public Quit () {
		text = " : Quit";
	}
	
	public void Execute() {
		Main.running = false;
	}
}

//Start the game
class StartGame extends MenuOption{
	public StartGame() {
		text = " : Start a New Game";
	}
	
	public void Execute() {
		Main.currentGame = new Game();
		Main.currentMenu = new GameMenu();
	}
}

class MoveToMenu extends MenuOption {
	validMove heldMove;
	public MoveToMenu (validMove inMove) {
		heldMove = inMove;
		text = " : " + MoveManager.findMove(heldMove).name;
	}
	
	public void Execute() {
		Mob target;
		if (MoveManager.Offensive(heldMove)) {
			target = Main.currentGame.enemy;
		} else {
			target = Main.currentGame.player;
		}
		
		MoveManager.DoMove(heldMove, target, Main.currentGame.player);
	}
}