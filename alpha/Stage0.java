package alpha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Stage0 extends JPanel{
	int x,y;
	int backcl=0x000000;
	int cover;
	Controller con;

	Stage0(int x,int y){
		this.x=x;
		this.y=y;
		this.con=new Controller();
		con.sizeX=x;
		con.sizeY=y;
		con.stage = 2;
		this.addMouseListener(con);
		this.addMouseMotionListener(con);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.setBackground(new Color(backcl));
		g.setFont(new Font("Arial",Font.PLAIN,60));
		g.setColor(new Color(Math.abs(backcl-0xffffff)));
		g.drawString("Game", (int)(x/2.4),(int)(y/3));
		g.setFont(new Font("Arial",Font.PLAIN,30));
		g.drawString("start", (int)(x/2.2),(int)(y/1.7));
		g.drawString("exit", (int)(x/2.17),(int)(y/1.5));
	}
}
