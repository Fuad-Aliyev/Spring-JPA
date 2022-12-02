package com.tutorial.jpa;

import com.tutorial.jpa.repository.CourseRepository;
import com.tutorial.jpa.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {
	//In jpa we use EntityManager and Persistent Context.
	// But in hibernate use Session and Session Factory

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.saveStudentWithPassport();
	}
}
