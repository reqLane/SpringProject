package com.naukma.springproject.controller;

import com.naukma.springproject.entity.StudentEntity;
import com.naukma.springproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@ConditionalOnBean(StudentService.class)
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/register")
    public ResponseEntity registerStudent(@RequestBody StudentEntity studentEntity){
        try{
            studentService.register(studentEntity);
            return ResponseEntity.ok("Student created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity getStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(studentService.get(studentId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId){
        try{
            studentService.delete(studentId);
            return ResponseEntity.ok("Student deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }
}
