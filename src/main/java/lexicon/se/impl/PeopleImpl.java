package lexicon.se.impl;

import lexicon.se.data.People;
import lexicon.se.helper.MySQLConnection;
import lexicon.se.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PeopleImpl implements People {

    @Override
    public Person create(Person person) {
        String query = "INSERT INTO todoit.person (first_name, last_name) VALUES (?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getInt(1)); // Set the generated ID to the Person object
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {
        String query = "SELECT * FROM todoit.person";
        Collection<Person> people = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Person findById(int id) {
        String query = "SELECT * FROM todoit.person WHERE person_id = ?";
        Person person = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Collection<Person> findByName(String name) {
        String query = "SELECT * FROM todoit.person WHERE first_name = ? OR last_name = ?";
        Collection<Person> people = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Person update(Person person) {
        String query = "UPDATE todoit.person SET first_name = ?, last_name = ? WHERE person_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setInt(3, person.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM todoit.person WHERE person_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
