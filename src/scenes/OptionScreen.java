package scenes;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Controller;
import main.Main;

public class OptionScreen extends MyScene {

	private static Pane root = new Pane();
	private int oldChoice1;
	private int newChoice1;
	private int oldChoice2;
	private int newChoice2;
	private ArrayList<ListOption> listoption1 = new ArrayList<ListOption>();
	private ArrayList<ListOption> listoption2 = new ArrayList<ListOption>();
	private boolean setState1;
	private boolean setState2;
	private Timeline timeline1;
	private Timeline timeline2;
//	private Timeline timeline3;
	private Text pressKey1;
	private Text pressKey2;
//	private Text pressKey3 ;
	private VBox optionmenu1;
	private VBox optionmenu2;
	private ArrayList<String> textList = new ArrayList<String>(
			Arrays.asList("Jump", "Crouch", "Left", "Right", "Melee", "Range", "Defense", "Dodge"));

	public OptionScreen() throws Exception {
		super(root);
		root.setPrefSize(1280, 720);
		Image image = new Image(ClassLoader.getSystemResource("background/paper.jpg").toString(), 1300, 720, false,
				true);
		root.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));

		addListOption(1);

		optionmenu1 = new VBox(10);
		optionmenu1.setTranslateX(250);
		optionmenu1.setTranslateY(150);
		optionmenu1.setSpacing(10);
		optionmenu1.getChildren().addAll(listoption1);
		((ListOption) optionmenu1.getChildren().get(oldChoice1)).setActive(true);

		addListOption(2);

		optionmenu2 = new VBox(10);
		optionmenu2.setTranslateX(800);
		optionmenu2.setTranslateY(150);
		optionmenu2.setSpacing(10);

		optionmenu2.getChildren().addAll(listoption2);
		((ListOption) optionmenu2.getChildren().get(oldChoice2)).setActive(true);

		root.getChildren().addAll(optionmenu1, optionmenu2);

		pressKey1 = new Text("Press " + Controller.getKeyP1().get(4).toString() + " key for player 1 or "
				+ Controller.getKeyP2().get(4).toString() + " key for player 2 to change the key.");
		pressKey1.setFont(getNarutoFontsmall());
		pressKey1.setFill(Color.WHITE);
		pressKey1.setStroke(Color.BLACK);
		pressKey1.setTranslateX(230);
		pressKey1.setTranslateY(100);
		pressKey1.setVisible(false);

		pressKey2 = new Text("Press the new key.");
		pressKey2.setFont(getNarutoFontsmall());
		pressKey2.setFill(Color.WHITE);
		pressKey2.setStroke(Color.BLACK);
		pressKey2.setTranslateX(530);
		pressKey2.setTranslateY(100);
		pressKey2.setVisible(false);

		root.getChildren().addAll(pressKey1, pressKey2);

		timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey1.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey1.setVisible(false)));
		timeline1.setCycleCount(Animation.INDEFINITE);

		timeline2 = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey2.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey2.setVisible(false)));
		timeline2.setCycleCount(Animation.INDEFINITE);

		timeline1.play();
	}

	public void keyHandling() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				ArrayList<KeyCode> keyP1 = Controller.getKeyP1();
				ArrayList<KeyCode> keyP2 = Controller.getKeyP2();
				System.out.println("OptionMenu:Pressed " + key.toString());
				if (setState1 && (key == keyP1.get(oldChoice1) || !keyP1.contains(key)) && !keyP2.contains(key)) {
					System.out.println(oldChoice1 + key.toString());
					changeKeyCode(1, oldChoice1, key);
					setState1 = false;
					timeline2.stop();
					pressKey2.setVisible(false);
					timeline1.play();
				} else if (setState2 && !keyP1.contains(key)
						&& (key == keyP2.get(oldChoice2) || !keyP2.contains(key))) {
					changeKeyCode(2, oldChoice2, key);
					setState2 = false;
					timeline2.stop();
					pressKey2.setVisible(false);
					timeline1.play();
				} else {
					if (key == KeyCode.BACK_SPACE || key == keyP1.get(5) || key == keyP2.get(5)) {
						playChoose();
						Main.ChangeScene(Main.getMainmenu());
						Main.getPlayer().setScene(Main.getMainmenu());
						Main.getPlayer().run();
					} else if (key == keyP1.get(4)) {
						setState1 = true;
						playChoose();
						timeline1.stop();
						pressKey1.setVisible(false);
						timeline2.play();
					} else if (key == keyP1.get(0) || key == keyP1.get(2)) {
						newChoice1 = (oldChoice1 - 1 + 8) % 8;
						playClick();
					} else if (key == keyP1.get(1) || key == keyP1.get(3)) {
						newChoice1 = (oldChoice1 + 1 + 8) % 8;
						playClick();
					} else if (key == keyP2.get(4)) {
						setState2 = true;
						playChoose();
						timeline1.stop();
						pressKey1.setVisible(false);
						timeline2.play();
					} else if (key == keyP2.get(0) || key == keyP2.get(2)) {
						newChoice2 = (oldChoice2 - 1 + 8) % 8;
						playClick();
					} else if (key == keyP2.get(1) || key == keyP2.get(3)) {
						newChoice2 = (oldChoice2 + 1 + 8) % 8;
						playClick();
					}

					((ListOption) optionmenu1.getChildren().get(oldChoice1)).setActive(false);
					((ListOption) optionmenu1.getChildren().get(newChoice1)).setActive(true);
					oldChoice1 = newChoice1;

					((ListOption) optionmenu2.getChildren().get(oldChoice2)).setActive(false);
					((ListOption) optionmenu2.getChildren().get(newChoice2)).setActive(true);
					oldChoice2 = newChoice2;
				}

			}
		});
	}

	public final void addListOption(int player) {
		for (int i = 0; i < 8; i++) {
			if (player == 1) {
				listoption1.add(new ListOption(textList.get(i) + ": " + Controller.getKeyP1().get(i), player));
			} else {
				listoption2.add(new ListOption(textList.get(i) + ": " + Controller.getKeyP2().get(i), player));
			}
		}
	}

	@Override
	public final void update() {
		keyHandling();

	}

	public final void changeKeyCode(int player, int choice, KeyCode key) {
		if (player == 1) {
			Controller.getIsPressedMap1().remove(Controller.getKeyP1().get(choice));
			Controller.getKeyP1().set(choice, key);
			Controller.getIsPressedMap1().put(key, false);
			listoption1.get(choice).text.setText(textList.get(choice) + ": " + Controller.getKeyP1().get(choice));
		} else if (player == 2) {
			Controller.getIsPressedMap2().remove(Controller.getKeyP2().get(choice));
			Controller.getKeyP2().set(choice, key);
			Controller.getIsPressedMap2().put(key, false);
			listoption2.get(choice).text.setText(textList.get(choice) + ": " + Controller.getKeyP2().get(choice));
		}
	}

	@Override
	public final void setDefault() {
		oldChoice1 = 0;
		oldChoice2 = 0;
		newChoice1 = 0;
		newChoice2 = 0;
		setState1 = false;
		setState2 = false;
	}

	public class ListOption extends HBox {
		private Text text;
		private int player;
		private ImageView shuriken1 = new ImageView(
				new Image(ClassLoader.getSystemResource("icon/shuriken.png").toString(), 130, 40, true, true));
		private ImageView shuriken2 = new ImageView(
				new Image(ClassLoader.getSystemResource("icon/shuriken.png").toString(), 130, 40, true, true));

		public ListOption(String text, int player) {
			// this.setBackground(new Background(new BackgroundImage(new
			// Image(ClassLoader.getSystemResource("icon/frame.png").toString(),230,60,false,true),
			// BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null)));
			this.player = player;
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			this.setPrefSize(180, 60);
			this.setStyle("-fx-background-image: url(" + ClassLoader.getSystemResource("icon/frame.png").toString()
					+ "); " + "-fx-background-size: cover;");
			this.text = new Text(text);
			this.text.setFont(getNarutoFontsmall());
			this.text.setStrokeWidth(2);
			getChildren().addAll(shuriken1, this.text, shuriken2);
			setActive(false);
		}

		public final void setActive(boolean check) {
			shuriken1.setVisible(check);
			shuriken2.setVisible(check);
			if (player == 1) {
				text.setStroke(check ? Color.ORANGE : Color.YELLOW);
				text.setFill(check ? Color.WHITE : Color.BLACK);
			} else if (player == 2) {
				text.setStroke(check ? Color.RED : Color.YELLOW);
				text.setFill(check ? Color.WHITE : Color.BLACK);
			}
		}
	}

}