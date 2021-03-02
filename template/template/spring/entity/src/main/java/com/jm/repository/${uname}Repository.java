package com.jm.repository;
import com.jm.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ${uname}Repository extends JpaRepository<${uname}Entity, Long> {
     public ${uname}Entity findOneBy${uname}Name(String name);
}