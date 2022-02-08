package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteForm(@PathVariable Long userId) {
        adminService.delete(userId);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
