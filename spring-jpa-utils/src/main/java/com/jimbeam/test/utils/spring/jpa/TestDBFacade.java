package com.jimbeam.test.utils.spring.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestDBFacade {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void cleanDatabase(String... tableNames) {
        transactionTemplate.execute(status -> {
            JdbcTestUtils.deleteFromTables(jdbcTemplate, tableNames);
            return null;
        });
    }

    public <T> T findById(Object id, Class<T> entityClass) {
        return transactionTemplate.execute(status -> testEntityManager.find(entityClass, id));
    }

    public <T, V> V findById(Object id, Class<T> entityClass, Function<T, V> callback) {
        return transactionTemplate.execute(status -> {
            T result = testEntityManager.find(entityClass, id);
            return callback.apply(result);
        });
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        return transactionTemplate.execute(status -> {
            EntityManager entityManager = testEntityManager.getEntityManager();
            CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(entityClass);
            Root<T> from = query.from(entityClass);
            query.select(from);
            return entityManager.createQuery(query).getResultList();
        });
    }

    public <T, V> V findAll(Supplier<List<T>> supplier, Function<List<T>, V> callback) {
        return transactionTemplate.execute(status -> {
            List<T> entities = supplier.get();
            return callback.apply(entities);
        });
    }

    public <T> List<T> saveAll(T... entities) {
        return transactionTemplate.execute(status -> saveAll(Arrays.asList(entities)));
    }

    public <T> List<T> saveAll(Collection<T> entities) {
        return transactionTemplate.execute(status -> entities.stream()
                .map(this::save)
                .toList());
    }

    public <T> T save(T entity) {
        return transactionTemplate.execute(
                status -> testEntityManager.persistAndFlush(entity));
    }

    public TestEntityManager getTestEntityManager() {
        return testEntityManager;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @TestConfiguration
    public static class Config {

        @Bean
        public TestDBFacade testDBFacade() {
            return new TestDBFacade();
        }
    }

}
