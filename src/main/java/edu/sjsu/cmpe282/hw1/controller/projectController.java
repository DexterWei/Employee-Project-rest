package edu.sjsu.cmpe282.hw1.controller;

import edu.sjsu.cmpe282.hw1.dao.projectDAO;
import edu.sjsu.cmpe282.hw1.model.employee;
import edu.sjsu.cmpe282.hw1.model.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by dexter on 2/29/16.
 */
@Controller
@RequestMapping("/cmpe282XiaoWei742/rest/project")
public class projectController {
    @Autowired
    private projectDAO pDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getAllEmployee() {
        ArrayList<project> rst = pDao.findAllProjects();
        if(rst.isEmpty())
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity(rst,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getProject(@PathVariable("id") int id) {
        project rst = pDao.findProjectById(id);
        if(rst == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rst,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    ResponseEntity<?> createProject(@RequestBody project newEmp) {
        if(pDao.findProjectById(newEmp.getId())!=null)
            return new ResponseEntity(null,HttpStatus.CONFLICT);
        boolean rst = pDao.createProject(newEmp.getId(),newEmp.getName(),newEmp.getBudget());
        return new ResponseEntity(rst,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public
    @ResponseBody
    ResponseEntity<?> updateProject(@PathVariable("id") int id, @RequestBody project obj) {
        project emp = pDao.findProjectById(id);
        if(emp == null)
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);

        boolean rst = pDao.updateProject(id, obj.getName(), obj.getBudget());
        return new ResponseEntity(rst,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<?> deleteProject(@PathVariable("id") int id) {
        project emp = pDao.findProjectById(id);
        if(emp == null)
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);

        boolean rst = pDao.deleteProject(id);
        return new ResponseEntity(rst,HttpStatus.OK);
    }
}
