package com.naukma.springproject.controller;

import com.naukma.springproject.model.Trio;
import com.naukma.springproject.service.HourRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
@Tag(name = "Hour Requests Controller", description = "Controller for hour requests")
public class HourRequestController {

    private final HourRequestService hourRequestService;

    @Autowired
    public HourRequestController(HourRequestService hourRequestService) {
        this.hourRequestService = hourRequestService;
    }

    @PostMapping("/create")
    @Operation(summary = "creating request")
    public ResponseEntity create(@ModelAttribute("requestData") Trio<String, String, String> data) {
        try{
            hourRequestService.create(data.getFirst(), data.getSecond(), Long.parseLong(data.getThird()));
            return ResponseEntity.ok("Request created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/decline")
    @Operation(summary = "declining request")
    public ResponseEntity decline(@RequestParam("requestId") String requestId) {
        try{
            hourRequestService.delete(Long.parseLong(requestId));
            return ResponseEntity.ok("Request deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
