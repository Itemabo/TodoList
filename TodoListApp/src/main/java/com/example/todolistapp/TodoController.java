package com.example.todolistapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TodoController {

    public TextField newTodoList;
    public ListView<String> todoListView;
    public String selectedItem;

    String url = "jdbc:mysql://localhost:3306/todolist";
    String username = "root";
    String password = "";


    //   To display the ListView with data from the database at the load of the page
    @FXML
    public void initialize() {
        loadTasks();
    }

//    On the of the add button
    public void onAddButtonClick(MouseEvent mouseEvent) {
       String todoText =  newTodoList.getText();

       if (!todoText.isEmpty()){
           addList(todoText);
           newTodoList.clear();

//        To update the ListView with data from the database when a new task is added
           loadTasks();
       }
    }

    public void handleClickListview(MouseEvent mouseEvent) {
        selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            newTodoList.setText(selectedItem);
            System.out.println("Selected task: " + selectedItem);
        }
    }

    public void onEditButtonClick(MouseEvent mouseEvent) {
        String updatedTask = newTodoList.getText();
        if (selectedItem != null && !updatedTask.isEmpty()) {
            editTask(selectedItem, updatedTask);
            newTodoList.clear();
            loadTasks(); // Refresh the ListView after editing
        }
    }

    public void onDeleteButtonClick(MouseEvent mouseEvent) {
        selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteTask(selectedItem);
            loadTasks();
        }
    }


//    To add the received tasks to the database
    public void addList( String todoText){
        try(Connection conn = DriverManager.getConnection(url, username,password)){
            String query = "INSERT INTO list(task) VALUES(?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, todoText);
            statement.execute();
            System.out.println("Task added");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void loadTasks() {
        ArrayList<String> tasks = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT task FROM list";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String task = resultSet.getString("task");
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        todoListView.getItems().setAll(tasks);
    }

    public void deleteTask(String task) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM list WHERE task = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, task);
            statement.execute();
            System.out.println("Task deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to edit a task in the database
    public void editTask(String oldTask, String newTask) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE list SET task = ? WHERE task = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, newTask);
            statement.setString(2, oldTask);
            statement.executeUpdate();
            System.out.println("Task updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void onCompleteButtonClick(MouseEvent mouseEvent) {
    }
}
