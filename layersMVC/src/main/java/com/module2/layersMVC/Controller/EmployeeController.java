package com.module2.layersMVC.Controller;


import com.module2.layersMVC.dto.EmployeeDto;
import com.module2.layersMVC.entity.EmployeeEntity;
//import com.module2.layersMVC.repository.EmployeeRepo;
import com.module2.layersMVC.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (path = "/employees")
public class EmployeeController {
//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//
//        return "Secret message: asdfal@#DASDJNJ";
//    }

  public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path= "/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name ="employeeId" ) Long id){
        return employeeService.getEmployeeById(id);
        // return new EmployeeDto(id,"Monu","monu@gmail.com",20,LocalDate.of(2023,8,21 ),true);
    }

    @GetMapping
 //   public String
   public List<EmployeeDto> getAllEmployeed(@RequestParam (required = false)Integer age,
                                               @RequestParam (required = false)String name){
        return employeeService.getAlltheEmploye();
    }

    @PostMapping

//    public String postmap(){
//
//        return "hii from post  ";
//
//    }

    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
   public EmployeeDto updateEmployeeById(@RequestBody EmployeeDto employeeDto,@PathVariable Long employeeId){

        return employeeService.updateEmployeeById(employeeId,employeeDto);
    }


    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId){
         return  employeeService.deleteEmployeeById(employeeId);
    }


    @PatchMapping (path = "/{employeeId}")
//    public String patchEmployee(){
//
//        return "message by patch ";
//
//    }

    public EmployeeDto updatePartialEmployeeById(@RequestBody Map<String,Object>updates,
                                                   @PathVariable long employeeId){
        return  employeeService.UpdatePartialEmployeeTd(employeeId,updates);
    }

//@RequestBody
}
