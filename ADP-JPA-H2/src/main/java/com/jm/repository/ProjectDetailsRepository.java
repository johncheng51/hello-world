package com.jm.repository;
import com.jm.domain.ProjectDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetailsEntity, Long> {
     public ProjectDetailsEntity findOneByProjectName(String name);
}