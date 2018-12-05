package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PauseMenuScreen extends Pane{
	private VBox menu ;
	private int CurChoice = 0 ;
	private int NewChoice = 0 ;
	public PauseMenuScreen() {
		super();
		setPadding(new Insets(10));
		setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
		setPrefSize(1280, 720);
		
		menu = new VBox();
		menu.setSpacing(10);
		menu.setAlignment(Pos.CENTER);
		menu.getChildren().addAll(new ListMenu("Continue")
								,new ListMenu("Option")
								,new ListMenu("How to play")
								,new ListMenu("Exit to Mainmenu")
								,new ListMenu("Quit game"));
		menu.setTranslateX(460);
		menu.setTranslateY(60);
		
		((ListMenu) menu.getChildren().get(CurChoice)).setActive(true);
		getChildren().addAll(menu);
	}
	
	public class ListMenu extends HBox {
		private Text text;
		ListMenu(String text) {
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			this.setPrefSize(180, 60);
			this.text = new Text(text);
			this.text.setFont(myScene.getNarutoFont());
			this.text.setStrokeWidth(2);
			getChildren().addAll(this.text);
			setActive(false);
		}

		public void setActive(boolean check) {
			if(check) {
				text.setStroke(Color.ALICEBLUE);
				text.setFill(Color.BLACK);
			}
			else {
				text.setStroke(Color.DARKGRAY);
				text.setFill(Color.DARKGRAY);
			}
		}
	}

	public VBox getMenu() {
		return menu;
	}

	public int getCurChoice() {
		return CurChoice;
	}

	public void setCurChoice(int curChoice) {
		CurChoice = curChoice;
	}

	public int getNewChoice() {
		return NewChoice;
	}

	public void setNewChoice(int newChoice) {
		NewChoice = newChoice;
	}
	
}
