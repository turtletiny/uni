package week07;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Second extends Application{
  public static void main(String[] args) {
	Second.launch(args);
  }

  public void start(Stage primaryStage){
    primaryStage.setTitle("Multiple buttons");
    Label label = new Label("Here's a range of buttons");



    VBox root = new VBox(label);
    root.setAlignment(Pos.CENTER);
    Scene scene = new Scene(root, 300, 250);
    primaryStage.setScene(scene);




    primaryStage.show();
  }
}
