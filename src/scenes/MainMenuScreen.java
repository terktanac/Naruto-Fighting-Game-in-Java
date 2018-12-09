package scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Controller;
import main.Main;

public class MainMenuScreen extends MyScene {
	private static Pane root = new Pane();
	private Image background = new Image(ClassLoader.getSystemResource("background/final_valley_bg.jpg").toString(),
			1280, 740, false, false);
	private ImageView imageView = new ImageView(
			new Image(ClassLoader.getSystemResource("icon/logo_new.png").toString(), 400, 250, true, true));
	private VBox menuBox = new VBox(5);
	private int oldchoice;
	private int newChoice;

	public MainMenuScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));

		menuBox.setTranslateX(350);
		menuBox.setTranslateY(250);
		menuBox.getChildren().addAll(new ListMenu("Singleplayer"), new ListMenu("Multiplayer"), new ListMenu("Option"),
				new ListMenu("Exit"));
		((ListMenu) menuBox.getChildren().get(oldchoice)).setActive(true);
		menuBox.setAlignment(Pos.CENTER);
		menuBox.setSpacing(30);

		imageView.setTranslateX(460);
		imageView.setTranslateY(60);
		imageView.prefWidth(1000);

		root.getChildren().addAll(menuBox, imageView);

	}

	@Override
	public final void update() {
		moveUp();
		moveDown();
		select();
		back();
	}

	private void back() {
		if (Controller.getKeySkillP1(1) || Controller.getKeySkillP2(1)
				|| Controller.getOtherKeys().contains(KeyCode.ESCAPE)
				|| Controller.getOtherKeys().contains(KeyCode.BACK_SPACE)) {
			Main.ChangeScene(Main.getLoadscreen());
			final Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae -> {
				Main.ChangeScene(Main.getIntro());
			}), new KeyFrame(Duration.millis(100), ae -> {
				playChoose();
			}));
			load.play();
			Controller.getOtherKeys().clear();
		}
	}

	private void select() {
		if (Controller.getKeySkillP1(0) || Controller.getKeySkillP2(0)
				|| Controller.getOtherKeys().contains(KeyCode.ENTER)
				|| Controller.getOtherKeys().contains(KeyCode.SPACE)) {
			chooseMenu();
		}
	}

	private void moveDown() {
		if (Controller.getKeyMoveP1(3) || Controller.getKeyMoveP2(3) || Controller.getKeyMoveP1(1)
				|| Controller.getKeyMoveP2(1)) {
			if (oldchoice == menuBox.getChildren().size() - 1) {
				newChoice = 0;
			} else {
				newChoice = oldchoice + 1;
			}
			playClick();
			((ListMenu) menuBox.getChildren().get(oldchoice)).setActive(false);
			((ListMenu) menuBox.getChildren().get(newChoice)).setActive(true);
			oldchoice = newChoice;
		}
	}

	private void moveUp() {
		if (Controller.getKeyMoveP1(0) || Controller.getKeyMoveP2(0) || Controller.getKeyMoveP1(2)
				|| Controller.getKeyMoveP2(2)) {
			if (oldchoice == 0) {
				newChoice = 3;
			} else {
				newChoice = oldchoice - 1;
			}
			playClick();
			((ListMenu) menuBox.getChildren().get(oldchoice)).setActive(false);
			((ListMenu) menuBox.getChildren().get(newChoice)).setActive(true);
			oldchoice = newChoice;

		}
	}

	private void chooseMenu() {
		if (oldchoice == 0) {
			System.out.println("Coming soon.");
		} else if (oldchoice == 1) {
			Main.ChangeScene(Main.getLoadscreen());
			final Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae -> {
				Main.ChangeScene(Main.getMultiplayer());
			}), new KeyFrame(Duration.millis(100), ae -> {
				playChoose();
			}));
			load.play();
			MultiPlayerScreen.getPlayer().setCycleCount(AudioClip.INDEFINITE);
			MultiPlayerScreen.getPlayer().play();
		} else if (oldchoice == 2) {
			Main.ChangeScene(Main.getLoadscreen());
			final Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae -> {
				Main.ChangeScene(Main.getOptionscreen());
			}), new KeyFrame(Duration.millis(100), ae -> {
				playChoose();
			}));
			load.play();
		} else if (oldchoice == menuBox.getChildren().size() - 1) {
			System.exit(1);
		}
	}

	@Override
	public final void setDefault() {
		((ListMenu) menuBox.getChildren().get(oldchoice)).setActive(false);
		oldchoice = 0;
		newChoice = 0;
		((ListMenu) menuBox.getChildren().get(oldchoice)).setActive(true);
	}

	public class ListMenu extends HBox {
		private Text name;
		private ImageView kunai = new ImageView(
				new Image(ClassLoader.getSystemResource("icon/kunai.png").toString(), 130, 40, true, true));
//		private Runnable script;

		ListMenu(String text) {
//			this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			name = new Text(text);
			name.setFont(getNarutoFont());
			name.setStrokeWidth(2);
			getChildren().addAll(kunai, name);
			setActive(false);
		}

		public final void setActive(boolean check) {
			kunai.setVisible(check);
			name.setStroke(check ? Color.WHITE : Color.GRAY);
		}

	}

}