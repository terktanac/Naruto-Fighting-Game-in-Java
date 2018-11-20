package main;



import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class MultiPlayerScreen extends Scene{
	private int player1 = 0,player2 = 1; //default character =0 : naruto
	private static Pane root = new Pane();
	public MultiPlayerScreen(Main main) {
		super(root);
		
		ArrayList<Image> Characters = new ArrayList<Image>();
		ArrayList<String> CharactersName = new ArrayList<String>();
		ArrayList<ListCharacter> listCharacterpy1 = new ArrayList<ListCharacter>();
		ArrayList<ListCharacter> listCharacterpy2 = new ArrayList<ListCharacter>();
		
		Characters.add(new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs_2.png").toString(),230,500,false,false));
		Characters.add(new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs_2.png").toString(),230,500,false,false));
		
		CharactersName.add("Naruto");
		CharactersName.add("Sasuke");

		listCharacterpy1.add(new ListCharacter("characters/naruto_sage/face.jpg",100,130));
		listCharacterpy1.add(new ListCharacter("characters/sasuke_aka/face.png",205,130));
		
		listCharacterpy2.add(new ListCharacter("characters/naruto_sage/face.jpg",980,130));
		listCharacterpy2.add(new ListCharacter("characters/sasuke_aka/face.png",1085,130));
		
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		
		ImageView lhschar,rhschar,vs,scrollpy1,scrollpy2;
		//vs. logo
		vs = new ImageView(new Image(ClassLoader.getSystemResource("icon/vs.png").toString(),170,170,false,false));
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
		
		scrollpy1 = new ImageView(new Image(ClassLoader.getSystemResource("icon/scroll.png").toString(),350,600,false,false));
		scrollpy1.setTranslateX(20);
		scrollpy1.setTranslateY(50);
		
		scrollpy2 = new ImageView(new Image(ClassLoader.getSystemResource("icon/scroll.png").toString(),350,600,false,false));
		scrollpy2.setTranslateX(900);
		scrollpy2.setTranslateY(50);
		
		
		root.getChildren().addAll(lhschar,rhschar,scrollpy1,scrollpy2,vs);
		
		for(int i = 0; i < listCharacterpy1.size(); i++)
			root.getChildren().add(listCharacterpy1.get(i));
		
		for(int i = 0; i < listCharacterpy2.size(); i++)
			root.getChildren().add(listCharacterpy2.get(i));
		
		listCharacterpy1.get(player1).setActive(true);
		listCharacterpy2.get(player2).setActive(true);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				boolean chosen = false;
				KeyCode key = event.getCode();
				System.out.println("Multiplayer:Pressed " + key.toString());
				
				
				
				if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
					choose.play();
					chosen = true;
				} 
				else if ((key == KeyCode.W || key == KeyCode.A) && !chosen) {
					listCharacterpy1.get(player1).setActive(false);
					click.play();
					player1 = (player1+1)%Characters.size();
					lhschar.setImage(Characters.get(player1));
					listCharacterpy1.get(player1).setActive(true);
				}
				else if ((key == KeyCode.UP || key == KeyCode.LEFT) && !chosen) {
					click.play();
					listCharacterpy2.get(player2).setActive(false);
					player2 = (player2+1+Characters.size())%Characters.size();
					rhschar.setImage(Characters.get(player2));
					listCharacterpy2.get(player2).setActive(true);
				}
				else if ((key == KeyCode.S || key == KeyCode.D) && !chosen) {
					listCharacterpy1.get(player1).setActive(false);
					click.play();
					player1 = (player1-1+Characters.size())%Characters.size();
					lhschar.setImage(Characters.get(player1));
					listCharacterpy1.get(player1).setActive(true);
				}
				else if ((key == KeyCode.DOWN ||key == KeyCode.RIGHT) && !chosen) {
					click.play();
					listCharacterpy2.get(player2).setActive(false);
					player2 = (player2-1+Characters.size())%Characters.size();
					rhschar.setImage(Characters.get(player2));
					listCharacterpy2.get(player2).setActive(true);
				}
			}
		});
		

	}
	public class ListCharacter extends HBox{
		private ImageView img;
		public ListCharacter(String s, int posx, int posy) {
			img = new ImageView(new Image(ClassLoader.getSystemResource(s).toString(),100,100,false,false));
			img.setTranslateX(posx);
			img.setTranslateY(posy);
			this.getChildren().addAll(img);
			setActive(false);
		}
		public void setActive(boolean check) {
			if(check == true)
				img.setStyle("");
				//want to make imageview's border 
			else
				img.setStyle("");
		}
	}
	
}

	