package com.jm.repository;
import com.jm.domain.TeamDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface TeamDetailsRepository extends JpaRepository<TeamDetailsEntity, Long> {
     public TeamDetailsEntity findOneByTeamName(String name);
}