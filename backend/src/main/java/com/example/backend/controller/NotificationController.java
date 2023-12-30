package com.example.backend.controller;


import com.example.backend.dto.Response.NotificationDTO;
import com.example.backend.service.AdopterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<NotificationDTO>> getNotifications(@AuthenticationPrincipal int userId ,
                                                                  @RequestParam int pageNumber){
        List<NotificationDTO> notifications = adopterService.getNotifications(userId , pageNumber);
        return ResponseEntity.ok(notifications);
    }

}
