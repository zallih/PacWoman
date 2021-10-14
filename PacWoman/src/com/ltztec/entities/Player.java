package com.ltztec.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ltztec.main.Game;
import com.ltztec.world.Camera;
import com.ltztec.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public int lastDir = 1;
	
	public BufferedImage sprite_left;
	public BufferedImage sprite_up;
	public BufferedImage sprite_down;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(32, 16, 16, 16);
		sprite_up = Game.spritesheet.getSprite(128, 80, 16, 16);
		sprite_down = Game.spritesheet.getSprite(128, 0, 16, 16);

	}
	
	public void tick(){
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			lastDir = 1;
		}else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			lastDir = -1;
		}else if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			lastDir = 2;
		}else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			lastDir = -2;
		}
		
		checkPickFruit();
		if(Game.cherry_currently == Game.cherry_count) {
			System.out.println("Você ganhou!");
			
		}
	}
	
	public void checkPickFruit() {
		
		for(int i  = 0; i < Game.entities.size(); i ++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Cherry) {
				if(isColidding(this, current)) {
					Game.cherry_currently++;
					Game.entities.remove(i);
					return;
				}
				
			}
		}
	}
	
	public void render(Graphics g) {
		if(lastDir == 1) {
			super.render(g);
		}else if(lastDir == -1){
			g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);

		}else if(lastDir == 2){
			g.drawImage(sprite_up,this.getX() - Camera.x,this.getY() - Camera.y,null);

		}else if(lastDir == -2){
			g.drawImage(sprite_down,this.getX() - Camera.x,this.getY() - Camera.y,null);

		}
		
		
		
	}

	


}
