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
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		root.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null)));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/arena.png").toString(),400,200,false,false)));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/final_valley.jpg").toString(),400,200,false,false)));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/forest.png").toString(),400,200,false,false)));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/konoha_road.jpg").toString(),400,200,false,false)));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/konoha_village.png").toString(),400,200,false,false)));
		listOfBackground.add(new ImageView(new Image(ClassLoader.getSystemResource("background/hospital.png").toString(),400,200,false,false)));
		
		root.getChildren().addAll(listOfBackground);

		setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	

}