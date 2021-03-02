package com.jm.repository;
import com.jm.domain.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
     public EmployeeEntity findOneByFirstName(String name);
}