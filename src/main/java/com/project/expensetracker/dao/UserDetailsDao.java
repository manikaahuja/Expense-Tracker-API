package com.project.expensetracker.dao;

import com.project.expensetracker.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetails,Long> {

}
