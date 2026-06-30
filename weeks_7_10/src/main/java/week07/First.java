package week07;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// The top-level class of a JavaFX app must extend the `Application` class.
public class First extends Application {
  public static void main(String[] args) {
    First.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // We can set the title of the stage (window) to be whatever we want.
    primaryStage.setTitle("Hello! Bye!");

    // We have 2 visible nodes in this app; a Label is used for displaying
    // text, and a Button is for...clicking. They will be arranged vertically
    // in the scene as children nodes of the VBox element, below.

    Label label = new Label("Click the button!");

    Button helloButton = new Button();
    helloButton.setText("Say 'Hello!'");

    // The VBox is a node that arranges its children nodes in a vertical
    // column from top to bottom. We call the variable `root` because it
    // will be the root node of the scene graph (see below).
    VBox root = new VBox();

    // Here we set `label` and `helloButton` as children nodes of the VBox.
    // Note how `label` comes before `helloButton`.
    root.getChildren().addAll(label, helloButton);

    // This method call aligns the VBox to sit right in the centre of the
    // scene.
    root.setAlignment(Pos.CENTER);

    // Scene is defined here with the VBox as the root node, a width of
    // 300 pixels and height of 250 pixels.
    Scene scene = new Scene(root, 300, 250);

    // Set the scene within the primary stage
    primaryStage.setScene(scene);

    // Display the stage (window)
    primaryStage.show();
  }
}
