package ch.fuzzle.lucenetest.identity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public Integer createPerson(Person person) {
        Person persistedPerson = em.merge(person);
        return persistedPerson.getId();
    }

    public Person readPerson(int personId) {
        return em.find(Person.class, personId);
    }
}
