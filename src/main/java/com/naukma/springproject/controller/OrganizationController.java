package com.naukma.springproject.controller;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.service.OrganizationService;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@ConditionalOnBean(OrganizationService.class)
public class OrganizationController {
    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register")
    public ResponseEntity registerOrganization(@RequestBody OrganizationEntity organizationEntity){
        try{
            ThreadContext.put("details", "orgName=" + organizationEntity.getName());
            organizationService.register(organizationEntity);
            ThreadContext.clearAll();
            return ResponseEntity.ok("Organization created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{organizationId}")
    public ResponseEntity deleteOrganization(@PathVariable Long organizationId){
        try{
            ThreadContext.put("details", "deletedId=" + organizationId);
            organizationService.delete(organizationId);
            return ResponseEntity.ok("Organization deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }
}
