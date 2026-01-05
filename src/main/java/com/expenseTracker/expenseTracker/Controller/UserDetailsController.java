package com.expenseTracker.expenseTracker.Controller;

import com.expenseTracker.expenseTracker.Entity.UserDetails;
import com.expenseTracker.expenseTracker.Service.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/test")
    public String test() {
        return "App is working";
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

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UserDetails userDetails) {
        userDetailsService.updateUserDetails(userDetails);
    }
}
