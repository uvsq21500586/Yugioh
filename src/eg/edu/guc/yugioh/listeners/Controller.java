package eg.edu.guc.yugioh.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.*;
/*
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;*/
import eg.edu.guc.yugioh.exceptions.*;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.CardButton;
import eg.edu.guc.yugioh.gui.EndTurnBut;
import eg.edu.guc.yugioh.gui.FieldSpellsPanel;
import eg.edu.guc.yugioh.gui.Gui;
import eg.edu.guc.yugioh.gui.HandPanel;
import eg.edu.guc.yugioh.gui.HiddenHandPanel;
import eg.edu.guc.yugioh.gui.MonsterButton;
import eg.edu.guc.yugioh.gui.MonstersExtraPanel;
import eg.edu.guc.yugioh.gui.MonstersPanel;
import eg.edu.guc.yugioh.gui.NextPhBut;
import eg.edu.guc.yugioh.gui.SpellButton;
import eg.edu.guc.yugioh.gui.SpellsPanel;

public class Controller implements ActionListener,MouseListener {



	private JButton fc;
	private JButton sc;
	private JButton tc;
	private Board board;
	private Gui gui;
	private int summonset;
	private int summonset2;
	private ArrayList<SpellCard> chaine;
	private boolean ischaine;
	private boolean ismultiplecible;
	private int nombretotalcible;
	private ArrayList<Card> targets;
	private SpellCard multiciblespell;
	
	
	
		public Controller(Board board,Gui gui) {
		this.board=board;
		this.gui=gui;
		addActionListeners();
		gui.getEndturn().addActionListener(this);
		gui.getNextphase().addActionListener(this);
		chaine = new ArrayList<SpellCard>();
		ischaine = false;
		ismultiplecible = false;
		nombretotalcible = 0;
		
	}


	public void addActionListeners(){
		ArrayList<MonsterButton> handp1 = this.gui.getHandp1().getMonsterbuttons();
		ArrayList<MonsterButton> handp2 = this.gui.getHandp2().getMonsterbuttons();
		ArrayList<SpellButton> handp1spells = this.gui.getHandp1().getSpellbuttons();
		ArrayList<SpellButton> handp2spells = this.gui.getHandp2().getSpellbuttons();
		//ArrayList<MonsterButton> monstersp1 = this.gui.getMonsterareap1().getMonsters();
		MonsterButton monstersp1[] = this.gui.getMonsterareap1().getMonsters2();
		MonsterButton monstersextrap1[] = this.gui.getMonsterextraareap1().getMonsters2();
		ArrayList<SpellButton> spellsp1 = this.gui.getSpellAreap1().getSpells();
		//ArrayList<MonsterButton> monstersp2 = this.gui.getMonsterAreap2().getMonsters();
		MonsterButton monstersp2[] = this.gui.getMonsterAreap2().getMonsters2();
		MonsterButton monstersextrap2[] = this.gui.getMonsterextraAreap2().getMonsters2();
		ArrayList<SpellButton> spellsp2 = this.gui.getSpellAreap2().getSpells();
		ArrayList<SpellButton> fieldspellsp1 = this.gui.getFieldspellAreap1().getSpells();
		ArrayList<SpellButton> fieldspellsp2 = this.gui.getFieldspellAreap2().getSpells();
		ArrayList<CardButton> hidp1 = this.gui.getP1hid().getHandButtons();
		ArrayList<CardButton> hidp2 = this.gui.getP2hid().getHandButtons();



		for (int i = 0; i < hidp1.size(); i++) {
			hidp1.get(i).addActionListener(this);
			hidp1.get(i).addMouseListener(this);
		}
		for (int i = 0; i < hidp2.size(); i++) {
			hidp2.get(i).addActionListener(this);
			hidp2.get(i).addMouseListener(this);
		}



		for (int i = 0; i < handp1.size(); i++) {
			handp1.get(i).addActionListener(this);
			handp1.get(i).addMouseListener(this);
		}
		for (int i = 0; i < handp2.size(); i++) {
			handp2.get(i).addActionListener(this);
			handp2.get(i).addMouseListener(this);
		}
		for (int i = 0; i < handp1spells.size(); i++) {
			handp1spells.get(i).addActionListener(this);
			handp1spells.get(i).addMouseListener(this);
		}
		for (int i = 0; i < handp2spells.size(); i++) {
			handp2spells.get(i).addActionListener(this);
			handp2spells.get(i).addMouseListener(this);
		}
		/*
		for (int i = 0; i < monstersp1.size(); i++) {
			monstersp1.get(i).addActionListener(this);
			monstersp1.get(i).addMouseListener(this);
		}
		for (int i = 0; i < monstersp2.size(); i++) {
			monstersp2.get(i).addActionListener(this);
			monstersp2.get(i).addMouseListener(this);
		}
		*/
		for (int i = 0; i < 5; i++) {
			monstersp1[i].addActionListener(this);
			monstersp1[i].addMouseListener(this);
		}
		for (int i = 0; i < 5; i++) {
			monstersp2[i].addActionListener(this);
			monstersp2[i].addMouseListener(this);
		}
		monstersextrap1[0].addActionListener(this);
		monstersextrap1[0].addMouseListener(this);
		monstersextrap2[0].addActionListener(this);
		monstersextrap2[0].addMouseListener(this);
		
		for (int i = 0; i < spellsp1.size(); i++) {
			spellsp1.get(i).addActionListener(this);
			spellsp1.get(i).addMouseListener(this);
		}
		
		for (int i = 0; i < spellsp2.size(); i++) {
			spellsp2.get(i).addActionListener(this);
			spellsp2.get(i).addMouseListener(this);
		}
		for (int i = 0; i < fieldspellsp1.size(); i++) {
			fieldspellsp1.get(i).addActionListener(this);
			fieldspellsp1.get(i).addMouseListener(this);
		}
		
		for (int i = 0; i < fieldspellsp2.size(); i++) {
			fieldspellsp2.get(i).addActionListener(this);
			fieldspellsp2.get(i).addMouseListener(this);
		}

	}

