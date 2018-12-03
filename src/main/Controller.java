package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller implements Runnable{

	private myScene scene;
	private static Map<KeyCode, Boolean> isPressedMap = new HashMap<KeyCode,Boolean>();
	private static ArrayList<KeyCode> pressedListP1 = new ArrayList<KeyCode>();
	private static ArrayList<KeyCode> pressedListP2 = new ArrayList<KeyCode>();
	private ArrayList<KeyCode> KeyP1 ;
	private ArrayList<KeyCode> KeyP2 ;
	private AnimationTimer gameLoop ;
	private long lastTime = -1;
	
	


	public Controller(KeyCode upKey_1, KeyCode downKey_1, KeyCode leftKey_1, KeyCode rightKey_1
					, KeyCode meleeKey_1,KeyCode rangeKey_1, KeyCode blockKey_1, KeyCode dodgeKey_1
					, KeyCode upKey_2, KeyCode downKey_2,KeyCode leftKey_2, KeyCode rightKey_2
					, KeyCode meleeKey_2, KeyCode rangeKey_2, KeyCode blockKey_2,KeyCode dodgeKey_2) {
		super();
		this.KeyP1.add(upKey_1);		isPressedMap.put(upKey_1, false);
		this.KeyP1.add(downKey_1);		isPressedMap.put(downKey_1, false);
		this.KeyP1.add(leftKey_1);		isPressedMap.put(leftKey_1, false);
		this.KeyP1.add(rightKey_1);		isPressedMap.put(rightKey_1, false);
		this.KeyP1.add(meleeKey_1);		isPressedMap.put(meleeKey_1, false);
		this.KeyP1.add(rangeKey_1);		isPressedMap.put(rangeKey_1, false);
		this.KeyP1.add(blockKey_1);		isPressedMap.put(blockKey_1, false);
		this.KeyP1.add(dodgeKey_1);		isPressedMap.put(dodgeKey_1, false);
		this.KeyP2.add(upKey_2);		isPressedMap.put(upKey_2, false);
		this.KeyP2.add(downKey_2);		isPressedMap.put(downKey_2, false);
		this.KeyP2.add(leftKey_2);		isPressedMap.put(leftKey_2, false);
		this.KeyP2.add(rightKey_2);		isPressedMap.put(rightKey_2, false);
		this.KeyP2.add(meleeKey_2);		isPressedMap.put(meleeKey_2, false);
		this.KeyP2.add(rangeKey_2);		isPressedMap.put(rangeKey_2, false);
		this.KeyP2.add(blockKey_2);		isPressedMap.put(blockKey_2, false);
		this.KeyP2.add(dodgeKey_2);		isPressedMap.put(dodgeKey_2, false);
		
	}

	@Override
	public void run() {
		System.out.println("start");
		scene.setOnKeyPressed((KeyEvent event)->{
			KeyCode key = event.getCode();
			System.out.println("Pressed:"+key);
			if(isPressedMap.containsKey(key) && !isPressedMap.get(key)) {
				isPressedMap.put(key, true);
				if(KeyP1.contains(key)) {pressedListP1.add(key);}
				else {pressedListP2.add(key);}
				System.out.println(isPressedMap.get(key).toString()+" "+key);
			}
		});
		
		scene.setOnKeyReleased((KeyEvent event)->{
			KeyCode key = event.getCode();
			System.out.println("Release:"+key);
			if(isPressedMap.containsKey(key)) {
				isPressedMap.put(key,false);
				System.out.println(isPressedMap.get(key).toString()+" "+key);
			}
		});
		
		gameLoop = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				scene.upPressed();
				scene.leftPressed();
				scene.downPressed();
				scene.rightPressed();
				scene.blockPressed();
				scene.dodgePressed();
				scene.meleePressed();
				scene.rangePressed();
				scene.nonePressed();
				if(now - lastTime > 1000000000) {
					System.out.println("In Thread "+scene);
					lastTime = now;
				}
			}
		};
		gameLoop.start();
	}

	public void setScene(myScene scene) {
		this.scene = scene;
	}


	public static Map<KeyCode, Boolean> getIsPressedMap() {
		return isPressedMap;
	}


	public static void setIsPressedMap(Map<KeyCode, Boolean> isPressedMap) {
		Controller.isPressedMap = isPressedMap;
	}


	
	public myScene getScene() {
		return scene;
	}



	public AnimationTimer getGameLoop() {
		return gameLoop;
	}

	public static ArrayList<KeyCode> getPressedListP1() {
		return pressedListP1;
	}

	public static ArrayList<KeyCode> getPressedListP2() {
		return pressedListP2;
	}
	
	
	
	
}
