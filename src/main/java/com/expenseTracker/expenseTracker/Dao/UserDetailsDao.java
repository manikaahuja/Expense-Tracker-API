package com.expenseTracker.expenseTracker.Dao;

import com.expenseTracker.expenseTracker.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetails,Long> {

}
