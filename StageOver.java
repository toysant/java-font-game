package alpha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class StageOver extends JPanel {
	int x, y;
	int backcl = 0x000000;
	int cover;
	int score=0;
	boolean minium = false;
	List<Bullet> Blist ;
	List<Enemy> Elist; 
	Controller con;

	StageOver(int x, int y) {
		this.x = x;
		this.y = y;
		this.con = new Controller();
		con.sizeX = x;
		con.sizeY = y;
		con.stage=2;
		this.addMouseListener(con);
		this.addMouseMotionListener(con);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.setBackground(new Color(backcl));
		g.setFont(new Font("Arial", Font.PLAIN, 60));
		g.setColor(new Color(Math.abs(backcl - 0xffffff)));
		g.drawString("GameOver", (int) (x / 3.2), (int) (y / 3));
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("yourscore  "+score, (int) (x / 2.5), (int) (y / 2.2));
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("restart", (int) (x / 2.3), (int) (y / 1.7));
		g.drawString("exit", (int) (x / 2.17), (int) (y / 1.5));
		if (minium == false) {
			g.setFont(new Font("Arial", Font.PLAIN, 50));
			g.setColor(new Color(Math.abs(backcl - 0xffffff)));
			g.fillOval(con.getX() - cover / 2, con.getY() - cover / 2, cover, cover);
			g.setColor(new Color(Math.abs(backcl)));
			for (int i =  Blist.size()-1; i >= 0; i--)
				g.drawString("*", Blist.get(i).x - 10, Blist.get(i).y +15);
			for (int i =  Elist.size()-1; i >= 0; i--){
				if(Elist.get(i).incircle==true){
				if (Elist.get(i).type == 0)
					g.fillOval(Elist.get(i).x-Elist.get(i).size/2, Elist.get(i).y-Elist.get(i).size/2, Elist.get(i).size,
							Elist.get(i).size);
				if (Elist.get(i).type == 1) {
					g.setColor(new Color(new Random().nextInt(0xffffff) + 1));
					g.fillOval(Elist.get(i).x - Elist.get(i).size / 2, Elist.get(i).y - Elist.get(i).size / 2,
							Elist.get(i).size, Elist.get(i).size);
						g.setColor(new Color(backcl));
				}
				}
			}
		}
	}

}
