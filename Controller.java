package alpha;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class Controller implements MouseListener, MouseMotionListener {
	int x = 0, y = 0, sizeY = 0, sizeX = 0;
	int stage = 0;
	List<Bullet> Blist;
	boolean start = false, exit = false;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (stage == 0) {
			if (x > (int) (sizeX / 2.2) && y > (int) (sizeY / 1.7) - 25 && x < ((int) (sizeX / 2.2) + 90)
					&& y < ((int) (sizeY / 1.7)))
				start = true;
			if (x > (int) (sizeX / 2.17) && y > (int) (sizeY / 1.5) - 25 && x < ((int) (sizeX / 2.17) + 80)
					&& y < ((int) (sizeY / 1.5)))
				exit = true;
		}
		if(stage==2){
			if (x > (int) (sizeX /  2.3) && y > (int) (sizeY / 1.7) - 25 && x < ((int) (sizeX / 2.2) + 108)
					&& y < ((int) (sizeY / 1.7)))
				start = true;
			if (x > (int) (sizeX / 2.17) && y > (int) (sizeY / 1.5) - 25 && x < ((int) (sizeX / 2.17) + 80)
					&& y < ((int) (sizeY / 1.5)))
				exit = true;
		}
//		if (stage == 1) {
//			Bullet tamago=new Bullet(e.getX(),e.getY());
//			Blist.add(tamago);
//		}
	}

	public boolean isStart() {
		return start;
	}

	public boolean isExit() {
		return exit;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (stage == 1) {
			Bullet tamago=new Bullet(e.getX(),e.getY());
			Blist.add(tamago);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
