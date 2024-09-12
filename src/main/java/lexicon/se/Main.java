package lexicon.se;
import lexicon.se.data.ToDoItems;
import lexicon.se.data.People;
import lexicon.se.impl.PeopleImpl;
import lexicon.se.impl.TodoItemImpl;
import lexicon.se.model.Person;
import lexicon.se.model.TodoItem;

public class Main {

    public static void main(String[] args) {

        People people = new PeopleImpl();
        ToDoItems toDoItems = new TodoItemImpl();


        Person person = new Person("Jonathan", "Araya");
        person = people.create(person);
        System.out.println("Person has been created: " + person);

        TodoItem todoItem = new TodoItem(
                0,
                "Complete Project",
                "Finish the project by the end of the week",
                "2024-09-20",
                false,
                person.getId()
        );
        todoItem = toDoItems.create(todoItem);
        System.out.println("ToDoItem Created: " + todoItem);
    }
}