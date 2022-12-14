package com.naukma.springproject.controller;

import com.naukma.springproject.aspects.LogExeTime;
import com.naukma.springproject.model.User;
import com.naukma.springproject.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    @LogExeTime
    @Operation(summary = "registering student operation via registering form")
    public ResponseEntity registerStudentFromForm(@ModelAttribute("user") User student){
        try{
            if(studentService.getUserByLogin(student.getLogin())!=null) return new ResponseEntity(HttpStatus.CONFLICT);
            studentService.register(student);
            return ResponseEntity.ok("Student created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register-test")
    @Operation(summary = "registering student with request body operation")
    public ResponseEntity registerStudentByRequestBody(@RequestBody User student){
        try{
            if(studentService.getUserByLogin(student.getLogin())!=null) return new ResponseEntity(HttpStatus.CONFLICT);
            studentService.register(student);
            return ResponseEntity.ok("Student created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{studentId}")
    @Operation(summary = "getting student by ID operation")
    public ResponseEntity getStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(studentService.get(studentId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-by-login/{userLogin}")
    @Operation(summary = "getting student by login operation")
    public ResponseEntity getStudentByLogin(@PathVariable String userLogin) {
        try {
            return ResponseEntity.ok(studentService.getUserByLogin(userLogin));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/by_login/{userName}")
    @Operation(summary = "getting user by login")
    public ResponseEntity getUserByName(@PathVariable String userName){
        try {
            return ResponseEntity.ok(studentService.getUserByLogin(userName));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/info")
    public String testInfo(){
        return "info (hello test)";
    }

    @DeleteMapping("/delete/{studentId}")
    @Operation(summary = "deleting student operation")
    public ResponseEntity deleteStudent(@PathVariable Long studentId){
        try{
            studentService.delete(studentId);
            return ResponseEntity.ok("Student deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException e) {
        //logging error
        return e.getMessage();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleException(MethodArgumentNotValidException e) {
        //logging error
        return e.getMessage();
    }
}
