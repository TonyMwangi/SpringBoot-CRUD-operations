package com.springrestapi.springrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springrestapi.springrestapi.entities.Course;
import com.springrestapi.springrestapi.services.CourseService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/home")
    public String home(){
        return "Hi Vishal, welcome to Springboot";
    }

    //get the courses
    @GetMapping("/courses")
    public List<Course> getCourses(){

        return  this.courseService.getCourses();
    }


    //get particular course
    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId){

        return this.courseService.getCourse(Long.parseLong(courseId));
    }

    //Add Course
    @PostMapping(path = "/courses", consumes = "application/json")
    public Course addCourse(@RequestBody Course newCourse){

        return courseService.addCourse(newCourse);
    }

    //Update Course
    @PutMapping (path = "/courses", consumes = "application/json")
    public Course updateCourse(@RequestBody Course newCourse){

        return courseService.updateCourse(newCourse);
    }

    //Delete Course
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId){
        try{
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
