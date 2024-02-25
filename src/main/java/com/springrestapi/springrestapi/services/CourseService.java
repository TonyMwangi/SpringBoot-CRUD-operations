package com.springrestapi.springrestapi.services;

import com.springrestapi.springrestapi.entities.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getCourses();
    public Course getCourse(Long courseId);

    public Course addCourse(Course newCourse);

    public Course updateCourse(Course newCourse);

    public void deleteCourse(long parseLong);
}
