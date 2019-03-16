package eg.edu.guc.yugioh.board.player;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.translater;
import eg.edu.guc.yugioh.cards.spells.*;
/*
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.Foret;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;*/
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Deck {

	//private static ArrayList<Card> monsters;
	//private static ArrayList<Card> spells;

	private ArrayList<Card> monsters;
	private ArrayList<Card> spells;
	
	//private static String monstersPath = "Database-Monsters.csv";
	//private static String spellsPath = "Database-Spells.csv";

	private String monstersPath;
	private String spellsPath;
	
	private final ArrayList<Card> deck;
	int trials = 0;
/*
	public Deck() throws IOException, NumberFormatException,
			UnexpectedFormatException {
		monstersPath = "Database-Monsters.csv";
		spellsPath = "Database-Spells.csv";
		if ((monsters == null) || (spells == null)) {

			Scanner sc = new Scanner(System.in);

			while (true) {

				try {

					monsters = loadCardsFromFile(Deck.getMonstersPath());
					spells = loadCardsFromFile(Deck.getSpellsPath());
					break;

				} catch (UnexpectedFormatException e) {

					if (trials >= 3) {
						sc.close();
						throw e;
					}

					System.out.println("Error in reading from file "
							+ e.getSourceFile() + " at line "
							+ e.getSourceLine());
					System.out.println(e.getMessage());
					System.out.println("Please enter another path:");

					trials++;

					if (e.getSourceFile().equalsIgnoreCase(
							Deck.getMonstersPath())) {
						Deck.setMonstersPath(sc.nextLine());
					}
					if (e.getSourceFile()
							.equalsIgnoreCase(Deck.getSpellsPath())) {
						Deck.setSpellsPath(sc.nextLine());
					}

				} catch (FileNotFoundException e) {

					if (trials >= 3) {
						sc.close();
						throw e;
					}

					String s = (monsters == null) ? Deck.getMonstersPath()
							: Deck.getSpellsPath();

					System.out.println("The file \"" + s + "\" is not found.");
					System.out.println("Please enter another path:");

					trials++;
					String path = sc.nextLine();

					if (monsters == null)
						Deck.setMonstersPath(path);
					else
						Deck.setSpellsPath(path);

				}

			}

			sc.close();

		}

		deck = new ArrayList<Card>();
		buildDeck(monsters, spells);
		shuffleDeck();

	}
*/
	public Deck(String monsterp,String spellp) throws IOException, NumberFormatException,
	UnexpectedFormatException, InstantiationException, IllegalAccessException {
		monstersPath = monsterp;
		spellsPath = spellp;
if ((monsters == null) || (spells == null)) {

	Scanner sc = new Scanner(System.in);

	while (true) {

		try {

			monsters = loadCardsFromFile(monstersPath);
			spells = loadCardsFromFile(spellsPath);
			break;

		} catch (UnexpectedFormatException e) {

			if (trials >= 3) {
				sc.close();
				throw e;
			}

			System.out.println("Error in reading from file "
					+ e.getSourceFile() + " at line "
					+ e.getSourceLine());
			System.out.println(e.getMessage());
			System.out.println("Please enter another path:");

			trials++;

			if (e.getSourceFile().equalsIgnoreCase(
					monstersPath)) {
				setMonstersPath(sc.nextLine());
			}
			if (e.getSourceFile()
					.equalsIgnoreCase(spellsPath)) {
				setSpellsPath(sc.nextLine());
			}

		} catch (FileNotFoundException e) {

			if (trials >= 3) {
				sc.close();
				throw e;
			}

			String s = (monsters == null) ? monstersPath
					: spellsPath;

			System.out.println("The file \"" + s + "\" is not found.");
			System.out.println("Please enter another path:");

			trials++;
			String path = sc.nextLine();

			if (monsters == null)
				setMonstersPath(path);
			else
				setSpellsPath(path);

		}

	}

	sc.close();

}

deck = new ArrayList<Card>();
buildDeck(monsters, spells);
shuffleDeck();

}
	
	@SuppressWarnings("resource")
	public ArrayList<Card> loadCardsFromFile(String path) throws IOException,
			UnexpectedFormatException {

		ArrayList<Card> temp = new ArrayList<Card>();

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
				throw new MissingFieldException(
						"The number of fields in the line did not match the expected.",
						path, lineNumber);

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

				if (cardInfo[1].equals("normal")) {
					temp.add(new MonsterCard(cardInfo[2], cardInfo[5],cardInfo[3], cardInfo[4], Integer
						.parseInt(cardInfo[8]), Integer.parseInt(cardInfo[6]),
						Integer.parseInt(cardInfo[7]), cardInfo[1]));
				} else {
					translater trans = new translater();
					
					if (trans.translatedmonster(cardInfo[2], cardInfo[5],cardInfo[3], cardInfo[4], Integer
							.parseInt(cardInfo[8]), Integer.parseInt(cardInfo[6]),
							Integer.parseInt(cardInfo[7]), cardInfo[1])==null){
						throw new UnknownSpellCardException("Unknown Monster card",
								path, lineNumber, cardInfo[1]);
					}
					temp.add(trans.translatedmonster(cardInfo[2], cardInfo[5],cardInfo[3], cardInfo[4], Integer
							.parseInt(cardInfo[8]), Integer.parseInt(cardInfo[6]),
							Integer.parseInt(cardInfo[7]), cardInfo[1]));
				}

			} else {

				if (!cardInfo[0].equalsIgnoreCase("Spell")) {

					br.close();
					throw new UnknownCardTypeException("Unknown Card type.",
							path, lineNumber, cardInfo[0]);

				}
				translater trans = new translater();
				
				if (trans.translatedspell(cardInfo[1],cardInfo[2], cardInfo[3])==null){
					throw new UnknownSpellCardException("Unknown Spell card",
							path, lineNumber, cardInfo[1]);
				}
				temp.add(trans.translatedspell(cardInfo[1],cardInfo[2], cardInfo[3]));
/*
				switch (cardInfo[2]) {
				case "Foret":
					temp.add(new Foret(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Epee Legendaire":
					temp.add(new Epee_Legendaire(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Crocs Demoniaques":
					temp.add(new Crocs_Demoniaques(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Cristal Violet":
					temp.add(new Cristal_Violet(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Livre d'Arts Secrets":
					temp.add(new Livre_d_Arts_Secrets(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Puissance de Kaishin":
					temp.add(new Puissance_de_Kaishin(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Terre Devastee":
					temp.add(new Terre_Devastee(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Montagne":
					temp.add(new Montagne(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Sogen":
					temp.add(new Sogen(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Umi":
					temp.add(new Umi(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Yami":
					temp.add(new Yami(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Medicament Rouge":
					temp.add(new Medicament_Rouge(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Etincelles":
					temp.add(new Etincelles(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Hinotama":
					temp.add(new Hinotama(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
				case "Piege Supprime":
					temp.add(new Piege_Supprime(cardInfo[1],cardInfo[2], cardInfo[3]));
					break;
/*
				case "Card Destruction":
					temp.add(new CardDestruction(cardInfo[1], cardInfo[2]));
					break;
				case "Change Of Heart":
					temp.add(new ChangeOfHeart(cardInfo[1], cardInfo[2]));
					break;
				case "Dark Hole":
					temp.add(new DarkHole(cardInfo[1], cardInfo[2]));
					break;
				case "Graceful Dice":
					temp.add(new GracefulDice(cardInfo[1], cardInfo[2]));
					break;
				case "Harpie's Feather Duster":
					temp.add(new HarpieFeatherDuster(cardInfo[1], cardInfo[2]));
					break;
				case "Heavy Storm":
					temp.add(new HeavyStorm(cardInfo[1], cardInfo[2]));
					break;
				case "Mage Power":
					temp.add(new MagePower(cardInfo[1], cardInfo[2]));
					break;
				case "Monster Reborn":
					temp.add(new MonsterReborn(cardInfo[1], cardInfo[2]));
					break;
				case "Pot of Greed":
					temp.add(new PotOfGreed(cardInfo[1], cardInfo[2]));
					break;
				case "Raigeki":
					temp.add(new Raigeki(cardInfo[1], cardInfo[2]));
					break;
					
				default:
					throw new UnknownSpellCardException("Unknown Spell card",
							path, lineNumber, cardInfo[1]);

				}*/

			}
			line = br.readLine();
		}}

		br.close();

		return (temp);

	}

	private void buildDeck(ArrayList<Card> Monsters, ArrayList<Card> Spells) throws InstantiationException, IllegalAccessException {

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
			deck.add(monsters.get(i));
		}

		for (int i=0; i<spells.size();i++) {
			spells.get(i).setLocation(Location.DECK);
			deck.add(spells.get(i));
			
			/*
			Card spell = spells.get(i);
			SpellCard clone;

			if (spell instanceof Foret) {

				clone = new Foret(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof Epee_Legendaire) {

				clone = new Epee_Legendaire(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			if (spell instanceof Crocs_Demoniaques) {

				clone = new Crocs_Demoniaques(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			if (spell instanceof Cristal_Violet) {

				clone = new Cristal_Violet(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			if (spell instanceof Livre_d_Arts_Secrets) {

				clone = new Livre_d_Arts_Secrets(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof Puissance_de_Kaishin) {

				clone = new Puissance_de_Kaishin(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof Montagne) {

				clone = new Montagne(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof Terre_Devastee) {

				clone = new Terre_Devastee(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof Sogen) {

				clone = new Sogen(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof Umi) {

				clone = new Umi(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof Yami) {

				clone = new Yami(((SpellCard) spell).getType2(),spell.getName(),
						spell.getDescription());
				
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}
			
			if (spell instanceof CardDestruction) {

				clone = new CardDestruction(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof ChangeOfHeart) {

				clone = new ChangeOfHeart(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof DarkHole) {

				clone = new DarkHole(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof GracefulDice) {

				clone = new GracefulDice(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof HarpieFeatherDuster) {

				clone = new HarpieFeatherDuster(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof HeavyStorm) {

				clone = new HeavyStorm(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof MagePower) {

				clone = new MagePower(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof MonsterReborn) {

				clone = new MonsterReborn(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof PotOfGreed) {

				clone = new PotOfGreed(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof Raigeki) {

				clone = new Raigeki(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}*/

		}

	}

	private void shuffleDeck() {

		Collections.shuffle(deck);

	}

	public ArrayList<Card> drawNCards(int n) {

		ArrayList<Card> cards = new ArrayList<Card>(n);

		for (int i = 0; i < n; i++)
			cards.add(deck.remove(0));

		return (cards);

	}

	public Card drawOneCard() {

		return (deck.remove(0));

	}

	public ArrayList<Card> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Card> monsters) {
		this.monsters = monsters;
	}

	public ArrayList<Card> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Card> spells) {
		this.spells = spells;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public String getMonstersPath() {
		return monstersPath;
	}

	public void setMonstersPath(String monstersPath) {
		this.monstersPath = monstersPath;
	}

	public String getSpellsPath() {
		return spellsPath;
	}

	public void setSpellsPath(String spellsPath) {
		this.spellsPath = spellsPath;
	}

}
