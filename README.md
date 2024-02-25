## ***Spring Boot RESTful Web Service***

**Step 01**: pom.xml Settings

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.1.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.springrestapi</groupId>
	<artifactId>springrestapi</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>springrestapi</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
 
```

**Step 02**: SpringrestapiApplication.java

```java
package com.springrestapi.springrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestapiApplication.class, args);
	}

}
```

**Step 03**: Course.java

```java
package com.springrestapi.springrestapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    private long id;
    private String title;
    private String description;

    public Course(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Course() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
```

**Step 04**: CourseDAO.java

```java
package com.springrestapi.springrestapi.dao;


import com.springrestapi.springrestapi.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Long> {
}
```

**Step 05**: MyController.java

```java
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

```

**Step 06**: Run and Test the application

```java
// Get
http://localhost:8080/courses
http://localhost:8080/courses/{courseId}

// Post
http://localhost:8080/courses

//Put
http://localhost:8080/courses

//Delete
http://localhost:8080/courses/{courseId}
```
