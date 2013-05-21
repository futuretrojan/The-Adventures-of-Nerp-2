package Nerp;

import java.util.Stack;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Main extends BasicGame{
	Image backgroundImage;
	Player p;
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main());
		app.setDisplayMode(650, 500, false);
		app.setResizable(false);
		app.start();
	}
	
	public Main() {
		super("Adventures of Nerp 2: Electric Boogaloo");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		float scale = backgroundImage.getHeight() / (float)gc.getHeight();
		g.scale(1/scale, 1/scale);
		g.drawImage(backgroundImage, 0, 0);
		g.scale(scale, scale);
		p.lvl.map.render(0, 0);
		p.render(gc, g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		p = new Player(gc);
		
		Stack<String> lvls= new Stack<String>();
		lvls.add(0, "Levels/Level1.tmx");
		lvls.add(0, "Levels/Level2.tmx");
		
		p.setLevel(new Level(lvls, new Vector2f(35, 35)));
		backgroundImage = new Image("Images/Background.png");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		p.update(gc, delta);
	}

	
	
}
