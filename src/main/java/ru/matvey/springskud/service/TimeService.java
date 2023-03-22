package ru.matvey.springskud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matvey.springskud.model.Pass;
import ru.matvey.springskud.repository.PassRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class TimeService {
    @Autowired
    PassRepository passRepository;
    @Autowired
    TimeService timeService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Date getBeginOfDay(Date date) throws ParseException {
        String stringDate = simpleDateFormat.format(date);
        Date resultDate = simpleDateFormat.parse(stringDate.substring(0, 10) + " 00:00:00");
        return resultDate;

    }

    public Date getEndOfDay(Date date) throws ParseException {
        String stringDate = simpleDateFormat.format(date);
        Date resultDate = simpleDateFormat.parse(stringDate.substring(0, 10) + " 23:59:59");
        return resultDate;
    }


    public Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public LocalDate convertToLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public String convertMillisToString(long millis) {
        long seconds = millis / 1000 % 60;
        long minutes = millis / (60 * 1000) % 60;
        long hours = millis / (60 * 60 * 1000) % 24;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public long getWorkTimeOfDateAndNameInMillis(LocalDate date, String name) throws ParseException {
        Date begin = timeService.getBeginOfDay(timeService.convertToDate(date));
        Date end = timeService.getEndOfDay(timeService.convertToDate(date));
        List<Pass> passes = passRepository.findPassesByEmployee_NameAndDateTimeIsBetween(name, begin, end);
        if (passes.size() > 0) {
            return getFirstEnterAndLastExit(passes)[1].getDateTime().getTime() - getFirstEnterAndLastExit(passes)[0].getDateTime().getTime();
        } else return 0;
    }

    public List<Date> getDatesFromPeriod(Date begin, Date end) {
        LocalDate beginDate = timeService.convertToLocalDate(begin);
        LocalDate endDate = timeService.convertToLocalDate(end);
        List<LocalDate> dates = beginDate.datesUntil(endDate.plusDays(1)).toList();
        List<Date> result = new ArrayList<>();

        for (LocalDate date : dates) {
            result.add(timeService.convertToDate(date));
        }
        return result;
    }

    public Pass[] getFirstEnterAndLastExit(List<Pass> passes){
        List<Pass> enters =  passes.stream().filter(p -> Objects.equals(p.getDoorController().getDescription(), "enter")).toList();
        List<Pass> exits =  passes.stream().filter(p -> Objects.equals(p.getDoorController().getDescription(), "exit")).toList();
        Pass firstEnter = enters.get(0);
        Pass lastExit = exits.get(exits.size()-1);
        return new Pass[]{firstEnter, lastExit};
    }
}
