package ru.matvey.springskud.service;

import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matvey.springskud.dto.WorkDayResponse;
import ru.matvey.springskud.model.Employee;
import ru.matvey.springskud.model.Pass;
import ru.matvey.springskud.model.WorkDay;
import ru.matvey.springskud.repository.EmployeeRepository;
import ru.matvey.springskud.repository.PassRepository;
import ru.matvey.springskud.repository.WorkDayRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ReportService {

    @Autowired
    TimeService timeService;
    @Autowired
    PassRepository passRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    WorkDayRepository workDayRepository;
    @Autowired
    WorkDayService workDayService;


    SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat simpleDateFormatDateJs = new SimpleDateFormat("yyyy-MM-dd");

    public String getWorktimeOfDay(List<Pass> passes) {
        if (passes.size() != 0) {
            List<Pass> enters = passes.stream().filter(p -> Objects.equals(p.getDoorController().getDescription(), "enter")).toList();
            List<Pass> exits = passes.stream().filter(p -> Objects.equals(p.getDoorController().getDescription(), "exit")).toList();

            Pass firstEnter = enters.get(0);
            Pass lastExit = Iterables.getLast(exits);

            return timeService.convertMillisToString(lastExit.getDateTime().getTime() - firstEnter.getDateTime().getTime());
        } else {
            return null;
        }

    }

    public String getWorkTimeOfDateAndName(String sdate, String name) throws ParseException {
        sdate = sdate.substring(0, 10);
        Date date = simpleDateFormatDateJs.parse(sdate);
        Date begin = timeService.getBeginOfDay(date);
        Date end = timeService.getEndOfDay(date);
        List<Pass> passes = passRepository.findPassesByEmployee_NameContainingAndDateTimeIsBetween(name, begin, end);
        return getWorktimeOfDay(passes);

    }

    public String getWorktimeByPeriodAndName(String begin, String end, String name) throws ParseException {
        Date beginDate = simpleDateFormatDateJs.parse(begin);
        Date endDate = simpleDateFormatDateJs.parse(end);
        Employee employee = employeeRepository.findByName(name);
        List<WorkDay> workDays = workDayRepository.findWorkDaysByDateBetweenAndEmployeeId(beginDate, endDate, employee.getId());
        List<Date> days = timeService.getDatesFromPeriod(beginDate, endDate);
        List<Employee> employees = employeeRepository.findAll();
        if (workDays.size() < days.size() + employees.size()) {
            workDayService.calculate(begin, end);
            workDays = workDayRepository.findWorkDaysByDateBetweenAndEmployeeId(beginDate, endDate, employee.getId());
        }
        long workTime = 0;
        for (WorkDay workDay : workDays) {
            workTime = workTime + workDay.getWorktime();
        }
        return timeService.convertMillisToString(workTime);
    }

    public List<WorkDayResponse> getWorkDaysResponseByPeriod(String begin, String end) throws ParseException {
        Date beginDate = simpleDateFormatDateJs.parse(begin);
        Date endDate = simpleDateFormatDateJs.parse(end);
        List<WorkDay> workDays = workDayRepository.findWorkDaysByDateBetween(beginDate, endDate);
        List<Date> days = timeService.getDatesFromPeriod(beginDate, endDate);
        List<Employee> employees = employeeRepository.findAll();
        if (workDays.size() < days.size() + employees.size()) {
            workDayService.calculate(begin, end);
            workDays = workDayRepository.findWorkDaysByDateBetween(beginDate, endDate);
        }
        return getWorkDaysResponse(workDays);
    }

    private List<WorkDayResponse> getWorkDaysResponse(List<WorkDay> workdays) {
        List<WorkDayResponse> wdResponses = new ArrayList<>();
        for (WorkDay workDay : workdays) {
            var workDayResponse = WorkDayResponse.builder()
                    .date(simpleDateFormatDate.format(workDay.getDate()))
                    .employeeName(employeeRepository.findById(workDay.getEmployeeId()).getName())
                    .worktime(timeService.convertMillisToString(workDay.getWorktime()))
                    .build();
            wdResponses.add(workDayResponse);
        }
        return wdResponses;
    }
}
