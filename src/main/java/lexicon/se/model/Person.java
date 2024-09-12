package lexicon.se.model;
import java.util.Objects;

public class Person {

    private Integer person_id;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person(Integer person_id, String firstName, String lastName) {
        this(firstName, lastName);
        this.person_id = Objects.requireNonNull(person_id, "person_id cannot be null");
    }

    public Integer getId() {
        return person_id;
    }

    public void setId(Integer person_id) {
        this.person_id = person_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty())
            throw new IllegalArgumentException("firstName is null or empty.");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("lastName is null or empty.");
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(person_id, person.person_id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + person_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}