package com.expenseTracker.expenseTracker.Service.Impl;

import com.expenseTracker.expenseTracker.Dao.UserDetailsDao;
import com.expenseTracker.expenseTracker.Entity.UserDetails;
import com.expenseTracker.expenseTracker.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailsDao userDetailsDao;

    @Override
    public void saveUserDetails(UserDetails userDetails) {
        userDetails.setCreatedDate(Timestamp.from(Instant.now()));
        userDetailsDao.save(userDetails);
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        return userDetailsDao.findAll();
    }

    @Override
    public String deleteUserDetails(Long userId) {
        userDetailsDao.deleteById(userId);
        return "User Id : "+ userId + " deleted successfully";
    }

    @Override
    public void updateUserDetails(UserDetails userDetails) {
        userDetails.setCreatedDate(Timestamp.from(Instant.now()));
        userDetailsDao.save(userDetails);
    }
}
