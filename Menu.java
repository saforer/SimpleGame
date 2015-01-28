import java.util.*;

class Menu {
	public String text;
	public List<MenuOption> heldOptions = new ArrayList<MenuOption>();	
	public void Run() {
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
			heldOptions.get(selection).Execute();
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
		text = "";
		text += "-------------------------------------\n";
		text += "|         Battle Screen             |\n";
		text += "-------------------------------------\n";
		
		heldOptions.add(new Quit());
		
		for (validMove move : Main.currentGame.player.moveList) {
			heldOptions.add(new MoveToMenu(move));
		}
	}
	
		public void Run() {
		System.out.println(text);
		System.out.println(Main.currentGame.player.name + " HP : " + Main.currentGame.player.HP + "\n");
		System.out.println(Main.currentGame.enemy.name + " HP : " + Main.currentGame.enemy.HP + "\n");
		
		int i = 0;
		for (MenuOption option : heldOptions) {
			System.out.println(i + " " + option.text);
			i++;
		}
		
		int selection = 0;
		Scanner scan = new Scanner(System.in);
		
		try {
			selection = scan.nextInt();
			heldOptions.get(selection).Execute();
			Main.currentGame.enemy.TakeTurn();
			if (Main.currentGame.enemy.HP <= 0) {
				Main.currentMenu = new Victory();
			}
			if (Main.currentGame.player.HP <= 0) {
				Main.currentMenu = new Loss();
			}
		} catch (Exception err) {
			System.out.println("Please enter a valid option");
		}
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