package com.tutorial.jpa.repository;

import com.tutorial.jpa.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {
    private static final Logger logger = LoggerFactory.getLogger(CourseRepository.class);

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return  em.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() {
        Course course = new Course("new course");
        em.persist(course);
        course.setName("new course - updated");
    }

    public void playWithEntityManager_2() {
        Course course1 = new Course("new course");
        em.persist(course1);
        Course course2 = new Course("new course 2");
        em.persist(course2);
        em.flush();

        //it will clear everything in entity manager that is being tracked by
//        em.clear();
        //the changes to course2 are not tracked by entity_manager anymore
//        em.detach(course2);

        course1.setName("new course - updated");
        course2.setName("new course 2 - updated");

        //all tha changes after saving course1 to db will be lost. it will fetched data of course1 from db for refreshing
        em.refresh(course1);
        em.flush();
    }
}
