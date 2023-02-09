package ru.matvey.springskud.service;

import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matvey.springskud.dto.EmployeeResponse;
import ru.matvey.springskud.dto.PassResponse;
import ru.matvey.springskud.exception.ResourceNotFoundException;
import ru.matvey.springskud.model.Employee;
import ru.matvey.springskud.model.Pass;
import ru.matvey.springskud.repository.EmployeeRepository;
import ru.matvey.springskud.repository.PassRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PassRepository passRepository;
    @Autowired
    TimeService timeService;

    SimpleDateFormat simpleDateFormatFull = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Employee addEmployee(Employee request) {
        var employee = Employee.builder()
                .name(request.getName())
                .cardId(request.getCardId())
                .position(request.getPosition())
                .department(request.getDepartment())
                .build();
        return employeeRepository.save(employee);
    }

    private PassResponse getLastPassByEmployeeName(String name) {
        List<Pass> passes = passRepository.findPassesByEmployee_NameContaining(name);
        if (passes.size() != 0) {
            Pass lastPass = Iterables.getLast(passes);
            return PassResponse.builder()
                    .doorControllerDescription(lastPass.getDoorController().getDescription())
                    .date(simpleDateFormatFull.format(lastPass.getDateTime()))
                    .employeeName(lastPass.getEmployee().getName())
                    .build();
        }
        return null;
    }

    public List<EmployeeResponse> getAllEmployees() throws ParseException {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee employee : employees) {
            PassResponse lastPass = getLastPassByEmployeeName(employee.getName());
            EmployeeResponse employeeResponse;
            if (lastPass != null) {
                employeeResponse = EmployeeResponse.builder()
                        .name(employee.getName())
                        .department(employee.getDepartment())
                        .position(employee.getPosition())
                        .lastPassDate(lastPass.getDate())
                        .lastPassDoor(lastPass.getDoorControllerDescription())
                        .build();
            } else {
                employeeResponse = EmployeeResponse.builder()
                        .name(employee.getName())
                        .department(employee.getDepartment())
                        .position(employee.getPosition())
                        .lastPassDate(null)
                        .lastPassDoor(null)
                        .build();
            }
            employeeResponses.add(employeeResponse);
        }
        return employeeResponses;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exists"));
    }

    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    public Employee getEmployeeByCardId(String cardId) {
        return employeeRepository.findByCardId(cardId);
    }
}
