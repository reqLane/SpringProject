package com.naukma.springproject.controller;

import com.naukma.springproject.entity.StudentOrganizationAdminEntity;
import com.naukma.springproject.service.StudentOrganizationAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizationAdmin")
@ConditionalOnBean(StudentOrganizationAdminService.class)
public class StudentOrganizationAdminController {
    private StudentOrganizationAdminService studentOrganizationAdminService;

    @Autowired
    public StudentOrganizationAdminController(StudentOrganizationAdminService studentOrganizationAdminService) {
        this.studentOrganizationAdminService = studentOrganizationAdminService;
    }

    @PostMapping("/addTo/{organizationId}")
    public ResponseEntity addToOrganization(@RequestBody StudentOrganizationAdminEntity organizationAdminEntity,
                                        @RequestParam Long organizationId){
        try{
            studentOrganizationAdminService.addTo(organizationAdminEntity, organizationId);
            return ResponseEntity.ok("Organization admin created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/{organizationAdminId}/deleteFrom/{organizationId}")
    public ResponseEntity deleteFromOrganization(@RequestParam Long organizationAdminId,
                                                 @RequestParam Long organizationId){
        try{
            studentOrganizationAdminService.deleteFrom(organizationAdminId,organizationId);
            return ResponseEntity.ok("Admin of organization deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

}
