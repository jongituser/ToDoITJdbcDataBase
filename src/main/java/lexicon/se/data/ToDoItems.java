package lexicon.se.data;
import lexicon.se.model.Person;
import lexicon.se.model.TodoItem;

import java.util.Collection;

public interface ToDoItems {

TodoItem create(TodoItem todoItem);
Collection<TodoItem> findAll();
TodoItem findById(int id);
Collection<TodoItem> findByDoneStatus(boolean done);
Collection<TodoItem> findByAssignee(int id);
Collection<TodoItem> findbyAssigne(Person person);
Collection<TodoItem> findByUnassignedTodoitems();
TodoItem update(TodoItem todoItem);
TodoItem deleteById(int id);


}
