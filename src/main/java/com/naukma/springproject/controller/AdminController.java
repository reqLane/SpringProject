package com.naukma.springproject.controller;

import com.naukma.springproject.entity.AdminEntity;
import com.naukma.springproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
@ConditionalOnBean(AdminService.class)
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
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
    public ResponseEntity getAdmin(@PathVariable Long adminId){
        try{
            return ResponseEntity.ok(adminService.get(adminId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error 404");
        }
    }

    @DeleteMapping("/delete/{adminId}")
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
