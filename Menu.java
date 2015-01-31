import java.util.*;

class Menu {
	public String text;
	public Scanner scan;
	public List<MenuOption> heldOptions = new ArrayList<MenuOption>();	
	public Mob currentPlayer;
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
	
	public Mob currentPlayer;
	
	public GameMenu() {
		
		text = "-------------------------------------\n";
		text += "|         Battle Screen             |\n";
		text += "-------------------------------------\n";
		
	}
	
	public void run() {
		int j = 1;
		//For each player in the list who needs to get a turn
		for (Mob ally : Main.currentGame.allyList) {
			heldOptions.clear();
			currentPlayer = ally;
			System.out.println(text);
			System.out.println("Player " + j + "'s Turn");
			j++;
		
			int k = 1;
			for (Mob mob : Main.currentGame.allyList) {
				System.out.println(k + " | " + mob.name);
				k++;
			}
			
			for (Mob mob : Main.currentGame.enemyList) {
				System.out.println(k + " | " + mob.name);
				k++;
			}
			
			System.out.println("-------------------------------------");
			
			for (ValidMove move : currentPlayer.moveList) {
				heldOptions.add(new MoveToMenu(move));
				
			}
			
			int i = 1;
			for (MenuOption option : heldOptions) {
				System.out.println(i + " " + option.text);
				i++;
			}
			
			scan = new Scanner(System.in);
			try {
				int selection = scan.nextInt() - 1;
				heldOptions.get(selection).execute();
			} catch (Exception err) {
				System.out.println(err);
				System.out.println("Please enter a valid option (Error in Menu.java)");
			}			
		}
	}
}