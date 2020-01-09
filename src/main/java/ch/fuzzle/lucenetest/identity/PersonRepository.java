package ch.fuzzle.lucenetest.identity;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<Person> findByFirstname(String firstname) {
        Query fuzzyQuery = getQueryBuilder()
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("firstname")
                .matching(firstname)
                .createQuery();

        List<Person> results = getJpaQuery(fuzzyQuery).getResultList();

        return results;
    }

    private FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        return fullTextEntityManager.createFullTextQuery(luceneQuery, Person.class);
    }

    private QueryBuilder getQueryBuilder() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Person.class)
                .get();
    }
}
