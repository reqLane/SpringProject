package com.naukma.springproject.controller;

import com.naukma.springproject.entity.StudentOrganizationEntity;
import com.naukma.springproject.service.StudentOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@ConditionalOnBean(StudentOrganizationService.class)
public class StudentOrganizationController {
    private StudentOrganizationService studentOrganizationService;

    @Autowired
    public StudentOrganizationController(StudentOrganizationService studentOrganizationService) {
        this.studentOrganizationService = studentOrganizationService;
    }

    @PostMapping("/register")
    public ResponseEntity registerOrganization(@RequestBody StudentOrganizationEntity studentOrganizationEntity){
        try{
            studentOrganizationService.register(studentOrganizationEntity);
            return ResponseEntity.ok("Organization created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{organizationId}")
    public ResponseEntity deleteOrganization(@RequestParam Long organizationId){
        try{
            studentOrganizationService.delete(organizationId);
            return ResponseEntity.ok("Organization deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }
}
