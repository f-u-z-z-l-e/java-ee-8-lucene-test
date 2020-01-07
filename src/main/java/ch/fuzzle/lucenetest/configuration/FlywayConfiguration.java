package ch.fuzzle.lucenetest.configuration;

import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class FlywayConfiguration {

    @Resource(lookup = "java:jboss/datasources/postgresDS")
    private DataSource dataSource;

    @PostConstruct
    public void initFlyway() {
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        int numberOfMigrations = flyway.migrate();
    }

}
