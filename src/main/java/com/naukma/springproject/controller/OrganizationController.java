package com.naukma.springproject.controller;

import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.model.Organization;
import com.naukma.springproject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/organization")
@ConditionalOnBean(OrganizationService.class)
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register")
    public ResponseEntity registerOrganization(@Valid @RequestBody Organization organization){
        try{
            organizationService.register(organization);
            return ResponseEntity.ok("Organization created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @PostMapping("/{organizationId}/addStudent/{studentId}")
    public ResponseEntity addStudent(@PathVariable Long organizationId,
                                     @PathVariable Long studentId) {
        try{
            organizationService.addStudent(organizationId, studentId);
            return ResponseEntity.ok("Student added to organization");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @GetMapping("/get/{organizationId}")
    public ResponseEntity getOrganization(@PathVariable Long organizationId) {
        try {
            return ResponseEntity.ok(organizationService.get(organizationId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{organizationId}")
    public ResponseEntity deleteOrganization(@PathVariable Long organizationId){
        try{
            organizationService.delete(organizationId);
            return ResponseEntity.ok("Organization deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleException(NoSuchElementException e) {
        //logging error
        return e.getMessage();
    }
    @ExceptionHandler(StudentAlreadyEnrolledException.class)
    public String handleException(StudentAlreadyEnrolledException e) {
        //logging error
        return e.getMessage();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleException(MethodArgumentNotValidException e) {
        //logging error
        return e.getMessage();
    }
}
