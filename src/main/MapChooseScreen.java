package main;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MapChooseScreen extends Scene {

	private static TilePane root = new TilePane(20,20);
	private static ArrayList<listMap> listOfBackground= new ArrayList<listMap>();
	private int row,column;
	private int choice;
	private Image background = new Image(ClassLoader.getSystemResource("background/shinobi2.jpg").toString(),1300,740,false,false);
	
	public MapChooseScreen(Main main) {
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
		
		choice = 0;
		setOnKeyPressed(new EventHandler<KeyEvent>() {		
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				KeyCode key = event.getCode();
				System.out.println("Multiplayer:Pressed " + key.toString());
				
				if (key == KeyCode.BACK_SPACE) {
					choose.play();
					main.ChangeScene(main.getMultiplayer());
				}
				else if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
					main.ChangeScene(main.getLoadscreen());
					Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae ->{main.ChangeScene(main.getGamescreen());})
							,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
					load.play();
					choose.play();
				} 
				else if ((key == main.getOptionscreen().getUp_1() || key == main.getOptionscreen().getUp_2())) {
					click.play();
					listOfBackground.get(choice).setActive(false);
					column = (column - 1 + 2)%2;
					choice = (3*column + row)%listOfBackground.size();
					listOfBackground.get(choice).setActive(true);
				}
				else if ((key == main.getOptionscreen().getDown_1() || key == main.getOptionscreen().getDown_2())) {
					click.play();
					listOfBackground.get(choice).setActive(false);
					column = (column + 1)%2;
					choice = (3*column + row)%listOfBackground.size();
					listOfBackground.get(choice).setActive(true);
				}
				else if ((key == main.getOptionscreen().getLeft_1() || key == main.getOptionscreen().getLeft_2())) {
					click.play();
					listOfBackground.get(choice).setActive(false);
					row = (row - 1 + 3)%3;
					choice = (3*column + row)%listOfBackground.size();
					listOfBackground.get(choice).setActive(true);
				}
				else if ((key == main.getOptionscreen().getRight_1() ||key == main.getOptionscreen().getRight_2())) {
					click.play();
					listOfBackground.get(choice).setActive(false);
					row = (row + 1)%3;
					choice = (3*column + row)%listOfBackground.size();
					listOfBackground.get(choice).setActive(true);
				}
				System.out.println(choice);
			}
			
		});
		
	}
	public static class listMap extends ImageView{
		private String normal,active;
		private Image imgn,imga;
		public listMap(String n,String a) {
			this.normal = n;
			this.active= a;
			this.imgn = new Image(ClassLoader.getSystemResource(normal).toString(),400,200,false,true);
			this.imga = new Image(ClassLoader.getSystemResource(active).toString(),400,200,false,true);
			this.setImage(imgn);
		}
		public void setActive(boolean check) {
			if(check == true) {
				this.setImage(imga);
			}
			else {
				this.setImage(imgn);
			}
		}
		public String getSrcBackground() {
			return normal;
		}
	}
	public Background getBackground() {
		Image bg = new Image(ClassLoader.getSystemResource(listOfBackground.get(choice).getSrcBackground()).toString(),1300,740,false,false);
		return new Background(new BackgroundImage(bg, null, null, null, null));
		
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
}