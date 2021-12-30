package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/home")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("/user/delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteForm(@PathVariable Long userId) {
        adminService.delete(userId);
    }
}
