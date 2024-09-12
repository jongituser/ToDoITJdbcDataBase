package lexicon.se.model;

import java.time.LocalDate;

public class TodoItem {

private int todo_id;
private String title;
private String description;
private String deadline;
private boolean done;
private int assignee_id;

    public TodoItem(int todo_id, String title, String description, String deadline, boolean done, int assignee_id) {
        this.todo_id = todo_id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assignee_id = assignee_id;
    }

    public TodoItem(int todoId, String title, String description, LocalDate deadline, boolean done, int assigneeId, int categoryId) {
    }

    public int getTodo_id() {
        return todo_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return done;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "todo_id=" + todo_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", done=" + done +
                ", assignee_id=" + assignee_id +
                '}';
    }
}
