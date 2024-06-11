package com.example.todolistapp;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    public TextField newTodoList;

    @FXML
    private ListView todoListView;

    public void initialize() {
        Todoitem item1 = new Todoitem("Mary's Birthday card", "Buy a birthday gift for Mary", LocalDate.of(2016, Month.APRIL, 25));
        Todoitem item2 = new Todoitem("Doctor's Appointment  ", "See Dr Smith at 123 main street ", LocalDate.of(2016, Month.MAY, 14));
        Todoitem item3 = new Todoitem("Finish design proposal", "Deadline before next week", LocalDate.of(2016, Month.APRIL, 23));
        Todoitem item4 = new Todoitem("Java Project review meeting", "Finish the Java ui", LocalDate.of(2016, Month.MARCH, 25));
        Todoitem item5 = new Todoitem("Pick up delivery", "Deadline for delivery document", LocalDate.of(2016, Month.APRIL, 20));

        List<Todoitem> todoItem = new ArrayList<Todoitem>();
        todoItem.add(item1);
        todoItem.add(item2);
        todoItem.add(item3);

        todoItem.add(item5);

        todoListView.getItems().setAll(todoItem);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


    }
    @FXML
    public void handleClickListview() {
        Todoitem item = (Todoitem) todoListView.getSelectionModel().getSelectedItem();



    }
    @FXML
    public void onAddButtonClick() {
        String todoText = newTodoList.getText();

        if(!todoText.isEmpty()){
            todoListView.getItems().add(todoText);
            newTodoList.clear();
        }


    }
    @FXML
    public void onDeleteButtonClick(){
        int indexToRemove = todoListView.getSelectionModel().getSelectedIndex();
        if(indexToRemove != -1){
            todoListView.getItems().remove(indexToRemove);

        }

    }

}