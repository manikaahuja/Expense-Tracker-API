package com.expenseTracker.expenseTracker.Service.Impl;

import com.expenseTracker.expenseTracker.Dao.UserDetailsDao;
import com.expenseTracker.expenseTracker.Entity.UserDetails;
import com.expenseTracker.expenseTracker.Service.UserDetailsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsDao userDetailsDao;

    public UserDetailsServiceImpl(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

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
    public String updateUserDetails(Long userId, UserDetails userDetails) {
        Optional<UserDetails> existingUser = userDetailsDao.findById(userId);

        if (existingUser.isPresent()) {
            UserDetails user = existingUser.get();
            user.setUserName(userDetails.getUserName());
            user.setUserMail(userDetails.getUserMail());
            user.setUserBirthDate(userDetails.getUserBirthDate());
            userDetailsDao.save(user);

            return "User Id : " + userId + " details updated successfully";
        }
        return "No user with user Id : " + userId + " was found";
    }

    @Override
    public void updateLastSentDate(Long userId) {
        Optional<UserDetails> user = userDetailsDao.findById(userId);
        if(user.isPresent()) {
            user.get().setLastSentMail(Timestamp.from(Instant.now()));
            userDetailsDao.save(user.get());
        }
    }
}
