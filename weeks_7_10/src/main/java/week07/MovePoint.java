package week07;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovePoint extends Application {
  public static void main(String[] args) {
    MovePoint.launch(args);
  }

  // Coordinates of our point
  private int x, y;
  private Label point;

  public void start(Stage primaryStage) {
    this.x = 0;
    this.y = 0;
    this.point = new Label();
    this.point.setText(displayPoint());
    primaryStage.setTitle("Move Point");

    Button increaseX = new Button("Increase x");
    increaseX.setOnAction(e -> {
      this.x += 1;
      updateLabel();
    });
    Button increaseY = new Button("Increase y");
    increaseY.setOnAction(e -> {
      this.y += 1;
      updateLabel();
    });
    Button decreaseX = new Button("Decrease x");
    decreaseX.setOnAction(e -> {
      this.x -= 1;
      updateLabel();
    });
    Button decreaseY = new Button("Decrease y");
    decreaseY.setOnAction(e -> {
      this.y -= 1;
      updateLabel();
    });

    HBox row1 = new HBox(increaseX, increaseY);
    row1.setAlignment(Pos.CENTER);
    HBox row2 = new HBox(decreaseX, decreaseY);
    row2.setAlignment(Pos.CENTER);
    VBox root = new VBox(point, row1, row2);
    root.setAlignment(Pos.CENTER);

    Scene scene = new Scene(root, 300, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void updateLabel() {
    this.point.setText(displayPoint());
  }

  public String displayPoint() {
    return "(" + this.x + "," + this.y + ")";
  }

}
