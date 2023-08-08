package com.example.springwebfluxexercise.repository;

import com.example.springwebfluxexercise.domain.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {
}
