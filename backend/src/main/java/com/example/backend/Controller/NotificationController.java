package com.example.backend.Controller;


import com.example.backend.DTO.Response.NotificationDTO;
import com.example.backend.Service.AdopterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/adopter")
public class NotificationController {

    private AdopterService adopterService;

    @GetMapping("/get-notifications")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@RequestParam int pageNumber){
        List<NotificationDTO> notifications = adopterService.getNotifications(pageNumber);
        return ResponseEntity.ok(notifications);
    }

}
