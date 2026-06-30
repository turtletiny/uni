package week07;

import java.util.List;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PersonEditor extends Application {
  private Person p;
  private Label label;
  private TextField nameField;
  private TextField taskField;

  public static void main(String[] args) {
    PersonEditor.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Person Editor");

    p = new Person("Donny", 54);

    label = new Label("Person details: " + p);

    // Text field and button to change the name
    nameField = new TextField();
    nameField.setPromptText("Enter new name");
    Button changeNameBtn = new Button("Change Name");
    changeNameBtn.setOnAction(e -> {
      updateName();
    });

    Button increaseAgeBtn = new Button("Increase age");
    increaseAgeBtn.setOnAction(e -> {
      p.increaseAge();
      updateLabel();
    });

    Button decreaseAgeBtn = new Button("Decrease age");
    decreaseAgeBtn.setOnAction(e -> {
      p.decreaseAge();
      updateLabel();
    });

    int horizontalPadding = 5;
    HBox nameRow = new HBox(horizontalPadding, nameField, changeNameBtn);
    nameRow.setAlignment(Pos.CENTER);

    HBox ageRow = new HBox(horizontalPadding, increaseAgeBtn, decreaseAgeBtn);
    ageRow.setAlignment(Pos.CENTER);

    taskField = new TextField();
    taskField.setPromptText("Enter a new task to-do");

    Button addTodoButton = new Button("Add to-do");
    addTodoButton.setOnAction(e -> {
      updateTodo();
      updateLabel();
    });

    HBox todoRow = new HBox(taskField, addTodoButton);
    todoRow.setAlignment(Pos.CENTER);

    int verticalPadding = 5;
    VBox root = new VBox(verticalPadding, label, nameRow, ageRow, todoRow);
    root.setAlignment(Pos.CENTER);

    Scene scene = new Scene(root, 300, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void updateLabel() {
    label.setText("Person details: " + p);
  }

  private void updateName() {
    String newName = nameField.getText();
    if (!newName.trim().isEmpty()) {
      p.setName(newName);
      updateLabel();
      nameField.clear();
    }
  }

  private void updateTodo() {
    String newTodo = taskField.getText();
    if (!newTodo.trim().isEmpty()) {
      p.addTodo(new Todo(newTodo));
      updateLabel();
      taskField.clear();
    }
  }
}

class Person {
  String name;
  int age;
  List<Todo> todos;

  Person(String name, int age) {
    this.name = name;
    this.age = age;
    this.todos = new ArrayList<>();
  }

  void increaseAge() {
    this.age += 1;
  }

  void decreaseAge() {
    this.age -= 1;
  }

  void setName(String name) {
    this.name = name;
  }

  void addTodo(Todo task) {
    this.todos.add(task);
  }

  @Override
  public String toString() {
    String str = "";
    str += this.name + ", aged: " + this.age;
    str += "\nTasks:";
    for (Todo task : this.todos) {
      str += "\n- " + task;
    }
    return str;
  }
}

class Todo {
  String task;

  Todo(String task) {
    this.task = task;
  }

  public String toString() {
    return this.task;
  }
}
