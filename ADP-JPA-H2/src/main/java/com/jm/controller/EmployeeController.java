package com.jm.controller;



import com.jm.domain.EmployeeEntity;
import com.jm.repository.EmployeeRepository;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    protected @Autowired EmployeeRepository employeeRepo;
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<EmployeeEntity>  getAll()
    {    return employeeRepo.findAll();   }
    
    
    @RequestMapping(value = "find/{name}", method = RequestMethod.GET)
    public EmployeeEntity  findOne(@PathVariable(value="name") String name)
    {    return employeeRepo.findOneByFirstName(name);   }
    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public EmployeeEntity save(
        @RequestBody  EmployeeEntity employee) {
        EmployeeEntity entity=employeeRepo.findOneByFirstName(employee.getFirstName()); 
        if (entity!=null) employeeRepo.save(employee);
        else {
            employee.setId(entity.getId());
            employeeRepo.save(employee);
        }
        return employeeRepo.findOneByFirstName(employee.getFirstName()); 
        
    }
    
    
    
    
    
    

}
