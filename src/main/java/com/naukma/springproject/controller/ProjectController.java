package com.naukma.springproject.controller;

import com.naukma.springproject.entity.ProjectEntity;
import com.naukma.springproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@ConditionalOnBean(ProjectService.class)
public class ProjectController {
    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/addTo/{organizationId}")
    public ResponseEntity addToOrganization(@RequestBody ProjectEntity projectEntity,
                                            @PathVariable Long organizationId){
        try{
            projectService.addTo(projectEntity,organizationId);
            return ResponseEntity.ok("Project created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{studentProjectId}")
    public ResponseEntity deleteFromOrganization(@PathVariable Long studentProjectId){
        try{
            projectService.delete(studentProjectId);
            return ResponseEntity.ok("Project deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

}
