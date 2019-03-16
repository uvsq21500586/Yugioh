package eg.edu.guc.yugioh.board.player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.translater;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;


public class ExtraDeck {
	private ArrayList<MonsterCard> monsters;
	private String monstersPath;
	
	private final ArrayList<MonsterCard> extradeck;
	
	public ExtraDeck(String monsterp) throws IOException, NumberFormatException,
	UnexpectedFormatException, InstantiationException, IllegalAccessException {
		monstersPath = monsterp;
		if (monsters == null) {
			Scanner sc = new Scanner(System.in);

			while (true) {
			try {

				monsters = loadCardsFromFile(monstersPath);
				break;

			} catch (UnexpectedFormatException e) {

				System.out.println("Error in reading from file "
						+ e.getSourceFile() + " at line "
						+ e.getSourceLine());
				System.out.println(e.getMessage());
				System.out.println("Please enter another path:");

				if (e.getSourceFile().equalsIgnoreCase(
						monstersPath)) {
					setMonstersPath(sc.nextLine());
				}

			} catch (FileNotFoundException e) {

				String s = (monsters == null) ? monstersPath
						: null;

				System.out.println("The file \"" + s + "\" is not found.");
				System.out.println("Please enter another path:");

				String path = sc.nextLine();

				if (monsters == null)
					setMonstersPath(path);

			}

		}

		sc.close();
		}
		extradeck = new ArrayList<MonsterCard>();
		buildDeck(monsters);
	}
	
	private void buildDeck(ArrayList<MonsterCard> Monsters) throws InstantiationException, IllegalAccessException {

		for (int i=0; i<monsters.size();i++) {

			/*MonsterCard monster = (MonsterCard) monsters.get(i);

			MonsterCard clone = new MonsterCard(monster.getName(),
					monster.getDescription(),monster.getAttribut(),monster.getEspece(), monster.getLevel(),
					monster.getAttackPoints(), monster.getDefensePoints(), monster.getType2());
			clone.setMode(monster.getMode());
			clone.setHidden(monster.isHidden());
			clone.setLocation(Location.DECK);

			deck.add(clone);*/
			monsters.get(i).setLocation(Location.DECK);
			extradeck.add(monsters.get(i));
		}

	}
	
	public String getMonstersPath() {
		return monstersPath;
	}

	public void setMonstersPath(String monstersPath) {
		this.monstersPath = monstersPath;
	}
	
	public ArrayList<MonsterCard> loadCardsFromFile(String path) throws IOException,
	UnexpectedFormatException {

		ArrayList<MonsterCard> temp = new ArrayList<MonsterCard>();

		String line;

		BufferedReader br = new BufferedReader(new FileReader(path));

		int lineNumber = 0;
		line = br.readLine();
		if (line != null && !line.isEmpty()) {
			//while ((line = br.readLine()) != null) {
			while (line != null) {
				lineNumber++;

				String[] cardInfo = line.split(",");

				if (cardInfo.length == 0) {

					br.close();
					throw new MissingFieldException("The number of fields in the line did not match the expected.",path, lineNumber);

				} else {

					if (cardInfo[0].equalsIgnoreCase("Monster")
							&& cardInfo.length != 9) {

						br.close();
						throw new MissingFieldException(
								"The number of fields in the line did not match the expected.",
								path, lineNumber);

					} else if (cardInfo[0].equalsIgnoreCase("Spell")
							&& cardInfo.length != 4) {

						br.close();
						throw new MissingFieldException(
								"The number of fields in the line did not match the expected.",
								path, lineNumber);

					}

				}
	
				for (int i = 0; i < cardInfo.length; i++)
					if (cardInfo[i].equals("") || cardInfo[i].equals(" ")) {

						br.close();
			
						throw new EmptyFieldException("Empty Field.", path,
								lineNumber, i + 1);

					}

				if (cardInfo[0].equalsIgnoreCase("Monster")) {
					translater trans = new translater();
					temp.add
					(trans.translatedmonster(cardInfo[2], cardInfo[5],cardInfo[3], cardInfo[4], Integer
							.parseInt(cardInfo[8]), Integer.parseInt(cardInfo[6]),
							Integer.parseInt(cardInfo[7]), cardInfo[1]));
				}
				line = br.readLine();
			}
		}

		br.close();

		return (temp);

	}
	
	public ArrayList<MonsterCard> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<MonsterCard> monsters) {
		this.monsters = monsters;
	}

}
