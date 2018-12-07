package Scenes;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.Controller;
import main.Main;

public class MultiPlayerScreen extends myScene {
	private static int player1; // default character =0 : naruto
	private static int player2;
	private static Pane root = new Pane();
	protected static AudioClip player = new AudioClip("file:music/menu/Gekiha.mp3");
	private ImageView lhschar;
	private ImageView rhschar;
	private ImageView vs;
	private ImageView scrollpy1;
	private ImageView scrollpy2;
	ArrayList<ListCharacter> listCharacterpy1 = new ArrayList<ListCharacter>();
	ArrayList<ListCharacter> listCharacterpy2 = new ArrayList<ListCharacter>();
	ArrayList<Image> Characters = new ArrayList<Image>();
	ArrayList<Image> CharactersReady = new ArrayList<Image>();
	ArrayList<String> CharactersName = new ArrayList<String>();
	private Check chosen1 = new Check(false);
	private Check chosen2 = new Check(false);
	private Timeline timeline;

	public MultiPlayerScreen() {
		super(root);
		player1 = 0;
		player2 = 1;
		player.setVolume(0.3);

		Characters.add(
				new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs_2.png").toString(),
						230, 500, false, true));
		Characters
				.add(new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs_2.png").toString(),
						230, 500, false, true));

		CharactersReady.add(
				new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs_3.png").toString(),
						310, 480, false, true));
		CharactersReady
				.add(new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs_3.png").toString(),
						230, 500, false, true));

		CharactersName.add("Naruto");
		CharactersName.add("Sasuke");

		listCharacterpy1.add(new ListCharacter("characters/naruto_sage/face.jpg",
				"characters/naruto_sage/face_active.jpg", 100, 130));
		listCharacterpy1.add(
				new ListCharacter("characters/sasuke_aka/face.jpg", "characters/sasuke_aka/face_active.jpg", 205, 130));

		listCharacterpy2.add(new ListCharacter("characters/naruto_sage/face.jpg",
				"characters/naruto_sage/face_active.jpg", 980, 130));
		listCharacterpy2.add(new ListCharacter("characters/sasuke_aka/face.jpg",
				"characters/sasuke_aka/face_active.jpg", 1085, 130));

		final Text pressKey = new Text("Press any key to Continue");
		pressKey.setFont(getNarutoFont());
		pressKey.setFill(Color.WHITE);
		pressKey.setStroke(Color.BLACK);
		pressKey.setTranslateX(340);
		pressKey.setTranslateY(600);
		pressKey.setVisible(false);

		root.setPrefSize(1280, 720);
		final Image background = new Image(ClassLoader.getSystemResource("background/shinobi2.jpg").toString(), 1300, 740,
				false, false);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));

		// vs. logo
		vs = new ImageView(new Image(ClassLoader.getSystemResource("icon/vs.png").toString(), 170, 170, false, false));
		vs.setTranslateX(560);
		vs.setTranslateY(280);

		lhschar = new ImageView(Characters.get(player1));
		lhschar.setTranslateX(360);
		lhschar.setTranslateY(110);

		rhschar = new ImageView(Characters.get(player2));
		rhschar.setTranslateX(690);
		rhschar.setTranslateY(110);
		rhschar.setRotationAxis(Rotate.Y_AXIS);
		rhschar.setRotate(180);

		scrollpy1 = new ImageView(
				new Image(ClassLoader.getSystemResource("icon/scroll.png").toString(), 350, 600, false, true));
		scrollpy1.setTranslateX(20);
		scrollpy1.setTranslateY(50);

		scrollpy2 = new ImageView(
				new Image(ClassLoader.getSystemResource("icon/scroll.png").toString(), 350, 600, false, true));
		scrollpy2.setTranslateX(900);
		scrollpy2.setTranslateY(50);

		root.getChildren().addAll(lhschar, rhschar, scrollpy1, scrollpy2, vs, pressKey);

		for (int i = 0; i < listCharacterpy1.size(); i++) {
			root.getChildren().add(listCharacterpy1.get(i));
		}

		for (int i = 0; i < listCharacterpy2.size(); i++) {
			root.getChildren().add(listCharacterpy2.get(i));
		}

		listCharacterpy1.get(player1).setActive(true);
		listCharacterpy2.get(player2).setActive(true);

		timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey.setVisible(false)));
		timeline.setCycleCount(Animation.INDEFINITE);

	}
	@Override
	public void update() {
		update_P1();
		update_P2();
		otherKeysPressed();

	}

	private void update_P1() {
		upPressed_1();
		downPressed_1();
		leftPressed_1();
		rightPressed_1();
		choose_1();
		back_1();
	}

	private void update_P2() {
		upPressed_2();
		downPressed_2();
		leftPressed_2();
		rightPressed_2();
		choose_2();
		back_2();
	}

	private void upPressed_1() {
		if (Controller.getKeyMove_P1(0) && !chosen1.check) {
			listCharacterpy1.get(player1).setActive(false);
			playClick();
			player1 = (player1 + 1) % Characters.size();
			lhschar.setImage(Characters.get(player1));
			listCharacterpy1.get(player1).setActive(true);
		}
	}

	private void downPressed_1() {
		if (Controller.getKeyMove_P1(1) && !chosen1.check) {
			listCharacterpy1.get(player1).setActive(false);
			playClick();
			player1 = (player1 - 1 + Characters.size()) % Characters.size();
			lhschar.setImage(Characters.get(player1));
			listCharacterpy1.get(player1).setActive(true);
		}
	}

	private void leftPressed_1() {
		if (Controller.getKeyMove_P1(2) && !chosen1.check) {
			listCharacterpy1.get(player1).setActive(false);
			playClick();
			player1 = (player1 + 1) % Characters.size();
			lhschar.setImage(Characters.get(player1));
			listCharacterpy1.get(player1).setActive(true);
		}
	}

	private void rightPressed_1() {
		if (Controller.getKeyMove_P1(3) && !chosen1.check) {
			listCharacterpy1.get(player1).setActive(false);
			playClick();
			player1 = (player1 - 1 + Characters.size()) % Characters.size();
			lhschar.setImage(Characters.get(player1));
			listCharacterpy1.get(player1).setActive(true);
		}
	}

	private void choose_1() {
		if (Controller.getKeySkill_P1(0)) {
			haveChose();
			lhschar.setImage(CharactersReady.get(player1));
			playChoose();
			chosen1.check = true;
			System.out.println("player1: " + player1);
		}
	}

	private void back_1() {
		if (Controller.getKeySkill_P1(1)) {
			if (chosen1.check) {
				lhschar.setImage(Characters.get(player1));
				chosen1.check = false;
			} else {
				goBacktoMainmenu();
			}
		}
	}

	private void upPressed_2() {
		if (Controller.getKeyMove_P2(0) && !chosen2.check) {
			playClick();
			listCharacterpy2.get(player2).setActive(false);
			player2 = (player2 + 1 + Characters.size()) % Characters.size();
			rhschar.setImage(Characters.get(player2));
			listCharacterpy2.get(player2).setActive(true);
		}
	}

	private void downPressed_2() {
		if (Controller.getKeyMove_P2(1) && !chosen2.check) {
			playClick();
			listCharacterpy2.get(player2).setActive(false);
			player2 = (player2 - 1 + Characters.size()) % Characters.size();
			rhschar.setImage(Characters.get(player2));
			listCharacterpy2.get(player2).setActive(true);
		}
	}

	private void leftPressed_2() {
		if (Controller.getKeyMove_P2(2) && !chosen2.check) {
			playClick();
			listCharacterpy2.get(player2).setActive(false);
			player2 = (player2 + 1 + Characters.size()) % Characters.size();
			rhschar.setImage(Characters.get(player2));
			listCharacterpy2.get(player2).setActive(true);
		}
	}

	private void rightPressed_2() {
		if (Controller.getKeyMove_P2(3) && !chosen2.check) {
			playClick();
			listCharacterpy2.get(player2).setActive(false);
			player2 = (player2 - 1 + Characters.size()) % Characters.size();
			rhschar.setImage(Characters.get(player2));
			listCharacterpy2.get(player2).setActive(true);
		}
	}

	private void choose_2() {
		if (Controller.getKeySkill_P2(0)) {
			haveChose();
			rhschar.setImage(CharactersReady.get(player2));
			playChoose();
			chosen2.check = true;
			System.out.println("Player2: " + player2);

		}
	}

	private void back_2() {
		if (Controller.getKeySkill_P2(1)) {
			if (chosen2.check) {
				chosen2.check = false;
				rhschar.setImage(Characters.get(player2));
			} else {
				goBacktoMainmenu();
			}
		}
	}

	private void haveChose() {
		if (chosen1.check == true && chosen2.check == true) {
			playChoose();
			Main.ChangeScene(Main.getMapscreen());
			Main.getGamescreen().setCharacter(player1, player2);
			Main.getPlayer().setScene(Main.getMapscreen());
			Main.getPlayer().run();
			timeline.play();
		}
	}

	private void goBacktoMainmenu() {
		player.stop();
		playChoose();
		chosen1.check = false;
		chosen2.check = false;
		rhschar.setImage(Characters.get(player2));
		lhschar.setImage(Characters.get(player1));
		Main.ChangeScene(Main.getMainmenu());
	}

	private void otherKeysPressed() {
		if (Controller.getOtherKeys().contains(KeyCode.ENTER) || Controller.getOtherKeys().contains(KeyCode.SPACE)) {
			if (!chosen1.getCheck()) {
				haveChose();
				lhschar.setImage(CharactersReady.get(player1));
				playChoose();
				chosen1.check = true;
			} else if (!chosen2.getCheck()) {
				haveChose();
				rhschar.setImage(CharactersReady.get(player2));
				playChoose();
				chosen2.check = true;
			} else {
				haveChose();
			}
		} else if (Controller.getOtherKeys().contains(KeyCode.BACK_SPACE)
				|| Controller.getOtherKeys().contains(KeyCode.ESCAPE)) {
			if (chosen1.getCheck()) {
				lhschar.setImage(Characters.get(player1));
				chosen1.check = false;
			} else if (chosen2.getCheck()) {
				chosen2.check = false;
				rhschar.setImage(Characters.get(player2));
			} else {
				goBacktoMainmenu();
			}
		}
	}

	@Override
	public void setDefault() {
		player1 = 0;
		player2 = 1;
		chosen1.setCheck(false);
		chosen2.setCheck(false);
		player.stop();
	}

	public static int getPlayer1() {
		return player1;
	}

	public static int getPlayer2() {
		return player2;
	}


	public class ListCharacter extends HBox {
		private ImageView img;
		private String normal;
		private String active;
//		private int px ;
//		private int py ;

		public ListCharacter(String n, String a, int posx, int posy) {
			this.normal = n;
			this.active = a;
//			this.px = posx;
//			this.py = posy;
			img = new ImageView(new Image(ClassLoader.getSystemResource(n).toString(), 100, 100, false, true));
			img.setTranslateX(posx);
			img.setTranslateY(posy);
			this.getChildren().addAll(img);
			setActive(false);
		}

		public void setActive(boolean check) {
			if (check) {
				img.setImage(new Image(ClassLoader.getSystemResource(this.active).toString(), 100, 100, false, true));
			} else {
				img.setImage(new Image(ClassLoader.getSystemResource(this.normal).toString(), 100, 100, false, true));
			}
		}
	}

	public class Check {
		private boolean check;

		public Check(boolean c) {
			check = c;
		}

		public boolean getCheck() {
			return check;
		}

		public void setCheck(boolean check) {
			this.check = check;
		}
	}

	
}
