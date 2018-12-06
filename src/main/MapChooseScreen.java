package main;

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
import javafx.util.Duration;

public class MapChooseScreen extends myScene {

	private static TilePane root = new TilePane(20,20);
	private ArrayList<listMap> listOfBackground= new ArrayList<listMap>();
	private int row,column,choice = 0;
	private Image background = new Image(ClassLoader.getSystemResource("background/shinobi2.jpg").toString(),1300,740,false,false);
	private static Image chooseBackground  = new Image(ClassLoader.getSystemResource("background/shinobi2.jpg").toString(),1300,740,false,false);
	public MapChooseScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		
		listOfBackground.add(new listMap("background/arena.png","background/arena_active.jpg"));
		listOfBackground.add(new listMap("background/final_valley1.jpg","background/final_valley_active.jpg"));
		listOfBackground.add(new listMap("background/forest.png","background/forest_active.jpg"));
		listOfBackground.add(new listMap("background/hospital.png","background/hospital_active.jpg"));
		listOfBackground.add(new listMap("background/konoha_road.jpg","background/konoha_road_active.jpg"));
		listOfBackground.add(new listMap("background/konoha_village.png","background/konoha_village_active.jpg"));
		
		listOfBackground.get(choice).setActive(true);
		
		root.getChildren().addAll(listOfBackground);


		
	}
	public static Image getChooseBackground() {
		return chooseBackground;
	}
	public static void setChooseBackground(Image chooseBackground) {
		MapChooseScreen.chooseBackground = chooseBackground;
	}
	public class listMap extends ImageView{
		private String normal,active;
		private Image imgn,imga;
		public listMap(String n,String a) {
			this.normal = n;
			this.active= a;
			this.imgn = new Image(ClassLoader.getSystemResource(normal).toString(),400,200,false,true);
			this.imga = new Image(ClassLoader.getSystemResource(active).toString(),400,200,false,true);
			this.setImage(imgn);
		}
		public String getNormal() {
			return normal;
		}
		public void setActive(boolean check) {
			if(check == true) {
				this.setImage(imga);
			}
			else {
				this.setImage(imgn);
			}
		}
	}
	@Override
	public void update() {
		upPressed();
		downPressed();
		leftPressed();
		rightPressed();
		choose();
		back();
	}
	public void upPressed() {
		if(Controller.getKeyMove_P1(0) || Controller.getKeyMove_P2(0)) {
			listOfBackground.get(choice).setActive(false);
			column = (column - 1 + 2)%2;
			choice = (3*column + row)%listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}
	public void downPressed() {
		if(Controller.getKeyMove_P1(1) || Controller.getKeyMove_P2(1)) {
			listOfBackground.get(choice).setActive(false);
			column = (column + 1)%2;
			choice = (3*column + row)%listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}
	public void leftPressed() {
		if(Controller.getKeyMove_P1(2) || Controller.getKeyMove_P2(2)) {
			listOfBackground.get(choice).setActive(false);
			row = (row - 1 + 3)%3;
			choice = (3*column + row)%listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}
	public void rightPressed() {
		if(Controller.getKeyMove_P1(3) || Controller.getKeyMove_P2(3)) {
			listOfBackground.get(choice).setActive(false);
			row = (row + 1)%3;
			choice = (3*column + row)%listOfBackground.size();
			listOfBackground.get(choice).setActive(true);
			playClick();
		}
	}
	public void choose() {
		if(Controller.getKeySkill_P1(0) || Controller.getKeySkill_P2(0) || Controller.getOtherKeys().contains(KeyCode.ENTER) || Controller.getOtherKeys().contains(KeyCode.SPACE)) {
			Timeline load = new Timeline(new KeyFrame(Duration.millis(3500), ae ->{Main.ChangeScene(Main.getGamescreen());})
					,new KeyFrame(Duration.millis(100), ae->{playChoose();}));
			Main.ChangeScene(Main.getLoadscreen());
			Main.getPlayer().setScene(Main.getGamescreen());
			Main.getPlayer().run();
			playChoose();
			load.play();
			setChooseBackground(new Image(ClassLoader.getSystemResource(listOfBackground.get(choice).getNormal()).toString(),1300,740,false,false));
			GameScreen.setBackground(chooseBackground);
		}
	}
	public void back() {
		if(Controller.getKeySkill_P1(1) || Controller.getKeySkill_P2(1) || Controller.getOtherKeys().contains(KeyCode.BACK_SPACE) || Controller.getOtherKeys().contains(KeyCode.ESCAPE)) {
			playChoose();
			Main.ChangeScene(Main.getMultiplayer());
			Main.getPlayer().setScene(Main.getMultiplayer());
			Main.getPlayer().run();
		}
	}
	@Override
	public void setDefault() {
		row = 0;
		column = 0;
		choice = 0;
	}
	}
