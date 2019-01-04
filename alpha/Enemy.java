package alpha;

import java.util.Random;

public class Enemy {
	int x = 0, y = 0;
	int size = 50;
	int type = 0;
	int speedY = 3;
	int speedX = 2;
	int health = 1;
	int expand = 0;
	boolean incircle=false;

	Enemy(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		if (type == 0) {
			speedY = new Random().nextInt(4) + 2;
			speedX = (new Random().nextInt(3) + 1) * (new Random().nextInt(3) - 1);
			size = new Random().nextInt(50) + 50;
		}
		if (type == 1) {
			this.size = 30;
			health = 3;
			speedY = new Random().nextInt(4) + 2;
			speedX = (new Random().nextInt(3) + 1) * (new Random().nextInt(3) - 1);
		}
	}

	Enemy(int x, int y, int type, int edge) {
		this.x = x;
		this.y = y;
		this.type = type;
		if (type == 0) {
			speedY = new Random().nextInt(4) + 2;
			speedX = (new Random().nextInt(4) + 2);
			speedX *= edge;
			size = new Random().nextInt(50) + 50;
		}
		if (type == 1) {
			this.size = 30;
			health = 3;
			speedY = new Random().nextInt(4) + 2;
			speedX = (new Random().nextInt(4) + 2);
			speedX *= edge;
		}
	}

	void expand() {
		if (expand > 0) {
			size++;
			expand--;
		}
		if (expand < 0) {
			size--;
			expand++;
		}
	}
	void setExpand(int expand){
		this.expand=expand;
	}
}
