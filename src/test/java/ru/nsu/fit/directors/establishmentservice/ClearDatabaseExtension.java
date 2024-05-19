package ru.nsu.fit.directors.establishmentservice;

import org.flywaydb.core.Flyway;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class ClearDatabaseExtension extends AbstractTestExecutionListener {
    @Override
    public void prepareTestInstance(TestContext testContext) {
        Flyway flyway = testContext.getApplicationContext().getBean(Flyway.class);
        flyway.clean();
        flyway.migrate();
    }
}
