package scenes;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import main.Controller;
import main.Main;

public class MapChooseScreen extends MyScene {

	private static TilePane root = new TilePane(20, 20);
	private ArrayList<ListMap> listOfBackground = new ArrayList<ListMap>();
	private int row, column, choice;
	private Image background = new Image(ClassLoader.getSystemResource("background/shinobi2.jpg").toString(), 1300, 740,
			false, false);
	private static Image chooseBackground = new Image(
			ClassLoader.getSystemResource("background/shinobi2.jpg").toString(), 1300, 740, false, false);

	public MapChooseScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));

		listOfBackground.add(new ListMap("background/arena.png", "background/arena_active.jpg"));
		listOfBackground.add(new ListMap("background/final_valley1.jpg", "background/final_valley_active.jpg"));
		listOfBackground.add(new ListMap("background/forest.png", "background/forest_active.jpg"));
		listOfBackground.add(new ListMap("background/hospital.png", "background/hospital_active.jpg"));
		listOfBackground.add(new ListMap("background/konoha_road.jpg", "background/konoha_road_active.jpg"));
		listOfBackground.add(new ListMap("background/konoha_village.png", "background/konoha_village_active.jpg"));

		listOfBackground.get(choice).setActive(true);

		root.getChildren().addAll(listOfBackground);

	}

	public static Image getChooseBackground() {
		return chooseBackground;
	}

	public static void setChooseBackground(Image chooseBackground) {
		MapChooseScreen.chooseBackground = chooseBackground;
	}
	@Override
	public final void update() {
		upPressed();
		downPressed();
		leftPressed();
		rightPressed();
		choose();
		back();
	}

	public final void upPressed() {
		if (Controller.getKeyMoveP1(0) || Controller.getKeyMoveP2(0)) {
			listOfBackground.get(choice).setActive(false);
			column = (column - 1 + 2) % 2;
			choice = (3 * column + row) % listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}

	public final void downPressed() {
		if (Controller.getKeyMoveP1(1) || Controller.getKeyMoveP2(1)) {
			listOfBackground.get(choice).setActive(false);
			column = (column + 1) % 2;
			choice = (3 * column + row) % listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}

	public final void leftPressed() {
		if (Controller.getKeyMoveP1(2) || Controller.getKeyMoveP2(2)) {
			listOfBackground.get(choice).setActive(false);
			row = (row - 1 + 3) % 3;
			choice = (3 * column + row) % listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}

	public final void rightPressed() {
		if (Controller.getKeyMoveP1(3) || Controller.getKeyMoveP2(3)) {
			listOfBackground.get(choice).setActive(false);
			row = (row + 1) % 3;
			choice = (3 * column + row) % listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}

	public final void choose() {
		if (Controller.getKeySkillP1(0) || Controller.getKeySkillP2(0)
				|| Controller.getOtherKeys().contains(KeyCode.ENTER)
				|| Controller.getOtherKeys().contains(KeyCode.SPACE)) {
			final Timeline load = new Timeline(new KeyFrame(Duration.millis(3500), ae -> {
				Main.ChangeScene(Main.getGamescreen());
			}), new KeyFrame(Duration.millis(100), ae -> {
				playChoose();
			}));
			Main.ChangeScene(Main.getLoadscreen());
			playChoose();
			load.play();
			setChooseBackground(
					new Image(ClassLoader.getSystemResource(listOfBackground.get(choice).getNormal()).toString(), 1300,
							740, false, false));
			GameScreen.setBackground(chooseBackground);
			MultiPlayerScreen.player.stop();
			GameScreen.getPlayer().setCycleCount(AudioClip.INDEFINITE);
			GameScreen.getPlayer().play();
		}
	}

	public final void back() {
		if (Controller.getKeySkillP1(1) || Controller.getKeySkillP2(1)
				|| Controller.getOtherKeys().contains(KeyCode.BACK_SPACE)
				|| Controller.getOtherKeys().contains(KeyCode.ESCAPE)) {
			playChoose();
			Main.ChangeScene(Main.getMultiplayer());
		}
	}

	@Override
	public final void setDefault() {
		row = 0;
		column = 0;
		choice = 0;
	}
	public class ListMap extends ImageView {
		private String normal;
		private String active;
		private Image imgn;
		private Image imga;

		public ListMap(String n, String a) {
			this.normal = n;
			this.active = a;
			this.imgn = new Image(ClassLoader.getSystemResource(normal).toString(), 400, 200, false, true);
			this.imga = new Image(ClassLoader.getSystemResource(active).toString(), 400, 200, false, true);
			this.setImage(imgn);
		}

		public final String getNormal() {
			return normal;
		}

		public final void setActive(boolean check) {
			if (check) {
				this.setImage(imga);
			} else {
				this.setImage(imgn);
			}
		}
	}

	
}
