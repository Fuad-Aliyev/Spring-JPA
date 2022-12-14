package com.tutorial.jpa.repository;

import com.tutorial.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
public class JPQLTest {
    private static final Logger logger = LoggerFactory.getLogger(JPQLTest.class);
    @Autowired
    private EntityManager em;

    @Test
    public void jpql_basic() {
        List resultList = em.createQuery("Select c from Course c").getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where c.name like '%50 Step%'", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where name like '%50 Step%' -> {}", resultList);
    }
}
