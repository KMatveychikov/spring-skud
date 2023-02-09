package ru.matvey.springskud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.matvey.springskud.dto.WorkDayResponse;
import ru.matvey.springskud.service.ReportService;
import ru.matvey.springskud.service.WorkDayService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/report/")
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    WorkDayService workDayService;

    @GetMapping("/get_worktime_by_date_and_name/{date}_{name}")
    public String getWorktimeByDateAndName(@PathVariable String date, @PathVariable String name) throws ParseException {
       return reportService.getWorkTimeOfDateAndName(date, name);
    }
    @GetMapping("/get_worktime_by_period_and_name/{begin}_{end}_{name}")
    public String getWorktimeByPeriodAndName(@PathVariable String begin ,@PathVariable String end, @PathVariable String name) throws ParseException {
        return reportService.getWorktimeByPeriodAndName(begin, end, name);
    }
    @GetMapping("/get_workdays_by_period/{begin}_{end}")
    public List<WorkDayResponse> getWorkDaysByPeriod(@PathVariable String begin, @PathVariable String end) throws ParseException {
        return reportService.getWorkDaysResponseByPeriod(begin, end);
    }
    @GetMapping("/calculate_work_days/{begin}_{end}")
    public void calculateWorkDays(@PathVariable String begin, @PathVariable String end) throws ParseException {
         workDayService.calculate(begin, end);
    }
}
