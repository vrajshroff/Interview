import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

@SuppressWarnings("serial")
	public class GameCourt extends JPanel {
		private int [][] scores;
		public static final int COURT_WIDTH = 500;
		public static final int COURT_HEIGHT = 500;
		private ArrayList<Card> cardsUsed = new ArrayList<Card>();
		private ArrayList<Card> playerCards = new ArrayList<Card>();
		private ArrayList<Card> CPUCards = new ArrayList<Card>();
		private ArrayList<Card> cardsPlayed = new ArrayList<Card>();
		private static ArrayList<Card> allCards = new ArrayList<Card>();
		private JLabel previous;
		private JLabel current;
		private String prev= "";
		private String curr= "";
		private JLabel CPUScore ;
		private JLabel yourScore;
		private String CScore = "";
		private String yScore = "";
		private WriteFile data;
		private ReadFile data2;
		private static String file = "High Score.txt";
		private Card last;
		private String val;
		private String sui; 
		
		public String arrayToString () {
			String ret = "";
			Arrays.sort(scores [0]);
			
			for(int i = 0; i < scores.length / 2; i++)
			{
			    int temp = scores[0][i];
			    scores[0][i] = scores [0][scores.length - i - 1];
			    scores[0][scores.length - i - 1] = temp;
			}
			
			for (int i = 0; i <3; i++) {
				ret = ret + " " + Integer.toString(scores[0][i]);
			}
			return ret;
		}
		
		public void resetScore () {
			for (int i =0; i<2; i++ ) {
				for (int j =0; j <3; j++) {
					scores[i][j] = 0;
				}
			}
		}
		public void whenClickedNext () {
			if (playerCards.size() == 0) {
				JOptionPane.showMessageDialog(null, 
	                    "You lost!", 
	                    "Game Over", 
	                    JOptionPane.WARNING_MESSAGE);
				current.setText("");
				previous.setText("");								
								
				data = new WriteFile(file);
				try {
					data.writeToFile( arrayToString());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				resetScore();
				CPUScore.setText("");
				yourScore.setText("");
				startGameCards();
			}
			
			prev = curr;
			previous.setText(prev);
			
			last = playerCards.remove(0);
			val = last.getVal();
			sui = last.getSuite();
			cardsPlayed.add (last);
			curr = val + "  " + sui;
			current.setText(val +"  " + sui);			
			
			
			//Compare cards for match													
		       ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	CPUPlays();
		            }
		        };

		        Timer timer = new Timer(2000 ,taskPerformer);
		        timer.setRepeats(false);
		        timer.start();				
							
			
		}
		
		public void CPUPlays () {
			//CPU plays now						
									
			//check for win
			if (CPUCards.size() == 0) {
				JOptionPane.showMessageDialog(null, 
	                    "You Won!", 
	                    "Game Over", 
	                    JOptionPane.PLAIN_MESSAGE);
				current.setText("");
				previous.setText("");
				data = new WriteFile(file);
				try {
					data.writeToFile(arrayToString());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				resetScore();
				CPUScore.setText("");
				yourScore.setText("");
				startGameCards();
			}
			
			//change previous
			prev = curr;
			previous.setText(val + "  " + sui);
			
			//change current
			last = CPUCards.remove(0);
			val = last.getVal();
			sui = last.getSuite();			
			curr = val + "  " + sui;
			current.setText(val + "  " + sui);
			
			
			//check match for CPU
			if (prev.substring(prev.length()-3,prev.length()-1).equals
					(curr.substring(curr.length()-3,curr.length()-1))) {
				int cardsWon = cardsPlayed.size();				
				while (cardsPlayed.size() != 0) {
					CPUCards.add(cardsPlayed.remove(0));
				}
				JOptionPane.showMessageDialog(null, 
	                    "CPU Got This Hand!", 
	                    "Continue the Game", 
	                    JOptionPane.PLAIN_MESSAGE);
				current.setText("");
				previous.setText("");
				
				int lowGrab = 100; //random number higher than 52
				int indexOfLowGrab = 100; //same logic
				for (int i =0; i< 3; i++) {
					if (scores [1][i] < lowGrab) {
						lowGrab= scores [1][i];
						indexOfLowGrab = i;
					};
					
				}
				if (scores[1][indexOfLowGrab] < cardsWon) {
				scores[1][indexOfLowGrab] = cardsWon;
				}
				
				this.changeScore();
				
			}
		}
		
		public void whenClickedCollect () {
			if (prev.substring(prev.length()-3,prev.length()-1).equals
					(curr.substring(curr.length()-3,curr.length()-1))) {
			int cardsWon = cardsPlayed.size();
			while (cardsPlayed.size() != 0) {
				playerCards.add(cardsPlayed.remove(0)); }
				JOptionPane.showMessageDialog(null, 
	                    "You Got This Hand!", 
	                    "Continue the Game", 
	                    JOptionPane.PLAIN_MESSAGE);
				current.setText("");
				previous.setText("");				
				int lowGrab = 100; //random number higher than 52
				int indexOfLowGrab = 100; //same logic
				for (int i =0; i< 3; i++) {
					if (scores [0][i] < lowGrab) {
						lowGrab= scores [0][i];
						indexOfLowGrab = i;
					};					
				}			
														
				if (scores[0][indexOfLowGrab] < cardsWon) {
				scores[0][indexOfLowGrab] = cardsWon;
				}
				
				this.changeScore();
							
		}
			else {
				JOptionPane.showMessageDialog(null, 
	                    "Not A Match! Be Careful", 
	                    "Continue the Game", 
	                    JOptionPane.WARNING_MESSAGE);
			}
		}

		public static Card getCard (int x) {
		    	
		    	return allCards.get(x);
		    
		    }
		    
		    public static ArrayList<Card> getAllCards () {
		        return allCards;
		        
		        }


		public GameCourt(JLabel prevGiven, JLabel curGiven, JLabel YS, JLabel CS) {
			CPUScore = CS;
			yourScore = YS;
			
			previous = prevGiven;
			current = curGiven;
			scores = new int [2][3];
			prev= previous.getText();
			curr= current.getText();
			for (int num =1; num <14; num++) {
	        	for (int j =1; j< 5; j++) {
	        		String suite = "temp";
	        		if (j==1) {
	        			suite= "Spade";
	        		}
	        		if (j== 2 ) {
	        			suite = "Diamond";
	        		}
	        		if (j ==3) {
	        			suite = "Heart";
	        		}
	        		if (j==4) {
	        			suite = "Club";
	        		}
	        		
	        	allCards.add(new Card (Integer.toString(num), suite));
	        	}
	    }
	      
		}

		public int getARandomNumber() {
			return (int) (Math.random() * 52);
		}

		public boolean contains(Card x) {
			boolean check = false;
			if (cardsUsed.size() == 0) {
				return check;
			}
			for (Card s : cardsUsed) {
				if (s.equals(x)) {
					check = true;
				}
			}

			return check;
		}
		
		public void play () {
			
		}

		public void startGameCards() {
			
			JOptionPane.showMessageDialog(null,                     
                    "This is a very simple and interesting card game.\n"
                    + "It is commonly referred as SlapJack or Slaps.\n"
                    + "The goal of this game is to get all the cards (52 cards) or get the opponent "
                    + "run out of cards.\n"
                    + "You win cards when your played card matches the previous card by suite (Heart to Heart).\n"
                    + "When this happnes, press 'Collect'. Else press 'Next' to continue the game.\n"
                    + "The CPU does the same after you.\n"
                    + "Hint: Make sure to press 'Collect' quickly or you will miss your chance and the CPU will "
                    + "play after you.\n"
                    + " Good Luck and Have Fun!", "Let's PLay!", 
                    JOptionPane.PLAIN_MESSAGE);
			current.setText("");
			previous.setText("");
			
			for (int i = 0; i < 26; i++) {

				Card checkCard = getCard(getARandomNumber());
				while (contains(checkCard) == true) {
					checkCard = getCard(getARandomNumber());

				}
				cardsUsed.add(checkCard);
				playerCards.add(checkCard);

				if (i > 12) {
					CPUCards.add(checkCard);
				}
				
				resetScore();
				data2 = new ReadFile(file);
				try {
					yScore = data2.toDisplay();
					yourScore.setText(yScore);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				 				
			}		
			
		}	
		public void changeScore () {
			yScore ="";
			CScore = "";
			for (int i=0; i<2; i++) {
				for (int j=0; j<3; j++) {
					if (i==0) {
						yScore = yScore + " " + Integer.toString((scores [i][j]));
						
					}
					else {
						CScore = CScore + " " +  Integer.toString((scores [i][j]));
					}
				}
				
			}
			
			yourScore.setText(yScore);
			CPUScore.setText(CScore);
				
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(COURT_WIDTH, COURT_HEIGHT);
		}


	}