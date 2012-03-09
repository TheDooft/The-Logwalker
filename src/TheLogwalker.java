import gui.Gui;

import java.io.FileNotFoundException;


public class TheLogwalker {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException,
	java.text.ParseException{
		Gui gui = new Gui();
		
		gui.start();
		/*System.out.println("Begin" + System.nanoTime() /1000000000.0);
		System.out.println("Parsing...");
		long start = System.nanoTime();
		LogParser parser = new LogParser("./sample/WoWCombatLog.txt");
		long total = System.nanoTime() - start;
		System.out.println("End" + total /1000000000.0);*/
	}

}
