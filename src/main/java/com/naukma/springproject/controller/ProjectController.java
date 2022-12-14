package com.naukma.springproject.controller;

import com.naukma.springproject.aspects.LogExeTime;
import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.exception.StudentIsNotEnrolledException;
import com.naukma.springproject.model.Pair;
import com.naukma.springproject.model.Project;
import com.naukma.springproject.service.HourRequestService;
import com.naukma.springproject.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/project")
@ConditionalOnBean(ProjectService.class)
@Tag(name = "Project Controller", description = "Controller for projects of organizations")
public class ProjectController {
    private final ProjectService projectService;
    private final HourRequestService hourRequestService;

    @Autowired
    public ProjectController(ProjectService projectService, HourRequestService hourRequestService) {
        this.projectService = projectService;
        this.hourRequestService = hourRequestService;
    }

    @PostMapping("/addTo")
    @LogExeTime
    @Operation(summary = "adding project to organization operation")
    public ResponseEntity addToOrganization(@ModelAttribute("projectToOrgPair") Pair<String, String> projectToOrgPair){
        try{
            Project project = new Project();
            project.setName(projectToOrgPair.getFirst());
            projectService.addTo(project, projectToOrgPair.getSecond());
            return ResponseEntity.ok("Project created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addStudent")
    @LogExeTime
    @Operation(summary = "adding student to project operation")
    public ResponseEntity addStudent(@ModelAttribute("studentToProjPair") Pair<String, String> studentToProjPair) {
        try{
            projectService.addStudent(studentToProjPair.getFirst(), studentToProjPair.getSecond());
            return ResponseEntity.ok("Student added to project");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{projectId}")
    @Operation(summary = "getting project by ID operation")
    public ResponseEntity getProject(@PathVariable Long projectId) {
        try {
            return ResponseEntity.ok(projectService.get(projectId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/members/setHours")
    @Operation(summary = "setting work hours to student")
    public ResponseEntity setHoursForMember(@RequestParam("requestId") String requestId,
                                            @RequestParam("studentLogin") String studentLogin,
                                            @RequestParam("projectName") String projectName,
                                            @RequestParam("hoursAmount") String hoursAmount) {
        try {
            projectService.setHoursForMember(studentLogin, projectName, Long.parseLong(hoursAmount));
            hourRequestService.delete(Long.parseLong(requestId));
            return ResponseEntity.ok("Hours set for the student");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{studentProjectId}")
    @Operation(summary = "delete project from organization operation")
    public ResponseEntity deleteFromOrganization(@PathVariable Long studentProjectId){
        try{
            projectService.delete(studentProjectId);
            return ResponseEntity.ok("Project deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException e) {
        //logging error
        return e.getMessage();
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
