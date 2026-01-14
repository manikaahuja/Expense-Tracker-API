package com.project.expensetracker.controller;

import com.project.expensetracker.entity.UserDetails;
import com.project.expensetracker.service.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserDetails userDetails) {
         userDetailsService.saveUserDetails(userDetails);
    }

    @GetMapping("/getUsers")
    public List<UserDetails> getUserDetails() {
        return userDetailsService.getAllUserDetails();
    }

    @DeleteMapping("/deleteUser")
    public String deleteUserDetails(@RequestParam Long userId) {
        return userDetailsService.deleteUserDetails(userId);
    }

    @PutMapping("/{userId}/updateUser")
    public String updateUser(@PathVariable Long userId, @RequestBody UserDetails userDetails) {
        return userDetailsService.updateUserDetails(userId, userDetails);
    }
}
