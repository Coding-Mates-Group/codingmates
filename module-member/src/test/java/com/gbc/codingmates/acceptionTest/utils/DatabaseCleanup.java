package com.gbc.codingmates.acceptionTest.utils;

import com.google.common.base.CaseFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.transaction.Transactional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleanup implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;

    private Map<String, String> idColumnNamePerTable = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        idColumnNamePerTable = entityManager.getMetamodel().getEntities().stream()
            .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
            .filter(e -> e.getJavaType().getAnnotation(Table.class) == null)
            .collect(Collectors.toMap(
                e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()),
                e -> {
                    try {
                        Column id = e.getJavaType().getDeclaredField("id")
                            .getAnnotation(Column.class);
                        return id != null ? id.name() : "id";
                    } catch (NoSuchFieldException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            ));


    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (Entry<String, String> tableInfo : idColumnNamePerTable.entrySet()) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableInfo.getKey()).executeUpdate();
//            entityManager.createNativeQuery("ALTER TABLE " + tableInfo.getKey() + " ALTER COLUMN "+tableInfo.getValue()+" RESTART WITH 1").executeUpdate();
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
