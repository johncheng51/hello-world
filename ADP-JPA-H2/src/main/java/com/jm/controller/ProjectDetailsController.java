package com.jm.controller;


import com.jm.domain.EmployeeEntity;
import com.jm.domain.ProjectDetailsEntity;
import com.jm.repository.EmployeeRepository;
import com.jm.repository.ProjectDetailsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projectDetails")
public class ProjectDetailsController {
    protected @Autowired
    ProjectDetailsRepository projectDetailsRepo;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<ProjectDetailsEntity> getAll() {
        return projectDetailsRepo.findAll();
    }


    @RequestMapping(value = "find/{name}", method = RequestMethod.GET)
    public ProjectDetailsEntity findOne(@PathVariable(value = "name") String name) {
        return projectDetailsRepo.findOneByProjectName(name);
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ProjectDetailsEntity save(@RequestBody ProjectDetailsEntity projectDetails) {
        ProjectDetailsEntity entity = projectDetailsRepo.findOneByProjectName(projectDetails.getProjectName());
        if (entity != null)
            projectDetailsRepo.save(projectDetails);
        else {
            projectDetails.setId(entity.getId());
            projectDetailsRepo.save(projectDetails);
        }
        return projectDetailsRepo.findOneByProjectName(projectDetails.getProjectName());

    }


}
