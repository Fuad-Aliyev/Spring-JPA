package com.tutorial.jpa.repository;

import com.tutorial.jpa.entity.Passport;
import com.tutorial.jpa.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {
    private static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return  em.find(Student.class, id);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(passport);
        em.persist(student);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }
}
