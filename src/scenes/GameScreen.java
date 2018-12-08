package scenes;

import java.util.ArrayList;
import java.util.Map;

import allInterface.Collidable;
import characters.Character;
import characters.Sasuke;
import characters.Naruto;
import gameobject.GameObject;
import gameobject.Shuriken;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.Controller;
import main.Main;

public class GameScreen extends MyScene {
	private static Pane root = new Pane();
//	private static Character[] play1 = {new WindCharacter1(Character.getMaxHealth()),new FireCharacter1(Character.getMaxHealth())};
//	private static Character[] play2 = {new WindCharacter1(Character.getMaxHealth()),new FireCharacter1(Character.getMaxHealth())};
//	private static Character player1 = play1[0]; ไว้สำหรับระบบเปลี่ยนตัวกลางเกม
//	private static Character player2 = play2[0];
	private static AudioClip player = new AudioClip("file:music/game/Nankou_Furaku.mp3");
	private static Character player1;
	private static Character player2;
	private HealthBar healthbarP1;
	private HealthBar healthbarP2;
	private static ArrayList<GameObject> gameObjects1 = new ArrayList<GameObject>();
	private static ArrayList<GameObject> gameObjects2 = new ArrayList<GameObject>();
	private static ArrayList<ImageView> healthIcon = new ArrayList<ImageView>();
	private PauseMenuScreen pause;
	private boolean isEnd;
	private boolean isPause;
	private int currentTime = 300;
	private AnimationTimer timer;
	private long lastTime = -1;
	private Text time;


