package edu.sjsu.cmpe282.hw1.controller;

import edu.sjsu.cmpe282.hw1.dao.employeeDAO;
import edu.sjsu.cmpe282.hw1.model.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by dexter on 2/28/16.
 */

@Controller
@RequestMapping("/cmpe282XiaoWei742/rest/employee")
public class employeeController {
    @Autowired
    private employeeDAO eDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getAllEmployee() {
        ArrayList<employee> rst = eDao.findAllEmployees();
        if(rst.isEmpty())
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity(rst,HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getEmployee(@PathVariable("id") int id) {
        employee rst = eDao.findEmployeeById(id);
        if(rst == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rst,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    ResponseEntity<?> createEmployee(@RequestBody employee newEmp) {
        if(eDao.findEmployeeById(newEmp.getId())!=null)
            return new ResponseEntity(null,HttpStatus.CONFLICT);
        boolean rst = eDao.createEmployee(newEmp.getId(),newEmp.getFirstName(),newEmp.getLastName());
        return new ResponseEntity(rst,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public
    @ResponseBody
    ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody employee obj) {
        employee emp = eDao.findEmployeeById(id);
        if(emp == null)
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);

        boolean rst = eDao.updateEmployee(id, obj.getFirstName(), obj.getLastName());
        return new ResponseEntity(rst,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) {
        employee emp = eDao.findEmployeeById(id);
        if(emp == null)
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);

        boolean rst = eDao.deleteEmployee(id);
        return new ResponseEntity(rst,HttpStatus.OK);
    }
}
