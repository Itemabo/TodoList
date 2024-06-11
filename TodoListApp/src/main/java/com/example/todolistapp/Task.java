package com.example.todolistapp;

public class Task {
    private final String task;
    private final boolean completed;

    public Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return task;
    }
}
