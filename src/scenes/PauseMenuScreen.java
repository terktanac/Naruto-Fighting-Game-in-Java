package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PauseMenuScreen extends Pane {
	private VBox menu;
	private int CurChoice;
	private int NewChoice;

	public PauseMenuScreen() {
		super();
		setPadding(new Insets(10));
		setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
		setPrefSize(1280, 720);
		setOpacity(0.5);

		menu = new VBox();
		menu.setSpacing(10);
		menu.setAlignment(Pos.CENTER);
		menu.getChildren().addAll(new ListMenu("Continue"), new ListMenu("Option"), new ListMenu("How to play"),
				new ListMenu("Exit to Mainmenu"), new ListMenu("Quit game"));
		menu.setTranslateX(500);
		menu.setTranslateY(100);

		((ListMenu) menu.getChildren().get(CurChoice)).setActive(true);
		getChildren().addAll(menu);
	}

	public final VBox getMenu() {
		return menu;
	}

	public final int getCurChoice() {
		return CurChoice;
	}

	public final void setCurChoice(int curChoice) {
		CurChoice = curChoice;
	}

	public final int getNewChoice() {
		return NewChoice;
	}

	public final void setNewChoice(int newChoice) {
		NewChoice = newChoice;
	}

	public class ListMenu extends HBox {
		private Text text;

		ListMenu(String text) {
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			this.setPrefSize(180, 60);
			this.text = new Text(text);
			this.text.setFont(MyScene.getNarutoFont());
			this.text.setStrokeWidth(2);
			getChildren().addAll(this.text);
			setActive(false);
		}

		public final void setActive(boolean check) {
			if (check) {
				text.setStroke(Color.ALICEBLUE);
				text.setFill(Color.BLACK);
			} else {
				text.setStroke(Color.DARKGRAY);
				text.setFill(Color.DARKGRAY);
			}
		}
	}

}
