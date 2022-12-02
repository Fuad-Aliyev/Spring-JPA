package com.tutorial.jpa.repository;

import com.tutorial.jpa.entity.Passport;
import com.tutorial.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
public class StudentRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        Passport passport = student.getPassport();
        logger.info("Student passport details -> {}", passport);
    }

    @Test
    //( in the end of method all queries are sent to database).
    // Persistent Context is created in the start of transaction and killed in the end of transaction
    //If there is no transactional annotation then persistent context will be started and closed in every query
    @Transactional
    public void someTest() {
        //retrieve operation - 1
        Student student = em.find(Student.class, 20001L);
        //retrieve operation - 2
        //here we are not using entity manager that is why there will not be session if transactional annotation removed
        //Persistent context has 2 roles. 1 for storing entities and 2 for giving access to database for quering.
        // here persistent context send select query to database
        Passport passport = student.getPassport();
        //update operation - 1
        passport.setNumber("E123457");
        //update operation - 2
        student.setName("Ranga - Updated");
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudentDetails() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("Passport -> {}", passport);
        logger.info("Student details -> {}", passport.getStudent());
    }
}
