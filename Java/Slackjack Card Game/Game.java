import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Game {
	private GameCourt state;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Slapjack: Vraj Shroff");
		frame.setResizable(false);
		frame.setBounds(500, 500, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblOpponent = new JLabel("Opponent");
		lblOpponent.setBounds(191, 8, 92, 20);
		frame.getContentPane().add(lblOpponent);

		JLabel lblYou = new JLabel("You");
		lblYou.setBounds(221, 424, 69, 20);
		frame.getContentPane().add(lblYou);

		JButton btnCollect = new JButton("Collect");
		btnCollect.setBounds(374, 195, 89, 29);
		frame.getContentPane().add(btnCollect);
		btnCollect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state.whenClickedCollect();
			}
		});

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setOpaque(true);
		horizontalBox.setBackground(Color.ORANGE);
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		horizontalBox.setBounds(166, 348, 124, 60);
		frame.getContentPane().add(horizontalBox);

		JLabel lblMyCards = new JLabel("Your Cards");
		horizontalBox.add(lblMyCards);
		lblMyCards.setHorizontalAlignment(SwingConstants.CENTER);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		horizontalBox_1.setBounds(170, 44, 120, 60);
		frame.getContentPane().add(horizontalBox_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_1.add(horizontalBox_2);
		horizontalBox_1.setOpaque(true);
		horizontalBox_1.setBackground(Color.GREEN);

		JLabel lblOpponentCards = new JLabel("Opponent Cards");
		lblOpponentCards.setHorizontalAlignment(SwingConstants.CENTER);
		horizontalBox_2.add(lblOpponentCards);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox.setBounds(114, 154, 92, 148);
		verticalBox.setOpaque(true);
		verticalBox.setBackground(Color.YELLOW);
		frame.getContentPane().add(verticalBox);

		JLabel lblPrev = new JLabel("");
		verticalBox.add(lblPrev);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_1.setBounds(250, 154, 92, 148);
		verticalBox_1.setOpaque(true);
		verticalBox_1.setBackground(Color.YELLOW);
		frame.getContentPane().add(verticalBox_1);

		JLabel lblCurr = new JLabel("");
		verticalBox_1.add(lblCurr);

		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state.whenClickedNext();
			}
		});
		btnNewButton.setBounds(15, 195, 75, 29);
		frame.getContentPane().add(btnNewButton);

		
		JLabel lblPreviousCard = new JLabel("Previous Card");
		lblPreviousCard.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreviousCard.setBounds(93, 312, 148, 20);
		frame.getContentPane().add(lblPreviousCard);
	
		JLabel lblCurrentCard = new JLabel("Current Card");
		lblCurrentCard.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentCard.setBounds(225, 312, 148, 20);
		frame.getContentPane().add(lblCurrentCard);
		
		JLabel lblYourBestThree = new JLabel("Your Best 3 Grabs:");
		lblYourBestThree.setForeground(Color.RED);
		lblYourBestThree.setBounds(339, 368, 140, 20);
		frame.getContentPane().add(lblYourBestThree);
		
		JLabel label = new JLabel("0 0 0");
		label.setBounds(374, 399, 69, 20);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("0 0 0");
		label_1.setBounds(394, 84, 69, 20);
		frame.getContentPane().add(label_1);
		
		JLabel lblCpusBest = new JLabel("CPU's Best 3 Grabs:");
		lblCpusBest.setForeground(Color.RED);
		lblCpusBest.setBounds(339, 57, 140, 20);
		frame.getContentPane().add(lblCpusBest);
		
		state = new GameCourt(lblPrev, lblCurr, label, label_1);
		state.startGameCards();

	}
}
