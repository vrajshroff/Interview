import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class FinalTestCases {

	@Test
	public void testCard1() {
		Card x = new Card ("1","1");
		assertEquals ("1", x.getSuite());
		assertEquals ("1", x.getVal());
	}
	
	@Test
	public void testCard2() {
		Card x = new Card ("13","1");
		assertEquals ("1", x.getSuite());
		assertEquals ("13", x.getVal());
	}
	
	@Test
	public void testCard13() {
		Card x = new Card ("10","1");
		assertEquals ("1", x.getSuite());
		assertEquals ("10", x.getVal());
	}
	@Test
	public void write1() {
		ReadFile x = new ReadFile ("High Score.txt");
		try {
			assertEquals (" 0 0 0", x.toDisplay());
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
	}
	
	@Test
	public void getAllCardsTestOrderOne() {
		ArrayList<Card> allCards = new ArrayList<Card>();
		allCards = GameCourt.getAllCards();
		assertEquals (0, allCards.size());
	}
	
	@Test
	public void getAllCardsTestOrderTwo() {
		ArrayList<Card> allCards = new ArrayList<Card>();
		allCards = GameCourt.getAllCards();
		assertEquals (0, allCards.size());
	}
	
	@Test
	public void matchTestforHearts() {
		String prev = "1 Heart";
		String curr = "10 Heart";
		assertTrue (prev.substring(prev.length()-3,prev.length()-1).equals
				(curr.substring(curr.length()-3,curr.length()-1)));
	}
	@Test
	public void matchTestforClub() {
		String prev = "1 Club";
		String curr = "10 Club";
		assertTrue (prev.substring(prev.length()-3,prev.length()-1).equals
				(curr.substring(curr.length()-3,curr.length()-1)));
	}
	@Test
	public void matchTestforSpade() {
		String prev = "1 Spade";
		String curr = "10 Spade";
		assertTrue (prev.substring(prev.length()-3,prev.length()-1).equals
				(curr.substring(curr.length()-3,curr.length()-1)));
	}
	@Test
	public void matchTestforDiamond() {
		String prev = "1 Diamond";
		String curr = "10 Diamond";
		assertTrue (prev.substring(prev.length()-3,prev.length()-1).equals
				(curr.substring(curr.length()-3,curr.length()-1)));
	}
	
	@Test
	public void matchTestfalse() {
		String prev = "1 Diamond";
		String curr = "10 Heart";
		assertFalse (prev.substring(prev.length()-3,prev.length()-1).equals
				(curr.substring(curr.length()-3,curr.length()-1)));
	}
}
