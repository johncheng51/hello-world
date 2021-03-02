package com.jm.controller;


import com.jm.domain.EmployeeEntity;
import com.jm.domain.TeamDetailsEntity;

import com.jm.repository.TeamDetailsRepository;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teamDetails")
public class TeamDetailsController {
    protected @Autowired TeamDetailsRepository teamDetailsRepo;
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<TeamDetailsEntity>  getAll()
    {    return teamDetailsRepo.findAll();   }
    
    
    @RequestMapping(value = "find/{name}", method = RequestMethod.GET)
    public TeamDetailsEntity  EmployeeRepository(@PathVariable(value="name") String name)
    {    return teamDetailsRepo.findOneByTeamName(name);   }
    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public TeamDetailsEntity save(
        @RequestBody  TeamDetailsEntity teamDetails) {
        TeamDetailsEntity entity=teamDetailsRepo.findOneByTeamName(teamDetails.getTeamName()); 
        if (entity!=null) teamDetailsRepo.save(teamDetails);
        else {
            teamDetails.setId(entity.getId());
            teamDetailsRepo.save(teamDetails);
        }
        return teamDetailsRepo.findOneByTeamName(teamDetails.getTeamName()); 
        
    }
    
    
    
    
    
    

}
