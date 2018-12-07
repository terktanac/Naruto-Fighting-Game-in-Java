package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import scenes.MyScene;

public class Controller implements Runnable {

	private MyScene scene;
	private static Map<KeyCode, Boolean> isPressedMap1 = new HashMap<KeyCode, Boolean>();
	private static Map<KeyCode, Boolean> isPressedMap2 = new HashMap<KeyCode, Boolean>();
	private static ArrayList<KeyCode> pressedListMoveP1 = new ArrayList<KeyCode>();
	private static ArrayList<KeyCode> pressedListSkillP1 = new ArrayList<KeyCode>();
	private static ArrayList<KeyCode> pressedListMoveP2 = new ArrayList<KeyCode>();
	private static ArrayList<KeyCode> pressedListSkillP2 = new ArrayList<KeyCode>();
	private static ArrayList<KeyCode> otherKeys = new ArrayList<KeyCode>();
	private static ArrayList<KeyCode> KeyP1 = new ArrayList<KeyCode>();
	private static ArrayList<KeyCode> KeyP2 = new ArrayList<KeyCode>();
	private AnimationTimer gameLoop;
	private long lastTime = -1;
	private long inGameLastTime = -1;

	public Controller(KeyCode upKey_1, KeyCode downKey_1, KeyCode leftKey_1, KeyCode rightKey_1, KeyCode meleeKey_1,
			KeyCode rangeKey_1, KeyCode blockKey_1, KeyCode dodgeKey_1, KeyCode upKey_2, KeyCode downKey_2,
			KeyCode leftKey_2, KeyCode rightKey_2, KeyCode meleeKey_2, KeyCode rangeKey_2, KeyCode blockKey_2,
			KeyCode dodgeKey_2) {
		super();
		Controller.KeyP1.add(upKey_1);
		isPressedMap1.put(upKey_1, false);
		Controller.KeyP1.add(downKey_1);
		isPressedMap1.put(downKey_1, false);
		Controller.KeyP1.add(leftKey_1);
		isPressedMap1.put(leftKey_1, false);
		Controller.KeyP1.add(rightKey_1);
		isPressedMap1.put(rightKey_1, false);
		Controller.KeyP1.add(meleeKey_1);
		isPressedMap1.put(meleeKey_1, false);
		Controller.KeyP1.add(rangeKey_1);
		isPressedMap1.put(rangeKey_1, false);
		Controller.KeyP1.add(blockKey_1);
		isPressedMap1.put(blockKey_1, false);
		Controller.KeyP1.add(dodgeKey_1);
		isPressedMap1.put(dodgeKey_1, false);

		Controller.KeyP2.add(upKey_2);
		isPressedMap2.put(upKey_2, false);
		Controller.KeyP2.add(downKey_2);
		isPressedMap2.put(downKey_2, false);
		Controller.KeyP2.add(leftKey_2);
		isPressedMap2.put(leftKey_2, false);
		Controller.KeyP2.add(rightKey_2);
		isPressedMap2.put(rightKey_2, false);
		Controller.KeyP2.add(meleeKey_2);
		isPressedMap2.put(meleeKey_2, false);
		Controller.KeyP2.add(rangeKey_2);
		isPressedMap2.put(rangeKey_2, false);
		Controller.KeyP2.add(blockKey_2);
		isPressedMap2.put(blockKey_2, false);
		Controller.KeyP2.add(dodgeKey_2);
		isPressedMap2.put(dodgeKey_2, false);

	}

	@Override
	public final void run() {
		System.out.println("start in Scene: " + scene);
		scene.setOnKeyPressed((KeyEvent event) -> {
			final KeyCode key = event.getCode();
			System.out.println("Pressed:" + key);
			if (isPressedMap1.containsKey(key) && !isPressedMap1.get(key)) {
				isPressedMap1.put(key, true);
				if (KeyP1.subList(0, 4).contains(key)) {
					pressedListMoveP1.add(key);
				} else {
					pressedListSkillP1.add(key);
				}
				System.out.println(isPressedMap1.get(key).toString() + " " + key);
			} else if (isPressedMap2.containsKey(key) && !isPressedMap2.get(key)) {
				isPressedMap2.put(key, true);
				if (KeyP2.subList(0, 4).contains(key)) {
					pressedListMoveP2.add(key);
				} else {
					pressedListSkillP2.add(key);
				}
				System.out.println(isPressedMap2.get(key).toString() + " " + key);
			} else if (!isPressedMap1.containsKey(key) && !isPressedMap2.containsKey(key) && !otherKeys.contains(key)) {
				otherKeys.add(key);
			}
		});

		scene.setOnKeyReleased((KeyEvent event) -> {
			final KeyCode key = event.getCode();
			System.out.println("Release:" + key);
			if (isPressedMap1.containsKey(key)) {
				isPressedMap1.put(key, false);
				System.out.println(isPressedMap1.get(key).toString() + " " + key);
			} else if (isPressedMap2.containsKey(key)) {
				isPressedMap2.put(key, false);
				System.out.println(isPressedMap2.get(key).toString() + " " + key);
			}
		});

		gameLoop = new AnimationTimer() {

			@Override
			public void handle(long now) {
//				if(now - gameTime > 1) {scene.update();gameTime = now;}
				scene.update();
				if (scene == Main.getGamescreen() && now - inGameLastTime > 350000000) {
					Main.getGamescreen().updateArrays();
					inGameLastTime = now;
				}
				if (now - lastTime > 2000000000) {
					System.out.println("In Thread " + scene);
					lastTime = now;
					System.out.println("=====================");
					System.out.println(pressedListMoveP1);
					System.out.println(pressedListMoveP2);
					System.out.println(pressedListSkillP1);
					System.out.println(pressedListSkillP2);
					System.out.println(otherKeys);
				}
			}
		};
		gameLoop.start();
	}

