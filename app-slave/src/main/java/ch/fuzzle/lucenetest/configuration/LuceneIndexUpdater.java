package ch.fuzzle.lucenetest.configuration;

import org.hibernate.search.jpa.FullTextEntityManager;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Startup
@Singleton
public class LuceneIndexUpdater {

    private Logger log = Logger.getLogger("StartupLogger");

    @PersistenceContext(name = "jpa-unit")
    private EntityManager entityManager;

    @PostConstruct
    public void updateLuceneIndex() {
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException ex) {
            log.warning("Unable to update local lucene index.");
        }
    }
}
