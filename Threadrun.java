package alpha;

import java.awt.CardLayout;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

public class Threadrun {
	MainFrame mf;
	Stage0 s0;
	Stage1 s1;
	StageOver sO;
	CardLayout card;
	JPanel mp;
	boolean Elite = false;
	int stage = 0, setcl = 0, speed = 5, ace = 1, n = 1, score = 0;
	int tempspeed;
	boolean stageswitch = false, endgame = false;

	Threadrun(MainFrame mf, Stage0 s0, Stage1 s1, StageOver sO, JPanel mp,
			CardLayout card) {
		this.mf = mf;
		this.s0 = s0;
		this.s1 = s1;
		this.sO = sO;
		this.card = card;
		this.mp = mp;
	}

	void startrun() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				while (true) {
					new Thread() {
						public void run() {
							super.run();
							if (stage == 0)
								s0.repaint();
							if (stage == 1) {
								s1.repaint();
							}
							if (stage == 2) {
								sO.repaint();
							}
						};
					}.start();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
		while (true) {
			new Thread() {
				@Override
				public void run() {
					super.run();
					if (stageswitch == true) {
						StageSwitch();
					}
					if (stage == 0) {
						stage0option();
					}
					if (stage == 1
							&& (s1.full == false || s1.eliteoval == true)) {
						stagecover();
					}
					if (stage == 1) {
						BulletShooter();
						EnemyActor();
						EnemyEngender();
						EnemyCombine();
						EnemyKilled();
						 if (/*s1.full == true &&*/ s1.eliteoval == false)
						overgame();
						n++;
					}
					if (stage == 2 && sO.minium == true) {
						stageOverOption();
					}
					if (stage == 2 && sO.minium == false) {
						stagecover();
						BulletShooter();
						EnemyActor();
						EnemyEngender();
						EnemyCombine();
						n++;
					}
				};
			}.start();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void StageSwitch() {
		switch (stage) {
		case 1:
			s1intial();
			card.show(mp, "Stage1");
			stageswitch = false;
			break;
		case 2:
			sOintial();
			card.show(mp, "StageOver");
			stageswitch = false;
			break;
		}
	}

	void stage0option() {
		if (s0.con.isStart() == true) {
			stage = 1;
			s1.backcl = s0.backcl;
			s1.con.x = s0.con.x;
			s1.con.y = s0.con.y;
			stageswitch = true;
		}
		if (s0.con.isExit() == true)
			System.exit(0);
	}

	void stagecover() {
		if (stage == 1 && s1.full == false) {
			for (int i = s1.Elist.size() - 1; i >= 0; i--) {
				if (s1.cover >= 2 * Math.sqrt((Math.pow(s1.Elist.get(i).x
						- s1.con.x, 2) + Math.pow(s1.Elist.get(i).y - s1.con.y,
						2))))
					s1.Elist.get(i).incircle = true;
				else
					s1.Elist.get(i).incircle = false;
			}
			speed += ace;
			s1.cover += speed;
			if (s1.cover >= 2 * Math.sqrt((Math.pow(s0.x, 2) + Math
					.pow(s0.y, 2)))) {
				s1.full = true;
				s1.backcl = Math.abs(s1.backcl - 0xffffff);
			}
		}
		if (stage == 1 && s1.eliteoval == true) {
			speed += ace;
			s1.cover += speed;
			if (s1.cover >= 2 * Math
					.sqrt((Math.pow(s1.elx - s1.con.x, 2) + Math.pow(s1.elx
							- s1.con.y, 2)))) {
				s1.starcl = 0;
			} else {
				s1.starcl = 1;
			}
			if (s1.cover >= 2 * Math.sqrt((Math.pow(s1.x, 2) + Math
					.pow(s1.y, 2)))) {
				s1.eliteoval = false;
				s1.Elist.removeAll(s1.Elist);
				s1.backcl = s1.clset;
				s1.starcl = 1;
			}
		}
		if (stage == 2) {
			for (int i = sO.Elist.size() - 1; i >= 0; i--) {
				if (sO.cover >= 2 * Math.sqrt((Math.pow(sO.Elist.get(i).x
						- sO.con.x, 2) + Math.pow(sO.Elist.get(i).y - sO.con.y,
						2))))
					sO.Elist.get(i).incircle = true;
				else
					sO.Elist.get(i).incircle = false;
			}
			speed -= ace;
			speed = Math.abs(speed) + 1;
			sO.cover -= speed;
			if (sO.cover <= 20) {
				sO.minium = true;
			}
		}
	}

	void BulletShooter() {
		for (int i = s1.Blist.size() - 1; i >= 0; i--) {
			s1.Blist.get(i).speed += s1.Blist.get(i).ace;
			s1.Blist.get(i).y -= s1.Blist.get(i).speed;
			if (s1.Blist.get(i).y < -50)
				s1.Blist.remove(i);

		}
	}

	void EnemyEngender() {
		if (s1.Elist.size() == 0) {
			for (int i = 0; i < 5; i++) {
				Enemy deki = new Enemy(i * s1.x / 5 + 50, 0 - 50, 0);
				s1.Elist.add(deki);
			}
		} else {
			if (n % (int) (30000 / n) == 0) {
				if (new Random().nextBoolean()) {
					if (new Random().nextBoolean()) {
						Enemy deki = new Enemy(
								new Random().nextInt(s1.x - 100) + 50, 0 - 50,
								0);
						s1.Elist.add(deki);
					} else {
						Enemy deki = new Enemy(
								new Random().nextInt(s1.x - 100) + 50,
								s1.y + 50, 0);
						deki.speedY *= -1;
						s1.Elist.add(deki);
					}
				} else {
					if (new Random().nextBoolean()) {
						Enemy deki = new Enemy(0 - 50, new Random()
								.nextInt(s1.y - 100) + 50, 0, 1);
						s1.Elist.add(deki);
					} else {
						Enemy deki = new Enemy(s1.x + 50, new Random()
								.nextInt(s1.y - 100) + 50, 0, -1);
						s1.Elist.add(deki);
					}
				}
			}
		}
		if (Elite == false && n % 1000 == 0) {
			if (new Random().nextBoolean()) {
				if (new Random().nextBoolean()) {
					Enemy deki = new Enemy(
							new Random().nextInt(s1.x - 30) + 15, 0 - 15, 1);
					s1.Elist.add(deki);
				} else {
					Enemy deki = new Enemy(
							new Random().nextInt(s1.x - 30) + 15, s1.y + 15, 1);
					deki.speedY *= -1;
					s1.Elist.add(deki);
				}
			} else {
				if (new Random().nextBoolean()) {
					Enemy deki = new Enemy(0 - 15, new Random()
							.nextInt(s1.y - 30) + 15, 1, 1);
					s1.Elist.add(deki);
				} else {
					Enemy deki = new Enemy(s1.x + 15, new Random()
							.nextInt(s1.y - 30) + 15, 1, -1);
					s1.Elist.add(deki);
				}
			}
			Elite = true;
		}
		/*if(Elite==false&&n==200){
		Enemy deki = new Enemy(440, 500, 0, 0);
		s1.Elist.add(deki);
		Enemy deki1 = new Enemy(466, 510, 0, 0);
		s1.Elist.add(deki1);
		Elite=true;
		}*/
	}

	void EnemyActor() {
		for (int i = s1.Elist.size() - 1; i >= 0; i--) {
			s1.Elist.get(i).y += s1.Elist.get(i).speedY;
			s1.Elist.get(i).x += s1.Elist.get(i).speedX;
			if (s1.Elist.get(i).y <= s1.Elist.get(i).size / 2)
				s1.Elist.get(i).speedY = Math.abs(s1.Elist.get(i).speedY);
			if (s1.Elist.get(i).y >= s1.y - s1.Elist.get(i).size / 2 - 20)
				s1.Elist.get(i).speedY = -Math.abs(s1.Elist.get(i).speedY);
			if (s1.Elist.get(i).x >= s1.x - s1.Elist.get(i).size / 2)
				s1.Elist.get(i).speedX = -Math.abs(s1.Elist.get(i).speedX);
			if (s1.Elist.get(i).x <= s1.Elist.get(i).size / 2)
				s1.Elist.get(i).speedX = Math.abs(s1.Elist.get(i).speedX);
			s1.Elist.get(i).expand();
			if (s1.Elist.get(i).size <= 10) {
				s1.Elist.remove(i);
			}
		}

	}

	void EnemyKilled() {
		boolean remov = false;
		for (int i = s1.Blist.size() - 1; i >= 0; i--) {
			for (int j = s1.Elist.size() - 1; j >= 0; j--) {
				// if (Math.sqrt(Math.pow(s1.Blist.get(i).x - s1.Elist.get(j).x,
				// 2)
				// + Math.pow(s1.Blist.get(i).y - s1.Elist.get(j).y, 2)) <=
				// s1.Elist.get(j).size / 2) {
				if (Math.abs(s1.Blist.get(i).x - s1.Elist.get(j).x) <= s1.Elist
						.get(j).size / 2
						&& s1.Blist.get(i).y >= s1.Elist.get(j).y
						&& s1.Blist.get(i).y - s1.Blist.get(i).speed
								- s1.Blist.get(i).ace <= s1.Elist.get(j).y) {
					score++;
					if (s1.Elist.get(j).type == 0) {
						if (s1.Elist.get(j).size <= 45) {
							s1.Elist.get(j).setExpand(-45);
							score += 20;
							remov = true;
							// s1.Elist.remove(j);
							// score++;
						} else {
							s1.Elist.get(j).expand -= (s1.Elist.get(j).size - (int) Math
									.sqrt(Math.pow(s1.Elist.get(j).size, 2) / 1.2 - 800));
							remov = true;
						}
					}
					if (s1.Elist.get(j).type == 1) {
						if (s1.Elist.get(j).health <= 1) {
							s1.eliteoval = true;
							s1.elx = s1.Elist.get(j).x;
							s1.ely = s1.Elist.get(j).y;
							s1.elsize = s1.Elist.get(j).size;
							s1.cover = 0;
							speed = 5;
							s1.Elist.remove(j);
							Elite = false;
							score += 300;
							n = 0;
							remov = true;
							if (setcl == 3) {
								if (new Random().nextBoolean())
									s1.clset = 0xffffff;
								else
									s1.clset = 0x000000;
								setcl = 0;
							}
							setcl += 1;
						} else {
							s1.Elist.get(j).health -= 1;
							remov = true;
						}
					}
				}
			}
			if (remov == true) {
				s1.Blist.remove(i);
				remov = false;
			}
		}
	}

	void overgame() {
		for (int j = 0; j < s1.Elist.size(); j++) {
			if ((int) Math.sqrt(Math.pow(s1.con.getX() - s1.Elist.get(j).x, 2)
					+ Math.pow(s1.con.getY() - s1.Elist.get(j).y, 2)) <= s1.Elist
					.get(j).size / 2) {
				stageswitch = true;
				stage = 2;
				if (s1.full == true)
					sO.backcl = Math.abs(s1.backcl - 0xffffff);
				sO.con.x = s1.con.x;
				sO.con.y = s1.con.y;
				sO.cover = s1.cover;
				sO.Elist = s1.Elist;
				sO.Blist = s1.Blist;
				sO.score = score * 2 + n / 30;
				s1.endgame = true;
				s1.score = score * 2 + n / 30;
				break;
			}
		}
	}

	void stageOverOption() {
		if (sO.con.isStart() == true && endgame == true) {
			stage = 1;
			s1.backcl = sO.backcl;
			s1.con.x = sO.con.x;
			s1.con.y = sO.con.y;
			stageswitch = true;
			endgame = false;
		}
		if (sO.con.isExit() == true)
			System.exit(0);
	}

	void sOintial() {
		sO.con.start = false;
		endgame = true;
		sO.minium = false;
		Elite = false;
	}

	void s1intial() {
		s1.full = false;
		s1.cover = 0;
		s1.eliteoval = false;
		speed = 5;
		n = 1;
		score = 0;
		s1.Elist.removeAll(s1.Elist);
		s1.Blist.removeAll(s1.Blist);
	}

	void EnemyCombine() {
		int[] tem = new int[30];
		int r = 0;
		for (int i = 0; i < s1.Elist.size(); i++) {
			for (int j = s1.Elist.size() - 1; j >= i; j--) {
				if (i != j) {
					if ((int) Math.sqrt(Math.pow(s1.Elist.get(i).x
							- s1.Elist.get(j).x, 2)
							+ Math
									.pow(s1.Elist.get(i).y - s1.Elist.get(j).y,
											2)) <= (int) (s1.Elist.get(j).size / 2 + s1.Elist
							.get(i).size / 2)) {
						if (s1.Elist.get(i).size - s1.Elist.get(j).size > 25
								&& s1.Elist.get(j).type == 0
								&& s1.Elist.get(i).type == 0
								&& s1.Elist.get(i).size <= 300) {
							if ((int) Math.sqrt(Math.pow(s1.Elist.get(i).x
									- s1.Elist.get(j).x, 2)
									+ Math.pow(s1.Elist.get(i).y
											- s1.Elist.get(j).y, 2)) < (int) (s1.Elist
									.get(i).size / 2)) {
								s1.Elist.get(i).expand += (int) Math.sqrt(Math
										.pow(s1.Elist.get(j).size, 2)
										+ Math.pow(s1.Elist.get(i).size, 2))
										- s1.Elist.get(i).size;
								tem[r] = j;
								r++;
							}
						} else if (s1.Elist.get(i).size - s1.Elist.get(j).size < -25
								&& s1.Elist.get(j).type == 0
								&& s1.Elist.get(i).type == 0
								&& s1.Elist.get(j).size <= 300) {
							if ((int) Math.sqrt(Math.pow(s1.Elist.get(i).x
									- s1.Elist.get(j).x, 2)
									+ Math.pow(s1.Elist.get(i).y
											- s1.Elist.get(j).y, 2)) < (int) (s1.Elist
									.get(i).size / 2)) {
								s1.Elist.get(j).expand += (int) Math.sqrt(Math
										.pow(s1.Elist.get(j).size, 2)
										+ Math.pow(s1.Elist.get(i).size, 2))
										- s1.Elist.get(j).size;
								tem[r] = i;
								r++;
							}
						} else {
							tempspeed = s1.Elist.get(i).speedX;
							s1.Elist.get(i).speedX = s1.Elist.get(j).speedX;
							s1.Elist.get(j).speedX = tempspeed;
							tempspeed = s1.Elist.get(i).speedY;
							s1.Elist.get(i).speedY = s1.Elist.get(j).speedY;
							s1.Elist.get(j).speedY = tempspeed;
							if ((int) Math.sqrt(Math.pow(s1.Elist.get(i).x
									+ s1.Elist.get(i).speedX
									- s1.Elist.get(j).x
									- s1.Elist.get(j).speedX, 2)
									+ Math.pow(s1.Elist.get(i).y
											+ s1.Elist.get(i).speedY
											- s1.Elist.get(j).y
											- s1.Elist.get(i).speedY, 2)) < (int) (s1.Elist
									.get(j).size / 2 + s1.Elist.get(i).size / 2)) {
								if (s1.Elist.get(i).x - s1.Elist.get(j).x < 0
										&& s1.Elist.get(i).y
												- s1.Elist.get(j).y < 0) {
									s1.Elist.get(i).x--;
									s1.Elist.get(i).y--;
									s1.Elist.get(j).x++;
									s1.Elist.get(j).y++;
								}
								if (s1.Elist.get(i).x - s1.Elist.get(j).x < 0
										&& s1.Elist.get(i).y
												- s1.Elist.get(j).y > 0) {
									s1.Elist.get(i).x--;
									s1.Elist.get(i).y++;
									s1.Elist.get(j).x++;
									s1.Elist.get(j).y--;
								}
								if (s1.Elist.get(i).x - s1.Elist.get(j).x > 0
										&& s1.Elist.get(i).y
												- s1.Elist.get(j).y < 0) {
									s1.Elist.get(i).x++;
									s1.Elist.get(i).y--;
									s1.Elist.get(j).x--;
									s1.Elist.get(j).y++;
								}
								if (s1.Elist.get(i).x - s1.Elist.get(j).x > 0
										&& s1.Elist.get(i).y
												- s1.Elist.get(j).y > 0) {
									s1.Elist.get(i).x++;
									s1.Elist.get(i).y++;
									s1.Elist.get(j).x--;
									s1.Elist.get(j).y--;
								}
							}
						}
					}
				}
			}
		}
		for (int i = r; i < tem.length; i++)
			tem[i] = 9999;
		Arrays.sort(tem);
		for (int i = r - 1; i >= 0; i--)
			s1.Elist.remove(tem[i]);
	}
}
