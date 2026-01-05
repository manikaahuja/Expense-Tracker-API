package com.expenseTracker.expenseTracker.Service;

import com.expenseTracker.expenseTracker.Entity.UserDetails;

import java.util.List;

public interface UserDetailsService {

    void saveUserDetails(UserDetails userDetails);
    List<UserDetails> getAllUserDetails();
    String deleteUserDetails(Long userId);
    void updateUserDetails(UserDetails userDetails);
    void updateLastSentDate(Long userId);
}
