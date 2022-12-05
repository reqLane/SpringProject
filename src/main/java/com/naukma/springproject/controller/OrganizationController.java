package com.naukma.springproject.controller;

import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.model.Organization;
import com.naukma.springproject.model.Pair;
import com.naukma.springproject.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/organization")
@ConditionalOnBean(OrganizationService.class)
@Tag(name = "Organization Controller", description = "Controller for student organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register-from-form")
    @Operation(summary = "registering organization operation")
    public ResponseEntity registerOrganizationFromForm(@ModelAttribute("orgToCreate") Organization organization){
        try{
            organizationService.register(organization);
            return ResponseEntity.ok("Organization created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @PostMapping("/register")
    @Operation(summary = "registering organization by request body operation")
    public ResponseEntity registerOrganizationByRequestBody(@RequestBody Organization organization){
        try{
            organizationService.register(organization);
            return ResponseEntity.ok("Organization created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @PostMapping("/addStudent")
    @Operation(summary = "adding student to organization operation")
    public ResponseEntity addStudent(@ModelAttribute("studentToOrgPair") Pair<String, String> studentOrgPair) {
        try{
            organizationService.addStudent(Long.parseLong(studentOrgPair.getFirst()), studentOrgPair.getSecond());
            return ResponseEntity.ok("Student added to organization");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @GetMapping("/get/{organizationId}")
    @Operation(summary = "getting organization by ID operation")
    public ResponseEntity getOrganization(@PathVariable Long organizationId) {
        try {
            return ResponseEntity.ok(organizationService.get(organizationId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{organizationId}")
    @Operation(summary = "deleting organization by ID operation")
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
