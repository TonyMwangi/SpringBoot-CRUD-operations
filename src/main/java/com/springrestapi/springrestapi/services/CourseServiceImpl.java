package com.springrestapi.springrestapi.services;

import com.springrestapi.springrestapi.dao.CourseDao;
import com.springrestapi.springrestapi.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CourseServiceImpl implements  CourseService{

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseDao.getOne(courseId);
    }

    @Override
    public Course addCourse(Course newCourse) {
        courseDao.save(newCourse);
        return newCourse;
    }

    @Override
    public Course updateCourse(Course newCourse) {
        courseDao.save(newCourse);
        return newCourse;
    }

    @Override
    public void deleteCourse(long courseId) {
        courseDao.deleteById(courseId);

    //other way is to get course entity and pass it as argument.
    //Course entity = courseDao.getOne(courseId);
    //courseDao.delete(entity);

    }


    // Below is Old code with static/temporary data without using JPA and MySQL to store and fetch from DB.
    /*

    List<Course> list;

    public CourseServiceImpl(){
        list= new ArrayList<>();
        list.add(new Course(123, "Java Arrays", "Arrays and it's Implementation"));
        list.add(new Course(124, "Java Strings", "Strings and it's Implementation"));
        list.add(new Course(125, "Java Springboot", "Creating REST APIs"));

    }

    @Override
    public List<Course> getCourses() {
        return list;
    }

    @Override
    public Course getCourse(Long courseId) {

        Course c = null;
        for(Course course : list){
            if(course.getId() == courseId){
                c = course;
                break;
            }
        }
        return  c;
    }

    @Override
    public Course addCourse(Course newCourse) {
        list.add(newCourse);
        return newCourse;
    }

    @Override
    public Course updateCourse(Course newCourse) {

        for(Course c : list){
            if(c.getId()== newCourse.getId()){
                c.setTitle(newCourse.getTitle());
                c.setDescription(newCourse.getDescription());
            }
        }
        return newCourse;
    }

    //This will give ConcurrentModificationException
//    @Override
//    public void deleteCourse(Long parseLong) {
//        for(Course c : list){
//            if(c.getId()== parseLong){
//                list.remove(c);
//            }
//        }
//    }

    @Override
    public void deleteCourse(long courseId) {
        Iterator<Course> iterator = list.iterator();
        while (iterator.hasNext()) {
            Course c = iterator.next();
            if (c.getId() ==courseId) {
                iterator.remove();
            }
        }
    }
*/

}
