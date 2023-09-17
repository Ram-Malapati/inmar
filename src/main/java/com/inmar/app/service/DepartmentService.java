package com.inmar.app.service;

import com.inmar.app.jpa.model.Department;
import com.inmar.app.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartmentById(long id){

        return departmentRepository.findById(id).orElse(null);
    }

    public Department getDepartmentByName(String name){

        return departmentRepository.findByDepartment(name);
    }

    /**
     * This method used for saveDepartment data
     * @param departmentName
     */
    public void saveDepartment(String departmentName){
        Department department=new Department();
        Department existedDepartment=departmentRepository.findByDepartment(departmentName);
        if(Objects.isNull(existedDepartment)) {
            department.setDepartment(departmentName);
            department.setDescription("Test Description for" + departmentName);
            departmentRepository.save(department);
        }
    }
    public void saveAllDepartments(Set<String> departments){

        for(String department:departments){
            saveDepartment(department);
        }
    }
    public void deleteAllDepartments(){
        departmentRepository.deleteAll();
    }
}
