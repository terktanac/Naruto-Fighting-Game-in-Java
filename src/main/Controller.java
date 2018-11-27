package main;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
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
	private String name;
	private static Map<KeyCode, Boolean> isPressed = new HashMap<KeyCode,Boolean>();
	
	public Controller(String name,KeyCode upKey, KeyCode downKey, KeyCode leftKey, KeyCode rightKey, KeyCode meleeKey,
			KeyCode rangeKey, KeyCode blockKey, KeyCode dodgeKey) {
		super();
		this.name = name ;
		this.upKey = upKey; isPressed.put(this.upKey, false);
		this.downKey = downKey; isPressed.put(this.downKey, false);
		this.leftKey = leftKey; isPressed.put(this.leftKey, false);
		this.rightKey = rightKey; isPressed.put(this.rightKey, false);
		this.meleeKey = meleeKey; isPressed.put(this.meleeKey, false);
		this.rangeKey = rangeKey; isPressed.put(this.rangeKey, false);
		this.blockKey = blockKey; isPressed.put(this.blockKey, false);
		this.dodgeKey = dodgeKey; isPressed.put(this.dodgeKey, false);
		
	}


	@Override
	public void run() {
		System.out.println(name + " start");
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				System.out.println(name + " pressed: "+key);
				if(isPressed.containsKey(key)) {
					isPressed.put(key,true);
					System.out.println(isPressed.get(key).toString()+" "+key);
				}
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				System.out.println(name + " release: "+key);
				if(isPressed.containsKey(key)) {
					isPressed.put(key, false);
					System.out.println(isPressed.get(key).toString()+" "+key);
				}
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


	public static Map<KeyCode, Boolean> getIsPressed() {
		return isPressed;
	}


	public static void setIsPressed(Map<KeyCode, Boolean> isPressed) {
		Controller.isPressed = isPressed;
	}
	
	
}