	public GameScreen() {
		super(root);
		isEnd = false;
		isPause = false;
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		pause = new PauseMenuScreen();
		pause.setVisible(false);

		healthIcon.add(new ImageView(new Image("characters/naruto_sage/naruto_sage_s.png", 348, 140, true,false)));
		healthIcon.add(new ImageView(new Image("characters/sasuke_aka/sasuke_aka_s.png", 348,140,true,false)));

		time = new Text("" + currentTime);
		time.setTranslateX(640);
		time.setTranslateY(100);
		time.setFont(getNarutoFontsmall());
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (now - lastTime > 1000000000) {
					currentTime--;
					lastTime = now;
					time.setText("" + currentTime);
					if (currentTime == 0) {
						isEnd = true;
					}
				}
			}
		};
		timer.start();


		root.getChildren().addAll(time, pause);
		root.getChildren().addAll(gameObjects1);

	}

	public static void setBackground(Image background) {
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
	}

	public final void updateArrays() {
		updateskill(1);
		updateskill(2);
		updatemove(1);
		updatemove(2);
		pause.setChangemenu(false);
	}

	private void updatemove(int player) {
		final ArrayList<KeyCode> pressed = (player == 1 ? Controller.getPressedListMoveP1()
				: Controller.getPressedListMoveP2());
		final ArrayList<KeyCode> key = (player == 1 ? Controller.getKeyP1() : Controller.getKeyP2());
		if (pressed.size() >= 3) {
			if (pressed.get(0) == key.get(0) && pressed.get(1) == key.get(1) && pressed.get(2) == key.get(0)) {
				System.out.println("Change char to earth");
				Controller.removePressed(player, "MOVE", 3);
			}
		}
		if (pressed.size() >= 1) {
			Controller.removePressed(player, "MOVE", 1);
		}

	}

	private void updateskill(int player) {
		final ArrayList<KeyCode> pressed = (player == 1 ? Controller.getPressedListSkillP1()
				: Controller.getPressedListSkillP2());
		final ArrayList<KeyCode> key = (player == 1 ? Controller.getKeyP1() : Controller.getKeyP2());
		if (pressed.size() >= 4) {
			if (pressed.get(0) == key.get(5) && pressed.get(1) == key.get(7) && pressed.get(2) == key.get(6)
					&& pressed.get(3) == key.get(4)) {
				System.out.println("Tier 3 skill");
				if (player == 1) {
					player1.setSkill3(true);
				} else {
					player2.setSkill3(true);
				}
				Controller.removePressed(player, "SKILL", 4);
				return;
			}
		}
		if (pressed.size() >= 3) {
			if (pressed.get(0) == key.get(6) && pressed.get(1) == key.get(4) && pressed.get(2) == key.get(5)) {
				System.out.println("Tier 2 skill");
				if (player == 1) {
					player1.setSkill2(true);
				} else {
					player2.setSkill2(true);
				}
				Controller.removePressed(player, "SKILL", 3);
				return;
			}
		}
		if (pressed.size() >= 2) {
			if (pressed.get(0) == key.get(5) && pressed.get(1) == key.get(4)) {
				System.out.println("Tier 1 skill");
				if (player == 1) {
					player1.setSkill1(true);
				} else {
					player2.setSkill1(true);
				}
				Controller.removePressed(player, "SKILL", 2);
				return;
			}
		}
		if (pressed.size() >= 1) {
			if (pressed.get(0) == key.get(4)) {
				if (!isPause) {
					if (player == 1) {
						meleePressed1();
					} else if (player == 2) {
						meleePressed2();
					}
				} else {
					choosen();
				}
			} else if (pressed.get(0) == key.get(5)) {
				if (!isPause) {
					if (player == 1) {
						rangePressed1();
					} else if (player == 2) {
						rangePressed2();
					}
				} else {
					pause.setVisible(false);
					isPause = false;
				}
			} else if (pressed.get(0) == key.get(7) && !isPause) {
				if (player == 1) {
					dodgePressed1();
				} else if (player == 2) {
					dodgePressed2();
				}
			}
			if (player == 1) {
				Controller.getPressedListSkillP1().clear();
			} else if (player == 2) {
				Controller.getPressedListSkillP2().clear();
			}
		}
	}

	@Override
	public final void update() {
		update1();
		update2();
		otherKeyPressed();
		if (player1.isDead() || player2.isDead()) {
			isEnd = true;
		}
		EndGame();
	}

	private final void update1() {
		upPressed1();
		downPressed1();
		leftPressed1();
		rightPressed1();
		blockPressed1();
		nonePressed1();
		doAnimation1();
		healthbarP1.setHealthBar(player1.getCurrenthealth() / Character.getMaxHealth());
	}

	private final void update2() {
		upPressed2();
		downPressed2();
		leftPressed2();
		rightPressed2();
		blockPressed2();
		nonePressed2();
		doAnimation2();
		healthbarP2.setHealthBar(player2.getCurrenthealth() / Character.getMaxHealth());
	}

	private final void upPressed1() {
		if (!Controller.getPressedListMoveP1().isEmpty()
				&& Controller.getIsPressedMap1().get(Controller.getKeyP1().get(0)) && !isPause) {
			player1.jump();
			System.out.println("UPPressed");
		} else if (!Controller.getPressedListMoveP1().isEmpty()
				&& Controller.getIsPressedMap1().get(Controller.getKeyP1().get(0)) && isPause) {
			moveUp();
		}
		player1.doJump();
	}

	private final void downPressed1() {
		if (Controller.getIsPressedMap1().get(Controller.getKeyP1().get(1)) && !isPause) {
			player1.crouch();
			System.out.println("DOWNPressed");
		} else if (player1.isCrouch() && !isPause) {
			player1.setCrouch(false);
		} else if (Controller.getIsPressedMap1().get(Controller.getKeyP1().get(1)) && isPause) {
			moveDown();
		}
	}

	private final void leftPressed1() {
		if (Controller.getIsPressedMap1().get((Controller.getKeyP1().get(2))) && !isPause) {
			player1.walkLeft();
			System.out.println("LeftPressed");
		} else if (player1.isMove() && !isPause) {
			player1.setMove(false);
		} else if (Controller.getIsPressedMap1().get((Controller.getKeyP1().get(2))) && isPause) {
			moveUp();
		}

	}

	private final void rightPressed1() {
		if (Controller.getIsPressedMap1().get((Controller.getKeyP1().get(3))) && !isPause) {
			player1.walkRight();
			System.out.println("RightPressed");
		} else if (player1.isMove() && !isPause) {
			player1.setMove(false);
		} else if (Controller.getIsPressedMap1().get((Controller.getKeyP1().get(3))) && isPause) {
			moveDown();
		}
	}

	private final void meleePressed1() {
		player1.melee(player2);
	}

	private final void rangePressed1() {
		gameObjects1.add(new Shuriken(player1.getTranslateX(), player1.getTranslateY() + 60, player1.isRight()));
		root.getChildren().add(gameObjects1.get(gameObjects1.size() - 1));
		gameObjects1.get(gameObjects1.size() - 1).getAnimation().play();
		player1.range();

	}

	private final void blockPressed1() {
		if (Controller.getIsPressedMap1().get(Controller.getKeyP1().get(6))) {
			player1.block();
		} else if (player1.isBlock()) {
			player1.setBlock(false);
		}
	}

	private final void dodgePressed1() {
		player1.dodge();

	}

	private final void nonePressed1() {
		final Map<KeyCode, Boolean> pressed = Controller.getIsPressedMap1();
		if (!pressed.containsValue(true)) {
			player1.stand();
		}
	}

	private final void doAnimation1() {
		player1.dead();
		player1.doRange();
		player1.doMelee();
		player1.doDodge();
		player1.basic_skill(player2, gameObjects1);
		player1.mid_skill(player2, gameObjects1);
		player1.High_skill(player2, gameObjects1);
		player1.dotakeDamage();
		if (player1.getTranslateX() > 950) {
			player1.setTranslateX(950);
		} else if (player1.getTranslateX() < -30) {
			player1.setTranslateX(-30);
		}
		if (!gameObjects1.isEmpty()) {
			for (int i = 0; i < gameObjects1.size(); i++) {
				final GameObject shu = gameObjects1.get(i);
				if (shu.getTranslateX() <= 1280 && shu.getTranslateX() >= -400) {
					shu.moveX();
					if (shu.isHasEffect()) {
						if (shu.isDone()) {
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects1.remove(i);
						} else if (checkCollide(shu, player2) && !shu.isDoing()) {
							player2.setStackFly(1);
							player2.takeDamage(shu.getDamage());
							shu.setSpeed(0);
							shu.setDoing(true);
							shu.setTranslateX(player2.getTranslateX());
							shu.setTranslateY(player2.getTranslateY());
						} else if (shu.isDoing()) {
							shu.doEffect();
						}
					} else {
						if (checkCollide(shu, player2)) {
							player2.takeDamage(shu.getDamage());
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects1.remove(i);
						}
					}
				} else {
					root.getChildren().remove(root.getChildren().indexOf(shu));
					gameObjects1.remove(i);
				}
			}
		}
	}

	private final void upPressed2() {
		if (!Controller.getPressedListMoveP2().isEmpty()
				&& Controller.getIsPressedMap2().get(Controller.getKeyP2().get(0)) && !isPause) {
			player2.jump();
			System.out.println("UPPressed");
		} else if (!Controller.getPressedListMoveP2().isEmpty()
				&& Controller.getIsPressedMap2().get(Controller.getKeyP2().get(0)) && isPause) {
			moveUp();
		}
		player2.doJump();
	}

	private final void downPressed2() {

		if (Controller.getIsPressedMap2().get(Controller.getKeyP2().get(1)) && !isPause) {
			player2.crouch();
			System.out.println("DOWNPressed");
		} else if (player2.isCrouch() && !isPause) {
			player2.setCrouch(false);
		} else if (Controller.getIsPressedMap2().get(Controller.getKeyP2().get(1)) && isPause) {
			moveDown();
		}
	}

	private final void leftPressed2() {
		if (Controller.getIsPressedMap2().get((Controller.getKeyP2().get(2))) && !isPause) {
			player2.walkLeft();
			System.out.println("LeftPressed");
		} else if (player2.isMove() && !isPause) {
			player2.setMove(false);
		} else if (Controller.getIsPressedMap2().get((Controller.getKeyP2().get(2))) && isPause) {
			moveUp();
		}
	}

	private final void rightPressed2() {
		if (Controller.getIsPressedMap2().get((Controller.getKeyP2().get(3))) && !isPause) {
			player2.walkRight();
			System.out.println("RightPressed");
		} else if (player2.isMove() && isPause) {
			player2.setMove(false);
		} else if (Controller.getIsPressedMap2().get((Controller.getKeyP2().get(3))) && isPause) {
			moveDown();
		}
	}

	private final void meleePressed2() {
		player2.melee(player1);
	}

	private final void rangePressed2() {
		gameObjects2.add(new Shuriken(player2.getTranslateX(), player2.getTranslateY() + 60, player2.isRight()));
		root.getChildren().add(gameObjects2.get(gameObjects2.size() - 1));
		gameObjects2.get(gameObjects2.size() - 1).getAnimation().play();
		player2.range();

	}

	private final void blockPressed2() {
		if (Controller.getIsPressedMap2().get(Controller.getKeyP2().get(6))) {
			player2.block();
		} else if (player2.isBlock()) {
			player2.setBlock(false);
		}
	}

	private final void dodgePressed2() {
		player2.dodge();

	}

	private final void nonePressed2() {
		final Map<KeyCode, Boolean> pressed = Controller.getIsPressedMap2();
		if (!pressed.containsValue(true)) {
			player2.stand();
		}
	}

	private final void doAnimation2() {
		player2.dead();
		player2.doRange();
		player2.doMelee();
		player2.doDodge();
		player2.basic_skill(player1, gameObjects2);
		player2.mid_skill(player1, gameObjects2);
		player2.High_skill(player1, gameObjects2);
		player2.dotakeDamage();
		if (player2.getTranslateX() > 950) {
			player2.setTranslateX(950);
		} else if (player2.getTranslateX() < -30) {
			player2.setTranslateX(-30);
		}
		if (!gameObjects2.isEmpty()) {
			for (int i = 0; i < gameObjects2.size(); i++) {
				final GameObject shu = gameObjects2.get(i);
				if (shu.getTranslateX() <= 1280 && shu.getTranslateX() >= -400) {
					shu.moveX();
					if (shu.isHasEffect()) {
						if (shu.isDone()) {
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects2.remove(i);
						} else if (checkCollide(shu, player1) && !shu.isDoing()) {
							player1.takeDamage(shu.getDamage());
							shu.setSpeed(0);
							shu.setDoing(true);
							shu.setTranslateX(player1.getTranslateX());
							shu.setTranslateY(player1.getTranslateY());
						} else if (shu.isDoing()) {
							shu.doEffect();
						}
					} else {
						if (checkCollide(shu, player1)) {
							player1.setStackFly(1);
							player1.takeDamage(shu.getDamage());
							shu.setSpeed(0);
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects2.remove(i);
						}
					}
				} else {
					root.getChildren().remove(root.getChildren().indexOf(shu));
					gameObjects2.remove(i);
				}
			}
		}
	}

	private final void otherKeyPressed() {
		final ArrayList<KeyCode> others = Controller.getOtherKeys();
		if (others.size() > 0) {
			final KeyCode key = others.get(0);
			if ((key == KeyCode.ESCAPE || key == KeyCode.BACK_SPACE) && !isEnd) {
				if (!isPause) {
					isPause = true;
					pause.setVisible(true);
				} else {
					isPause = false;
					pause.setVisible(false);
					MultiPlayerScreen.getPlayer().stop();
					Main.ChangeScene(Main.getMainmenu());
				}
			} else if (isPause && (key == KeyCode.ENTER || key == KeyCode.SPACE)) {
				choosen();
			} else if (isEnd && (key == KeyCode.ENTER || key == KeyCode.SPACE)) {
				System.exit(1);
//				Main.ChangeScene(Main.getIntro());
//				Main.setDefault();
			}
			if (!Controller.getOtherKeys().isEmpty()) {
				Controller.removePressed(0, "OTHER", 1);
			}
		}
	}

	public static final boolean checkCollide(Collidable obj1, Collidable obj2) {
		return obj1.getBoundary().intersects(obj2.getBoundary());
	}

	private final void moveDown() {
		if (!pause.isChangemenu()) {
			if (pause.getCurChoice() == pause.getMenu().getChildren().size() - 1) {
				pause.setNewChoice(0);
			} else {
				pause.setNewChoice(pause.getCurChoice() + 1);
			}
			((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getCurChoice())).setActive(false);
			((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getNewChoice())).setActive(true);
			pause.setCurChoice(pause.getNewChoice());
			pause.setChangemenu(true);
		}
	}

	private final void moveUp() {
		if (!pause.isChangemenu()) {
			if (pause.getCurChoice() == 0) {
				pause.setNewChoice(pause.getMenu().getChildren().size() - 1);
			} else {
				pause.setNewChoice(pause.getCurChoice() - 1);
			}
			((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getCurChoice())).setActive(false);
			((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getNewChoice())).setActive(true);
			pause.setCurChoice(pause.getNewChoice());
			pause.setChangemenu(true);
		}
	}

	private final void choosen() {
		if (pause.getCurChoice() == 0) {
			pause.setVisible(false);
			isPause = false;
		} else if (pause.getCurChoice() == 1) {
			// to option
			isPause = false;
			pause.setVisible(false);
			player.stop();
			Main.ChangeScene(Main.getOptionscreen());
		} else if (pause.getCurChoice() == 2) {
			// to howto
			isPause = false;
			pause.setVisible(false);
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Coming Soon.");
			alert.show();
			Main.getPlayer().setScene(Main.getGamescreen());
		} else if (pause.getCurChoice() == 3) {
			// to mainmenu
			isPause = false;
			pause.setVisible(false);
			Main.ChangeScene(Main.getMainmenu());
			player.stop();
		} else if (pause.getCurChoice() == 4) {
			System.exit(1);
		}
	}

	public final void EndGame() {
		if (isEnd) {
			final Text endtext = new Text("KO!");
			endtext.setFont(getNarutoFont());
			endtext.setStroke(Color.WHITE);
			endtext.setTranslateX(600);
			endtext.setTranslateY(300);

			final Text next = new Text("Press Enter to quit");
			next.setFont(getNarutoFontsmall());
			next.setTranslateX(600);
			next.setTranslateY(400);
			next.setStroke(Color.WHITE);
			if (player1.getCurrenthealth() == Character.getMaxHealth()
					|| player2.getCurrenthealth() == Character.getMaxHealth()) {
				endtext.setText("Perfect!");
				endtext.setTranslateX(600);
				endtext.setTranslateY(300);
			} else if (player1.getCurrenthealth() == player2.getCurrenthealth()) {
				endtext.setText("Tie!");
				endtext.setTranslateX(650);
				endtext.setTranslateY(300);
			} else if (player1.getCurrenthealth() < player2.getCurrenthealth()) {
				endtext.setText("Player 2 Win!");
				endtext.setTranslateX(500);
				endtext.setTranslateY(500);
			} else if (player1.getCurrenthealth() > player2.getCurrenthealth()) {
				endtext.setText("Player 1 Win!");
				endtext.setTranslateX(500);
				endtext.setTranslateY(300);
			}
			final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> next.setVisible(true)),
					new KeyFrame(Duration.seconds(0.7), evt -> next.setVisible(false)));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();

			root.getChildren().addAll(endtext, next);
		}
	}

	public final void setCharacter(int choose1, int choose2) {
		if (root.getChildren().contains(player1)) {
			root.getChildren().remove(player1);
		}
		if (root.getChildren().contains(player2)) {
			root.getChildren().remove(player2);
		}
		if (root.getChildren().contains(healthbarP1)) {
			root.getChildren().remove(healthbarP1);
		}
		if (root.getChildren().contains(healthbarP2)) {
			root.getChildren().remove(healthbarP2);
		}
		if (choose1 == 0) {
			player1 = new Naruto(Character.getMaxHealth());
		} else {
			player1 = new Sasuke(Character.getMaxHealth());
		}
		if (choose2 == 0) {
			player2 = new Naruto(Character.getMaxHealth());
		} else {
			player2 = new Sasuke(Character.getMaxHealth());
		}
		healthbarP1 = new HealthBar(800, 312.5,healthIcon.get(choose1), -25, -50);
		healthbarP2 = new HealthBar(800, 312.5,healthIcon.get(choose2), 5, -50);
		healthbarP2.setTranslateX(535);
		healthbarP2.setRotationAxis(Rotate.Y_AXIS);
		healthbarP2.setRotate(180);
		
		root.getChildren().add(0, player1);
		root.getChildren().add(0, player2);
		root.getChildren().add(0, healthbarP1);
		root.getChildren().add(0, healthbarP2);
		player1.setTranslateX(300);
		player1.setTranslateY(300);
		player2.setTranslateX(600);
		player2.setTranslateY(300);

		player2.getImageview().setRotationAxis(Rotate.Y_AXIS);
		player2.getImageview().setRotate(180);
		player2.setRight(false);

		player1.getAnimation().play();
		player2.getAnimation().play();

		player1.stand();
		player2.stand();
	}

	@Override
	public final void setDefault() {
		player1.setCurrenthealth(Character.getMaxHealth());
		player2.setCurrenthealth(Character.getMaxHealth());
		healthbarP1.setHealthBar(1);
		healthbarP2.setHealthBar(1);
		gameObjects1.clear();
		gameObjects2.clear();
		isEnd = false;
		isPause = false;
		pause.setCurChoice(0);
		pause.setNewChoice(0);
		currentTime = 300;
	}

	public final void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public static Character getPlayer1() {
		return player1;
	}

	public static Character getPlayer2() {
		return player2;
	}

	public static Pane getPaneRoot() {
		return root;
	}

	public static ArrayList<GameObject> getgameObjects1() {
		return gameObjects1;
	}

	public static ArrayList<GameObject> getgameObjects2() {
		return gameObjects2;
	}

	public final void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	protected static AudioClip getPlayer() {
		return player;
	}

	public class HealthBar extends StackPane {
		private double width;
		private double height;
		private ImageView healthbarPlain;
		private ImageView healthbarBorder;
		private double widthD;
		private double xD;
		private double heightD;
		private double yD;
		private double[] xPoints;
		private double[] yPoints;
		private double[] xPointstemp;
		private Canvas healthbar;
		private GraphicsContext gc;
		private int red;
		private int green;

		public HealthBar(double width, double height, ImageView characters, int xpos, int ypos) {
			red = 0;
			green = 255;
			this.width = width;
			this.height = height;
			widthD = width - 215;
			xD = xpos + 58;
			heightD = height - 265;
			yD = ypos + 140;
			healthbarPlain = new ImageView(new Image("icon/healthbar.png", width, height, true, true));
			healthbarBorder = new ImageView(new Image("icon/healthbarborder.png", width, height, true, true));
			xPoints = new double[] { xD, xD + widthD, xD + (widthD * 0.99), xD + (widthD * 0.579),
					xD + (widthD * 0.562), xD };
			yPoints = new double[] { yD, yD, yD + (heightD * 0.42), yD + (heightD * 0.42), yD + heightD, yD + heightD };
			xPointstemp = new double[] { xD, xD + widthD, xD + (widthD * 0.99), xD + (widthD * 0.579),
					xD + (widthD * 0.562), xD };
			setPrefSize(width, height);
			setTranslateX(xpos);
			setTranslateY(ypos);
			characters.setTranslateX(xpos-215);
			characters.setTranslateY(ypos+20);
			healthbarPlain.setTranslateX(xpos);
			healthbarPlain.setTranslateY(ypos);
			healthbarBorder.setTranslateX(xpos);
			healthbarBorder.setTranslateY(ypos);
			healthbar = new Canvas(width, height);
			gc = healthbar.getGraphicsContext2D();
			setHealthBar(1);

			getChildren().addAll(healthbarPlain, healthbar, healthbarBorder, characters);
		}

		public final double setHealthBar(double curDIVmax) {
			if (curDIVmax < 0) {
				curDIVmax = 0;
			}
			if (curDIVmax >= 0.5) {
				red = (int) (2 * 255 * (1 - curDIVmax));
			} else {
				green = (int) (255 * (curDIVmax * 2));
			}
			Stop[] stops = new Stop[] { new Stop(0, Color.rgb(red, green, 0)),
					new Stop(1, Color.rgb(red, green, 100)) };
			gc.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));

			if (curDIVmax < 0.579) {
				xPoints[3] = xPointstemp[1] * curDIVmax;
				xPoints[4] = xPointstemp[2] * curDIVmax;
			}
			xPoints[1] = xPointstemp[1] * curDIVmax;
			xPoints[2] = xPointstemp[2] * curDIVmax;
			gc.clearRect(0, 0, width, height);
			gc.fillPolygon(xPoints, yPoints, 6);
			return curDIVmax * 100;
		}

	}

}