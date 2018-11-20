package main;

import javafx.scene.input.KeyCode;

public class Controller implements Runnable{
	private KeyCode leftKey = KeyCode.LEFT;
	private KeyCode rightKey = KeyCode.RIGHT;
	private double x_pos;
	private double y_pos;
	private double x_speed;
	private double y_speed;
	private double x;
	private double y;
	private boolean isJumping = false;
	@Override
	public void run() {
		/*if(moveLeft()) {
			x_pos += x_speed ;
		}
		if(moveRight()) {
			x_pos -= x_speed ;
		}
		if(jump()) {
			isJumping = true;
		}
		if(isJumping) {
			
		}
		if(crouch()) {
			x /= 2;
		}*/
	}

}
