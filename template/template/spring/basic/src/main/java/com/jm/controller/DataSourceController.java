package com.jm.controller;

import com.jm.config.PrimaryDataSource;
import com.jm.config.SecondDataSource;
import com.jm.model.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataSourceController {
    protected @Autowired
    PrimaryDataSource pdatasource;
    protected @Autowired
    SecondDataSource sdatasource;

    private DataSource copy(DataSource source) {
        DataSource dataSource = new DataSource();
        dataSource.setUsername(source.getUsername());
        dataSource.setUrl(source.getUrl());
        dataSource.setPassword(source.getPassword());
        dataSource.setDriverClassName(source.getDriverClassName());
        return dataSource;
    }


    private DataSource assign(DataSource source, DataSource target) {
        target.setUsername(source.getUsername());
        target.setUrl(source.getUrl());
        target.setPassword(source.getPassword());
        target.setDriverClassName(source.getDriverClassName());
        return copy(target);
    }


    @RequestMapping(value = "readPrimary", method = RequestMethod.GET)
    public DataSource readPrimary() {
        return copy(pdatasource);
    }

    @RequestMapping(value = "readSecond", method = RequestMethod.GET)
    public DataSource readSecond() {
        return copy(sdatasource);
    }

    @RequestMapping(value = "loadPrimary", method = RequestMethod.POST)
    public DataSource loadPrimary(@RequestBody DataSource dataSouce) {
        return assign(dataSouce, pdatasource);
    }
    
    @RequestMapping(value = "loadSecond", method = RequestMethod.POST)
    public DataSource loadSecond(@RequestBody DataSource dataSouce) {
        return assign(dataSouce, sdatasource);
    }


}
