package ch.fuzzle.lucenetest.identity;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "person")
@JsonbPropertyOrder({"id", "firstname", "lastname"})
public class Person implements Serializable {
    private static final long serialVersionUID = 615394686230954784L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PersonSequence")
    @SequenceGenerator(name = "PersonSequence", sequenceName = "person_seq", allocationSize = 1)
    private Integer id;

    private String firstname;

    private String lastname;

    public Person() {
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstname, person.firstname) &&
                Objects.equals(lastname, person.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
