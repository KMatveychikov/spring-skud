package ru.matvey.springskud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matvey.springskud.dto.PassRequest;
import ru.matvey.springskud.dto.PassResponse;
import ru.matvey.springskud.model.Employee;
import ru.matvey.springskud.model.Pass;
import ru.matvey.springskud.repository.DoorControllerRepository;
import ru.matvey.springskud.repository.EmployeeRepository;
import ru.matvey.springskud.repository.PassRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PassRepository passRepository;

    @Autowired
    DoorControllerRepository doorControllerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimeService timeService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public boolean addPass(PassRequest request) {
        Employee employee = employeeService.getEmployeeByCardId(request.getCardId());
        Date date = new Date();
        if (employee != null) {
            var pass = Pass.builder()
                    .doorController(doorControllerRepository.findById(request.getDoorControllerId()).orElseThrow())
                    .cardId(employee.getCardId())
                    .employee(employee)
                    .dateTime(date)
                    .build();
            passRepository.save(pass);
            return true;
        } else {
            return false;
        }
    }

    public List<PassResponse> getAllPass() {
        List<Pass> passes = passRepository.findAll();
        return getPassResponses(passes);
    }

    public List<PassResponse> getPassByTime(String simpleBegin, String simpleEnd) throws ParseException {
        Date begin = simpleDateFormat.parse(simpleBegin);
        Date end = simpleDateFormat.parse(simpleEnd);
        List<Pass> passes = passRepository.findPassesByDateTimeIsBetween(begin, end);
        return getPassResponses(passes);
    }

    public List<PassResponse> getPassByTimeAfter(String simpleBegin) throws ParseException {
        Date begin = simpleDateFormat.parse(simpleBegin);
        List<Pass> passes = passRepository.findPassesByDateTimeIsAfter(begin);
        return getPassResponses(passes);
    }

    public List<PassResponse> getPassByEmployeeName(String name) throws ParseException {
        List<Pass> passes = passRepository.findPassesByEmployee_NameContaining(name);
        return getPassResponses(passes);
    }

    private List<PassResponse> getPassResponses(List<Pass> passes) {
        List<PassResponse> passResponses = new ArrayList<>();
        for (Pass pass : passes) {
            var passResponse = PassResponse.builder()
                    .doorControllerDescription(pass.getDoorController().getDescription())
                    .date(simpleDateFormat.format(pass.getDateTime()))
                    .employeeName(pass.getEmployee().getName())
                    .build();
            passResponses.add(passResponse);
        }

        return passResponses;
    }


}
