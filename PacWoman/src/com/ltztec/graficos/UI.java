package com.ltztec.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ltztec.main.Game;


public class UI {
	public void render(Graphics g) {

		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD,25));
		g.drawString("Cerejas - " + Game.cherry_currently + " / " + Game.cherry_count, 10, 24);
	}
	
}
