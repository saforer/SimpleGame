//Simple test to make a game
class Main {
	
	public static Menu currentMenu;
	public static boolean running = true;
	public static Game currentGame;
	
	public static void main(String[] args) {
		currentMenu = new TitleScreen();
		
		while (running) {
			currentMenu.run();
		}
	}
}