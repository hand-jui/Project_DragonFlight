package dragonF;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GameElement extends Thread {

	private Image player = new ImageIcon("images/player1.png").getImage();
	private GameOverFrame gameOverFrame;

	// 적군이 여러마리 -> 각자의 절대값이 다르기 때문에 총알 하나하나의 기능을 수행하기 위해
	private ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private PlayerAttack playerAttack;
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerSpeed = 10;
	protected int state = 0;

	// 적군 여러마리 생성 리스트
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private Enemy enemy;

	private int count; // 총알 발사 빠르기 조절
	private int score;

	private boolean left;
	private boolean right;
	private boolean shooting;
	private boolean isOver;
	private boolean runFlag = true;

	private GameFrame mContext;

	public GameElement(GameFrame mContext) {
		this.mContext = mContext;
	}

	@Override
	public void run() {
		reset();
		while (runFlag) {
			while (!isOver) { // 부정으로 true
				// 게임 멈추기 위한 if문 - restart 구현부
				if (state == 1) {
					isOver = true;
					runFlag = false;
					mContext.setVisible(false);
					gameOverFrame = new GameOverFrame();
					break;
				}
				if (true) {

					try {
						Thread.sleep(10);
						keyProcess();
						playerAttackProcess();
						enemyAppearProcess(); // 적 생성 메서드 호출
						enemyMoveProcess(); // 적 아래로 내려오는 메서드 호출
						playerbeAttacked(); // 플레이어 감지
						count++;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 플레이어 초기 위치
	public void reset() {
		isOver = false;
		count = 0;
		score = 0;
		playerX = 250;
		playerY = 780;

		playerAttackList.clear();
		enemyList.clear();

	}

	// 배경 밖으로 못나가게하는 코드
	private void keyProcess() {
		if (left && playerX - playerSpeed > 0)
			playerX -= playerSpeed;
		if (right && playerX + playerWidth + playerSpeed < ScreenSize.SCREEN_WIDTH)
			playerX += playerSpeed;
		if (shooting && count % 10 == 0) {
			playerAttack = new PlayerAttack(playerX - 10, playerY - 70);
			playerAttackList.add(playerAttack);
		}
	}

	// 총알 피격 판정 메서드
	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i); // 총알 어레이를 변수에 넣는다
			playerAttack.fire();

			for (int j = 0; j < enemyList.size(); j++) {
				enemy = enemyList.get(j);
				if (playerAttack.x + 60> enemy.x && playerAttack.x +60 < enemy.x + enemy.width && playerAttack.y > enemy.y
						&& playerAttack.y < enemy.y + enemy.height) {
					enemy.hp -= playerAttack.attack; // 적의 Hp를 깎는다
					playerAttackList.remove(playerAttack); // 위의 조건에 부합하면 총알을 지운다.
				}
				if (enemy.hp <= 0) { // 적 Hp가 0이 되면

					enemyList.remove(enemy); // 적배열을
					score += 100;
				}
			}
		}
	}

	private void playerbeAttacked() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (Math.abs(playerX - enemyList.get(i).x) < 50 && Math.abs(playerY - enemyList.get(i).y) < 50) {
				player = null;
				state = 1;
			}
		} // end of for
	} // end of beAttacked

	private void enemyAppearProcess() {
		if (count % 80 == 0) {
			// enemy = new Enemy(1120, (int)(Math.random()*621));
			enemyList.add(new Enemy(20, 100));
			enemyList.add(new Enemy(135, 100));
			enemyList.add(new Enemy(250, 100));
			enemyList.add(new Enemy(365, 100));
			enemyList.add(new Enemy(480, 100));
		}
	}

	private void enemyMoveProcess() {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			enemy.move();
		}
	}

	public void gameElementDraw(Graphics g) {
		if (player != null) {
			playerDraw(g); // 플레이어 그리는거 호출
		}
		enemyDraw(g);// 적 그리는거 호출
		infoDraw(g); // 게임 정보 호출
	}

	public void playerDraw(Graphics g) {
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.GREEN);
		g.fillRect(playerX - 1, playerY - 10, 100, 10);
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
	}

	public void enemyDraw(Graphics g) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			g.drawImage(enemy.image, enemy.x, enemy.y, null);
			g.setColor(Color.yellow);
			g.fillRect(enemy.x + 1, enemy.y - 40, enemy.hp * 15, 15);
		}
	}

	// 게임 정보 그려주는 곳
	public void infoDraw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE : " + score, 40, 80); // 스코어 표시
		if (isOver) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 63));
			g.drawString("GAME OVER", 100, 400);
		}
	}

	public boolean isOver() {
		return isOver;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
}
