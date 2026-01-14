package com.project.expensetracker.service;

import com.project.expensetracker.entity.UserDetails;

import java.util.List;

public interface UserDetailsService {

    void saveUserDetails(UserDetails userDetails);
    List<UserDetails> getAllUserDetails();
    String deleteUserDetails(Long userId);
    String updateUserDetails(Long userId, UserDetails userDetails);
    void updateLastSentDate(Long userId);
}
