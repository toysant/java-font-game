package alpha;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainThread {
	public static void main(String[] args) {
		int[] size={1000,1000};
		CardLayout card=new CardLayout();
		JPanel mp=new JPanel(card);
		mp.setLayout(card);
		Stage0 s0=new Stage0(size[0],size[1]);
		Stage1 s1=new Stage1(size[0],size[1]);
		StageOver sO=new StageOver(size[0],size[1]);
		mp.add(s0,"Stage0");
		mp.add(s1,"Stage1");
		mp.add(sO,"StageOver");
		MainFrame mf=new MainFrame(size[0],size[1],mp);
		Threadrun runner=new Threadrun(mf,s0,s1,sO,mp,card);
		mf.display();
		runner.startrun();
	}
}
