package com.springrestapi.springrestapi.dao;


import com.springrestapi.springrestapi.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Long> {
}
