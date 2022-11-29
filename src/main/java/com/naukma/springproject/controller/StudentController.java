package com.naukma.springproject.controller;

import com.naukma.springproject.model.User;
import com.naukma.springproject.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
@ConditionalOnBean(StudentService.class)
@Tag(name = "Student Controller", description = "Controller for students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


//    @PostMapping("/register")
//    @Operation(summary = "registering student operation")
//    public ResponseEntity registerStudent(@Valid @RequestBody User student){
//        try{
//            studentService.register(student);
//            return ResponseEntity.ok("Student created");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Error 404");
//        }
//    }

    @PostMapping("/register")
    @Operation(summary = "registering student operation")
    public ResponseEntity registerStudent(@ModelAttribute("user") User student){
        try{
            studentService.register(student);
            return ResponseEntity.ok("Student created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @GetMapping("/get/{studentId}")
    @Operation(summary = "getting student by ID operation")
    public ResponseEntity getStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(studentService.get(studentId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @GetMapping("/get/by_login/{userName}")
    @Operation(summary = "getting user by login")
    public ResponseEntity getUserByName(@PathVariable String userName){
        try {
            return ResponseEntity.ok(studentService.getUserByLogin(userName));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{studentId}")
    @Operation(summary = "deleting student operation")
    public ResponseEntity deleteStudent(@PathVariable Long studentId){
        try{
            studentService.delete(studentId);
            return ResponseEntity.ok("Student deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleException(MethodArgumentNotValidException e) {
        //logging error
        return e.getMessage();
    }
}
