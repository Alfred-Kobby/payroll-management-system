package com.ug.PayrollManagementSystem.controller;

import com.ug.PayrollManagementSystem.entity.Employee;
import com.ug.PayrollManagementSystem.model.EmployeeRequest;
import com.ug.PayrollManagementSystem.service.DatabaseBackupService;
import com.ug.PayrollManagementSystem.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DatabaseBackupService databaseBackupService;

    // list all employees
    @GetMapping("/employees")
    public String listEmployees (Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());

        return "employees";
    }

    // add a new employee
    @GetMapping("/employee/add")
    public String createStudentForm(Model model){

        // student object to hold new employee
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "create_employee";
    }

    // save new employee
    @PostMapping("/add/employee")
    public String saveNewEmployee(@ModelAttribute("employee") EmployeeRequest employeeRequest){
        log.info("Request to add new Employee; {}", employeeRequest);
        LocalDate employmentDate = LocalDate.parse(employeeRequest.getEmploymentDate());
        Employee employee = new Employee(employeeRequest, employmentDate);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    // create edit employee form
    @GetMapping("/employee/edit/{employeeNo}")
    public String createEditStudentForm(@PathVariable Integer employeeNo,  Model model){

        model.addAttribute("employee", employeeService.getEmployeeById(employeeNo));

        return "edit_employee";
    }

    // edit employees
    @PostMapping("/employee/{id}")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute("employee") Employee employee, Model model){

        // get employee from DB
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setEmployeeNo(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmploymentDate(employee.getEmploymentDate());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setCreated(employee.getCreated());
        existingEmployee.setUpdated(LocalDateTime.now());

        // save updated employee object
        employeeService.updateEmployee(existingEmployee);
        return "redirect:/employee";
    }

    @GetMapping("/backup")
    public String backupDatabase(){
        log.info("Request to backup database");
        return databaseBackupService.backupDatabase();
    }

//    // handler method for delete student request
//    @GetMapping("/students/{id}")
//    public String deleteStudent(@PathVariable Integer id){
//
//        employeeService.deleteEmployeeById(id);
//        return "redirect:/students";
//    }
}
