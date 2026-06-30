package week07;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VariousExamples extends Application {

  Label sizeLabel;
  boolean isStrikethrough, isBold;
  Text coloredText;
  int rowIndex;

  // Define an enum for Size
  enum Size {
    SMALL, MEDIUM, LARGE
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Size Selection Example");

    this.sizeLabel = new Label("Select a button");
    this.isStrikethrough = false;
    this.isBold = false;
    this.rowIndex = 0;

    // --------------------------------------------------------------------------------
    // The upper VBox demonstrates the use of so-called radio buttons
    VBox topVbox = new VBox(3);
    topVbox.setAlignment(Pos.CENTER);

    // Create a toggle group to ensure that only one radio button can be selected at
    // a time
    ToggleGroup toggleGroup = new ToggleGroup();

    // Create radio buttons for each size and add them to the toggle group
    RadioButton smallRadioButton = createRadioButton("Small", Size.SMALL, toggleGroup);
    RadioButton mediumRadioButton = createRadioButton("Medium", Size.MEDIUM, toggleGroup);
    RadioButton largeRadioButton = createRadioButton("Large", Size.LARGE, toggleGroup);

    HBox sizeRow = new HBox(4, smallRadioButton, mediumRadioButton, largeRadioButton);
    sizeRow.setAlignment(Pos.CENTER);

    // Add radio buttons to the VBox
    topVbox.getChildren().addAll(this.sizeLabel, sizeRow);

    // --------------------------------------------------------------------------------
    // The middle VBox shows how you can control text in a finer grained
    // way. Note that we need to use `Text` objects to be able to style
    // text.

    CheckBox strikethroughCBox = new CheckBox("Strikethrough");
    strikethroughCBox.setOnAction(event -> {
      if (strikethroughCBox.isSelected()) {
        this.isStrikethrough = true;
      } else {
        this.isStrikethrough = false;
      }
      updateColouredText();
    });

    CheckBox boldCBox = new CheckBox("Bold");
    boldCBox.setOnAction(event -> {
      if (boldCBox.isSelected()) {
        this.isBold = true;
      } else {
        this.isBold = false;
      }
      updateColouredText();
    });

    // Create a Text object for the first part of the text (in black)
    Text textPart1 = new Text("This is ");
    textPart1.setFont(Font.font(16)); // Set the font size

    // Create a Text object for the colored word (in red)
    this.coloredText = new Text("colored");
    this.coloredText.setFont(Font.font(16)); // Set the font size
    this.coloredText.setFill(Color.DODGERBLUE); // Set the text color

    // Create a Text object for the second part of the text (in black)
    Text textPart2 = new Text(" text.");
    textPart2.setFont(Font.font(16)); // Set the font size

    HBox textRow = new HBox(textPart1, coloredText, textPart2);
    textRow.setAlignment(Pos.CENTER);

    VBox middleVBox = new VBox(5, strikethroughCBox, boldCBox, textRow);
    middleVBox.setAlignment(Pos.CENTER);

    // ------------------------------------------------------------------------------------------
    // This lower VBox demonstrates how we can dynamically add and remove
    // children elements to/from the window.
    VBox lowerVBox = new VBox(5);
    lowerVBox.setAlignment(Pos.CENTER);
    Button addRowBtn = new Button("Add new row");
    addRowBtn.setOnAction(event -> {
      lowerVBox.getChildren().add(new Label("Row " + this.rowIndex));
      rowIndex += 1;
    });

    Button removeRowBtn = new Button("Remove last row");
    removeRowBtn.setOnAction(event -> {
      int numRows = lowerVBox.getChildren().size();
      if (numRows > 2) {
        lowerVBox.getChildren().remove(numRows - 1);
        rowIndex -= 1;
      }
    });

    lowerVBox.getChildren().addAll(addRowBtn, removeRowBtn);

    // ------------------------------------------------------------------------------------------
    // Now we stitch together the vboxes
    VBox outerVBox = new VBox(20);
    outerVBox.getChildren().addAll(topVbox, middleVBox, lowerVBox);
    outerVBox.setAlignment(Pos.TOP_CENTER);

    // Create a scene and set it on the stage
    Scene scene = new Scene(outerVBox, 300, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private RadioButton createRadioButton(String text, Size size, ToggleGroup toggleGroup) {
    RadioButton radioButton = new RadioButton(text);
    radioButton.setToggleGroup(toggleGroup);

    // Attach an event handler to handle the selection
    radioButton.setOnAction(event -> {
      Font font;
      if (size == Size.SMALL) {
        font = new Font(12);
      } else if (size == Size.MEDIUM) {
        font = new Font(16);
      } else {
        font = new Font(20);

      }
      if (radioButton.isSelected()) {
        this.sizeLabel.setText("Selected Size: " + size);
        this.sizeLabel.setFont(font);
      }
    });

    return radioButton;
  }

  private void updateColouredText() {
    if (this.isStrikethrough) {
      this.coloredText.setStrikethrough(true);
    } else {
      this.coloredText.setStrikethrough(false);
    }

    if (this.isBold) {
      Font boldFont = Font.font("Arial", FontWeight.BOLD, 16.0);
      this.coloredText.setFont(boldFont);
    } else {
      Font regularFont = Font.font(16.0);
      this.coloredText.setFont(regularFont);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
