import java.util.*;

class Menu {
	public String text;
	public List<MenuOption> heldOptions = new ArrayList<MenuOption>();	
	public void run() {
		System.out.println(text);
		int i = 0;
		for (MenuOption option : heldOptions) {
			System.out.println(i + " " + option.text);
			i++;
		}
		
		int selection = 0;
		Scanner scan = new Scanner(System.in);
		
		try {
			selection = scan.nextInt();
			heldOptions.get(selection).execute();
		} catch (Exception err) {
			System.out.println("Please enter a valid option");
		}
		
	}
}

class TitleScreen extends Menu  {
	public TitleScreen() {
		text = "Title Screen";
		
		heldOptions.add(new Quit());
		heldOptions.add(new StartGame());
	}
}

class GameMenu extends Menu {
	public GameMenu() {
		text = "\n\n\n";
		text += "-------------------------------------\n";
		text += "|         Battle Screen             |\n";
		text += "-------------------------------------\n";
		
		heldOptions.add(new Quit());
		
		for (ValidMove move : Main.currentGame.player.moveList) {
			heldOptions.add(new MoveToMenu(move));
		}
	}
	
	public void run() {
		System.out.println(text);
		System.out.println(Main.currentGame.player.name + " HP : " + Main.currentGame.player.HP + "\n");
		for (StatEffect effect : Main.currentGame.player.effectList) {
			System.out.println("Effect: " + effect.name + " for " + effect.durationLeft + " turns.");
		}
		
		System.out.println(Main.currentGame.enemy.name + " HP : " + Main.currentGame.enemy.HP + "\n");
		for (StatEffect effect : Main.currentGame.enemy.effectList) {
			System.out.println("Effect: " + effect.name + " for " + effect.durationLeft + " turns.");
		}
		
		int i = 0;
		for (MenuOption option : heldOptions) {
			System.out.println(i + " " + option.text);
			i++;
		}
		
		int selection = 0;
		Scanner scan = new Scanner(System.in);
		
		try {
			selection = scan.nextInt();
			heldOptions.get(selection).execute();
		} catch (Exception err) {
			System.out.println("Please enter a valid option");
		}
		
		if (Main.currentGame.enemy.HP <= 0) {
			Main.currentMenu = new Victory();
		}
		
		Main.currentGame.enemy.takeTurn();
		
		
		if (Main.currentGame.player.HP <= 0) {
			Main.currentMenu = new Loss();
		}
		
		Main.currentGame.tick();
	}
}

class Victory extends Menu {
	public Victory () {
		text = "Congratulations on winning with " + Main.currentGame.player.HP + " health left on " + Main.currentGame.player.name;
		
		heldOptions.add(new Quit());
	}
}

class Loss extends Menu {
	public Loss () {
		text = "Lost with " + Main.currentGame.player.HP + " health left on " + Main.currentGame.player.name + "\nThank you for play.";
		
		heldOptions.add(new Quit());
	}
}