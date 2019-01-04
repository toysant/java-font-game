package alpha;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	MainFrame(int x,int y,JPanel mp)
	{
		setSize(x,y);
		setTitle("Is this a game?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(mp);
		setResizable(false);
	}
	void display(){
		setVisible(true);
	}
}
