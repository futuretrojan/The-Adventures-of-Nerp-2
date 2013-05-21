package Nerp;

import java.util.Stack;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	TiledMap map;
	Vector2f spawn;
	Stack<String> lvls;

	public Level(Stack<String> levelFiles, Vector2f spawn) throws SlickException {
		map = new TiledMap(levelFiles.pop());
		lvls = levelFiles;
		this.spawn = spawn;
	}
	
	public void nextLevel(Player p) throws SlickException{
		this.map = new TiledMap(lvls.pop());
		p.kill();
		
		
	}

	public void checkCollision(Player p) {
		int x = (int) p.position.x / 25;
		int y = (int) p.position.y / 25;
		int id = map.getTileId(x, y, 0);
		if (tileHasCollision(id)) {
			float xoff = p.position.x % 25f;
			float yoff = p.position.y % 25f;
			if (yoff <= 2f) {
				p.position.y = y * 25f - .02f;
				p.velocity.y = 0;
				p.isJumping = false;
			} else if (yoff > 23f) {
				p.position.y = y * 25f + 25.02f;
				p.velocity.y = -p.velocity.y;
			}

			yoff = p.position.y % 25f;
			if ((xoff < 1f || xoff > 24f)) {
				p.position.x = (xoff < 12.5f ? x * 25 - .02f : x * 25 + 25.02f);
			}
		}
		tileEffect(id, p);
	}

	public void tileEffect(int id, Player p) {
		if (id == 2) { //spike
			p.kill();
		} else if (id == 1) { //ladder
			p.velocity.y = -.2f;
		} else if (id == 4) { //checkpoint/flag
			try {
				nextLevel(p);
			} catch (Exception e) {
				System.exit(0); //If no level is next, exit game
			}
		}
	}

	public boolean tileHasCollision(int id) {
		return (id == 3);
	}

	enum Direction {

		LEFT, RIGHT, UP, DOWN;

	}

}
