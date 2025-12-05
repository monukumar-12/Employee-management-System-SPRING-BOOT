package com.module2.layersMVC.service;


import com.module2.layersMVC.dto.EmployeeDto;
import com.module2.layersMVC.entity.EmployeeEntity;
import com.module2.layersMVC.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

      private final ModelMapper modelMapper;
    private final EmployeeRepo employeeRepo;

    public EmployeeService(ModelMapper modelMapper, EmployeeRepo employeeRepo) {
        this.modelMapper = modelMapper;
        this.employeeRepo = employeeRepo;
    }

    public EmployeeDto getEmployeeById(Long id) {
 EmployeeEntity employeeEntity= employeeRepo.findById(id).orElse(null);

        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    public List<EmployeeDto> getAlltheEmploye() {
        List<EmployeeEntity> employeeEntities= employeeRepo.findAll();

       return employeeEntities
               .stream()
               .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
               .collect(Collectors.toList());

    }

    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee) {
       EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
       EmployeeEntity savedEmployeeEntity=employeeRepo.save(toSaveEntity);
       return modelMapper.map(savedEmployeeEntity,EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto employeeDto) {
       EmployeeEntity employeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
       employeeEntity.setId(employeeId);
       EmployeeEntity savedEmployeeEntity = employeeRepo.save(employeeEntity);
       return modelMapper.map(savedEmployeeEntity,EmployeeDto.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {

           boolean exists = isExistByEmployeeTd(employeeId);
           if(!exists) return false;
         employeeRepo.deleteById(employeeId);

        return true;
    }

//    public EmployeeDto UpdatePartialEmployeeTd(long employeeId, Map<String, Object> updates) {
//        boolean exists = isExistByEmployeeTd(employeeId);
//        if(!exists) return null;
//        EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).get();
//        updates.forEach((field, value)-> {
//            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
//
//            fielToBeUpdated.setAccessible(true);
//            ReflectionUtils.setField(fielToBeUpdated, employeeEntity, value);
//        });
//        return modelMapper.map(employeeRepo.save(employeeEntity),EmployeeDto.class);
//
//    }
public EmployeeDto UpdatePartialEmployeeTd(long employeeId, Map<String, Object> updates) {
    boolean exists = isExistByEmployeeTd(employeeId);
    if (!exists) return null;

    EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).get();

    updates.forEach((field, value) -> {
        Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
        fieldToBeUpdated.setAccessible(true);
        ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
    });

    return modelMapper.map(employeeRepo.save(employeeEntity), EmployeeDto.class);
}


    public boolean isExistByEmployeeTd(Long employeeId){
        return employeeRepo.existsById(employeeId);
    }
}


