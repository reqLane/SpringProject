package com.naukma.springproject.controller;

import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.exception.StudentIsNotEnrolledException;
import com.naukma.springproject.model.Project;
import com.naukma.springproject.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/project")
@ConditionalOnBean(ProjectService.class)
@Api(value = "", tags  = "Project Controller")
@Tag(name = "Project Controller", description = "Controller for projects of organization")

public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/addTo/{organizationId}")
    @ApiOperation("adding project to organization by id operation")
    public ResponseEntity addToOrganization(@Valid @RequestBody Project project,
                                            @PathVariable Long organizationId){
        try{
            projectService.addTo(project,organizationId);
            return ResponseEntity.ok("Project created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @PostMapping("/{projectId}/addStudent/{studentId}")
    @ApiOperation("adding student to project operation")
    public ResponseEntity addStudent(@PathVariable Long projectId,
                                     @PathVariable Long studentId) {
        try{
            projectService.addStudent(projectId, studentId);
            return ResponseEntity.ok("Student added to project");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @GetMapping("/get/{projectId}")
    @ApiOperation("getting project by id operation")
    public ResponseEntity getProject(@PathVariable Long projectId) {
        try {
            return ResponseEntity.ok(projectService.get(projectId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @PatchMapping("/{projectId}/members/{studentId}/setHours")
    @ApiOperation("setting work hours to student operation")
    public ResponseEntity setHoursForMember(@PathVariable Long projectId,
                                            @PathVariable Long studentId,
                                            @RequestParam Long hoursAmount) {
        try {
            projectService.setHoursForMember(projectId, studentId, hoursAmount);
            return ResponseEntity.ok("Hours set for the student");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{studentProjectId}")
    @ApiOperation("deleting project from organization operation")
    public ResponseEntity deleteFromOrganization(@PathVariable Long studentProjectId){
        try{
            projectService.delete(studentProjectId);
            return ResponseEntity.ok("Project deleted");
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
    @ExceptionHandler(StudentIsNotEnrolledException.class)
    public String handleException(StudentIsNotEnrolledException e) {
        //logging error
        return e.getMessage();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleException(MethodArgumentNotValidException e) {
        //logging error
        return e.getMessage();
    }
}