	public final void setScene(MyScene scene) {
		this.scene = scene;
		if (!otherKeys.isEmpty()) {
			otherKeys.clear();
		}
		if (!pressedListMoveP1.isEmpty()) {
			pressedListMoveP1.clear();
		}
		if (!pressedListMoveP2.isEmpty()) {
			pressedListMoveP2.clear();
		}
		if (!pressedListSkillP1.isEmpty()) {
			pressedListSkillP1.clear();
		}
		if (!pressedListSkillP2.isEmpty()) {
			pressedListSkillP2.clear();
		}
		for (Entry<KeyCode, Boolean> entry : isPressedMap1.entrySet()) {
			isPressedMap1.put(entry.getKey(), false);
		}
		for (Entry<KeyCode, Boolean> entry : isPressedMap2.entrySet()) {
			isPressedMap2.put(entry.getKey(), false);
		}
	}

	public static Map<KeyCode, Boolean> getIsPressedMap1() {
		return isPressedMap1;
	}

	public static Map<KeyCode, Boolean> getIsPressedMap2() {
		return isPressedMap2;
	}

	public final MyScene getScene() {
		return scene;
	}

	public final AnimationTimer getGameLoop() {
		return gameLoop;
	}

	public static ArrayList<KeyCode> getKeyP1() {
		return KeyP1;
	}

	public static ArrayList<KeyCode> getKeyP2() {
		return KeyP2;
	}

	public static ArrayList<KeyCode> getPressedListMoveP1() {
		return pressedListMoveP1;
	}

	public static ArrayList<KeyCode> getPressedListSkillP1() {
		return pressedListSkillP1;
	}

	public static ArrayList<KeyCode> getPressedListMoveP2() {
		return pressedListMoveP2;
	}

	public static ArrayList<KeyCode> getPressedListSkillP2() {
		return pressedListSkillP2;
	}

	public static ArrayList<KeyCode> getOtherKeys() {
		return otherKeys;
	}

	public static boolean getKeyMoveP1(int index) {
		final boolean result = getPressedListMoveP1().contains(Controller.getKeyP1().get(index));
		if (result) {
			getPressedListMoveP1().remove(0);
		}
		return result;
	}

	public static boolean getKeyMoveP2(int index) {
		final boolean result = getPressedListMoveP2().contains(Controller.getKeyP2().get(index));
		if (result) {
			getPressedListMoveP2().remove(0);
		}
		return result;
	}

	public static boolean getKeySkillP1(int index) {
		final boolean result = getPressedListSkillP1().contains(Controller.getKeyP1().get(index + 4));
		if (result) {
			getPressedListSkillP1().remove(0);
		}
		return result;
	}

	public static boolean getKeySkillP2(int index) {
		final boolean result = getPressedListSkillP2().contains(Controller.getKeyP2().get(index + 4));
		if (result) {
			getPressedListSkillP2().remove(0);
		}
		return result;
	}

	public static void removePressed(int player, String type, int amount) {
		for (int i = 0; i < amount; i++) {
			if (player == 1) {
				if ("MOVE".equals(type)) {
					pressedListMoveP1.remove(0);
				} else if ("SKILL".equals(type)) {
					pressedListSkillP1.remove(0);
				}
			} else if (player == 2) {
				if ("MOVE".equals(type)) {
					pressedListMoveP2.remove(0);
				} else if ("SKILL".equals(type)) {
					pressedListSkillP2.remove(0);
				}
			} else if ("OTHER".equals(type)) {
				otherKeys.remove(0);
			}
		}
	}
}