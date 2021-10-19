package com.ltztec.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ltztec.main.Game;
import com.ltztec.world.Camera;
import com.ltztec.world.World;

public class Player extends Entity {
	public boolean right, left, up, down;
	public double speed = 1.2;

	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = down_dir;

	private int frames = 0, maxFrames = 6, index = 0, maxIndex = 2;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;

	//	public BufferedImage sprite_left;
	//	public BufferedImage sprite_up;
	//	public BufferedImage sprite_down;

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		upPlayer = new BufferedImage[3];
		downPlayer = new BufferedImage[3];

		//		sprite_left = Game.spritesheet.getSprite(32, 16, 16, 16);
		//		sprite_up = Game.spritesheet.getSprite(128, 80, 16, 16);
		//		sprite_down = Game.spritesheet.getSprite(128, 0, 16, 16);

		for (int i = 0; i < 3; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
		}
		for (int i = 0; i < 3; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(64 - (i * 16), 16, 16, 16);
		}
		for (int i = 0; i < 3; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(128, 80 - (i * 16), 16, 16);
		}
		for (int i = 0; i < 3; i++) {
			downPlayer[i] = Game.spritesheet.getSprite(128, 0 + (i * 16), 16, 16);

		}
	}

	public void tick() {
		depth = 1;
		moved = false;
		if (right && World.isFree((int) (x + speed), this.getY())) {
			moved = true;
			dir = right_dir;
			x += speed;
		} else if (left && World.isFree((int) (x - speed), this.getY())) {
			moved = true;
			dir = left_dir;
			x -= speed;
		} else if (up && World.isFree(this.getX(), (int) (y - speed))) {
			moved = true;
			dir = up_dir;
			y -= speed;
		} else if (down && World.isFree(this.getX(), (int) (y + speed))) {
			moved = true;
			dir = down_dir;
			y += speed;
		}
		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}

		checkPickFruit();
		
		if (Game.cherry_currently == Game.cherry_count) {
			// Ganhamos
			World.restartGame();
		}
		
		
	}

	public void checkPickFruit() {

		for (int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if (current instanceof Cherry) {
				if (isColidding(this, current)) {
					Game.cherry_currently++;
					Game.entities.remove(i);
					return;
				}

			}
		}
	}

	public void render(Graphics g) {
		if (dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} else if (dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == down_dir) {
			
			g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		}

	}
}