	public int activerPiege() {
		// 1-> pas de chaine, 0->commencer ou continuer chaine
		if (board.getOpponentPlayer().getField().countTraporSpeedCards() == 0) {
			return 1;
		}
		
		String[] options = { "ok", "cancel"};

		int x = JOptionPane.showOptionDialog(null, "Do you want to active your trap?", "SpellCard",
				JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
		
		return x;
		
	}





	private void updatefield() {
		
		if(board.isGameOver()){
			Object[] options = {"End Game!","Start New Game"};
			int choice = JOptionPane.showOptionDialog(gui, "GAME Over!,The winner is "+board.getWinner().getName()+"",null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
			if(choice==0){
				
				
				System.exit(0);
			}else{
				try {
					Gui.main(null);
					gui.setVisible(false);
					gui.audioClip.close();
				} catch (IOException | UnexpectedFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


		gui.getDeckp1().setText("" + gui.getP1().getField().getDeck().getDeck().size());
		gui.getDeckp2().setText("" + gui.getP2().getField().getDeck().getDeck().size());
		gui.getLifep1().setText("Life Points: " + gui.getP1().getLifePoints());
		gui.getLifep2().setText("Life Points: " + gui.getP2().getLifePoints());
		gui.getDescription2().setText("Att/Def: " + gui.getDescrAtt() + "/" + gui.getDescrDef());
		gui.getCurrphase().setText(Card.getBoard().getActivePlayer().getField().getPhase().name());
		if(gui.getP1()==board.getActivePlayer()){
			gui.getSp1().remove(gui.getP1hid());
			gui.getSp1().remove(gui.getHandp1());
			gui.getPanel1().remove(gui.getSp1());
			gui.setHandp1(new HandPanel(gui.getP1()));
			JScrollPane sp1 = new JScrollPane(gui.getHandp1());
			sp1.setPreferredSize(new Dimension(500,150));
			sp1.setBorder(null);
			sp1.getViewport().setOpaque(false);
			sp1.setOpaque(false);
			sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			gui.setSp1(sp1);
			gui.getPanel1().add(gui.getSp1(),BorderLayout.SOUTH);
			gui.revalidate();
		}else{
			gui.getSp1().remove(gui.getP1hid());
			gui.getSp1().remove(gui.getHandp1());
			gui.getPanel1().remove(gui.getSp1());
			gui.setP1hid(new HiddenHandPanel(gui.getP1()));
			JScrollPane sp1 = new JScrollPane(gui.getP1hid());
			sp1.setBorder(null);
			sp1.getViewport().setOpaque(false);
			 sp1.setPreferredSize(new Dimension(500,150));
			sp1.setOpaque(false);
			sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			gui.setSp1(sp1);
			gui.getPanel1().add(gui.getSp1(),BorderLayout.SOUTH);
			gui.revalidate();
		}
		
		

		if(gui.getP2()==board.getActivePlayer()){
			gui.getSp2().remove(gui.getP2hid());
			gui.getSp2().remove(gui.getHandp2());
			gui.getPanel2().remove(gui.getSp2());
			gui.setHandp2(new HandPanel(gui.getP2()));
			JScrollPane sp2 = new JScrollPane(gui.getHandp2());
			sp2.setBorder(null);
			sp2.getViewport().setOpaque(false);
			 sp2.setPreferredSize(new Dimension(500,150));
			sp2.setOpaque(false);
			sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			gui.setSp2(sp2);
			gui.getPanel2().add(gui.getSp2(),BorderLayout.NORTH);
			gui.revalidate();
		}else{
			gui.getSp2().remove(gui.getP2hid());
			gui.getSp2().remove(gui.getHandp2());
			gui.getPanel2().remove(gui.getSp2());
			gui.setP2hid(new HiddenHandPanel(gui.getP2()));
			JScrollPane sp2 = new JScrollPane(gui.getP2hid()); 
			sp2.setBorder(null);
			sp2.getViewport().setOpaque(false);
			sp2.setPreferredSize(new Dimension(500,150));
			sp2.setOpaque(false);
			sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			gui.setSp2(sp2);
			gui.getPanel2().add(gui.getSp2(),BorderLayout.NORTH);
			gui.revalidate();
		}

		gui.getPanel1().remove(gui.getMonsterareap1());
		gui.setMonsterareap1(new MonstersPanel(gui.getP1()));
		gui.getPanel1().add(gui.getMonsterareap1(),BorderLayout.NORTH);

		gui.getPanel4().remove(gui.getFieldspellAreap1());
		//gui.setMonsterareap1(new MonstersPanel(gui.getP1()));
		gui.setFieldspellAreap1(new FieldSpellsPanel(gui.getP1()));
		//gui.getPanel1().add(gui.getMonsterareap1(),BorderLayout.NORTH);
		gui.getPanel4().add(gui.getFieldspellAreap1(),BorderLayout.CENTER);

		
		gui.getPanel2().remove(gui.getMonsterAreap2());
		gui.setMonsterAreap2(new MonstersPanel(gui.getP2()));
		gui.getPanel2().add(gui.getMonsterAreap2(),BorderLayout.SOUTH);

		gui.getPanel5().remove(gui.getFieldspellAreap2());
		//gui.setMonsterareap1(new MonstersPanel(gui.getP1()));
		gui.setFieldspellAreap2(new FieldSpellsPanel(gui.getP2()));
		//gui.getPanel1().add(gui.getMonsterareap1(),BorderLayout.NORTH);
		gui.getPanel5().add(gui.getFieldspellAreap2(),BorderLayout.CENTER);

		gui.getPanel1().remove(gui.getSpellAreap1());
		gui.setSpellAreap1(new SpellsPanel(gui.getP1()));
		gui.getPanel1().add(gui.getSpellAreap1(),BorderLayout.CENTER);

		gui.getPanel1extra().remove(gui.getMonsterextraareap1());
		gui.setMonsterextraareap1(new MonstersExtraPanel(gui.getP1()));
		gui.getPanel1extra().add(gui.getMonsterextraareap1(),BorderLayout.NORTH);
		
		gui.getPanel2extra().remove(gui.getMonsterextraAreap2());
		gui.setMonsterextraAreap2(new MonstersExtraPanel(gui.getP2()));
		gui.getPanel2extra().add(gui.getMonsterextraAreap2(),BorderLayout.NORTH);
		

		gui.getPanel2().remove(gui.getSpellAreap2());
		gui.setSpellAreap2(new SpellsPanel(gui.getP2()));
		gui.getPanel2().add(gui.getSpellAreap2(),BorderLayout.CENTER);

		if(gui.getP1().getField().getGraveyard().size()>0){
			String url;
			if(gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size()-1) instanceof MonsterCard){
				url = "Cards Images Database/Monsters/"+gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size()-1).getName()+".jpg";
			}else{
				url = "Cards Images Database/Spells/"+gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size()-1).getName()+".jpg";
			}
			ImageIcon img = new ImageIcon(url);
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			gui.getGravep1().setIcon(newIcon);
		} else {
			String url;
			url ="Cards Images Database/Graveyard2.png";
			ImageIcon img = new ImageIcon(url);
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			gui.getGravep1().setIcon(newIcon);
		}

		if(gui.getP2().getField().getGraveyard().size()>0){
			String url;
			if(gui.getP2().getField().getGraveyard().get(gui.getP2().getField().getGraveyard().size()-1) instanceof MonsterCard){
				url = "Cards Images Database/Monsters/"+gui.getP2().getField().getGraveyard().get(gui.getP2().getField().getGraveyard().size()-1).getName()+".jpg";
			}else{
				url = "Cards Images Database/Spells/"+gui.getP2().getField().getGraveyard().get(gui.getP2().getField().getGraveyard().size()-1).getName()+".jpg";
			}
			ImageIcon img = new ImageIcon(url);
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			gui.getGravep2().setIcon(newIcon);
		} else {
			String url;
			url ="Cards Images Database/Graveyard2.png";
			ImageIcon img = new ImageIcon(url);
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			gui.getGravep2().setIcon(newIcon);
		}
		
		gui.getCh().remove(gui.getChoices());
		JScrollPane ch = new JScrollPane(gui.getChoices());
		ch.setBorder(null);
		ch.getViewport().setOpaque(false);
		ch.setPreferredSize(new Dimension(500,150));
		ch.setOpaque(false);
		ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		gui.setCh(ch);
		gui.getPanel6().add(gui.getCh(),BorderLayout.SOUTH);
		gui.revalidate();

		addActionListeners();
		//gui.getHandp1().update(gui.getP1());
		gui.revalidate();



	}




	public void seticons(){

	}











	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof MonsterButton){

			MonsterButton b = (MonsterButton) e.getSource();
			MonsterCard c = b.getMonster();
			
			String url;
			Point po = gui.getMousePosition();
			if(c!=null){
				/*if (c.isHidden() && ((board.getActivePlayer().getName().equals(gui.getP1().getName()) && gui.getMousePosition().y > 360) || (board.getActivePlayer().equals(gui.getP2().getName()) && gui.getMousePosition().y < 360) )) {
					url = "Cards Images Database/Card Back2.png";
				}else {*/
				JLabel panel = new JLabel();
				if (c.isHidden() && ((board.getActivePlayer().getName().equals(gui.getP1().getName()) && po.y < 376) || (board.getActivePlayer().getName().equals(gui.getP2().getName()) && po.y > 475) )) {
					url = "Cards Images Database/Card Back2.png";
					ImageIcon img = new ImageIcon(url);
					panel.setIcon(img);
					gui.getDescription().setIcon(img);
					//gui.getDescription().add(att);
					//gui.getDescription().add(def);
				}else {
				url = "Cards Images Database/Monsters/"+c.getName()+".jpg";
				gui.setDescrAtt(c.getAttackPoints());
				gui.setDescrDef(c.getDefensePoints());
				ImageIcon img = new ImageIcon(url);
				gui.getDescription().setIcon(img);
				gui.getDescription2().setText("Att/Def: " + gui.getDescrAtt() + "/" + gui.getDescrDef());
				
				}
/*
				
				JPanel panel3 = new JPanel();
				panel3.setOpaque(false);
				panel3.setLayout(null);
				panel3.add(comp, constraints);*/
				//panel3.add(c.getAttackPoints());
				
				//gui.getDescription().add()
				gui.getDescription().revalidate();
				gui.revalidate();
			}
		}
		if(e.getSource() instanceof SpellButton){
			SpellButton b = (SpellButton) e.getSource();
			SpellCard c = b.getSpell();
			JLabel panel = new JLabel();
			String url;
			Point po = gui.getMousePosition();
			if(c!=null){
				if (c.isHidden() && ((board.getActivePlayer().getName().equals(gui.getP1().getName()) && po.y < 376) || (board.getActivePlayer().getName().equals(gui.getP2().getName()) && po.y > 475) )) {
					url = "Cards Images Database/Card Back2.png";
					ImageIcon img = new ImageIcon(url);
					panel.setIcon(img);
					gui.getDescription().setIcon(img);
					//gui.getDescription().add(att);
					//gui.getDescription().add(def);
				}else {
					url = "Cards Images Database/Spells/"+c.getName()+".jpg";

					ImageIcon img = new ImageIcon(url);
					gui.getDescription().setIcon(img);
					gui.getDescription().revalidate();
					gui.revalidate();
				
				}
				
			}
		}
		if(e.getSource() instanceof CardButton){



			ImageIcon img = new ImageIcon("Cards Images Database/Card Back2.png");
			gui.getDescription().setIcon(img);
			gui.getDescription().revalidate();
			gui.revalidate();
		}





	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof NextPhBut){
			board.getActivePlayer().setEvenement("passe");
			//time spells
			for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
				if (board.getActivePlayer().getField().getSpellArea().get(i).isTimespell()){
					board.getActivePlayer().getField().getSpellArea().get(i).setTimeturn(board.getActivePlayer().getField().getSpellArea().get(i).getTimeturn()-1);
					if (board.getActivePlayer().getField().getSpellArea().get(i).getTimeturn() == 0){
						board.getActivePlayer().getField().removeSpellToGraveyard(board.getActivePlayer().getField().getSpellArea().get(i));
					}
				}
			}
			for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
				if (board.getOpponentPlayer().getField().getSpellArea().get(i).isTimespell()){
					board.getOpponentPlayer().getField().getSpellArea().get(i).setTimeturn(board.getOpponentPlayer().getField().getSpellArea().get(i).getTimeturn()-1);
					if (board.getOpponentPlayer().getField().getSpellArea().get(i).getTimeturn() == 0){
						board.getOpponentPlayer().getField().removeSpellToGraveyard(board.getOpponentPlayer().getField().getSpellArea().get(i));
					}
				}
			}
			
			board.getActivePlayer().endPhase();
			
			gui.getCurrphase().setText("Current Phase: " + Card.getBoard().getActivePlayer().getField().getPhase());
			
			for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
				SpellCard spell = board.getActivePlayer().getField().getSpellArea().get(i);
				if (!spell.isHidden() && spell.getType2().equals("continue trap")){
					((ContinueTrapCard) spell).actionterrain(gui);
				}
				if (spell.isHidden() && spell.getType2().contains("trap")){
					((TrapCard) spell).setJoue(false);
				}
			}
			for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
				SpellCard spell = board.getOpponentPlayer().getField().getSpellArea().get(i);
				if (!spell.isHidden() && spell.getType2().equals("continue trap")){
					((ContinueTrapCard) spell).actionterrain(gui);
				}
				if (spell.isHidden() && spell.getType2().contains("trap")){
					((TrapCard) spell).setJoue(false);
				}
			}
			updatefield();
			
			//addActionListeners();
		}
		if(arg0.getSource() instanceof EndTurnBut){
			board.getActivePlayer().endTurn();
			for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
				SpellCard spell = board.getActivePlayer().getField().getSpellArea().get(i);
				if (spell.getType2().equals("continue trap") && !spell.isHidden()){
					((ContinueTrapCard) spell).actionterrain(gui);
				}
				if (spell.isHidden() && spell.getType2().contains("trap")){
					((TrapCard) spell).setJoue(false);
				}
			}
			for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
				SpellCard spell = board.getOpponentPlayer().getField().getSpellArea().get(i);
				if (spell.getType2().equals("continue trap") && !spell.isHidden()){
					((ContinueTrapCard) spell).actionterrain(gui);
				}
				if (spell.isHidden() && spell.getType2().contains("trap")){
					((TrapCard) spell).setJoue(false);
				}
			}
			updatefield();
			//addActionListeners();
		}
		if (!ischaine) {
		if(arg0.getSource() instanceof MonsterButton){

			try{
				
				if(fc==null){

					MonsterCard monster = ((MonsterButton) arg0.getSource()).getMonster();
					//fc = button;

					if(monster.getLocation()==Location.HAND){
						fc = (MonsterButton) arg0.getSource();
						monster = ((MonsterButton) fc).getMonster();
						//fc = button;
						Object[] options = {"Summon","Set","Cancel"};
						summonset = JOptionPane.showOptionDialog(gui, "What is your action?",null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[2]);
						if(summonset==2){
							fc = null;
							return;
						}
						if(monster.getLevel()<=4){
							Object[] options2 = {"0","1","2","3","4"};
							summonset2 = JOptionPane.showOptionDialog(gui, "Choose the place?",null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[2]);
							if(summonset==0){
								Card.getBoard().getActivePlayer().summonMonster(monster,summonset2);
							}
							else{
								Card.getBoard().getActivePlayer().setMonster(monster, summonset2);
							}
							board.getActivePlayer().setEvenement("invocation normale");
							int x = activerPiege();
							if (x==0) {
								board.nextPlayerchaine();
								ischaine = true;
							} else {
								fc = null;
							}
							//Field cards effect
							for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
								SpellCard spell = board.getActivePlayer().getField().getSpellArea().get(i);
								if (spell.getType2().equals("continue trap") && !spell.isHidden()){
									((ContinueTrapCard) spell).actionterrain(gui);
								}
							}
							for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
								SpellCard spell = board.getOpponentPlayer().getField().getSpellArea().get(i);
								if (spell.getType2().equals("continue trap") && !spell.isHidden()){
									((ContinueTrapCard) spell).actionterrain(gui);
								} else if (!spell.isHidden() && spell.isTimespell()){
									spell.actionmonstreadversejoue(monster);
								}
							}
							if (gui.getP1().getField().getFieldarea().size()>0){
								((FieldSpellCard) gui.getP1().getField().getFieldarea().get(0)).setmonster(monster);
								/*if (gui.getP1().getField().getFieldarea().get(0).getName().equals("Foret")) {
									((Foret) gui.getP1().getField().getFieldarea().get(0)).setmonster(monster);
								} else if (gui.getP1().getField().getFieldarea().get(0).getName().equals("Terre Devastee")) {
									((Terre_Devastee) gui.getP1().getField().getFieldarea().get(0)).setmonster(monster);
								}*/
								
								
							}
							if (gui.getP2().getField().getFieldarea().size()>0){
								((FieldSpellCard) gui.getP2().getField().getFieldarea().get(0)).setmonster(monster);
								/*
								if (gui.getP2().getField().getFieldarea().get(0).getName().equals("Foret")) {
									((Foret) gui.getP2().getField().getFieldarea().get(0)).setmonster(monster);
								} else if (gui.getP1().getField().getFieldarea().get(0).getName().equals("Terre Devastee")) {
									((Terre_Devastee) gui.getP1().getField().getFieldarea().get(0)).setmonster(monster);
								}*/
							}

							updatefield();
							return;
						}
						else{
							if(monster.getLevel()<=6){
								if( Card.getBoard().getActivePlayer().getField().countMonstersonField()  ==0){
									JOptionPane.showMessageDialog(gui, "No sufficient monsters");
									fc = null;
									//updatefield();
									return;
								}
								Object[] options2 = {"OK","Cancel"};
								int y = JOptionPane.showOptionDialog(gui, "Choose one sacrifice",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
								if(y==0){
									board.getActivePlayer().setEvenement("invocation normale");
									fc = (MonsterButton) arg0.getSource();
									//JOptionPane.showMessageDialog(gui, "nnnnjjs");
									//updatefield();
									return;
								}
								else{
									fc = null;
									//updatefield();
									return;
								}


							}
							else{
								if( Card.getBoard().getActivePlayer().getField().countMonstersonField()<=1){
									JOptionPane.showMessageDialog(gui, "No sufficient monsters");
									fc = null;
									updatefield();
									return;
								}
								Object[] options2 = {"OK","Cancel"};
								int y = JOptionPane.showOptionDialog(gui, "Choose the first sacrifice",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
								if(y==0){
									board.getActivePlayer().setEvenement("invocation normale");
									//updatefield();
									return;
								}
								else{
									fc = null;
									//updatefield();
									return;
								}

							}


						}

					}else{
						//if(monster.getLocation()==Location.FIELD)
						if(board.getActivePlayer().getField().getPhase()!=Phase.BATTLE){
							if (!monster.isLockswitch()) {
								Object[] options2 = {"OK","Cancel"};
								int y = JOptionPane.showOptionDialog(gui, "Change Monster's Mode ?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
								if(y==0){
									board.getActivePlayer().switchMonsterMode(monster);
									updatefield();
									fc=null;
									sc=null;
									tc=null;
								}
							}
						}else{
							fc = (MonsterButton)arg0.getSource();
							monster = ((MonsterButton) fc).getMonster();
							Point po = gui.getMousePosition();
							if (board.getActivePlayer().getNumeroturn()==1) {
								JOptionPane.showMessageDialog(gui, "You cannot attack during the first turn");
								fc=null;
								sc=null;
								tc=null;
								return;
							}
							
							//if ((board.getActivePlayer().getName().equals(gui.getP1().getName()) && po.y>400) || (board.getActivePlayer().getName().equals(gui.getP2().getName()) && po.y<400) ){
								Object[] options2 = {"OK","Cancel"};
								int y = JOptionPane.showOptionDialog(gui, "Attack ?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
								//if(y==1 || (board.getActivePlayer().getName().equals(gui.getP1().getName()) && po.y>400) || (board.getActivePlayer().getName().equals(gui.getP2().getName()) && po.y<400) ){
								if(y==1){
									fc=null;
									sc=null;
									tc=null;
									return;
//								} else if ((board.getActivePlayer().getName().equals(gui.getP1().getName()) && po.y<400) || (board.getActivePlayer().getName().equals(gui.getP2().getName()) && po.y>400)) {
								} else if (!board.getActivePlayer().getField().MonsterInField(monster) && (board.getActivePlayer().getField().getMonstersExtraArea2()[0]==null || !board.getActivePlayer().getField().getMonstersExtraArea2()[0].equals(monster))) {
									JOptionPane.showMessageDialog(gui, "You Can only Attack with your own monsters");
									fc=null;
									sc=null;
									tc=null;
									return;
								}
								int x = activerPiege();
								if (x==0) {
									board.nextPlayerchaine();
									ischaine = true;
								}
								/*//Field cards effect
								for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
									SpellCard spell = board.getActivePlayer().getField().getSpellArea().get(i);
									if (spell.getType2().equals("continue trap") && !spell.isHidden()){
										((ContinueTrapCard) spell).actionterrain(gui);
									}
								}
								for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
									SpellCard spell = board.getOpponentPlayer().getField().getSpellArea().get(i);
									if (spell.getType2().equals("continue trap") && !spell.isHidden()){
										((ContinueTrapCard) spell).actionterrain(gui);
									}
								}*/
								if(board.getOpponentPlayer().getField().countMonstersonField()==0){
									board.getActivePlayer().declareAttack(((MonsterButton)fc).getMonster());
									fc=null;
									updatefield();
									return;
								}
								if (x==1){
									JOptionPane.showMessageDialog(gui, "Choose the monster you wish to attack");
								}
								return;
							
							//}

						}
					}
				}




				else{


					if(sc==null){

						if(fc instanceof MonsterButton){

							MonsterCard monster = ((MonsterButton)arg0.getSource()).getMonster();
							if(board.getActivePlayer().getField().getPhase()!= Phase.BATTLE && !board.getActivePlayer().getField().MonsterInField(monster)){
								JOptionPane.showMessageDialog(gui, "You must choose monster cards from your field");
								fc=null;
								sc=null;
								return;
							}
							if(((MonsterButton) fc).getMonster().getLocation()==Location.HAND && monster.getLocation()==Location.FIELD
									&&  board.getActivePlayer().getField().MonsterInField(monster)
									&& board.getActivePlayer().getField().getPhase()!= Phase.BATTLE){
								if(((MonsterButton) fc).getMonster().getLevel()<=6){

									sc = (MonsterButton) arg0.getSource();
									monster = ((MonsterButton) sc).getMonster();

									if(monster.getLocation()==Location.FIELD){
										ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
										sacrifices.add(((MonsterButton)sc).getMonster());
										Object[] options2 = {"0","1","2","3","4"};
										summonset2 = JOptionPane.showOptionDialog(gui, "Choose the place?",null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[2]);
										if(summonset == 0){
											Card.getBoard().getActivePlayer().summonMonster(((MonsterButton) fc).getMonster(), sacrifices, summonset2);
										}
										else{
											board.getActivePlayer().setMonster(((MonsterButton)fc).getMonster(), sacrifices,summonset2);
										}
										board.getActivePlayer().setEvenement("invocation normale");
										int x = activerPiege();
										if (x==0) {
											board.nextPlayerchaine();
											ischaine = true;
										} else {
											fc = null;
											fc=null;
											sc=null;
										}
										//Field cards effect
										for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
											SpellCard spell = board.getActivePlayer().getField().getSpellArea().get(i);
											if (spell.getType2().equals("continue trap") && !spell.isHidden()){
												((ContinueTrapCard) spell).actionterrain(gui);
											}
										}
										for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
											SpellCard spell = board.getOpponentPlayer().getField().getSpellArea().get(i);
											if (spell.getType2().equals("continue trap") && !spell.isHidden()){
												((ContinueTrapCard) spell).actionterrain(gui);
											}
										}
										updatefield();
										
										return;
									}
								}
								else{
									MonsterButton button = (MonsterButton) arg0.getSource();
									MonsterCard card = button.getMonster();
									sc = button;
									Object[] options2 = {"OK","Cancel"};
									int y = JOptionPane.showOptionDialog(gui, "Choose the second sacrifice",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
									if(y==0){
										//updatefield();
										return;
									}
									else{
										//updatefield();
										fc = null;
										sc = null;
										return;
									}
								}
							}
							else{
								sc = (MonsterButton) arg0.getSource();
								monster = ((MonsterButton) sc).getMonster();
								if (board.getActivePlayer().getField().MonsterInField(monster) || ( board.getActivePlayer().getField().getMonstersExtraArea2()[0]!=null && board.getActivePlayer().getField().getMonstersExtraArea2()[0].equals(monster))){
									fc=null;
									sc=null;
									tc = null;
									//ne peut pas attaquer ses propres monstres
									JOptionPane.showMessageDialog(gui, "You must attack monster cards from your opponent's field");
									return;
								}
								board.getActivePlayer().setEvenement("declare attaque");
								board.getActivePlayer().declareAttack(((MonsterButton)fc).getMonster(), ((MonsterButton)sc).getMonster());
								fc=null;
								sc=null;
								tc = null;
								updatefield();
								return;
							}
						}
						else{//fc is a spellbutton
							
							
							if (arg0.getSource() instanceof MonsterButton){
							MonsterCard monster = ((MonsterButton)arg0.getSource()).getMonster();
							try {
								if(((SpellButton)fc).getSpell().getType2().equals("equipment")){
									if(!board.getActivePlayer().getField().MonsterInField(monster) && !board.getOpponentPlayer().getField().MonsterInField(monster)){
										JOptionPane.showMessageDialog(gui, "You must choose monster cards from your field or your opponent's field");
										fc=null;
										sc=null;
										return;
									}
									sc = (MonsterButton)arg0.getSource();
									MonsterCard mons = ((MonsterButton)sc).getMonster();
									board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), ((MonsterButton)sc).getMonster());
									board.getActivePlayer().setEvenement("active carte magie");
									
									int x = activerPiege();
									
									if (x==0) {
										board.nextPlayerchaine();
										ischaine = true;
									} else {
										fc=null;
										sc=null;
									}

									updatefield();
									return;
								}
								if(((SpellButton)fc).getSpell().isTargetmonster()){
									if(!((SpellButton)fc).getSpell().monstercondition(monster)){
										JOptionPane.showMessageDialog(gui, "You cannot choose this monster");
										fc=null;
										sc=null;
										return;
									}
									sc = (MonsterButton)arg0.getSource();
									MonsterCard mons = ((MonsterButton)sc).getMonster();
									board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), ((MonsterButton)sc).getMonster());
									fc=null;
									sc=null;
									updatefield();
									return;
								}
							} catch (IncompatibleMonster e) {
								fc = null;
								sc = null;
								tc = null;
								JOptionPane.showMessageDialog(gui, "Incompatible monster");
							}
							} else {
								SpellCard spell = ((SpellButton)arg0.getSource()).getSpell();
								if(((SpellButton)fc).getSpell().isTargetspell()){
									if(!((SpellButton)fc).getSpell().compatible(spell)){
										JOptionPane.showMessageDialog(gui, "You cannot choose this card");
										fc=null;
										sc=null;
										return;
									}
									sc = (SpellButton)arg0.getSource();
									SpellCard spell2 = ((SpellButton)sc).getSpell();
									board.getActivePlayer().activateSpell2(((SpellButton)fc).getSpell(), ((SpellButton)sc).getSpell());
									fc=null;
									sc=null;
									updatefield();
									return;
								}
							}
						}
					}
					else{
						if(arg0.getSource() instanceof MonsterButton){
							MonsterCard monster = ((MonsterButton)arg0.getSource()).getMonster();

							if(fc instanceof MonsterButton && ((MonsterButton) fc).getMonster().getLocation()==Location.HAND && monster.getLocation()==Location.FIELD && board.getActivePlayer().getField().MonsterInField(monster)){

								MonsterButton button = (MonsterButton) arg0.getSource();
								monster = button.getMonster();
								tc = button;
								if(tc==sc){
									JOptionPane.showMessageDialog(gui, "you have to choose two different monsters");
									fc=null;
									sc=null;
									tc=null;
									return;
								}
								ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
								sacrifices.add(((MonsterButton)sc).getMonster());
								sacrifices.add(((MonsterButton)tc).getMonster());
								Object[] options2 = {"0","1","2","3","4"};
								summonset2 = JOptionPane.showOptionDialog(gui, "Choose the place?",null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[2]);
								if(summonset == 0){
									Card.getBoard().getActivePlayer().summonMonster(((MonsterButton) fc).getMonster(), sacrifices, summonset2);
								}
								else{
									board.getActivePlayer().setMonster(((MonsterButton) fc).getMonster(), sacrifices,summonset2);
								}
								board.getActivePlayer().setEvenement("invocation normale");
								int x = activerPiege();
								
								if (x==0) {
									board.nextPlayerchaine();
									ischaine = true;
								} else {
									fc=null;
									sc=null;
									tc=null;
								}
								updatefield();
								
								return;

							}
							else{

							}
						} else {
							//spellcard
							SpellCard spell = ((SpellButton)arg0.getSource()).getSpell();
							if(((SpellButton)fc).getSpell().isTargetspell()){
								if(!((SpellButton)fc).getSpell().compatible(spell)){
									JOptionPane.showMessageDialog(gui, "You cannot choose this card");
									fc=null;
									sc=null;
									return;
								}
								sc = (SpellButton)arg0.getSource();
								SpellCard spell2 = ((SpellButton)sc).getSpell();
								board.getActivePlayer().activateSpell2(((SpellButton)fc).getSpell(), ((SpellButton)sc).getSpell());
								board.getActivePlayer().setEvenement("active carte magie");
								fc=null;
								sc=null;
								updatefield();
								return;
							}
							
						}
					}

					
					
				}
			}

			catch(MultipleMonsterAdditionException e){
				fc = null;
				sc = null;
				tc = null;
				JOptionPane.showMessageDialog(gui, "you can't play more than one card");
			}
			catch(WrongPhaseException e){
				fc = null;
				sc = null;
				tc = null;
				JOptionPane.showMessageDialog(gui, "you can't set or summon a monster in this phase");
			}
			catch(NoMonsterSpaceException e){
				fc = null;
				sc = null;
				tc = null;
				JOptionPane.showMessageDialog(gui, "There is no avaialble space in monster Area");
			}
			catch(MonsterMultipleAttackException e){
				fc = null;
				sc = null;
				tc = null;
				JOptionPane.showMessageDialog(gui, "You Can Attack Only Once");
			}
			catch(DefenseMonsterAttackException e){
				fc = null;
				sc = null;
				tc = null;
				JOptionPane.showMessageDialog(gui, "You Can't Attack in Defense Mode");
			}

		}

		try{
		if(arg0.getSource() instanceof SpellButton){
			
			if(fc instanceof MonsterButton){
				fc = null;
				sc=null;
				JOptionPane.showMessageDialog(gui, "you must sacrifice a monster card");
				return;
			}
			/*
			if(fc!=null &&((SpellButton)fc).getName().equalsIgnoreCase("Change Of Heart")){
				JOptionPane.showMessageDialog(gui, "you must choose a monster card");
				fc = null;
				sc=null;
				return;
			}*/

			if(fc==null){
				if(board.getActivePlayer().getField().getSpellArea().contains(((SpellButton)arg0.getSource()).getSpell())
						|| board.getActivePlayer().getField().getHand().contains(((SpellButton)arg0.getSource()).getSpell())){
					if(((SpellButton)arg0.getSource()).getSpell().getLocation()==Location.HAND){
						String[] buttons = { "Activate", "Set", "cancel"};

						int rc = JOptionPane.showOptionDialog(null, "Activate or set spell ?", "SpellCard",
								JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
						SpellButton button = (SpellButton) arg0.getSource();
						SpellCard card = button.getSpell();
						fc=button;
						if(rc==1){
							if (((SpellCard)card).getType2().equals("field")){
								fc=null;
								JOptionPane.showMessageDialog(gui, "You cannot set a field card");
								return;
							}
							if (((SpellCard) card).getType2().contains("trap") ){
								((TrapCard)card).setJoue(true);
							}
							Card.getBoard().getActivePlayer().setSpell(card);
							fc=null;
							updatefield();
							return;

						}
						if(rc==2){
							fc=null;
							return;
						}
						else{
							SpellButton buttonbis = (SpellButton) arg0.getSource();
							if (buttonbis.getSpell().isFusionspell() && buttonbis.getSpell().conditionactivation()) {
								//fusion
								ArrayList<Card> fusionmonsterc = new ArrayList<Card>();
								
								
								gui.getChoices().setMonsterbuttons(new ArrayList<MonsterButton>());
								gui.getChoices().setSpellbuttons(new ArrayList<SpellButton>());
								for (int i=0;i<board.getActivePlayer().getField().getExtradeck().getMonsters().size();i++) {
									if (board.getActivePlayer().getField().getExtradeck().getMonsters().get(i).getType2().contains("fusion")) {
										gui.getChoices().getMonsterbuttons().add(new MonsterButton(board.getActivePlayer().getField().getExtradeck().getMonsters().get(i)));
										fusionmonsterc.add(board.getActivePlayer().getField().getExtradeck().getMonsters().get(i));
									}
								}
								String[] options = new String[fusionmonsterc.size()];
								for (int i=0;i<fusionmonsterc.size();i++) {
									options[i] = fusionmonsterc.get(i).getName();
								}
								
								int x = JOptionPane.showOptionDialog(null, "Choose your fusion monster", "fusionMonster",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[0]);
								FusionMonsterCard fusion = (FusionMonsterCard) fusionmonsterc.get(x);
								
								int nbmaterielsfusions = ((FusionMonsterCard) fusion).getNbmaterielsfusions();
								
								targets = new ArrayList<Card>();
								for (int i1=0;i1<nbmaterielsfusions;i1++){
									//sélectionner les cibles
									targets.add(targetfusion((FusionMonsterCard) fusion,i1));
								
								}
								for (int i1=0;i1<nbmaterielsfusions;i1++){
									if (targets.get(i1)==null) {
										JOptionPane.showMessageDialog(gui, "No compatible monsters");
										fc = null;
										return;
									}
								}
								
								
								//MonsterCard fusionmonster;
								board.getActivePlayer().getField().getExtradeck().getMonsters().remove(fusion);
								board.getActivePlayer().getField().getMonstersExtraArea2()[0] = fusion;
								Object[] options2 = {"Summon","Set"};
								summonset = JOptionPane.showOptionDialog(gui, "What is your action?",null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[0]);
								if(summonset==0){
									board.getActivePlayer().getField().getMonstersExtraArea2()[0].setMode(Mode.ATTACK);
								} else {
									board.getActivePlayer().getField().getMonstersExtraArea2()[0].setMode(Mode.DEFENSE);
								}
								fusion.setHidden(false);
								fusion.setLocation(Location.FIELD);
								SpellCard spell = buttonbis.getSpell();
								board.getActivePlayer().getField().removeCardfromHandToGraveyard(spell);
								for (int i1=0;i1<nbmaterielsfusions;i1++){
									board.getActivePlayer().getField().removeCardfromHandToGraveyard(targets.get(i1));
								}
								
								fc = null;
								sc=null;
								updatefield();
								return;
							}
							
							if (!buttonbis.getSpell().conditionactivation()){
								fc = null;
								sc=null;
								JOptionPane.showMessageDialog(gui, "you cannot use this card now");
								return;
							}
							if (card.isMultipletargetcard()) {
								ismultiplecible = true;
								nombretotalcible = card.getNombretotalcible();
								multiciblespell = card;
								targets = new ArrayList<Card>();
								for (int i1=0;i1<multiciblespell.getNombrecible().size();i1++){
									//sélectionner les cibles
									for (int i2=0;i2<multiciblespell.getNombrecible().get(i1);i2++)
										targets.add(targetCard(card,i1));
								
								}
								card.actionmulti(targets);
								board.getActivePlayer().setEvenement("active carte magie");
								if (!card.getType2().contains("continue")){
									board.getActivePlayer().getField().removeSpellToGraveyard(card);
								}
								fc=null;
								updatefield();
								return;
							}
							if (((SpellCard) card).getType2().contains("trap") && ((TrapCard)card).isJoue()){
								fc=null;
								JOptionPane.showMessageDialog(gui, "You cannot activate a trap card from your hand");
								return;
							}
							if (((SpellCard) card).getType2().equals("equipment") ){
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the monster you wish to equip", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
							}
							if (((SpellCard) card).getType2().equals("field")) {
								board.getActivePlayer().setEvenement("active carte magie");
								if (board.getActivePlayer().getField().getFieldarea().size()>0){
									SpellCard sp = board.getActivePlayer().getField().getFieldarea().get(0);
									((FieldSpellCard) sp).gotograveyard(gui);
								}
								board.getActivePlayer().activateSpell(card, null);
								((FieldSpellCard) card).action2(gui);
								
								updatefield();
								fc = null;
								return;
							}
							if (((SpellCard) card).isTargetspell()) {
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the spell/trap card you wish to target", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
								
							}
							if (((SpellCard) card).isTargetmonster()) {
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the monster card you wish to target", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
								
							}
							switch (card.getName()) {
							case "Change Of Heart":
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the monster you wish to control", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
							case "Dark Hole":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Graceful Dice":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Harpie's Feather Duster":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Heavy Storm":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Mage Power":
								String[] options1 = { "ok", "cancel"};

								int x1 = JOptionPane.showOptionDialog(null, "Choose the monster you wish to enhance", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options1, options1[1]);
								if(x1==0){
									fc = button;
									return;
								}
								fc=null;
								return;
							case "Monster Reborn":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Pot of Greed":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							default:
								//board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), null);
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;

							}
						}
					}
					else{
						String[] buttons = { "ok", "cancel"};

						int rc = JOptionPane.showOptionDialog(null, "Activate spell card ?", "SpellCard",
								JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
						SpellButton button = (SpellButton) arg0.getSource();
						SpellCard card = button.getSpell();
						fc=button;
						if(rc==1){
							Card.getBoard().getActivePlayer().setSpell(card);
							fc=null;
							updatefield();
							return;
						}
						else{
							SpellButton buttonbis = (SpellButton) arg0.getSource();
							if (buttonbis.getSpell().isFusionspell() && buttonbis.getSpell().conditionactivation()) {
								//fusion
								ArrayList<Card> fusionmonsterc = new ArrayList<Card>();
								
								
								gui.getChoices().setMonsterbuttons(new ArrayList<MonsterButton>());
								gui.getChoices().setSpellbuttons(new ArrayList<SpellButton>());
								for (int i=0;i<board.getActivePlayer().getField().getExtradeck().getMonsters().size();i++) {
									if (board.getActivePlayer().getField().getExtradeck().getMonsters().get(i).getType2().contains("fusion")) {
										gui.getChoices().getMonsterbuttons().add(new MonsterButton(board.getActivePlayer().getField().getExtradeck().getMonsters().get(i)));
										fusionmonsterc.add(board.getActivePlayer().getField().getExtradeck().getMonsters().get(i));
									}
								}
								String[] options = new String[fusionmonsterc.size()];
								for (int i=0;i<fusionmonsterc.size();i++) {
									options[i] = fusionmonsterc.get(i).getName();
								}
								
								int x = JOptionPane.showOptionDialog(null, "Choose your fusion monster", "fusionMonster",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[0]);
								FusionMonsterCard fusion = (FusionMonsterCard) fusionmonsterc.get(x);
								
								int nbmaterielsfusions = ((FusionMonsterCard) fusion).getNbmaterielsfusions();
								
								targets = new ArrayList<Card>();
								for (int i1=0;i1<nbmaterielsfusions;i1++){
									//sélectionner les cibles
									targets.add(targetfusion((FusionMonsterCard) fusion,i1));
								
								}
								for (int i1=0;i1<nbmaterielsfusions;i1++){
									if (targets.get(i1)==null) {
										JOptionPane.showMessageDialog(gui, "No compatible monsters");
										fc = null;
										return;
									}
								}
								
								
								//MonsterCard fusionmonster;
								board.getActivePlayer().getField().getExtradeck().getMonsters().remove(fusion);
								board.getActivePlayer().getField().getMonstersExtraArea2()[0] = fusion;
								Object[] options2 = {"Summon","Set"};
								summonset = JOptionPane.showOptionDialog(gui, "What is your action?",null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[0]);
								if(summonset==0){
									board.getActivePlayer().getField().getMonstersExtraArea2()[0].setMode(Mode.ATTACK);
								} else {
									board.getActivePlayer().getField().getMonstersExtraArea2()[0].setMode(Mode.DEFENSE);
								}
								fusion.setHidden(false);
								SpellCard spell = buttonbis.getSpell();
								board.getActivePlayer().getField().removeCardfromHandToGraveyard(spell);
								for (int i1=0;i1<nbmaterielsfusions;i1++){
									board.getActivePlayer().getField().removeCardfromHandToGraveyard(targets.get(i1));
								}
								
								fc = null;
								sc=null;
								updatefield();
								return;
							}
							if (!buttonbis.getSpell().conditionactivation()){
								fc = null;
								sc=null;
								JOptionPane.showMessageDialog(gui, "you cannot use this card now");
								return;
							}
							if (((SpellCard) card).getType2().contains("trap") && ((TrapCard)card).isJoue()){
								fc=null;
								JOptionPane.showMessageDialog(gui, "You cannot activate this trap card this turn");
								return;
							}
							if (card.isMultipletargetcard()) {
								ismultiplecible = true;
								nombretotalcible = card.getNombretotalcible();
								multiciblespell = card;
								targets = new ArrayList<Card>();
								for (int i1=0;i1<multiciblespell.getNombrecible().size();i1++){
									//sélectionner les cibles
									for (int i2=0;i2<multiciblespell.getNombrecible().get(i1);i2++)
										targets.add(targetCard(card,i1));
								
								}
								board.getActivePlayer().setEvenement("active carte magie");
								card.actionmulti(targets);
								if (!card.getType2().contains("continue")){
									board.getActivePlayer().getField().removeSpellToGraveyard(card);
								}
								fc=null;
								updatefield();
								return;
							}
							if (((SpellCard) card).getType2().equals("equipment") ){
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the monster you wish to equip", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
							}
							if (((SpellCard) card).isTargetspell()) {
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the spell/trap card you wish to target", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
								
							}
							if (((SpellCard) card).isTargetmonster()) {
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the monster card you wish to target", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
								
							}
							if (((SpellCard) card).isTargetspell()) {
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the spell/trap card you wish to target", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
								
							}
							
							switch (card.getName()) {

							case "Card Destruction":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
								/*
							case "Epee Legendaire":{
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the monster you wish to equip", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
							}*/
							case "Change Of Heart":
								String[] options = { "ok", "cancel"};

								int x = JOptionPane.showOptionDialog(null, "Choose the monster you wish to control", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
								if(x==0){
									fc = button;
									return;
								}
								fc=null;
								return;
							case "Dark Hole":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Graceful Dice":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Harpie's Feather Duster":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Heavy Storm":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Mage Power":
								String[] options1 = { "ok", "cancel"};

								int x1 = JOptionPane.showOptionDialog(null, "Choose the monster you wish to enhance", "SpellCard",
										JOptionPane.WARNING_MESSAGE, 0, null, options1, options1[1]);
								if(x1==0){
									fc = button;
									return;
								}
								fc=null;
								return;
							case "Monster Reborn":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;
							case "Pot of Greed":
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;

							default:
								//board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), null);
								board.getActivePlayer().activateSpell(card, null);
								updatefield();
								fc = null;
								return;

							}
						}
					}


				}
			} else {
				if(fc instanceof SpellButton){
					SpellCard spell = ((SpellButton)arg0.getSource()).getSpell();
					if(((SpellButton)fc).getSpell().isTargetspell()){
						if(!((SpellButton)fc).getSpell().compatible(spell)){
							JOptionPane.showMessageDialog(gui, "You cannot choose this card");
							fc=null;
							sc=null;
							return;
						}
						sc = (SpellButton)arg0.getSource();
						SpellCard spell2 = ((SpellButton)sc).getSpell();
						board.getActivePlayer().activateSpell2(((SpellButton)fc).getSpell(), ((SpellButton)sc).getSpell());
						fc=null;
						sc=null;
						updatefield();
						return;
					}
				}
			}
		}
		} catch(NoSpellSpaceException e){
			fc = null;
			sc = null;
			tc = null;
			JOptionPane.showMessageDialog(gui, "There is no available space in spell Area");
		} catch(IncompatibleMonster e){
			fc = null;
			sc = null;
			tc = null;
			JOptionPane.showMessageDialog(gui, "Incompatible monster");
		}
		} else {
			chaine = new ArrayList<SpellCard>();
			//chaine
			if(arg0.getSource() instanceof SpellButton){
				SpellButton trap = (SpellButton)arg0.getSource();
				SpellCard spell2 = ((SpellButton)trap).getSpell();
				if (spell2.isHidden() && board.getActivePlayer().getField().getSpellArea().contains(spell2) ){
					String[] buttons = { "ok", "cancel"};
					int rc = 1;
					if (!spell2.conditionactivation()) {
						JOptionPane.showMessageDialog(gui, "Conditions are not correct");
					}else {
						rc = JOptionPane.showOptionDialog(null, "Activate spell card ?", "SpellCard",
							JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					}
					//SpellButton button = (SpellButton) arg0.getSource();
					if(rc==1){
						for (int i=chaine.size()-1;i>=0;i--) {
							if (i==0){
								if (chaine.get(0).isMultipletargetcard()) {
									ismultiplecible = true;
									nombretotalcible = chaine.get(0).getNombretotalcible();
									multiciblespell = chaine.get(0);
									targets = new ArrayList<Card>();
									for (int i1=0;i1<multiciblespell.getNombrecible().size();i1++){
										//sélectionner les cibles
										for (int i2=0;i2<multiciblespell.getNombrecible().get(i1);i2++)
											targets.add(targetCard(chaine.get(0),i1));
									
									}
									chaine.get(0).actionmulti(targets);
								} else if (((TrapCard)chaine.get(0)).isCiblejoue() ) {
									Card cartecible = null;
									if (fc instanceof MonsterButton) {
										cartecible = ((MonsterButton)fc).getMonster();
									} else {
										cartecible = ((SpellButton)fc).getSpell();
									}
									((TrapCard)chaine.get(0)).setCible(cartecible);
									((TrapCard)chaine.get(0)).actionsurcible();
								
								
								} else {
								if (fc instanceof SpellButton) {
									Card.getBoard().getActivePlayer().activateSpell2(spell2, ((SpellButton)fc).getSpell());
								} else {
									Card.getBoard().getActivePlayer().activateSpell(spell2, ((MonsterButton)fc).getMonster());
								}
								((TrapCard)chaine.get(i)).setChoisi(false);
								}
								
							}
							board.nextPlayerchaine();
							
						}
						board.nextPlayerchaine();
						sc=null;
						ischaine = false;
						fc = null;
						updatefield();
						return;
					}
					else{
						//piège activé
						chaine.add(spell2);
						((TrapCard)spell2).setChoisi(true);
						spell2.setHidden(false);
						int x = activerPiege();
						if (x==0) {
							board.nextPlayerchaine();
							updatefield();
							return;
						} else {
							for (int i=chaine.size()-1;i>=0;i--) {
								if (chaine.get(i).conditionactivation()){
									if (i==0){
										if (chaine.get(0).isMultipletargetcard()) {
											ismultiplecible = true;
											nombretotalcible = chaine.get(0).getNombretotalcible();
											multiciblespell = chaine.get(0);
											targets = new ArrayList<Card>();
											for (int i1=0;i1<multiciblespell.getNombrecible().size();i1++){
												//sélectionner les cibles
												for (int i2=0;i2<multiciblespell.getNombrecible().get(i1);i2++)
													targets.add(targetCard(chaine.get(0),i1));
											
											}
											chaine.get(0).actionmulti(targets);
										
										} else if (((TrapCard)chaine.get(0)).isCiblejoue() ) {
											Card cartecible = null;
											if (fc instanceof MonsterButton) {
												cartecible = ((MonsterButton)fc).getMonster();
											} else {
												cartecible = ((SpellButton)fc).getSpell();
											}
											((TrapCard)chaine.get(0)).setCible(cartecible);
											((TrapCard)chaine.get(0)).actionsurcible();
										} else {
											if (fc instanceof SpellButton) {
												Card.getBoard().getActivePlayer().activateSpell2(spell2, ((SpellButton)fc).getSpell());
											} else {
												Card.getBoard().getActivePlayer().activateSpell(spell2, ((MonsterButton)fc).getMonster());
											}
											if (chaine.get(i).getType2().equals("continue trap")){
												chaine.get(i).actionterrain(gui);
											}
										}
										((TrapCard)chaine.get(i)).setChoisi(false);
									
									}
									if (!chaine.get(i).getType2().contains("continue")){
										board.getActivePlayer().getField().removeSpellToGraveyard(chaine.get(i));
									}
								}
								board.nextPlayerchaine();
								/*
								if (!board.getActivePlayer().getEvenement().contains("impossible")){
									//si le piège n'a pas perturbé l'événement
									if (!board.getActivePlayer().getEvenement().equals("invocation normale")) {
										
									}
								}*/
								
							}
							sc=null;
							ischaine = false;
							fc = null;
							updatefield();
							return;
						}
					}
				}
			}
		}
		return;
	}
	
	public Card targetCard(SpellCard spell,int ensemble){
		String lieu = spell.getLocationcible().get(ensemble);
		ArrayList<String> buttons = new ArrayList<String>();
		ArrayList<Card> listechoix = new ArrayList<Card>();
		if (lieu.contains("active")) {
			//carte du joueur actif
			if (lieu.contains("field")){
				//carte sur le terrain
				if (lieu.contains("monster")){
					//carte monstre
					for (int i=0;i<5;i++){
						if (board.getActivePlayer().getField().getMonstersArea2()[i]!=null){
							if (spell.multicompatible(ensemble, board.getActivePlayer().getField().getMonstersArea2()[i]) && !board.getActivePlayer().getField().getMonstersArea2()[i].isChoisi()){
								//carte valide
								buttons.add(board.getActivePlayer().getField().getMonstersArea2()[i].getName());
								listechoix.add(board.getActivePlayer().getField().getMonstersArea2()[i]);
							}
						}
						
					}
					if (board.getActivePlayer().getField().getMonstersExtraArea2()[0]!=null && spell.multicompatible(ensemble, board.getActivePlayer().getField().getMonstersExtraArea2()[0]) && !board.getActivePlayer().getField().getMonstersExtraArea2()[0].isChoisi()){
						//carte valide
						buttons.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0].getName());
						listechoix.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0]);
					}
				}
				if (lieu.contains("spell")){
					for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
						if (spell.multicompatible(ensemble, board.getActivePlayer().getField().getSpellArea().get(i)) && !board.getActivePlayer().getField().getSpellArea().get(i).isChoisi()){
							//carte valide
							buttons.add(board.getActivePlayer().getField().getMonstersArea2()[i].getName());
							listechoix.add(board.getActivePlayer().getField().getMonstersArea2()[i]);
						}
					}
					
					if (board.getActivePlayer().getField().getFieldarea().size() != 0 && spell.multicompatible(ensemble,board.getActivePlayer().getField().getFieldarea().get(0) ) && !board.getActivePlayer().getField().getFieldarea().get(0).isChoisi()){
						buttons.add(board.getActivePlayer().getField().getFieldarea().get(0).getName());
						listechoix.add(board.getActivePlayer().getField().getFieldarea().get(0));
					}
				}
			}
			if (lieu.contains("graveyard")){
				if (lieu.contains("monster")){
					for (int i=0;i<board.getActivePlayer().getField().getGraveyard().size();i++){
						if ((board.getActivePlayer().getField().getGraveyard().get(i) instanceof MonsterCard) && !board.getActivePlayer().getField().getGraveyard().get(i).isChoisi()){
							buttons.add(board.getActivePlayer().getField().getGraveyard().get(i).getName());
							listechoix.add(board.getActivePlayer().getField().getGraveyard().get(i));
						}
					}
				}
			}
		}
		if (lieu.contains("opponent")) {
			//carte du joueur passif
			if (lieu.contains("field")){
				//carte sur le terrain
				if (lieu.contains("monster")){
					//carte monstre
					for (int i=0;i<5;i++){
						if (board.getOpponentPlayer().getField().getMonstersArea2()[i]!=null){
							if (spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getMonstersArea2()[i]) && !board.getOpponentPlayer().getField().getMonstersArea2()[i].isChoisi()){
								//carte valide
								buttons.add(board.getOpponentPlayer().getField().getMonstersArea2()[i].getName());
								listechoix.add(board.getOpponentPlayer().getField().getMonstersArea2()[i]);
							}
						}
						
					}
					if (board.getOpponentPlayer().getField().getMonstersExtraArea2()[0] != null && spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getMonstersExtraArea2()[0]) && !board.getOpponentPlayer().getField().getMonstersExtraArea2()[0].isChoisi()){
						//carte valide
						buttons.add(board.getOpponentPlayer().getField().getMonstersExtraArea2()[0].getName());
						listechoix.add(board.getOpponentPlayer().getField().getMonstersExtraArea2()[0]);
					}
				}
				if (lieu.contains("spell")){
					for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
						if (spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getSpellArea().get(i)) && !board.getOpponentPlayer().getField().getSpellArea().get(i).isChoisi()){
							//carte valide
							buttons.add(board.getOpponentPlayer().getField().getMonstersArea2()[i].getName());
							listechoix.add(board.getOpponentPlayer().getField().getMonstersArea2()[i]);
						}
					}
					
					if (board.getOpponentPlayer().getField().getFieldarea().size() != 0 && spell.multicompatible(ensemble,board.getOpponentPlayer().getField().getFieldarea().get(0) ) && !board.getOpponentPlayer().getField().getFieldarea().get(0).isChoisi()){
						buttons.add(board.getOpponentPlayer().getField().getFieldarea().get(0).getName());
						listechoix.add(board.getOpponentPlayer().getField().getFieldarea().get(0));
					}
				}
			}
			if (lieu.contains("graveyard")){
				if (lieu.contains("monster")){
					for (int i=0;i<board.getOpponentPlayer().getField().getGraveyard().size();i++){
						if ( (board.getOpponentPlayer().getField().getGraveyard().get(i) instanceof MonsterCard) && !board.getOpponentPlayer().getField().getGraveyard().get(i).isChoisi()){
							buttons.add(board.getOpponentPlayer().getField().getGraveyard().get(i).getName());
							listechoix.add(board.getOpponentPlayer().getField().getGraveyard().get(i));
						}
					}
				}
			}
		}
		String[] buttons2 = new String[buttons.size()];
		for (int i=0;i<buttons.size();i++){
			buttons2[i] = buttons.get(i);
		}
		int x = JOptionPane.showOptionDialog(null, "Do you want to active your trap?", "SpellCard",
				JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[0]);
		
		Card card = listechoix.get(x);
		listechoix.get(x).setChoisi(true);
		return card;
	}

	public MonsterCard targetfusion(FusionMonsterCard monster,int ensemble){
		ArrayList<String> buttons = new ArrayList<String>();
		ArrayList<MonsterCard> listechoix = new ArrayList<MonsterCard>();
		
		//((FusionMonsterCard)monster)
		for (int i=0;i<5;i++){
			if (board.getActivePlayer().getField().getMonstersArea2()[i]!=null){
				if (((FusionMonsterCard)monster).compatiblemonster( board.getActivePlayer().getField().getMonstersArea2()[i],ensemble) && !board.getActivePlayer().getField().getMonstersArea2()[i].isChoisi()){
					//carte valide
					buttons.add(board.getActivePlayer().getField().getMonstersArea2()[i].getName());
					listechoix.add(board.getActivePlayer().getField().getMonstersArea2()[i]);
				}
			}
			
		}
		if (board.getActivePlayer().getField().getMonstersExtraArea2()[0]!=null && ((FusionMonsterCard)monster).compatiblemonster(board.getActivePlayer().getField().getMonstersExtraArea2()[0],ensemble) && !board.getActivePlayer().getField().getMonstersExtraArea2()[0].isChoisi()){
			//carte valide
			buttons.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0].getName());
			listechoix.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0]);
		}
		//hand
		for (int i=0;i<board.getActivePlayer().getField().getHand().size();i++){
			if (board.getActivePlayer().getField().getHand().get(i) instanceof MonsterCard) {
				if (((FusionMonsterCard)monster).compatiblemonster((MonsterCard) board.getActivePlayer().getField().getHand().get(i), ensemble) && !board.getActivePlayer().getField().getHand().get(i).isChoisi()){
					buttons.add(board.getActivePlayer().getField().getHand().get(i).getName());
					listechoix.add((MonsterCard) board.getActivePlayer().getField().getHand().get(i));
				}
			}
		}
		buttons.add("annuler");
		String[] buttons2 = new String[buttons.size()];
		for (int i=0;i<buttons.size();i++){
			buttons2[i] = buttons.get(i);
		}
		int x = JOptionPane.showOptionDialog(null, "Choose the material fusion", "Fusion",
				JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[0]);
		
		if (x==buttons.size()-1) {
			return null;
		}
		
		MonsterCard card = listechoix.get(x);
		listechoix.get(x).setChoisi(true);
		
		return card;
	}
	
}
