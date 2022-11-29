package com.tutorial.jpa.repository;

import com.tutorial.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA In 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext // after deletion spring will restore data
    public void deleteById_basic() {
        courseRepository.deleteById(10001L);
        assertNull(courseRepository.findById(10001L));
    }

    @Test
    @DirtiesContext
    public void update_basic() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA In 50 Steps", course.getName());
        course.setName("JPA IN 50 Steps - Updated");
        courseRepository.save(course);
        assertEquals("JPA IN 50 Steps - Updated", course.getName());
    }
}
