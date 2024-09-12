package lexicon.se.data.impl;

import lexicon.se.data.ToDoItems;
import lexicon.se.helper.MySQLConnection;
import lexicon.se.model.Person;
import lexicon.se.model.TodoItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TodoItemImpl implements ToDoItems {

    @Override
    public TodoItem create(TodoItem todoItem) {
        String query = "INSERT INTO todoit.todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, todoItem.getTitle());
            stmt.setString(2, todoItem.getDescription());
            stmt.setDate(3, Date.valueOf(todoItem.getDeadline()));
            stmt.setBoolean(4, todoItem.isDone());
            stmt.setInt(5, todoItem.getAssignee_id());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                todoItem.setTodo_id(generatedKeys.getInt(1)); // Set the generated ID to the TodoItem object
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public Collection<TodoItem> findAll() {
        String query = "SELECT * FROM todoit.todo_item";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TodoItem todoItem = new TodoItem(
                        rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline").toString(),
                        rs.getBoolean("done"),
                        rs.getInt("assignee_id")
                );
                todoItems.add(todoItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public TodoItem findById(int id) {
        String query = "SELECT * FROM todoit.todo_item WHERE todo_id = ?";
        TodoItem todoItem = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                todoItem = new TodoItem(
                        rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline").toString(),
                        rs.getBoolean("done"),
                        rs.getInt("assignee_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean done) {
        String query = "SELECT * FROM todoit.todo_item WHERE done = ?";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setBoolean(1, done);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TodoItem todoItem = new TodoItem(
                        rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline").toString(),
                        rs.getBoolean("done"),
                        rs.getInt("assignee_id")
                );
                todoItems.add(todoItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int id) {
        String query = "SELECT * FROM todoit.todo_item WHERE assignee_id = ?";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TodoItem todoItem = new TodoItem(
                        rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline").toString(),
                        rs.getBoolean("done"),
                        rs.getInt("assignee_id")
                );
                todoItems.add(todoItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findbyAssigne(Person person) {
        return findByAssignee(person.getId());
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoitems() {
        String query = "SELECT * FROM todoit.todo_item WHERE assignee_id IS NULL";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TodoItem todoItem = new TodoItem(
                        rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline").toString(),
                        rs.getBoolean("done"),
                        rs.getInt("assignee_id")
                );
                todoItems.add(todoItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        String query = "UPDATE todoit.todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, todoItem.getTitle());
            stmt.setString(2, todoItem.getDescription());
            stmt.setDate(3, Date.valueOf(todoItem.getDeadline()));
            stmt.setBoolean(4, todoItem.isDone());
            stmt.setInt(5, todoItem.getAssignee_id());
            stmt.setInt(6, todoItem.getTodo_id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public TodoItem deleteById(int id) {
        String query = "DELETE FROM todoit.todo_item WHERE todo_id = ?";
        TodoItem deletedItem = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // First, retrieve the item to be deleted
            deletedItem = findById(id);

            // Then, perform the deletion
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedItem;
    }
}
