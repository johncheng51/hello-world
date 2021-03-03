
package com.jm.controller;

import java.util.List;

import com.jm.mock.MockModel;
import com.jm.model.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.jm.model.Home;
import com.jm.service.HomeService;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    protected HomeService homeService;

    @RequestMapping(value = "GetCount", method = RequestMethod.GET)
    public int GetCount() {
        return homeService.findAll().size();
    }


    @RequestMapping(value = "GetAll", method = RequestMethod.GET)
    public List<MockModel> GetAll() {
        return homeService.findAll();
    }


    @RequestMapping(value = "searchHome", method = RequestMethod.POST)
    public List<Home> searchHome(@RequestBody Search search) {
        return (List<Home>) (Object) homeService.search(search);
    }


    @RequestMapping(value = "createHome", method = RequestMethod.POST)
    public Home createHome(@RequestBody Home home) {
        return (Home) homeService.create(home);
    }


    @RequestMapping(value = "saveHome", method = RequestMethod.POST)
    public Home saveHome(@RequestBody Home home) {
        return (Home) homeService.update(home);
    }


    @RequestMapping(value = "getHome", method = RequestMethod.GET)
    public Home getHome() {
        Home home = new Home();
        home.mock();
        return home;
    }


    @RequestMapping(value = "requestHome", method = RequestMethod.GET)
    public List<Home> requestHome(
         @RequestParam("key") String key, 
         @RequestParam("value") String value) {
        Search search = new Search(key, value);
        return (List<Home>) (Object) homeService.search(search);
    }


    @RequestMapping(value = "pathHome/{key}/{value}", method = RequestMethod.GET)
    public List<Home> pathHome(
        @PathVariable("key") String key, 
        @PathVariable("value") String value) {
        Search search = new Search(key, value);
        return (List<Home>) (Object) homeService.search(search);
    }

    /*JAVA_BLOCK*/
}
