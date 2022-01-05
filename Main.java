package brickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create JFranme and add Gameplay class
		
		JFrame frame = new JFrame();
		Gameplay gameplay = new Gameplay();

		
		frame.setBounds(10,10,700,600);
		frame.setTitle("Brick Breaker");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gameplay);
		
	}

}
