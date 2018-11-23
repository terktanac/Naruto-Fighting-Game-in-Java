package main;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class MapChooseScreen extends Scene{

	private static TilePane root = new TilePane(20,20);
	
	ArrayList<ImageView> listOfBackground= new ArrayList<ImageView>();
	public MapChooseScreen(Main main) {
		super(root);
		root.setPrefSize(1280, 720);
		root.setTileAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null)));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/arena.png").toString())));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/final_valley.jpg").toString())));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/forest.png").toString())));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/konoha_road.jpg").toString())));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/konoha_village.png").toString())));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/hospital.png").toString())));
		
		root.getChildren().addAll(listOfBackground);
		
		for(int i = 0; i < 6; i++) {
			listOfBackground.get(i).setFitWidth(400);
			listOfBackground.get(i).setFitHeight(200);
			root.setMargin(listOfBackground.get(i),new Insets(10));
		}
		setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	

}
