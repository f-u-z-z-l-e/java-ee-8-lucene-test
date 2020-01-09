package ch.fuzzle.lucenetest.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.search.jpa.FullTextEntityManager;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Startup
@Singleton
public class LuceneIndexUpdater {

    private Logger log = Logger.getLogger("StartupLogger");

    @Inject
    @ConfigProperty(name = "mode", defaultValue = "slave")
    private String applicationMode;

    @PersistenceContext(name = "jpa-unit")
    private EntityManager entityManager;

    @PostConstruct
    public void updateLuceneIndex() {
        if ("master".equals(applicationMode)) {
            log.info("Start updating lucene index.");
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
            try {
                fullTextEntityManager.createIndexer().startAndWait();
            } catch (InterruptedException ex) {
                log.warning("Unable to update local lucene index.");
            }

            log.info("Updating lucene index completed.");
        }
    }
}
