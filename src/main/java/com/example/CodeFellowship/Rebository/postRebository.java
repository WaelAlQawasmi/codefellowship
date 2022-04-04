package com.example.CodeFellowship.Rebository;

import com.example.CodeFellowship.table.post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postRebository extends JpaRepository<post, Long> {
   List<post> findByapplicationUserId(Long id) ;

}
