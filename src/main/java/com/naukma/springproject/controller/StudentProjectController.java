package com.naukma.springproject.controller;

import com.naukma.springproject.entity.StudentProjectEntity;
import com.naukma.springproject.service.StudentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@ConditionalOnBean(StudentProjectService.class)
public class StudentProjectController {
    StudentProjectService studentProjectService;

    @Autowired
    public StudentProjectController(StudentProjectService studentProjectService) {
        this.studentProjectService = studentProjectService;
    }

    @PostMapping("/addTo/{organizationId}")
    public ResponseEntity addToOrganization(@RequestBody StudentProjectEntity studentProjectEntity,
                                            @RequestParam Long organizationId){
        try{
            studentProjectService.addTo(studentProjectEntity,organizationId);
            return ResponseEntity.ok("Project created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{studentProjectId}")
    public ResponseEntity deleteFromOrganization(@RequestParam Long studentProjectId){
        try{
            studentProjectService.delete(studentProjectId);
            return ResponseEntity.ok("Project deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

}
