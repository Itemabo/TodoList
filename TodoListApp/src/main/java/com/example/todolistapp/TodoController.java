package com.example.todolistapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TodoController {
    @FXML
    private TextField inputField;
    @FXML
    private VBox taskList;
    @FXML
    protected void handleAddTask(){
        String task = inputField.getText();
        if (task.isEmpty()){
            return;
        }

        HBox taskItem = new HBox(10);
        Text taskText = new Text(task);
        taskText.setStyle("-fx-font-size: 18;");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        deleteButton.setOnAction(e -> taskList.getChildren().remove(taskItem));

        taskItem.getChildren().addAll(taskText, editButton, deleteButton);
        taskList.getChildren().add(taskItem);

        inputField.clear();

    }
}
