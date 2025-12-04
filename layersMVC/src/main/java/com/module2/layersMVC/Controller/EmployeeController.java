package com.module2.layersMVC.Controller;


import com.module2.layersMVC.dto.EmployeeDto;
import com.module2.layersMVC.entity.EmployeeEntity;
import com.module2.layersMVC.repository.EmployeeRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping (path = "/employees")
public class EmployeeController {
//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//
//        return "Secret message: asdfal@#DASDJNJ";
//    }

    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @GetMapping(path= "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name ="employeeId" ) Long id){
        return employeeRepo.findById(id).orElse(null);
        // return new EmployeeDto(id,"Monu","monu@gmail.com",20,LocalDate.of(2023,8,21 ),true);
    }

    @GetMapping
 //   public String
   public List<EmployeeEntity> getAllEmployeed(@RequestParam (required = false)Integer age,
                                               @RequestParam (required = false)String name){
        return employeeRepo.findAll();
    }

    @PostMapping

//    public String postmap(){
//
//        return "hii from post  ";
//
//    }

    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepo.save(inputEmployee);
    }
    @PutMapping
    public String updateEmployeeId(){

        return "employee id update successful by put ";

    }

    @DeleteMapping
    public String deleteEmployee(){

        return "employee deleted successful by delete  ";

    }

    @PatchMapping
    public String patchEmployee(){

        return "message by patch ";

    }

//@RequestBody
}
