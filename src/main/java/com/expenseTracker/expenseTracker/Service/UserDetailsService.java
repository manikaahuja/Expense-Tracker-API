package com.expenseTracker.expenseTracker.Service;

import com.expenseTracker.expenseTracker.Entity.UserDetails;

import java.util.List;

public interface UserDetailsService {

    public void saveUserDetails(UserDetails userDetails);
    public List<UserDetails> getAllUserDetails();
    public String deleteUserDetails(Long userId);
    public void updateUserDetails(UserDetails userDetails);
    public void updateLastSentDate(Long userId);
}
