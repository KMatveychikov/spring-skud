package ru.matvey.springskud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matvey.springskud.model.Employee;
import ru.matvey.springskud.model.Pass;
import ru.matvey.springskud.model.WorkDay;
import ru.matvey.springskud.repository.EmployeeRepository;
import ru.matvey.springskud.repository.PassRepository;
import ru.matvey.springskud.repository.WorkDayRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WorkDayService {
    @Autowired
    WorkDayRepository workDayRepository;
    @Autowired
    PassRepository passRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TimeService timeService;

    SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat simpleDateFormatJs = new SimpleDateFormat("yyyy-MM-dd");

    public WorkDay add(Date date, long employeeId, long workTime) {
        List<WorkDay> workDays = workDayRepository.findWorkDaysByDateAndEmployeeId(date, employeeId);
        if (workDays.size() == 0) {
            var workDay = WorkDay.builder()
                    .date(date)
                    .employeeId(employeeId)
                    .worktime(workTime)
                    .build();
            return workDayRepository.save(workDay);
        } else {
            return workDays.get(0);
        }
    }

    public void calculate(String begin, String end) throws ParseException {
        Date beginDate = simpleDateFormatJs.parse(begin);
        Date endDate = simpleDateFormatJs.parse(end);
        List<Date> days = timeService.getDatesFromPeriod(beginDate, endDate);
        List<Employee> employees = employeeRepository.findAll();
        for(Date day : days) {
            for(Employee employee : employees){
                long workTime = timeService.getWorkTimeOfDateAndNameInMillis(timeService.convertToLocalDate(day), employee.getName());
                add(day, employee.getId(), workTime);
            }
        }


    }


}
