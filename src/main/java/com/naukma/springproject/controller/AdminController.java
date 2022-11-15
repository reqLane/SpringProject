package com.naukma.springproject.controller;

import com.naukma.springproject.entity.AdminEntity;
import com.naukma.springproject.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
@ConditionalOnBean(AdminService.class)
@Api(value = "", tags  = "Admin Controller")
@Tag(name = "Admin Controller", description = "Controller for admins of site")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("/delete-organization/{organizationId}")
    @ApiOperation("deleting organization operation")
    public ResponseEntity deleteOrganization(@PathVariable Long organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/organization/delete/" + organizationId;

        try {
            restTemplate.delete(url);
            return ResponseEntity.ok("Organization deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @PostMapping("/register")
    @ApiOperation("registering of new admin")
    public ResponseEntity registerAdmin(@RequestBody AdminEntity adminEntity){
        try{
            adminService.register(adminEntity);
            return ResponseEntity.ok("Admin created");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @GetMapping("/get/{adminId}")
    @ApiOperation("getting admin by id operation")
    public ResponseEntity getAdmin(@PathVariable Long adminId){
        try{
            return ResponseEntity.ok(adminService.get(adminId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{adminId}")
    @ApiOperation("deleting admin by id operation")
    public ResponseEntity deleteAdmin(@PathVariable Long adminId){
        try{
            adminService.delete(adminId);
            return ResponseEntity.ok("Admin deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleException(NoSuchElementException e) {
        //logging error
        return e.getMessage();
    }
}