package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteForm(@PathVariable Long userId) {
        adminService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
