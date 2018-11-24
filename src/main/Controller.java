package main;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller implements Runnable{
	private KeyCode upKey;
	private KeyCode downKey;
	private KeyCode leftKey ;
	private KeyCode rightKey;
	private KeyCode meleeKey ;
	private KeyCode rangeKey;
	private KeyCode blockKey;
	private KeyCode dodgeKey ;
	private double x_pos;
	private double y_pos;
	private double x_speed;
	private double y_speed;
	private double x;
	private double y;
	private boolean isJumping = false;
	private myScene scene;
	
	
	public Controller(KeyCode upKey, KeyCode downKey, KeyCode leftKey, KeyCode rightKey, KeyCode meleeKey,
			KeyCode rangeKey, KeyCode blockKey, KeyCode dodgeKey) {
		super();
		this.upKey = upKey;
		this.downKey = downKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.meleeKey = meleeKey;
		this.rangeKey = rangeKey;
		this.blockKey = blockKey;
		this.dodgeKey = dodgeKey;
	}


	@Override
	public void run() {
		System.out.println("HI");
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				if(key == upKey) {scene.upPressed();}
				else if(key == downKey) {scene.downPressed();}
			}
		});
	}

//	public void keyHandling() {
//		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				KeyCode key = event.getCode();
//				System.out.println("Controller: "+key);
//				
//			}
//		});
//	}
	public void setScene(myScene scene) {
		this.scene = scene;
	}


	public KeyCode getUpKey() {
		return upKey;
	}


	public void setUpKey(KeyCode upKey) {
		this.upKey = upKey;
	}


	public KeyCode getDownKey() {
		return downKey;
	}


	public void setDownKey(KeyCode downKey) {
		this.downKey = downKey;
	}


	public KeyCode getLeftKey() {
		return leftKey;
	}


	public void setLeftKey(KeyCode leftKey) {
		this.leftKey = leftKey;
	}


	public KeyCode getRightKey() {
		return rightKey;
	}


	public void setRightKey(KeyCode rightKey) {
		this.rightKey = rightKey;
	}


	public KeyCode getMeleeKey() {
		return meleeKey;
	}


	public void setMeleeKey(KeyCode meleeKey) {
		this.meleeKey = meleeKey;
	}


	public KeyCode getRangeKey() {
		return rangeKey;
	}


	public void setRangeKey(KeyCode rangeKey) {
		this.rangeKey = rangeKey;
	}


	public KeyCode getBlockKey() {
		return blockKey;
	}


	public void setBlockKey(KeyCode blockKey) {
		this.blockKey = blockKey;
	}


	public KeyCode getDodgeKey() {
		return dodgeKey;
	}


	public void setDodgeKey(KeyCode dodgeKey) {
		this.dodgeKey = dodgeKey;
	}
	
	
}
