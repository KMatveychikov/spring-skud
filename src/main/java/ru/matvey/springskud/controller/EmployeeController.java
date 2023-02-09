package ru.matvey.springskud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matvey.springskud.dto.EmployeeResponse;
import ru.matvey.springskud.model.Employee;
import ru.matvey.springskud.service.EmployeeService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }
    @GetMapping("/all")
    public List<EmployeeResponse> getAllEmployees() throws ParseException {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get_by_name/{name}")
    public List<Employee> getEmployeeByName(@PathVariable String name){
        return employeeService.getEmployeeByName(name);
    }
}
