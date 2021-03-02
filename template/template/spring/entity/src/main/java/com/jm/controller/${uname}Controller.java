package com.jm.controller;


import com.jm.domain.*;
import com.jm.model.*;
import com.jm.repository.UserRepository;
import com.jm.util.BeanCopy;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${lname}")
public class UserController {
    
    @Autowired
    protected ${uname}Repository ${lname}Repository;
   
    @RequestMapping(value = "${uname}Sample", method = RequestMethod.GET)
    public ${uname} ${uname}Sample() {
        return ${uname}.sample();
    }


    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<${uname}> getAll() {
        List<${uname}Entity> entitys = ${lname}Repository.findAll();
        return  toModels(entitys);
    }


    @RequestMapping(value = "find/{name}", method = RequestMethod.GET)
    public ${uname} findOne(@PathVariable(value = "name") String name) {
        ${uname}Entity entity = ${lname}Repository.findOneBy${uname}Name(name);
        return entityToModel(entity);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ${uname} save(@RequestBody ${uname} user) {
        ${uname}Entity entity = ${lname}Repository.findOneBy${uname}Name(user.get${uname}Name());
        if (entity == null) {
            ${uname}Entity entity1 = modelToEntity(user);
            ${lname}Repository.save(entity1);
        } else {
            updateEntity(user, entity);
            ${lname}Repository.save(entity);
        }
        entity = ${lname}Repository.findOneBy${uname}Name(user.get${uname}Name());
        return entityToModel(entity);
    }


    private List<${uname}> toModels(List<${uname}Entity> entitys) {
        return entitys.stream().map(x->entityToModel(x)).collect(Collectors.toList());
    }

    private ${uname} entityToModel(${uname}Entity entity) {
        return (${uname}) BeanCopy.copy(entity, ${uname}.class);
    }

    private void updateEntity(${uname} user, ${uname}Entity entity) {
        BeanCopy.copy(user, entity);
    }

    private ${uname}Entity modelToEntity(${uname} user) {
        return (${uname}Entity) BeanCopy.copy(user, ${uname}Entity.class);
    }
}
