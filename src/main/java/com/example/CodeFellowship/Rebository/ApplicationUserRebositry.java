package com.example.CodeFellowship.Rebository;

import com.example.CodeFellowship.table.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRebositry extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByusername(String username);  //to find userName


}
