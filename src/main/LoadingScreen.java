package main;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;

public class LoadingScreen extends myScene{
	private static  Pane root = new Pane();
	private Image background = new Image(ClassLoader.getSystemResource("background/four_seal.gif").toString(),1280,740,false,false);
	public LoadingScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		
	}
	@Override
	public void upPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void downPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void leftPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rightPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void meleePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rangePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dodgePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void blockPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void SpacePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void EnterPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nonePressed() {
		// TODO Auto-generated method stub
		
	}
	
}
