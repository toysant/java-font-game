package alpha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Stage1 extends JPanel {
	int x, y;
	boolean full = false;
	int backcl;
	int cover = 0;
	int score = 0;
	int clset = 0;
	int starcl = 1;
	int elx = 0, ely = 0, elsize = 0;
	boolean endgame = false, eliteoval = false;
	Controller con;
	List<Bullet> Blist = new ArrayList<Bullet>();
	List<Enemy> Elist = new ArrayList<Enemy>();

	Stage1(int x, int y) {
		this.x = x;
		this.y = y;
		this.con = new Controller();
		con.sizeX = x;
		con.sizeY = y;
		con.stage = 1;
		con.Blist = Blist;
		this.addMouseListener(con);
		this.addMouseMotionListener(con);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.setBackground(new Color(backcl));
		if (full == false && endgame == false) {
			g.setColor(new Color(Math.abs(backcl - 0xffffff)));
			g.fillOval(con.getX() - cover / 2, con.getY() - cover / 2, cover, cover);
			g.setFont(new Font("Arial", Font.PLAIN, 60));
			g.drawString("Game", (int) (x / 2.4), (int) (y / 3));
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString("start", (int) (x / 2.2), (int) (y / 1.7));
			g.drawString("exit", (int) (x / 2.17), (int) (y / 1.5));
			g.setColor(new Color(backcl));
		} else if (full == false && endgame == true) {
			g.setColor(new Color(Math.abs(backcl - 0xffffff)));
			g.fillOval(con.getX() - cover / 2, con.getY() - cover / 2, cover, cover);
			g.setFont(new Font("Arial", Font.PLAIN, 60));
			g.drawString("GameOver", (int) (x / 3.2), (int) (y / 3));
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString("yourscore  " + score, (int) (x / 2.5), (int) (y / 2.2));
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString("restart", (int) (x / 2.3), (int) (y / 1.7));
			g.drawString("exit", (int) (x / 2.17), (int) (y / 1.5));
			g.setColor(new Color(backcl));
		} else {
			g.setColor(new Color(Math.abs(backcl - 0xffffff)));
		}
		g.setFont(new Font("Arial", Font.PLAIN, 50));
		for (int i = Elist.size() - 1; i >= 0; i--) {
			if (Elist.get(i).incircle == true||full==true) {
				if (Elist.get(i).type == 0)
					g.fillOval(Elist.get(i).x - Elist.get(i).size / 2, Elist.get(i).y - Elist.get(i).size / 2,
							Elist.get(i).size, Elist.get(i).size);
				if (Elist.get(i).type == 1 && eliteoval == false) {
					g.setColor(new Color(clset = new Random().nextInt(0xffffff) + 1));
					g.fillOval(Elist.get(i).x - Elist.get(i).size / 2, Elist.get(i).y - Elist.get(i).size / 2,
							Elist.get(i).size, Elist.get(i).size);
					if (full == false)
						g.setColor(new Color(backcl));
					else
						g.setColor(new Color(Math.abs(backcl - 0xffffff)));
				}
			}
		}
		if (eliteoval == true) {
			g.setColor(new Color(clset));
			g.fillOval(elx - elsize / 2 - cover / 2, ely - elsize / 2 - cover / 2, cover + elsize, cover + elsize);
			if (full == false)
				g.setColor(new Color(clset + starcl * backcl));
			else
				g.setColor(new Color(
						Math.abs(starcl - 1) * Math.abs(clset - 0xffffff) + starcl * Math.abs(backcl - 0xffffff)));
		}
		for (int i = Blist.size() - 1; i >= 0; i--)
			g.drawString("*", Blist.get(i).x - 10, Blist.get(i).y + 15);
		g.drawString("*", con.getX() - 10, con.getY() + 25);
	}

}
