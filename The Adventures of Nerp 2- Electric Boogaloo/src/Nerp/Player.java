package Nerp;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Player implements KeyListener {
	Image playerImage;
	float speed = 0.5f;
	float jumpSpeed = 0.75f;
	boolean isJumping = false;
	
	Level lvl = null;
	
	public Player(GameContainer gc) throws SlickException {
		
		playerImage = new Image("Images/Nerp.png");
		position = new Vector2f(100,100);
		velocity = new Vector2f(0f, 0);
		gc.getInput().addKeyListener(this);
		
	}

	public Vector2f position;
	Vector2f velocity;

	public void render(GameContainer gc, Graphics g) {
		
		g.drawImage(playerImage, position.x - playerImage.getWidth()/2, position.y - playerImage.getHeight());
		
	}

	public void update(GameContainer gc, int delta) {
		// This is gravity
		velocity.y += .001 * delta;
		position.x += velocity.x;
		position.y += velocity.y;
//		if (position.y > gc.getHeight()*.9f) {
//			position.y = gc.getHeight()*.9f;
//			velocity.y = 0;
//			isJumping = false;
//		}
		if (position.x < 0 || position.x > gc.getWidth())
			kill();
		if (position.y < 0 || position.y > gc.getHeight())
			kill();
		if (lvl != null) lvl.checkCollision(this);
	}
	
	public void setLevel(Level l) {
		this.lvl = l;
		kill();
	}
	
	public void kill() {
		if (lvl == null) {
			this.position = new Vector2f(100,100);
		} else {
			this.position.x = lvl.spawn.x;
			this.position.y = lvl.spawn.y;
		}
		velocity.y = 0f;
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input arg0) {
		
	}

	public void keyPressed(int keyCode, char keyChar) {
		
		switch(keyCode){
		case Input.KEY_LEFT:
			velocity.x -= speed;
			break;
			
		case Input.KEY_RIGHT:
			velocity.x +=speed;
			break;
			
		case Input.KEY_SPACE:
			if (!isJumping)
				velocity.y -= jumpSpeed;
			isJumping = true;
		
		default: break;
		}
		
	}

	@Override
	public void keyReleased(int keyCode, char keyChar) {
		
		switch(keyCode){
		case Input.KEY_LEFT:
			velocity.x += speed;
			break;
			
		case Input.KEY_RIGHT:
			velocity.x -=speed;
			break;
			
		
		default: break;
		}
		
	}

}
