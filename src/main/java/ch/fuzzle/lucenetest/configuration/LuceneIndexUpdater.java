package ch.fuzzle.lucenetest.configuration;

import org.hibernate.search.jpa.FullTextEntityManager;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class LuceneIndexUpdater {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager entityManager;

    @PostConstruct
    public void updateLuceneIndex() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }
}
